package com.licious.DrugComp.Repositories;

import com.licious.DrugComp.models.Composition;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CompositionRepository extends JpaRepository {
    List<Composition> findAll();
    Composition findById(int id);
    Composition findByName(String name);

}
