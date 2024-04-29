package cn.edu.swust.qd.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class SuwstGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(SuwstGatewayApplication.class, args);
    }

}
