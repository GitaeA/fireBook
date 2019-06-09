package com.mobitant.firebook;

public class MyCompo {
    private String image_url;  //책이미지

    public String getImage_url() {
        return image_url;
    }

    public String getBook_text() {
        return book_text;
    }

    public String getBook_price() {
        return book_price;
    }

    public String getBook_publish() {
        return book_publish;
    }

    private String book_text; //책제목
    private String book_price;   //가격
    private String book_publish;

    //마이페이에서의 판매자 번호, 내가 올린건 memo
    public MyCompo(String image_url, String book_text, String book_price, String book_publish) {
        this.image_url = image_url;
        this.book_text = book_text;
        this.book_price = book_price;
        this.book_publish = book_publish;
    }


}