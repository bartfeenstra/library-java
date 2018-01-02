package library;

import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

/**
 * Provides a book repository.
 */
@Service
final class BookRepository {

    /**
     * The stored books.
     */
    private List<Book> books = new ArrayList<>();

    /**
     * Adds a book.
     */
    public void addBook(@NotNull Book book) {
        if (null == book) {
            throw new NullPointerException();
        }
        books.add(book);
    }

    /**
     * Gets all books.
     */
    public List<Book> getBooks() {
        return books;
    }

}