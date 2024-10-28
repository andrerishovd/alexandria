package alexandria.alexandria.application;

import alexandria.alexandria.book.Book;
import alexandria.alexandria.book.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

// @RestController DOES NOT WORK, use @Controller instead
@Controller
@RequestMapping("/html")
public class HtmlController {

    private final BookService bookService;

    @Autowired
    public HtmlController(BookService bookService){
        this.bookService = bookService;
    }

    @GetMapping
    public String home(Model model){
        model.addAttribute("books", bookService.getBooks());
        return "index";
    }

    @GetMapping("/addNewBook")
    public String addNewBook(Model model) {
        model.addAttribute("book", new Book());
        return "newBook";
    }

    @PostMapping("/addNewBook")
    public String addNewBook(@ModelAttribute Book book) {
        bookService.saveBook(book);
        return "newBookResult";
    }
}
