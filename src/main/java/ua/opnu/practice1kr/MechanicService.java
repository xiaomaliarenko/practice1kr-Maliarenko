package ua.opnu.practice1kr;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MechanicService {

    private final MechanicRepository mechanicRepository;

    public List<Mechanic> getAll() {
        return mechanicRepository.findAll();
    }

    public Mechanic getById(Long id) {
        return mechanicRepository.findById(id).orElseThrow();
    }

    public Mechanic save(Mechanic mechanic) {
        return mechanicRepository.save(mechanic);
    }

    public Mechanic update(Long id, Mechanic updated) {
        Mechanic mechanic = getById(id);
        mechanic.setName(updated.getName());
        mechanic.setSpecialization(updated.getSpecialization());
        return mechanicRepository.save(mechanic);
    }

    public void delete(Long id) {
        mechanicRepository.deleteById(id);
    }
}

