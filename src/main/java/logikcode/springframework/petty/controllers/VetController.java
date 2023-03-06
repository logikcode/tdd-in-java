package logikcode.springframework.petty.controllers;

import logikcode.springframework.petty.fauxspring.Model;
import logikcode.springframework.petty.services.VetService;
import logikcode.springframework.petty.services.map.VetMapService;

import java.util.Arrays;

public class VetController {

    private final VetMapService vetService;

    public VetController(VetMapService vetService) {
        this.vetService = vetService;
    }

    public String listVets(Model model){

        model.addAttribute("vets", vetService.findAll());
        return "vets/index";
    }
}
