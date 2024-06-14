package dooks.tododook.domain.todo.dto;

import dooks.tododook.domain.todo.entity.Category;
import dooks.tododook.domain.todo.entity.Priority;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TodoRequest {
    private String id;
    private String title;
    private Priority priority;
    private Category category;
    private boolean done;
}
