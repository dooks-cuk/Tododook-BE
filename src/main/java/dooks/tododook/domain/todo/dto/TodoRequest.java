package dooks.tododook.domain.todo.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class TodoRequest {
    private String id;
    private String title;
    private String priority;
    private boolean done;
}
