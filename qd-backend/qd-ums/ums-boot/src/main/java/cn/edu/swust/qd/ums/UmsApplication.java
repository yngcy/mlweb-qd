package cn.edu.swust.qd.ums;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author <a href="https://github.com/yngcy">YounGCY</a>
 */
@SpringBootApplication
@EnableDiscoveryClient
public class UmsApplication {
    public static void main(String[] args) {
        SpringApplication.run(UmsApplication.class, args);
    }
}
