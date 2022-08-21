package com.cuadratura.app.config;


import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean; 
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import com.zaxxer.hikari.HikariDataSource; 

@Configuration 
public class DataSourceConfig   {
	
	@Bean
	@Primary
	@ConfigurationProperties (prefix= "oracle.datasource")
	DataSource dsOne (){
		 return DruidDataSourceBuilder.create().build();
	}
	
	@Bean
	@ConfigurationProperties (prefix= "mysql.datasource")
	DataSource dsTwo (){
		 return DruidDataSourceBuilder.create().build();
	}
	
}
