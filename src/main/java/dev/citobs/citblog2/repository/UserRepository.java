package dev.citobs.citblog2.repository;

import dev.citobs.citblog2.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;


//DAO
//자동으로 Bean 등록이 됨
//@Repository는 생략이 가능함
public interface UserRepository extends JpaRepository<User, Integer> {

    //select * from user WHERE username =1?;
    Optional<User> findByUsername(String username);

}
//JPA Naming 전략
//SELECT * FROM user Wherer username =? AND password =?
// User findByUsernameAndPassword(String username, String password);

//    @Query(value = "SELECT * FROM user WHERE username=?1 AND password=?2", nativeQuery = true)
//    User login(String username, String password);