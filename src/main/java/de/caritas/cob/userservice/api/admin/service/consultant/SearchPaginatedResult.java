package de.caritas.cob.userservice.api.admin.service.consultant;

import de.caritas.cob.userservice.api.model.TenantAware;
import java.util.List;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class SearchPaginatedResult<T extends TenantAware> {

  List<T> results;
  long total;
}
