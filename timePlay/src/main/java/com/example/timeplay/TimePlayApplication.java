package com.example.timeplay;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
@SpringBootApplication(scanBasePackages = { "com.example.*" })
@EntityScan("com.example.*")
@EnableConfigurationProperties
@ConfigurationPropertiesScan("com.example.*")
public class TimePlayApplication {
    public static void main(String[] args) {
        SpringApplication.run(TimePlayApplication.class, args);
        System.out.print("TimePlayApplication");

    }

}
