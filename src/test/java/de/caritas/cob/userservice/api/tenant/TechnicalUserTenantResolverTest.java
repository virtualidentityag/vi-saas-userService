package de.caritas.cob.userservice.api.tenant;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import jakarta.servlet.http.HttpServletRequest;
import org.assertj.core.util.Sets;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;
import org.keycloak.representations.AccessToken;
import org.keycloak.representations.AccessToken.Access;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class TechnicalUserTenantResolverTest {
  public static final long TECHNICAL_CONTEXT = 0L;
  @Mock HttpServletRequest authenticatedRequest;

  @Mock KeycloakAuthenticationToken token;

  @Mock AccessToken accessToken;

  @Mock Access access;

  @InjectMocks TechnicalOrSuperAdminUserTenantResolver technicalOrSuperadminUserTenantResolver;

  @BeforeEach
  public void setUp() {
    token = Mockito.mock(KeycloakAuthenticationToken.class, Mockito.RETURNS_DEEP_STUBS);
    accessToken = Mockito.mock(AccessToken.class, Mockito.RETURNS_DEEP_STUBS);
  }

  @Test
  void resolve_should_ResolveTechnicalTenantId_ForTechnicalUserRole() {
    // given
    when(authenticatedRequest.getUserPrincipal()).thenReturn(token);
    when(token.getAccount().getKeycloakSecurityContext().getToken()).thenReturn(accessToken);
    when(accessToken.getRealmAccess().getRoles()).thenReturn(Sets.newLinkedHashSet("technical"));
    var resolved = technicalOrSuperadminUserTenantResolver.resolve(authenticatedRequest);
    // then
    assertThat(resolved).contains(TECHNICAL_CONTEXT);
  }

  @Test
  void resolve_should_NotResolveTenantId_When_NonTechnicalUserRole() {
    // given
    when(authenticatedRequest.getUserPrincipal()).thenReturn(token);
    when(token.getAccount().getKeycloakSecurityContext().getToken()).thenReturn(accessToken);
    when(accessToken.getRealmAccess().getRoles()).thenReturn(Sets.newLinkedHashSet("another-role"));
    var resolved = technicalOrSuperadminUserTenantResolver.resolve(authenticatedRequest);
    // then
    assertThat(resolved).isEmpty();
  }
}
