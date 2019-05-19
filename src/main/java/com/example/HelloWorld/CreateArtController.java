package com.example.HelloWorld;


import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;


import utils.HibernateUtil;
import DAL.*;
import common.JobEncoderDecoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.hibernate.Session;
import java.util.Date;
import java.io.File;
import java.io.IOException;
import java.util.Base64;
import DTO.JobDecoderDTO;
import DTO.JobEncoderDTO;
import DAL.Queue;

@Controller
public class CreateArtController {

    @RequestMapping("/createart")
    public String UploadPage(Model model) {
        return "uploadview";
    }

    @RequestMapping("/upload")
    public String upload(Model model, @RequestParam("image")MultipartFile[] files) throws IOException {


        byte[] imageFileContent = files[0].getBytes();
        //String imageEncodedString = Base64.getEncoder().encodeToString(imageFileContent);

        byte[] styleFileContent = files[1].getBytes();
        //String styleEncodedString = Base64.getEncoder().encodeToString(styleFileContent);


        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        OriginalImagesEntity OriginalImagesEntity = new OriginalImagesEntity();

        OriginalImagesEntity.setImage(imageFileContent);

        session.save(OriginalImagesEntity);

        session.getTransaction().commit();
        session.close();


        Session session2 = HibernateUtil.getSessionFactory().openSession();
        session2.beginTransaction();

        ContextImagesEntity ContextImagesEntity = new ContextImagesEntity();

        ContextImagesEntity.setImage(styleFileContent);

        session2.save(ContextImagesEntity);

        session2.getTransaction().commit();
        session2.close();


        Session session3 = HibernateUtil.getSessionFactory().openSession();
        session3.beginTransaction();

        UsersEntity UsersEntity = new UsersEntity();
        UsersEntity.setEmail("vasyaPupkin@gmail.com");
        UsersEntity.setPassword("12345");
        UsersEntity.setDefaultPrivate((byte)1);

        session3.save(UsersEntity);

        session3.getTransaction().commit();
        session3.close();


        Session session4 = HibernateUtil.getSessionFactory().openSession();
        session4.beginTransaction();

        ResultImagesEntity ResultImagesEntity = new ResultImagesEntity();
        ResultImagesEntity.setOriginalImagesByOriginalImage(OriginalImagesEntity);
        ResultImagesEntity.setContextImagesByContextImage(ContextImagesEntity);
        Date date = new Date();
        ResultImagesEntity.setCreationDate(date);
        ResultImagesEntity.setUsersByUser(UsersEntity);
        ResultImagesEntity.setPrivateStatus((byte)1);
        ResultImagesEntity.setResultImage(null);

        session4.save(ResultImagesEntity);

        session4.getTransaction().commit();
        session4.close();

        JobEncoderDecoder JobEncoderDecoderupload = new JobEncoderDecoder();
        String uploadString = JobEncoderDecoderupload.encode(ResultImagesEntity.getId(), imageFileContent, styleFileContent);
        Queue Queue = new Queue();
        Queue.putPending(uploadString);

        //byte[] decodedBytes = Base64.getDecoder().decode(imageEncodedString);
        //FileUtils.writeByteArrayToFile(new File(files[0].getOriginalFilename()), decodedBytes);
        //String name = files[0].getOriginalFilename();
        //name = files[1].getOriginalFilename();

        /*
        HttpClient httpClient = new DefaultHttpClient();
        try {
            HttpPost request = new HttpPost("http://127.0.0.1:5000/submit_image/123");
            StringEntity params = new StringEntity("{\"image\":\"" + imageEncodedString + "\",\"style\":\""+styleEncodedString +"\"}");
            request.addHeader("content-type", "application/json");
            request.addHeader("Accept","application/json");
            request.setEntity(params);
            HttpResponse response = httpClient.execute(request);

            // handle response here...
        }catch (Exception ex) {
            System.out.println("reeeeeeeeeeeeeeee");
        } finally {
            httpClient.getConnectionManager().shutdown();
        }
    */

        return "uploadview";
    }

}
