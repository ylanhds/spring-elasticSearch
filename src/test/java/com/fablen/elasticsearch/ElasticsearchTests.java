package com.fablen.elasticsearch;


import com.fablen.elasticsearch.entity.Book;
import com.fablen.elasticsearch.repository.BookRepository;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @author zhangbaosheng
 */
@SpringBootTest
class ElasticsearchTests {
    @Autowired
    BookRepository bookRepository;

    @Test
    void testDocument() {

        List<Book> books = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            Book book = new Book();
            book.setId(i + 20000);
            book.setName(UUID.randomUUID().toString());
            books.add(book);
        }
        bookRepository.saveAll(books);

    }

    // 定义一个全局的记录器，通过LoggerFactory获取
    private final static Logger log = LoggerFactory.getLogger(Test.class);

    @Before
    public void setUp() {
    }

    @Test
    public void test() {
        for (int i = 0; i < 10; i++) {
            log.trace("trace 成功了"+i);
            log.debug("debug 成功了"+i);
            log.info("info 成功了"+i);
            log.warn("warn 成功了"+i);
            log.error("error 成功了"+i);
            log.info("你好啊e"+i);
            log.warn("This is a warn message!"+i);
            log.error("This is error message!"+i);
        }
    }
}
