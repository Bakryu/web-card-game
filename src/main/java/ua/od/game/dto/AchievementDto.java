package ua.od.game.dto;

import java.util.List;

public class AchievementDto {
    private Integer id;
    private String name;
    private String description;
    private List<ResourceSetDto> resourceSetList;
    private List<BuildingSetDto> buildingSetList;
    private List<UpgradeSetDto> upgradeSetList;

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

    public List<ResourceSetDto> getResourceSetList() {
        return resourceSetList;
    }

    public void setResourceSetList(List<ResourceSetDto> resourceSetList) {
        this.resourceSetList = resourceSetList;
    }

    public List<BuildingSetDto> getBuildingSetList() {
        return buildingSetList;
    }

    public void setBuildingSetList(List<BuildingSetDto> buildingSetList) {
        this.buildingSetList = buildingSetList;
    }

    public List<UpgradeSetDto> getUpgradeSetList() {
        return upgradeSetList;
    }

    public void setUpgradeSetList(List<UpgradeSetDto> upgradeSetList) {
        this.upgradeSetList = upgradeSetList;
    }

    @Override
    public String toString() {
        return "AchievementDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", resourceSetList=" + resourceSetList +
                ", buildingSetList=" + buildingSetList +
                ", upgradeSetList=" + upgradeSetList +
                '}';
    }
}
