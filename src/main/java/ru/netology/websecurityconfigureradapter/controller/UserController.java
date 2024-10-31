package ru.netology.websecurityconfigureradapter.controller;

import jakarta.ws.rs.core.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import ru.netology.websecurityconfigureradapter.config.KeycloakConfig;
import ru.netology.websecurityconfigureradapter.dto.UserDto;
import ru.netology.websecurityconfigureradapter.entity.User;
import ru.netology.websecurityconfigureradapter.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService service;
    private final KeycloakConfig keycloak;


    public UserController(UserService service, KeycloakConfig keycloak) {
        this.service = service;
        this.keycloak = keycloak;
    }

    // добавление
    @PostMapping("/add")
    public ResponseEntity add(@RequestBody UserDto userDTO) {

        // проверка на обязательные параметры
        if (userDTO.getId() != null && userDTO.getId() != 0) {
            // id создается автоматически в БД (autoincrement), поэтому его передавать не нужно, иначе может быть конфликт уникальности значения
            return new ResponseEntity("redundant param: id MUST be null", HttpStatus.NOT_ACCEPTABLE);
        }

        // если передали пустое значение
        if (userDTO.getEmail() == null || userDTO.getEmail().trim().length() == 0) {
            return new ResponseEntity("missed param: email", HttpStatus.NOT_ACCEPTABLE);
        }

        if (userDTO.getPassword() == null || userDTO.getPassword().trim().length() == 0) {
            return new ResponseEntity("missed param: password", HttpStatus.NOT_ACCEPTABLE);
        }

        if (userDTO.getUserName() == null || userDTO.getUserName().trim().length() == 0) {
            return new ResponseEntity("missed param: username", HttpStatus.NOT_ACCEPTABLE);
        }
        Response createdResponse = keycloak.createKeycloakUser(userDTO);

        return ResponseEntity.status(createdResponse.getStatus()).build();

    }


//    @GetMapping("/get-users")
//    @Secured("ROLE_READ")
//    public List<User> getUsers() {
//        return service.getUsers();
//    }
//
//    @GetMapping("/getName")
//    public String getName(@RequestParam Long id) {
//        return service.getName(id);
//    }
//
//    @GetMapping("/getSurname")
//    public String getSurname(@RequestParam Long id) {
//        return service.getSurname(id);
//    }
//
//    @GetMapping("/getAge")
//    public int getAge(@RequestParam Long id) {
//        return service.getAge(id);
//    }
}
