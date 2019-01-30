package ua.od.game.service.impl;

import ua.od.game.dto.BuildingDto;
import ua.od.game.repository.dao.BuildingDao;
import ua.od.game.service.BuildingService;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

public class BuildingServiceImpl implements BuildingService {

    @Inject
    public BuildingDao buildingDao;

    @Override
    public List<BuildingDto> getAllBuildingList() {
        final List<BuildingDto> buildings = new ArrayList<>();
        buildingDao.getAllBuildingList().forEach(buildingEntity -> buildings.add(new BuildingDto() {{
            setId(buildingEntity.getId());
            setName(buildingEntity.getName());
            setDescription(buildingEntity.getDescription());
            setDefaultNumber(buildingEntity.getDefaultNumber());
        }}));
        return buildings;
    }
}
