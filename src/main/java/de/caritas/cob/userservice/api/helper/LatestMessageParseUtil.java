package de.caritas.cob.userservice.api.helper;

import de.caritas.cob.userservice.api.adapters.web.dto.ConsultantSessionResponseDTO;
import de.caritas.cob.userservice.api.adapters.web.dto.UserSessionResponseDTO;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LatestMessageParseUtil {

  public static Date parseLatestMessage(UserSessionResponseDTO userSession) {
    Object latestMessage = userSession.getLatestMessage();
    return parseLatestMessageObject(latestMessage);
  }

  public static Date parseLatestMessageForConsultantSession(
      ConsultantSessionResponseDTO userSession) {
    Object latestMessage = userSession.getLatestMessage();
    return parseLatestMessageObject(latestMessage);
  }

  private static Date parseLatestMessageObject(Object latestMessage) {
    if (latestMessage instanceof Date date) {
      return date;
    } else if (latestMessage instanceof String string) {
      try {
        return new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ").parse(string);
      } catch (ParseException e) {
        log.error("Could not parse timestamp string");
        return null;
      }
    }
    return null;
  }
}
