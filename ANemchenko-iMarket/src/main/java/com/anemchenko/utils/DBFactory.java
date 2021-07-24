package com.anemchenko.utils;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Component
public class DBFactory{
    private SessionFactory factory;

    public DBFactory() {
        factory = new Configuration()
                .configure("configs/hibernate.cfg.xml")
                .buildSessionFactory();
    }

    @PostConstruct
    public void init(){
    }

    @PreDestroy
    public void shutdown(){
        factory.close();
    }

    public SessionFactory getFactory() {
        return factory;
    }
}
