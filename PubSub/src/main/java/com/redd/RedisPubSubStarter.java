package com.redd;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;



public class RedisPubSubStarter {
    public static void main(String[] args) {
        new AnnotationConfigApplicationContext( AppConfig.class );
    }
}