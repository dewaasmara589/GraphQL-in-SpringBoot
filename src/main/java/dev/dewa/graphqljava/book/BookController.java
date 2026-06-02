package dev.dewa.graphqljava.book;

import dev.dewa.graphqljava.author.Author;
import dev.dewa.graphqljava.author.AuthorRepository;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

@Controller
public class BookController {
    private final AuthorRepository authorRepository;
    private static final Logger log = LoggerFactory.getLogger(BookController.class);
    private final BookRepository bookRepository;

    public BookController(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    // Cara 1 like Query in schema.graphqls
    // @SchemaMapping(typeName = "Query", field = "books")

    // Cara 2
    // @QueryMapping(name = "books")
    // public List<Book> findAll(){

    // Cara 3 funcation name equals with Query in schema.graphqls
    @QueryMapping
    public List<Book> books() {
        return bookRepository.findAll();
    }

    @QueryMapping
    public Optional<Book> book(@Argument Long id) {
        return bookRepository.findById(id);
    }

    @MutationMapping
    public Book addBook(@Argument BookInput bookInput){
        Author author = authorRepository.findById(bookInput.authorId()).orElseThrow();
        var book = new Book();
        book.setTitle(bookInput.title());
        book.setAuthor(author);
        return bookRepository.save(book);
    }
}
