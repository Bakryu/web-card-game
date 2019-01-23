package ua.od.game.repository.dao.impl;

import ua.od.game.model.BuildingEntity;
import ua.od.game.model.ResourceSetEntity;
import ua.od.game.repository.dao.BuildingDao;
import ua.od.game.repository.helper.SqlHelper;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


public class BuildingDaoImpl implements BuildingDao {

    private final String GET_BUILDING_LIST_QUERY = "SELECT * FROM Building";
    private final String GET_RES_SET_LIST_FOR_BUILDINGS_QUERY = "SELECT * FROM Resource_Set rs " +
                            "WHERE set_id IN (" +
                            "SELECT resource_set_id FROM Building b " +
                            "INNER JOIN Resource_Set rs " +
                            "ON b.resource_set_id = rs.set_id);";

    @Override
    public List<BuildingEntity> getAllBuildingList() {
        List<ResourceSetEntity> rsList = getAllResourceSet();
        return SqlHelper.prepareStatement(GET_BUILDING_LIST_QUERY, statementForBuildingList -> {
            ResultSet buildingResSet = statementForBuildingList.executeQuery();
            List<BuildingEntity> buildings = new ArrayList<>();
            while (buildingResSet.next()) {
                buildings.add(new BuildingEntity() {{
                    setId(buildingResSet.getInt("id"));
                    setName(buildingResSet.getString("name"));
                    setDescription(buildingResSet.getString("description"));
                    setDefaultNumber(buildingResSet.getInt("default_number"));
                    setResourceSetList(getAllBuildingResourceSetList(
                            buildingResSet.getInt("resource_set_id"), rsList));
                }});
            }
            return buildings;
        });
    }

    // Getting all resource_sets that are used for buildings
    protected List<ResourceSetEntity> getAllResourceSet() {
        return SqlHelper.prepareStatement(GET_RES_SET_LIST_FOR_BUILDINGS_QUERY, statement -> {
            ResultSet rsResultSet = statement.executeQuery();
            List<ResourceSetEntity> rsList = new ArrayList<>();
            while (rsResultSet.next()) {
                rsList.add(new ResourceSetEntity() {{
                    setId(rsResultSet.getInt("id"));
                    setSetId(rsResultSet.getInt("set_id"));
                    setResourceId(rsResultSet.getInt("resource_id"));
                    setAmount(rsResultSet.getFloat("amount"));
                }});
            }
            return rsList;
        });
    }

    // Getting resource_sets for exact building
    protected List<ResourceSetEntity> getAllBuildingResourceSetList(int resourceSetId, List<ResourceSetEntity> rsList) {
        List<ResourceSetEntity> bRsList = new ArrayList<>();
        for (ResourceSetEntity rsEntity : rsList) {
            if (rsEntity.getSetId().equals(resourceSetId)) {
                bRsList.add(rsEntity);
            }
        }
        return bRsList;
    }

}

