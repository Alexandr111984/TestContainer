package ru.netology.TestContainer.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import ru.netology.TestContainer.profile.DevProfile;
import ru.netology.TestContainer.profile.ProductionProfile;
import ru.netology.TestContainer.profile.SystemProfile;


@Configuration

public class JavaConfig {
    @Bean
    @ConditionalOnProperty(value = "ru.netology.TestContainer.profile.dev", havingValue = "true", matchIfMissing = true)
    public SystemProfile devProfile() {
        return new DevProfile();
    }

    @Bean
    @ConditionalOnProperty(value = "ru.netology.TestContainer.profile.dev", havingValue = "true")
    public SystemProfile prodProfile() {

        return new ProductionProfile();
    }
}
