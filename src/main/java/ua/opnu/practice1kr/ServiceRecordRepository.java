package ua.opnu.practice1kr;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface ServiceRecordRepository extends JpaRepository<ServiceRecord, Long> {
    List<ServiceRecord> findByCarId(Long carId);
    List<ServiceRecord> findByMechanicId(Long mechanicId);

    @Query("SELECT sr.serviceType.name, COUNT(sr) as cnt FROM ServiceRecord sr GROUP BY sr.serviceType.name ORDER BY cnt DESC")
    List<Object[]> countServiceTypeUsage();

    List<ServiceRecord> findByDateBetween(LocalDate startDate, LocalDate endDate);

    @Query("SELECT sr.car.id, COUNT(sr) as cnt FROM ServiceRecord sr GROUP BY sr.car.id ORDER BY cnt DESC")
    List<Object[]> countServiceRecordsByCar();

}
