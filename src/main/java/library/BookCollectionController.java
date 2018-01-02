package library;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping("/books")
final class BookCollectionController {

    @Autowired
    private BookRepository books;

    /**
     * Gets all books.
     */
    @GetMapping(produces = "application/json")
    public @ResponseBody
    List<Book> getBooks() {
        return books.getBooks();
    }

    /**
     * Adds a book.
     */
    @PostMapping(consumes = "application/json", produces = "application/json")
    public @ResponseBody
    @NotNull
    Book addBook(@RequestBody @Valid @NotNull Book book) {
        books.addBook(book);
        return book;
    }

}