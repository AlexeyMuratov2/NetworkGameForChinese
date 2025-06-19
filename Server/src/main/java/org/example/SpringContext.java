package org.example;

import org.springframework.context.ApplicationContext;

public class SpringContext {
    private static ApplicationContext context;

    public static void setContext(ApplicationContext ctx){
        context = ctx;
    }

    public static <T> T getBean(Class<T> tClass){
        return context.getBean(tClass);
    }
}
