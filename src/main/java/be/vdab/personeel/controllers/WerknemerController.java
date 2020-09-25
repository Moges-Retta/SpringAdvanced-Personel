package be.vdab.personeel.controllers;

import be.vdab.personeel.domain.Werknemer;
import be.vdab.personeel.services.WerknemerService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashSet;
import java.util.LinkedList;

@Controller
@RequestMapping("/Werknemershierarchie")
public class WerknemerController {
    private final WerknemerService service;

    public WerknemerController(WerknemerService service) {
        this.service = service;
    }

    @GetMapping("{id}")
    public ModelAndView werknemers(@PathVariable long id) {
        var werknemer = service.findById(id).get();
        HashSet<Werknemer> Ondergeschikten = new HashSet<>(werknemer.getOndergeschikten());

        var modelAndView = new ModelAndView("werknemers");
        modelAndView.addObject("werknemer", werknemer);
        modelAndView.addObject("Ondergeschikten", Ondergeschikten);
        modelAndView.addObject("jobtitel", werknemer.getJobtitel().getNaam());

        return modelAndView;
    }
}