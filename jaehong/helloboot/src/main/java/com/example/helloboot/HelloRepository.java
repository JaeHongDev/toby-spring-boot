package com.example.helloboot;

public interface HelloRepository {
    Hello findHello(String name);

    void increaseCount(String name);


    // interface 를 사용하다 보면 자바의 default메서드를 사용할 수 없을까를 고민
    default int countOf(String name) {
        var hello = this.findHello(name);
        return hello == null ? 0 : hello.getCount();
    }
}
