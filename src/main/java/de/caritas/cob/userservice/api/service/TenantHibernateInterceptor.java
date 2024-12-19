package de.caritas.cob.userservice.api.service;

import de.caritas.cob.userservice.api.model.TenantAware;
import de.caritas.cob.userservice.api.tenant.TenantContext;
import java.util.Iterator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Interceptor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class TenantHibernateInterceptor implements Interceptor {

  @Override
  public void preFlush(Iterator<Object> entities) {
    Object entity;
    while (entities.hasNext()) {
      entity = entities.next();
      if (entity instanceof TenantAware tenantAware) {
        if (tenantAware.getTenantId() == null && !TenantContext.isTechnicalOrSuperAdminContext()) {
          tenantAware.setTenantId(TenantContext.getCurrentTenant());
        }
      }
    }

    Interceptor.super.preFlush(entities);
  }
}
