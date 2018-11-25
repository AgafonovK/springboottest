package com.xvr.controller;

import com.xvr.dto.AppUserSignInDTO;
import com.xvr.model.AppUser;
import com.xvr.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping(value = "/signAppUser", consumes = {"application/x-www-form-urlencoded"})

    public String login (AppUserSignInDTO appUserSignInDTO, @RequestParam(value = "userName") String userName,
                         @RequestParam(value = "password") String password){
        appUserSignInDTO.setUserName(userName);
        appUserSignInDTO.setPassword(password);
        System.out.println(appUserSignInDTO.getUserName() + " " + appUserSignInDTO.getPassword());
        String passwordEncoded = passwordEncoder.encode(appUserSignInDTO.getPassword());
        System.out.println(passwordEncoded);
        boolean notUserUnique = userRepository.getAppUserByUserName(appUserSignInDTO.getUserName()).map(appUser -> {
          return appUser.getEncryptedPassword().equals(passwordEncoded);
        }).orElse(false);
        System.out.println(notUserUnique);
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
