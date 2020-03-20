package com.example.demo.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

import com.example.demo.models.Type;
import com.example.demo.Exceptions.ResourceNotFoundException;
import com.example.demo.dao.TypeRepository;

@Service
public class TypeService {
     
    @Autowired
    TypeRepository repository;
     
    public Type createType(Type type) {
        return repository.save(type);
    }
    
    
    public Type getTypeByName(String typeName) {

        Optional <Type> typeDb = this.repository.findByTypeName(typeName);

        if (typeDb.isPresent()) {
            return typeDb.get();
        } else {
            throw new ResourceNotFoundException("Record not found");
        }
    }
}