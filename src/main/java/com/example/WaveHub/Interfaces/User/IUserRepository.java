package com.example.WaveHub.Interfaces.User;

import com.example.WaveHub.Models.User;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IUserRepository {

    Optional<User> findByEmail(String email);
}
