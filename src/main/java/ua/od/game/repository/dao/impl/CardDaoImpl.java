package ua.od.game.repository.dao.impl;

import ua.od.game.model.BuildingSetEntity;
import ua.od.game.model.CardEntity;
import ua.od.game.model.CardGroupEntity;
import ua.od.game.repository.dao.CardDao;
import ua.od.game.repository.helper.SqlHelper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class CardDaoImpl implements CardDao {
    private static final String GET_CARD = "SELECT * FROM Card c\n" +
            " LEFT JOIN Card_Group cg on c.card_group_id = cg.id \n" +
            " LEFT JOIN Resource_Set rs on c.player_resource_set_id = rs.set_id\n" +
            "and c.enemy_resource_set_id = rs.set_id\n" +
            " LEFT JOIN Building_Set bs on c.player_building_set_id = bs.set_id\n" +
            "and c.enemy_building_set_id = bs.set_id\n" +
            " LEFT JOIN Upgrade_Set us on c.player_upgrade_set_id = us.set_id\n" +
            "and c.enemy_upgrade_set_id = us.set_id\n" +
            " ORDER BY c.id;";
    private CardGroupEntity getCardGropEntity(ResultSet cardsResultSet) throws SQLException {
        CardGroupEntity cardGroupEntity = new CardGroupEntity();
        cardGroupEntity.setId(cardsResultSet.getInt("cg.id"));
        cardGroupEntity.setName( cardsResultSet.getString("cg.name"));
        cardGroupEntity.setDescription( cardsResultSet.getString("cg.description"));
        return cardGroupEntity;

    }
    private List<ResourceSetEntity> getResorceSetEntity(ResultSet cardsResaltSet) throws SQLException {
        List<ResourceSetEntity> resourceSetEntityList = new ArrayList<>();
        ResourceSetEntity resourceSetEntity = new ResourceSetEntity();
        resourceSetEntity.setId(cardsResaltSet.getInt("rs.id"));
        resourceSetEntity.setSetId(cardsResaltSet.getInt("rs.setId"));
        resourceSetEntity.setResourceId(cardsResaltSet.getInt("rs.resourceId"));
        resourceSetEntityList.add(resourceSetEntity);

              return resourceSetEntityList;
    }
    private List<BuildingSetEntity> getBuildingSetEntity(ResultSet cardsResaltSet) throws SQLException {
        List<BuildingSetEntity> buildingSetEntitie = new ArrayList<>();
        BuildingSetEntity buildingSetEntity = new BuildingSetEntity();
        buildingSetEntity.setId(cardsResaltSet.getInt("bs.id"));
        buildingSetEntity.setSetId(cardsResaltSet.getInt("bs.setId"));
        buildingSetEntity.setResourceId(cardsResaltSet.getInt("bs.resourceId"));
        buildingSetEntitie.add(buildingSetEntity);

        return resourceSetEntityList;
    } private List<ResourceSetEntity> getResorceSetEntity(ResultSet cardsResaltSet) throws SQLException {
        List<ResourceSetEntity> resourceSetEntityList = new ArrayList<>();
        ResourceSetEntity resourceSetEntity = new ResourceSetEntity();
        resourceSetEntity.setId(cardsResaltSet.getInt("rs.id"));
        resourceSetEntity.setSetId(cardsResaltSet.getInt("rs.setId"));
        resourceSetEntity.setResourceId(cardsResaltSet.getInt("rs.resourceId"));
        resourceSetEntityList.add(resourceSetEntity);

        return resourceSetEntityList;
    } private List<ResourceSetEntity> getResorceSetEntity(ResultSet cardsResaltSet) throws SQLException {
        List<ResourceSetEntity> resourceSetEntityList = new ArrayList<>();
        ResourceSetEntity resourceSetEntity = new ResourceSetEntity();
        resourceSetEntity.setId(cardsResaltSet.getInt("rs.id"));
        resourceSetEntity.setSetId(cardsResaltSet.getInt("rs.setId"));
        resourceSetEntity.setResourceId(cardsResaltSet.getInt("rs.resourceId"));
        resourceSetEntityList.add(resourceSetEntity);

        return resourceSetEntityList;
    } private List<ResourceSetEntity> getResorceSetEntity(ResultSet cardsResaltSet) throws SQLException {
        List<ResourceSetEntity> resourceSetEntityList = new ArrayList<>();
        ResourceSetEntity resourceSetEntity = new ResourceSetEntity();
        resourceSetEntity.setId(cardsResaltSet.getInt("rs.id"));
        resourceSetEntity.setSetId(cardsResaltSet.getInt("rs.setId"));
        resourceSetEntity.setResourceId(cardsResaltSet.getInt("rs.resourceId"));
        resourceSetEntityList.add(resourceSetEntity);

        return resourceSetEntityList;
    }





    }

    @Override
    public List<CardEntity> getAllCardList() {
        return SqlHelper.prepareStatement(GET_CARD, statementForCardList -> {
            ResultSet cardsResultSet = statementForCardList.executeQuery();
            List<CardEntity> cards = new LinkedList<>();

            while (cardsResultSet.next()) {
                cards.add(new CardEntity() {{
                    setId(cardsResultSet.getInt("id"));
                    setName(cardsResultSet.getString("name"));
                    setDescription(cardsResultSet.getString("description"));
                    setCardGroup(getCardGropEntity(cardsResultSet));
                    setPalayerResourceSetList(getResorceSetEntity(cardsResultSet));
                    ResourceSetEntity resourceSetEntity = new ResourceSetEntity(cardsResultSet.getInt())
                    List<ResourceSetEntity> resourceSetEntities = new ArrayList<>();
                    resourceSetEntities.
                    setPalayerResourceSetList();




                }}

            }
        });

        return null;




}}

