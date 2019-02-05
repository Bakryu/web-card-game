package ua.od.game.controller.impl;

import ua.od.game.controller.MessageController;
import ua.od.game.dto.MessageDto;
import ua.od.game.service.MessageService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Path("/account/message")
public class MessageControllerImpl implements MessageController {

    @Inject
    private MessageService messageService;

    @GET
    @Path("/time/{fromTime}/player/{fromAccountId}/enemy/{toAccountId}/list")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public List<MessageDto> getMessageList(@PathParam("fromAccountId")Integer fromAccountId,@PathParam("toAccountId") Integer toAccountId,@PathParam("fromTime") Date fromTime) {
        List<MessageDto> messageList = messageService.getMessageList(fromAccountId,toAccountId,fromTime);
        Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, messageList.toString());
        return messageList;
    }

    @POST
    @Path("/send")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response sendMessage(MessageDto message) {
        messageService.sendMessage(message);
        Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, message.toString());
        return Response.ok().build();
    }
}
