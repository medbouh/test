package com.a2a.api.microservices;

import org.springframework.stereotype.Component;

@Component
public class DataSourceProperties {

    private String drive = "jdbc:mysql://localhost:3306/oAuth2?serverTimezone=UTC";
    private String url ="root";
    private String username="" ;
    private String password ="";


    public String getDrive() {
        return drive;
    }

    public void setDrive(String drive) {
        this.drive = drive;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
