package info.example.rest.starter;

import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

public class MapperNumberExistingCondition implements Condition {
    @Override
    public boolean matches(ConditionContext conditionContext, AnnotatedTypeMetadata annotatedTypeMetadata) {
        Object mapperNumber;
        try {
            mapperNumber = conditionContext.getBeanFactory().getBean("mapperNumber");
        } catch (NoSuchBeanDefinitionException ex) {
            return false;
        }
        return mapperNumber instanceof Integer;
    }
}
