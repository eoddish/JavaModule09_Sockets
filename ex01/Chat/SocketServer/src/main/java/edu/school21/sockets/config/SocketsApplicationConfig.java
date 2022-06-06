package edu.school21.sockets.config;


import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import edu.school21.sockets.models.User;
import edu.school21.sockets.repositories.MessagesRepository;
import edu.school21.sockets.repositories.MessagesRepositoryImpl;
import edu.school21.sockets.repositories.UsersRepository;
import edu.school21.sockets.repositories.UsersRepositoryImpl;
import edu.school21.sockets.services.UsersService;
import edu.school21.sockets.services.UsersServiceImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.*;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;
import java.io.IOException;
import java.lang.annotation.Target;

@Configuration
@PropertySource("classpath:db.properties")
public class SocketsApplicationConfig {

    @Value("${db.driver.name}")
    private  String driverName;
    @Value("${db.url}")
    private  String url;
    @Value("${db.user}")
    private  String user;
    @Value("${db.password}")
    private  String password;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(10);
    }
    @Bean
    public UsersService usersService() {
        return new UsersServiceImpl(passwordEncoder());
    }

    @Bean
    public UsersRepository usersRepository(){
        return new UsersRepositoryImpl(hikariDataSource());
    }

    @Bean
    public MessagesRepository messagesRepository(){
        return new MessagesRepositoryImpl(hikariDataSource());
    }

    @Bean
    public DataSource hikariDataSource() {
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setDriverClassName(driverName);
        hikariConfig.setJdbcUrl(url);
        hikariConfig.setUsername(user);
        hikariConfig.setPassword(password);
        HikariDataSource dataSource = new HikariDataSource(hikariConfig);
        return dataSource;
    }
}
