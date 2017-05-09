/*
 * Copyright 2013-2015 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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
 * @author linzhaoming
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
        log.info("", eurekaClient.getAllKnownRegions());
        log.info("", eurekaClient.getApplications());
        InstanceInfo instance = eurekaClient.getNextServerFromEureka("easyframe-demo".toUpperCase(), false);
        return instance.getHomePageUrl();
    }

    @GetMapping("/service2")
    public String serviceUrl2() {
        log.info("services: ", discoveryClient.getServices());
        List<ServiceInstance> list = discoveryClient.getInstances("easyframe-demo".toUpperCase());
        if (!list.isEmpty()) {
            return list.get(0).getUri().toString();
        }
        return null;
    }

}
