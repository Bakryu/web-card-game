package ua.od.game.repository.dao.impl;

import ua.od.game.model.RoomEntity;
import ua.od.game.repository.dao.RoomDao;
import ua.od.game.repository.helper.SqlHelper;

import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

public class RoomDaoImpl implements RoomDao {

    private static final String JOIN_ROOM_QUERY_ROOM_QUERY = new StringBuilder()
            .append("UPDATE room ")
            .append("SET user_1_id = CASE WHEN user_1_id IS NULL ")
            .append("THEN ? ")
            .append("ELSE user_1_id END, ")
            .append("user_2_id = CASE WHEN user_1_id IS NOT NULL AND user_2_id IS NULL AND user_1_id <> ? ")
            .append("THEN ? ")
            .append("ELSE user_2_id END ")
            .append("WHERE id = ?")
            .toString();
    private static final String LEAVE_ROOM_QUERY_ROOM_QUERY = new StringBuilder()
            .append("UPDATE room ")
            .append("SET user_1_id = CASE WHEN user_1_id = ? ")
            .append("THEN NULL ")
            .append("ELSE user_1_id END, ")
            .append("user_2_id = CASE WHEN user_2_id = ? ")
            .append("THEN NULL ")
            .append("ELSE user_2_id END ")
            .append("WHERE user_1_id = ? OR user_2_id = ?")
            .toString();
    private static final String GET_ROOM_LIST_QUERY = "SELECT * FROM room";
    private static final String CHECK_ROOM_IS_FULL_QUERY = "SELECT account_1_id, account_2_id FROM room WHERE id = ?";
    private static final String GET_USER_NAME_BY_ID = "SELECT account.name FROM room JOIN account ON room.account_?_id = account.id WHERE room.id = ?";
    private static final String CHECK_FOR_EXISTENCE_OF_USER = "SELECT id FROM user WHERE id = ?";
    private static final String CHECK_FOR_EXISTENCE_OF_ROOM = "SELECT id FROM room WHERE id = ?";
    private static final String USER_IN_THE_ROOM = "SELECT account_1_id, account_2_id FROM room WHERE account_1_id = ? OR user_2_id = ?";

    public List<RoomEntity> getRoomList() {
        return SqlHelper.prepareStatement(GET_ROOM_LIST_QUERY, statementForRoomList -> {
            ResultSet roomsResultSet = statementForRoomList.executeQuery();
            List<RoomEntity> rooms = new LinkedList<>();
            while (roomsResultSet.next()) {
                rooms.add(new RoomEntity() {{
                    setId(roomsResultSet.getInt("id"));
                    setName(roomsResultSet.getString("name"));
                    setDescription(roomsResultSet.getString("description"));
                }});
            }
            return rooms;
        });
    }

    public Boolean joinRoom(Integer roomId, Integer accountId) {
        if (!(checkForExistenceOfUser(accountId) && checkForExistenceOfRoom(roomId)))
            return false;
        else return SqlHelper.prepareStatement(CHECK_ROOM_IS_FULL_QUERY, statement -> {
            statement.setInt(1, roomId);
            ResultSet resultSetFullRoomCheck = statement.executeQuery();
            resultSetFullRoomCheck.next();
            if (resultSetFullRoomCheck.getInt("user_1_id") > 0 &&
                    resultSetFullRoomCheck.getInt("user_2_id") > 0) {
                return false;
            }
            else {
                statement = statement.getConnection().prepareStatement(JOIN_ROOM_QUERY_ROOM_QUERY);
                statement.setInt(1, accountId);
                statement.setInt(2, accountId);
                statement.setInt(3, accountId);
                statement.setInt(4, roomId);
                if (isUserInTheRoom(accountId)) {
                    leaveRoom(accountId);
                }
                return statement.executeUpdate()> 0 ? true : false;
            }
        });
    }

    public Boolean leaveRoom(Integer accountId) {
        if (!checkForExistenceOfUser(accountId))
            return false;
        return SqlHelper.prepareStatement(LEAVE_ROOM_QUERY_ROOM_QUERY, statementForRoom -> {
            statementForRoom.setInt(1, accountId);
            statementForRoom.setInt(2, accountId);
            statementForRoom.setInt(3, accountId);
            statementForRoom.setInt(4, accountId);
            return statementForRoom.executeUpdate() > 0 ? true : false;
        });
    }

    private boolean checkForExistenceOfRoom(Integer id) {
        return SqlHelper.prepareStatement(CHECK_FOR_EXISTENCE_OF_ROOM, statementCheckForExistence -> {
            statementCheckForExistence.setInt(1, id);
            ResultSet resultSetExistence = statementCheckForExistence.executeQuery();
            return resultSetExistence.next();
        });
    }

    private boolean checkForExistenceOfUser(Integer id) {
        return SqlHelper.prepareStatement(CHECK_FOR_EXISTENCE_OF_USER, statementCheckForExistence -> {
            statementCheckForExistence.setInt(1, id);
            ResultSet resultSetExistence = statementCheckForExistence.executeQuery();
            return resultSetExistence.next();
        });
    }

    private boolean isUserInTheRoom(Integer id) {
        return SqlHelper.prepareStatement(USER_IN_THE_ROOM, statementCheckForUserInTheRoom -> {
            statementCheckForUserInTheRoom.setInt(1, id);
            statementCheckForUserInTheRoom.setInt(2, id);
            ResultSet resultSetExistence = statementCheckForUserInTheRoom.executeQuery();
            return resultSetExistence.next();
        });
    }

    private String getUserNameById(Integer userNumber, Integer roomId) {
        return SqlHelper.prepareStatement(GET_USER_NAME_BY_ID, statementForNameById -> {
            statementForNameById.setInt(1, userNumber);
            statementForNameById.setInt(2, roomId);
            ResultSet resultSet = statementForNameById.executeQuery();
            resultSet.next();
            return resultSet.getString("name");
        });
    }
}
