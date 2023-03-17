package logikcode.springframework.petty.controllers;

import logikcode.springframework.petty.exceptions.DataAccessException;
import logikcode.springframework.petty.fauxspring.Model;
import logikcode.springframework.petty.model.Vets;
import logikcode.springframework.petty.services.map.VetMapService;
import logikcode.springframework.petty.services.service.ClinicService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;
@Controller
public class VetController {
    private final ClinicService clinicService;
    private final VetMapService vetService;

    public VetController(ClinicService clinicService, VetMapService vetService) {
        this.clinicService = clinicService;
        this.vetService = vetService;
    }
    @RequestMapping (value = "/vets.html", method = RequestMethod.GET)
    public String listVets(Model model){

        model.addAttribute("vets", vetService.findAll());
        return "vets/index";
    }

    public String showVetList(Map<String, Object> model) throws DataAccessException{
        Vets vets = new Vets();
        vets.getVetList().addAll(this.clinicService.findVets());
        model.put("vets", vets);

        return "vets/vetList";
    }
    @RequestMapping(value = {"/vets.json", "vets.xml"}, method = RequestMethod.GET)
    @ResponseBody
    public Vets showResourcesVetList() throws DataAccessException {
        Vets vets = new Vets();
        vets.getVetList().addAll(this.clinicService.findVets());
        return vets;
    }
}
