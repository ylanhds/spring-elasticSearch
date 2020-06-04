package com.fablen.elasticsearch.controller;

import com.fablen.elasticsearch.entity.Book;
import com.fablen.elasticsearch.repository.BookRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhangbaosheng
 */
@RestController
public class BookController {
    // 定义一个全局的记录器，通过LoggerFactory获取
    private final static Logger log = LoggerFactory.getLogger(BookController.class);
    @Autowired
    BookRepository bookRepository;

    @GetMapping("/book")
    public Iterable<Book> hello() {
        Iterable<Book> all = bookRepository.findAll();
        log.info("返回数据 "+all);
        return bookRepository.findAll();
    }
}
