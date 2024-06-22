package learn.luckyyou.controllers;

import learn.luckyyou.domain.ConcordBirthdayService;
import learn.luckyyou.domain.ZodiacService;
import learn.luckyyou.models.ConcordBirthday;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/concord-birthday")
@CrossOrigin
public class ConcordBirthdayController {
    private final ConcordBirthdayService service;

    public ConcordBirthdayController(ConcordBirthdayService service) {
        this.service = service;
    }

    @GetMapping
    public List<ConcordBirthday> findAll() {
        return service.findAll();
    }

    @GetMapping("/id/{id}")
    public ConcordBirthday findById(@PathVariable int id) {
        return service.findById(id);
    }

    @GetMapping("/group/{concordGroupId}")
    public List<ConcordBirthday> findByGroupId(@PathVariable int concordGroupId) {
        return service.findByGroupId(concordGroupId);
    }

    @GetMapping("/birthday/{birthdayNumber}")
    public ConcordBirthday findConcordBirthdayNumber(@PathVariable int birthdayNumber) {
        return service.findConcordBirthdayNumber(birthdayNumber);
    }

}
