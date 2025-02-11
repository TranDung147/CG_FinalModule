package controllers.cg_finalmodule.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.Instant;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;

    private String fullName;
    private String address;
    private Instant dateOfBirth;
    private String work;
    private String phone;
    private String username;
    private String passwordHash;
    private String email;
    private Instant createAt;
    private Instant updateAt;
}