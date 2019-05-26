package com.example.HelloWorld;

import DAL.Entities.*;
import DAL.DAO.*;
import utils.HibernateUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.hibernate.Session;
import javax.servlet.http.Cookie;
import java.util.UUID;

import java.util.Date;

@Controller
public class GreetingController {
    @GetMapping("/greeting")
    public String greeting(
            Model model
    ) {
        model.addAttribute("name", "Hello, World!");


        //---This code is creating new user in DB---
        /*
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        UsersEntity UsersEntity = new UsersEntity();
        UsersEntity.setEmail("vasyaPupkin@gmail.com");
        UsersEntity.setPassword("12345");
        UsersEntity.setDefaultPrivate((byte)1);
        session.save(UsersEntity);
        session.getTransaction().commit();
        session.close();
        */

        //---This code is creating new records in DB---

        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        ContextImagesEntity ContextImagesEntity = new ContextImagesEntity();
        byte [] b = {1, 2, 3};
        ContextImagesEntity.setImage(b);
        session.save(ContextImagesEntity);

    //    session = HibernateUtil.getSessionFactory().openSession();
        OriginalImagesEntity OriginalImagesEntity = new OriginalImagesEntity();
        AbstractHibernateDAO DAO = new OriginalImagesDAO();
      //  session.getCurrentSession();
        OriginalImagesEntity.setImage(b);
       // session.save(OriginalImagesEntity);
        DAO.create(OriginalImagesEntity);
        /**/
        //DAO.deleteById(6);

        DAO = new UsersDAO();
        DAO.deleteById(10);

        UsersEntity UsersEntity = new UsersEntity();
        Cookie cookieName = new Cookie("neuralartId", UUID.randomUUID().toString());
        String cookie = cookieName.toString();
        Date cookieDate = new Date();
        UsersEntity.setCookie(cookie);
        UsersEntity.setCookieCreationDate(cookieDate);
        session.save(UsersEntity);
        session.getTransaction().commit();
        session.close();

        Session session2 = HibernateUtil.getSessionFactory().openSession();
        session2.beginTransaction();
        ResultImagesEntity ResultImagesEntity = new ResultImagesEntity();
        ResultImagesEntity.setOriginalImagesByOriginalImage(OriginalImagesEntity);
        ResultImagesEntity.setContextImagesByContextImage(ContextImagesEntity);
        Date date = new Date();
        ResultImagesEntity.setCreationDate(date);
        ResultImagesEntity.setUsersByUser(UsersEntity);
        ResultImagesEntity.setPrivateStatus((byte)1);
        ResultImagesEntity.setResultImage(b);

        session2.save(ResultImagesEntity);

        session2.getTransaction().commit();
        session2.close();

        Session session3 = HibernateUtil.getSessionFactory().openSession();
        session3.beginTransaction();
        ImageEmotionsEntity ImageEmotionsEntity = new ImageEmotionsEntity();
        ImageEmotionsEntity.setResultImagesByImage(ResultImagesEntity);
        ImageEmotionsEntity.setArtisticCount(1);
        ImageEmotionsEntity.setBeautifulCount(1);
        ImageEmotionsEntity.setFunnyCount(1);
        ImageEmotionsEntity.setSadCount(3);
        ImageEmotionsEntity.setScaryCount(0);
        ImageEmotionsEntity.setUglyCount(1);

        session3.save(ImageEmotionsEntity);

        session3.getTransaction().commit();
        session3.close();

        return "greeting";
    }




}