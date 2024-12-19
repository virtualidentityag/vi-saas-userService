package de.caritas.cob.userservice.api.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.Objects;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.ParamDef;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(
    name = "admin",
    indexes = {
      @Index(
          columnList = "username, first_name, last_name, email",
          name = "idx_username_first_name_last_name_email",
          unique = true),
    })
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
@EntityListeners(AuditingEntityListener.class)
@FilterDef(
    name = "tenantFilter",
    parameters = {@ParamDef(name = "tenantId", type = Long.class)})
@Filter(name = "tenantFilter", condition = "tenant_id = :tenantId")
public class Admin implements TenantAware {

  protected static final String EMAIL_ANALYZER = "emailAnalyzer";

  public enum AdminType {
    AGENCY,
    TENANT,
    SUPER
  }

  @Id
  @Column(name = "admin_id", updatable = false, nullable = false)
  @Size(max = 36)
  @NonNull
  private String id;

  @Column(name = "tenant_id")
  private Long tenantId;

  @Column(name = "username", updatable = false, nullable = false)
  @Size(max = 255)
  @NonNull
  private String username;

  @Column(name = "first_name", nullable = false)
  @Size(max = 255)
  @NonNull
  private String firstName;

  @Column(name = "last_name", nullable = false)
  @Size(max = 255)
  @NonNull
  private String lastName;

  @Column(name = "email", nullable = false)
  @Size(max = 255)
  @NonNull
  private String email;

  @Enumerated(EnumType.STRING)
  @Column(length = 6, nullable = false)
  private AdminType type;

  @Column(name = "rc_user_id")
  private String rcUserId;

  @Column(name = "id_old", updatable = false)
  private Long oldId;

  @CreatedDate
  @Column(name = "create_date", columnDefinition = "datetime")
  private LocalDateTime createDate;

  @LastModifiedDate
  @Column(name = "update_date", columnDefinition = "datetime")
  private LocalDateTime updateDate;

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Admin)) {
      return false;
    }
    Admin admin = (Admin) o;
    return id.equals(admin.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }

  public interface AdminBase {

    String getId();

    String getFirstName();

    String getLastName();

    String getEmail();

    Long getTenantId();
  }
}
