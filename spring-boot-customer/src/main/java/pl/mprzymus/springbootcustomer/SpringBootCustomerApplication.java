package pl.mprzymus.springbootcustomer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
public class SpringBootCustomerApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootCustomerApplication.class, args);
    }

}
