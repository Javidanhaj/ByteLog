package com.javidanhaj.bytelog.services.impl;

import com.javidanhaj.bytelog.domain.entities.User;
import com.javidanhaj.bytelog.repositories.UserRepository;
import com.javidanhaj.bytelog.services.UserService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public User getUserByID(UUID id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found with id" + id));
    }
}
