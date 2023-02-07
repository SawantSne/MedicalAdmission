package org.example.service;

import org.example.model.Admission;
import org.example.repository.AdmissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AdmissionService {

    @Autowired
    AdmissionRepository admissionRepository;

    public List<Admission> getAllAdmissions() {
        List<Admission> admissionList = new ArrayList<Admission>();
        admissionRepository.findAll().forEach(admission -> admissionList.add(admission));
        return admissionList;
    }

    public void saveOrUpdate(Admission admission) {
        admissionRepository.save(admission);
    }

    public void deleteById(int id) {
        admissionRepository.deleteById(id);
    }
}
