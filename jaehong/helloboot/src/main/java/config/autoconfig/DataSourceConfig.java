package config.autoconfig;

import com.zaxxer.hikari.HikariDataSource;
import config.ConditionalMyOnClass;
import config.EnableMyConfigurationProperties;
import config.MyAutoConfiguration;
import java.sql.Driver;
import javax.sql.DataSource;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

@MyAutoConfiguration
@ConditionalMyOnClass("org.springframework.jdbc.core.JdbcOperations")
@EnableMyConfigurationProperties(value = MyDataSourceProperties.class)
public class DataSourceConfig {
    @Bean
    @ConditionalOnMissingBean
    DataSource dataSource(MyDataSourceProperties properties) throws ClassNotFoundException {
        SimpleDriverDataSource dataSource = new SimpleDriverDataSource();
        dataSource.setDriverClass((Class<? extends Driver>) Class.forName(properties.getDriverClassName()));
        dataSource.setUrl(properties.getUrl());
        dataSource.setPassword(properties.getPassword());
        dataSource.setUsername(properties.getUsername());

        return dataSource;
    }

    @Bean
    @ConditionalMyOnClass("com.zaxxer.hikari.HikariDataSource")
    DataSource hikariDataSource(MyDataSourceProperties properties) {
        var dataSource = new HikariDataSource();

        dataSource.setDriverClassName(properties.getDriverClassName());
        dataSource.setJdbcUrl(properties.getUrl());
        dataSource.setPassword(properties.getPassword());
        dataSource.setUsername(properties.getUsername());
        return dataSource;
    }
}
