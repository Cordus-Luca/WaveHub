package com.example.WaveHub;

import com.example.WaveHub.ServiceLayer.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
public class WaveHubApplication {
	public static void main(String[] args) {

		ApplicationContext context = SpringApplication.run(WaveHubApplication.class, args);

		StorageService storageService = context.getBean(StorageService.class);

			System.out.println(storageService.getSongFileNames());
	}


}
