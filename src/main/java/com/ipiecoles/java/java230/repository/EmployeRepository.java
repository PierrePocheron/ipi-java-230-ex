package com.ipiecoles.java.java230.repository;

import com.ipiecoles.java.java230.model.Employe;
import org.joda.time.LocalDate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import java.util.List;
import java.util.Optional;

//public interface EmployeRepository extends CrudRepository<Employe,Long> {
public interface EmployeRepository extends PagingAndSortingRepository<Employe,Long> {

    Employe findByMatricule(String matricule);
    Optional<Employe> findById(Long id);
    List<Employe> findAll();
    List<Employe> findByNomAndPrenom(String nom, String prenom);
    List<Employe> findByNomIgnoreCase(String nom);
    Page<Employe> findByNomIgnoreCase(String nom, Pageable pageable);
    List<Employe> findByDateEmbaucheBefore(LocalDate dateEmbauche);
    List<Employe> findByDateEmbaucheAfter(LocalDate dateEmbauche);
    List<Employe> findBySalaireGreaterThanOrderBySalaireDesc(Double salaire);

    @Query("SELECT e from Employe e where lower(e.prenom) = lower(:nomOuPrenom) or lower(e.nom) = lower(:nomOuPrenom)")
    List<Employe> findByNomOrPrenomAllIgnoreCase(@Param("nomOuPrenom") String nomOuPrenom);

    @Query(value = "SELECT * FROM Employe WHERE salaire > (SELECT avg(e2.salaire) FROM Employe e2)", nativeQuery = true)
    List<Employe> findEmployesPlusRiches();

}