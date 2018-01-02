package library;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public final class BookRepositoryTest {

    private String isbn = "978-0-356-50429-2";
    private String title = "Babylon's Ashes";
    private String author = "james S.A. Corey";

    @Test
    public void getBooksWithEmptyRepository() throws Exception {
        BookRepository sut = new BookRepository();
        assertEquals(0, sut.getBooks().size());
    }

    @Test(expected = NullPointerException.class)
    public void addBookHandlesNullPointer() throws Exception {
        BookRepository sut = new BookRepository();
        sut.addBook(null);
    }

    @Test
    public void booksCanBeAddedAndRetrieved() throws Exception {
        BookRepository sut = new BookRepository();
        Book book = new Book();
        book.isbn = isbn;
        book.title = title;
        book.author = author;
        sut.addBook(book);
        List<Book> expected = new ArrayList<>();
        expected.add(book);
        assertEquals(expected, sut.getBooks());
    }

}