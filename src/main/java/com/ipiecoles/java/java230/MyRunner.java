package com.ipiecoles.java.java230;

import com.ipiecoles.java.java230.model.Employe;
import com.ipiecoles.java.java230.model.Manager;
import com.ipiecoles.java.java230.model.Technicien;
import com.ipiecoles.java.java230.repository.EmployeRepository;
import com.ipiecoles.java.java230.repository.ManagerRepository;
import com.ipiecoles.java.java230.repository.TechnicienRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Component
public class MyRunner implements CommandLineRunner {

    @Autowired
    private EmployeRepository employeRepository;
    @Autowired
    private TechnicienRepository technicienRepository;
    @Autowired
    private ManagerRepository managerRepository;

    @Override
    public void run(String... strings) throws Exception {
        System.out.println("====================");
        System.out.println("Salut les plouks ! ");
        System.out.println("====================\n");


//        Long nbEmployes = employeRepository.count();
//        System.out.println("Il y a " + nbEmployes + " employés dans la base de données");
//        Optional<Employe> employe = employeRepository.findById(55L);
//        if(employe.isEmpty()){
//            System.out.println("Employé inexistant !");
//        } else {
//            Employe e = employe.get();
//            e.setSalaire(e.getSalaire() + 200);
//            e = employeRepository.save(e);
//            System.out.println(e.toString());
//        }
//        for(Employe emp : employeRepository.findAll()){
//            System.out.println(emp.toString());
//        }

        Employe e = employeRepository.findByMatricule("C00002");
        if(e != null){
            System.out.println("\n L'Employé portant le matricule COOOO2 est le suivant :");
            System.out.println(e.toString());
            System.out.println("\n");
        } else {
            System.out.println("\nEmployé non trouvé\n");
        }


        List<Employe> employeList = employeRepository.findBySalaireGreaterThanOrderBySalaireDesc(2495.0);
        System.out.println("\n AFFICHAGE D'UNE LISTE D'EMPLOYE GAGNANT + DE 2495€ : \n");
        for(Employe employe : employeList){
            System.out.println(employe.toString());
        }
        System.out.println("\n");



        PageRequest pageRequest = PageRequest.of(1,5, Sort.Direction.ASC,"prenom");
        Page<Employe> employeList2 = employeRepository.findByNomIgnoreCase("Andre", pageRequest);
        System.out.println("\n Le nombre total d'element : " + employeList2.getTotalElements());
        System.out.println("Le nombre total de Pages : " + employeList2.getTotalPages());
        System.out.println("La taille de la page : " + employeList2.getSize());
        System.out.println("Trié par : " + employeList2.getSort().toString());
        System.out.println("AFFICHAGE DE LA PAGE 0 ....... \n");
        for(Employe employe : employeList2){
            System.out.println(employe.toString());
        }
        System.out.println("\n");



        List<Employe> employeList3 = employeRepository.findByNomOrPrenomAllIgnoreCase("Lisa");
        System.out.println("\n AFFICHAGE D'UNE LISTE D'EMPLOYE QUI s'appellent Lisa : \n");
        for(Employe employe : employeList3){
            System.out.println(employe.toString());
        }
        System.out.println("\n");


//        List<Employe> employeList4 = employeRepository.findEmployesPlusRiches();
//        System.out.println("\n AFFICHAGE D'UNE LISTE D'EMPLOYE LES PLUS RICHES : \n");
//        for(Employe employe : employeList4){
//            System.out.println(employe.toString());
//        }
//        System.out.println("\n");


        System.out.println("============================");
        System.out.println("============================\n");

        Employe e5 = employeRepository.findById(3L).get();
        System.out.println(e5.toString());

        System.out.println("\n============================");
        System.out.println("============================\n");

        Iterable<Technicien> employeList5 = technicienRepository.findAll();
        System.out.println("\n FIND ALL : \n");
        for(Technicien employe : employeList5){
            //System.out.println(employe.getMatricule();
            System.out.println(employe.getManager().getNom());
            //System.out.println(employe.getPrimeAnnuelle());
        }


        System.out.println("\n============================");
        System.out.println("============================\n");

        Optional<Manager> manager = managerRepository.findById(44L);
        for(Technicien technicien : manager.get().getEquipe()){
            System.out.println(manager.toString());
        }



    }
    public static void print(Object t) {
        System.out.println(t);
    }
}
