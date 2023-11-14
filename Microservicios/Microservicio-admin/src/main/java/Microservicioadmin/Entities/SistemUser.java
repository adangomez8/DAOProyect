package Microservicioadmin.Entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(uniqueConstraints = {
        @UniqueConstraint(columnNames = "username")
})
@Data
public class SistemUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    @Column(unique = true)
    private String username;

    private String password;

}
