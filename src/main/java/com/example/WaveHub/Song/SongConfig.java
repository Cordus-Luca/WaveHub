package com.example.WaveHub.Song;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.List;

@Configuration
public class SongConfig {

    @Bean
    CommandLineRunner commandLineRunner(SongRepository repository) {
        return args -> {

        };
    }
}
