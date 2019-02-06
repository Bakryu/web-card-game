package ua.od.game.repository.dao.impl;

import ua.od.game.model.*;
import ua.od.game.repository.dao.CardDao;
import ua.od.game.repository.helper.SqlHelper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CardDaoImpl implements CardDao {

    private static final String GET_ALL_CARDS = "SELECT c.id c_id, c.name c_name, c.description c_description,\n" +
            "cg.id cg_id, cg.name cg_name, cg.description cg_description,\n" +
            "p_rs.set_id p_rs_set_id, p_rs.resource_id p_rs_resource_id, p_rs.amount p_rs_amount,\n" +
            "p_bs.set_id p_bs_set_id, p_bs.building_id p_bs_building_id, p_bs.amount p_bs_amount,\n" +
            "p_us.set_id p_us_set_id, p_us.upgrade_id p_us_upgrade_id, p_us.amount p_us_amount,\n" +
            "e_rs.set_id e_rs_set_id, e_rs.resource_id e_rs_resource_id, e_rs.amount e_rs_amount,\n" +
            "e_bs.set_id e_bs_set_id, e_bs.building_id e_bs_building_id, e_bs.amount e_bs_amount,\n" +
            "e_us.set_id e_us_set_id, e_us.upgrade_id e_us_upgrade_id, e_us.amount e_us_amount\n" +
            "FROM Card c\n" +
            "INNER JOIN Card_Group cg on c.id = cg.id\n" +
            "INNER JOIN Resource_Set p_rs on c.player_resource_set_id = p_rs.set_id\n" +
            "INNER JOIN Building_Set p_bs on c.player_building_set_id = p_bs.set_id\n" +
            "INNER JOIN Upgrade_Set p_us on c.player_upgrade_set_id = p_us.set_id\n" +
            "INNER JOIN Resource_Set e_rs on c.enemy_resource_set_id = e_rs.set_id\n" +
            "INNER JOIN Building_Set e_bs on c.enemy_building_set_id = e_bs.set_id\n" +
            "INNER JOIN Upgrade_Set e_us on c.enemy_upgrade_set_id = e_us.set_id\n" +
            "ORDER BY c_id;";


    public List<CardEntity> getAllCardList() {

        return SqlHelper.prepareStatement(GET_ALL_CARDS, statement -> {
            ResultSet rs = statement.executeQuery();
            List<CardEntity> cards = new ArrayList();
            CardEntity currentCard = new CardEntity();

            while (rs.next()) {
                currentCard = fetchCardEntity(rs, cards, currentCard);
                fetchPlayerResourceSetEntity(rs, currentCard.getPalayerResourceSetList());
                fetchPlayerBuildingSetEntity(rs, currentCard.getPalayerBuildingSetList());
                fetchPlayerUpgradeSetEntity(rs, currentCard.getPalayerUpgradeSetList());
                fetchEnemyResourceSetEntity(rs, currentCard.getEnemyResourceSetList());
                fetchEnemyBuildingSetEntity(rs, currentCard.getEnemyBuildingSetList());
                fetchEnemyUpgradeSetEntity(rs, currentCard.getEnemyUpgradeSetList());
            }

            return cards;

        });

    }

    private CardEntity fetchCardEntity(ResultSet rs, List<CardEntity> cards, CardEntity currentCard) throws SQLException {

        if (currentCard.getId() != null && currentCard.getId() == rs.getInt("c_id")) return currentCard;
        CardEntity card = new CardEntity() {{
            setId(rs.getInt("c_id"));
            setName(rs.getString("c_name"));
            setDescription(rs.getString("c_description"));
            setCardGroup(getCardGroupEntity(rs));
            setPalayerResourceSetList(new ArrayList());
            setPalayerBuildingSetList(new ArrayList());
            setPalayerUpgradeSetList(new ArrayList());
            setEnemyResourceSetList(new ArrayList());
            setEnemyBuildingSetList(new ArrayList());
            setEnemyUpgradeSetList(new ArrayList());

        }};

        cards.add(card);


        return card;

    }

    private CardGroupEntity getCardGroupEntity(ResultSet rs) throws SQLException {
        CardGroupEntity cg_entity = new CardGroupEntity();
        cg_entity.setId(rs.getInt("cg_id"));
        cg_entity.setName(rs.getString("cg_name"));
        cg_entity.setDescription(rs.getString("cg_description"));
        return cg_entity;
    }

    private void fetchPlayerBuildingSetEntity(ResultSet rs, List<BuildingSetEntity> buildingSetEntities) throws SQLException {

        if (rs.getString("p_bs_set_id") == null) return;
        buildingSetEntities.add(new BuildingSetEntity() {{
            setSetId(rs.getInt("p_bs_set_id"));
            setBuildingId(rs.getInt("p_bs_building_id"));
            setAmount(rs.getFloat("p_bs_amount"));

        }});

    }

    private void fetchPlayerResourceSetEntity(ResultSet rs, List<ResourceSetEntity> resourceSetEntities) throws SQLException {

        if (rs.getString("p_rs_set_id") == null) return;
        resourceSetEntities.add(new ResourceSetEntity() {{
            setSetId(rs.getInt("p_rs_set_id"));
            setResourceId(rs.getInt("p_rs_resource_id"));
            setAmount(rs.getFloat("p_rs_amount"));

        }});

    }

    private void fetchPlayerUpgradeSetEntity(ResultSet rs, List<UpgradeSetEntity> upgradeSetEntities) throws SQLException {

        if (rs.getString("p_us_set_id") == null) return;
        upgradeSetEntities.add(new UpgradeSetEntity() {{
            setSetId(rs.getInt("p_us_set_id"));
            setUpgradeId(rs.getInt("p_us_upgrade_id"));
            setAmount(rs.getFloat("p_us_amount"));

        }});

    }

    private void fetchEnemyBuildingSetEntity(ResultSet rs, List<BuildingSetEntity> buildingSetEntities) throws SQLException {

        if (rs.getString("e_bs_set_id") == null) return;
        buildingSetEntities.add(new BuildingSetEntity() {{
            setSetId(rs.getInt("e_bs_set_id"));
            setBuildingId(rs.getInt("e_bs_building_id"));
            setAmount(rs.getFloat("e_bs_amount"));

        }});

    }

    private void fetchEnemyResourceSetEntity(ResultSet rs, List<ResourceSetEntity> resourceSetEntities) throws SQLException {

        if (rs.getString("e_rs_set_id") == null) return;
        resourceSetEntities.add(new ResourceSetEntity() {{
            setSetId(rs.getInt("e_rs_set_id"));
            setResourceId(rs.getInt("e_rs_resource_id"));
            setAmount(rs.getFloat("e_rs_amount"));

        }});

    }

    private void fetchEnemyUpgradeSetEntity(ResultSet rs, List<UpgradeSetEntity> upgradeSetEntities) throws SQLException {

        if (rs.getString("e_us_set_id") == null) return;
        upgradeSetEntities.add(new UpgradeSetEntity() {{
            setSetId(rs.getInt("e_us_set_id"));
            setUpgradeId(rs.getInt("e_us_upgrade_id"));
            setAmount(rs.getFloat("e_us_amount"));

        }});

    }
}

