package org.example.alexandria.modules;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookUpdate {
    private int id;
    private String variable;
    private String update;
}
