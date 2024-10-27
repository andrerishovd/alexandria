package org.example.alexandria.book;

import org.example.alexandria.modules.Book;
import org.example.alexandria.modules.BookUpdate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    private final BookRepository repo;

    @Autowired
    public BookService(BookRepository repo) {
        this.repo = repo;
    }

    public List<Book> getBooks() {
        return repo.getBooks();
    }

    public List<Book> getAvailableBooks() {
        return repo.getAvailableBooks();
    }

    public Book getBookById(int id) {
        return repo.getBookById(id);
    }

    public Book addNewBook(Book newBook) {
        return repo.addBook(newBook);
    }

    public Book editExistingBook(BookUpdate bookUpdate) {
        return repo.editExistingBook(bookUpdate);
    }

    public List<Book> deleteBookById(int id) {
        return repo.deleteBookById(id);
    }
}
