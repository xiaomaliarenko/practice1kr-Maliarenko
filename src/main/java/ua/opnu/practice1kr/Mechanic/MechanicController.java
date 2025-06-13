package ua.opnu.practice1kr.Mechanic;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/mechanics")
@RequiredArgsConstructor
public class MechanicController {

    private final MechanicService service;

    @GetMapping
    public List<Mechanic> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public Mechanic get(@PathVariable Long id) {
        return service.getById(id);
    }

    @PostMapping
    public Mechanic create(@RequestBody Mechanic m) {
        return service.save(m);
    }

    @PutMapping("/{id}")
    public Mechanic update(@PathVariable Long id, @RequestBody Mechanic m) {
        return service.update(id, m);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}

