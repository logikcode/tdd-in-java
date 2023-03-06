package logikcode.springframework.petty.controllers;

import logikcode.springframework.petty.fauxspring.Model;
import logikcode.springframework.petty.model.Speciality;
import logikcode.springframework.petty.model.Vet;
import logikcode.springframework.petty.services.SpecialtyService;
import logikcode.springframework.petty.services.VetService;
import logikcode.springframework.petty.services.map.SpecialityMapService;
import logikcode.springframework.petty.services.map.VetMapService;
import logikcode.springframework.vetspring.ModelMapImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class VetControllerTest {
    VetMapService vetService;
    SpecialtyService specialtyService;
    VetController vetController;
    @BeforeEach
    void setUp() {
        specialtyService = new SpecialityMapService();
        vetService = new VetMapService(specialtyService);
        vetController = new VetController(vetService);
        Speciality vetSpecialty = new Speciality("Experienced working with cats for 5 years");
        Set<Speciality> setsOfSpecialties = new HashSet<>();
        setsOfSpecialties.add(vetSpecialty);

        Vet vet1 = new Vet(1L, "Mark", "John", setsOfSpecialties);
        Vet vet2 = new Vet(2L, "Erik", "Lars", setsOfSpecialties);
        Vet vet3 = new Vet(3L, "Dickson", "Kent", setsOfSpecialties);

        vetService.save(vet1);
        vetService.save(vet2);
        vetService.save(vet3);

    }

    @Test
    void listOfVets() {
        Model model = new ModelMapImpl();
        String view = vetController.listVets(model);
        assertEquals("vets/index", view);
        int vetNumbers = ((ModelMapImpl) model).getMap().get("vets").size();
        assertEquals(3, vetNumbers) ;
    }


}