package ua.od.game.model;

import java.util.List;

public class CardEntity {
    private Integer id;
    private String name;
    private String description;
    private CardGroupEntity cardGroup;

    private List<ResourceSetEntity> palayerResourceSetList;
    private List<BuildingSetEntity> palayerBuildingSetList;
    private List<UpgradeSetEntity> palayerUpgradeSetList;

    private List<ResourceSetEntity> enemyResourceSetList;
    private List<BuildingSetEntity> enemyBuildingSetList;
    private List<UpgradeSetEntity> enemyUpgradeSetList;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public CardGroupEntity getCardGroup() {
        return cardGroup;
    }

    public void setCardGroup(CardGroupEntity cardGroup) {
        this.cardGroup = cardGroup;
    }

    public List<ResourceSetEntity> getPalayerResourceSetList() {
        return palayerResourceSetList;
    }

    public void setPalayerResourceSetList(List<ResourceSetEntity> palayerResourceSetList) {
        this.palayerResourceSetList = palayerResourceSetList;
    }

    public List<BuildingSetEntity> getPalayerBuildingSetList() {
        return palayerBuildingSetList;
    }

    public void setPalayerBuildingSetList(List<BuildingSetEntity> palayerBuildingSetList) {
        this.palayerBuildingSetList = palayerBuildingSetList;
    }

    public List<UpgradeSetEntity> getPalayerUpgradeSetList() {
        return palayerUpgradeSetList;
    }

    public void setPalayerUpgradeSetList(List<UpgradeSetEntity> palayerUpgradeSetList) {
        this.palayerUpgradeSetList = palayerUpgradeSetList;
    }

    public List<ResourceSetEntity> getEnemyResourceSetList() {
        return enemyResourceSetList;
    }

    public void setEnemyResourceSetList(List<ResourceSetEntity> enemyResourceSetList) {
        this.enemyResourceSetList = enemyResourceSetList;
    }

    public List<BuildingSetEntity> getEnemyBuildingSetList() {
        return enemyBuildingSetList;
    }

    public void setEnemyBuildingSetList(List<BuildingSetEntity> enemyBuildingSetList) {
        this.enemyBuildingSetList = enemyBuildingSetList;
    }

    public List<UpgradeSetEntity> getEnemyUpgradeSetList() {
        return enemyUpgradeSetList;
    }

    public void setEnemyUpgradeSetList(List<UpgradeSetEntity> enemyUpgradeSetList) {
        this.enemyUpgradeSetList = enemyUpgradeSetList;
    }


}
