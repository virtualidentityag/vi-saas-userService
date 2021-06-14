package de.caritas.cob.userservice.api.admin.service.rocketchat;

import de.caritas.cob.userservice.api.facade.RocketChatFacade;
import de.caritas.cob.userservice.api.manager.consultingtype.ConsultingTypeManager;
import de.caritas.cob.userservice.api.model.rocketchat.group.GroupMemberDTO;
import de.caritas.cob.userservice.api.repository.consultant.Consultant;
import de.caritas.cob.userservice.api.repository.session.Session;
import de.caritas.cob.userservice.api.repository.session.SessionStatus;
import de.caritas.cob.userservice.api.service.LogService;
import de.caritas.cob.userservice.api.service.helper.KeycloakAdminClientService;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
abstract class RocketChatGroupOperation {

  private final @NonNull RocketChatFacade rocketChatFacade;
  private final @NonNull KeycloakAdminClientService keycloakAdminClientService;

  protected Consumer<String> logMethod = LogService::logInfo;

  void addConsultantToGroupOfSession(Session session, Consultant consultant, ConsultingTypeManager consultingTypeManager) {
    var operationConditionProvider =
        new RocketChatOperationConditionProvider(this.keycloakAdminClientService, session,
            consultant, consultingTypeManager);

    if (operationConditionProvider.canAddToRocketChatGroup()) {
      rocketChatFacade.addUserToRocketChatGroup(consultant.getRocketChatId(), session.getGroupId());
      logMethod.accept(String.format("Consultant added to rc group %s (%s).",
          session.getGroupId(), resolveTypeOfSession(session)));
    }

    if (operationConditionProvider.canAddToRocketChatFeedbackGroup()) {
      rocketChatFacade
          .addUserToRocketChatGroup(consultant.getRocketChatId(), session.getFeedbackGroupId());
      logMethod.accept(String.format("Consultant added to rc feedback group %s (%s).",
          session.getFeedbackGroupId(), resolveTypeOfSession(session)));
    }
  }

  String resolveTypeOfSession(Session session) {
    if (SessionStatus.NEW.equals(session.getStatus())) {
      return "enquiry";
    }
    return session.isTeamSession() ? "team-session" : "standard-session";
  }

  void removeConsultantsFromSessionGroups(Session session,
      List<Consultant> consultants) {
    removeConsultantsFromRocketChatGroup(session.getGroupId(), consultants);
    removeConsultantsFromRocketChatGroup(session.getFeedbackGroupId(), consultants);
  }

  void removeConsultantsFromSessionGroup(String rcGroupId, List<Consultant> consultants) {
    removeConsultantsFromRocketChatGroup(rcGroupId, consultants);
  }

  private void removeConsultantsFromRocketChatGroup(String rcGroupId,
      List<Consultant> consultants) {
    List<String> groupMemberList = obtainRocketChatGroupMemberIds(rcGroupId);

    consultants.stream()
        .map(Consultant::getRocketChatId)
        .filter(groupMemberList::contains)
        .forEach(rcUserId -> rocketChatFacade.removeUserFromGroup(rcUserId, rcGroupId));
  }

  private List<String> obtainRocketChatGroupMemberIds(String groupId) {
    return this.rocketChatFacade.retrieveRocketChatMembers(groupId).stream()
        .map(GroupMemberDTO::get_id)
        .collect(Collectors.toList());
  }
}
