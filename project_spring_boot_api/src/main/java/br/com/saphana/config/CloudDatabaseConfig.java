package br.com.saphana.config;

import com.sap.db.jdbcext.DataSourceSAP;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import org.hibernate.boot.SchemaAutoTooling;
import org.hibernate.cfg.AvailableSettings;
import org.hibernate.dialect.H2Dialect;
import org.hibernate.dialect.SAPDBDialect;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.lookup.JndiDataSourceLookup;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = "mysqlEntityManagerFactory",
        transactionManagerRef = "mysqlTransactionManager",
        basePackages = "br.com.saphana.**")
@PropertySource("classpath:application.properties")
public class CloudDatabaseConfig {

    @Bean(name = "mysqlDataSource")
    public DataSource dataSource() {
        DataSourceSAP dataSource = new DataSourceSAP();
        dataSource.setUrl("jdbc:sap://hxehost:39013/ALCIBARROS?createDatabaseIfNotExist=true");
        dataSource.setUser("ALCIBARROS");
        dataSource.setPassword("f6g4v5AL");
        dataSource.setServerName("hxehost");
        return dataSource;
    }

//    @Bean(name = "mysqlDataSource")
//    public DataSource dataSource() {
//        DataSourceSAP dataSource = new DataSourceSAP();
//        dataSource.setUrl("jdbc:sap://hxehost:39013/DATABASEHANA_HDI_DB");
//        dataSource.setUser("DATABASEHANA_HDI_DB_0SUBUUYORM7TF4UKMB53M0SGF_RT");
//        dataSource.setPassword("Eh36Bx49spVdtFYzTnJ0WlKa0hWakQNb2_9vc8O1awX6hmhDzh59iGG._X67RoL8fAD_NR8SyQDqw86BBDVlnytCm2kwseT5LpfyVbIZjCoX43cYEGDwiNkVozFaYsaL");
//        dataSource.setServerName("hxehost");
//        return dataSource;
//    }
//    @Bean(name = "mysqlDataSource")
//    public DataSource dataSource() {
//        DriverManagerDataSource dataSource = new DriverManagerDataSource();
//        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
//        dataSource.setUrl("jdbc:mysql://localhost:3306/admin_company?useSSL=false&createDatabaseIfNotExist=true&useTimezone=true&serverTimezone=UTC");
//        dataSource.setUsername("root");
//        dataSource.setPassword("root");
//        return dataSource;
//    }
    @Bean(name = "mysqlEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean mysqlEntityManagerFactory(EntityManagerFactoryBuilder builder, @Qualifier("mysqlDataSource") DataSource dataSource) {

        Map<String, String> properties = new HashMap<>();
        properties.put(AvailableSettings.DRIVER, com.sap.db.jdbc.Driver.class.getName());
        properties.put(AvailableSettings.DIALECT, "org.hibernate.dialect.HANAColumnStoreDialect");
        properties.put(AvailableSettings.HBM2DDL_AUTO, "create-drop");
        properties.put(AvailableSettings.SHOW_SQL, "true");
        properties.put(AvailableSettings.FORMAT_SQL, "true");

        LocalContainerEntityManagerFactoryBean build = builder.dataSource(dataSource)
                .packages("br.com.saphana.models")
                .persistenceUnit("sap_hana_PU")
                .properties(properties)
                .build();
        return build;
    }

    @Bean(name = "mysqlTransactionManager")
    public PlatformTransactionManager mysqlTransactionManager(@Qualifier("mysqlEntityManagerFactory") EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }
}
