package org.example.repository;

import org.example.model.Admission;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdmissionRepository extends CrudRepository<Admission, Integer> {
    Admission findById(int id);
}
