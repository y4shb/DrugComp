package com.licious.DrugComp.Repositories;

import com.licious.DrugComp.models.MoleculeIngredient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MoleculeIngredientRepository extends JpaRepository {
    MoleculeIngredient findById(int id);
    List<MoleculeIngredient> findAllByMoleculeId(int moleculeId);
    List<MoleculeIngredient> findAllByIngredientId(int ingredientId);
}
