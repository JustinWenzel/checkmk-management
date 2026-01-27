package com.checkmk.checkmk_management.dashboard;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class DashboardController {
    @GetMapping("/dashboard/current_problems")
    public String showCurrentProblems() {
        return "current_problems";
    }
    
}
