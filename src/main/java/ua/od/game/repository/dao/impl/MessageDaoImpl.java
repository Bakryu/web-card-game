
package ua.od.game.repository.dao.impl;

import ua.od.game.model.MessageEntity;
import ua.od.game.repository.dao.MessageDao;
import ua.od.game.repository.helper.SqlHelper;

import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import java.util.logging.Logger;

public class MessageDaoImpl implements MessageDao {
    private static Logger LOG = Logger.getLogger(MessageDaoImpl.class.getName());
    private static final String GET_MESSAGES = "SELECT * FROM Message WHERE from_account_id = ? AND to_account_id = ? AND time > ?";
    private static final String ADD_NEW_MESSAGE = "INSERT INTO Message (text, from_account_id, to_account_id, time) values (?,?,?,?)";

    @Override
    public List<MessageEntity> getMessageList(Integer fromAccountId, Integer toAccountId, Date fromTime) {
        return SqlHelper.prepareStatement(GET_MESSAGES, pstmt -> {
            pstmt.setInt(1, fromAccountId);
            pstmt.setInt(2, toAccountId);
            pstmt.setTimestamp(3, new Timestamp(fromTime.getTime()));
            ResultSet rs = pstmt.executeQuery();
            List<MessageEntity> message = new LinkedList<>();
            while (rs.next()) {
                message.add(new MessageEntity() {{
                    setFromAccountId(rs.getInt("from_account_id"));
                    setToAccountId(rs.getInt("to_account_id"));
                    setTime(rs.getTimestamp("time"));
                    setText(rs.getString("text"));
                }});
            }
            return message;
        });
    }

    @Override
    public Boolean sendMessage(MessageEntity message) {
        Boolean isOk = SqlHelper.prepareStatement(ADD_NEW_MESSAGE, pstmt -> {
            pstmt.setString(1, message.getText());
            pstmt.setInt(2, message.getFromAccountId());
            pstmt.setInt(3, message.getToAccountId());
            pstmt.setDate(4, new java.sql.Date(message.getTime().getTime()));
            return pstmt.executeUpdate() > 0;
        });
        if(!isOk) {
            LOG.warning("Message was not delivered!");
        }
        return isOk;
    }
}

