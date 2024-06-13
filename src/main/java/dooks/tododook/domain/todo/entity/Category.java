package dooks.tododook.domain.todo.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Category {
    PERSONAL("#FFCDD2"),
    WORK("#C8E6C9"),
    STUDY("#BBDEFB"),
    HOBBY("#FFECB3"),
    FINANCE("#D1C4E9"),
    OTHER("#E0E0E0");
    private final String color;
}
