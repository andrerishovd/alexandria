package alexandria.alexandria;

import alexandria.alexandria.author.Author;
import alexandria.alexandria.author.AuthorService;
import alexandria.alexandria.book.Book;
import alexandria.alexandria.book.BookService;
import alexandria.alexandria.location.Location;
import alexandria.alexandria.location.LocationService;
import alexandria.alexandria.modules.Status;
import com.github.javafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Component
public class InitialData implements CommandLineRunner {

    private final AuthorService authorService;
    private final BookService bookService;
    private final LocationService locationService;

    Faker faker = Faker.instance();

    @Autowired
    public InitialData(AuthorService authorService, BookService bookService, LocationService locationService) {
        this.authorService = authorService;
        this.bookService = bookService;
        this.locationService = locationService;
    }

    @Override
    public void run(String... args) {

        List<Location> locations = new ArrayList<>();
        for(long i = 0; i < 10; i++) {
            locations.add(locationService.saveLocation(
                new Location(
                        faker.lorem().character(),
                        faker.random().nextInt(0,10)
                )
            ));
        }

        List<Author> authors = new ArrayList<>();
        for(long i = 0; i < 20; i++) {
            authors.add(
                authorService.saveAuthor(
                    new Author(
                            faker.name().lastName(),
                            faker.random().nextInt(15,80)
                    )
            ));
        }

        List<Book> books = new ArrayList<>();
        for(long i = 0; i < 20; i++) {
            books.add(
                bookService.saveBook(
                    new Book(
                        faker.book().title(),
                        faker.book().publisher(),
                        getRandomStatus(),
                        getRandomAuthors(authors),
                        getRandomLocation(locations)
                    )
                )
            );
        }
    }

    private Status getRandomStatus() {
        return Status.values()[new Random().nextInt(Status.values().length)];
    }

    private List<Author> getRandomAuthors(List<Author> authors) {
        List<Author> randomAuthors = new ArrayList<>();
        for (int i = 0; i < new Random().nextInt(1, 3); i++) {
            randomAuthors.add(authors.get(new Random().nextInt(authors.size())));
        }
        return randomAuthors;
    }

    private Location getRandomLocation(List<Location> locations) {
        return locations.get(new Random().nextInt(locations.size()));
    }
}
