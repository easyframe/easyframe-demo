package io.github.easyframe.demo.eureka;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author: linzhaoming
 */
@SpringBootApplication
@RestController
@EnableEurekaClient
@Slf4j
public class EurekaApplication {

    @Autowired
    private EurekaClient eurekaClient;

    @Autowired
    private DiscoveryClient discoveryClient;

    public static void main(String[] args) {
        SpringApplication.run(EurekaApplication.class, args);
    }

    @GetMapping("/test")
    public String hello() {
        log.info("From /test request");
        return "EasyFrame";
    }

    @GetMapping("/service")
    public String serviceUrl() {
        System.out.println(eurekaClient.getAllKnownRegions());
        System.out.println(eurekaClient.getApplications());
        InstanceInfo instance = eurekaClient.getNextServerFromEureka("easyframe-demo".toUpperCase(), false);
        return instance.getHomePageUrl();
    }

    @GetMapping("/service2")
    public String serviceUrl2() {
        System.out.println("services: " + discoveryClient.getServices());
        List<ServiceInstance> list = discoveryClient.getInstances("easyframe-demo".toUpperCase());
        if (list != null && list.size() > 0) {
            return list.get(0).getUri().toString();
        }
        return null;
    }

}
