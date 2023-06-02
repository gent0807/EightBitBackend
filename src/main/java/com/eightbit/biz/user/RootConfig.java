package com.eightbit.biz.user;


import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.commons.dbcp.BasicDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;


@Configuration
@ComponentScan(basePackages = {"com.eightbit.biz"})
@PropertySource("classpath:database.properties")
@MapperScan(basePackages = {"com.eightbit.biz.user.persistence", "com.eightbit.biz.board.persistence"})
public class RootConfig {
    @Autowired
    Environment env;

    @Bean
    public DataSource dataSource(){
        BasicDataSource basicDataSource=new BasicDataSource();
        basicDataSource.setDriverClassName(env.getProperty("jdbc.driver"));
        basicDataSource.setUrl(env.getProperty("jdbc.url"));
        basicDataSource.setUsername(env.getProperty("jdbc.username"));
        basicDataSource.setPassword(env.getProperty("jdbc.password"));

        return  basicDataSource;
    }
    @Bean(name="jdbcTemplate")
    public JdbcTemplate jdbcTemplate(){
        JdbcTemplate jdbcTemplate=new JdbcTemplate();
        jdbcTemplate.setDataSource(dataSource());
        return jdbcTemplate;
    }

    @Bean(name="txManager")
    public DataSourceTransactionManager txManager(){
        return new DataSourceTransactionManager(dataSource());
    }
    /*
    @Bean(name="dataSource2")
    public DataSource dataSource2(){
        HikariConfig hikariConfig=new HikariConfig();
        hikariConfig.setDriverClassName(env.getProperty("jdbc.driver"));
        hikariConfig.setJdbcUrl(env.getProperty("jdbc.uel"));
        hikariConfig.setUsername(env.getProperty("jdbc.username"));
        hikariConfig.setPassword(env.getProperty("jdbc.password"));

        HikariDataSource dataSource=new HikariDataSource(hikariConfig);
        return  dataSource;
    }

    @Bean(name="sqlSessonFactory")
    public SqlSessionFactory sqlSessionFactory() throws Exception{
        SqlSessionFactoryBean sqlSessionFactory= new SqlSessionFactoryBean();
        sqlSessionFactory.setDataSource(dataSource2());
        return (SqlSessionFactory) sqlSessionFactory.getObject();
    }
     */
}
