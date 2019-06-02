package ru.sstu.se20.DTO;

import ru.sstu.se20.DAL.Entities.ImageEmotionsEntity;

import java.util.Collection;
import java.util.Date;

public class YourArtDTO {

    public YourArtDTO() {

    }

    public YourArtDTO(int id, byte privateStatus, Date creationDate, String originalImages, String contextImages, String resultImage, int artisticCount, int beautifulCount, int funnyCount, int sadCount, int scaryCount, int uglyCount) {
        this.id = id;
        this.privateStatus = privateStatus;
        this.creationDate = creationDate;
        this.originalImage = originalImages;
        this.contextImage = contextImages;
        this.resultImage = resultImage;

        this.artisticCount = artisticCount;
        this.beautifulCount = beautifulCount;
        this.funnyCount = funnyCount;
        this.sadCount = sadCount;
        this.scaryCount = scaryCount;
        this.uglyCount = uglyCount;
    }

    public int id;
    public byte privateStatus;
    public java.util.Date creationDate;
    public String originalImage;
    public String contextImage;
    public String resultImage;
    public int artisticCount;
    public int beautifulCount;
    public int funnyCount;
    public int sadCount;
    public int scaryCount;
    public int uglyCount;
}
