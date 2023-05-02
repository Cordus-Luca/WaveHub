package com.example.WaveHub;

import com.example.WaveHub.Song.Song;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@SpringBootApplication
public class WaveHubApplication {

	public static void main(String[] args) {
		SpringApplication.run(WaveHubApplication.class, args);
	}


}
