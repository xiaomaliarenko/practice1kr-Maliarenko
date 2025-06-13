package ua.opnu.practice1kr.ServiceRecord;

import jakarta.persistence.*;
import lombok.*;
import ua.opnu.practice1kr.Car.Car;
import ua.opnu.practice1kr.Mechanic.Mechanic;
import ua.opnu.practice1kr.ServiceType.ServiceType;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ServiceRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "car_id")
    private Car car;

    @ManyToOne
    @JoinColumn(name = "mechanic_id")
    private Mechanic mechanic;

    @ManyToOne
    @JoinColumn(name = "service_type_id")
    private ServiceType serviceType;


    private LocalDate date;

    private String description;
}

