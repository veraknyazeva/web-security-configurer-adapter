package ru.netology.websecurityconfigureradapter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.netology.websecurityconfigureradapter.entity.User;

public interface UserRepository extends JpaRepository<User,Long > {
}
