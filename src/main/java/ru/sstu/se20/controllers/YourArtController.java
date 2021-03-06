package ru.sstu.se20.controllers;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.thymeleaf.context.WebContext;
import ru.sstu.se20.DAL.DAO.ResultImagesDAO;
import ru.sstu.se20.DAL.DAO.UsersDAO;
import ru.sstu.se20.DAL.Entities.ResultImagesEntity;
import ru.sstu.se20.DAL.Entities.UsersEntity;
import ru.sstu.se20.DTO.YourArtDTO;
import ru.sstu.se20.common.ControllerUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.util.*;

@Controller
public class YourArtController {

    public YourArtController() {

    }

    @RequestMapping("/myarts")
    public String getArts(Model model, @CookieValue(value = "neuralartId", required = false) Cookie cookieName, HttpServletResponse response) {
        if (cookieName == null) {
            cookieName = new Cookie("neuralartId", UUID.randomUUID().toString());
            response.addCookie(cookieName);
        }
        UsersEntity user = ControllerUtils.getUser(cookieName.getValue());
        List<ResultImagesEntity> resultImages = new ArrayList<>();
        if (user.getResultImagesById() != null) {
            resultImages = new ArrayList<>(user.getResultImagesById());
        }
        List<YourArtDTO> dto = new ArrayList<>();

        for (ResultImagesEntity image : resultImages) {
            if (image.getResultImage() == null || image.getContextImagesByContextImage() == null || image.getOriginalImagesByOriginalImage() == null) {
                continue;
            }
            String resultImage = "data:image/png;base64," + Base64.getEncoder().encodeToString(image.getResultImage());
            int id = image.getId();
            byte privateStatus = image.getPrivateStatus();
            java.util.Date creationDate = image.getCreationDate();
            String originalImages = "data:image/png;base64," + Base64.getEncoder().encodeToString(image.getOriginalImagesByOriginalImage().getImage());
            String contextImages = "data:image/png;base64," + Base64.getEncoder().encodeToString(image.getContextImagesByContextImage().getImage());
            dto.add(new YourArtDTO(id, privateStatus, creationDate, originalImages, contextImages, resultImage));
        }
        model.addAttribute("images", dto);
        return "myarts";
    }
}
