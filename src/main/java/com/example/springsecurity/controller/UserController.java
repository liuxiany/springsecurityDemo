package com.example.springsecurity.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class UserController {

    @GetMapping("/user")
//    @PreAuthorize("hasRole('ROLE_USER')")
    @PreAuthorize("hasPermission('/user','user')")
    public String user(@AuthenticationPrincipal Principal principal, Model model){

        String name = SecurityContextHolder.getContext().getAuthentication().getName();

//        model.addAttribute("username", principal.getName());
        model.addAttribute("username", name);
        return "user/user";
    }

}
