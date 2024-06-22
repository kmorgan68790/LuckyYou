package learn.luckyyou.controllers;

import learn.luckyyou.domain.ConcordDaysService;
import learn.luckyyou.domain.NumerologyDescriptionService;
import learn.luckyyou.models.NumerologyDescription;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/numerology")
@CrossOrigin
public class NumerologyDescriptionController {
    private final NumerologyDescriptionService service;

    public NumerologyDescriptionController(NumerologyDescriptionService service) {
        this.service = service;
    }

    @GetMapping
    public List<NumerologyDescription> findAll() {
        return service.findAll();
    }

    @GetMapping("/id/{numerologyDescriptionId}")
    public NumerologyDescription findById(@PathVariable int numerologyDescriptionId) {
        return service.findById(numerologyDescriptionId);
    }

    @GetMapping("/type/{numerologyType}")
    public NumerologyDescription findByNumerologyType(@PathVariable String numerologyType) {
        return service.findByNumerologyType(numerologyType);
    }

//    @GetMapping("/description/{numerologyType}")
//    public NumerologyDescription findByNumerologyDescription(@PathVariable String numerologyDescription) {
//        return service.findByNumerologyDescription(numerologyDescription);
//    }

    @GetMapping("/number/{numerologyNumber}")
    public NumerologyDescription findByNumerologyNumber(@PathVariable int numerologyNumber) {
        return service.findByNumerologyNumber(numerologyNumber);
    }

}
