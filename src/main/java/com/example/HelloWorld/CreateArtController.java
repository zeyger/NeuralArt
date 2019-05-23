package com.example.HelloWorld;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import utils.HibernateUtil;
import DAL.*;
import common.JobEncoderDecoder;
import org.hibernate.Session;
import java.util.Date;
import java.io.IOException;
import java.util.UUID;

import DAL.Queue;

import javax.servlet.http.Cookie;

import org.springframework.web.bind.annotation.CookieValue;
import javax.servlet.http.HttpServletResponse;

@Controller
public class CreateArtController {

    @RequestMapping("/createart")
    public String UploadPage(Model model) {
        return "uploadview";
    }

    @RequestMapping("/upload")
    public String upload(Model model, @RequestParam("image")MultipartFile[] files, @CookieValue(value = "neuralartId", required = false) Cookie cookieName, HttpServletResponse response) throws IOException {

        //Image conversion to binary code
        byte[] imageFileContent = files[0].getBytes();
        byte[] styleFileContent = files[1].getBytes();

        if (cookieName == null) {
            cookieName = new Cookie("neuralartId", UUID.randomUUID().toString());
            response.addCookie(cookieName);
        }

        //Record the initial image in the database
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        OriginalImagesEntity OriginalImagesEntity = new OriginalImagesEntity();
        OriginalImagesEntity.setImage(imageFileContent);
        session.save(OriginalImagesEntity);
        session.getTransaction().commit();
        session.close();

        //Record image style in database
        Session session2 = HibernateUtil.getSessionFactory().openSession();
        session2.beginTransaction();
        ContextImagesEntity ContextImagesEntity = new ContextImagesEntity();
        ContextImagesEntity.setImage(styleFileContent);
        session2.save(ContextImagesEntity);
        session2.getTransaction().commit();
        session2.close();

        Session sessions = HibernateUtil.getSessionFactory().openSession();
        sessions.beginTransaction();

        UsersEntity UsersEntity = new UsersEntity();
        String cookie = cookieName.toString();
        Date cookieDate = new Date();
        UsersEntity.setCookie(cookie);
        UsersEntity.setCookieCreationDate(cookieDate);
        sessions.save(UsersEntity);
        sessions.getTransaction().commit();
        sessions.close();

        //Write empty result image
        Session session3 = HibernateUtil.getSessionFactory().openSession();
        session3.beginTransaction();
        ResultImagesEntity ResultImagesEntity = new ResultImagesEntity();
        ResultImagesEntity.setOriginalImagesByOriginalImage(OriginalImagesEntity);
        ResultImagesEntity.setContextImagesByContextImage(ContextImagesEntity);
        Date date = new Date();
        ResultImagesEntity.setCreationDate(date);
        ResultImagesEntity.setUsersByUser(UsersEntity);
        ResultImagesEntity.setPrivateStatus((byte)1);
        ResultImagesEntity.setResultImage(null);
        session3.save(ResultImagesEntity);
        session3.getTransaction().commit();
        session3.close();

        //Transfer 2 images and id result image to a queue
        JobEncoderDecoder JobEncoderDecoderupload = new JobEncoderDecoder();
        String uploadString = JobEncoderDecoderupload.encode(ResultImagesEntity.getId(), imageFileContent, styleFileContent);
        Queue Queue = new Queue();
        Queue.putPending(uploadString);

        return "uploadview";
    }

}
