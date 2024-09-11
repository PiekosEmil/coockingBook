package com.emilpiekos.coockingbook.przepis;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PrzepisRepository extends CrudRepository<Przepis, Long> {

    @Query("SELECT p FROM Przepis p WHERE p.likes > 0 ORDER by p.likes DESC")
    List<Przepis> findAllOrderByLikes();

    List<Przepis> findAllByKategoria_Id(Long id);

    @Modifying
    @Transactional
    @Query("UPDATE Przepis p SET p.likes = :l where p.id = :id")
    void polubPrzepis(@Param(value = "id") Long id, @Param(value = "l") Integer l);

    @Modifying
    @Transactional
    @Query("DELETE FROM Przepis p WHERE p.id = :id")
    void usunPrzepis(@Param(value = "id") Long id);
}
