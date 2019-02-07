package ua.od.game.model;

import java.util.ArrayList;
import java.util.List;

public class NotificationEntity {
    private Integer id;
    private String name;
    private String description;
    private Integer showFromScratch;
    private List<ResourceSetEntity> resourceSetList = new ArrayList<>();
    private List<BuildingSetEntity> buildingSetList = new ArrayList<>();
    private List<UpgradeSetEntity> upgradeSetList   = new ArrayList<>();

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

    public Integer getShowFromScratch() {
        return showFromScratch;
    }

    public void setShowFromScratch(Integer showFromScratch) {
        this.showFromScratch = showFromScratch;
    }

    public List<ResourceSetEntity> getResourceSetList() {
        return resourceSetList;
    }

    public List<BuildingSetEntity> getBuildingSetList() {
        return buildingSetList;
    }

    public List<UpgradeSetEntity> getUpgradeSetList() {
        return upgradeSetList;
    }

    @Override
    public String toString() {
        return "\nNotificationEntity{" +
                "  \n\tid=" + id +
                ", \n\tname='" + name + '\'' +
                ", \n\tdescription='" + description + '\'' +
                ", \n\tshowFromScratch=" + showFromScratch +
                ", \n\tresourceSetList=" + resourceSetList +
                ", \n\tbuildingSetList=" + buildingSetList +
                ", \n\tupgradeSetList=" + upgradeSetList +
                '}';
    }

}
