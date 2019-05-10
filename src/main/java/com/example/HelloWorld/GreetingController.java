package com.example.HelloWorld;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.hibernate.Session;
import com.example.HelloWorld.HibernateUtil;

@Controller
public class GreetingController {
    @GetMapping("/greeting")
    public String greeting(
            Model model
    ) {
        model.addAttribute("name", "Hello, World!");
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        UsersEntity UsersEntity = new UsersEntity();
        //UsersEntity.setId(1);
        UsersEntity.setEmail("vasyaPupkin@gmail.com");
        UsersEntity.setPassword("12345");
        UsersEntity.setDefaultPrivate((byte)1);

        session.save(UsersEntity);
        session.getTransaction().commit();

        session.close();

        return "greeting";
    }




}