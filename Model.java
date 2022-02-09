package com.ourlove.testingapp;

public class Model {


    private String title;
    private String content;
    private String key;


    public Model(String title,String content,String key){
        this.setTitle(title);
        this.setContent(content);
        this.setKey(key);
    }

    public Model(){

    }



    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
