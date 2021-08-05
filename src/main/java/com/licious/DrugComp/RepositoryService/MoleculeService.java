package com.licious.DrugComp.RepositoryService;

import com.licious.DrugComp.Repositories.MoleculeRepository;
import com.licious.DrugComp.models.Molecule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MoleculeService {
    @Autowired
    private MoleculeRepository moleculeRepository;

    public Molecule getMoleculeById(int id) {
        return moleculeRepository.findById(id);
    }
    public List<Molecule> getMoleculeByName(String name) {
        return moleculeRepository.findByName(name);
    }
}
