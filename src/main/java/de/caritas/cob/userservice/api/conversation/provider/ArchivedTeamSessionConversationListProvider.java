package de.caritas.cob.userservice.api.conversation.provider;

import static de.caritas.cob.userservice.api.conversation.model.ConversationListType.ARCHIVED_TEAM_SESSION;

import de.caritas.cob.userservice.api.adapters.web.dto.ConsultantSessionListResponseDTO;
import de.caritas.cob.userservice.api.conversation.model.ConversationListType;
import de.caritas.cob.userservice.api.conversation.model.PageableListRequest;
import de.caritas.cob.userservice.api.service.session.SessionService;
import de.caritas.cob.userservice.api.service.sessionlist.ConsultantSessionEnricher;
import de.caritas.cob.userservice.api.service.user.ValidatedUserAccountProvider;
import lombok.NonNull;
import org.springframework.stereotype.Service;

/** {@link ConversationListProvider} to provide archived team session conversations. */
@Service
public class ArchivedTeamSessionConversationListProvider extends DefaultConversationListProvider {

  private final SessionService sessionService;
  private final ValidatedUserAccountProvider userAccountProvider;

  public ArchivedTeamSessionConversationListProvider(
      @NonNull ValidatedUserAccountProvider userAccountProvider,
      @NonNull ConsultantSessionEnricher consultantSessionEnricher,
      @NonNull SessionService sessionService) {
    super(consultantSessionEnricher);
    this.sessionService = sessionService;
    this.userAccountProvider = userAccountProvider;
  }

  /** {@inheritDoc} */
  @Override
  public ConsultantSessionListResponseDTO buildConversations(
      PageableListRequest pageableListRequest) {
    var consultant = this.userAccountProvider.retrieveValidatedConsultant();

    return buildConversations(
        pageableListRequest,
        consultant,
        sessionService.getArchivedTeamSessionsForConsultant(consultant));
  }

  /** {@inheritDoc} */
  @Override
  public ConversationListType providedType() {
    return ARCHIVED_TEAM_SESSION;
  }
}
