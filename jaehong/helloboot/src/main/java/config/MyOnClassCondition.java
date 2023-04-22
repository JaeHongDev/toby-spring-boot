package config;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;
import org.springframework.util.ClassUtils;

public class MyOnClassCondition implements Condition {
    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        final var attributes = metadata.getAnnotationAttributes(ConditionalMyOnClass.class.getName());
        String value = attributes.get("value").toString();
        return ClassUtils.isPresent(value, context.getClassLoader());
    }
}
