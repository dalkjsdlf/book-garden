package io.ratel.bookgarden.common.persistence.config.datasource;

import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@Configuration
@EnableJpaRepositories(
        basePackages = "io.ratel.bookgarden.domain",
        entityManagerFactoryRef = "bookGardenEntityManagerFactory",
        transactionManagerRef = "bookGardenTransactionManager"
)
public class BookGardenDbConfig {

    @Bean(name = "bookGardenDataSource")
    @ConfigurationProperties(prefix = "spring.datasource-msqlbgd")
    public DataSource bookGardenDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "bookGardenEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean bookGardenEntityManagerFactory(
            EntityManagerFactoryBuilder builder, @Qualifier("bookGardenDataSource") DataSource dataSource) {
        return builder
                .dataSource(dataSource)
                .packages("io.ratel.bookgarden.domain")  // BookGarden 엔티티 클래스 패키지
                .persistenceUnit("bookGardenPersistenceUnit")
                .build();
    }

    @Bean(name = "bookGardenTransactionManager")
    public PlatformTransactionManager bookGardenTransactionManager(
            @Qualifier("bookGardenEntityManagerFactory") EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }
}

