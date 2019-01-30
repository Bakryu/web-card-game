package ua.od.game.model;

import java.util.List;

public class AchievementEntity {
    private Integer id;
    private String name;
    private String description;
    private List<ResourceSetEntity> resourceSetList;
    private List<BuildingSetEntity> buildingSetList;
    private List<UpgradeSetEntity> upgradeSetList;

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

    public List<ResourceSetEntity> getResourceSetList() {
        return resourceSetList;
    }

    public void setResourceSetList(List<ResourceSetEntity> resourceSetList) {
        this.resourceSetList = resourceSetList;
    }

    public List<BuildingSetEntity> getBuildingSetList() {
        return buildingSetList;
    }

    public void setBuildingSetList(List<BuildingSetEntity> buildingSetList) {
        this.buildingSetList = buildingSetList;
    }

    public List<UpgradeSetEntity> getUpgradeSetList() {
        return upgradeSetList;
    }

    public void setUpgradeSetList(List<UpgradeSetEntity> upgradeSetList) {
        this.upgradeSetList = upgradeSetList;

    }

    @Override
    public String toString() {
        return "AchievementEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", resourceSetList=" + resourceSetList +
                ", buildingSetList=" + buildingSetList +
                ", upgradeSetList=" + upgradeSetList +
                '}';
    }
}
