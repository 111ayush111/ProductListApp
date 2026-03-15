package com.bitsandbytes.product;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@OpenAPIDefinition(
        info = @Info(
                    title = "Product Service REST API documentation",
                version = "Product Service REST API",
                description = "v1",
                contact =@Contact(
                        name="Ayush Anand",
                        email = "codewithalaric@gmail.com"
                                    )
                    )
                ,externalDocs = @ExternalDocumentation( description = "share Point Url Product Service Api"
                                                                ,url="example.com")
)

@SpringBootApplication
public class ProductApplication {

	public static void main(String[] args) {

        ApplicationContext context= SpringApplication .run(ProductApplication.class, args);
        MyApp myComponent= context.getBean(MyApp.class);
        myComponent.run();
	}
//        MyComponents myComponents = new MyComponents();//no need to make component as comornt is added
}
