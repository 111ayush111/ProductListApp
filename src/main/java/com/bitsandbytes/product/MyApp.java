package com.bitsandbytes.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MyApp {
//    @Autowired
//    ye myComponents data type->MyComponents type ka hai
    private final MyComponents myComponents;


//    object ban raha hai autowired se kya ho raha hai?---> constructor Injection
//    @Autowired
//    यहां यह constructor injection का तरीका अपनाता है।
//    मतलब, जब Spring container (जैसे ApplicationContext) आपके MyApp क्लास का object बनाएगा,
//    तो वह पहले MyComponents का object (bean) ढूंढेगा और उसे constructor के parameter के रूप में inject (पास) कर देगा।
//    see as normal constructor making, having variable
    @Autowired
    public MyApp(MyComponents myComponents) {
        this.myComponents=myComponents;
    }

    public void run()
    {
        myComponents.getMessage();
    }
}
