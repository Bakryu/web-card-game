package ua.od.game.service.impl;

import ua.od.game.dto.MessageDto;
import ua.od.game.model.MessageEntity;
import ua.od.game.repository.dao.MessageDao;
import ua.od.game.service.MessageService;

import javax.inject.Inject;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class MessageServiceImpl implements MessageService {
    @Inject
    private MessageDao messageDao;

    @Override
    public List<MessageDto> getMessageList(Integer fromAccountId, Integer toAccountId, Date fromTime) {
        final List<MessageDto> messages = new LinkedList<>();
        messageDao.getMessageList(fromAccountId,toAccountId,fromTime).forEach(messageEntity -> messages.add(new MessageDto() {{
            setId(messageEntity.getId());
            setFromAccountId(messageEntity.getFromAccountId());
            setToAccountId(messageEntity.getToAccountId());
            setTime(messageEntity.getTime());
            setText(messageEntity.getText());
        }}));
        return messages;
    }

    @Override
    public Boolean sendMessage(MessageDto message) {
        return messageDao.sendMessage(new MessageEntity(){{
            setId(message.getId());
            setFromAccountId(message.getFromAccountId());
            setToAccountId(message.getToAccountId());
            setTime(message.getTime());
            setText(message.getText());
        }});
    }
}
