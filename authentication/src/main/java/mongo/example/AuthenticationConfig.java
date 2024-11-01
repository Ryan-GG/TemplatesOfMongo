package mongo.example;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "spring.data.mongodb")
public class AuthenticationConfig {

    private String username;
    private String password;
    private String database;
    private String authenticationDatabase;
    private String port;
    private String host;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username){
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public String getDatabase() {
        return database;
    }

    public void setDatabase(String database){
        this.database = database;
    }

    public String getAuthenticationDatabase(){
        return authenticationDatabase;
    }

    public void setAuthenticationDatabase(String authenticationDatabase){
        this.authenticationDatabase = authenticationDatabase;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host){
        this.host = host;
    }

    public String getPort(){
        return port;
    }

    public void setPort(String port)
    {
        this.port = port;
    }
}

