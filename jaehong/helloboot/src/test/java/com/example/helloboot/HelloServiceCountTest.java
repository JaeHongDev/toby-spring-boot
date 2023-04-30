package com.example.helloboot;


import java.util.stream.IntStream;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

@HellobootTest
public class HelloServiceCountTest {

    @Autowired
    HelloService helloService;

    @Autowired
    HelloRepository helloRepository; // 목적이 다름

    @Test
    void sayHelloIncreaseCount() {
        IntStream.rangeClosed(1, 10).forEach(count -> {
            helloService.sayHello("Jaehong");
            Assertions.assertThat(helloRepository.countOf("Jaehong")).isEqualTo(count);
        });

    }
}
