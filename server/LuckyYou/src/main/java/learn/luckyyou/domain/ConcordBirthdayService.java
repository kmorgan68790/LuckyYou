package learn.luckyyou.domain;

import learn.luckyyou.data.ConcordBirthdayRepository;
import learn.luckyyou.data.ConcordGroupRepository;
import learn.luckyyou.models.ConcordBirthday;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConcordBirthdayService {
    private final ConcordGroupRepository concordGroupRepository;
    private final ConcordBirthdayRepository concordBirthdayRepository;

    public ConcordBirthdayService(ConcordGroupRepository concordGroupRepository, ConcordBirthdayRepository concordBirthdayRepository) {
        this.concordGroupRepository = concordGroupRepository;
        this.concordBirthdayRepository = concordBirthdayRepository;
    }

    public ConcordBirthday findById(int concordBirthdayId) {
        return concordBirthdayRepository.findById(concordBirthdayId);
    }

    public List<ConcordBirthday> findAll() {
        return concordBirthdayRepository.findAll();
    }

    public List<ConcordBirthday> findByGroupId(int concordGroupId) {
        return concordBirthdayRepository.findByGroupId(concordGroupId);
    }

    public ConcordBirthday findConcordBirthdayNumber(int birthdayNumber) {
        return concordBirthdayRepository.findConcordBirthdayNumber(birthdayNumber);
    }
}

