package logikcode.springframework.petty.controllers;

import logikcode.springframework.petty.exceptions.DataAccessException;
import logikcode.springframework.petty.fauxspring.Model;
import logikcode.springframework.petty.model.Speciality;
import logikcode.springframework.petty.model.Vet;
import logikcode.springframework.petty.services.ClinicService;
import logikcode.springframework.petty.services.SpecialtyService;
import logikcode.springframework.petty.services.map.SpecialityMapService;
import logikcode.springframework.petty.services.map.VetMapService;
import logikcode.springframework.vetspring.ModelMapImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class VetControllerTest {
    @Mock
    Map<String, Object> model;
    @Mock
    ClinicService clinicService;
    @Mock
    VetMapService vetService;
    SpecialtyService specialtyService;
    @InjectMocks
    VetController vetController;
    List<Vet> listOfVets = new ArrayList<>();
    MockMvc mockMvc;
    @BeforeEach
    void setUp() throws DataAccessException {


        specialtyService = new SpecialityMapService();
        vetService = new VetMapService(specialtyService);
        vetController = new VetController(clinicService, vetService);
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

    @BeforeEach
    void init() throws DataAccessException {
        listOfVets.add(new Vet(1L, "Emmanuel", "Mikel", null));
        given(clinicService.findVets()).willReturn(listOfVets);
        mockMvc = MockMvcBuilders.standaloneSetup(vetController).build();
    }

    @Test
    void testVetControllerGetRequest(){
    try {
        mockMvc.perform(get("/vets.html"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("vets"))
                .andExpect(view().name("vets/vetList"));
    } catch (Exception ignored){

    }

    }
    @Test
    void showListOfVets() throws DataAccessException {
        String view = vetController.showVetList(model);
        then(clinicService).should().findVets();
        then(model).should().put(anyString(), any());
        System.out.println(view);
        assertEquals("vets/vetList", view);

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