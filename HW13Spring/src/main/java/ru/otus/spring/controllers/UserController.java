package ru.otus.spring.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;
import ru.otus.core.model.User;
import ru.otus.core.service.DbServiceUser;

import java.util.List;
import java.util.Optional;

@Controller
public class UserController {
    private final DbServiceUser repository;

    public UserController(DbServiceUser repository) {
        this.repository = repository;
    }

    @GetMapping({"/user/list"})
    public String userListView(Model model) {
        List<User> users = repository.findAll();
        //   Optional<User> user = repository.getUserById(5);
        //    model.addAttribute("users", user.get());
        model.addAttribute("users", users);
        return "userList.html";
    }

    @GetMapping("/user/create")
    public String userCreateView(Model model) {
        User someTestUser = new User(666L, "Eddie", "IronMaidenLogin", "666");
        model.addAttribute("user", someTestUser);
        return "userCreate.html";
    }

    @PostMapping("/user/save")
    public RedirectView userSave(@ModelAttribute User user) {
        repository.saveUser(user);
        return new RedirectView("/user/list", true);
    }
}
