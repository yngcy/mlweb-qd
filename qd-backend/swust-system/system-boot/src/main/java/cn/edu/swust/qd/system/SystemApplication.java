package cn.edu.swust.qd.system;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author <a href="https://github.com/yngcy">YounGCY</a>
 */
@EnableDiscoveryClient
@SpringBootApplication(scanBasePackages = "cn.edu.swust.qd")
public class SystemApplication {
    public static void main(String[] args) {
        SpringApplication.run(SystemApplication.class, args);
    }
}
