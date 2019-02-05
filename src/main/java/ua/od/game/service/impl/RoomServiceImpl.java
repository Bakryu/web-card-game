package ua.od.game.service.impl;

import ua.od.game.dto.RoomDto;
import ua.od.game.repository.dao.RoomDao;
import ua.od.game.service.RoomService;

import javax.inject.Inject;
import java.util.LinkedList;
import java.util.List;

public class RoomServiceImpl implements RoomService {

    @Inject
    public RoomDao roomDao;

    public List<RoomDto> getRoomList() {
        final List<RoomDto> rooms = new LinkedList<>();
        roomDao.getRoomList().forEach(roomEntity -> rooms.add(new RoomDto() {{
            setId(roomEntity.getId());
            setName(roomEntity.getName());
            setDescription(roomEntity.getDescription());
        }}));
        return rooms;
    }


    public void joinRoom(Integer roomId, Integer userId) {
        roomDao.joinRoom(roomId, userId);
    }

    public void leaveRoom(Integer userId) {
        roomDao.leaveRoom(userId);
    }
}
