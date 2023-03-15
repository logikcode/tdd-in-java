package logikcode.springframework.petty.repositories;

import logikcode.springframework.petty.exceptions.DataAccessException;
import logikcode.springframework.petty.model.Vet;

import java.util.Collection;

public interface VetRepository extends CrudRepository<Vet, Long> {
    /**
     * Retrieve all <code>Vet</code>s from the data store.
     *
     * @return a <code>Collection</code> of <code>Vet</code>s
     */
    Collection<Vet> findAll();
}
