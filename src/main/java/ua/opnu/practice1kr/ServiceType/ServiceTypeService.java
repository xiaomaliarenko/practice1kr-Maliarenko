package ua.opnu.practice1kr.ServiceType;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ServiceTypeService {

    private final ServiceTypeRepository repository;

    public List<ServiceType> getAll() {
        return repository.findAll();
    }

    public ServiceType getById(Long id) {
        return repository.findById(id).orElseThrow();
    }

    public ServiceType save(ServiceType serviceType) {
        return repository.save(serviceType);
    }

    public ServiceType update(Long id, ServiceType updated) {
        ServiceType s = getById(id);
        s.setName(updated.getName());
        s.setStandardPrice(updated.getStandardPrice());
        return repository.save(s);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}

