package pl.coderslab.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import pl.coderslab.model.Book;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/books")
public class BookController {

    MemoryBookService memoryBookService;

    @Autowired
    public BookController(MemoryBookService memoryBookService) {
        this.memoryBookService = memoryBookService;
    }

    @RequestMapping("/hello")
    public String hello() {
        return "{hello: World}";
    }

    @RequestMapping("/helloBook")
    public Book helloBook() {
        return new Book(1L, "9788324631766", "Thinking in Java",
                "Bruce Eckel", "Helion", "programming");
    }

    @RequestMapping("/list")
    public List<Book> listBook() {
        return memoryBookService.getList();
    }

    @RequestMapping("/book/{id}")
    public Book thisBook(@PathVariable long id) {

        return memoryBookService.showBook(id);
    }

    @RequestMapping(value = "/delete/{id}", produces = "text/html; charset=UTF-8")
    @ResponseBody
    public String deleteBook(@PathVariable long id) {

        memoryBookService.deleteBook(id);

        return "usunięto książkę o id: " + id;
    }

    @PostMapping("/addBook")
    public void addBook(@RequestBody Book book){
        memoryBookService.addBook(book.getId(),
                book.getIsbn(),
                book.getTitle(),
                book.getAuthor(),
                book.getPublisher(),
                book.getType());
    }


    @RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
    public Book editBook(@RequestBody Book book) {

        memoryBookService.editBook(book.getId(), book.getIsbn(), book.getTitle(), book.getAuthor(), book.getPublisher(), book.getType());

        return book;
    }
}