package dooks.tododook.domain.todo.controller;


import dooks.tododook.domain.auth.jwt.UserDetailsImpl;
import dooks.tododook.domain.todo.dto.TodoRequest;
import dooks.tododook.domain.todo.dto.TodoResponse;
import dooks.tododook.domain.todo.service.TodoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@Slf4j
@CrossOrigin(origins="*")
@RestController
@RequestMapping("todo")
public class TodoController {
    @Autowired
    private TodoService service;


    @PostMapping
    public ResponseEntity<?> createTodo(@AuthenticationPrincipal UserDetailsImpl userDetails, @RequestBody TodoRequest todo) {
        Long userId = userDetails.getId();
        List<TodoResponse> response=service.create(userId,todo);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping
    public ResponseEntity<?> retrieveTodolist(@AuthenticationPrincipal UserDetailsImpl userDetails){
        Long userId = userDetails.getId();
        List<TodoResponse> response=service.retrieve(userId);
        return ResponseEntity.ok().body(response);
    }

    @PutMapping
    public ResponseEntity<?> updateTodo(@AuthenticationPrincipal UserDetailsImpl userDetails, @RequestBody TodoRequest todo){
        Long userId = userDetails.getId();
        List<TodoResponse> response=service.update(userId,todo);
        return ResponseEntity.ok().body(response);
    }

    @DeleteMapping
    public ResponseEntity<?> deleteTodo(@AuthenticationPrincipal UserDetailsImpl userDetails,@RequestBody TodoRequest todo){
        Long userId = userDetails.getId();
        List<TodoResponse> response=service.delete(userId,todo);
        return ResponseEntity.ok().body(response);
    }


}
