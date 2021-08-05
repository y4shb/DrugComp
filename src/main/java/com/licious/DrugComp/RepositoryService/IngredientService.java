package com.licious.DrugComp.RepositoryService;

import com.licious.DrugComp.Repositories.IngredientRepository;
import com.licious.DrugComp.models.Ingredient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IngredientService {
    @Autowired
    private IngredientRepository ingredientRepository;

    public Ingredient getIngredientById(int id) {
        return ingredientRepository.findById(id);
    }

    public Ingredient getIngredientByName(String name) {
        return ingredientRepository.findByName(name);
    }

    public List<Ingredient> getAllIngredients() {
        return ingredientRepository.findAll();
    }

}
