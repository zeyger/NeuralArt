package ru.sstu.se20.controllers;

import org.hibernate.Session;
import org.springframework.web.bind.annotation.*;
import ru.sstu.se20.DAL.DAO.ResultImagesDAO;
import ru.sstu.se20.DAL.Entities.ContextImagesEntity;
import ru.sstu.se20.DAL.Entities.ImageEmotionsEntity;
import ru.sstu.se20.DAL.Entities.ResultImagesEntity;
import ru.sstu.se20.utils.HibernateUtil;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@RestController
public class EmotionController {


    @RequestMapping(value = "/emotions", method = RequestMethod.POST)
    public String addEmotion(@RequestParam int id, @RequestParam int emotionIndex) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        ResultImagesDAO dao = new ResultImagesDAO();
        ResultImagesEntity entity = dao.getById(id);
        List<ImageEmotionsEntity> emotions = new ArrayList(entity.getImageEmotionsById());
        ImageEmotionsEntity emotion;
        if (emotions == null || emotions.isEmpty()) {
            emotions = new ArrayList<>();
            ImageEmotionsEntity imageEmotionsEntity = new ImageEmotionsEntity();
            imageEmotionsEntity.setResultImagesByImage(entity);
            emotion = imageEmotionsEntity;
            session.save(imageEmotionsEntity);
            session.getTransaction().commit();
            session.close();
        }
        else {
            emotion = emotions.get(0);
        }
        int newCount = -1;
        if (emotionIndex == 0) {
            newCount = emotion.getArtisticCount() + 1;
            emotion.setArtisticCount(newCount);
        }
        if (emotionIndex == 1) {
            newCount = emotion.getBeautifulCount() + 1;
            emotion.setBeautifulCount(newCount);
        }
        if (emotionIndex == 2) {
            newCount = emotion.getFunnyCount() + 1;
            emotion.setFunnyCount(newCount);
        }
        if (emotionIndex == 3) {
            newCount = emotion.getSadCount() + 1;
            emotion.setSadCount(newCount);
        }
        if (emotionIndex == 4) {
            newCount = emotion.getScaryCount()  + 1;
            emotion.setScaryCount(newCount);
        }
        if (emotionIndex == 5) {
            newCount = emotion.getUglyCount() + 1;
            emotion.setUglyCount(newCount);
        }
        emotions.clear();
        emotions.add(emotion);
        entity.setImageEmotionsById(emotions);
        dao.update(entity);
        return "{\"id\":\"" + id + "\", \"emotionIndex\":\"" + emotionIndex + "\",\"count\":\""+ newCount +"\"}";
    }

}

