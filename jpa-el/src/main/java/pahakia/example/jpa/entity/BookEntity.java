package pahakia.example.jpa.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Version;

@Entity(name="Book")
@Table(name="BOOK")
public class BookEntity {
    @Id
    @SequenceGenerator(name = "BOOK_SEQ", sequenceName = "BOOK_SEQ", initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    private String name;
    private String isbn;
    private String bookAbstract;
    private int pages;
    @Version
    private int version;

    public BookEntity() {
    }

    public BookEntity(String name, String isbn, String bookAbstract, int pages) {
        this.name = name;
        this.isbn = isbn;
        this.bookAbstract = bookAbstract;
        this.pages = pages;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getBookAbstract() {
        return bookAbstract;
    }

    public void setBookAbstract(String bookAbstract) {
        this.bookAbstract = bookAbstract;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public String toString() {
        return "id=" + id + ", name=" + name + ", isbn=" + isbn + ", pages=" + pages + ", abstract=" + bookAbstract;
    }
}
