package com.example.exam.service.impl;

import com.example.exam.model.entity.Ship;
import com.example.exam.model.service.ShipServiceModel;
import com.example.exam.model.view.ShipViewModel;
import com.example.exam.repository.ShipRepository;
import com.example.exam.sec.CurrentUser;
import com.example.exam.service.CategoryService;
import com.example.exam.service.ShipService;
import com.example.exam.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ShipServiceImpl implements ShipService {
    private final ShipRepository shipRepository;
    private final ModelMapper modelMapper;
    private final UserService userService;
    private final CurrentUser currentUser;
    private final CategoryService categoryService;

    public ShipServiceImpl(ShipRepository shipRepository, ModelMapper modelMapper, UserService userService, CurrentUser currentUser, CategoryService categoryService) {
        this.shipRepository = shipRepository;
        this.modelMapper = modelMapper;
        this.userService = userService;
        this.currentUser = currentUser;
        this.categoryService = categoryService;
    }

    @Override
    public void addShip(ShipServiceModel shipServiceModel) {

        Ship ship = modelMapper
                .map(shipServiceModel, Ship.class);
        ship.setUser(userService.findById(currentUser.getId()));
        ship.setCategory(categoryService.findByCategoryNameEnum(shipServiceModel.getCategory()));

        shipRepository.save(ship);

    }

    @Override
    public List<ShipViewModel> findAllShipsByIdAndHealthAndPower() {
        return shipRepository.findAllByIdAndAndHealthAndPower()
                .stream()
                .map(s -> {
                    ShipViewModel viewModel = modelMapper.map(s, ShipViewModel.class);
                    viewModel.setUser(s.getUser().getUsername());
                    return viewModel;
                })
                .collect(Collectors.toList());
    }
}
