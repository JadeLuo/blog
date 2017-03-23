package com.example.data;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com"})
public class DemoApplication {

    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(DemoApplication.class, args);
        String[] beanNames = ctx.getBeanDefinitionNames();
        System.out.println("所以beanNames个数：" + beanNames.length);
        for (String bn : beanNames) {
            if (bn.indexOf("org.springframework") == -1)
                System.out.println("*******************************************************" + bn);
        }
    }
}
