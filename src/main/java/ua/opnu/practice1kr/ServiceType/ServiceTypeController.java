package ua.opnu.practice1kr.ServiceType;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/types")
@RequiredArgsConstructor
public class ServiceTypeController {

    private final ServiceTypeService service;

    @GetMapping
    public List<ServiceType> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public ServiceType get(@PathVariable Long id) {
        return service.getById(id);
    }

    @PostMapping
    public ServiceType create(@RequestBody ServiceType type) {
        return service.save(type);
    }

    @PutMapping("/{id}")
    public ServiceType update(@PathVariable Long id, @RequestBody ServiceType type) {
        return service.update(id, type);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}

