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

    @GetMapping("/group/{concordGroupId}")
    public List<ConcordDays> findByConcordGroupId(@PathVariable int concordGroupId) {
        return service.findByConcordGroupId(concordGroupId);
    }

//    @GetMapping("/group-day/{concordGroupId}")
//    public List<ConcordDays> findByDayTypeAndGroupId(String dayType, int concordGroupId) {
//        return service.findByDayTypeAndGroupId(dayType,concordGroupId);
//    }

}
