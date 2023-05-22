package com.anywr.javasprintsecuritytest;

import com.anywr.javasprintsecuritytest.Entity.Personn;
import com.anywr.javasprintsecuritytest.Entity.Role;
import com.anywr.javasprintsecuritytest.Service.Interface.AccountService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;

@SpringBootApplication
public class JavaSprintSecurityTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(JavaSprintSecurityTestApplication.class, args);
    }

    @Bean
    CommandLineRunner start(AccountService accountService){
        return args -> {
            accountService.saveRole(new Role(1L,"ADMIN"));
            accountService.savePersonn(new Personn(1L,"admin",getBCPE().encode("admin"),true,new Date()));
        };
    }
    @Bean
    BCryptPasswordEncoder getBCPE(){
        return new BCryptPasswordEncoder();
    }

}
