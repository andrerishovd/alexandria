package alexandria.alexandria.author;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorService {
    private final AuthorRepo authorRepo;

    @Autowired
    public AuthorService(AuthorRepo authorRepo) {
        this.authorRepo = authorRepo;
    }

    public List<Author> getAuthors() {
        return authorRepo.findAll();
    }

    public Author getAuthorById(long id) {
        return authorRepo.findById(id).orElse(null);
    }

    public Author saveAuthor(Author author) {
        return authorRepo.save(author);
    }

    public List<Author> deleteAuthor(long id) {
        authorRepo.deleteById(id);
        return authorRepo.findAll();
    }
}
