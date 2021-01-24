package com.very.configuration;

import com.very.utils.SpringContextUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan(basePackages = "com.very")
@Import(DatabaseConfiguration.class)
public class AppConfiguration {
    @Bean
    SpringContextUtils springContextUtils() {
        return new SpringContextUtils();
    }

}
