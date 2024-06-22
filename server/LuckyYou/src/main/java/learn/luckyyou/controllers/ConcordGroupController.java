package learn.luckyyou.controllers;

import learn.luckyyou.domain.ConcordBirthdayService;
import learn.luckyyou.domain.ConcordGroupService;
import learn.luckyyou.models.ConcordGroup;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/concord-group")
@CrossOrigin
public class ConcordGroupController {
    private final ConcordGroupService service;

    public ConcordGroupController(ConcordGroupService service) {
        this.service = service;
    }

    @GetMapping
    public List<ConcordGroup> findAll() {
        return service.findAll();
    }

    @GetMapping("/id/{concordGroupId}")
    public ConcordGroup findById(@PathVariable int concordGroupId) {
        return service.findById(concordGroupId);
    }

}
