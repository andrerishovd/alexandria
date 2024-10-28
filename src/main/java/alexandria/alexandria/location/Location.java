package alexandria.alexandria.location;

import alexandria.alexandria.book.Book;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "location_gen")
    @SequenceGenerator(name = "location_gen", sequenceName = "location_seq", allocationSize = 1)
    @Column(name = "location_id", nullable = false)
    private Long location_id;
    @Column(name = "shelf_location")
    private char shelfLocation;
    @Column(name = "location_row")
    private int row;

    @OneToMany(mappedBy = "location")
    @JsonIgnoreProperties("location")
    private List<Book> books;

    public Location(char shelfLocation, int row) {
        this.shelfLocation = shelfLocation;
        this.row = row;
    }
}
