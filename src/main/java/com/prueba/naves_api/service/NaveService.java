package com.prueba.naves_api.service;

import com.prueba.naves_api.model.Nave;
import com.prueba.naves_api.repository.NaveRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NaveService {

    @Autowired
    private NaveRepository naveRepository;

    @Cacheable(value = "naves", key = "#pageable")
    public Page<Nave> getAllNaves(Pageable pageable) {
        return naveRepository.findAll(pageable);
    }

    @Cacheable("naves")
    public Nave getNaveById(Long id) {
        return naveRepository.findById(id).orElse(null);
    }

    @Cacheable(value = "naves", key = "#name")
    public List<Nave> getNavesByName(String name) {
        return naveRepository.findByNameContainingIgnoreCase(name);
    }

    @CacheEvict(value = "naves", allEntries = true)
    public Nave saveNave(Nave nave) {
        return naveRepository.save(nave);
    }

    @CacheEvict(value = "naves", allEntries = true)
    public Nave updateNave(Long id, Nave naveData) {
        Nave nave = naveRepository.findById(id).orElse(null);
        if (nave != null) {
            nave.setName(naveData.getName());
            nave.setSeries(naveData.getSeries());
            return naveRepository.save(nave);
        }
        return null;
    }

    @CacheEvict(value = "naves", allEntries = true)
    public void deleteNave(Long id) {
        naveRepository.deleteById(id);
    }
}
