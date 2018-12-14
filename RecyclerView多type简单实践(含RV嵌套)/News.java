package com.example.guikai.myapplication.bean;

import java.util.List;

public class News {
    private List<String> imgUrl;  // 图片地址
    private String content;//新闻内容
    private String source;//新闻来源
    private String time;//发布时间
    private String link;//点击链接

    public News(List<String> imgUrl, String content, String source, String time, String link) {
        this.imgUrl = imgUrl;
        this.content = content;
        this.source = source;
        this.time = time;
        this.link = link;
    }

    public List<String> getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(List<String> imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
