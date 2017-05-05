package io.github.easyframe.demo.stand;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * Demo Server: default url is http://localhost:28080
 *
 * @author linzhaoming
 */
@SpringBootApplication
@RestController
@Slf4j
@EnableEurekaClient
public class DemoApplication {

    @Autowired
    private Environment environment;

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @GetMapping("/test")
    public String test() {
        log.info("From /test request: " + new Date());
        return "EasyFrame-22 \n";
    }

    @GetMapping("/test1")
    public String test1() {
        return "test12: " + new Date();
    }

    @GetMapping("/test2")
    public String test2() {
        return "test2: " + new Date();
    }

    @GetMapping("/test3")
    public String test3() {
        return "test3: linzm=" + environment.getProperty("linzm");
    }

}
