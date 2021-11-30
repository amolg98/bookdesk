package com.amolgupta.bookdesk;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

/**
 * Created by agupta on 09/26/2021
 * JPA Configuration Class for creating access to #database type
 * Database using Hibernate
 */

 @Configuration
 @EnableTransactionManagement
public class JPAConfig {
    
    @Bean(name = "entityManagerFactory")
    public LocalContainerEntityManagerFactoryBean vmf() {
        LocalContainerEntityManagerFactoryBean vmf = new LocalContainerEntityManagerFactoryBean();
        vmf.setDataSource(getDataSource());
        vmf.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        vmf.setPackagesToScan("com.amolgupta.bookdesk");

        Properties properties = new Properties();
        properties.put("hibernate.dialect", "com.amolgupta.bookdesk.SQLDialect.SQLiteDialect");
        properties.put("hibernate.hbm2ddl.auto", "update");
        properties.put("hibernate.show_sql", "true");

        vmf.setJpaProperties(properties);
        return vmf;

    }

    @Bean
    public DataSource getDataSource() {
        DriverManagerDataSource ds = new DriverManagerDataSource();
        ds.setDriverClassName("org.sqlite.JDBC");
        // ds.setUrl("jdbc:sqlite:memory:myDb?cache=shared"); // only for in-memory database
        ds.setUrl("jdbc:sqlite:/home/agupta/BookDesk.db");
        // Seems like being lightweight sqlite3 doesn't support authentication out of box, well we don't need it as well as we are going to use bamboohr/google authentication for making any such requests
        ds.setUsername("sa");
        ds.setPassword("sa");
        return ds;
    }

    @Bean(name="transactionManager")
    public PlatformTransactionManager toManager(EntityManagerFactory emf) {
        JpaTransactionManager txm = new JpaTransactionManager(emf);
        txm.setEntityManagerFactory(emf);
        return txm;
    }

}
