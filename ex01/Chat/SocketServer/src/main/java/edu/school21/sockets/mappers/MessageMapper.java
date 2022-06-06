package edu.school21.sockets.mappers;

import edu.school21.sockets.models.Message;
import edu.school21.sockets.models.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MessageMapper implements RowMapper<Message> {

    public Message mapRow(ResultSet rs, int rowNum) throws SQLException {
        Message message = new Message();
        message.setId(rs.getLong("id"));
        message.setSender(rs.getLong("sender"));
        message.setText(rs.getString("text"));
        message.setTime(rs.getTimestamp("time").toLocalDateTime());
        return message;
    }
}
