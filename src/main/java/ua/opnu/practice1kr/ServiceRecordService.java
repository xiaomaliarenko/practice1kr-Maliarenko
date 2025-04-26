package ua.opnu.practice1kr;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ServiceRecordService {

    private final ServiceRecordRepository serviceRecordRepository;

    public List<ServiceRecord> getAll() {
        return serviceRecordRepository.findAll();
    }

    public ServiceRecord getById(Long id) {
        return serviceRecordRepository.findById(id).orElseThrow();
    }

    public List<ServiceRecord> getByCarId(Long carId) {
        return serviceRecordRepository.findByCarId(carId);
    }

    public List<ServiceRecord> getByMechanicId(Long mechanicId) {
        return serviceRecordRepository.findByMechanicId(mechanicId);
    }

    public ServiceRecord save(ServiceRecord record) {
        return serviceRecordRepository.save(record);
    }

    public ServiceRecord update(Long id, ServiceRecord updated) {
        ServiceRecord record = getById(id);
        record.setDate(updated.getDate());
        record.setDescription(updated.getDescription());
        record.setCar(updated.getCar());
        record.setMechanic(updated.getMechanic());
        return serviceRecordRepository.save(record);
    }

    public void delete(Long id) {
        serviceRecordRepository.deleteById(id);
    }

    public ServiceRecord assignServiceType(Long recordId, ServiceType serviceType) {
        ServiceRecord record = getById(recordId);
        record.setServiceType(serviceType);
        return serviceRecordRepository.save(record);
    }

    public BigDecimal getTotalCostByCarId(Long carId) {
        List<ServiceRecord> records = serviceRecordRepository.findByCarId(carId);
        return records.stream()
                .filter(r -> r.getServiceType() != null)
                .map(r -> r.getServiceType().getStandardPrice())
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public long countRecordsByMechanicId(Long mechanicId) {
        return serviceRecordRepository.findByMechanicId(mechanicId).size();
    }

    public List<Object[]> getMostPopularServiceTypes() {
        return serviceRecordRepository.countServiceTypeUsage();
    }

    public List<ServiceRecord> getRecordsBetweenDates(LocalDate start, LocalDate end) {
        return serviceRecordRepository.findByDateBetween(start, end);
    }

    public List<Object[]> getTopServicedCars() {
        return serviceRecordRepository.countServiceRecordsByCar();
    }

}

