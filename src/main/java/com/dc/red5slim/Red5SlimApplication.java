package com.dc.red5slim;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource({"classpath:red5.xml"})
public class Red5SlimApplication {
    
    public static void main(String[] args) {
        System.setProperty("red5.deployment.type","spring-boot");
        SpringApplication.run(Red5SlimApplication.class, args);
        
    }
    
}
