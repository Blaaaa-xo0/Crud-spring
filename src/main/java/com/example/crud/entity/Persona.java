package com.example.crud.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@RequiredArgsConstructor
@NoArgsConstructor
public class Persona {
    @Id
    @GeneratedValue(
            strategy = GenerationType.AUTO
    )
    private Integer id;

    @NonNull
    private String name;

    @NonNull
    private String username;

    @NonNull
    private String email;

    @NonNull
    private String birthday;

    @NonNull
    private Integer age;

}
