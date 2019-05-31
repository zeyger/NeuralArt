package ru.sstu.se20.controllers;

import ru.sstu.se20.DAL.DAO.*;
import ru.sstu.se20.DAL.Entities.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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

        //---This code is creating new records in DB---

        AbstractHibernateDAO DAO = new ContextImagesDAO();
        ContextImagesEntity ContextImagesEntity = new ContextImagesEntity();
        byte [] b = {1, 2, 3};
        ContextImagesEntity.setImage(b);
        DAO.create(ContextImagesEntity);

        DAO = new OriginalImagesDAO();
        OriginalImagesEntity OriginalImagesEntity = new OriginalImagesEntity();
        OriginalImagesEntity.setImage(b);
        DAO.create(OriginalImagesEntity);

        DAO = new UsersDAO();
        UsersEntity UsersEntity = new UsersEntity();
        Cookie cookieName = new Cookie("neuralartId", UUID.randomUUID().toString());
        String cookie = cookieName.getValue();
        Date cookieDate = new Date();
        UsersEntity.setCookie(cookie);
        UsersEntity.setCookieCreationDate(cookieDate);
        DAO.create(UsersEntity);

        DAO = new ResultImagesDAO();
        ResultImagesEntity ResultImagesEntity = new ResultImagesEntity();
        ResultImagesEntity.setOriginalImagesByOriginalImage(OriginalImagesEntity);
        ResultImagesEntity.setContextImagesByContextImage(ContextImagesEntity);
        Date date = new Date();
        ResultImagesEntity.setCreationDate(date);
        ResultImagesEntity.setUsersByUser(UsersEntity);
        ResultImagesEntity.setPrivateStatus((byte)1);
        ResultImagesEntity.setResultImage(b);

        DAO.create(ResultImagesEntity);

        DAO = new ImageEmotionsDAO();
        ImageEmotionsEntity ImageEmotionsEntity = new ImageEmotionsEntity();
        ImageEmotionsEntity.setResultImagesByImage(ResultImagesEntity);
        ImageEmotionsEntity.setArtisticCount(1);
        ImageEmotionsEntity.setBeautifulCount(1);
        ImageEmotionsEntity.setFunnyCount(1);
        ImageEmotionsEntity.setSadCount(3);
        ImageEmotionsEntity.setScaryCount(0);
        ImageEmotionsEntity.setUglyCount(1);
        DAO.create(ImageEmotionsEntity);

        //This code is select User by cookie
        DAO = new UsersDAO();
        cookie = "1a91132c-0f0f-4a51-9af0-b1ea78c06592";
        UsersEntity findUser = ((UsersDAO) DAO).getByCookie(cookie);


        return "greeting";
    }




}