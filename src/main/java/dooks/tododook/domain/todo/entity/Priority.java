package dooks.tododook.domain.todo.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Priority {
    HIGH(2),MEDIUM(1),LOW(0);
    private final int priority;
}
