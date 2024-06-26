package learn.luckyyou.controllers;

import learn.luckyyou.domain.UserNumerologyMappingService;
import learn.luckyyou.domain.ZodiacService;
import learn.luckyyou.models.Zodiac;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/zodiac")
@CrossOrigin
public class ZodiacController {
    private final ZodiacService service;

    public ZodiacController(ZodiacService service) {
        this.service = service;
    }

    @GetMapping
    public List<Zodiac> findAll() {
        return service.findAll();
    }

    @GetMapping("/id/{zodiacId}")
    public Zodiac findById(@PathVariable int zodiacId) {
        return  service.findById(zodiacId);
    }

    @GetMapping("/date/{date}")
    public Zodiac findByDate(LocalDate date) {
        return service.findByDate(date);
    }

}
