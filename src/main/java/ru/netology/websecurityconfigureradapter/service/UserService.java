package ru.netology.websecurityconfigureradapter.service;

import org.springframework.stereotype.Service;
import ru.netology.websecurityconfigureradapter.entity.User;
import ru.netology.websecurityconfigureradapter.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public List<User> getUsers() {
        return repository.findAll();
    }

    public String getName(Long id) {
        Optional<User> userOptional = repository.findById(id);
        String name = userOptional.get().getName();
        return name;
    }

    public int getAge(Long id) {
        Optional<User> userOptional = repository.findById(id);
        int age = userOptional.get().getAge();
        return age;
    }

    public String getSurname(Long id) {
        Optional<User> userOptional = repository.findById(id);
        String surname = userOptional.get().getSurname();
        return surname;
    }
}
