package com.xvr.controller;

import com.xvr.dto.AppUserSignInDTO;
import com.xvr.model.AppUser;
import com.xvr.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.awt.*;

@Controller

public class MainController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    @Qualifier ("encoder")
    PasswordEncoder passwordEncoder;


    @RequestMapping("/index")
    public String hello(){
        return "index";
    }

    @PostMapping(value = "/signAppUser", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String login (AppUserSignInDTO appUserSignInDTO, PasswordEncoder passwordEncoder){

        String passwordEncoded = passwordEncoder.encode(appUserSignInDTO.getPassword());
        boolean notUserUnique = userRepository.getAppUserByUserName(appUserSignInDTO.getUserName()).map(appUser -> {
          return appUser.getEncryptedPassword().equals(passwordEncoded);
        }).orElse(false);
        //if appUser notUnique user is exist, if Unique signAppUser.html
        if (notUserUnique){
            return "redirect:/joinAppUser";
        }else{
            AppUser appUser = new AppUser();
            appUser.setUserName(appUserSignInDTO.getUserName());
            appUser.setEncryptedPassword(passwordEncoded);
            userRepository.save(appUser);
            return "signAppUser";
        }
    }

    @PostMapping("/joinAppUser")
    public String joinAppUser(){

        return "joinAppUser";
    }



}
