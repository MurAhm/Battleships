package com.example.exam.service;

import com.example.exam.model.service.ShipServiceModel;
import com.example.exam.model.view.AttackerViewModel;
import com.example.exam.model.view.ShipViewModel;

import java.util.List;

public interface ShipService {
    void addShip(ShipServiceModel shipServiceModel);

    List<ShipViewModel> findAllShipsByIdAndHealthAndPower();

    List<AttackerViewModel> findShipsByOwner(Long id);

    List<AttackerViewModel> findShipOfAnotherOwners(Long id);


    void fire(Long attackerId, Long defenderId);
}
