package com.spring.demo;

import java.util.List;
import java.util.ArrayList;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class BookController {

    private final BookRepository bookRepository;

    public BookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    

    @GetMapping("/api/books")
    @ResponseBody
    public Iterable<Book> findAllApi() {
        return bookRepository.findAll();
    }

    @GetMapping("/api/books/title/{bookTitle}")
    @ResponseBody
    public List<Book> findByTitleApi(@PathVariable String bookTitle) {
        return bookRepository.findByTitle(bookTitle);
    }

    @GetMapping("/api/books/{id}")
    @ResponseBody
    public Book findOneApi(@PathVariable Long id) {
        return bookRepository.findById(id)
                .orElseThrow(BookNotFoundException::new);
    }

    @PostMapping("/api/books")
    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    public Book createApi(@RequestBody Book book) {
        return bookRepository.save(book);
    }

    @DeleteMapping("/api/books/{id}")
    @ResponseBody
    public void deleteApi(@PathVariable Long id) {
        bookRepository.findById(id)
                .orElseThrow(BookNotFoundException::new);
        bookRepository.deleteById(id);
    }

    @PutMapping("/api/books/{id}")
    @ResponseBody
    public Book updateBookApi(@RequestBody Book book, @PathVariable Long id) {
        if (!book.getId().equals(id)) {
            throw new BookIdMismatchException();
        }
        bookRepository.findById(id)
                .orElseThrow(BookNotFoundException::new);
        return bookRepository.save(book);
    }


   
    @GetMapping("/")
    public String home(Model model) {
    model.addAttribute("appName", "My Book Application");
    return "home";  // resources/templates/home.html
}

    @GetMapping("/books")
    public String listBooks(Model model) {
        try {
            Iterable<Book> books = bookRepository.findAll();
            List<Book> bookList = (List<Book>) books;
            System.out.println("=== DEBUG INFO ===");
            System.out.println("Total books found: " + bookList.size());
            for (Book book : bookList) {
                System.out.println("Book: " + book.getTitle() + " by " + book.getAuthor() + " (" + book.getYear() + ")");
            }
            System.out.println("==================");
            model.addAttribute("books", books);
        } catch (Exception e) {
            System.err.println("Error loading books: " + e.getMessage());
            e.printStackTrace();
            model.addAttribute("books", new ArrayList<>());
        }
        return "books";
    }

    @GetMapping("/books/add")
    public String showAddForm(Model model) {
        model.addAttribute("book", new Book());
        return "add-book"; // resources/templates/add-book.html
    }

    @PostMapping("/books/add")
    public String addBook(@ModelAttribute Book book) {
        bookRepository.save(book);
        return "redirect:/books";
    }

    @GetMapping("/books/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        model.addAttribute("book", bookRepository.findById(id).orElseThrow());
        return "edit_book"; // resources/templates/edit-book.html
    }

    @PostMapping("/books/edit/{id}")
    public String editBook(@PathVariable Long id, @ModelAttribute Book book) {
        book.setId(id);
        bookRepository.save(book);
        return "redirect:/books";
    }

    @GetMapping("/books/view/{id}")
    public String viewBook(@PathVariable Long id, Model model) {
        model.addAttribute("book", bookRepository.findById(id).orElseThrow());
        return "view_book"; 
    }

    @GetMapping("/books/delete/{id}")
    public String deleteBook(@PathVariable Long id) {
        bookRepository.deleteById(id);
        return "redirect:/books";
    }

    @GetMapping("/test/add-book")
    public String addTestBook() {
        try {
            Book testBook = new Book();
            testBook.setTitle("Test Book");
            testBook.setAuthor("Test Author");
            testBook.setYear(2024);
            
            Book savedBook = bookRepository.save(testBook);
            System.out.println("Test book saved with ID: " + savedBook.getId());
            
            return "redirect:/books";
        } catch (Exception e) {
            System.err.println("Error saving test book: " + e.getMessage());
            e.printStackTrace();
            return "redirect:/books";
        }
    }
}
