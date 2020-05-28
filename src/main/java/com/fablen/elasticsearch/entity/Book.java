package com.fablen.elasticsearch.entity;

import lombok.Data;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;

/**
 * @author zhangbaosheng
 */
@Data
@Document(indexName = "index_book")
public class Book {
    @Field
    private int id;
    private String name;
}
