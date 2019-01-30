package ua.od.game.repository.dao.impl;

import org.junit.Before;
import org.junit.Test;
import ua.od.game.model.AccountAchievementEntity;
import ua.od.game.repository.dao.DbTest;

import java.util.List;

import static org.junit.Assert.assertFalse;

public class AccountAchievementDaoImplTest extends DbTest {
    private AccountAchievementDaoImpl achievementDao;

    @Before
    public void init(){
        achievementDao = new AccountAchievementDaoImpl();

    }
    @Test
   public void getUserAchievementsList(){

        List<AccountAchievementEntity>accountAchievementEntities = achievementDao.getUserAchievementsList(2);

        for (int i = 0; i < accountAchievementEntities.size(); i++) {
            System.out.println(accountAchievementEntities.get(i).getId() + " ");
            System.out.println(accountAchievementEntities.get(i).getAccountAchievementId() + " ");
            System.out.println(accountAchievementEntities.get(i).getAchievementId() + " ");
            System.out.println(accountAchievementEntities.get(i).getAmount() + " ");
        }
        assertFalse(accountAchievementEntities.isEmpty());
    }

}
