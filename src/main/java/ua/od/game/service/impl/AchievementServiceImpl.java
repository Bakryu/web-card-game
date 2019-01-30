package ua.od.game.service.impl;

import ua.od.game.dto.AchievementDto;
import ua.od.game.repository.dao.AchievementDao;
import ua.od.game.service.AchievementService;

import javax.inject.Inject;
import java.util.LinkedList;
import java.util.List;

public class AchievementServiceImpl implements AchievementService {

    @Inject
    private AchievementDao achievementDao;
    @Override
    public List<AchievementDto> getAllAchievementList() {
        List<AchievementDto> achievementDtos = new LinkedList<>();
        achievementDao.getAllAchievementList().forEach(achievementEntity -> achievementDtos.add(new AchievementDto() {{
            setId(achievementEntity.getId());
            setName(achievementEntity.getName());
            setDescription(achievementEntity.getDescription());
        }}));
        return achievementDtos;
    }
}
