package com.example.eventuser;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(scanBasePackages = { "com.example.*" })
@ComponentScan({"com.example.*"})
@EntityScan("com.example.*")
@EnableConfigurationProperties
@ConfigurationPropertiesScan("com.example.*")
public class EventUserApplication {

    public static void main(String[] args) {
        SpringApplication.run(EventUserApplication.class, args);

    }

}
