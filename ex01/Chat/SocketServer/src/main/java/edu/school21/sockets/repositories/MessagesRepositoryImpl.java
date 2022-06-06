package edu.school21.sockets.repositories;

import edu.school21.sockets.mappers.MessageMapper;
import edu.school21.sockets.mappers.UserMapper;
import edu.school21.sockets.models.Message;
import edu.school21.sockets.models.User;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

public class MessagesRepositoryImpl implements MessagesRepository<Message>{
    private JdbcTemplate jdbcTemplateObject;
    private DataSource dataSource;

    public MessagesRepositoryImpl(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplateObject = new JdbcTemplate(dataSource);
    }
     public Optional<Message> findByText(String text) {
       Optional<Message> result = Optional.empty();
        String SQL = "select * from MESSAGES where messagetext = ?;";
        Message message = jdbcTemplateObject.queryForObject(SQL, new MessageMapper(), text);
        result = Optional.of(message);
        return result;
    }

    public Message findById(Long id) {
        String SQL = "select * from MESSAGES where id = ?;";
        Message message = jdbcTemplateObject.queryForObject(SQL, new MessageMapper(), id);
        return message;
    }


    public List<Message> findAll() {
        String SQL = "select * from MESSAGES;";
        List <Message> messages = jdbcTemplateObject.query(SQL, new MessageMapper());
        return messages;
    }


    public void save(Message entity) {
        String SQL = "insert into MESSAGES (sender, messagetext, time) values (?, ?, ?);";
        jdbcTemplateObject.update(SQL, entity.getSender(), entity.getText(), entity.getTime());
    }


    public void update(Message entity) {
        String SQL = "update MESSAGES SET sender = ?, messagetext = ?, time = ? WHERE id = ?;";
        jdbcTemplateObject.update(SQL, entity.getSender(), entity.getText(), entity.getTime(), entity.getId());
    }


    public void delete(Long id) {
        String SQL = "delete from MESSAGES where id = ?;";
        jdbcTemplateObject.update(SQL, id);
    }
}

