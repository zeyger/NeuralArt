package ru.sstu.se20.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.sstu.se20.DAL.DAO.ResultImagesDAO;
import ru.sstu.se20.DAL.DAO.ImageEmotionsDAO;
import ru.sstu.se20.DAL.Entities.ResultImagesEntity;
import ru.sstu.se20.DTO.GalleryDto;
import ru.sstu.se20.DAL.Entities.ImageEmotionsEntity;

import java.util.*;

@Controller
public class GalleryController {

    public GalleryController() {
    }

    @RequestMapping("/gallery")
    public String getArts(Model model) {

        ResultImagesDAO dao = new ResultImagesDAO();
        List<ResultImagesEntity> resultImages = dao.getAll();
        List<GalleryDto> dto = new ArrayList<>();

        for (ResultImagesEntity image : resultImages) {
            if (image.getResultImage() == null || image.getContextImagesByContextImage() == null || image.getOriginalImagesByOriginalImage() == null || image.getPrivateStatus() != 1) {
                continue;
            }

            //Take information about emotions from the database
            int artisticCount = 0, beautifulCount = 0, funnyCount = 0, sadCount = 0, scaryCount = 0, uglyCount = 0;
            ImageEmotionsDAO emotionsdao = new ImageEmotionsDAO();
            Collection<ImageEmotionsEntity> emotions = image.getImageEmotionsById();
            for (ImageEmotionsEntity emotion : emotions) {
                artisticCount = emotion.getArtisticCount();
                beautifulCount = emotion.getBeautifulCount();
                funnyCount = emotion.getFunnyCount();
                sadCount = emotion.getSadCount();
                scaryCount = emotion.getScaryCount();
                uglyCount = emotion.getUglyCount();
            }

            int id = image.getId();
            String resultImage = "data:image/png;base64," + Base64.getEncoder().encodeToString(image.getResultImage());
            java.util.Date creationDate = image.getCreationDate();
            String originalImages = "data:image/png;base64," + Base64.getEncoder().encodeToString(image.getOriginalImagesByOriginalImage().getImage());
            String contextImages = "data:image/png;base64," + Base64.getEncoder().encodeToString(image.getContextImagesByContextImage().getImage());
            dto.add(new GalleryDto(id, creationDate, originalImages, contextImages, resultImage, artisticCount, beautifulCount, funnyCount, sadCount, scaryCount,uglyCount));
        }
        model.addAttribute("images", dto);
        return "gallery";
    }

}
