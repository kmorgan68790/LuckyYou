package learn.luckyyou.controllers;

import learn.luckyyou.domain.ConcordBirthdayService;
import learn.luckyyou.domain.ConcordDaysService;
import learn.luckyyou.models.ConcordDays;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/concord-days")
@CrossOrigin
public class ConcordDaysController {
    private final ConcordDaysService service;

    public ConcordDaysController(ConcordDaysService service) {
        this.service = service;
    }

    @GetMapping("/id/{concordDaysId}")
    public ConcordDays findById(@PathVariable int concordDaysId) {
        return service.findById(concordDaysId);
    }

    @GetMapping("/birthday/{concordBirthdayNumber}/group/{concordGroupId}")
    public List<ConcordDays> findConcordDaysByBirthdayAndGroupId(@PathVariable int concordBirthdayNumber,
                                                                 @PathVariable int concordGroupId) {
        return service.findConcordDaysByBirthdayAndGroupId(concordBirthdayNumber, concordGroupId);
    }

    @GetMapping("/group/{concordGroupId}")
    public List<ConcordDays> findByConcordGroupId(@PathVariable int concordGroupId) {
        return service.findByConcordGroupId(concordGroupId);
    }

}
