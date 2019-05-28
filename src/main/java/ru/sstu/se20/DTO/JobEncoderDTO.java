package ru.sstu.se20.DTO;

public class JobEncoderDTO {
    private int jobId;
    private String contentImage;
    private String styleImage;

    public JobEncoderDTO(int jobId, String contentImage, String styleImage) {
        this.jobId = jobId;
        this.contentImage = contentImage;
        this.styleImage = styleImage;
    }

    public int getJobId() {
        return jobId;
    }

    public void setJobId(int jobId) {
        this.jobId = jobId;
    }

    public String getContentImage() {
        return contentImage;
    }

    public void setContentImage(String contentImage) {
        this.contentImage = contentImage;
    }

    public String getStyleImage() {
        return styleImage;
    }

    public void setStyleImage(String styleImage) {
        this.styleImage = styleImage;
    }
}
