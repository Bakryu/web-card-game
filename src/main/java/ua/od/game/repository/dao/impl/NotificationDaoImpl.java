package ua.od.game.repository.dao.impl;


import ua.od.game.model.BuildingSetEntity;
import ua.od.game.model.NotificationEntity;
import ua.od.game.model.ResourceSetEntity;
import ua.od.game.model.UpgradeSetEntity;
import ua.od.game.repository.dao.NotificationDao;
import ua.od.game.repository.helper.SqlHelper;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class NotificationDaoImpl implements NotificationDao {
    private static final String GET_ALL_NOTIFICATIONS =
            "SELECT * FROM Notification";
    private static final String RESOURCE_SET_FOR_NOTIFICATION =
            "SELECT resource_set.id, set_id, resource_id, amount FROM Resource_set " +
            "JOIN Notification ON Notification.resource_set_id = Resource_set.set_id " +
            "WHERE Notification.id = ?";
    private static final String BUILDING_SET_FOR_NOTIFICATION =
            "SELECT Building_set.id, set_id, building_id, amount FROM Building_set " +
            "JOIN Notification ON Notification.building_set_id = Building_set.set_id " +
            "WHERE Notification.id = ?";
    private static final String UPGRADE_SET_FOR_NOTIFICATION =
            "SELECT Upgrade_set.id, set_id, upgrade_id, amount FROM Upgrade_set " +
            "JOIN Notification ON Notification.upgrade_set_id = Upgrade_set.set_id " +
            "WHERE Notification.id = ?";

    @Override
    public List<NotificationEntity> getAllNotificationList() {
        List<NotificationEntity> notificationList = new ArrayList<>();
        fillBasicInfo(notificationList);
        fillResourceSet(notificationList);
        fillBuildingSet(notificationList);
        fillUpgradeSet(notificationList);
        return notificationList;
    }

    private void fillBasicInfo(List<NotificationEntity> notificationEntities) {
        SqlHelper.createStatement(statement -> {
            ResultSet basicInfo = statement.executeQuery(GET_ALL_NOTIFICATIONS);
            while(basicInfo.next()) {
                NotificationEntity notificationEntity = new NotificationEntity() {{
                    setId(basicInfo.getInt("id"));
                    setName(basicInfo.getString("name"));
                    setDescription(basicInfo.getString("description"));
                    setShowFromScratch(basicInfo.getInt("show_from_scrach"));
                }};
                notificationEntities.add(notificationEntity);
            }
            return null;
        });
    }

    private void fillResourceSet(List<NotificationEntity> notificationEntities) {
        SqlHelper.prepareStatement(RESOURCE_SET_FOR_NOTIFICATION, statement -> {
            for (NotificationEntity notificationEntity : notificationEntities) {
                statement.setInt(1, notificationEntity.getId());
                ResultSet resultSet = statement.executeQuery();

                while(resultSet.next()) {
                    ResourceSetEntity resourceSetEntity = new ResourceSetEntity() {{
                        setId(resultSet.getInt("id"));
                        setSetId(resultSet.getInt("set_id"));
                        setResourceId(resultSet.getInt("resource_id"));
                        setAmount(resultSet.getFloat("amount"));
                    }};
                    notificationEntity.getResourceSetList().add(resourceSetEntity);
                }
            }
            return null;
        });
    }

    private void fillBuildingSet(List<NotificationEntity> notificationEntities) {
        SqlHelper.prepareStatement(BUILDING_SET_FOR_NOTIFICATION, statement -> {
            for (NotificationEntity notificationEntity : notificationEntities) {
                statement.setInt(1, notificationEntity.getId());
                ResultSet resultSet = statement.executeQuery();

                while(resultSet.next()) {
                    BuildingSetEntity buildingSetEntity = new BuildingSetEntity() {{
                        setId(resultSet.getInt("id"));
                        setSetId(resultSet.getInt("set_id"));
                        setBuildingId(resultSet.getInt("building_id"));
                        setAmount(resultSet.getFloat("amount"));
                    }};
                    notificationEntity.getBuildingSetList().add(buildingSetEntity);
                }
            }
            return null;
        });
    }

    private void fillUpgradeSet(List<NotificationEntity> notificationEntities) {
        SqlHelper.prepareStatement(UPGRADE_SET_FOR_NOTIFICATION, statement -> {
            for (NotificationEntity notificationEntity : notificationEntities) {
                statement.setInt(1, notificationEntity.getId());
                ResultSet resultSet = statement.executeQuery();

                while(resultSet.next()) {
                    UpgradeSetEntity upgradeSetEntity = new UpgradeSetEntity() {{
                        setId(resultSet.getInt("id"));
                        setSetId(resultSet.getInt("set_id"));
                        setUpgradeId(resultSet.getInt("upgrade_id"));
                        setAmount(resultSet.getFloat("amount"));
                    }};
                    notificationEntity.getUpgradeSetList().add(upgradeSetEntity);
                }
            }
            return null;
        });
    }
}
