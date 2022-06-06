package edu.school21.sockets.repositories;

import edu.school21.sockets.mappers.UserMapper;
import edu.school21.sockets.models.User;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

public class UsersRepositoryImpl implements UsersRepository<User>{
    private JdbcTemplate jdbcTemplateObject;
    private DataSource dataSource;

    public UsersRepositoryImpl(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplateObject = new JdbcTemplate(dataSource);
    }
     public Optional<User> findByName(String username) {
       Optional<User> result = Optional.empty();
        String SQL = "select * from USERS where username = ?;";
        User user = jdbcTemplateObject.queryForObject(SQL, new UserMapper(), username);
        result = Optional.of(user);
        return result;
    }

    public User findById(Long id) {
        String SQL = "select * from USERS where id = ?;";
        User user = jdbcTemplateObject.queryForObject(SQL, new UserMapper(), id);
        return user;
    }


    public List<User> findAll() {
        String SQL = "select * from USERS;";
        List <User> users = jdbcTemplateObject.query(SQL, new UserMapper());
        return users;
    }


    public void save(User entity) {
        String SQL = "insert into USERS (id, username, password) values (?, ?, ?);";
        jdbcTemplateObject.update(SQL, entity.getId(), entity.getUsername(), entity.getPassword());
    }


    public void update(User entity) {
        String SQL = "update USERS SET username = ?, password = ? WHERE id = ?;";
        jdbcTemplateObject.update(SQL, entity.getUsername(), entity.getPassword(), entity.getId());
    }


    public void delete(Long id) {
        String SQL = "delete from USERS where id = ?;";
        jdbcTemplateObject.update(SQL, id);
    }
}

