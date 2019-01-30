package ua.od.game.controller.impl;

import ua.od.game.controller.AchievementController;
import ua.od.game.dto.AchievementDto;
import ua.od.game.service.AchievementService;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import java.util.List;

@Path("/account")
public class AchievementControllerImpl implements AchievementController {

    @Inject
    private AchievementService achievementService;

    @GET
    @Path("/achievement/list")
    public List<AchievementDto> getAllAchievementList() {
        return achievementService.getAllAchievementList();
    }
}
