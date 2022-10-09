package com.example.liquebase;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.sql.DataSource;
import java.util.List;

@SpringBootApplication
@OpenAPIDefinition
public class LiquebaseApplication {

//    DataSource dataSource;


    public static void main(String[] args) {
        SpringApplication.run(LiquebaseApplication.class, args);
    }


}


@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
class Book {

    @Id
    @GenericGenerator(name = "uuid_seq", strategy = "uuid2")
    @GeneratedValue(generator = "uuid_seq")
    private String id;
    private String title;
    private String author;

}


interface Repo extends JpaRepository<Book, String> {
}


@RestController
@RequestMapping("/api/book")
record BookController(Repo repo) {

    @GetMapping
    public List<Book> getAll() {
        return repo.findAll();
    }

}