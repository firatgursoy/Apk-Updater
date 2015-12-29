package com.gnosis.apkupdater;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Created by F�rat on 18.11.2015.
 */
@ComponentScan(basePackages = {"com.gnosis.apkupdater"})
@Configuration
@EnableAutoConfiguration
public class Bootstrap {
    public static void main(String[] args) throws Exception {
        SpringApplication.run(Bootstrap.class, args);
    }
}
