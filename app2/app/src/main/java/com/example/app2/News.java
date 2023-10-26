package com.example.app2;

import java.io.Serializable;

/**
 * @author 86186
 */
public class News implements Serializable {
    private String title;
    private int imageId;
    private String content;

    News() {
    }

    public News(String name, int imageId, String content) {
        this.title = name;
        this.imageId = imageId;
        this.content = content;
    }


    public String getName() {
        return title;
    }

    public String getTitle() {
        return title;
    }

    public int getImageId() {
        return imageId;
    }


    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setTitle(String s) {
        this.title = s;
    }
}
