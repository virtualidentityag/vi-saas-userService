package de.caritas.cob.userservice.api.admin.service.consultant;

import de.caritas.cob.userservice.api.adapters.web.dto.ConsultantFilter;
import de.caritas.cob.userservice.api.adapters.web.dto.ConsultantSearchResultDTO;
import de.caritas.cob.userservice.api.adapters.web.dto.Sort;
import de.caritas.cob.userservice.api.adapters.web.dto.Sort.FieldEnum;
import de.caritas.cob.userservice.api.model.Consultant;
import jakarta.persistence.EntityManagerFactory;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.search.engine.search.sort.dsl.SearchSortFactory;
import org.hibernate.search.engine.search.sort.dsl.SortFinalStep;
import org.hibernate.search.engine.search.sort.dsl.SortOrder;
import org.hibernate.search.mapper.orm.Search;
import org.hibernate.search.mapper.orm.session.SearchSession;
import org.springframework.stereotype.Service;

/** Service class to provide filtered search for all {@link Consultant} entities. */
@Service
@RequiredArgsConstructor
@Slf4j
public class ConsultantAdminFilterService {

  private final @NonNull EntityManagerFactory entityManagerFactory;

  /**
   * Searches for consultants by given {@link ConsultantFilter}, limits the result by perPage and
   * generates a {@link ConsultantSearchResultDTO} containing hal links.
   *
   * @param consultantFilter the filter object containing filter values
   * @param page the current requested page
   * @param perPage the amount of items in one page
   * @return the result list
   */
  public ConsultantSearchResultDTO findFilteredConsultants(
      final Integer page,
      final Integer perPage,
      final ConsultantFilter consultantFilter,
      final Sort sort) {

    try (var entityManager = entityManagerFactory.createEntityManager()) {
      var session = entityManager.unwrap(Session.class);

      // Obtain a SearchSession from the Hibernate Session
      synchronized (this) {
        var searchSession = Search.session(session);
        searchSession.massIndexer(Consultant.class).startAndWait();
        // Build the search query
        var result = fetchConsultants(consultantFilter, searchSession, sort, page, perPage);
        // Build the result
        return convertToSearchResultDTO(result, page, perPage);
      }
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
  }

  private ConsultantSearchResultDTO convertToSearchResultDTO(
      SearchPaginatedResult<Consultant> searchPaginatedResult, Integer page, Integer perPage) {
    ConsultantSearchResultBuilder consultantSearchResultBuilder =
        new ConsultantSearchResultBuilder(searchPaginatedResult, page, perPage);
    return consultantSearchResultBuilder.buildSearchResult();
  }

  protected SearchPaginatedResult<Consultant> fetchConsultants(
      ConsultantFilter consultantFilter,
      SearchSession searchSession,
      Sort sortDefinition,
      Integer page,
      Integer perPage) {
    int offset = Math.max((page - 1) * perPage, 0);

    if (consultantFilter == null) {
      var fetchedResult =
          searchSession
              .search(Consultant.class)
              .where(f -> f.matchAll())
              .sort(f -> buildSort(f, sortDefinition))
              .fetch(offset, Math.max(perPage, 1));

      return new SearchPaginatedResult<Consultant>(
          fetchedResult.hits(), fetchedResult.total().hitCount());
    }
    var fetchedResult =
        searchSession
            .search(Consultant.class)
            .where(
                f ->
                    f.bool(
                        bool -> {
                          // If no filters are applied, match all records
                          if (consultantFilter.getUsername() == null
                              && consultantFilter.getLastname() == null
                              && consultantFilter.getEmail() == null
                              && consultantFilter.getAgencyId() == null) {
                            bool.must(f.matchAll()); // Match all documents if no filter is set
                          }
                          // Apply username filter if present
                          if (consultantFilter.getUsername() != null) {
                            bool.must(
                                f.match()
                                    .field("username")
                                    .matching(consultantFilter.getUsername()));
                          }
                          // Apply lastname filter if present
                          if (consultantFilter.getLastname() != null) {
                            bool.must(
                                f.match()
                                    .field("lastName")
                                    .matching(consultantFilter.getLastname()));
                          }
                          // Apply email filter if present
                          if (consultantFilter.getEmail() != null) {
                            bool.must(
                                f.match().field("email").matching(consultantFilter.getEmail()));
                          }
                          // Apply agencyId filter if present
                          if (consultantFilter.getAgencyId() != null) {
                            bool.must(
                                f.nested()
                                    .objectField(
                                        "consultantAgencies") // Navigate to the consultantAgencies
                                    // field
                                    .nest(
                                        f.match()
                                            .field("consultantAgencies.agencyId") // Match agencyId
                                            // within
                                            // consultantAgencies
                                            .matching(consultantFilter.getAgencyId())));
                          }
                        }))
            .sort(f -> buildSort(f, sortDefinition)) // Apply sorting here
            .fetch(offset, Math.max(perPage, 1)); // Apply pagination

    return new SearchPaginatedResult<Consultant>(
        fetchedResult.hits(), fetchedResult.total().hitCount());
  }

  private SortFinalStep buildSort(SearchSortFactory factory, Sort sort) {
    if (sort != null && sort.getField() != null) {
      boolean reverse = Sort.OrderEnum.DESC.equals(sort.getOrder());
      return factory
          .field(sort.getField().getValue() + "_sort")
          .order(reverse ? SortOrder.DESC : SortOrder.ASC);
    } else {
      return factory.field(FieldEnum.LAST_NAME.getValue() + "_sort").order(SortOrder.ASC);
    }
  }
}
