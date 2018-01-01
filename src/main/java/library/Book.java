package library;

import io.swagger.annotations.ApiModelProperty;

/**
 * Provides a library book.
 *
 * @todo Find out how to disallow empty properties.
 */
final class Book {

    /**
     * The ISBN.
     */
    @ApiModelProperty(required = true)
    public String isbn;

    /**
     * The human-readable title.
     */
    @ApiModelProperty(required = true)
    public String title;

    /**
     * The book's human-readable author name.
     */
    @ApiModelProperty(required = true)
    public String author;

}