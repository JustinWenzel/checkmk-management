package com.checkmk.checkmk_management.host;

import org.springframework.stereotype.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.checkmk.checkmk_management.host.dto.HostFormDTO;

import jakarta.validation.Valid;



@Controller
@RequiredArgsConstructor
public class HostController {
    private final HostService hostService;

    @GetMapping("/monitoring/create_host")
    public String createHostPage(Model model) {
        model.addAttribute("hostForm", new HostFormDTO());
        return "/create_host";
        
    }


    @PostMapping("/monitoring/create_host")
    public String postNewHost(@Valid @ModelAttribute("hostForm") HostFormDTO hostFormDTO, BindingResult result, RedirectAttributes redirectAttributes) {
        
        if (result.hasErrors()) {
            return "/create_host";
        }

            hostService.createHost(hostFormDTO);
            redirectAttributes.addFlashAttribute("success", "Host " + hostFormDTO.getName() + " created successfully!");
            
        // Redirect needs the path
        return "redirect:/monitoring/create_host";
    }
    
    
    
}
