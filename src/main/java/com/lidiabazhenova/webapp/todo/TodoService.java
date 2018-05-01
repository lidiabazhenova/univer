package com.lidiabazhenova.webapp.todo;

import java.util.ArrayList;
import java.util.List;

public class TodoService {
    private static List<Todo> todos = new ArrayList();

    static {
        todos.add(new Todo("https://oz.by/stationery/more10571950.html?sbtoken=88b118047983de3f728d85deb9507f4b"));
        todos.add(new Todo("https://oz.by/stationery/more10254731.html?sbtoken=d4a99efcd221d6c11288ceaf233be8d5"));
        todos.add(new Todo("https://oz.by/pens/more10354337.html?sbtoken=41b6d2ce226183997c21ae656e785234"));
    }

    public List<Todo> retrieveTodos() {
        return todos;
    }

    public void addTodo(Todo todo){
        todos.add(todo);
    }

    public void deleteTodo(Todo todo){
        todos.remove(todo);
    }
}
