package proxy.factory.bean;

import java.lang.reflect.Method;

import org.springframework.aop.MethodBeforeAdvice;

public class MyBeforeAdvice implements MethodBeforeAdvice{
    @Override

    public void before(Method method, Object[] args, Object target)

            throws Throwable {

        System.out.println("run my before advice");

    }
}