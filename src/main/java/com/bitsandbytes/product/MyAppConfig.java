package com.bitsandbytes.product;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


//phele hamare pass My component ka object nhi tha kyuki @component use nhi kiya tha hamne ,
// to , ho sakta hia koi external class ho , jiska mane nhi pata ki componet se objejct create hua hai ya nhi
// hame kaise pata ki uske pass component hia ya nhi to ham khud se bean bane lete hai phle uska
@Configuration
public class MyAppConfig {

    @Bean
    public MyComponents myComponents()
    {
        return new MyComponents();
    }
}
