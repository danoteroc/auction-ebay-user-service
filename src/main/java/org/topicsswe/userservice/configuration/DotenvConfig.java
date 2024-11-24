package org.topicsswe.userservice.configuration;

import io.github.cdimascio.dotenv.Dotenv;
import io.github.cdimascio.dotenv.DotenvEntry;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MapPropertySource;

import java.util.Map;
import java.util.stream.Collectors;

public class DotenvConfig implements EnvironmentPostProcessor {

    @Override
    public void postProcessEnvironment(ConfigurableEnvironment environment, SpringApplication application) {
        // Load the .env file
        Dotenv dotenv = Dotenv.load();

        // Convert .env variables to a Map and add to the Spring Environment
        Map<String, Object> envMap = dotenv.entries()
                .stream()
                .collect(Collectors.toMap(
                        DotenvEntry::getKey, DotenvEntry::getValue
                ));

        environment.getPropertySources().addLast(new MapPropertySource("dotenvProperties", envMap));
    }
}

