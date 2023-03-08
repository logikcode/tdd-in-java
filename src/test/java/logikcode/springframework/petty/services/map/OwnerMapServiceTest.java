package logikcode.springframework.petty.services.map;

import logikcode.springframework.petty.model.Owner;
import logikcode.springframework.petty.model.PetType;
import logikcode.springframework.petty.services.PetService;
import logikcode.springframework.petty.services.PetTypeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
@DisplayName("Pet Type")
class OwnerMapServiceTest {
    PetTypeService petTypeService;
    PetService petService;
    OwnerMapService ownerMapService;

    @BeforeEach
    void setUp() {
        petTypeService = new PetTypeMapService();
        petService = new PetMapService();
        ownerMapService = new OwnerMapService(petTypeService, petService);
        System.out.println("First Before Each...");

    }
    @Test
    void testThatOwnerIsZero(){
        int zero = ownerMapService.findAll().size();
        assertEquals(0,zero);

    }


    @DisplayName("Pet Type")
    @Nested
    class TestCreatePetTypes{
        @BeforeEach
        void setUp(){
            PetType horse = new PetType(1L, "Horse");
            PetType dog = new PetType(2L, "Dog");
            PetType goat = new PetType(3L, "Goat");
            petTypeService.save(horse);
            petTypeService.save(dog);
            petTypeService.save(goat);

            System.out.println("Nested: 2nd Before Each");
        }
        @DisplayName("Verify Zero Pet Owners")
        @Test
        void testPetCount(){
            int count = petTypeService.findAll().size();
            assertEquals(3, count);
            assertNotEquals(0, count);

        }


        @DisplayName("Save Owner Tests")
        @Nested
        class TestSavedPetOwner{
        @BeforeEach
        void setUp(){
            ownerMapService.save(new Owner(2L, "John", "Stone"));
            System.out.println("Nest: Saved Owners Before Each SetUp ");

            }

            @Test
            void saveOwner(){
            Owner owner = new Owner(2L, "Deril", "Zacks");
            Owner persistedOwner = ownerMapService.save(owner);
            assertNotNull(persistedOwner);

            }

            @Test
            @DisplayName("Find Saved Owner Test")
            void testExistenceOfPetOwner(){
            Owner petOwner = ownerMapService.map.get(2L);
            assertNotNull(petOwner);
            }
        }

    }

}