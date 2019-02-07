package ua.od.game.repository.dao.impl;

import ua.od.game.model.AccountUpgradeEntity;
import ua.od.game.repository.dao.AccountUpgradeDao;
import ua.od.game.repository.helper.SqlHelper;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class AccountUpgradeDaoImpl implements AccountUpgradeDao {
    private static final String GET_ACCOUNT_UPGRADE = "SELECT * FROM `Account_Upgrade` where account_id = ?;";
    private static final String CLEAR_ACCOUNT_UPGRADE = "DELETE FROM `Account_Upgrade` where account_id = ";

    @Override
    public Boolean clearAccountUpgradeList(Integer accountId) {
        return SqlHelper.prepareStatement(CLEAR_ACCOUNT_UPGRADE + accountId + ";", statement -> {
            return statement.execute();
        });
    }

    @Override
    public List<AccountUpgradeEntity> getAccountUpgradeList(Integer accountId) {
        return SqlHelper.prepareStatement(GET_ACCOUNT_UPGRADE, statement -> {
            statement.setInt(1, accountId);
            ResultSet resultSet = statement.executeQuery();
            List<AccountUpgradeEntity> accountUpgradeEntities = new ArrayList<>();
            while (resultSet.next()) {
                accountUpgradeEntities.add(new AccountUpgradeEntity() {
                    {
                        setUpgradeId(resultSet.getInt("upgrade_id"));
                        setAmount(resultSet.getInt("amount"));
                    }
                });
            }
            return accountUpgradeEntities;
        });
    }
}
