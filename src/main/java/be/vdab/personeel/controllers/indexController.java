package be.vdab.personeel.controllers;

import be.vdab.personeel.services.WerknemerService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
public class indexController {
    private final WerknemerService service;

    public indexController(WerknemerService service) {
        this.service = service;
    }

    @GetMapping
    public ModelAndView index(){

        return new ModelAndView("index","chef",service.findChef().get());
    }

}
