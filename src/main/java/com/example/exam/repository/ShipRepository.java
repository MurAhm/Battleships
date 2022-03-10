package com.example.exam.repository;

import com.example.exam.model.entity.Ship;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShipRepository extends JpaRepository<Ship, Long> {

    @Query("select s from Ship s where s.health > 0  order by s.id, s.health, s.power desc ")
List<Ship> findAllByIdAndAndHealthAndPower();
}
