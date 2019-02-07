package ua.od.game.repository.dao.impl;

import org.junit.Test;
import static org.junit.Assert.*;
import ua.od.game.model.NotificationEntity;
import ua.od.game.repository.dao.DbTest;

import java.util.List;


public class NotificationDaoImplTest extends DbTest {

    private NotificationDaoImpl notificationDao = new NotificationDaoImpl();

    @Test
    public void getAllNotificationListTest(){
        List<NotificationEntity> notificationEntities;
        notificationEntities = notificationDao.getAllNotificationList();
        System.out.println(notificationEntities);

        assertFalse(notificationEntities.isEmpty());
    }
}
