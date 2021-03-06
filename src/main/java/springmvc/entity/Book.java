package springmvc.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "book")
public class Book {

    public Book() {

    }

    public Book(Category category, String name) {

        this.category = category;
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) {

            return true;
        }
        if (o == null || getClass() != o.getClass()) {

            return false;
        }
        Book book = (Book) o;

        if (id != book.getId()) {

            return false;
        }

        return id == book.getId();
    }

    public Set<Author> getAuthors() {

        return authors;
    }

    public Category getCategory() {

        return category;
    }

    public void setCategory(Category category) {

        this.category = category;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {

        this.name = name;
    }

    public long getId() {

        return id;
    }

    public void setId(long id) {

        this.id = id;
    }

    public void setAuthors(Set<Author> authors) {

        this.authors = authors;
    }

    @Override
    public String toString() {

        return "Book{" +
                "id=" + this.id +
                ", name='" + this.name +
                '}';
    }

    @ManyToMany(mappedBy = "books", fetch = FetchType.LAZY)
    private Set<Author> authors = new HashSet<Author>();

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_id")
    private long id;

    private String name;
}