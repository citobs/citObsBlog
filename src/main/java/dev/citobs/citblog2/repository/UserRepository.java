package dev.citobs.citblog2.repository;

import dev.citobs.citblog2.model.User;
import org.springframework.data.jpa.repository.JpaRepository;


//DAO
//자동으로 Bean 등록이 됨
//@Repository는 생략이 가능함
public interface UserRepository extends JpaRepository<User, Integer> {
}
