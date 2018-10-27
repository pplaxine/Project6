package com.philippe75.env.bootstrap;



import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

//@ImportResource("classpath*:applicationContext*.xml")

@Configuration
@ComponentScan("com.philippe75.env")
public class SpringConfiguration {

}
