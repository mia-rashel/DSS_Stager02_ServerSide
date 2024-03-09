package com.example.coen_project;

import com.example.coen_project.SkiersServlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServletConfig {

    @Bean
    public ServletRegistrationBean<SkiersServlet> skiersServletRegistrationBean() {
        ServletRegistrationBean<SkiersServlet> registrationBean = new ServletRegistrationBean<>(new SkiersServlet(), "/skiers");
        return registrationBean;
    }
}
