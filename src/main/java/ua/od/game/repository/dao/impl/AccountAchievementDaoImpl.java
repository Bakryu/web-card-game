package ua.od.game.repository.dao.impl;

import ua.od.game.model.AccountAchievementEntity;
import ua.od.game.repository.dao.AccountAchievementDao;
import ua.od.game.repository.helper.SqlHelper;

import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

public class AccountAchievementDaoImpl implements AccountAchievementDao {

    private static final String GET_ACCOUNT_ACHIEVEMENT_LIST = "SELECT * FROM Account_Achievement WHERE account_id = ?";

    @Override
    public List<AccountAchievementEntity> getUserAchievementsList(Integer accountId) {
        return SqlHelper.prepareStatement(GET_ACCOUNT_ACHIEVEMENT_LIST, query -> {
            query.setInt(1, accountId);
            ResultSet resultSet = query.executeQuery();
            List<AccountAchievementEntity> result = new LinkedList<>();
            while (resultSet.next()) {
                result.add(new AccountAchievementEntity() {{
                    setId(resultSet.getInt("id"));
                    setAccountAchievementId(resultSet.getInt("account_id"));
                    setAchievementId(resultSet.getInt("achievement_id"));
                    setAmount(resultSet.getFloat("amount"));
                }});
            }
            return result;
        });
    }
}