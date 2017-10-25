package com.robert.spring.jpa.rest.config;

import org.dozer.spring.DozerBeanMapperFactoryBean;
import org.h2.tools.Server;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import java.io.IOException;
import java.sql.SQLException;

/**
 * @author Roberto Noriega
 * @version 1.0.0
 * @since 20/10/17
 **/
@Configuration
public class AppConfig {

    @Bean(name = "dozerBean")
    public DozerBeanMapperFactoryBean configDozer() throws IOException {
        DozerBeanMapperFactoryBean mapper = new DozerBeanMapperFactoryBean();
        Resource[] resources = new PathMatchingResourcePatternResolver().getResources("classpath*:/dozer/bean-mappings.xml");
        mapper.setMappingFiles(resources);

        return mapper;
    }

    /**
     * TCP connection to connect with SQL clients to the embedded h2 database.
     * <p>
     * Connect to "jdbc:h2:tcp://localhost:9092/mem:${spring.datasource.name}", username: ${spring.datasource.username},
     * password: ${spring.datasource.password}.
     */
    @Bean
    @Profile({"embedded"})
    @ConditionalOnExpression("${h2.tcp.enabled:true}")
    public Server h2TcpServer() throws SQLException {
        return Server.createTcpServer("-tcp", "-tcpAllowOthers", "-tcpPort", "9092").start();
    }

    /**
     * Web console for the embedded h2 database.
     * <p>
     * Go to http://localhost:8082 and connect to the database
     * "jdbc:h2:mem:${spring.datasource.name}", username: ${spring.datasource.username}, password: ${spring.datasource.password}.
     */
    @Bean
    @Profile({"embedded"})
    @ConditionalOnExpression("${h2.web.enabled:true}")
    public Server h2WebServer() throws SQLException {
        return Server.createWebServer("-web", "-webAllowOthers", "-webPort", "8082").start();
    }

}
