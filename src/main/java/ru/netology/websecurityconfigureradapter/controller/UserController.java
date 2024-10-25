package ru.netology.websecurityconfigureradapter.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.netology.websecurityconfigureradapter.model.User;
import ru.netology.websecurityconfigureradapter.service.UserService;

import java.util.List;

@RestController
public class UserController {
    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping("/get-users")
    public List<User> getUsers() {
        return service.getUsers();
    }

    @GetMapping("/getName")
    public String getName(@RequestParam int id) {
        return service.getName(id);
    }

    @GetMapping("/getSurname")
    public String getSurname(@RequestParam int id) {
        return service.getSurname(id);
    }

    @GetMapping("/getAge")
    public int getAge(@RequestParam int id) {
        return service.getAge(id);
    }
}
