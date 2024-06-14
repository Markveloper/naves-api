package com.prueba.naves_api.controller;

import com.prueba.naves_api.model.Nave;
import com.prueba.naves_api.service.NaveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/naves")
public class NaveController {

    @Autowired
    private NaveService naveService;

    @GetMapping
    public Page<Nave> getAllNaves(Pageable pageable) {
        return naveService.getAllNaves(pageable);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Nave> getNaveById(@PathVariable Long id) {
        try {
            if (id < 0) {
                throw new IllegalArgumentException("ID negativo proporcionado: "+ id);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        Nave nave = naveService.getNaveById(id);
        if (nave == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, id.toString());
        }

        return ResponseEntity.ok(nave);
    }

    @GetMapping("/search")
    public List<Nave> getNavesByName(@RequestParam String name) {
        return naveService.getNavesByName(name);
    }

    @PostMapping
    public Nave createNave(@RequestBody Nave nave) {
        return naveService.saveNave(nave);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Nave> updateNave(@PathVariable Long id, @RequestBody Nave naveData) {
        Nave updatedNave = naveService.updateNave(id, naveData);
        if (updatedNave != null) {
            return ResponseEntity.ok(updatedNave);
        }else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Nave no encontrada");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNave(@PathVariable Long id) {
        try {
            naveService.deleteNave(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
