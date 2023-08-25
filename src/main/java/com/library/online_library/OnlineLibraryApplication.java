package com.library.online_library;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages="com.library.online_library.repository")
public class OnlineLibraryApplication {

	public static void main(String[] args) {
		SpringApplication.run(OnlineLibraryApplication.class, args);
	}

}
