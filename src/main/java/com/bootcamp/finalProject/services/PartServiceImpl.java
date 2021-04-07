package com.bootcamp.finalProject.services;

import com.bootcamp.finalProject.model.Part;
import com.bootcamp.finalProject.repositories.PartRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PartServiceImpl implements PartService {

    private final PartRepository repo;

    public PartServiceImpl(PartRepository repo) {
        this.repo = repo;
    }

    @Override
    public void save(Part part) {
        repo.save(part);
    }

    @Override
    public void delete(Long id) {
        repo.deleteById(id);
    }

    @Override
    public Part findById(Long id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    public List<Part> findAll() {
        return repo.findAll();
    }
}
