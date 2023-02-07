package org.example.restcontroller;

import org.example.model.Admission;
import org.example.repository.AdmissionRepository;
import org.example.service.AdmissionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Controller
public class AdmissionController {
    private static final Logger LOG = LoggerFactory.getLogger(AdmissionController.class);

    @Autowired
    private AdmissionRepository admissionRepository;

    @Autowired
    private AdmissionService admissionService;

    @GetMapping(path= "/greeting")
    public @ResponseBody
    String getGreeting() { return "Hello, world!"; }

    @GetMapping(path = "/fetchAdmissions")
    public @ResponseBody
    List<Admission> fetchAdmissions() {
        List<Admission> admissionList = admissionService.getAllAdmissions();
        LOG.info("Getting all the admission list");
        return admissionList;
    }

    @PostMapping(path = "/addAdmission")
    public @ResponseBody
    String addAdmission(@RequestBody Admission admission) throws Exception {
        validateDOB(admission.getBirthDate());
        admissionService.saveOrUpdate(admission);
        return "Admission added successfully. Admission id: " + admission.getId();
    }

    @PostMapping(path = "/editAdmission")
    public @ResponseBody
    String editAdmission(@RequestBody Admission admission) throws Exception {
        validateDOB(admission.getBirthDate());
        if(admissionRepository.existsById(admission.getId())) {
            if(!admission.getSourceId().equalsIgnoreCase("local"))
                admission.setCategory(admissionRepository.findById(admission.getId()).getCategory());
            admissionService.saveOrUpdate(admission);
            return "Admission updated successfully";
        } else {
            return "Admission not found";
        }
    }

    public void validateDOB(Date date) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date today = sdf.parse(sdf.format(new Date()));
        if(date.after(today))
            throw new Exception("Birth date cannot be in future");
    }

}
