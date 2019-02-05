package ua.od.game.config;

import ua.od.game.controller.*;
import ua.od.game.controller.impl.*;
import ua.od.game.repository.dao.*;
import ua.od.game.repository.dao.impl.*;
import ua.od.game.service.*;
import ua.od.game.service.impl.*;

import ua.od.game.controller.AccountAchievementController;
import ua.od.game.controller.AccountRoomController;
import ua.od.game.controller.RoomController;
import ua.od.game.controller.UserController;
import ua.od.game.controller.impl.AccountAchievementControllerImpl;
import ua.od.game.controller.impl.AccountRoomControllerImpl;
import ua.od.game.controller.impl.RoomControllerImpl;
import ua.od.game.controller.impl.UserControllerImpl;

import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.FilterHolder;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.servlets.CrossOriginFilter;
import org.eclipse.jetty.util.resource.Resource;
import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.servlet.ServletContainer;

import javax.servlet.DispatcherType;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.EnumSet;

public class AppContextConfig {
    public HandlerList getHandlersConfig() {
        HandlerList handlers = new HandlerList();
        handlers.addHandler(getWebResourceHandler());
        handlers.addHandler(getServletHandler());
        return handlers;
    }

    private Handler getServletHandler() {
        ServletContextHandler servletsHandler = new ServletContextHandler(ServletContextHandler.NO_SESSIONS);
        servletsHandler.setContextPath("/");
        servletsHandler.addServlet(new ServletHolder(new ServletContainer(getResourceConfig())), "/rest/*");
        FilterHolder holder = new FilterHolder(new CrossOriginFilter());
        holder.setInitParameter(CrossOriginFilter.ALLOWED_METHODS_PARAM, "GET,POST,HEAD,OPTIONS");
        servletsHandler.addFilter(holder, "/rest/*", EnumSet.of(DispatcherType.REQUEST));
        return servletsHandler;
    }

    private Handler getWebResourceHandler() {
        ResourceHandler resourceHandler = new ResourceHandler() {
            public void handle(String target, Request baseRequest, HttpServletRequest request,
                               HttpServletResponse response) throws IOException, ServletException {
                if(target.equals("/rooms.html")
                        || target.equals("/achievements.html")
                        || target.equals("/gameplay.html")) {
                    Boolean flag = true;
                    for (Cookie cookie : request.getCookies()) {
                        if ("token".equals(cookie.getName())) {
                            flag = false;
                            if (cookie.getValue() == null || cookie.getValue().equals("")) {
                                flag = true;
                            }
                            break;
                        }
                    }
                    if (flag) {
                        response.sendRedirect("/login.html");
                    }
                }
                super.handle(target, baseRequest, request, response);
            }
        };
        resourceHandler.setWelcomeFiles(new String[]{"login.html"});
        resourceHandler.setBaseResource(Resource.newClassPathResource("/webapp"));
        return resourceHandler;
    }

    private ResourceConfig getResourceConfig() {
        return new ResourceConfig() {{
            packages("ua/od/game");
            register(new AbstractBinder() {
                protected void configure () {
                    bindAsContract(AccountRoomControllerImpl.class).to(AccountRoomController.class);

                    bindAsContract(RoomDaoImpl.class).to(RoomDao.class);
                    bindAsContract(RoomServiceImpl.class).to(RoomService.class);
                    bindAsContract(RoomControllerImpl.class).to(RoomController.class);

                    bindAsContract(UserDaoImpl.class).to(UserDao.class);
                    bindAsContract(UserServiceImpl.class).to(UserService.class);
                    bindAsContract(UserControllerImpl.class).to(UserController.class);

                    bindAsContract(AccountAchievementDaoImpl.class).to(AccountAchievementDao.class);
                    bindAsContract(AccountAchievementServiceImpl.class).to(AccountAchievementService.class);
                    bindAsContract(AccountAchievementControllerImpl.class).to(AccountAchievementController.class);

                    bindAsContract(AchievementDaoImpl.class).to(AchievementDao.class);
                    bindAsContract(AchievementServiceImpl.class).to(AchievementService.class);
                    bindAsContract(AchievementControllerImpl.class).to(AchievementController.class);

                    bindAsContract(ResourceDaoImpl.class).to(ResourceDao.class);
                    bindAsContract(ResourceServiceImpl.class).to(ResourceService.class);
                    bindAsContract(ResourceControllerImpl.class).to(ResourceController.class);

                    bindAsContract(BuildingDaoImpl.class).to(BuildingDao.class);
                    bindAsContract(BuildingServiceImpl.class).to(BuildingService.class);
                    bindAsContract(BuildingControllerImpl.class).to(BuildingController.class);

                    bindAsContract(MessageDaoImpl.class).to(MessageDao.class);
                    bindAsContract(MessageServiceImpl.class).to(MessageService.class);
                    bindAsContract(MessageControllerImpl.class).to(MessageController.class);
                }
            });
        }};
    }
}