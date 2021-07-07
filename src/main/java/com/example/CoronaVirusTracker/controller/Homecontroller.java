package com.example.CoronaVirusTracker.controller;

import com.example.CoronaVirusTracker.models.Locationstats;
import com.example.CoronaVirusTracker.services.CoronaVirusDataServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class Homecontroller {

    @Autowired
    CoronaVirusDataServices coronaVirusDataServices;

    @GetMapping("/")
    public String home(Model model){
        List<Locationstats> allatats=coronaVirusDataServices.getAllstats();
         int totalReportedcases=allatats.stream().mapToInt(stat ->stat.getLatestTotalcases()).sum();
         model.addAttribute("locationStats", allatats);
         model.addAttribute("totalReportedcases", totalReportedcases);

        return "home";
    }
}
