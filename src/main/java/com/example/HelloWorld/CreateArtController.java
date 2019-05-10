package com.example.HelloWorld;


import org.apache.commons.io.FileUtils;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;





import java.io.File;
import java.io.IOException;
import java.util.Base64;

@Controller
public class CreateArtController {

    @RequestMapping("/createart")
    public String UploadPage(Model model) {
        return "uploadview";
    }

    @RequestMapping("/upload")
    public String upload(Model model, @RequestParam("image")MultipartFile[] files) throws IOException {


        byte[] imageFileContent = files[0].getBytes();
        String imageEncodedString = Base64.getEncoder().encodeToString(imageFileContent);

        byte[] styleFileContent = files[1].getBytes();
        String styleEncodedString = Base64.getEncoder().encodeToString(styleFileContent);

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
