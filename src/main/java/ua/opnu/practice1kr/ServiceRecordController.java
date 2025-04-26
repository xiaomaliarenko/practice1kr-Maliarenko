package ua.opnu.practice1kr;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/records")
@RequiredArgsConstructor
public class ServiceRecordController {

    private final ServiceRecordService service;
    private final ServiceTypeService serviceTypeService;

    @GetMapping
    public List<ServiceRecord> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public ServiceRecord get(@PathVariable Long id) {
        return service.getById(id);
    }

    @GetMapping("/car/{carId}")
    public List<ServiceRecord> byCar(@PathVariable Long carId) {
        return service.getByCarId(carId);
    }

    @GetMapping("/mechanic/{mechanicId}")
    public List<ServiceRecord> byMechanic(@PathVariable Long mechanicId) {
        return service.getByMechanicId(mechanicId);
    }

    @PostMapping
    public ServiceRecord create(@RequestBody ServiceRecord r) {
        return service.save(r);
    }

    @PutMapping("/{id}")
    public ServiceRecord update(@PathVariable Long id, @RequestBody ServiceRecord r) {
        return service.update(id, r);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

    @PutMapping("/{recordId}/assign-service-type/{serviceTypeId}")
    public ServiceRecord assignServiceType(@PathVariable Long recordId, @PathVariable Long serviceTypeId) {
        ServiceType serviceType = serviceTypeService.getById(serviceTypeId);
        return service.assignServiceType(recordId, serviceType);
    }

    @GetMapping("/car/{carId}/total-cost")
    public BigDecimal getTotalCostByCarId(@PathVariable Long carId) {
        return service.getTotalCostByCarId(carId);
    }

    @GetMapping("/mechanic/{mechanicId}/count")
    public long countRecordsByMechanic(@PathVariable Long mechanicId) {
        return service.countRecordsByMechanicId(mechanicId);
    }

    @GetMapping("/popular-service-types")
    public List<Object[]> getMostPopularServiceTypes() {
        return service.getMostPopularServiceTypes();
    }

    @GetMapping("/between")
    public List<ServiceRecord> getRecordsBetween(
            @RequestParam("start") LocalDate start,
            @RequestParam("end") LocalDate end) {
        return service.getRecordsBetweenDates(start, end);
    }

    @GetMapping("/top-cars")
    public List<Object[]> getTopServicedCars() {
        return service.getTopServicedCars();
    }
}


