package dooks.tododook.domain.todo.repository;


import dooks.tododook.domain.todo.entity.TodoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TodoRepository extends JpaRepository<TodoEntity,String> {
    @Query("select  t from TodoEntity t where t.userId= ?1")

    List<TodoEntity> findByUserId(Long userId);
    Optional<TodoEntity> findByIdAndUserId(String id, Long userId);
}
