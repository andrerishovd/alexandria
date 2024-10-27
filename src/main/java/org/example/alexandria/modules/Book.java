package org.example.alexandria.modules;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Book {
    private int bookId;
    // Make author class / status class / location
    private String title;
    private Author author;
    private String publisher;
    private Status status;
    private Location shelfLocation;
}

