package library;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/books")
final class BookCollectionController {

    /**
     * Gets all books.
     */
    @GetMapping(produces = "application/json")
    public String getBooks() {
        return "[]";
    }

}