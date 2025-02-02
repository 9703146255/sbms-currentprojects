package in.thiru.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import jakarta.persistence.EntityManagerFactory;


@Profile("h2")
@Configuration
@EnableJpaRepositories(
    basePackages = "in.thiru.dao",
    entityManagerFactoryRef = "h2EntityManager",
    transactionManagerRef = "h2TransactionManager"
)

public class H2DBConfig {
	
	 @Bean
	    @ConfigurationProperties(prefix = "spring.datasource.h2")
	    public DataSource h2DataSource() {
	        return DataSourceBuilder.create().build();
	    }

	    @Bean
	    public LocalContainerEntityManagerFactoryBean h2EntityManager(
	            EntityManagerFactoryBuilder builder) {
	        return builder
	                .dataSource(h2DataSource())
	                .packages("in.thiru.entity")
	                .persistenceUnit("h2")
	                .build();
	    }

	    @Bean
	    public PlatformTransactionManager h2TransactionManager(
	            @Qualifier("h2EntityManager") EntityManagerFactory entityManagerFactory) {
	        return new JpaTransactionManager(entityManagerFactory);
	    }

}
