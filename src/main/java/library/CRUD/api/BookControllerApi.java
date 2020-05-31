package library.CRUD.api;

import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.Operation;
import javassist.NotFoundException;
import library.CRUD.model.Book;
import library.CRUD.model.enumeration.BookStatus;
import library.CRUD.service.BookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;

import static org.springframework.http.CacheControl.noCache;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;
import static org.springframework.http.ResponseEntity.ok;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.PATCH;

@Slf4j
@RestController
public class BookControllerApi {

    private static final String LIMIT = "limit";
    private static final String LIBRARY_PATH = "/library/";
    private static final String BOOK_STATUS = "book.bookStatus";
    private static final String BOOK_PATH_VARIABLE = "bookId";



    @Autowired
    BookService bookService;

    @RequestMapping(value = LIBRARY_PATH,
            produces = APPLICATION_JSON_UTF8_VALUE,
            consumes = APPLICATION_JSON_UTF8_VALUE,
            method = RequestMethod.POST)
    public ResponseEntity<Book> createBook(
            @Valid
            @RequestBody
                    Book book
    ) throws Exception {

        try {
           Book newBook = bookService.saveBook(book);
           return ResponseEntity.created(URI.create(book.getHref())).body(newBook);

        } catch (Exception e){
            log.error("Couldn't serialize response for content type" + APPLICATION_JSON.toString());
            throw e;
        }
    }




//    @GetMapping("book/{id}")
//    public Book getBook(@PathVariable String id){
//        return bookService.getBook(id);
//    }

//    @PostMapping("book/")
//    public Book addBook(Book book){
//        return bookService.saveBook(book);
//    }


    @RequestMapping(value = LIBRARY_PATH,
            produces = APPLICATION_JSON_UTF8_VALUE,
            consumes = {APPLICATION_JSON_UTF8_VALUE, "*"},
            method = GET)
    public ResponseEntity<List<Book>> listBooks(
            @Valid
            @RequestParam(value = LIMIT, required = false)
                    Integer limit,
            @Valid
            @RequestParam(value = BOOK_STATUS, required = false)
                    BookStatus bookStatus
    ) throws HttpMediaTypeNotAcceptableException, ServletException {
        try {
            List<Book> books = bookService.getAllBooks(
            );
            if (books.isEmpty()) {
                return ResponseEntity
                        .noContent()
                        .cacheControl(noCache())
                        .build();
            }

            return ok()
                    .cacheControl(noCache())
                    .body(books);
        } catch (Exception e) {
            log.error("List books: ", e);
            throw e;
        }
    }


    @RequestMapping(value = LIBRARY_PATH + "{" + BOOK_PATH_VARIABLE + "}",
            produces = APPLICATION_JSON_UTF8_VALUE,
            consumes = APPLICATION_JSON_UTF8_VALUE,
            method = PATCH)
    public ResponseEntity<Book> patchShoppingCart(
            @PathVariable(BOOK_PATH_VARIABLE)
                    String id,
            @Valid
            @RequestBody
                    Book newBook
    ) throws ServletException, NotFoundException {
        try {
            Book updatedCart = bookService.updateBook(id, newBook);

            return ok().cacheControl(noCache()).body(updatedCart);

        } catch (Exception e) {
            log.error("Order patching error.", e);
            throw e;
        }
    }

    @Operation(description = "Retrieves a 'Book' by Id",
            method = "GET")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Ok",response = Book.class),
            @ApiResponse(code = 400, message = "Bad Request", response = Error.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = Error.class),
            @ApiResponse(code = 403, message = "Forbidden", response = Error.class),
            @ApiResponse(code =404, message = "Not Found", response = Error.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = Error.class)
    }
    )
    @RequestMapping(value = LIBRARY_PATH + "{" + BOOK_PATH_VARIABLE + "}",
            produces = APPLICATION_JSON_UTF8_VALUE,
            consumes = {APPLICATION_JSON_UTF8_VALUE, "*"},
            method = GET)
    public ResponseEntity<Book> retriveBook(
            @PathVariable(BOOK_PATH_VARIABLE)
                    String id
    ) throws ServletException, NotFoundException {
        Book book;
        HttpStatus status = OK;

        try {
            book = bookService.getBook(id);
            return ok()
                    .cacheControl(noCache())
                    .body(book);
        } catch (Exception e) {
            log.error("Unable to get shopping cart with id: " + id, e);
            throw e;
        }
    }
}
