package ua.od.game.repository.dao.impl;

import org.junit.*;
import ua.od.game.model.AccountUpgradeEntity;
import ua.od.game.repository.dao.AccountUpgradeDao;
import ua.od.game.repository.dao.DbTest;

import static org.junit.Assert.*;

public class AccountUpgradeDaoImplTest extends DbTest {
    AccountUpgradeDao accountUpgradeDao;

    @Before
    public void init() {
        accountUpgradeDao = new AccountUpgradeDaoImpl();
    }

    @Test
    public void getAccountUpgradeList() {
        Assert.assertFalse(accountUpgradeDao.getAccountUpgradeList(1).isEmpty());
        accountUpgradeDao.getAccountUpgradeList(1).forEach(System.out::println);
    }

    @Test
    public void clearAccountUpgradeList() {
        Assert.assertTrue(accountUpgradeDao.clearAccountUpgradeList(1));
        accountUpgradeDao.getAccountUpgradeList(1).forEach(System.out::println);
    }

}