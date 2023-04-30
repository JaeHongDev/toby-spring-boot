package com.example.helloboot;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class HelloServiceTest {


    private static final HelloRepository helloRepositoryStub = new HelloRepository() {
        @Override
        public Hello findHello(String name) {
            return null;
        }

        @Override
        public void increaseCount(String name) {

        }
    };

    @Test
    void success_test() {
        final var sampleHello = new SimpleHelloService(helloRepositoryStub);
        final var result = sampleHello.sayHello("name");

        Assertions.assertThat(result).isEqualTo("hello name");
    }
}