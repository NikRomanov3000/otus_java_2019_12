package ru.otus.spring.controllers;

import com.google.gson.Gson;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.core.model.User;
import ru.otus.core.service.DbServiceUser;

import java.util.List;

@RestController
public class TrueRestController {
    private final DbServiceUser repository;

    public TrueRestController(DbServiceUser repository) {
        this.repository = repository;
    }

    @GetMapping({"/user"})
    public String userListView(Model model) {
        List<User> users = repository.findAll();
        model.addAttribute("users", users);
        return new Gson().toJson(users);
    }

}
