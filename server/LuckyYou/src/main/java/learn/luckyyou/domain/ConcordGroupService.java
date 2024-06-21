package learn.luckyyou.domain;

import learn.luckyyou.data.ConcordGroupRepository;
import learn.luckyyou.models.ConcordGroup;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConcordGroupService {

    private final ConcordGroupRepository repository;

    public ConcordGroupService(ConcordGroupRepository repository) {
        this.repository = repository;
    }

    public ConcordGroup findById(int concordGroupId) {
        return repository.findById(concordGroupId);
    }

    public List<ConcordGroup> findAll() {
        return repository.findAll();
    }
}
