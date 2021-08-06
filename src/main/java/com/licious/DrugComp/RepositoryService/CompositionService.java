package com.licious.DrugComp.RepositoryService;

import com.licious.DrugComp.Repositories.CompositionRepository;
import com.licious.DrugComp.models.Composition;
import com.licious.DrugComp.models.CompositionIngredient;
import com.licious.DrugComp.models.Ingredient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompositionService {
    @Autowired
    private CompositionRepository compositionRepository;

    public Composition getCompositionById(int id) {
        return compositionRepository.findById(id).get();
    }
    public Composition getCompositionByName(String name) {
        return compositionRepository.findByName(name);
    }
    public List<Composition> getAllCompositions() {
        return compositionRepository.findAll();
    }

}
