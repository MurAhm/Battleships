package com.example.exam.web;

import com.example.exam.model.view.AttackerViewModel;
import com.example.exam.model.view.ShipViewModel;
import com.example.exam.sec.CurrentUser;
import com.example.exam.service.ShipService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Arrays;
import java.util.List;

@Controller
public class HomeController {
    private final CurrentUser currentUser;
    private final ShipService shipService;


    public HomeController(CurrentUser currentUser, ShipService shipService) {
        this.currentUser = currentUser;

        this.shipService = shipService;
    }

    @GetMapping("/")
    public String index(Model model) {

        if (currentUser.getId() == null) {
            return "index" ;
        }

        List<ShipViewModel> ships = shipService.findAllShipsByIdAndHealthAndPower();

        List<AttackerViewModel> attackers=  this.shipService.findShipsByOwner(currentUser.getId());
        List<AttackerViewModel> defenders=  this.shipService.findShipOfAnotherOwners(currentUser.getId());



        model.addAttribute("ships", ships);
        model.addAttribute("attackers", attackers);
        model.addAttribute("defenders", defenders);



        return "home";

    }
}
