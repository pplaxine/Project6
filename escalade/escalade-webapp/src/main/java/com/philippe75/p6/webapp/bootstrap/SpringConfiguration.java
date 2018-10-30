package com.philippe75.p6.webapp.bootstrap;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;



@Configuration
@ComponentScan("com.philippe75.p6")
@ImportResource("classpath*:applicationContext*.xml")
public class SpringConfiguration {

}
