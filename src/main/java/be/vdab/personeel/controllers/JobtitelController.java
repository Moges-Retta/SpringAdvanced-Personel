package be.vdab.personeel.controllers;

import be.vdab.personeel.services.JobtitelService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/Jobtitels")
public class JobtitelController {
    private final JobtitelService jobtitelService;
    public JobtitelController(JobtitelService jobtitelService) {
        this.jobtitelService = jobtitelService;
    }

    @GetMapping
    public ModelAndView Jobtitels() {
        return new ModelAndView("jobtitel",
                "jobtitels", jobtitelService.findAllByOrderBynaamAsc());
    }

    @GetMapping("{id}")
    public ModelAndView werknemers(@PathVariable("id") long id) {
        var werknemers = jobtitelService.findById(id).get().getWerknemers();
        var jobtitel = werknemers.stream().findFirst().get().getJobtitel();
        var modelAndView = new ModelAndView("jobtitel");

        modelAndView.addObject("werknemers", werknemers);
        modelAndView.addObject("jobtitel", jobtitel);
        return modelAndView;
    }

}
