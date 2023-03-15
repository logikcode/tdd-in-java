package logikcode.springframework.petty.repositories;

import logikcode.springframework.petty.exceptions.DataAccessException;
import logikcode.springframework.petty.model.Pet;
import logikcode.springframework.petty.model.PetType;

import java.util.List;

public interface PetRepository extends CrudRepository<Pet, Long> {
    /**
     * Retrieve all <code>PetType</code>s from the data store.
     *
     * @return a <code>Collection</code> of <code>PetType</code>s
     */
    List<PetType> findPetTypes() throws DataAccessException;

    /**
     * Retrieve a <code>Pet</code> from the data store by id.
     *
     * @param id the id to search for
     * @return the <code>Pet</code> if found
     * @throws org.springframework.dao.DataRetrievalFailureException if not found
     */
    Pet findById(int id) throws DataAccessException;

    /**
     * Save a <code>Pet</code> to the data store, either inserting or updating it.
     *
     * @param pet the <code>Pet</code> to save
     * @see BaseEntity#isNew
     */
    Pet save(Pet pet);
}
