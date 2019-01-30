package ua.od.game.repository.dao.impl;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import ua.od.game.model.AchievementEntity;
import ua.od.game.repository.dao.AchievementDao;
import ua.od.game.repository.dao.DbTest;

import java.util.List;

public class AchievementDaoImplTest extends DbTest {
    AchievementDao achievementDao;

    @Before
    public void init() {
        achievementDao = new AchievementDaoImpl();
    }
    @Test
    public void getAllAchievementList (){
        List<AchievementEntity> achievementEntities = achievementDao.getAllAchievementList();
        for (int i = 0; i < achievementEntities.size(); i++) {
            System.out.println(achievementEntities.get(i).getId() + " ");
            System.out.print(achievementEntities.get(i).getName() + " ");
            System.out.println(achievementEntities.get(i).getDescription() + " ");
        }
        Assert.assertFalse(achievementEntities.isEmpty());
    }
}