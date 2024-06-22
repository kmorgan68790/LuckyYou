package learn.luckyyou.domain;

import learn.luckyyou.data.NumerologyDescriptionRepository;
import learn.luckyyou.models.NumerologyDescription;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class NumerologyDescriptionService {
    private final NumerologyDescriptionRepository repository;

    public NumerologyDescriptionService(NumerologyDescriptionRepository repository) {
        this.repository = repository;
    }

    public List<NumerologyDescription> findAll() {
        return repository.findAll();
    }

    public NumerologyDescription findById(int numerologyDescriptionId) {
        return repository.findById(numerologyDescriptionId);
    }

    public NumerologyDescription findByNumerologyType(String numerologyType) {
        return repository.findByNumerologyType(numerologyType);
    }

    public NumerologyDescription findByNumerologyDescription(String numerologyDescription) {
        return repository.findByNumerologyDescription(numerologyDescription);
    }

    public NumerologyDescription findByNumerologyNumber(int numerologyNumber) {
        return repository.findByNumerologyNumber(numerologyNumber);
    }

}
