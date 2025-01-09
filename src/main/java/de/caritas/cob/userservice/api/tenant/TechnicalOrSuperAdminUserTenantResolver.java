package de.caritas.cob.userservice.api.tenant;

import static de.caritas.cob.userservice.api.config.auth.UserRole.TECHNICAL;
import static de.caritas.cob.userservice.api.config.auth.UserRole.TENANT_ADMIN;

import com.google.common.collect.Lists;
import jakarta.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.keycloak.representations.AccessToken;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Component;

@Component
public class TechnicalOrSuperAdminUserTenantResolver implements TenantResolver {

  @Override
  public Optional<Long> resolve(HttpServletRequest request) {
    return isTechnicalOrTenantSuperAdminUserRole(request) ? Optional.of(0L) : Optional.empty();
  }

  private boolean isTechnicalOrTenantSuperAdminUserRole(HttpServletRequest request) {
    return containsAnyRole(request, TECHNICAL.getValue(), TENANT_ADMIN.getValue());
  }

  public Collection<String> extractRealmRoles(Jwt jwt) {
    Map<String, Object> realmAccess = (Map<String, Object>) jwt.getClaims().get("realm_access");
    if (realmAccess != null) {
      var roles = (List<String>) realmAccess.get("roles");
      if (roles != null) {
        return roles;
      }
    }
    return Lists.newArrayList();
  }

  private boolean containsAnyRole(HttpServletRequest request, String... expectedRoles) {
    JwtAuthenticationToken token = ((JwtAuthenticationToken) request.getUserPrincipal());
    var roles = extractRealmRoles(token.getToken());
    if (!roles.isEmpty()) {
      return containsAny(roles, expectedRoles);
    } else {
      return false;
    }
  }

  private boolean containsAny(Collection<String> roles, String... expectedRoles) {
    return Arrays.stream(expectedRoles).anyMatch(roles::contains);
  }

  private boolean hasRoles(AccessToken accessToken) {
    return accessToken.getRealmAccess() != null && accessToken.getRealmAccess().getRoles() != null;
  }

  @Override
  public boolean canResolve(HttpServletRequest request) {
    return resolve(request).isPresent();
  }
}
