package ua.od.game.controller.impl;

import ua.od.game.controller.BuildingController;
import ua.od.game.dto.BuildingDto;
import ua.od.game.service.BuildingService;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Path("/building")
public class BuildingControllerImpl implements BuildingController {

    @Inject
    public BuildingService buildingService;

    @GET
    @Path("list")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public List<BuildingDto> getAllBuildingList() {
        List<BuildingDto> buildingList = buildingService.getAllBuildingList();
        Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, buildingList.toString());
        return buildingList;
    }
}
