package pl.coderslab.controller;

import org.springframework.stereotype.Service;
import pl.coderslab.model.Book;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class MemoryBookService {

    private List<Book> list;

    public MemoryBookService() {
        list = new ArrayList<>();
        list.add(new Book(1L, "9788324631766", "Thinking in Java", "Bruce Eckel",
                "Helion", "programming"));
        list.add(new Book(2L, "9788324627738", "Rusz glowa, Java.",
                "Sierra Kathy, Bates Bert", "Helion", "programming"));
        list.add(new Book(3L, "9780130819338", "Java 2. Podstawy",
                "Cay Horstmann, Gary Cornell", "Helion", "programming"));
        list.add(new Book(4L, "143153151", "Władca Pierścieni","J.R.R. Tolkien", "Company", "fantasy"));
    }

    public List<Book> getList() {
        return list;
    }

    public void setList(List<Book> list) {
        this.list = list;
    }

    public void addBook(long id, String isbn, String title, String author, String publisher, String type){
        Book book = new Book(id, isbn, title, author, publisher, type);
        list.add(book);
    }

    public void deleteBook (long id){
        list.remove(list.stream()
                .filter(book1 -> book1.getId() == id)
                .collect(Collectors.toList()).get(0));
    }

    public Book editBook (long id, String isbn, String title, String author, String publisher, String type){
        Book book = list.stream()
                .filter(book1 -> book1.getId() == id)
                .collect(Collectors.toList()).get(0);

        book.setId(id);
        book.setIsbn(isbn);
        book.setTitle(title);
        book.setAuthor(author);
        book.setPublisher(publisher);
        book.setType(type);

        return book;
    }

    public Book showBook(long id){
        return list.stream()
                .filter(book1 -> book1.getId() == id)
                .collect(Collectors.toList()).get(0);
    }
}
