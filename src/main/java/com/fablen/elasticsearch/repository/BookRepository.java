package com.fablen.elasticsearch.repository;


import com.fablen.elasticsearch.entity.Book;
import org.springframework.data.repository.CrudRepository;

/**
 * @author zhangbaosheng
 */
public interface BookRepository extends CrudRepository<Book, Long> {

}
