package ua.od.game.repository.dao.impl;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ua.od.game.repository.dao.DbTest;
import ua.od.game.repository.dao.UpgradeDao;


public class UpgradeDaoImplTest extends DbTest {

    UpgradeDao upgradeDao;

    @Before
    public void init() {
        upgradeDao = new UpgradeDaoImpl();
    }

    @Test
    public void getAllUpgradeList() {
        Assert.assertFalse(upgradeDao.getAllUpgradeList().isEmpty());
        upgradeDao.getAllUpgradeList().forEach(System.out::println);
    }
}