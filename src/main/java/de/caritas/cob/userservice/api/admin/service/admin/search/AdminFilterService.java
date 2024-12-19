package de.caritas.cob.userservice.api.admin.service.admin.search;

import static java.util.Objects.nonNull;

import de.caritas.cob.userservice.api.adapters.web.dto.AdminFilter;
import de.caritas.cob.userservice.api.adapters.web.dto.AdminResponseDTO;
import de.caritas.cob.userservice.api.adapters.web.dto.AdminSearchResultDTO;
import de.caritas.cob.userservice.api.adapters.web.dto.Sort;
import de.caritas.cob.userservice.api.adapters.web.dto.Sort.FieldEnum;
import de.caritas.cob.userservice.api.admin.service.admin.AdminResponseDTOBuilder;
import de.caritas.cob.userservice.api.model.Admin;
import jakarta.persistence.EntityManagerFactory;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Stream;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.search.engine.search.sort.dsl.SearchSortFactory;
import org.hibernate.search.engine.search.sort.dsl.SortFinalStep;
import org.hibernate.search.engine.search.sort.dsl.SortOrder;
import org.hibernate.search.mapper.orm.Search;
import org.hibernate.search.mapper.orm.session.SearchSession;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminFilterService {

  private final @NonNull EntityManagerFactory entityManagerFactory;

  public AdminSearchResultDTO findFilteredAdmins(
      final Integer page, final Integer perPage, final AdminFilter adminFilter, Sort sort) {

    try (var entityManager = entityManagerFactory.createEntityManager()) {
      var session = entityManager.unwrap(Session.class);

      // Obtain a SearchSession from the Hibernate Session
      SearchSession searchSession = Search.session(session);

      // Ensure the sort is valid
      sort = getValidSorter(sort);

      // Build the search query
      var admins = fetchAdmins(adminFilter, searchSession, sort, page, perPage);

      // Build the result
      return convertToSearchResultDTO(admins);
    }
  }

  private AdminSearchResultDTO convertToSearchResultDTO(List<Admin> admins) {
    AdminSearchResultDTO result = new AdminSearchResultDTO();
    for (Admin admin : admins) {
      AdminResponseDTO adminResponseDTO =
          AdminResponseDTOBuilder.getInstance(admin).buildAgencyAdminResponseDTO();
      result.addEmbeddedItem(adminResponseDTO);
    }
    return result;
  }

  protected List<Admin> fetchAdmins(
      AdminFilter adminFilter,
      SearchSession searchSession,
      Sort sortDefinition,
      Integer page,
      Integer perPage) {
    int offset = Math.max((page - 1) * perPage, 0);

    return searchSession
        .search(Admin.class)
        .where(
            f ->
                f.bool(
                    bool -> {
                      // Apply username filter if present
                      if (adminFilter.getUsername() != null) {
                        bool.must(f.match().field("username").matching(adminFilter.getUsername()));
                      }
                      // Apply lastname filter if present
                      if (adminFilter.getLastname() != null) {
                        bool.must(f.match().field("lastname").matching(adminFilter.getLastname()));
                      }
                      // Apply email filter if present
                      if (adminFilter.getEmail() != null) {
                        bool.must(f.match().field("email").matching(adminFilter.getEmail()));
                      }
                      // Apply agencyId filter if present
                      if (adminFilter.getAgencyId() != null) {
                        bool.must(f.match().field("agencyId").matching(adminFilter.getAgencyId()));
                      }
                    }))
        .sort(f -> buildSort(f, sortDefinition)) // Apply sorting here
        .fetchHits(offset, Math.max(perPage, 1)); // Apply pagination
  }

  private SortFinalStep buildSort(SearchSortFactory factory, Sort sort) {
    if (sort != null && sort.getField() != null) {
      boolean reverse = Sort.OrderEnum.DESC.equals(sort.getOrder());
      return factory
          .field(sort.getField().getValue())
          .order(reverse ? SortOrder.DESC : SortOrder.ASC);
    } else {
      return factory.field(FieldEnum.LAST_NAME.getValue()).order(SortOrder.ASC);
    }
  }

  private Sort getValidSorter(Sort sort) {
    if (sort == null
        || Stream.of(Sort.FieldEnum.values()).noneMatch(providedSortFieldIgnoringCase(sort))) {
      sort = new Sort();
      sort.setField(FieldEnum.LAST_NAME);
      sort.setOrder(Sort.OrderEnum.ASC);
    }
    return sort;
  }

  private Predicate<FieldEnum> providedSortFieldIgnoringCase(Sort sort) {
    return field -> {
      if (nonNull(sort.getField())) {
        return field.getValue().equalsIgnoreCase(sort.getField().getValue());
      }
      return false;
    };
  }
}
