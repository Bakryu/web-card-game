package ua.od.game.repository.dao.impl;


import org.junit.Before;
import org.junit.Test;
import ua.od.game.model.MessageEntity;
import ua.od.game.repository.dao.DbTest;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


/**
 * @author Andrey Kolodiy
 */

public class MessageDaoImplTest extends DbTest {
    MessageDaoImpl messageDao;

    private static final Integer FROM_ACCOUNT_ID = 222;
    private static final Integer TO_ACCOUNT_ID = 111;
    private final Date GET_DATETIME = Timestamp.valueOf("2019-01-15 10:45:00");
    private final Date SET_DATETIME = Timestamp.valueOf("2019-01-15 10:46:00");
    private final String TEST_MESSAGE = "Test messages";

    @Before
    public void init() {
        messageDao = new MessageDaoImpl();
    }

    @Test
    public void getMessageListTest() {
        messageDao.getMessageList(FROM_ACCOUNT_ID, TO_ACCOUNT_ID, GET_DATETIME).forEach(messages -> {
            assertEquals(FROM_ACCOUNT_ID, messages.getFromAccountId());
            assertEquals(TO_ACCOUNT_ID, messages.getToAccountId());
            assertTrue(messages.getTime().after(GET_DATETIME));
        });
    }

    @Test
    public void sendMessageTest() {
        MessageEntity messageEntity = new MessageEntity();
        messageEntity.setTime(SET_DATETIME);
        messageEntity.setFromAccountId(FROM_ACCOUNT_ID);
        messageEntity.setToAccountId(TO_ACCOUNT_ID);
        messageEntity.setText(TEST_MESSAGE);

        messageDao.sendMessage(messageEntity);

        List<MessageEntity> messages = messageDao.getMessageList(FROM_ACCOUNT_ID, TO_ACCOUNT_ID, GET_DATETIME);
        {
            Boolean checkFlag = false;
            for (MessageEntity mesages : messages) {
                if (mesages.getText().equals(TEST_MESSAGE)) {
                    checkFlag = true;
                    break;
                }
            }
            assertTrue(checkFlag);
            assertEquals(3, messages.size());
            assertTrue(!messages.isEmpty());
        }
    }
}