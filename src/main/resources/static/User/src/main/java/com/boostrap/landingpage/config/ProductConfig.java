package com.boostrap.landingpage.config;
import org.springframework.context.annotation.Bean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
public class ProductConfig {

    @Autowired
    Environment environment;

//    public void getDataSourceManager(){
//        DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
//        environment.s
//    }
}
