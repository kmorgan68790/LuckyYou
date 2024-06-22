package learn.luckyyou.domain;

import learn.luckyyou.data.ConcordBirthdayRepository;
import learn.luckyyou.data.ConcordDaysRepository;
import learn.luckyyou.data.ConcordGroupRepository;
import learn.luckyyou.models.ConcordDays;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConcordDaysService {
    private final ConcordGroupRepository concordGroupRepository;
    private final ConcordDaysRepository concordDaysRepository;

    public ConcordDaysService(ConcordGroupRepository concordGroupRepository, ConcordDaysRepository concordDaysRepository) {
        this.concordGroupRepository = concordGroupRepository;
        this.concordDaysRepository = concordDaysRepository;
    }

    public ConcordDays findById(int concordDaysId) {
        return concordDaysRepository.findById(concordDaysId);
    }

    public List<ConcordDays> findByConcordGroupId(int concordGroupId) {
        return concordDaysRepository.findByConcordGroupId(concordGroupId);
    }

    public List<ConcordDays> findByDayTypeAndGroupId(String dayType, int concordGroupId) {
        return concordDaysRepository.findByDayTypeAndGroupId(dayType, concordGroupId);
    }

}
