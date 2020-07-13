package com.fablen.elasticsearch.entity;

import lombok.Data;

/**
 * <p>
 * Description:
 * </p>
 *
 * @author ZhangBaoSheng
 * @version v1.0.0
 */
@Data
public class Book {
    private String bookName;
    private int bookPrice;

    public Book() {
    }

    public Book(String name, int price) {
        this.bookName = name;
        this.bookPrice = price;
    }
}
