package com.example.HelloWorld;

public class ImageStorage {
    public byte[] Image;
    private static ImageStorage ourInstance = new ImageStorage();

    public static ImageStorage getInstance() {
        return ourInstance;
    }

    private ImageStorage() {
        Image = null;
    }
}
