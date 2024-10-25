package ru.netology.websecurityconfigureradapter.service;

import org.springframework.stereotype.Service;
import ru.netology.websecurityconfigureradapter.model.User;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    private static List<User> users = new ArrayList<>();

    static {
        users.add(new User(1, "Georgiy", "Petrov", 19));
        users.add(new User(2, "Anna", "Ivanova", 30));
        users.add(new User(3, "Ekaterina", "Sidorova", 39));
        users.add(new User(4, "Evgeniy", "Bychik", 40));
        users.add(new User(5, "Aleksandr", "Shvedov", 51));
        users.add(new User(6, "Ivan", "Ivanov", 25));
    }


    public List<User> getUsers() {
        return users.stream().toList();
    }

    public String getName(int id) {
        return users.get(id).getName();
    }

    public int getAge(int id) {
        return users.get(id).getAge();
    }

    public String getSurname(int id) {
        return users.get(id).getSurname();
    }
}
