package dev.dewa.graphqljava.author;

import dev.dewa.graphqljava.book.BookRepository;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.graphql.data.method.annotation.BatchMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

import dev.dewa.graphqljava.DataLoader;
import dev.dewa.graphqljava.book.Book;

@Controller
public class AuthorController {
    private final BookRepository bookRepository;
    private static final Logger log = LoggerFactory.getLogger(AuthorController.class);
    private final AuthorRepository authorRepository;
    private final DataLoader dataLoader;

    public AuthorController(AuthorRepository authorRepository, DataLoader dataLoader, BookRepository bookRepository) {
        this.authorRepository = authorRepository;
        this.dataLoader = dataLoader;
        this.bookRepository = bookRepository;
    }

    @QueryMapping
    public List<Author> authors() {
        return authorRepository.findAll();
    }

    @SchemaMapping
    // pass to parent in paramete
    public List<Book> books(Author author) throws InterruptedException {
        // Microservice for author books here
        log.info("Getting books for {}", author);
        Thread.sleep(1000);
        return new ArrayList<>();
    }

    // @BatchMapping
    // // ono by one steps big deal for small data
    // public List<List<Book>> books(List<Author> authors){
    // log.info("Getting books for {}", authors.size());

    // //Get all authors id
    // List<Long> authorsIds = authors.stream()
    // .map(Author::getId)
    // .toList();

    // //make single query or fetch
    // List<Book> allBooks = bookRepository.findByAuthorIdIn(authorsIds);
    // Map<Long, List<Book>> booksByAuthorId =
    // allBooks.stream().collect(Collectors.groupingBy(book->book.getAuthor().getId()));

    // return
    // authors.stream().map(author->booksByAuthorId.getOrDefault(author.getId(),
    // Collections.emptyList())).toList();
    // }
}
