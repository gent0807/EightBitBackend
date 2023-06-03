package com.eightbit.biz.user;


import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.commons.dbcp.BasicDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
@Import({MailAuthConfiguration.class})
@ComponentScan(basePackages = {"com.eightbit.biz"})
@PropertySource("classpath:database.properties")
@MapperScan(basePackages = {"com.eightbit.biz.user.persistence", "com.eightbit.biz.board.persistence"})
public class RootConfig {
    @Autowired
    Environment env;

    @Autowired
    ApplicationContext applicationContext;

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



    @Bean
    public DataSource hikariDataSource(){
        HikariConfig hikariConfig=new HikariConfig();
        hikariConfig.setDriverClassName(env.getProperty("jdbc.driver"));
        hikariConfig.setJdbcUrl(env.getProperty("jdbc.url"));
        hikariConfig.setUsername(env.getProperty("jdbc.username"));
        hikariConfig.setPassword(env.getProperty("jdbc.password"));

        HikariDataSource dataSource=new HikariDataSource(hikariConfig); //DataSource 객체 생성
        return  dataSource;
    }



    @Bean
    public SqlSessionFactory sqlSessionFactory() throws Exception{
        SqlSessionFactoryBean sqlSessionFactory= new SqlSessionFactoryBean();
        sqlSessionFactory.setDataSource(hikariDataSource());
        sqlSessionFactory.setConfigLocation(applicationContext.getResource("classpath:mybatis-config.xml"));
        sqlSessionFactory.setMapperLocations(applicationContext.getResources("classpath:/mappings/*.xml"));
        return (SqlSessionFactory) sqlSessionFactory.getObject();
    }

    @Bean(name="sqlSessionTemplate")
    public SqlSessionTemplate sqlSessionTemplate() throws Exception
    {
        SqlSessionTemplate sqlSessionTemplate=new SqlSessionTemplate(sqlSessionFactory());
        return sqlSessionTemplate;
    }


}
