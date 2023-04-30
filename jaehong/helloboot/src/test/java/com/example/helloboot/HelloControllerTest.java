package com.example.helloboot;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;


class HelloControllerTest {


    @Test
    void success_test() {
        final var helloController = new HelloController(new HelloService() {
            @Override
            public String sayHello(String name) {
                return name;
            }

            @Override
            public int countOf(String name) {
                return 0;
            }
        });
        final var result = helloController.hello("spring");

        Assertions.assertThat(result).isEqualTo("spring");
    }

    @Test
    void fail_test() {
        final var helloController = new HelloController(new HelloService() {
            @Override
            public String sayHello(String name) {
                return null;
            }

            @Override
            public int countOf(String name) {
                return 0;
            }
        });

        assertThatIllegalArgumentException().isThrownBy(() -> helloController.hello(null));
        assertThatIllegalArgumentException().isThrownBy(() -> helloController.hello(""));
    }
}