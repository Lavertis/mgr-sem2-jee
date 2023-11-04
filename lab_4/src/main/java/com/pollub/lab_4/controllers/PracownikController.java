package com.pollub.lab_4.controllers;

import com.pollub.lab_4.beans.Pracownik;
import com.pollub.lab_4.dao.PracownikDao;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class PracownikController {
    @Autowired
    PracownikDao dao; // wstrzyknięcie dao z pliku XML

    @ExceptionHandler(Exception.class)
    public ModelAndView handleException(HttpServletRequest req, Exception e) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("exception", e);
        mav.addObject("url", req.getRequestURL());
        mav.setViewName("error");
        return mav;
    }

    /* Wynikiem działania metody jest przekazanie danych w modelu do
     * strony widoku addForm.jsp, która wyświetla formularz
     * wprowadzania danych, a „command” jest zastrzeżonym atrybutem
     * żądania, umożliwiającym wyświetlenie danych obiektu pracownika
     * w formularzu.
     */
    @RequestMapping("/addForm")
    public String showForm(Model m) {
        m.addAttribute("command", new Pracownik());
        return "addForm"; // przekierowanie do addForm.jsp
    }

    /* Metoda obsługuje zapis pracownika do BD. @ModelAttribute
     * umożliwia pobranie danych z żądania do obiektu pracownika.
     * Jawnie wskazano RequestMethod.POST (domyślnie jest to GET)
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(@ModelAttribute("pr") Pracownik pr) {
        dao.save(pr);
        return "redirect:/viewAll"; // przekierowanie do endpointa o URL: /viewAll
    }

    /* Metoda pobiera listę pracowników z BD i umieszcza je w modelu */
    @RequestMapping("/viewAll")
    public String viewAll(Model m) {
        List<Pracownik> list = dao.getAll();
        m.addAttribute("list", list);
        return "viewAll"; // przejście do widoku viewAll.jsp
    }

    /* Metoda obsługuje żądanie usunięcia pracownika o podanym id */
    @RequestMapping("/delete/{id}")
    public String delete(@PathVariable int id) {
        dao.delete(id);
        return "redirect:/viewAll";
    }

    @RequestMapping("/edit/{id}")
    public String edit(@PathVariable int id, Model m) {
        Pracownik pr = dao.getById(id);
        m.addAttribute("command", pr);
        return "editForm";
    }

    @RequestMapping(value = "/editSave", method = RequestMethod.POST)
    public String editSave(@ModelAttribute("pr") Pracownik pr) {
        dao.update(pr);
        return "redirect:/viewAll";
    }
}
