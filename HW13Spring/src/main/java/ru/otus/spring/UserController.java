package ru.otus.spring;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    @GetMapping({"/", "/user/list"})
    public String userListView(Model model) {
        List<User> users = repository.findAll();
        model.addAttribute("users", users);
        return "userList.html";
    }

    @GetMapping({"/", "/user"})
    public String userViewById(Model model, @RequestParam long id) {
        Optional<User> user = repository.getUserById(id);
        model.addAttribute("users", user);
        return "userList.html";
    }

    @GetMapping("/user/create")
    public String userCreateView(Model model) {
        User someTestUser = new User(666L, "Eddie", "IronMaidenLogin", "666" );
        model.addAttribute("user", someTestUser);
        repository.saveUser(someTestUser);
        return "userCreate.html";
    }
}
