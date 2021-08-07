package com.licious.DrugComp.Repositories;

import com.licious.DrugComp.models.Molecule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MoleculeRepository extends JpaRepository<Molecule, Integer> {
    List<Molecule> findAll();
    Molecule findById(int id);
    List<Molecule> findByName(String name);

    Optional<Molecule> findOneByName(String name);
}
