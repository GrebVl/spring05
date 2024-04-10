package com.gb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.gb.lesson08.SecondBean;


@SpringBootApplication
public class SpringBootLesson05Application {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(SpringBootLesson05Application.class, args);

		context.getBean(SecondBean.class).postConstruct();
	}

}
