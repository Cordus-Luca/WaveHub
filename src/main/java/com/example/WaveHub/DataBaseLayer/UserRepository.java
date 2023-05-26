package com.example.WaveHub.DataBaseLayer;

import com.example.WaveHub.Interfaces.User.IUserRepository;
import com.example.WaveHub.Models.User;

import java.util.Optional;

public class UserRepository implements IUserRepository {

    public Optional<User> findByEmail(String email) {
        return null;
    }
}
