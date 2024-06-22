package learn.luckyyou.domain;

import learn.luckyyou.data.UserRepository;
import learn.luckyyou.data.ZodiacRepository;
import learn.luckyyou.models.Zodiac;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ZodiacService {
    private final ZodiacRepository repository;

    private final UserRepository userRepository;

    public ZodiacService(ZodiacRepository repository, UserRepository userRepository) {
        this.repository = repository;
        this.userRepository = userRepository;
    }

    public List<Zodiac> findAll() {
        return repository.findAll();
    }

    public Zodiac findById(int zodiacId) {
        return repository.findById(zodiacId);
    }

    public Zodiac findZodiacStartAndEnd(LocalDate zodiacDateStart, LocalDate zodiacDateEnd) {
        return repository.findZodiacStartAndEnd(zodiacDateStart,zodiacDateEnd);
    }

    public Zodiac findByDate(LocalDate date) {
        return repository.findByDate(date);
    }
}
