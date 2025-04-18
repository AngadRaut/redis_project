package com.redis.redis_practice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.redis.domain.geo.RadiusShape;

@SpringBootApplication
@EnableCaching
public class RedisProjectApplication {
	private static final Logger logger = LoggerFactory.getLogger(RadiusShape.class);

	@Autowired
	private ApplicationContext context;

	public static void main(String[] args) {
		SpringApplication.run(RedisProjectApplication.class, args);
		logger.info("Application Started!!!!!");

	/*	MyAnnotationDemo demo=new MyAnnotationDemo();
		Class aClass = demo.getClass();
		MyAnnotation a = (MyAnnotation) aClass.getAnnotation(MyAnnotation.class);
		System.out.println(a.city());
		System.out.println(a.name());
		System.out.println(a.value()w);*/
	}
}