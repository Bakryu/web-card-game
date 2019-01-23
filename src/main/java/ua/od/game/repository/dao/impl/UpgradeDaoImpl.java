package ua.od.game.repository.dao.impl;

import ua.od.game.model.BuildingSetEntity;
import ua.od.game.model.ResourceSetEntity;
import ua.od.game.model.UpgradeEntity;
import ua.od.game.repository.dao.UpgradeDao;
import ua.od.game.repository.helper.SqlHelper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UpgradeDaoImpl implements UpgradeDao {

    private static final String GET_ALL_UPGRADES =
            "select u.id u_id, " +
            "       u.name,\n" +
            "       u.description,\n" +
            "       u.default_number,\n" +
            "       u.resource_set_id,\n" +
            "       u.building_set_id,\n" +
            "       rs.id rs_id, rs.resource_id, rs.amount rs_amount,\n" +
            "       bs.id bs_id, bs.building_id, bs.amount bs_amount\n" +
            "from Upgrade u\n" +
            "inner join Resource_Set rs on u.resource_set_id = rs.id\n" +
            "inner join Building_Set bs on u.building_set_id = bs.id;";

    /**
     * @return the static list of nested elements with information about upgrade,
     * resource(s) and building(s) which will be upgraded.
     * @author Vladimir Pletniov
     */
    @Override
    public List<UpgradeEntity> getAllUpgradeList() {
        return SqlHelper.prepareStatement(GET_ALL_UPGRADES, statement -> {
            ResultSet resultSet = statement.executeQuery();
            List<UpgradeEntity> upgradeEntities = new ArrayList<>();
            while (resultSet.next()) {
                upgradeEntities.add(new UpgradeEntity() {{
                        setId(resultSet.getInt("u_id"));
                        setName(resultSet.getString("name"));
                        setDescription(resultSet.getString("description"));
                        setDefaultNumber(resultSet.getInt("default_number"));
                        setResourceSetList(fetchResourceSetEntityList(resultSet));
                        setBuildingSetList(fetchBuildingSetEntityList(resultSet));
                }});
            }
            return upgradeEntities;
        });

    }

    private List<BuildingSetEntity> fetchBuildingSetEntityList(ResultSet resultSet) throws SQLException {
        List<BuildingSetEntity> buildingSetEntityList = new ArrayList<>();
        buildingSetEntityList.add(new BuildingSetEntity() {{
                setId(resultSet.getInt("bs_id"));
                setBuildingId(resultSet.getInt("building_id"));
                setAmount(resultSet.getFloat("bs_amount"));
        }});
        return buildingSetEntityList;
    }

    private List<ResourceSetEntity> fetchResourceSetEntityList(ResultSet resultSet) throws SQLException {
        List<ResourceSetEntity> resourceSetEntityList = new ArrayList<>();
        resourceSetEntityList.add(new ResourceSetEntity() {{
                setId(resultSet.getInt("rs_id"));
                setResourceId(resultSet.getInt("resource_id"));
                setAmount(resultSet.getFloat("rs_amount"));
        }});
        return resourceSetEntityList;
    }
}



