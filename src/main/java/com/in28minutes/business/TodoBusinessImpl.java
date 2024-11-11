package com.in28minutes.business;

import com.in28minutes.data.api.TodoService;

import java.util.ArrayList;
import java.util.List;

public class TodoBusinessImpl {

    private final TodoService todoService;

    public TodoBusinessImpl(TodoService todoService) {
        this.todoService = todoService;
    }

    public List<String> retriveTodosRelatedToSpring(String user) {
        List<String> todos = todoService.retrieveTodos(user);
        return todos
                .stream()
                .filter(todo -> todo.contains(user))
                .toList();
    }

    public List<String> anotherRetrieveTodosRelatedToSpring(String user) {
        List<String> filteredTodos = new ArrayList<String>();
        List<String> todos = todoService.retrieveTodos(user);
        return todos.stream()
                .filter(todo -> todo.contains(user))
                .toList();
    }

    public void deleteTodosNotRelatedToSpring(String user) {
        List<String> todos = todoService.retrieveTodos(user);
        todos.stream()
                .filter(todo -> !todo.contains(user))
                .forEach(todoService::deleteTodo);
    }

}
