package health.controller;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import health.beans.Vitals;
import health.beans.VitalsLinkedList;


@Controller
public class VitalsController {
	
    @Autowired
    private VitalsLinkedList vitalsLinkedList;
    
    @GetMapping("/inputVital")
    public String inputVital(Model model) {
        model.addAttribute("newVital", new Vitals());
        return "inputVital";
    }
    
    @PostMapping("/inputVital")
    public String addVital(@ModelAttribute Vitals newVital) {
        newVital.setInputDate(LocalDate.now());
        vitalsLinkedList.addVital(newVital);
        return "redirect:/viewVitals";
    }
    
    @GetMapping("/viewVitals")
    public String getAllVitals(Model model) {
        model.addAttribute("vitalsList", vitalsLinkedList.getAllVitals());
        return "viewVitals";
    }
    
    @GetMapping("/editVital/{id}")
    public String editVital(@PathVariable("id") long id, Model model) {
        Vitals vitalToEdit = vitalsLinkedList.getVitalsById(id);
        if (vitalToEdit != null) {
            model.addAttribute("vitalToEdit", vitalToEdit);
            return "editVital";
        }
        return "redirect:/viewVitals";
    }
    
    @PostMapping("/editVital")
    public String updateVital(@ModelAttribute Vitals updatedVital) {
        vitalsLinkedList.updateVitals(updatedVital);
        return "redirect:/viewVitals";
    }
    
    @GetMapping("/deleteVital/{id}")
    public String deleteVital(@PathVariable("id") long id) {
        vitalsLinkedList.deleteVitalsById(id);
        return "redirect:/viewVitals";
    }
}
