package de.caritas.cob.userservice.testHelper;

import static com.google.common.collect.ImmutableMap.of;

import de.caritas.cob.userservice.api.container.RocketChatCredentials;
import de.caritas.cob.userservice.api.helper.AuthenticatedUser;
import de.caritas.cob.userservice.api.helper.Helper;
import de.caritas.cob.userservice.api.manager.consultingType.ConsultingTypeSettings;
import de.caritas.cob.userservice.api.manager.consultingType.SessionDataInitializing;
import de.caritas.cob.userservice.api.manager.consultingType.registration.Registration;
import de.caritas.cob.userservice.api.manager.consultingType.registration.mandatoryFields.MandatoryFields;
import de.caritas.cob.userservice.api.model.AbsenceDTO;
import de.caritas.cob.userservice.api.model.AgencyDTO;
import de.caritas.cob.userservice.api.model.ChatDTO;
import de.caritas.cob.userservice.api.model.ConsultantSessionResponseDTO;
import de.caritas.cob.userservice.api.model.CreateChatResponseDTO;
import de.caritas.cob.userservice.api.model.NewRegistrationDto;
import de.caritas.cob.userservice.api.model.NewRegistrationResponseDto;
import de.caritas.cob.userservice.api.model.SessionAttachmentDTO;
import de.caritas.cob.userservice.api.model.SessionConsultantForUserDTO;
import de.caritas.cob.userservice.api.model.SessionDTO;
import de.caritas.cob.userservice.api.model.UserDTO;
import de.caritas.cob.userservice.api.model.UserSessionResponseDTO;
import de.caritas.cob.userservice.api.model.chat.UserChatDTO;
import de.caritas.cob.userservice.api.model.rocketChat.RocketChatUserDTO;
import de.caritas.cob.userservice.api.model.rocketChat.group.GroupMemberDTO;
import de.caritas.cob.userservice.api.model.rocketChat.login.DataDTO;
import de.caritas.cob.userservice.api.model.rocketChat.login.LoginResponseDTO;
import de.caritas.cob.userservice.api.model.rocketChat.message.attachment.AttachmentDTO;
import de.caritas.cob.userservice.api.model.rocketChat.message.attachment.FileDTO;
import de.caritas.cob.userservice.api.model.rocketChat.room.RoomsLastMessageDTO;
import de.caritas.cob.userservice.api.model.rocketChat.room.RoomsUpdateDTO;
import de.caritas.cob.userservice.api.model.rocketChat.subscriptions.SubscriptionsUpdateDTO;
import de.caritas.cob.userservice.api.model.rocketChat.user.UserInfoResponseDTO;
import de.caritas.cob.userservice.api.repository.chat.Chat;
import de.caritas.cob.userservice.api.repository.chat.ChatInterval;
import de.caritas.cob.userservice.api.repository.chatAgency.ChatAgency;
import de.caritas.cob.userservice.api.repository.consultant.Consultant;
import de.caritas.cob.userservice.api.repository.consultantAgency.ConsultantAgency;
import de.caritas.cob.userservice.api.repository.session.ConsultingType;
import de.caritas.cob.userservice.api.repository.session.Session;
import de.caritas.cob.userservice.api.repository.session.SessionFilter;
import de.caritas.cob.userservice.api.repository.session.SessionStatus;
import de.caritas.cob.userservice.api.repository.user.User;
import de.caritas.cob.userservice.api.repository.userAgency.UserAgency;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class TestConstants {

  /*
   * Common
   */
  public static final String APPLICATION_BASE_URL = "https://beratung.caritas.de";
  public static final String HOST_BASE_URL = "https://beratung.caritas.de";
  public static final String APPLICATION_BASE_URL_FIELD_NAME = "applicationBaseUrl";
  public static final String POSTCODE = "12345";
  public static final String INVALID_POSTCODE = "12";
  public static final String NAME = "testname";
  public static final String CITY = "testcity";
  public static final String ERROR = "error";
  public static final String NULL = null;
  public static final boolean SUCCESS = true;
  public static final boolean FAILED = false;
  public static final Exception EXCEPTION = new Exception();
  public static final Long MESSAGE_DATE = 123456L;

  /*
   * Date and Time
   */
  public static final Date NOW = new Date();
  public static final Date NOW_MINUS_1_DAY = new Date(NOW.getTime() - 86400000);
  public static final Date NOW_MINUS_2_DAYS = new Date(NOW.getTime() - (2 * 86400000));
  public static final Date NOW_MINUS_3_DAYS = new Date(NOW.getTime() - (3 * 86400000));

  /**
   * ConsultingTypes
   */
  public static final int INVALID_CONSULTING_TYPE = 9999;
  public static final String CONSULTING_TYPE_SUCHT_URL_NAME = ConsultingType.SUCHT.getUrlName();
  public static final ConsultingType CONSULTING_TYPE_SUCHT = ConsultingType.SUCHT;
  public static final ConsultingType CONSULTING_TYPE_U25 = ConsultingType.U25;
  public static final ConsultingType CONSULTING_TYPE_PREGNANCY = ConsultingType.PREGNANCY;
  public static final ConsultingType CONSULTING_TYPE_AIDS = ConsultingType.AIDS;
  public static final ConsultingType CONSULTING_TYPE_CHILDREN = ConsultingType.CHILDREN;
  public static final ConsultingType CONSULTING_TYPE_CURE = ConsultingType.CURE;
  public static final ConsultingType CONSULTING_TYPE_DEBT = ConsultingType.DEBT;
  public static final ConsultingType CONSULTING_TYPE_DISABILITY = ConsultingType.DISABILITY;
  public static final ConsultingType CONSULTING_TYPE_LAW = ConsultingType.LAW;
  public static final ConsultingType CONSULTING_TYPE_OFFENDER = ConsultingType.OFFENDER;
  public static final ConsultingType CONSULTING_TYPE_PARENTING = ConsultingType.PARENTING;
  public static final ConsultingType CONSULTING_TYPE_PLANB = ConsultingType.PLANB;
  public static final ConsultingType CONSULTING_TYPE_REHABILITATION = ConsultingType.REHABILITATION;
  public static final ConsultingType CONSULTING_TYPE_SENIORITY = ConsultingType.SENIORITY;
  public static final ConsultingType CONSULTING_TYPE_SOCIAL = ConsultingType.SOCIAL;
  public static final ConsultingType CONSULTING_TYPE_KREUZBUND = ConsultingType.KREUZBUND;
  public static final ConsultingType CONSULTING_TYPE_MIGRATION = ConsultingType.MIGRATION;
  public static final ConsultingType CONSULTING_TYPE_EMIGRATION = ConsultingType.EMIGRATION;
  public static final ConsultingType CONSULTING_TYPE_HOSPICE = ConsultingType.HOSPICE;
  public static final ConsultingType CONSULTING_TYPE_REGIONAL = ConsultingType.REGIONAL;

  /*
   * Session data
   */
  public static final String ACLOHOL = "alcohol";
  public static final String DRUGS = "drugs";
  public static final String OTHERS = "others";
  public static final String ADDICTIVE_DRUGS = "addictiveDrugs";
  public static final String RELATION = "relation";
  public static final String AGE = "age";
  public static final String GENDER = "gender";
  public static final String STATE = "state";


  /*
   * RocketChat
   */
  public static final String ROCKETCHAT_ID = "xN3Mobksn3xdp7gEk";
  public static final String ROCKETCHAT_ID_2 = "tZ6Mdfks5rtxdp7a8i";
  public static final String RC_TOKEN = "2fUGwNSqvpiEDTsMJQ54XeYdx0XzzCWdu0PP0lXFNu8";
  public static final String RC_USER_ID = "ogCRwt3ieDiBNJFaR";
  public static final String RC_USER_ID_2 = "sd3ssdfFFGSGDGWww";
  public static final String RC_USER_ID_3 = "abcdefsDefdfrWrt";
  public static final String RC_USERNAME = "rcUsername";
  public static final String RC_SYSTEM_USERNAME = "system";
  public static final String ROCKET_CHAT_SYSTEM_USER_ID = "xN3Msb3ksnfxda7gEk";
  public static final String RC_TOKEN_HEADER_PARAMETER_NAME = "RCToken";
  public static final String RC_USER_ID_HEADER_PARAMETER_NAME = "RCUserId";
  public static final String ROCKET_CHAT_TECHNICAL_USER_ID = "dasd83juiosdf";
  public static final String RC_TECHNICAL_USERNAME = "technical";
  public static final String RC_GROUP_ID = "jjjuuu";
  public static final String RC_GROUP_ID_2 = "sdfsdff";
  public static final String RC_GROUP_ID_3 = "gggewwww";
  public static final String RC_GROUP_ID_4 = "ssssuuu";
  public static final String RC_GROUP_ID_5 = "aldoeke";
  public static final String RC_GROUP_ID_6 = "vmndsjk";
  public static final String RC_GROUP_ID_7 = "juuuzte";
  public static final String RC_STATUS_ONLINE = "online";
  public static final String RC_UTC_OFFSET = "1";
  public static final String RC_FEEDBACK_GROUP_ID = "yyyZZZ";
  public static final String RC_FEEDBACK_GROUP_ID_2 = "gggasaa";
  public static final String RC_FEEDBACK_GROUP_ID_3 = "llldkdks";
  public static final String RC_ATTACHMENT_TITLE = "filename.jpg";
  public static final String RC_ATTACHMENT_FILE_TYPE = "image/jpeg";
  public static final String RC_ATTACHMENT_IMAGE_PREVIEW =
      "/9j/2wBDAAYEBQYFBAYGBQYHBwYIChAKCgkJChQODwwQFxQYGBcUFhYaHSUfGhsjHBYWICwgIyYnKSopGR8tMC0oMCUoKSj/2wBDAQcHBwoIChMKChMoGhYaKCgoKCgoKCgoKCgoKCgoKCgoKCgoKCgoKCgoKCgoKCgoKCgoKCgoKCgoKCgoKCgoKCj/wAARCAAVACADASIAAhEBAxEB/8QAGQAAAgMBAAAAAAAAAAAAAAAAAAYDBAUH/8QAKBAAAQMDBAAFBQAAAAAAAAAAAQIDBAAFBhESITETIkFRgQcjQmHw/8QAGAEAAwEBAAAAAAAAAAAAAAAAAgMEAQX/xAAdEQADAAICAwAAAAAAAAAAAAAAAQIDEQQSIjEy/9oADAMBAAIRAxEAPwCjapLjU7YhHlA+77I+aY0XNgHxZGo52pI9RSvIx6LerBPbsd0U1JLv56jcNff4rHstquEF+PFuc0uBscacgfNc+uJExpMzrpGxdszGL5avw5BYQ+gHno1LdMzs0yKXX56FSFebeDyKzc6iW+4WZ5TzBelAbG3D6HQ/qlbHcPiSVoTKhLUdnSOdDxyafgfWFsKZO24JFiy8ZZfXFaCndSfL1/a1PZ7ZEl3p1l9lJbSNQANKKKg5NNNJDn7QqJsEef8AUB+1OrWIpOoHe3vqnqxYnGs8mT4D7i1ngKUOhRRV+L4AT8j/2Q==";
  public static final List<RoomsUpdateDTO> EMPTY_ROOMS_UPDATE_DTO_LIST =
      new ArrayList<RoomsUpdateDTO>();
  public static final GroupMemberDTO GROUP_MEMBER_SYS_USER =
      new GroupMemberDTO(ROCKET_CHAT_SYSTEM_USER_ID, RC_STATUS_ONLINE, RC_SYSTEM_USERNAME,
          RC_SYSTEM_USERNAME, RC_UTC_OFFSET);
  public static final GroupMemberDTO GROUP_MEMBER_TECH_USER =
      new GroupMemberDTO(ROCKET_CHAT_TECHNICAL_USER_ID, RC_STATUS_ONLINE, RC_TECHNICAL_USERNAME,
          RC_SYSTEM_USERNAME, RC_UTC_OFFSET);
  public static final ResponseEntity<LoginResponseDTO> LOGIN_RESPONSE_ENTITY_BAD_REQUEST =
      new ResponseEntity<LoginResponseDTO>(HttpStatus.BAD_REQUEST);
  public static final DataDTO DATA_DTO_LOGIN = new DataDTO(RC_USER_ID, RC_TOKEN, null);
  public static final DataDTO DATA_DTO_LOGIN_NO_TOKEN = new DataDTO(RC_USER_ID, null, null);
  public static final String STATUS_OK = "OK";
  public static final LoginResponseDTO LOGIN_RESPONSE_DTO =
      new LoginResponseDTO(STATUS_OK, DATA_DTO_LOGIN);
  public static final LoginResponseDTO LOGIN_RESPONSE_DTO_NO_TOKEN =
      new LoginResponseDTO(STATUS_OK, DATA_DTO_LOGIN_NO_TOKEN);
  public static final ResponseEntity<LoginResponseDTO> LOGIN_RESPONSE_ENTITY_OK =
      new ResponseEntity<LoginResponseDTO>(LOGIN_RESPONSE_DTO, HttpStatus.OK);
  public static final ResponseEntity<LoginResponseDTO> LOGIN_RESPONSE_ENTITY_OK_NO_TOKEN =
      new ResponseEntity<LoginResponseDTO>(LOGIN_RESPONSE_DTO_NO_TOKEN, HttpStatus.OK);
  public static final List<SubscriptionsUpdateDTO> SUBSCRIPTIONS_UPDATE_LIST_DTO_WITH_ONE_FEEDBACK_UNREAD =
      Arrays.asList(
          new SubscriptionsUpdateDTO("A", true, false, 0, 0, 0, NOW, RC_GROUP_ID, "A", "A", "P",
              null, null, null, null),
          new SubscriptionsUpdateDTO("A", true, false, 0, 0, 0, NOW, RC_GROUP_ID_2, "A", "A", "P",
              null, null, null, null),
          new SubscriptionsUpdateDTO("A", true, false, 0, 0, 0, NOW, RC_GROUP_ID_3, "A", "A", "P",
              null, null, null, null),
          new SubscriptionsUpdateDTO("A", true, false, 1, 0, 0, NOW, RC_FEEDBACK_GROUP_ID, "A", "A",
              "P", null, null, null, null),
          new SubscriptionsUpdateDTO("A", true, false, 0, 0, 0, NOW, RC_FEEDBACK_GROUP_ID_2, "A",
              "A", "P", null, null, null, null),
          new SubscriptionsUpdateDTO("A", true, false, 0, 0, 0, NOW, RC_FEEDBACK_GROUP_ID_3, "A",
              "A", "P", null, null, null, null));


  /**
   * Rocket.Chat credentials
   */
  public final static String TECHNICAL_USER_A_USERNAME = "techUserAName";
  public final static String TECHNICAL_USER_A_TOKEN = "techUserAToken";
  public final static String TECHNICAL_USER_A_ID = "techUserAID";

  public final static String TECHNICAL_USER_B_USERNAME = "techUserBName";
  public final static String TECHNICAL_USER_B_TOKEN = "techUserBToken";
  public final static String TECHNICAL_USER_B_ID = "techUserBID";

  public final static String TECHNICAL_USER_C_USERNAME = "techUserCName";
  public final static String TECHNICAL_USER_C_TOKEN = "techUserCToken";
  public final static String TECHNICAL_USER_C_ID = "techUserCID";

  public final static String SYSTEM_USER_A_USERNAME = "sysUserAName";
  public final static String SYSTEM_USER_A_TOKEN = "sysUserAToken";
  public final static String SYSTEM_USER_A_ID = "sysUserAID";

  public final static String SYSTEM_USER_B_USERNAME = "sysUserBName";
  public final static String SYSTEM_USER_B_TOKEN = "sysUserBToken";
  public final static String SYSTEM_USER_B_ID = "sysUserBID";

  public final static String SYSTEM_USER_C_USERNAME = "sysUserBName";
  public final static String SYSTEM_USER_C_TOKEN = "sysUserBToken";
  public final static String SYSTEM_USER_C_ID = "sysUserBID";

  public static final RocketChatCredentials RC_CREDENTIALS =
      RocketChatCredentials.builder().RocketChatToken(RC_TOKEN).RocketChatUserId(RC_USER_ID)
          .RocketChatUsername(RC_USERNAME).TimeStampCreated(LocalDateTime.now()).build();

  public static final RocketChatCredentials RC_CREDENTIALS_TECHNICAL_A =
      RocketChatCredentials.builder().RocketChatToken(TECHNICAL_USER_A_TOKEN)
          .RocketChatUserId(TECHNICAL_USER_A_ID).RocketChatUsername(TECHNICAL_USER_A_USERNAME)
          .TimeStampCreated(LocalDateTime.now().minusMinutes(5)).build();

  public static final RocketChatCredentials RC_CREDENTIALS_TECHNICAL_B =
      RocketChatCredentials.builder().RocketChatToken(TECHNICAL_USER_B_TOKEN)
          .RocketChatUserId(TECHNICAL_USER_B_ID).RocketChatUsername(TECHNICAL_USER_B_USERNAME)
          .TimeStampCreated(LocalDateTime.now().minusMinutes(1)).build();

  public static final RocketChatCredentials RC_CREDENTIALS_TECHNICAL_C =
      RocketChatCredentials.builder().RocketChatToken(TECHNICAL_USER_C_TOKEN)
          .RocketChatUserId(TECHNICAL_USER_C_ID).RocketChatUsername(TECHNICAL_USER_C_USERNAME)
          .TimeStampCreated(LocalDateTime.now().minusMinutes(10)).build();

  public static final RocketChatCredentials RC_CREDENTIALS_SYSTEM_A =
      RocketChatCredentials.builder().RocketChatToken(SYSTEM_USER_A_TOKEN)
          .RocketChatUserId(SYSTEM_USER_A_ID).RocketChatUsername(SYSTEM_USER_A_USERNAME)
          .TimeStampCreated(LocalDateTime.now().minusMinutes(5)).build();

  public static final RocketChatCredentials RC_CREDENTIALS_SYSTEM_B =
      RocketChatCredentials.builder().RocketChatToken(SYSTEM_USER_B_TOKEN)
          .RocketChatUserId(SYSTEM_USER_B_ID).RocketChatUsername(SYSTEM_USER_A_USERNAME)
          .TimeStampCreated(LocalDateTime.now().minusMinutes(1)).build();

  public static final RocketChatCredentials RC_CREDENTIALS_SYSTEM_C =
      RocketChatCredentials.builder().RocketChatToken(SYSTEM_USER_C_TOKEN)
          .RocketChatUserId(SYSTEM_USER_C_ID).RocketChatUsername(SYSTEM_USER_C_USERNAME)
          .TimeStampCreated(LocalDateTime.now().minusMinutes(10)).build();


  /*
   * Agencies
   */
  public static final Long AGENCY_ID = 1L;
  public static final Long AGENCY_ID_2 = 2L;
  public static final Long AGENCY_ID_3 = 3L;
  public static final String AGENCY_NAME = "Test Beratungsstelle";
  public static final AgencyDTO EMPTY_AGENCY_DTO = new AgencyDTO();
  public static final String DESCRIPTION = "description";
  public static final boolean IS_TEAM_AGENCY = true;
  public static final boolean IS_NOT_OFFLINE = false;
  public static final AgencyDTO AGENCY_DTO_SUCHT = new AgencyDTO(AGENCY_ID, AGENCY_NAME, POSTCODE,
      CITY, DESCRIPTION, IS_TEAM_AGENCY, IS_NOT_OFFLINE, CONSULTING_TYPE_SUCHT);
  public static final AgencyDTO AGENCY_DTO_U25 = new AgencyDTO(AGENCY_ID, AGENCY_NAME, POSTCODE,
      CITY, DESCRIPTION, IS_TEAM_AGENCY, IS_NOT_OFFLINE, CONSULTING_TYPE_U25);
  public static final AgencyDTO AGENCY_DTO_KREUZBUND = new AgencyDTO(AGENCY_ID, AGENCY_NAME,
      POSTCODE, CITY, DESCRIPTION, IS_TEAM_AGENCY, IS_NOT_OFFLINE, CONSULTING_TYPE_KREUZBUND);
  public static final List<AgencyDTO> AGENCY_DTO_LIST = Collections.singletonList(AGENCY_DTO_SUCHT);
  /*
   * Users / Consultants
   */
  public static final String USER_ID = "9b71cc46-650d-42bb-8299-f8e3f6d7249f";
  public static final String USER_ID_2 = "3234234-3344-32gg-2344-sdfsdf33333";
  public static final String USER_ID_3 = "df3322-fd33-asdf-3333-2332jk23j32j";
  public static final String CONSULTANT_ID = "87ddcss-650d-42bb-8299-f8e3f6j8dk3";
  public static final String CONSULTANT_ID_2 = "kd93kd-fd33-asdf-3333-2332jkkk39d";
  public static final String CONSULTANT_ID_3 = "ksf93j-3344-32gg-2344-93kd93jaf";
  public static final String MAIN_CONSULTANT_ID = "asdj78wfjsdf";
  public static final String RC_USER_ID_MAIN_CONSULTANT = "xxxyyy";
  public static final String TEAM_CONSULTANT_ID = "34t789hqeg-q34g8weq9rhg-q34g09";
  public static final String ENCODING_PREFIX = "enc.";
  public static final String USERNAME_CONSULTANT_DECODED = "Consultantname!#123";
  public static final String USERNAME_CONSULTANT_ENCODED =
      ENCODING_PREFIX + "INXW443VNR2GC3TUNZQW2ZJBEMYTEMY.";
  public static final String USERNAME = "username";
  public static final String USERNAME_SIMPLE_ENCODED = ENCODING_PREFIX + "OVZWK4TOMFWWK===";
  public static final String USERNAME_DECODED = "Username!#123";
  public static final String USERNAME_ENCODED = ENCODING_PREFIX + "KVZWK4TOMFWWKIJDGEZDG...";
  public static final String USERNAME_INVALID_ENCODED = ENCODING_PREFIX + "223======";
  public static final boolean IS_ABSENT = true;
  public static final String ABSENCE_MESSAGE = "Bin nicht da";
  public static final String FIRST_NAME = "vorname";
  public static final String LAST_NAME = "nachname";
  public static final String EMAIL = "email@email.com";
  public static final String PASSWORD = "TestPw!#123";
  public static final String PASSWORD_URL_ENCODED = "TestPw!%23123";
  public static final boolean IS_TEAM_CONSULTANT = true;
  public static final boolean IS_LANGUAGE_FORMAL = true;
  public static final String VALID_AGE = "1";
  public static final String INVALID_AGE = "xxx";
  public static final String VALID_STATE = "1";
  public static final String INVALID_STATE = "xxx";
  public static final Consultant CONSULTANT =
      new Consultant(CONSULTANT_ID, ROCKETCHAT_ID, USERNAME, FIRST_NAME, LAST_NAME, EMAIL,
          IS_ABSENT, IS_TEAM_CONSULTANT, ABSENCE_MESSAGE, IS_LANGUAGE_FORMAL, null, null, null);
  public static final Consultant CONSULTANT_2 = new Consultant(CONSULTANT_ID_2, ROCKETCHAT_ID,
      USERNAME, "first name", "last name", EMAIL, false, false, null, false, null, null, null);
  public static final Consultant CONSULTANT_NO_RC_USER_ID = new Consultant(CONSULTANT_ID, "",
      USERNAME, "first name", "last name", EMAIL, false, false, null, false, null, null, null);
  public static final Consultant MAIN_CONSULTANT =
      new Consultant(MAIN_CONSULTANT_ID, RC_USER_ID_MAIN_CONSULTANT, USERNAME, "first name",
          "last name", EMAIL, false, false, null, false, null, null, null);
  public static final SessionConsultantForUserDTO CONSULTANT_DTO = new SessionConsultantForUserDTO();
  public static final AbsenceDTO ABSENCE_DTO_WITH_NULL_MESSAGE = new AbsenceDTO(true, null);
  public static final GroupMemberDTO GROUP_MEMBER_USER_1 =
      new GroupMemberDTO(RC_USER_ID, RC_STATUS_ONLINE, USERNAME, USERNAME, RC_UTC_OFFSET);
  public static final GroupMemberDTO GROUP_MEMBER_USER_2 = new GroupMemberDTO(RC_USER_ID_2,
      RC_STATUS_ONLINE, USERNAME_SIMPLE_ENCODED, USERNAME_SIMPLE_ENCODED, RC_UTC_OFFSET);
  public static final List<GroupMemberDTO> GROUP_MEMBER_DTO_LIST = Arrays.asList(
      GROUP_MEMBER_SYS_USER, GROUP_MEMBER_TECH_USER, GROUP_MEMBER_USER_1, GROUP_MEMBER_USER_2);
  public static final User USER = new User(USER_ID, null, USERNAME, EMAIL, IS_LANGUAGE_FORMAL);
  public static final User USER_WITH_RC_ID =
      new User(USER_ID, null, USERNAME, EMAIL, RC_USER_ID, IS_LANGUAGE_FORMAL, null, null);
  public static final User USER_WITH_RC_ID_2 =
      new User(USER_ID_2, null, USERNAME, EMAIL, RC_USER_ID_2, IS_LANGUAGE_FORMAL, null, null);
  public static final User USER_NO_RC_USER_ID =
      new User(USER_ID, null, USERNAME, EMAIL, null, false, null, null);
  public static final User USER_NO_RC_USER_ID_2 =
      new User(USER_ID_2, null, USERNAME, EMAIL, null, false, null, null);
  public static final String ACCESS_TOKEN = "DASDLAJS835u83hKSAJDF";
  public static final AuthenticatedUser AUTHENTICATED_USER =
      new AuthenticatedUser(USER_ID, USERNAME, null, ACCESS_TOKEN, null);
  public static final AuthenticatedUser AUTHENTICATED_USER_3 =
      new AuthenticatedUser(USER_ID_3, USERNAME, null, ACCESS_TOKEN, null);
  public static final AuthenticatedUser AUTHENTICATED_USER_CONSULTANT =
      new AuthenticatedUser(CONSULTANT_ID, USERNAME, null, ACCESS_TOKEN, null);
  public static final User USER_NO_DATA = new User(null, null, null, null, true);
  public static final UserDTO USER_DTO_SUCHT =
      new UserDTO(USERNAME, POSTCODE, AGENCY_ID, PASSWORD, EMAIL, null, null, null, null, null,
          "true", Integer.toString(CONSULTING_TYPE_SUCHT.getValue()));
  public static final UserDTO USER_DTO_SUCHT_WITH_INVALID_POSTCODE =
      new UserDTO(USERNAME, INVALID_POSTCODE, AGENCY_ID, PASSWORD, EMAIL, null, null, null, null,
          null, "true", Integer.toString(CONSULTING_TYPE_SUCHT.getValue()));
  public static final UserDTO USER_DTO_SUCHT_WITHOUT_EMAIL =
      new UserDTO(USERNAME, POSTCODE, AGENCY_ID, PASSWORD, null, null, null, null, null, null,
          "true", Integer.toString(CONSULTING_TYPE_SUCHT.getValue()));
  public static final UserDTO USER_DTO_KREUZBUND =
      new UserDTO(USERNAME, POSTCODE, AGENCY_ID, PASSWORD, EMAIL, null, null, null, null, null,
          "true", Integer.toString(CONSULTING_TYPE_KREUZBUND.getValue()));
  public static final UserDTO USER_DTO_WITH_AGE =
      new UserDTO(VALID_AGE, null, Integer.toString(CONSULTING_TYPE_U25.getValue()));
  public static final UserDTO USER_DTO_WITH_INVALID_AGE =
      new UserDTO(INVALID_AGE, null, Integer.toString(CONSULTING_TYPE_U25.getValue()));
  public static final UserDTO USER_DTO_WITHOUT_MANDATORY_AGE = new UserDTO(null, null, null, null,
      null, null, Integer.toString(CONSULTING_TYPE_SUCHT.getValue()));
  public static final UserDTO USER_DTO_WITHOUT_CONSULTING_TYPE = new UserDTO();
  public static final UserDTO USER_DTO_WITH_STATE =
      new UserDTO(null, VALID_STATE, Integer.toString(CONSULTING_TYPE_U25.getValue()));
  public static final UserDTO USER_DTO_WITH_INVALID_STATE =
      new UserDTO(null, INVALID_STATE, Integer.toString(CONSULTING_TYPE_U25.getValue()));
  public static final UserDTO USER_DTO_WITHOUT_MANDATORY_STATE = new UserDTO(null, null, null, null,
      null, null, Integer.toString(CONSULTING_TYPE_SUCHT.getValue()));
  public static final RocketChatUserDTO ROCKET_CHAT_USER_DTO =
      new RocketChatUserDTO(RC_USER_ID, USERNAME);
  public static final UserInfoResponseDTO USER_INFO_RESPONSE_DTO =
      new UserInfoResponseDTO(ROCKET_CHAT_USER_DTO, SUCCESS, NULL, NULL);
  public static final RocketChatUserDTO ROCKET_CHAT_USER_DTO_2 =
      new RocketChatUserDTO(RC_USER_ID_2, USERNAME);
  public static final UserInfoResponseDTO USER_INFO_RESPONSE_DTO_2 =
      new UserInfoResponseDTO(ROCKET_CHAT_USER_DTO_2, SUCCESS, NULL, NULL);
  public static final UserInfoResponseDTO USER_INFO_RESPONSE_DTO_FAILED =
      new UserInfoResponseDTO(ROCKET_CHAT_USER_DTO, FAILED, ERROR, ERROR);
  public static final SessionConsultantForUserDTO SESSION_CONSULTANT_FOR_USER_DTO =
      new SessionConsultantForUserDTO(USERNAME, IS_ABSENT, ABSENCE_MESSAGE);
  public static final RocketChatUserDTO USER_DTO_1 = new RocketChatUserDTO("xyz", "123");
  public static final RocketChatUserDTO USER_DTO_2 = new RocketChatUserDTO(ROCKETCHAT_ID_2, "456");
  public static final RocketChatUserDTO USER_DTO_3 = new RocketChatUserDTO("adg", "789");

  /*
   * /* Messages
   */
  public static final String ENCRYPTED_MESSAGE =
      "enc:uWHNUkWrQJikGnVpknvB3SkzT1RWHJuY0igDT9p7fGFHWECLBpV2+0eIZF6Qi7J0";
  public static final String DECRYPTED_MESSAGE = "Das hier ist jetzt mal eine Test-Message";
  public static final String MESSAGE = "Testnachricht";
  public static final String MESSAGE_TOO_LONG = "Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magn";
  public static final AbsenceDTO ABSENCE_DTO = new AbsenceDTO(true, TestConstants.MESSAGE);
  public static final String MESSAGE_WITH_HTML_AND_JS =
      "<b>Testnachricht</b><script>alert('1');</script>";
  public static final boolean MESSAGES_READ = true;
  public static final boolean MESSAGES_NOT_READ = false;
  public static final String MESSAGE_EMPTY = StringUtils.EMPTY;
  public static final AbsenceDTO ABSENCE_DTO_WITH_EMPTY_MESSAGE =
      new AbsenceDTO(true, TestConstants.MESSAGE_EMPTY);
  public static final String MESSAGE_WITH_NON_REPLACED_USERNAME = "Hello ${username}";
  public static final String MESSAGE_WITH_REPLACED_USERNAME = "Hello " + USER.getUsername();
  /*
   * Attachments
   */
  public static final FileDTO FILE_DTO = new FileDTO(RC_ATTACHMENT_TITLE, RC_ATTACHMENT_FILE_TYPE);
  public static final AttachmentDTO ATTACHMENT_DTO = new AttachmentDTO(RC_ATTACHMENT_IMAGE_PREVIEW);

  /*
   * ConsultantAgency
   */
  public static final ConsultantAgency[] CONSULTANT_AGENCY =
      new ConsultantAgency[]{new ConsultantAgency(1L, CONSULTANT, AGENCY_ID)};
  public static final ConsultantAgency CONSULTANT_AGENCY_2 =
      new ConsultantAgency(2L, CONSULTANT, AGENCY_ID_2);
  public static final Set<ConsultantAgency> CONSULTANT_AGENCY_SET =
      new HashSet<ConsultantAgency>(Arrays.asList(CONSULTANT_AGENCY));
  public static final Set<Long> CONSULTANT_AGENCY_IDS_SET =
      new HashSet<Long>(Arrays.asList(AGENCY_ID));
  public static final Consultant CONSULTANT_WITH_AGENCY = new Consultant(CONSULTANT_ID,
      ROCKETCHAT_ID, USERNAME, "first name", "last name", EMAIL, false, false, "", false, null,
      null, new HashSet<ConsultantAgency>(Arrays.asList(CONSULTANT_AGENCY)));
  public static final Consultant CONSULTANT_WITH_AGENCY_2 = new Consultant(CONSULTANT_ID_2,
      ROCKETCHAT_ID, USERNAME, "first name", "last name", EMAIL, false, false, null, false, null,
      null, new HashSet<ConsultantAgency>(Arrays.asList(CONSULTANT_AGENCY_2)));
  /**
   * UserAgency
   */
  public static final UserAgency USER_AGENCY = new UserAgency(USER, AGENCY_ID);
  public static final UserAgency USER_AGENCY_2 = new UserAgency(USER, AGENCY_ID_2);
  public static final List<UserAgency> USER_AGENCY_LIST = Arrays.asList(USER_AGENCY, USER_AGENCY_2);
  public static final Set<UserAgency> USER_AGENCY_SET =
      new HashSet<>(Arrays.asList(USER_AGENCY, USER_AGENCY_2));
  public static final User USER_WITH_AGENCIES = new User(USER_ID, null, USERNAME, EMAIL, RC_USER_ID,
      IS_LANGUAGE_FORMAL, null, USER_AGENCY_SET);
  /*
   * Session
   */
  public static final Long SESSION_ID = 1L;
  public static final Long TEAM_SESSION_ID = 55L;
  public static final Integer SESSION_STATUS_NEW = 1;
  public static final Integer SESSION_STATUS_IN_PROGRESS = 2;
  public static final Integer SESSION_STATUS_INVALID = 234234324;
  public static final boolean IS_TEAM_SESSION = true;
  public static final boolean IS_NO_TEAM_SESSION = false;
  public static final boolean IS_MONITORING = true;
  public static final boolean IS_NOT_MONITORING = false;
  public static final SessionFilter SESSION_FILTER_ALL = SessionFilter.ALL;
  public static final Long ENQUIRY_ID = 1L;
  public static final Long ENQUIRY_ID_2 = 2L;
  public static final Session SESSION =
      new Session(SESSION_ID, null, null, null, null, null, null, null, null);
  public static final Session SESSION_WITH_CONSULTANT =
      new Session(SESSION_ID, null, CONSULTANT_2, ConsultingType.SUCHT, POSTCODE, AGENCY_ID,
          SessionStatus.IN_PROGRESS, new Date(), RC_GROUP_ID);
  public static final Session ENQUIRY_SESSION_WITH_CONSULTANT =
      new Session(SESSION_ID, null, CONSULTANT_2, ConsultingType.SUCHT, POSTCODE, AGENCY_ID,
          SessionStatus.NEW, new Date(), RC_GROUP_ID);
  public static final Session SESSION_WITHOUT_CONSULTANT =
      new Session(SESSION_ID, USER_WITH_RC_ID, null, ConsultingType.U25, POSTCODE, AGENCY_ID,
          SessionStatus.NEW, null, RC_GROUP_ID, null, null, IS_TEAM_SESSION, IS_MONITORING);
  public static final Session FEEDBACKSESSION_WITHOUT_CONSULTANT = new Session(SESSION_ID,
      USER_WITH_RC_ID, null, ConsultingType.U25, POSTCODE, AGENCY_ID, SessionStatus.NEW, new Date(),
      RC_GROUP_ID, RC_FEEDBACK_GROUP_ID, null, IS_TEAM_SESSION, IS_MONITORING);
  public static final Session FEEDBACKSESSION_WITH_CONSULTANT =
      new Session(SESSION_ID, USER_WITH_RC_ID, CONSULTANT_2, ConsultingType.U25, POSTCODE,
          AGENCY_ID, SessionStatus.IN_PROGRESS, new Date(), RC_GROUP_ID, RC_FEEDBACK_GROUP_ID, null,
          IS_TEAM_SESSION, IS_MONITORING);
  public static final Session SESSION_WITHOUT_CONSULTANT_NO_RC_USER_ID =
      new Session(SESSION_ID, USER_NO_RC_USER_ID_2, null, ConsultingType.SUCHT, POSTCODE, AGENCY_ID,
          SessionStatus.NEW, new Date(), RC_GROUP_ID, null, IS_NO_TEAM_SESSION, IS_MONITORING);
  public static final Session U25_SESSION_WITH_CONSULTANT = new Session(SESSION_ID, USER_WITH_RC_ID,
      CONSULTANT_2, ConsultingType.U25, POSTCODE, AGENCY_ID, SessionStatus.IN_PROGRESS, new Date(),
      RC_GROUP_ID, RC_FEEDBACK_GROUP_ID, null, IS_TEAM_SESSION, IS_MONITORING);
  public static final Session U25_SESSION_WITHOUT_CONSULTANT = new Session(SESSION_ID,
      USER_WITH_RC_ID, null, ConsultingType.U25, POSTCODE, AGENCY_ID, SessionStatus.NEW, new Date(),
      RC_GROUP_ID, RC_FEEDBACK_GROUP_ID, null, IS_TEAM_SESSION, IS_MONITORING);
  public static final List<Session> SESSION_LIST = Arrays.asList(SESSION);
  public static final Set<Session> SESSION_SET = new HashSet<Session>(
      Arrays.asList(U25_SESSION_WITHOUT_CONSULTANT, SESSION_WITHOUT_CONSULTANT_NO_RC_USER_ID));
  public static final User USER_WITH_SESSIONS =
      new User(USER_ID, null, USERNAME, EMAIL, RC_USER_ID, IS_LANGUAGE_FORMAL, SESSION_SET, null);
  public static final SessionDTO SESSION_DTO_SUCHT = new SessionDTO(SESSION_ID, AGENCY_ID,
      CONSULTING_TYPE_SUCHT.getValue(), SESSION_STATUS_IN_PROGRESS, POSTCODE, RC_GROUP_ID,
      RC_FEEDBACK_GROUP_ID, RC_USER_ID, MESSAGE_DATE, IS_TEAM_SESSION, IS_MONITORING);
  public static final SessionDTO SESSION_DTO_U25 = new SessionDTO(SESSION_ID, AGENCY_ID,
      CONSULTING_TYPE_U25.getValue(), SESSION_STATUS_IN_PROGRESS, POSTCODE, RC_GROUP_ID,
      RC_FEEDBACK_GROUP_ID, RC_USER_ID, MESSAGE_DATE, IS_TEAM_SESSION, IS_MONITORING);
  public static final UserSessionResponseDTO USER_SESSION_RESPONSE_DTO_SUCHT =
      new UserSessionResponseDTO(SESSION_DTO_SUCHT, AGENCY_DTO_SUCHT,
          SESSION_CONSULTANT_FOR_USER_DTO);
  public static final UserSessionResponseDTO USER_SESSION_RESPONSE_DTO_U25 =
      new UserSessionResponseDTO(SESSION_DTO_U25, AGENCY_DTO_U25, SESSION_CONSULTANT_FOR_USER_DTO);
  public static final List<UserSessionResponseDTO> USER_SESSION_RESPONSE_DTO_LIST_SUCHT =
      Arrays.asList(USER_SESSION_RESPONSE_DTO_SUCHT);
  public static final List<UserSessionResponseDTO> USER_SESSION_RESPONSE_DTO_LIST_U25 =
      Arrays.asList(USER_SESSION_RESPONSE_DTO_U25);
  public static final NewRegistrationDto NEW_REGISTRATION_DTO_SUCHT =
      new NewRegistrationDto(POSTCODE, AGENCY_ID, Long.toString(CONSULTING_TYPE_SUCHT.getValue()));
  public static final NewRegistrationDto NEW_REGISTRATION_DTO_U25 =
      new NewRegistrationDto(POSTCODE, AGENCY_ID, Long.toString(CONSULTING_TYPE_U25.getValue()));
  public static final NewRegistrationResponseDto NEW_REGISTRATION_RESPONSE_DTO_CREATED =
      NewRegistrationResponseDto.builder().status(HttpStatus.CREATED).build();
  public static final SessionDTO SESSION_DTO_1 =
      new SessionDTO(SESSION_ID, AGENCY_ID, 0, 0, null, RC_GROUP_ID, RC_FEEDBACK_GROUP_ID,
          RC_USER_ID, Helper.getUnixTimestampFromDate(NOW), IS_NO_TEAM_SESSION, IS_MONITORING);
  public static final SessionDTO SESSION_DTO_2 =
      new SessionDTO(SESSION_ID, AGENCY_ID, 0, 0, null, RC_GROUP_ID_2, RC_FEEDBACK_GROUP_ID_2,
          RC_USER_ID_2, Helper.getUnixTimestampFromDate(new Date(NOW.getTime() + 86400000)),
          IS_NO_TEAM_SESSION, IS_MONITORING);
  public static final SessionDTO SESSION_DTO_3 =
      new SessionDTO(SESSION_ID, AGENCY_ID, 0, 0, null, RC_GROUP_ID_3, RC_FEEDBACK_GROUP_ID_3,
          RC_USER_ID_3, Helper.getUnixTimestampFromDate(new Date(NOW.getTime() + 8640000)),
          IS_NO_TEAM_SESSION, IS_MONITORING);
  public static final SessionDTO SESSION_DTO_WITH_FEEDBACK =
      new SessionDTO(SESSION_ID, AGENCY_ID, 0, 0, null, RC_GROUP_ID_3, RC_FEEDBACK_GROUP_ID_3,
          RC_USER_ID_3, Helper.getUnixTimestampFromDate(new Date(NOW.getTime() + 8640000)),
          IS_NO_TEAM_SESSION, IS_MONITORING, false);
  public static final SessionDTO SESSION_DTO_WITHOUT_FEEDBACK =
      new SessionDTO(SESSION_ID, AGENCY_ID, 0, 0, null, RC_GROUP_ID_3, RC_FEEDBACK_GROUP_ID_3,
          RC_USER_ID_3, Helper.getUnixTimestampFromDate(new Date(NOW.getTime() + 8640000)),
          IS_NO_TEAM_SESSION, IS_MONITORING, true);
  /**
   * Chat
   */
  public static final String CHAT_TOPIC = "Pregnancy";
  public static final String CHAT_TOPIC_2 = "Debts";
  public static final String CHAT_TOPIC_3 = "Children";
  public static final boolean IS_REPETITIVE = true;
  public static final boolean IS_NOT_REPETITIVE = false;
  public static final boolean IS_ACTIVE = true;
  public static final boolean IS_NOT_ACTIVE = false;
  public static final int CHAT_MAX_PARTICIPANTS = 5;
  public static final int CHAT_DURATION_30 = 30;
  public static final int CHAT_DURATION_60 = 60;
  public static final int CHAT_DURATION_90 = 90;
  public static final LocalDate CHAT_START_DATE = LocalDate.of(2019, 10, 23);
  public static final LocalTime CHAT_START_TIME = LocalTime.of(14, 0);
  public static final LocalDateTime CHAT_START_DATETIME =
      LocalDateTime.of(CHAT_START_DATE, CHAT_START_TIME);
  public static final int CHAT_DURATION = 60;
  public static final boolean CHAT_REPETITIVE = false;
  public static final ChatInterval CHAT_INTERVAL_WEEKLY = ChatInterval.WEEKLY;
  public static final Long CHAT_ID = 136L;
  public static final Long CHAT_ID_2 = 137L;
  public static final Long CHAT_ID_3 = 138L;
  public static final String INVALID_CHAT_ID = "xyz";
  public static final String CHAT_ID_ENCODED = "GEZTM";
  public static final String GROUP_CHAT_NAME = "GROUP_CHAT_NAME";
  public static final String CHAT_LINK_SUCHT =
      HOST_BASE_URL + "/" + CONSULTING_TYPE_SUCHT_URL_NAME + "/" + CHAT_ID_ENCODED;
  public static final ChatDTO CHAT_DTO =
      new ChatDTO(CHAT_TOPIC, CHAT_START_DATE, CHAT_START_TIME, CHAT_DURATION, CHAT_REPETITIVE);
  public static final CreateChatResponseDTO CREATE_CHAT_RESPONSE_DTO =
      new CreateChatResponseDTO(RC_GROUP_ID, CHAT_LINK_SUCHT);
  public static final Chat ACTIVE_CHAT = new Chat(CHAT_ID, CHAT_TOPIC, CONSULTING_TYPE_SUCHT,
      LocalDateTime.of(CHAT_START_DATE, CHAT_START_TIME),
      LocalDateTime.of(CHAT_START_DATE, CHAT_START_TIME), CHAT_DURATION_30, IS_REPETITIVE,
      CHAT_INTERVAL_WEEKLY, IS_ACTIVE, CHAT_MAX_PARTICIPANTS, RC_GROUP_ID, CONSULTANT, null);
  public static final Chat INACTIVE_CHAT = new Chat(CHAT_ID_2, CHAT_TOPIC, CONSULTING_TYPE_SUCHT,
      LocalDateTime.of(CHAT_START_DATE, CHAT_START_TIME),
      LocalDateTime.of(CHAT_START_DATE, CHAT_START_TIME), CHAT_DURATION_30, IS_REPETITIVE,
      ChatInterval.WEEKLY, IS_NOT_ACTIVE, CHAT_MAX_PARTICIPANTS, RC_GROUP_ID, CONSULTANT, null);
  public static final ChatAgency CHAT_AGENCY = new ChatAgency(ACTIVE_CHAT, AGENCY_ID);
  public static final Set<ChatAgency> CHAT_AGENCIES =
      new HashSet<ChatAgency>(Arrays.asList(CHAT_AGENCY));
  public static final UserChatDTO USER_CHAT_DTO_1 = new UserChatDTO(CHAT_ID, CHAT_TOPIC, null, null,
      CHAT_DURATION_30, IS_REPETITIVE, IS_ACTIVE, ConsultingType.PREGNANCY.getValue(), null,
      Helper.getUnixTimestampFromDate(new Date(NOW.getTime() + 86300000)), MESSAGES_NOT_READ,
      RC_GROUP_ID_4, null, false, null, LocalDateTime.now());
  public static final UserChatDTO USER_CHAT_DTO_2 = new UserChatDTO(CHAT_ID_2, CHAT_TOPIC_2, null,
      null,
      CHAT_DURATION_60, IS_REPETITIVE, IS_NOT_ACTIVE, ConsultingType.DEBT.getValue(), null,
      Helper.getUnixTimestampFromDate(new Date(NOW.getTime() + 86200000)), MESSAGES_NOT_READ,
      RC_GROUP_ID_5, null, false, null, LocalDateTime.now());
  public static final UserChatDTO USER_CHAT_DTO_3 = new UserChatDTO(CHAT_ID_3, CHAT_TOPIC_3, null,
      null,
      CHAT_DURATION_90, IS_NOT_REPETITIVE, IS_NOT_ACTIVE, ConsultingType.CHILDREN.getValue(), null,
      Helper.getUnixTimestampFromDate(new Date(NOW.getTime() + 86410000)), MESSAGES_NOT_READ,
      RC_GROUP_ID_6, null, false, null, LocalDateTime.now());
  public static final UserSessionResponseDTO USER_CHAT_RESPONSE_DTO =
      new UserSessionResponseDTO(null, USER_CHAT_DTO_1, EMPTY_AGENCY_DTO, CONSULTANT_DTO, NOW);
  public static final UserSessionResponseDTO USER_CHAT_RESPONSE_DTO_2 = new UserSessionResponseDTO(
      null,
      USER_CHAT_DTO_2, EMPTY_AGENCY_DTO, CONSULTANT_DTO, new Date(NOW.getTime() + 80000000));
  public static final UserSessionResponseDTO USER_CHAT_RESPONSE_DTO_3 = new UserSessionResponseDTO(
      null,
      USER_CHAT_DTO_3, EMPTY_AGENCY_DTO, CONSULTANT_DTO, new Date(NOW.getTime() + 70000000));

  /* Session list */
  public static final UserSessionResponseDTO USER_SESSION_RESPONSE_DTO =
      new UserSessionResponseDTO(SESSION_DTO_1, null, EMPTY_AGENCY_DTO, CONSULTANT_DTO, NOW);
  public static final UserSessionResponseDTO USER_SESSION_RESPONSE_DTO_2 = new UserSessionResponseDTO(
      SESSION_DTO_2, null, EMPTY_AGENCY_DTO, CONSULTANT_DTO, new Date(NOW.getTime() + 86400000));
  public static final UserSessionResponseDTO USER_SESSION_RESPONSE_DTO_3 = new UserSessionResponseDTO(
      SESSION_DTO_3, null, EMPTY_AGENCY_DTO, CONSULTANT_DTO, new Date(NOW.getTime() + 8640000));
  public static final List<UserSessionResponseDTO> USER_SESSION_RESPONSE_DTO_LIST = Arrays
      .asList(USER_SESSION_RESPONSE_DTO, USER_SESSION_RESPONSE_DTO_2, USER_SESSION_RESPONSE_DTO_3);
  public static final List<UserSessionResponseDTO> USER_SESSION_RESPONSE_SESSION_CHAT_DTO_LIST = Arrays
      .asList(USER_SESSION_RESPONSE_DTO, USER_SESSION_RESPONSE_DTO_2, USER_SESSION_RESPONSE_DTO_3, USER_CHAT_RESPONSE_DTO, USER_CHAT_RESPONSE_DTO_2);
  public static final Map<String, Boolean> MESSAGES_READ_MAP_WITH_UNREADS =
      new HashMap<String, Boolean>() {{
        put(RC_GROUP_ID, false);
        put(RC_GROUP_ID_2, false);
        put(RC_GROUP_ID_3, false);
        put(RC_GROUP_ID_4, false);
        put(RC_GROUP_ID_5, false);
        put(RC_GROUP_ID_6, false);
        put(RC_FEEDBACK_GROUP_ID, false);
        put(RC_FEEDBACK_GROUP_ID_2, false);
        put(RC_FEEDBACK_GROUP_ID_3, false);
      }};
  public static final Map<String, Boolean> MESSAGES_READ_MAP_WITHOUT_UNREADS =
      new HashMap<String, Boolean>() {{
        put(RC_GROUP_ID, true);
        put(RC_GROUP_ID_2, true);
        put(RC_GROUP_ID_3, true);
        put(RC_GROUP_ID_4, true);
        put(RC_GROUP_ID_5, true);
        put(RC_GROUP_ID_6, true);
        put(RC_FEEDBACK_GROUP_ID, true);
        put(RC_FEEDBACK_GROUP_ID_2, true);
        put(RC_FEEDBACK_GROUP_ID_3, true);
      }};
  public static final Map<String, Boolean> MESSAGES_READ_MAP_WITH_ONE_FEEDBACK_UNREAD =
      new HashMap<String, Boolean>() {{
        put(RC_GROUP_ID, true);
        put(RC_GROUP_ID_2, true);
        put(RC_GROUP_ID_3, true);
        put(RC_GROUP_ID_4, true);
        put(RC_GROUP_ID_5, true);
        put(RC_GROUP_ID_6, true);
        put(RC_FEEDBACK_GROUP_ID, false);
        put(RC_FEEDBACK_GROUP_ID_2, true);
        put(RC_FEEDBACK_GROUP_ID_3, true);
      }};
  public static final List<UserSessionResponseDTO> USER_CHAT_RESPONSE_DTO_LIST =
      Arrays.asList(USER_CHAT_RESPONSE_DTO, USER_CHAT_RESPONSE_DTO_2, USER_CHAT_RESPONSE_DTO_3);

  public static final RoomsLastMessageDTO ROOMS_LAST_MESSAGE_DTO_WITHOUT_ATTACHMENT =
      new RoomsLastMessageDTO("id", RC_GROUP_ID, NOW_MINUS_1_DAY, USER_DTO_1, true, NOW_MINUS_1_DAY,
          MESSAGE, null, null);
  public static final RoomsLastMessageDTO ROOMS_LAST_MESSAGE_DTO_1 =
      new RoomsLastMessageDTO("id", RC_GROUP_ID, NOW_MINUS_1_DAY, USER_DTO_1, true, NOW_MINUS_1_DAY,
          MESSAGE, FILE_DTO, org.assertj.core.util.Arrays.array(ATTACHMENT_DTO));
  public static final RoomsLastMessageDTO ROOMS_LAST_MESSAGE_DTO_2 =
      new RoomsLastMessageDTO("id", RC_GROUP_ID_2, NOW_MINUS_3_DAYS, USER_DTO_2, true,
          NOW_MINUS_3_DAYS, MESSAGE, FILE_DTO, org.assertj.core.util.Arrays.array(ATTACHMENT_DTO));
  public static final RoomsLastMessageDTO ROOMS_LAST_MESSAGE_DTO_3 = new RoomsLastMessageDTO("id",
      RC_GROUP_ID, NOW_MINUS_2_DAYS, USER_DTO_3, true, NOW_MINUS_2_DAYS, MESSAGE, null, null);
  public static final RoomsLastMessageDTO ROOMS_LAST_MESSAGE_DTO_4 =
      new RoomsLastMessageDTO("id", RC_GROUP_ID_4, NOW_MINUS_1_DAY, USER_DTO_1, true,
          NOW_MINUS_1_DAY, MESSAGE, FILE_DTO, org.assertj.core.util.Arrays.array(ATTACHMENT_DTO));
  public static final RoomsLastMessageDTO ROOMS_LAST_MESSAGE_DTO_5 =
      new RoomsLastMessageDTO("id", RC_GROUP_ID_5, NOW_MINUS_1_DAY, USER_DTO_1, true,
          NOW_MINUS_1_DAY, MESSAGE, FILE_DTO, org.assertj.core.util.Arrays.array(ATTACHMENT_DTO));
  public static final RoomsLastMessageDTO ROOMS_LAST_MESSAGE_DTO_6 =
      new RoomsLastMessageDTO("id", RC_GROUP_ID_6, NOW_MINUS_1_DAY, USER_DTO_1, true,
          NOW_MINUS_1_DAY, MESSAGE, FILE_DTO, org.assertj.core.util.Arrays.array(ATTACHMENT_DTO));
  public static final List<RoomsUpdateDTO> ROOMS_UPDATE_DTO_LIST = Arrays.asList(
      new RoomsUpdateDTO(RC_GROUP_ID, "name1", "fname1", "P", USER_DTO_1, true, false, new Date(),
          ROOMS_LAST_MESSAGE_DTO_1),
      new RoomsUpdateDTO(RC_GROUP_ID_2, "name2", "fname2", "P", USER_DTO_2, true, false, new Date(),
          ROOMS_LAST_MESSAGE_DTO_2),
      new RoomsUpdateDTO(RC_GROUP_ID_3, "name3", "fname3", "P", USER_DTO_3, true, false, new Date(),
          ROOMS_LAST_MESSAGE_DTO_3),
      new RoomsUpdateDTO(RC_FEEDBACK_GROUP_ID, "name1", "fname1", "P", USER_DTO_1, true, false,
          new Date(), ROOMS_LAST_MESSAGE_DTO_1),
      new RoomsUpdateDTO(RC_FEEDBACK_GROUP_ID_2, "name2", "fname2", "P", USER_DTO_2, true, false,
          new Date(), ROOMS_LAST_MESSAGE_DTO_2),
      new RoomsUpdateDTO(RC_FEEDBACK_GROUP_ID_3, "name3", "fname3", "P", USER_DTO_3, true, false,
          new Date(), ROOMS_LAST_MESSAGE_DTO_3),
      new RoomsUpdateDTO(RC_GROUP_ID_4, "name4", "fname4", "P", USER_DTO_1, true, false, new Date(),
          ROOMS_LAST_MESSAGE_DTO_4),
      new RoomsUpdateDTO(RC_GROUP_ID_5, "name5", "fname5", "P", USER_DTO_2, true, false, new Date(),
          ROOMS_LAST_MESSAGE_DTO_5),
      new RoomsUpdateDTO(RC_GROUP_ID_6, "name6", "fname6", "P", USER_DTO_3, true, false, new Date(),
          ROOMS_LAST_MESSAGE_DTO_6));
  public static final Map<String, RoomsLastMessageDTO> ROOMS_LAST_MESSAGE_DTO_MAP =
      new HashMap<String, RoomsLastMessageDTO>() {{
        put(RC_GROUP_ID, ROOMS_LAST_MESSAGE_DTO_1);
        put(RC_GROUP_ID_2, ROOMS_LAST_MESSAGE_DTO_2);
        put(RC_GROUP_ID_3, ROOMS_LAST_MESSAGE_DTO_3);
        put(RC_GROUP_ID_4, ROOMS_LAST_MESSAGE_DTO_4);
        put(RC_GROUP_ID_5, ROOMS_LAST_MESSAGE_DTO_5);
        put(RC_GROUP_ID_6, ROOMS_LAST_MESSAGE_DTO_6);
        put(RC_FEEDBACK_GROUP_ID, ROOMS_LAST_MESSAGE_DTO_1);
        put(RC_FEEDBACK_GROUP_ID_2, ROOMS_LAST_MESSAGE_DTO_2);
        put(RC_FEEDBACK_GROUP_ID_3, ROOMS_LAST_MESSAGE_DTO_3);
      }};
  public static final RoomsLastMessageDTO ROOMS_LAST_MESSAGE_DTO_WITH_ATTACHMENT =
      new RoomsLastMessageDTO("id", RC_GROUP_ID, NOW_MINUS_1_DAY, USER_DTO_1, true, NOW_MINUS_1_DAY,
          MESSAGE, FILE_DTO, org.assertj.core.util.Arrays.array(ATTACHMENT_DTO));
  public static final RoomsLastMessageDTO ROOMS_LAST_MESSAGE_DTO_WITH_ATTACHMENT_FOR_CHAT =
      new RoomsLastMessageDTO("id", RC_GROUP_ID_6, NOW_MINUS_1_DAY, USER_DTO_1, true,
          NOW_MINUS_1_DAY, MESSAGE, FILE_DTO, org.assertj.core.util.Arrays.array(ATTACHMENT_DTO));
  public static final List<RoomsUpdateDTO> ROOMS_UPDATE_DTO_LIST_WITH_ATTACHMENT =
      Arrays.asList(new RoomsUpdateDTO(RC_GROUP_ID, "name1", "fname1", "P", USER_DTO_1, true, false,
          new Date(), ROOMS_LAST_MESSAGE_DTO_WITH_ATTACHMENT));
  public static final List<RoomsUpdateDTO> ROOMS_UPDATE_DTO_LIST_WITH_ATTACHMENT_FOR_CHAT = Arrays
      .asList(
          new RoomsUpdateDTO(RC_GROUP_ID_4, "name1", "fname1", "P", USER_DTO_1, true, false,
              new Date(),
              ROOMS_LAST_MESSAGE_DTO_WITH_ATTACHMENT_FOR_CHAT),
          new RoomsUpdateDTO(RC_GROUP_ID_6, "name1", "fname1", "P", USER_DTO_1, true, false,
              new Date(),
              ROOMS_LAST_MESSAGE_DTO_WITH_ATTACHMENT_FOR_CHAT));
  public static final SessionAttachmentDTO SESSION_ATTACHMENT_DTO_RECEIVED = new SessionAttachmentDTO(
      ROOMS_LAST_MESSAGE_DTO_1.getFile().getType(),
      ROOMS_LAST_MESSAGE_DTO_1.getAttachements()[0].getImagePreview(),
      true);
  public static final SessionAttachmentDTO SESSION_ATTACHMENT_DTO_NOT_RECEIVED = new SessionAttachmentDTO(
      ROOMS_LAST_MESSAGE_DTO_1.getFile().getType(),
      ROOMS_LAST_MESSAGE_DTO_1.getAttachements()[0].getImagePreview(),
      false);
  public static final List<String> USERS_ROOMS_LIST =
      ROOMS_UPDATE_DTO_LIST.stream().map(RoomsUpdateDTO::getId).collect(Collectors.toList());
  public static final List<String> USERS_EMPTY_ROOMS_LIST = new ArrayList<>();
  public static final ConsultantSessionResponseDTO CONSULTANT_SESSION_RESPONSE_DTO =
      new ConsultantSessionResponseDTO(SESSION_DTO_1, null, null, null, NOW);
  public static final ConsultantSessionResponseDTO CONSULTANT_SESSION_RESPONSE_DTO_2 =
      new ConsultantSessionResponseDTO(SESSION_DTO_2, null, null, null,
          new Date(NOW.getTime() + 86400000));
  public static final ConsultantSessionResponseDTO CONSULTANT_SESSION_RESPONSE_DTO_3 =
      new ConsultantSessionResponseDTO(SESSION_DTO_3, null, null, null,
          new Date(NOW.getTime() + 8640000));
  public static final ConsultantSessionResponseDTO CONSULTANT_SESSION_RESPONSE_DTO_WITH_FEEDBACK =
      new ConsultantSessionResponseDTO(SESSION_DTO_WITH_FEEDBACK, null, null, null,
          new Date(NOW.getTime() + 8640000));
  public static final ConsultantSessionResponseDTO CONSULTANT_SESSION_RESPONSE_DTO_WITHOUT_FEEDBACK =
      new ConsultantSessionResponseDTO(SESSION_DTO_WITHOUT_FEEDBACK, null, null, null,
          new Date(NOW.getTime() + 8640000));
  public static final List<ConsultantSessionResponseDTO> CONSULTANT_SESSION_RESPONSE_DTO_LIST =
      new ArrayList<ConsultantSessionResponseDTO>() {
        private static final long serialVersionUID = 1L;

        {
          add(CONSULTANT_SESSION_RESPONSE_DTO);
          add(CONSULTANT_SESSION_RESPONSE_DTO_2);
          add(CONSULTANT_SESSION_RESPONSE_DTO_3);
        }
      };
  public static final List<ConsultantSessionResponseDTO> CONSULTANT_SESSION_RESPONSE_DTO_LIST_2 =
      new ArrayList<ConsultantSessionResponseDTO>() {
        private static final long serialVersionUID = 1L;

        {
          add(CONSULTANT_SESSION_RESPONSE_DTO);
          add(CONSULTANT_SESSION_RESPONSE_DTO_2);
          add(CONSULTANT_SESSION_RESPONSE_DTO_3);
        }
      };
  public static final List<ConsultantSessionResponseDTO> CONSULTANT_SESSION_RESPONSE_DTO_LIST_WITH_ONE_FEEDBACK =
      new ArrayList<ConsultantSessionResponseDTO>() {
        private static final long serialVersionUID = 1L;

        {
          add(CONSULTANT_SESSION_RESPONSE_DTO_WITH_FEEDBACK);
          add(CONSULTANT_SESSION_RESPONSE_DTO_WITHOUT_FEEDBACK);
        }
      };
  public static final List<ConsultantSessionResponseDTO> CONSULTANT_SESSION_CHAT_RESPONSE_DTO_LIST =
      new ArrayList<ConsultantSessionResponseDTO>() {
        private static final long serialVersionUID = 1L;

        {
          add(CONSULTANT_SESSION_RESPONSE_DTO);
          add(CONSULTANT_SESSION_RESPONSE_DTO_2);
          add(CONSULTANT_SESSION_RESPONSE_DTO_3);
          add(CONSULTANT_SESSION_RESPONSE_DTO_WITH_ENCRYPTED_CHAT_MESSAGE);
        }
      };
  public static final SessionDTO SESSION_DTO_WITHOUT_FEEDBACK_CHAT =
      new SessionDTO(SESSION_ID, AGENCY_ID, 0, 0, null, RC_GROUP_ID, null, RC_USER_ID,
          Helper.getUnixTimestampFromDate(NOW), IS_NO_TEAM_SESSION, IS_MONITORING);
  public static final ConsultantSessionResponseDTO CONSULTANT_SESSION_RESPONSE_DTO_WITHOUT_FEEDBACK_CHAT =
      new ConsultantSessionResponseDTO(SESSION_DTO_WITHOUT_FEEDBACK_CHAT, null, null, null, NOW);
  public static final List<ConsultantSessionResponseDTO> CONSULTANT_SESSION_RESPONSE_DTO_LIST_WITHOUT_FEEDBACK_CHAT =
      Arrays.asList(CONSULTANT_SESSION_RESPONSE_DTO_WITHOUT_FEEDBACK_CHAT);

  /**
   * GroupMemberDTO
   */
  public static final GroupMemberDTO GROUP_MEMBER_DTO =
      new GroupMemberDTO(RC_USER_ID, null, USERNAME, null, null);
  public static final GroupMemberDTO GROUP_MEMBER_DTO_2 =
      new GroupMemberDTO(RC_USER_ID_2, null, USERNAME, null, null);
  public static final GroupMemberDTO GROUP_MEMBER_DTO_MAIN_CONSULTANT =
      new GroupMemberDTO(RC_USER_ID_MAIN_CONSULTANT, null, USERNAME, null, null);
  public static final List<GroupMemberDTO> LIST_GROUP_MEMBER_DTO =
      Arrays.asList(GROUP_MEMBER_DTO, GROUP_MEMBER_DTO_2, GROUP_MEMBER_DTO_MAIN_CONSULTANT);
  /*
   * Passwords
   */
  public static final String ENCODED_PASSWORD = "Tester123%24";
  public static final String DECODED_PASSWORD = "Tester123$";
  /*
   * Masterkey
   */
  public static final String MASTER_KEY_1 = "key1";
  public static final String MASTER_KEY_2 = "key2";
  public static final String MASTER_KEY_DTO_KEY_1 = "{\"masterKey\": \"" + MASTER_KEY_1 + "\"}";
  public static final String MASTER_KEY_DTO_KEY_2 = "{\"masterKey\": \"" + MASTER_KEY_2 + "\"}";
  /*
   * Roles
   */
  public static final String USER_ROLE = "user";
  public static final Set<String> USER_ROLES = new HashSet<>(Arrays.asList(USER_ROLE));
  public static final String CONSULTANT_ROLE = "consultant";
  public static final Set<String> CONSULTANT_ROLES = new HashSet<>(Arrays.asList(CONSULTANT_ROLE));
  /**
   * Registration values
   */
  public static final String USERNAME_TOO_SHORT = "flo";
  public static final String USERNAME_TOO_LONG = "UsernameVielZuLangMit31Zeicheeen";
  public static final String ADDICTIVE_DRUGS_VALUE = "2,4";
  public static final String RELATION_VALUE = "2";
  public static final String AGE_VALUE = "12";
  public static final String INVALID_AGE_VALUE = "12age";
  public static final String GENDER_VALUE = "1";
  public static final String STATE_VALUE = "16";
  public static final boolean TERMS_ACCEPTED = true;
  /**
   * ConsultingTypeSettings
   */

  public static final ConsultingTypeSettings CONSULTING_TYPE_SETTINGS_WITH_FORMAL_LANGUAGE =
      new ConsultingTypeSettings(ConsultingType.SUCHT, false, null, null, true, null, false, null,
          true, null, null);
  public static final ConsultingTypeSettings CONSULTING_TYPE_SETTINGS_KREUZBUND =
      new ConsultingTypeSettings(ConsultingType.KREUZBUND, false, null, null, true, null, false,
          null, true, null, null);
  public static final MandatoryFields MANDATORY_FIELDS_WITH_AGE = new MandatoryFields(true, false);
  public static final MandatoryFields MANDATORY_FIELDS_WITHOUT_AGE =
      new MandatoryFields(false, false);
  public static final Registration REGISTRATION_WITH_MANDATORY_AGE =
      new Registration(3, MANDATORY_FIELDS_WITH_AGE);
  public static final Registration REGISTRATION_WITHOUT_MANDATORY_AGE =
      new Registration(3, MANDATORY_FIELDS_WITHOUT_AGE);
  public static final ConsultingTypeSettings CONSULTING_TYPE_SETTINGS_WITH_AGE_MANDATORY =
      new ConsultingTypeSettings(CONSULTING_TYPE_U25, false, null, null, true, null, false, null,
          false, null, REGISTRATION_WITH_MANDATORY_AGE);
  public static final ConsultingTypeSettings CONSULTING_TYPE_SETTINGS_WITHOUT_AGE_MANDATORY =
      new ConsultingTypeSettings(CONSULTING_TYPE_U25, false, null, null, true, null, false, null,
          false, null, REGISTRATION_WITHOUT_MANDATORY_AGE);
  public static final ConsultingTypeSettings CONSULTING_TYPE_SETTINGS_WITHOUT_STATE_FIELD =
      new ConsultingTypeSettings();
  public static final MandatoryFields MANDATORY_FIELDS_WITH_STATE =
      new MandatoryFields(false, true);
  public static final MandatoryFields MANDATORY_FIELDS_WITHOUT_STATE =
      new MandatoryFields(false, false);
  public static final Registration REGISTRATION_WITH_MANDATORY_STATE =
      new Registration(3, MANDATORY_FIELDS_WITH_STATE);
  public static final Registration REGISTRATION_WITHOUT_MANDATORY_STATE =
      new Registration(3, MANDATORY_FIELDS_WITHOUT_STATE);
  public static final ConsultingTypeSettings CONSULTING_TYPE_SETTINGS_WITH_MANDATORY_STATE =
      new ConsultingTypeSettings(CONSULTING_TYPE_U25, false, null, null, true, null, false, null,
          false, null, REGISTRATION_WITH_MANDATORY_STATE);
  public static final ConsultingTypeSettings CONSULTING_TYPE_SETTINGS_WITHOUT_MANDATORY_STATE =
      new ConsultingTypeSettings(CONSULTING_TYPE_U25, false, null, null, true, null, false, null,
          false, null, REGISTRATION_WITHOUT_MANDATORY_STATE);
  public static final MandatoryFields MANDATORY_FIELDS_FALSE = new MandatoryFields(false, false);
  public static final MandatoryFields MANDATORY_FIELDS_TRUE = new MandatoryFields(true, true);
  public static final SessionDataInitializing SESSION_DATA_INITIALIZING =
      new SessionDataInitializing(true, true, true, true, true);
  public static final Registration REGISTRATION_WITH_MANDATORY_FIELDS_TRUE =
      new Registration(3, MANDATORY_FIELDS_TRUE);
  public static final Registration REGISTRATION_WITH_MANDATORY_FIELDS_FALSE =
      new Registration(3, MANDATORY_FIELDS_FALSE);
  public static final ConsultingTypeSettings CONSULTING_TYPE_SETTINGS_WITHOUT_MANDATORY_FIELDS =
      new ConsultingTypeSettings(CONSULTING_TYPE_SUCHT, true, "Hallo", SESSION_DATA_INITIALIZING,
          true, null, false, null, false, null, REGISTRATION_WITH_MANDATORY_FIELDS_FALSE);
  public static final ConsultingTypeSettings CONSULTING_TYPE_SETTINGS_WITH_MANDATORY_FIELDS =
      new ConsultingTypeSettings(CONSULTING_TYPE_U25, true, "Hallo", SESSION_DATA_INITIALIZING,
          true, null, false, null, false, null, REGISTRATION_WITH_MANDATORY_FIELDS_TRUE);
  public static final String CONSULTING_TYPE_SETTINGS_JSON_FILE_PATH = "/monitoring/test.json";
  public static final ConsultingTypeSettings CONSULTING_TYPE_SETTINGS_SUCHT =
      new ConsultingTypeSettings(CONSULTING_TYPE_SUCHT, false, null, SESSION_DATA_INITIALIZING,
          true, CONSULTING_TYPE_SETTINGS_JSON_FILE_PATH, false, null, true, null,
          REGISTRATION_WITH_MANDATORY_FIELDS_TRUE);
  public static final ConsultingTypeSettings CONSULTING_TYPE_SETTINGS_U25 =
      new ConsultingTypeSettings(CONSULTING_TYPE_U25, true, "Hallo", SESSION_DATA_INITIALIZING,
          true, CONSULTING_TYPE_SETTINGS_JSON_FILE_PATH, false, null, false, null,
          REGISTRATION_WITH_MANDATORY_FIELDS_TRUE);
  public static final ConsultingTypeSettings CONSULTING_TYPE_SETTINGS_PREGNANCY =
      new ConsultingTypeSettings(CONSULTING_TYPE_PREGNANCY, false, null, SESSION_DATA_INITIALIZING,
          false, null, false, null, false, null, REGISTRATION_WITH_MANDATORY_FIELDS_FALSE);
  public static final ConsultingTypeSettings CONSULTING_TYPE_SETTINGS_AIDS =
      new ConsultingTypeSettings(CONSULTING_TYPE_PREGNANCY, false, null, SESSION_DATA_INITIALIZING,
          false, null, false, null, true, null, REGISTRATION_WITH_MANDATORY_FIELDS_FALSE);
  public static final ConsultingTypeSettings CONSULTING_TYPE_SETTINGS_CHILDREN =
      new ConsultingTypeSettings(CONSULTING_TYPE_PREGNANCY, false, null, SESSION_DATA_INITIALIZING,
          false, null, false, null, false, null, REGISTRATION_WITH_MANDATORY_FIELDS_FALSE);
  public static final ConsultingTypeSettings CONSULTING_TYPE_SETTINGS_CURE =
      new ConsultingTypeSettings(CONSULTING_TYPE_PREGNANCY, false, null, SESSION_DATA_INITIALIZING,
          false, null, false, null, true, null, REGISTRATION_WITH_MANDATORY_FIELDS_FALSE);
  public static final ConsultingTypeSettings CONSULTING_TYPE_SETTINGS_DEBT =
      new ConsultingTypeSettings(CONSULTING_TYPE_PREGNANCY, false, null, SESSION_DATA_INITIALIZING,
          false, null, false, null, true, null, REGISTRATION_WITH_MANDATORY_FIELDS_FALSE);
  public static final ConsultingTypeSettings CONSULTING_TYPE_SETTINGS_DISABILITY =
      new ConsultingTypeSettings(CONSULTING_TYPE_PREGNANCY, false, null, SESSION_DATA_INITIALIZING,
          false, null, false, null, true, null, REGISTRATION_WITH_MANDATORY_FIELDS_FALSE);
  public static final ConsultingTypeSettings CONSULTING_TYPE_SETTINGS_LAW =
      new ConsultingTypeSettings(CONSULTING_TYPE_PREGNANCY, false, null, SESSION_DATA_INITIALIZING,
          false, null, false, null, true, null, REGISTRATION_WITH_MANDATORY_FIELDS_FALSE);
  public static final ConsultingTypeSettings CONSULTING_TYPE_SETTINGS_OFFENDER =
      new ConsultingTypeSettings(CONSULTING_TYPE_PREGNANCY, false, null, SESSION_DATA_INITIALIZING,
          false, null, false, null, true, null, REGISTRATION_WITH_MANDATORY_FIELDS_FALSE);
  public static final ConsultingTypeSettings CONSULTING_TYPE_SETTINGS_PARENTING =
      new ConsultingTypeSettings(CONSULTING_TYPE_PREGNANCY, false, null, SESSION_DATA_INITIALIZING,
          false, null, false, null, true, null, REGISTRATION_WITH_MANDATORY_FIELDS_FALSE);
  public static final ConsultingTypeSettings CONSULTING_TYPE_SETTINGS_PLANB =
      new ConsultingTypeSettings(CONSULTING_TYPE_PREGNANCY, false, null, SESSION_DATA_INITIALIZING,
          false, null, false, null, false, null, REGISTRATION_WITH_MANDATORY_FIELDS_FALSE);
  public static final ConsultingTypeSettings CONSULTING_TYPE_SETTINGS_REHABILITATION =
      new ConsultingTypeSettings(CONSULTING_TYPE_PREGNANCY, false, null, SESSION_DATA_INITIALIZING,
          false, null, false, null, true, null, REGISTRATION_WITH_MANDATORY_FIELDS_FALSE);
  public static final ConsultingTypeSettings CONSULTING_TYPE_SETTINGS_SENIORITY =
      new ConsultingTypeSettings(CONSULTING_TYPE_PREGNANCY, false, null, SESSION_DATA_INITIALIZING,
          false, null, false, null, true, null, REGISTRATION_WITH_MANDATORY_FIELDS_FALSE);
  public static final ConsultingTypeSettings CONSULTING_TYPE_SETTINGS_SOCIAL =
      new ConsultingTypeSettings(CONSULTING_TYPE_PREGNANCY, false, null, SESSION_DATA_INITIALIZING,
          false, null, false, null, true, null, REGISTRATION_WITH_MANDATORY_FIELDS_FALSE);
  public static final ConsultingTypeSettings CONSULTING_TYPE_SETTINGS_WITHOUT_WELCOME_MESSAGE =
      new ConsultingTypeSettings(CONSULTING_TYPE_U25, false, null, null, true, null, false, null,
          false, null, null);
  public static final ConsultingTypeSettings CONSULTING_TYPE_SETTINGS_WITH_WELCOME_MESSAGE =
      new ConsultingTypeSettings(CONSULTING_TYPE_U25, true, MESSAGE_WITH_NON_REPLACED_USERNAME,
          null, true, null, false, null, false, null, null);
  public static final ConsultingTypeSettings CONSULTING_TYPE_SETTINGS_WIT_MONITORING =
      new ConsultingTypeSettings(CONSULTING_TYPE_U25, false, null, null, true, null, false, null,
          false, null, null);
  public static final ConsultingTypeSettings CONSULTING_TYPE_SETTINGS_WITH_MONITORING =
      new ConsultingTypeSettings(ConsultingType.SUCHT, false, null, null, IS_MONITORING, null,
          false, null, false, null, null);
  public static final ConsultingTypeSettings CONSULTING_TYPE_SETTINGS_WITHOUT_MONITORING =
      new ConsultingTypeSettings(ConsultingType.SUCHT, false, null, null, IS_NOT_MONITORING, null,
          false, null, false, null, null);
  public static List<Long> AGENCY_ID_LIST = Arrays.asList(1L, 2L);
  public static AbsenceDTO ABSENCE_DTO_WITH_HTML_AND_JS =
      new AbsenceDTO(true, TestConstants.MESSAGE_WITH_HTML_AND_JS);
  /*
   * Parameter
   */
  public static int OFFSET_0 = 0;
  public static int COUNT_10 = 10;
  public static int COUNT_1 = 1;
  public static int COUNT_0 = 0;
  public static final SessionDTO SESSION_DTO_WITH_ENCRYPTED_MESSAGE = new SessionDTO(SESSION_ID,
      AGENCY_ID, 0, 0, null, RC_GROUP_ID, RC_FEEDBACK_GROUP_ID, RC_USER_ID, ENCRYPTED_MESSAGE,
      Helper.getUnixTimestampFromDate(NOW), false, false, IS_NO_TEAM_SESSION, IS_MONITORING, null);
  public static final ConsultantSessionResponseDTO CONSULTANT_SESSION_RESPONSE_DTO_WITH_ENCRYPTED_MESSAGE =
      new ConsultantSessionResponseDTO(SESSION_DTO_WITH_ENCRYPTED_MESSAGE, null, null, null,
          new Date(NOW.getTime() + 8640000));
  public static final UserChatDTO USER_CHAT_DTO_WITH_ENCRYPTED_MESSAGE =
      new UserChatDTO(CHAT_ID_3, CHAT_TOPIC_3, null, null, CHAT_DURATION_90, IS_NOT_REPETITIVE,
          IS_NOT_ACTIVE, ConsultingType.CHILDREN.getValue(), ENCRYPTED_MESSAGE,
          Helper.getUnixTimestampFromDate(new Date(NOW.getTime() + 86410000)), MESSAGES_NOT_READ,
          RC_GROUP_ID_6, null, false, null, LocalDateTime.now());
  public static final ConsultantSessionResponseDTO CONSULTANT_SESSION_RESPONSE_DTO_WITH_ENCRYPTED_CHAT_MESSAGE =
      new ConsultantSessionResponseDTO(null, USER_CHAT_DTO_WITH_ENCRYPTED_MESSAGE, null, null,
          new Date(NOW.getTime() + 8640000));
  public static final List<ConsultantSessionResponseDTO> CONSULTANT_SESSION_RESPONSE_DTO_LIST_WITH_ENCRYPTED_CHAT_MESSAGE =
      new ArrayList<ConsultantSessionResponseDTO>() {
        private static final long serialVersionUID = 1L;

        {
          add(CONSULTANT_SESSION_RESPONSE_DTO_WITH_ENCRYPTED_CHAT_MESSAGE);
        }
      };
}
