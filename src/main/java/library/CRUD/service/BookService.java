package library.CRUD.service;

import javassist.NotFoundException;
import library.CRUD.model.Book;
import library.CRUD.model.enumeration.BookStatus;
import library.CRUD.repository.BookRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import javax.transaction.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static java.util.UUID.randomUUID;
import static library.CRUD.model.QBook.book;

@Slf4j
@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    @Transactional
    public Book saveBook(Book newBook) {
        newBook.setId(randomUUID().toString());

        if (newBook.getAuthor() != null) {
            newBook.getAuthor().setId(randomUUID().toString());
        }
        if (newBook.getCustomer() != null) {
            newBook.getCustomer().setId(randomUUID().toString());
        }
        return bookRepository.save(newBook);
    }

    public Book getBook(String id) throws NotFoundException {
        Optional<Book> book = bookRepository.findById(id);
        if (book.isPresent()) {
            return book.get();
        } else {
            throw new NotFoundException("Unexisting book id: " + id);
        }
    }

    @Transactional
    public void deleteBook(String id){
        bookRepository.deleteById(id);
    }

    @Transactional
    public Book updateBookStatus(String id, BookStatus bookStatus, Book newBook) throws NotFoundException {
        if (id != null && bookStatus != null && newBook!=null) {
            Book book = getBook(id);
            if(bookStatus == BookStatus.LOAN){
                book.setCustomer(newBook.getCustomer());
            }
            if(bookStatus == BookStatus.ACTIVE){
                book.setCustomer(null);
            }
            book.setStatus(bookStatus);
            return bookRepository.save(book);
        }
        return null;
    }

    @Transactional
    public Book updateBook(String id, Book updateBook) throws NotFoundException {
        if (id != null && updateBook != null) {
            Book book = getBook(id);
            book.setAuthor(updateBook.getAuthor());
            book.setCustomer(updateBook.getCustomer());
            book.setGenre(updateBook.getGenre());
            book.setHref(updateBook.getHref());
            book.setStatus(updateBook.getStatus());
            book.setTitle(updateBook.getTitle());
            return bookRepository.save(book);
        }
        return null;
    }

    public List<Book> getAllBooks() {

        Iterable<Book> shoppingCartIterator = bookRepository.findAll();

        var books = new ArrayList<Book>();
        shoppingCartIterator.forEach(books::add);

        log.info("For filtering criteria:\n{} books have been retrieved.", books.size());

        return books;
    }
}
