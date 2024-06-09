package dooks.tododook.domain.todo.dto;


import dooks.tododook.domain.todo.entity.TodoEntity;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class TodoResponse {
    String id;
    String title;
    String priority;
    boolean done;

    public static TodoResponse of(TodoEntity entity) {
        return new TodoResponse(
                entity.getId(),
                entity.getTitle(),
                entity.getPriority(),
                entity.isDone()

        );
    }

    public static List<TodoResponse> of(List<TodoEntity> entities) {
        return entities.stream()
                .map(TodoResponse::of)
                .collect(Collectors.toList());
    }
}
