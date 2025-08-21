package com.javidanhaj.bytelog.services;

import com.javidanhaj.bytelog.domain.entities.User;

import java.util.UUID;

public interface UserService {

    User getUserByID(UUID id);
}
