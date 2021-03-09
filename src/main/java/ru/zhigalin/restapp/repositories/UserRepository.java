package ru.zhigalin.restapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.zhigalin.restapp.model.User;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    String GET_ALL_USERS = "select u from User u join fetch u.roles roles";

    User findByLogin(String login);

    @Query(GET_ALL_USERS)
    List<User> findAll();
}
