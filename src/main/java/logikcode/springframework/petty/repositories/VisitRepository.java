package logikcode.springframework.petty.repositories;

import logikcode.springframework.petty.exceptions.DataAccessException;
import logikcode.springframework.petty.model.Visit;

import java.util.List;

public interface VisitRepository extends CrudRepository<Visit, Long> {
    /**
     * Save a <code>Visit</code> to the data store, either inserting or updating it.
     *
     * @param visit the <code>Visit</code> to save
     * @return
     * @see BaseEntity#isNew
     */
   Visit save(Visit visit);

    List<Visit> findByPetId(Integer petId);

}
