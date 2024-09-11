package com.emilpiekos.coockingbook;

import com.emilpiekos.coockingbook.kategoria.KategoriaRepository;
import com.emilpiekos.coockingbook.przepis.Przepis;
import com.emilpiekos.coockingbook.przepis.PrzepisRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
public class MainController {

    private final PrzepisRepository przepisRepository;
    private final KategoriaRepository kategoriaRepository;

    public MainController(PrzepisRepository przepisRepository, KategoriaRepository kategoriaRepository) {
        this.przepisRepository = przepisRepository;
        this.kategoriaRepository = kategoriaRepository;
    }

    @GetMapping("/")
    public String home(Model model, @RequestParam(required = false) boolean sortByLikes, @RequestParam(required = false) Long id) {
        if (sortByLikes) {
            model.addAttribute("przepisy", przepisRepository.findAllOrderByLikes());
        } else {
            if (id != null) {
                model.addAttribute("przepisy", przepisRepository.findAllByKategoria_Id(id));
            } else {
                model.addAttribute("przepisy", przepisRepository.findAll());
            }
        }
        return "home";
    }

    @GetMapping("/przepis")
    public String przepis(Model model, @RequestParam Long id) {
        Optional<Przepis> przepis = przepisRepository.findById(id);
        if (przepis.isPresent()) {
            model.addAttribute("przepis", przepis.get());
            return "przepis";
        } else {
            return "error";
        }
    }

    @GetMapping("/kategorie")
    public String kategorie(Model model, @RequestParam(required = false) Long id) {
        model.addAttribute("kategorie", kategoriaRepository.findAll());
        return "kategorie";
    }

    @GetMapping("/form")
    public String dodaj(Model model, @RequestParam(required = false) Long id) {
        if (id != null) {
            model.addAttribute("przepis", przepisRepository.findById(id));
            przepisRepository.deleteById(id);
        } else {
            model.addAttribute("przepis", new Przepis());
        }
        model.addAttribute("kategorie", kategoriaRepository.findAll());
        return "form";
    }

    @PostMapping("/save")
    public String dodajPrzepis(Przepis przepis) {
        if (przepisRepository.findById(przepis.getId()).isPresent()) {
            przepisRepository.delete(przepis);
            przepisRepository.save(przepis);
            return "redirect:/przepis?id=" + przepis.getId();
        } else {
            przepisRepository.save(przepis);
            return "redirect:/przepis?id=" + przepis.getId();
        }
    }

    @PostMapping("/polub")
    public String polubPrzepis(@RequestParam(required = false) Long id) {
        Optional<Przepis> przepis = przepisRepository.findById(id);
        if (przepis.isPresent()) {
            przepisRepository.polubPrzepis(id, przepis.get().getLikes() + 1);
        }
        return "redirect:/przepis?id=" + id;
    }

    @PostMapping("/usun")
    public String usunPrzepis(@RequestParam(required = false) Long id) {
        Optional<Przepis> przepis = przepisRepository.findById(id);
        if (przepis.isPresent()) {
            przepisRepository.usunPrzepis(id);
        }
        return "redirect:/";
    }
}
