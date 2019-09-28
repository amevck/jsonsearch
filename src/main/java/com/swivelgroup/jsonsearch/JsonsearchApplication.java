package com.swivelgroup.jsonsearch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class JsonsearchApplication implements CommandLineRunner {
	@Autowired
	CommandLineApplication commandLineApplication;

	public static void main(String[] args) {
		SpringApplication.run(JsonsearchApplication.class, args);
	}

	@Override
	public void run(String[] args) {
		commandLineApplication.run();
	}
}