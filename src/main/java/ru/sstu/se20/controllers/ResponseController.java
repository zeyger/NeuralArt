package ru.sstu.se20.controllers;


import org.apache.commons.io.FileUtils;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;







import java.io.File;
import java.io.IOException;
import java.util.Base64;
import java.util.HashMap;

@Controller
public class ResponseController {

    @PostMapping(value = "/response")
    public String upload(Model model, @RequestBody() HashMap<String, String> json) throws IOException {
//        byte[] imageFileContent = files[0].getBytes();
//        String imageEncodedString = Base64.getEncoder().encodeToString(imageFileContent);
//
//        byte[] styleFileContent = files[1].getBytes();
//        String styleEncodedString = Base64.getEncoder().encodeToString(styleFileContent);


        System.out.println("RABOTAET" + json.get("output"));


        byte[] decodedBytes = Base64.getDecoder().decode(json.get("output"));
        FileUtils.writeByteArrayToFile(new File("result_name.png"), decodedBytes);

        return "uploadview";
    }

}
