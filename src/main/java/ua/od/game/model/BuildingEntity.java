package ua.od.game.model;

import java.util.List;

public class BuildingEntity {
    private Integer id;
    private String name;
    private String description;
    private Integer defaultNumber;
    private List<ResourceSetEntity> resourceSetList;

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

    public Integer getDefaultNumber() {
        return defaultNumber;
    }

    public void setDefaultNumber(Integer defaultNumber) {
        this.defaultNumber = defaultNumber;
    }

    public List<ResourceSetEntity> getResourceSetList() {
        return resourceSetList;
    }

    public void setResourceSetList(List<ResourceSetEntity> resourceSetList) {
        this.resourceSetList = resourceSetList;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
