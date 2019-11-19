package com.example.exercise2;

public interface MyCollback<T> {
    void succeed(T bean);
    void failed(String error);
}
