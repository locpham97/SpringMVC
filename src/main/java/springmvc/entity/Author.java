package springmvc.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "author")
public class Author {

    public Author() {

    }

    public Author(String name) {

        this.name = name;
    }

    public void addBook(Book book) {

        books.add(book);
        book.getAuthors().add(this);
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) {

            return true;
        }
        if (o == null || getClass() != o.getClass()) {

            return false;
        }
        Author author = (Author) o;
        if (id != author.id) {

            return false;
        }

        return name != null ? name.equals(author.name) : author.name == null;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {

        this.name = name;
    }

    public Set<Book> getBooks() {

        return books;
    }

    public void setBooks(Set<Book> books) {

        this.books = books;
    }

    public long getId() {

        return id;
    }

    public void setId(long id) {

        this.id = id;
    }

    public void removeBook(Book book) {

        this.books.remove(book);
        book.getAuthors().remove(this);
    }

    @Override
    public String toString() {

        return "Author{" +
                "id=" + this.id +
                ", name='" + this.name +
                '}';
    }

    @ManyToMany(
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.DETACH,
                    CascadeType.REFRESH,
                    CascadeType.MERGE
            },
            fetch = FetchType.EAGER
    )
    @JoinTable(
            name = "books_authors",
            joinColumns = @JoinColumn(name = "author_id"),
            inverseJoinColumns = @JoinColumn(name = "book_id")
    )
    private Set<Book> books = new HashSet<Book>();

    private String name;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "author_id")
    private long id;
}
