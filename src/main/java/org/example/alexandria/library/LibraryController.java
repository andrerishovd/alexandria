package org.example.alexandria.library;

import org.example.alexandria.book.BookService;
import org.example.alexandria.modules.Book;
import org.example.alexandria.modules.BookUpdate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class LibraryController {

    private final BookService bookService;

    @Autowired
    public LibraryController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Book>> getBooks(){
        List<Book> books = bookService.getBooks();
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    @GetMapping("/available")
    public ResponseEntity<List<Book>> getAvailableBooks() {
        List<Book> books = bookService.getAvailableBooks();
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable int id) {
        return new ResponseEntity<>(bookService.getBookById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Book> addNewBook(@RequestBody Book newBook) {
        return new ResponseEntity<>(bookService.addNewBook(newBook), HttpStatus.CREATED);
    }

    @PatchMapping("/patch")
    public ResponseEntity<Book> editExistingBook(@RequestBody BookUpdate bookUpdate) {
        return new ResponseEntity<>(bookService.editExistingBook(bookUpdate), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<List<Book>> deleteBook(@PathVariable int id) {
        return new ResponseEntity<>(bookService.deleteBookById(id), HttpStatus.OK);

    }
}
