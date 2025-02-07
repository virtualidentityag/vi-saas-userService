package de.caritas.cob.userservice.api.admin.service.consultant;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import de.caritas.cob.userservice.api.adapters.web.dto.ConsultantDTO;
import de.caritas.cob.userservice.api.adapters.web.dto.ConsultantFilter;
import de.caritas.cob.userservice.api.adapters.web.dto.ConsultantSearchResultDTO;
import de.caritas.cob.userservice.api.adapters.web.dto.HalLink;
import de.caritas.cob.userservice.api.adapters.web.dto.PaginationLinks;
import de.caritas.cob.userservice.api.admin.service.SearchResultBuilder;
import de.caritas.cob.userservice.api.model.Consultant;
import de.caritas.cob.userservice.generated.api.adapters.web.controller.UseradminApi;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Builder class to generate a {@link ConsultantSearchResultDTO} containing available hal links and
 * result of {@link ConsultantDTO} elements.
 */
public class ConsultantSearchResultBuilder
    extends SearchResultBuilder<ConsultantFilter, ConsultantSearchResultDTO> {

  private final Stream<Consultant> consultantStream;

  public ConsultantSearchResultBuilder(
      SearchPaginatedResult<Consultant> searchPaginatedResult, Integer page, Integer perPage) {
    this.consultantStream = searchPaginatedResult.results.stream();
    this.total = searchPaginatedResult.total;
    this.page = page;
    this.perPage = perPage;
  }

  /**
   * Generates the {@link ConsultantSearchResultDTO} containing all results and navigation hal
   * links.
   *
   * @return the generated {@link ConsultantSearchResultDTO}
   */
  public ConsultantSearchResultDTO buildSearchResult() {
    var resultList =
        consultantStream
            .map(ConsultantResponseDTOBuilder::getInstance)
            .map(ConsultantResponseDTOBuilder::buildResponseDTO)
            .collect(Collectors.toList());

    var paginationLinks =
        new PaginationLinks()
            .self(buildSelfLink())
            .next(buildNextLink())
            .previous(buildPreviousLink());

    return new ConsultantSearchResultDTO().embedded(resultList).links(paginationLinks);
  }

  private HalLink buildSelfLink() {
    return super.buildSelfLink(
        methodOn(UseradminApi.class).getConsultants(page, perPage, filter, sort));
  }

  private HalLink buildNextLink() {
    return super.buildNextLink(
        methodOn(UseradminApi.class).getConsultants(page + 1, perPage, filter, sort));
  }

  private HalLink buildPreviousLink() {
    return buildPreviousLink(
        methodOn(UseradminApi.class).getConsultants(page - 1, perPage, filter, sort));
  }
}
