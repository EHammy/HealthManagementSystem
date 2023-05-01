package health.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import health.beans.Routines;
import health.repository.RoutineRepository;
import health.beans.SearchForm;

@Controller
public class RoutineController {
    
    @Autowired
    private RoutineRepository routineRepository;
    
    @GetMapping("/inputRoutine")
    public String inputRoutine(Model model) {
        model.addAttribute("newRoutine", new Routines());
        return "inputRoutine";
    }
    
    @PostMapping("/inputRoutine")
    public String addRoutine(@ModelAttribute Routines newRoutine) {
        routineRepository.save(newRoutine); // Add the new routine to the database
        return "redirect:/viewRoutines";
    }
    
    @GetMapping("/viewRoutines")
    public String viewRoutines(Model model, @ModelAttribute("searchForm") SearchForm searchForm) {
        if (searchForm.getSearchTerm() != null && !searchForm.getSearchTerm().isEmpty()) {
        	List<Routines> matchedRoutines = routineRepository.findByRoutineNameContainingIgnoreCase(searchForm.getSearchTerm());
            model.addAttribute("routinesList", matchedRoutines);
        } else {
            model.addAttribute("routinesList", routineRepository.findAll());
        }
        model.addAttribute("searchForm", new SearchForm());
        return "viewRoutines";
    }

    @GetMapping("/editRoutine/{id}")
    public String editRoutine(@PathVariable("id") long id, Model model) {
        Routines routineToEdit = routineRepository.findById(id).orElse(null);
        if (routineToEdit != null) {
            model.addAttribute("routineToEdit", routineToEdit);
            return "editRoutine";
        }
        return "redirect:/viewRoutines";
    }
    
    @PostMapping("/editRoutine")
    public String updateRoutine(@ModelAttribute Routines updatedRoutine) {
        routineRepository.save(updatedRoutine);
        return "redirect:/viewRoutines";
    }

    @GetMapping("/deleteRoutine/{id}")
    public String deleteRoutine(@PathVariable("id") long id) {
        routineRepository.deleteById(id);
        return "redirect:/viewRoutines";
    }
    
}
