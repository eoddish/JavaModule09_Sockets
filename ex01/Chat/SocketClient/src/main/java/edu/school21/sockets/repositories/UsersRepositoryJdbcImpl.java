package edu.school21.sockets.repositories;

import edu.school21.sockets.models.User;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UsersRepositoryJdbcImpl implements UsersRepository<User>{
    private DataSource dataSource;
    private Connection connection;
    private Statement statement;

    UsersRepositoryJdbcImpl(DataSource dataSource) throws Exception {
        this.dataSource = dataSource;
        this.connection = dataSource.getConnection();
        this.statement = connection.createStatement();
    }
    public Optional<User> findByEmail(String email) {
        Optional<User> result = Optional.empty();
        String SQL = "select * from USERS where EMAIL = '" + email + "';";
        try {
            ResultSet resultSet = statement.executeQuery(SQL);
            User user = new User(resultSet.getLong("id"), resultSet.getString("email"));
            result = Optional.of(user);
        } catch (Exception e) {
            System.out.println(e);
        }
        return result;
    }

    public User findById(Long id) {
        User user = null;
        String SQL = "select * from USERS where id = " + id + ";";
        try {
            ResultSet resultSet = statement.executeQuery(SQL);
            resultSet.next();
            user = new User(resultSet.getLong("id"), resultSet.getString("email"));
        } catch (Exception e) {
            System.out.println(e);
        }
        return user;
    }

    public List<User> findAll() {
        List<User> result = new ArrayList<User>();
        String SQL = "select * from USERS;";
        try {
            ResultSet resultSet = statement.executeQuery(SQL);
            while (resultSet.next()) {
                result.add(new User(resultSet.getLong("id"), resultSet.getString("email")));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return result;
    }

    public void save(User entity) {

        String SQL = "insert into USERS (id, email) values (" + entity.getId() + ",'" + entity.getEmail() +  "');";
        try {
            statement.executeUpdate(SQL);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void update(User entity) {
        String SQL = "update USERS SET email = '" + entity.getEmail() + "' WHERE id = " + entity.getId() + ";";
        try {
            statement.executeUpdate(SQL);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void delete(Long id) {
        String SQL = "delete from USERS where id = " + id + ";";
        try {
            statement.executeUpdate(SQL);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
