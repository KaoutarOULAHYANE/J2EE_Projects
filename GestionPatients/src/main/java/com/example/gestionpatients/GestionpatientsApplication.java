package com.example.gestionpatients;

import com.example.gestionpatients.dao.PatientRepository;
import com.example.gestionpatients.entities.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@SpringBootApplication
public class GestionpatientsApplication implements CommandLineRunner {

    @Autowired
    private PatientRepository patientRepository;

    public static void main(String[] args) {
        SpringApplication.run(GestionpatientsApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        DateFormat df = new SimpleDateFormat("dd-mm-yyyy");
        patientRepository.save(new Patient(null,"Sarah",df.parse("31-01-1982"),false));
        patientRepository.save(new Patient(null,"Ahmed",df.parse("18-05-1990"),true));
        patientRepository.save(new Patient(null,"Salim",df.parse("03-12-2002"),true));
        patientRepository.save(new Patient(null,"Badr",df.parse("22-04-1985"),true));
        patientRepository.save(new Patient(null,"Amina",df.parse("01-01-1993"),false));
        patientRepository.save(new Patient(null,"Haitam",df.parse("23-12-1997"),true));
        patientRepository.save(new Patient(null,"Samir",df.parse("02-06-1970"),true));
        patientRepository.save(new Patient(null,"Akram",df.parse("26-11-2005"),true));
        patientRepository.save(new Patient(null,"Maher",df.parse("13-10-1991"),true));
        patientRepository.save(new Patient(null,"Maram",df.parse("14-02-1998"),false));
        patientRepository.save(new Patient(null,"Majed",df.parse("28-07-2004"),true));
        patientRepository.save(new Patient(null,"Khaled",df.parse("29-02-2020"),true));
        patientRepository.save(new Patient(null,"Karmen",df.parse("17-05-1999"),true));
        patientRepository.save(new Patient(null,"Rayane",df.parse("29-11-2001"),false));
        patientRepository.save(new Patient(null,"Amal",df.parse("24-02-1996"),false));

        /*Page<Patient> patients = patientRepository.findByNameContains("Ah",PageRequest.of(0,2));

        *//*Nbr des élements dans la page*//*
        System.out.println(patients.getSize());

        *//*Nbr des pages*//*
        System.out.println(patients.getTotalPages());

        *//*Nbr des pages*//*
        System.out.println(patients.getTotalElements());

        *//*Contenu de la page*//*
        patients.getContent().forEach( patient ->
                System.out.println(patient));*/
    }
}
