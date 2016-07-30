package com.bartoszjd;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * Created by bartoszjedrzejewski on 30/07/2016.
 */
@Configuration
public class GithubConfig {

    @Value("${github.username}")
    private String username;
    @Value("${github.password}")
    private String password;

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
