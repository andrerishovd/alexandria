package org.example.alexandria.book;

import com.github.javafaker.Faker;
import org.example.alexandria.config.MainConfig;
import org.example.alexandria.exception.BookNotFoundException;
import org.example.alexandria.modules.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class BookRepository {

    private final MainConfig config;

    public final List<Book> library = new ArrayList<>();

    @Autowired
    public BookRepository(MainConfig config) {
        this.config = config;

        for(int i = 0; i < config.getInitialbooks(); i++) {
            library.add(new Book(
                    i,
                    Faker.instance().book().title(),
                    new Author(
                            Faker.instance().funnyName().name(),
                            Faker.instance().number().numberBetween(5,130)
                    ),
                    Faker.instance().book().publisher(),
                    new Status(Faker.instance().bool().bool()),
                    new Location(Faker.instance().lorem().character(), Faker.instance().number().numberBetween(1,10))

            ));
        }
    }

    public List<Book> getBooks(){
        return library;
    }

    public List<Book> getAvailableBooks(){
        return library
                .stream()
                .filter(book -> book.getStatus().isAvailable())
                .toList();
    }

    public Book getBookById(int id) {
        Book book = library
                .stream()
                .filter(b -> b.getBookId() == id)
                .findFirst()
                .orElse(null);
        if (book == null) throw new BookNotFoundException(id);
        return book;
    }

    public Book addBook(Book newBook) {
        library.add(newBook);
        return getBookById(newBook.getBookId());
    }

    public Book editExistingBook(BookUpdate bookUpdate) {
        int id = bookUpdate.getId();
        String variable = bookUpdate.getVariable();
        String update = bookUpdate.getUpdate();

        switch (variable) {
            case "title" -> getBookById(id).setTitle(update);
            case "author_name" -> getBookById(id).getAuthor().setName(update);
            case "author_age" -> getBookById(id).getAuthor().setAge(Integer.parseInt(update));
            case "publisher" -> getBookById(id).setPublisher(update);
            case "status" -> getBookById(id).getStatus().setAvailable(Boolean.parseBoolean(update));
            case "location_shelfLocation" -> getBookById(id).getShelfLocation().setShelfSection(update.charAt(0));
            case "location_row" -> getBookById(id).getShelfLocation().setRow(Integer.parseInt(update));
        }
        return getBookById(id);
    }

    public List<Book> deleteBookById(int id) {

        if (getBookById(id) != null){
            library.removeIf(book -> book.getBookId() == id);
            System.out.println("Book with ID = " +id+ " was successfully deleted");
        }
        return library;
    }
}
