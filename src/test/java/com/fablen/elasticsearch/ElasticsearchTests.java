package com.fablen.elasticsearch;


import com.fablen.elasticsearch.entity.Book;
import com.fablen.elasticsearch.repository.BookRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
/**
 *
 * @author zhangbaosheng
 */
@SpringBootTest
class ElasticsearchTests {
    @Autowired
    BookRepository bookRepository;
    @Test
    void testDeleteDocument()  {
        Book book= new Book();
        book.setId(10);
        book.setName("ylanhds");
        bookRepository.save(book);
    }


}
