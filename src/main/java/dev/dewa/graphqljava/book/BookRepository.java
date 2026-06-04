package dev.dewa.graphqljava.book;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {

    Collection<?> findAllByTitleContainsIgnoreCase(String text);

    List<Book> findByAuthorIdIn(List<Long> authorsIds);
    
}
