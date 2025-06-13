package ua.opnu.practice1kr.Car;

import jakarta.persistence.*;
import lombok.*;
import ua.opnu.practice1kr.Client.Client;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    private String make;

    private String model;

    private Integer year;

    private String vin;
}

