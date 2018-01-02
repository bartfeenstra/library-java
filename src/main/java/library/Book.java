package library;

import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;

/**
 * Provides a library book.
 */
final class Book {

    /**
     * The ISBN.
     */
    @ApiModelProperty(required = true)
    @NotNull
    public String isbn;

    /**
     * The human-readable title.
     */
    @ApiModelProperty(required = true)
    @NotNull
    public String title;

    /**
     * The book's human-readable author name.
     */
    @ApiModelProperty(required = true)
    @NotNull
    public String author;

}