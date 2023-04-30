package com.example.helloboot;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest(webEnvironment = WebEnvironment.NONE)
@Transactional
public class HelloRepositoryTest {
    @Autowired
    HelloRepository helloRepository;
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Test
    void findHelloFailed() {
        Assertions.assertThat(helloRepository.findHello("Jaehong")).isNull();
    }

    @Test
    void increaseCount() {
        Assertions.assertThat(helloRepository.countOf("Jaehong")).isEqualTo(0);

        helloRepository.increaseCount("Jaehong");
        Assertions.assertThat(helloRepository.countOf("Jaehong")).isEqualTo(1);

        helloRepository.increaseCount("Jaehong");
        Assertions.assertThat(helloRepository.countOf("Jaehong")).isEqualTo(2);
    }
}
