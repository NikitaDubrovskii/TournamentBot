package dev.dubrovsky.model.user;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @Column(name = "name")
    private String name;

    @Column(name = "chat_id")
    private Long chatId;

    @Column(name = "registered")
    private Boolean registered;

    public User(String username, Long chatId, Boolean registered) {
        this.username = username;
        this.chatId = chatId;
        this.registered = registered;
    }

}
