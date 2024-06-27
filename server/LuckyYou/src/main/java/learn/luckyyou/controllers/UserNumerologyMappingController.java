package learn.luckyyou.controllers;

import learn.luckyyou.domain.UserNumerologyMappingService;
import learn.luckyyou.models.UserNumerologyMapping;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/mapping")
@CrossOrigin
public class UserNumerologyMappingController {
    private final UserNumerologyMappingService service;

    public UserNumerologyMappingController(UserNumerologyMappingService service) {
        this.service = service;
    }

    @GetMapping
    public List<UserNumerologyMapping> findAll() {
        return service.findAll();
    }

    @GetMapping("/user/{userId}")
    public List<UserNumerologyMapping> findByUserId(@PathVariable int userId) {
        return service.findByUserId(userId);
    }

    @GetMapping("/description/{numerologyDescriptionId}")
    List<UserNumerologyMapping> findByNumerologyDescriptionId(@PathVariable int numerologyDescriptionId) {
        return service.findByNumerologyDescriptionId(numerologyDescriptionId);
    }

}
