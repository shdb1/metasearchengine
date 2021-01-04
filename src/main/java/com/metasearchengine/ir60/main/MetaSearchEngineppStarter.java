package com.metasearchengine.ir60.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author moshadab
 *
 */
@SpringBootApplication
@ComponentScan("com.metasearchengine.ir60")
public class MetaSearchEngineppStarter {
	
	public static void main(String[] args) {
		SpringApplication.run(MetaSearchEngineppStarter.class,args);
	}

}
