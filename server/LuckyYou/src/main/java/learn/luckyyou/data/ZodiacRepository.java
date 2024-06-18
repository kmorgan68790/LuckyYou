package learn.luckyyou.data;

import learn.luckyyou.models.ConcordBirthday;
import learn.luckyyou.models.Users;
import learn.luckyyou.models.Zodiac;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ZodiacRepository {
    List<Zodiac> findAll();
    Zodiac findById(int zodiacId);
    Zodiac findZodiacStartAndEnd(LocalDate zodiacDateStart, LocalDate zodiacDateEnd);
    Zodiac findByDate(LocalDate date); // Find zodiac by a specific date
}
