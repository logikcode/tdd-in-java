package logikcode.springframework.petty.controllers;

import logikcode.springframework.petty.fauxspring.Model;
import logikcode.springframework.petty.services.VetService;

public class VetController {

    private final VetService vetService;

    public VetController(VetService vetService) {
        this.vetService = vetService;
    }

    public String listVets(Model model){

        model.addAttribute("vets", vetService.findAll());

        return "vets/index";
    }
}
