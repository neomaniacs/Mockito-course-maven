package com.in28minutes.business;

import com.in28minutes.data.api.TodoService;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.*;

public class TodoBusinessImplMockTest {

    @Test
    public void testRetriveTodosRelatedToSpring_usingMock() {
        TodoService todoService = mock(TodoService.class);
        when(todoService.retrieveTodos(anyString())).thenReturn(Arrays.asList("Learn Spring MVC", "Learn Spring", "Learn to Dance"));
        TodoBusinessImpl todoBusinessImpl = new TodoBusinessImpl(todoService);
        List<String> filteredTodos = todoBusinessImpl.retriveTodosRelatedToSpring("Dance");
        assertEquals(2, filteredTodos.size());
    }

    @Test
    public void testRetriveTodosRelatedToSpring_usingMock_BDDMode() {
        // Given
        TodoService todoService = mock(TodoService.class);
        given(todoService.retrieveTodos(anyString()))
                .willReturn(Arrays.asList("Learn Spring MVC", "Learn Spring", "Learn to Dance"));
        TodoBusinessImpl todoBusinessImpl = new TodoBusinessImpl(todoService);

        // When
        List<String> filteredTodos = todoBusinessImpl.retriveTodosRelatedToSpring("Spring");

        // Then
        assertThat(filteredTodos.size(), is(2));
    }

    @Test
    public void testDeleteTodosNotRelatedToSpring_usingMock() {
        // Declare Argument Captor
        ArgumentCaptor<String> stringArgumentCaptor = ArgumentCaptor.forClass(String.class);

        // Given
        TodoService todoServiceMock = mock(TodoService.class);
        List<String> todos = Arrays.asList("Learn Spring MVC", "Learn Spring", "Learn to Dance");
        given(todoServiceMock.retrieveTodos(anyString())).willReturn(todos);
        TodoBusinessImpl todoBusinessImpl = new TodoBusinessImpl(todoServiceMock);

        // When
        todoBusinessImpl.deleteTodosNotRelatedToSpring("Spring");

        // Then
        then(todoServiceMock).should().deleteTodo(stringArgumentCaptor.capture());

        assertThat(stringArgumentCaptor.getValue(), is("Learn to Dance"));
    }
}
