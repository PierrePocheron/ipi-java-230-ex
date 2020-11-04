package com.ipiecoles.java.java230.repository;

import com.ipiecoles.java.java230.model.Technicien;
import org.joda.time.LocalDate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface TechnicienRepository extends PagingAndSortingRepository<Technicien,Long> {

    Technicien findByMatricule(String matricule);
    List<Technicien> findByNomAndPrenom(String nom, String prenom);
    List<Technicien> findByGrade(String grade);


}