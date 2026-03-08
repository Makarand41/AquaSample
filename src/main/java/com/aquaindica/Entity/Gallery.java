package com.aquaindica.Entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Gallery {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String type; // "image" or "video"

    @Lob
    @Column(columnDefinition = "LONGBLOB")
    private byte[] file;

//    @Lob
//    @Column(columnDefinition = "LONGBLOB")
//    private byte[] image;
}
