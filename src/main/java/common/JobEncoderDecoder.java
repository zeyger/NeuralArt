package common;


import DTO.JobDecoderDTO;
import DTO.JobEncoderDTO;
import com.google.gson.Gson;

import java.util.Base64;

public class JobEncoderDecoder {
    private static Gson gson = new Gson();
    /**
     * @param jobId        - number of job
     * @param contentImage - Content image, bytes
     * @param styleImage   - Style image, bytes
     * @return Json serialized with same fields as input, but in BASE64(except for jobId)
     */
    public static String encode(int jobId, byte[] contentImage, byte[] styleImage) {
        String contentImageBase64 = Base64.getEncoder().encodeToString(contentImage);
        String styleImageBase64 = Base64.getEncoder().encodeToString(styleImage);
        JobEncoderDTO jobEncoderDTO = new JobEncoderDTO(jobId, contentImageBase64, styleImageBase64);
        String jsonString = gson.toJson(jobEncoderDTO);
        return jsonString; // jsonString
    }


    /**
     * @param jsonString - Json serialized with fields: jobId, contentImage, styleImage
     * @return Object with fields int jobId, byte[] resultImage
     */
    public static JobDecoderDTO decode(String jsonString) {
        JobDecoderDTO jobDecoderDTO = gson.fromJson(jsonString, JobDecoderDTO.class);
        return jobDecoderDTO;  // result - объект с полями int jobId, byte[] resultImage
    }
}
