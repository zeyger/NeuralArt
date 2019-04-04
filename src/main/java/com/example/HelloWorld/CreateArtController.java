package com.example.HelloWorld;


import org.apache.commons.io.FileUtils;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;



import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;



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
        byte[] fileContent = files[0].getBytes();
        String encodedString = Base64.getEncoder().encodeToString(fileContent);

        byte[] decodedBytes = Base64.getDecoder().decode(encodedString);
        FileUtils.writeByteArrayToFile(new File(files[0].getOriginalFilename()), decodedBytes);
        //String name = files[0].getOriginalFilename();
        //name = files[1].getOriginalFilename();


        HttpClient httpClient = new DefaultHttpClient();
        try {
            HttpPost request = new HttpPost("http://127.0.0.1:5000/submit_image/123");
            StringEntity params = new StringEntity("{\"image\":\"" + encodedString + "\",\"style\":\""+encodedString +"\"}");
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


        return "uploadview";
    }

}
