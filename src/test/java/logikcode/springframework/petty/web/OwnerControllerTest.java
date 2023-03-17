package logikcode.springframework.petty.web;

import logikcode.springframework.petty.model.Owner;
import logikcode.springframework.petty.services.service.ClinicService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringJUnitWebConfig(locations = {"classpath:spring/mvc-test-config.xml", "classpath:spring/mvc-core-config.xml"})
@ExtendWith(MockitoExtension.class)
class OwnerControllerTest {
//    @Autowired
    @Mock
    OwnerController ownerController;
//    @Autowired
    @Mock
    ClinicService clinicService;
    MockMvc mockMvc;
    @BeforeEach
    void init(){
       mockMvc = MockMvcBuilders.standaloneSetup(ownerController).build();
    }
    @Test
    void testNotFoundLastName() throws Exception {
        mockMvc.perform(get("/owners").param("last name", "Not Found"))
                .andExpect(status().isOk())
                .andExpect(view().name("owners/findOwners"));
    }
    @Captor
    ArgumentCaptor<String> captor;
    @Test
    void testReturnOfOwnerList() throws Exception {
        Collection<Owner> owners = Arrays.asList(new Owner(1L, "", ""), new Owner(2L, "", ""));

        given(clinicService.findOwnerByLastName(""))
                .willReturn(owners);

      mockMvc.perform(get("/owners")).andExpect(status().isOk())
                .andExpect(view().name("views/ownerList"));
      then(clinicService).should().findOwnerByLastName(captor.capture());
      assertEquals(captor.getValue(), "");
    }

    @Test
    void testValidOwnerPostRequest() throws Exception{
        mockMvc.perform(post("/owner/new")
                .param("firstName", "Emmanuel")
                .param("lastName", "Boniface")
                .param("Address", "my home address")
                .param("City", "Lagos")
                .param("telephone", "234123456789"))
                .andExpect(status().isOk());
    }
    @Test
    void testInValidOwnerPostRequest() throws Exception{
        mockMvc.perform(post("/owner/new")
                        .param("firstName", "Emmanuel")
                        .param("lastName", "Boniface")
                        .param("City", "Lagos"))
                .andExpect(status().isOk())
                .andExpect(model().attributeHasErrors("owner"))
                .andExpect(model().attributeHasFieldErrors("owner", "address"))
                .andExpect(model().attributeHasFieldErrors("owner", "telephone"))
                .andExpect(view().name("owners/createOrUpdateOwnerForm"));
    }

    @Test
    void testProcessFormUpdatePostRequest() throws Exception{
        mockMvc.perform(post("/owners/{ownerId}/edit", 1)
                        .param("firstName", "Emmanuel")
                        .param("lastName", "Boniface")
                        .param("Address", "my home address")
                        .param("City", "Lagos")
                        .param("telephone", "234123456789"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("owners/createOrUpdateOwnerForm"));
    }

    @Test
    void testProcessFormUpdateInvalidPostRequest() throws Exception{
        mockMvc.perform(post("/owners/{ownerId}/edit", 1)
                        .param("firstName", "Emmanuel")
                        .param("lastName", "Boniface")
                        .param("Address", "my home address")
                        .param("City", "Lagos"))

                .andExpect(status().isOk())
                .andExpect(view().name("owners/createOrUpdateOwnerForm"));
    }
}