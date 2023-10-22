package dev.citobs.citblog2.repository;

import dev.citobs.citblog2.model.Board;
import dev.citobs.citblog2.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface BoardRepository extends JpaRepository<Board, Integer> {


}
