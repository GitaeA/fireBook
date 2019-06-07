package com.mobitant.firebook;

public class Books {

    private String name;
    private String image_url;
    private String publisher;
    private String author;
    private String price;
    private String state;
    private String deliver ; //택배거래
    public Books(String name, String image_url, String publisher, String author, String price, String state, String deliver) {
        this.name = name;
        this.image_url = image_url;
        this.publisher = publisher;
        this.author = author;
        this.price = price;
        this.state = state;
        this.deliver = deliver ;
    }

    public String getDeliever(){return deliver;}
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
