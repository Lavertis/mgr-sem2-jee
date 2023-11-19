package com.pollub.lab_6.controllers;

import com.pollub.lab_6.dao.UserDao;
import com.pollub.lab_6.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;

@Controller
public class UserController {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserDao dao;

    @GetMapping("/login")
    public String loginPage() {
        // zwrócenie nazwy widoku logowania — login.html
        return "login";
    }

    @GetMapping("/register")
    public String registerPage(Model m) {
        // dodanie do modelu nowego użytkownika
        m.addAttribute("user", new User());
        // zwrócenie nazwy widoku rejestracji — register.html
        return "register";
    }

    @PostMapping("/register")
    public String registerPagePOST(@ModelAttribute User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        dao.save(user);
        // przekierowanie do adresu url: /login
        return "redirect:/login";
    }

    @GetMapping("/profile")
    public String profilePage(Model m, Principal principal) {
        // dodanie do modelu aktualnie zalogowanego użytkownika:
        m.addAttribute("user", dao.findByLogin(principal.getName()));
        // zwrócenie nazwy widoku profilu użytkownika — profile.html
        return "profile";
    }

    @GetMapping("/users")
    public String usersPage(Model m) {
        // dodanie do modelu listy użytkowników
        m.addAttribute("users", dao.findAll());
        // zwrócenie nazwy widoku users.html
        return "users";
    }

    @GetMapping("/users/current/edit")
    public String editCurrentUser(Model m, Principal principal) {
        User user = dao.findByLogin(principal.getName());
        m.addAttribute("user", user);
        return "user_edit";
    }

    @PostMapping("/users/current/edit")
    public String updateCurrentUser(@ModelAttribute User user, Principal principal) {
        User currentUser = dao.findByLogin(principal.getName());
        currentUser.setName(user.getName());
        currentUser.setSurname(user.getSurname());
        currentUser.setLogin(user.getLogin());
        if (!user.getPassword().isEmpty()) {
            currentUser.setPassword(passwordEncoder.encode(user.getPassword()));
        }
        dao.save(currentUser);
        return "redirect:/profile";
    }

    @GetMapping("/users/current/delete")
    public String deleteCurrentUser(Principal principal) {
        User user = dao.findByLogin(principal.getName());
        dao.delete(user);
        return "redirect:/logout";
    }
}
