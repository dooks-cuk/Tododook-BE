package dooks.tododook.domain.todo.service;

import dooks.tododook.domain.todo.dto.TodoRequest;
import dooks.tododook.domain.todo.dto.TodoResponse;
import dooks.tododook.domain.todo.entity.TodoEntity;
import dooks.tododook.domain.todo.exception.TodoException;
import dooks.tododook.domain.todo.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;


@Slf4j
@Service
@RequiredArgsConstructor
public class TodoService {

    private final TodoRepository repository;

    public List<TodoResponse> create(Long userId,TodoRequest todo){
        TodoEntity entity = TodoEntity.builder()
                .userId(userId)
                .title(todo.getTitle())
                .priority(todo.getPriority())
                .category(todo.getCategory())
                .done(todo.isDone()).build();
        repository.save(entity);
        List<TodoEntity> todos = repository.findByUserId(entity.getUserId());
        return TodoResponse.of(todos);
    }
    public List<TodoResponse> retrieve(Long userId){
        List<TodoEntity> todos =repository.findByUserId(userId);
        return TodoResponse.of(todos);
    }


    public List<TodoResponse> update(Long userId, TodoRequest todo){
        TodoEntity entity = repository.findByIdAndUserId(todo.getId(), userId)
                .orElseThrow(TodoException.TodoNotFoundException::new);
        entity.setTitle(todo.getTitle());
        entity.setPriority(todo.getPriority());
        entity.setDone(todo.isDone());

        repository.save(entity);
        List<TodoEntity> todos = repository.findByUserId(entity.getUserId());
        return TodoResponse.of(todos);
    }

    public List<TodoResponse> delete(Long userId, TodoRequest todo){
        TodoEntity entity = repository.findByIdAndUserId(todo.getId(), userId)
                .orElseThrow(TodoException.TodoNotFoundException::new);
        repository.delete(entity);
        List<TodoEntity> todos = repository.findByUserId(entity.getUserId());
        return TodoResponse.of(todos);
    }
}
