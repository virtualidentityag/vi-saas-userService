package de.caritas.cob.userservice.api.port.out;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import de.caritas.cob.userservice.api.helper.CustomLocalDateTime;
import de.caritas.cob.userservice.api.model.Chat;
import de.caritas.cob.userservice.api.model.ChatUser;
import de.caritas.cob.userservice.api.model.Consultant;
import de.caritas.cob.userservice.api.model.User;
import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;

@DataJpaTest
@TestPropertySource(properties = "spring.profiles.active=testing")
@AutoConfigureTestDatabase(replace = Replace.ANY)
class ChatUserRepositoryTest {

  private static final EasyRandom easyRandom = new EasyRandom();

  @Autowired
  ChatUserRepository chatUserRepository;

  @Autowired
  ChatRepository chatRepository;

  @Autowired
  UserRepository userRepository;

  @Autowired
  ConsultantRepository consultantRepository;

  @Test
  void save_Should_saveChatUserRelation() {
    // given
    var chat = givenChat();
    var user = givenUser();

    // when
    var chatUser = chatUserRepository.save(new ChatUser(chat, user));

    // then
    assertNotNull(chat.getId());
    assertEquals(chat, chatUser.getChat());
    assertEquals(user, chatUser.getUser());
  }

  @Test
  void findByChatAndUser_Should_findChatUserCombination() {
    // given
    var chat = givenChat();
    var user = givenUser();
    var chatUser = chatUserRepository.save(new ChatUser(chat, user));

    // when
    var findByChatAndUser = chatUserRepository.findByChatAndUser(chat, user).orElseThrow();

    // then
    assertEquals(chatUser.getId(), findByChatAndUser.getId());
    assertEquals(chatUser.getChat(), findByChatAndUser.getChat());
    assertEquals(chatUser.getUser(), findByChatAndUser.getUser());
  }

  private Chat givenChat() {
    Chat chat = easyRandom.nextObject(Chat.class);
    chat.setId(null);
    chat.setActive(true);
    chat.setRepetitive(true);
    chat.setChatOwner(givenConsultant());
    chat.setConsultingTypeId(easyRandom.nextInt(128));
    chat.setDuration(easyRandom.nextInt(32768));
    chat.setMaxParticipants(easyRandom.nextInt(128));
    chat.setUpdateDate(CustomLocalDateTime.nowInUtc());
    return chatRepository.save(chat);
  }

  private User givenUser() {
    return userRepository.findById("015d013d-95e7-4e91-85b5-12cdb3d317f3").orElseThrow();
  }

  private Consultant givenConsultant() {
    return consultantRepository.findById("0b3b1cc6-be98-4787-aa56-212259d811b9").orElseThrow();
  }
}