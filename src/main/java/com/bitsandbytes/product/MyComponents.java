package com.bitsandbytes.product;

import org.springframework.stereotype.Component;

@Component
public class MyComponents  {
    public MyComponents()
    {
        System.out.println("Result from MyComponent");
    }
    public void getMessage()
    {
        System.out.println("Result from getMessage");
    }
}
