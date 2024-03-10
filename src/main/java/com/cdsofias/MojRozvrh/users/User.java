package com.cdsofias.MojRozvrh.users;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import java.util.UUID;
@Data
@Builder
@AllArgsConstructor
@Entity
@Table(name = "users")
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class User {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private UUID id;
    private String name;
    private String surname;
    private String email;
    private String password;
    private Enum role;
}
