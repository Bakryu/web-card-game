package ua.od.game.dto;

import java.util.List;

public class BuildingDto {
    private Integer id;
    private String name;
    private String description;
    private Integer defaultNumber;
    private List<ResourceSetDto> resourceSetList;

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

    public Integer getDefaultNumber() {
        return defaultNumber;
    }

    public void setDefaultNumber(Integer defaultNumber) {
        this.defaultNumber = defaultNumber;
    }

    public List<ResourceSetDto> getResourceSetList() {
        return resourceSetList;
    }

    public void setResourceSetList(List<ResourceSetDto> resourceSetList) {
        this.resourceSetList = resourceSetList;
    }
}
