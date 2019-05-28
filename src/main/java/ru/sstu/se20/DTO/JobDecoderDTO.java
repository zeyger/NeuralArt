package ru.sstu.se20.DTO;

public class JobDecoderDTO {
    private int jobId;
    private String resultImage;
    private byte[] bytesResultImage;

    public JobDecoderDTO(int jobId, String resultImage) {
        this.jobId = jobId;
        this.resultImage = resultImage;
    }

    public int getJobId() {
        return jobId;
    }

    public void setJobId(int jobId) {
        this.jobId = jobId;
    }

    public String getResultImage() {
        return resultImage;
    }

    public void setResultImage(String resultImage) {
        this.resultImage = resultImage;
    }

    public byte[] getBytesResultImage() {
        return bytesResultImage;
    }

    public void setBytesResultImage(byte[] bytesResultImage) {
        this.bytesResultImage = bytesResultImage;
    }
}
