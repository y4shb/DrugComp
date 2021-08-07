package com.licious.DrugComp.Repositories;

import com.licious.DrugComp.models.Composition;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CompositionRepository extends JpaRepository<Composition, Integer> {
    Composition findByName(String name);
    Optional<Composition> findOneByName(String name);

}
