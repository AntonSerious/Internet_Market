package com.anemchenko;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ANemchenkoIMarketApplication {

	public static SessionFactory factory;

	public static void init(){
		factory = new Configuration()
				.configure("configs/hibernate.cfg.xml")
				.buildSessionFactory();
	}

	public static void main(String[] args) {
		SpringApplication.run(ANemchenkoIMarketApplication.class, args);

	}




}
