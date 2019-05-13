package com.mobitant.firebook;


public class BookCompo {
    private String image_url;
    private String book_text;
    private String book_price;
    private String book_publish;
    private String book_author;
    private String barcode;


    public BookCompo(String image_url, String book_text, String book_price, String book_publish, String book_author, String barcode) {
        this.image_url = image_url;
        this.book_text = book_text;
        this.book_price = book_price;
        this.book_publish = book_publish;
        this.book_author = book_author;
        this.barcode = barcode;
    }


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

    public String getBook_author() {
        return book_author;
    }

    public String getBarcode() {
        return barcode;
    }


}
