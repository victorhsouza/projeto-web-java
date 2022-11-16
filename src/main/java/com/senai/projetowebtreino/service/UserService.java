package com.senai.projetowebtreino.service;

import com.senai.projetowebtreino.model.UserModel;
import com.senai.projetowebtreino.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<UserModel> findAll(){
        return userRepository.findAll();
    }

    public UserModel save(UserModel userModel){
        return userRepository.save(userModel);
    }

    public void deleteUser(Integer id){
        userRepository.deleteById(id);
    }

    public Optional<UserModel> getUserById(Integer id){
        return userRepository.findById(id);
    }
}
