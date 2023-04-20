package study;

import static org.assertj.core.api.Assertions.assertThat;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.runner.ApplicationContextRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.type.AnnotatedTypeMetadata;

public class ConditionalTest {

    @Test
    void conditional() {
        // true
        new ApplicationContextRunner().withUserConfiguration(Config1.class)
                .run(context -> {
                    assertThat(context).hasSingleBean(MyBean.class);
                });

        // false
        new ApplicationContextRunner().withUserConfiguration(Config2.class)
                .run(context -> {
                    assertThat(context).doesNotHaveBean(MyBean.class);
                    assertThat(context).doesNotHaveBean(Config1.class);
                });
    }

    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.TYPE)
    @Conditional(TrueConditional.class)
    @interface TrueCondition {
    }

    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.TYPE)
    @Conditional(FalseConditional.class)
    @interface FalseCondition {
    }


    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.TYPE)
    @Conditional(BooleanConditional.class)
    @interface BooleanCondition {
        boolean value();
    }

    static class BooleanConditional implements Condition {
        @Override
        public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
            final var attributes = metadata.getAnnotationAttributes(BooleanCondition.class.getName());
            return (Boolean) attributes.get("value");
        }
    }

    @Configuration
    @BooleanCondition(true)
    static class Config1 {

        @Bean
        MyBean myBean() {
            return new MyBean();
        }


    }

    @Configuration
    @BooleanCondition(false)
    static class Config2 {
        @Bean
        MyBean myBean() {
            return new MyBean();
        }
    }

    static class MyBean {

    }

    static class TrueConditional implements Condition {
        @Override
        public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
            return true;
        }
    }

    static class FalseConditional implements Condition {
        @Override
        public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
            return false;
        }
    }

}
