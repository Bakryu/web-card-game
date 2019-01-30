package ua.od.game.repository.dao.impl;

import ua.od.game.model.*;
import ua.od.game.repository.dao.CardDao;
import ua.od.game.repository.helper.SqlHelper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class CardDaoImpl implements CardDao {
    private static final String GET_CARD = "SELECT c.id c_id , c.name c_name , c.description c_description , cg.id cg_id , cg.name cg_name , cg.description cg_description  , rs.id rs_id , rs.set_id rs_set_id , rs.resource_id rs_resource_id  , rs.amount rs_amount , bs.id bs_id , bs.set_id bs_set_id , bs.building_id bs_building_id , bs.amount bs_amount , us.id us_id , us.set_id us_set_id , us.upgrade_id us_upgrade_id , us.amount us_amount  FROM Card c\n" +
            " LEFT JOIN Card_Group cg on c.card_group_id = cg.id \n" +
            " LEFT JOIN Resource_Set rs on c.player_resource_set_id = rs.set_id\n" +
            "and c.enemy_resource_set_id = rs.set_id\n" +
            " LEFT JOIN Building_Set bs on c.player_building_set_id = bs.set_id\n" +
            "and c.enemy_building_set_id = bs.set_id\n" +
            " LEFT JOIN Upgrade_Set us on c.player_upgrade_set_id = us.set_id\n" +
            "and c.enemy_upgrade_set_id = us.set_id\n" +
            " ORDER BY c.id;";


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
                    setPalayerBuildingSetList(getBuildingSetEntity(cardsResultSet));
                    setPalayerUpgradeSetList(getUpgradeSetEntity(cardsResultSet));
                    setEnemyResourceSetList(getResorceSetEntity(cardsResultSet));
                    setEnemyBuildingSetList(getBuildingSetEntity(cardsResultSet));
                    setEnemyUpgradeSetList(getUpgradeSetEntity(cardsResultSet));
                }});

            }
            return cards;
        });


    }

    private CardGroupEntity getCardGropEntity(ResultSet cardsResultSet) throws SQLException {
        CardGroupEntity cardGroupEntity = new CardGroupEntity();
        cardGroupEntity.setId(cardsResultSet.getInt("cg.id"));
        cardGroupEntity.setName(cardsResultSet.getString("cg.name"));
        cardGroupEntity.setDescription(cardsResultSet.getString("cg.description"));
        return cardGroupEntity;

    }

    private List<ResourceSetEntity> getResorceSetEntity(ResultSet cardsResaltSet) throws SQLException {
        List<ResourceSetEntity> resourceSetEntityList = new ArrayList<>();
        ResourceSetEntity resourceSetEntity = new ResourceSetEntity();
        resourceSetEntity.setId(cardsResaltSet.getInt("rs.id"));
        resourceSetEntity.setSetId(cardsResaltSet.getInt("rs.setId"));
        resourceSetEntity.setResourceId(cardsResaltSet.getInt("rs.resourceId"));
        resourceSetEntity.setAmount(cardsResaltSet.getFloat("rs.amount"));
        resourceSetEntityList.add(resourceSetEntity);

        return resourceSetEntityList;
    }

    private List<BuildingSetEntity> getBuildingSetEntity(ResultSet cardsResaltSet) throws SQLException {
        List<BuildingSetEntity> buildingSetEntitiesSet = new ArrayList<>();
        BuildingSetEntity buildingSetEntity = new BuildingSetEntity();
        buildingSetEntity.setId(cardsResaltSet.getInt("bs.id"));
        buildingSetEntity.setSetId(cardsResaltSet.getInt("bs.setId"));
        buildingSetEntity.setBuildingId(cardsResaltSet.getInt("bs.buildingId"));
        buildingSetEntity.setAmount(cardsResaltSet.getFloat("bs.amount"));
        buildingSetEntitiesSet.add(buildingSetEntity);
        return buildingSetEntitiesSet;
    }

    private List<UpgradeSetEntity> getUpgradeSetEntity(ResultSet cardsResaltSet) throws SQLException {
        List<UpgradeSetEntity> upgradeSetEntitisList = new ArrayList<>();
        UpgradeSetEntity upgradeSetEntitie = new UpgradeSetEntity();
        upgradeSetEntitie.setId(cardsResaltSet.getInt("us.id"));
        upgradeSetEntitie.setSetId(cardsResaltSet.getInt("us.setId"));
        upgradeSetEntitie.setUpgradeId(cardsResaltSet.getInt("us.resourceId"));
        upgradeSetEntitie.setAmount(cardsResaltSet.getFloat("us.amount"));
        upgradeSetEntitisList.add(upgradeSetEntitie);
        return upgradeSetEntitisList;
    }
}

