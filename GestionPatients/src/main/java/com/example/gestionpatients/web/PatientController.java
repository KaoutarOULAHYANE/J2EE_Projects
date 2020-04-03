package com.example.gestionpatients.web;

import com.example.gestionpatients.dao.PatientRepository;
import com.example.gestionpatients.entities.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class PatientController {
    @Autowired
    private PatientRepository patientRepository;

    @GetMapping(path="/index")
    public String index(){
        return "index";
    }

    @GetMapping(path="/patients")
    public String patients(Model model,
                           @RequestParam(name = "page",defaultValue = "0")int page,
                           @RequestParam(name = "size",defaultValue = "5")int size,
                           @RequestParam(name = "motCle",defaultValue = "")String motCle){
        Page<Patient> patients =
                patientRepository.findByNameContains(
                        motCle,
                        PageRequest.of(page,size));
        model.addAttribute("patients",patients.getContent());
        model.addAttribute("size",size);
        model.addAttribute("currentPage",page);
        model.addAttribute("motCle",motCle);
        model.addAttribute("pages",new int[patients.getTotalPages()]);
        return "patients";
    }

    @GetMapping(path="/deletePatient")
    public String deletePatient(
                           @RequestParam(name = "id")Long id){
        patientRepository.deleteById(id);
        return "patients";
    }

    @GetMapping(path = "/updatePage")
    public String updatePage(Model model,
            @RequestParam(name = "id")Long id) {
        Patient patient = patientRepository.getOne(id);
        model.addAttribute("patient", patient);
        return "updatePatient";
    }

    @GetMapping(path = "/updatePatient")
    public RedirectView updatePatient(
            @RequestParam(name = "id")Long id,
            @RequestParam(name = "name")String name,
            @RequestParam(name = "dateNaissance")String dateNaissance,
            @RequestParam(name = "malade")boolean malade) throws ParseException {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Patient patient = new Patient(id,name,df.parse(dateNaissance),malade);
        patientRepository.save(patient);
        return new RedirectView("patients");
    }

    @GetMapping(path = "/addPage")
    public String addPage(){
        return "addPatient";
    }

    @GetMapping(path = "/addPatient")
    public RedirectView addPatient(
            @RequestParam(name = "name")String name,
            @RequestParam(name = "dateNaissance")String dateNaissance,
            @RequestParam(name = "malade")boolean malade) throws ParseException {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Patient patient = new Patient(null,name,df.parse(dateNaissance),malade);
        patientRepository.save(patient);
        return new RedirectView("patients");
    }
}
