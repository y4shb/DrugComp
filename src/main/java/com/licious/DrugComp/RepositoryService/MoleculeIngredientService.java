package com.licious.DrugComp.RepositoryService;

import com.licious.DrugComp.Repositories.MoleculeIngredientRepository;
import com.licious.DrugComp.models.MoleculeIngredient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MoleculeIngredientService {

    @Autowired
    MoleculeIngredientRepository moleculeIngredientRepository;

    public List<MoleculeIngredient> getMoleculeIngredientByIngredientId(int ingredientId) {
        return moleculeIngredientRepository.findByIngredientId(ingredientId);
    }
}
