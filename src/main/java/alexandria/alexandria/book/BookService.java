package alexandria.alexandria.book;

import alexandria.alexandria.book.Book;
import alexandria.alexandria.book.BookRepo;
import alexandria.alexandria.modules.BookUpdate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    private final BookRepo bookRepo;

    @Autowired
    public BookService(BookRepo bookRepo) {
        this.bookRepo = bookRepo;
    }

    public List<Book> getBooks() {
        return bookRepo.findAll();
    }

    public Book getBookById(long id) {
        return bookRepo.findById(id).orElse(null);
    }

    public Book saveBook(Book book) {
        return bookRepo.save(book);
    }

    public List<Book> deleteBook(long id) {
        bookRepo.deleteById(id);
        return bookRepo.findAll();
    }

    /*
    public Book patchBook(BookUpdate bookUpdate) {
        Long id = bookUpdate.getId();
        String bookVariable = bookUpdate.getVariable();
        String update = bookUpdate.getUpdate();

        switch (bookVariable) {
            case "title" -> getBookById(id).setTitle(update);
            case "author_name" -> getBookById(id).getAuthor().setName(update);
            case "author_age" -> getBookById(id).getAuthor().setAge(Integer.parseInt(update));
            case "publisher" -> getBookById(id).setPublisher(update);
            // case "status" -> getBookById(id).getStatus().setAvailable(Boolean.parseBoolean(update));
            // case "location_shelfLocation" -> getBookById(id).getShelfLocation().setShelfSection(update.charAt(0));
            // case "location_row" -> getBookById(id).getShelfLocation().setRow(Integer.parseInt(update));
        }
        return getBookById(id);
    }
     */
}
