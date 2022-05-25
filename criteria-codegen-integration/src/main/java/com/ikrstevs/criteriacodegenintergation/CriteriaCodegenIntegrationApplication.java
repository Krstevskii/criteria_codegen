package com.ikrstevs.criteriacodegenintergation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "com.ikrstevs")
public class CriteriaCodegenIntegrationApplication {

  public static void main(String[] args) {
    SpringApplication.run(CriteriaCodegenIntegrationApplication.class, args);
  }

}
