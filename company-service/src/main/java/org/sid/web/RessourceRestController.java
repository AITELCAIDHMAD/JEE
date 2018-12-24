package org.sid.web;

import java.util.List;

import org.sid.dao.RessourceRepository;
import org.sid.entities.Ressource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RessourceRestController {

    @Autowired
    private RessourceRepository ressourceRepository;

    @GetMapping("/ressources")
    public List<Ressource> getAll() {
        return ressourceRepository.findAll();
    }

    @GetMapping("/ressources/{id}")
    public Ressource getRessourceById(@PathVariable(name = "id") Long id) {
        return ressourceRepository.findById(id).get();
    }

    @PostMapping("/ressources")
    public Ressource save(@RequestBody Ressource Ressource) {
        return ressourceRepository.save(Ressource);
    }
    
    @PutMapping("/ressources/{id}")
    public Ressource update(@PathVariable(name = "id") Long id, @RequestBody Ressource Ressource) {
        Ressource.setNumero(id);
        return ressourceRepository.save(Ressource);
    }

    @DeleteMapping("/ressources/{id}")
    public void delete(@PathVariable(name = "id") Long id) {
        ressourceRepository.deleteById(id);
    }

}
