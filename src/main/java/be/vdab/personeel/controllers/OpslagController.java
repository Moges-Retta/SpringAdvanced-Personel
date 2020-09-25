package be.vdab.personeel.controllers;

import be.vdab.personeel.services.WerknemerService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.transaction.Transactional;
import java.math.BigDecimal;

@Controller
@RequestMapping("opslag/{id}")
public class OpslagController {
    private final WerknemerService service;

    public OpslagController(WerknemerService service) {
        this.service = service;
    }
    @GetMapping
    public ModelAndView opslagForm(@PathVariable long id){
        return new ModelAndView("opslag","werknemer",service.findById(id).get());
    }
    @GetMapping("bedrag")
    public String opslag(@PathVariable long id,
                         @RequestParam(value = "bedrag", required = false) BigDecimal bedrag){
        service.Opslag(id,bedrag);
        return "redirect:/Werknemershierarchie/{id}";
    }
}