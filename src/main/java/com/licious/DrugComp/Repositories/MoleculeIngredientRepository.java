package com.licious.DrugComp.Repositories;

import com.licious.DrugComp.models.MoleculeIngredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MoleculeIngredientRepository extends JpaRepository<MoleculeIngredient, Integer> {
    MoleculeIngredient findById(int id);
    List<MoleculeIngredient> findByMoleculeId(int moleculeId);
    List<MoleculeIngredient> findByIngredientId(int ingredientId);

    @Query(value = "select ingredient_id from molecule_ingredients where molecule_id=?1",nativeQuery = true)
    List<Integer> findAllMoleculeIngredientsByMoleculeId(int molecule_id);
}
