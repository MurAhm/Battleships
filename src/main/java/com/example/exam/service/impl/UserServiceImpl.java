package com.example.exam.service.impl;

import com.example.exam.model.entity.User;
import com.example.exam.model.service.UserServiceModel;
import com.example.exam.repository.UserRepository;
import com.example.exam.sec.CurrentUser;
import com.example.exam.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final CurrentUser currentUser;

    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper, CurrentUser currentUser) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.currentUser = currentUser;
    }

    @Override
    public boolean registerUser(UserServiceModel userServiceModel) {
        try {
            userRepository.save(modelMapper.map(userServiceModel, User.class));
        } catch (Exception e) {
            return false;
        }
        return true;

    }

    @Override
    public UserServiceModel findByUsernameAndPassword(String username, String password) {
        return userRepository.findByUsernameAndPassword(username, password)
                .map(user -> modelMapper.map(user, UserServiceModel.class))
                .orElse(null);
    }

    @Override
    public void loginUser(Long id, String username) {
        currentUser.setId(id);
        currentUser.setUsername(username);

    }

    @Override
    public User findById(Long id) {
        return userRepository
                .findById(id)
                .orElse(null);
    }


}
