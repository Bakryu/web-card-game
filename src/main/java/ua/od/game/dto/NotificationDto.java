package ua.od.game.dto;

import java.util.List;

public class NotificationDto {
    private Integer id;
    private String name;
    private String description;
    private Integer showFromScratch;
    private List<ResourceSetDto> resourceSetList;
    private List<BuildingSetDto> buildingSetList;
    private List<UpgradeSetDto> upgradeSetList;
}
