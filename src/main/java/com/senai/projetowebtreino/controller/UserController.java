package com.senai.projetowebtreino.controller;


import com.senai.projetowebtreino.model.UserModel;

import com.senai.projetowebtreino.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(value = "/usuarios" )
public class UserController {

    @Autowired
    private UserService userService;

//    @Autowired
//    private UserRepository userRepository;

    @GetMapping
    public String getAllUsers(Model model){
        List<UserModel> users = userService.findAll();
        model.addAttribute("users", users);
        return "user/usuarios";
    }

    @GetMapping(value = "/novo")
    public String formUser(){
        return "user/novo";
    }


    @PostMapping(value = "/cadastrar")
    public String insertUser(UserModel userModel){
        userService.save(userModel);
        return "redirect:/usuarios";
    }

    @GetMapping(value = "/{id}/excluir")
    public String deleteUser(@PathVariable Integer id){
        userService.deleteUser(id);
        return "redirect:/usuarios";
    }

    @GetMapping(value = "/{id}")
    public String getUser(@PathVariable Integer id, Model model){
        Optional<UserModel> user = userService.getUserById(id);
        try{
        model.addAttribute("user", user.get());

        }catch(Exception e){
            return "redirect:/usuarios";
        }
        return "user/alterar";
    }

    @PostMapping(value = "/alterar/{id}")
    public String updateUser(UserModel userModel, @PathVariable Integer id){
        Optional<UserModel> user = userService.getUserById(id);
        if(user.isEmpty()){
        return "redirect:/usuarios";
        }
        userService.save(userModel);
        return "redirect:/usuarios";
    }
}
