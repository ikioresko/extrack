package com.home.extrack.database;

import com.home.extrack.entity.User;
import com.home.extrack.repository.UserRepo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserInfoDB {
    private final UserRepo userRepo;

    public UserInfoDB(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Transactional
    public User save(User entity) {
        return userRepo.save(entity);
    }
}
