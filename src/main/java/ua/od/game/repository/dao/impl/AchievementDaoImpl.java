package ua.od.game.repository.dao.impl;

import ua.od.game.model.AchievementEntity;
import ua.od.game.repository.dao.AchievementDao;
import ua.od.game.repository.helper.SqlHelper;

import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

public class AchievementDaoImpl implements AchievementDao {

    private static final String GET_ALL_ACHIEVEMENT_QUERY = "SELECT * FROM Achievement";

    @Override
    public List<AchievementEntity> getAllAchievementList() {
        return SqlHelper.prepareStatement(GET_ALL_ACHIEVEMENT_QUERY, query -> {
            ResultSet resultSet = query.executeQuery();
            List<AchievementEntity> achievementEntityList = new LinkedList<>();
            while (resultSet.next()) {
                achievementEntityList.add(new AchievementEntity() {{
                    setId(resultSet.getInt("id"));
                    setName(resultSet.getString("name"));
                    setDescription(resultSet.getString("description"));
                }});
            }
            return achievementEntityList;
        });
    }
}
