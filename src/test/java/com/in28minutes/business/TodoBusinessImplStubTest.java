package com.in28minutes.business;

import com.in28minutes.data.api.TodoService;
import com.in28minutes.data.api.TodoServiceStub;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoBusinessImplStubTest {

    @Test
    public void testRetriveTodosRelatedToSpring() {
        // Given
        TodoService todoService = new TodoServiceStub();
        TodoBusinessImpl todoBusinessImpl = new TodoBusinessImpl(todoService);

        // When
        List<String> filteredTodos = todoBusinessImpl.retriveTodosRelatedToSpring("Spring");

        // Then
        assertEquals(2, filteredTodos.size());
    }

    @Test
    public void testRetriveTodosRelatedToSpring2() {
        // Given
        TodoService todoService = new TodoServiceStub();
        TodoBusinessImpl todoBusinessImpl = new TodoBusinessImpl(todoService);

        // When
        List<String> filteredTodos = todoBusinessImpl.retriveTodosRelatedToSpring("Dummy");

        // Then
        assertEquals(0, filteredTodos.size());
    }

}
