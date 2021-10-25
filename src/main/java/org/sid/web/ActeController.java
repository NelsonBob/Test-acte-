package org.sid.web;

import java.io.IOException;
import java.security.Principal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.sid.entity.Acte;
import org.sid.entity.User;
import org.sid.repository.ActeRepository;
import org.sid.repository.UserRepository;
import org.sid.service.ActeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ActeController {

    @Autowired
    ActeRepository acteRepository;

    @Autowired
    ActeService acteService;

    @Autowired
    UserRepository userRepository;

    @GetMapping("/actes")
    public String listActesUser(Model model, @RequestParam(name = "code", defaultValue = "-1") Long code,
            @RequestParam(name = "motCle", defaultValue = "") String motCle,
            @RequestParam("page") Optional<Integer> page, @RequestParam("size") Optional<Integer> size) {
        if (code == -1) {
            model.addAttribute("actes", new ArrayList<Acte>());
        } else {
            User e = new User();
            e.setId(code);
            int currentPage = page.orElse(0);
            int pageSize = size.orElse(5);
            Page<Acte> pageActes = acteRepository.chercher("%" + motCle + "%", code,
                    PageRequest.of(currentPage - 0, pageSize));
            System.out.println(code);
            model.addAttribute("actes", pageActes.getContent());
            int[] pages = new int[pageActes.getTotalPages()];
            model.addAttribute("actes", pageActes.getContent());
            model.addAttribute("pages", pages);
            model.addAttribute("pageCourante", currentPage);
            model.addAttribute("motCle", motCle);
        }
        return "security/listActeUser";
    }

    @RequestMapping(value = "/acte", method = RequestMethod.GET)
    public String listActeSauv(Model model, Principal principal,
            @RequestParam(name = "motCle", defaultValue = "") String motCle,
            @RequestParam("page") Optional<Integer> page, @RequestParam("size") Optional<Integer> size) {
        int currentPage = page.orElse(0);
        int pageSize = size.orElse(5);
        String status = "sauvegarder";
        User user = userRepository.findByEmail(principal.getName());
        Page<Acte> pageUsers = acteRepository.findTop1ByUserAndStatusAndNomEnfLike(
                userRepository.findByID(user.getId()), status, "%" + motCle + "%",
                PageRequest.of(currentPage - 0, pageSize));
        model.addAttribute("actes", pageUsers.getContent());
        int[] pages = new int[pageUsers.getTotalPages()];
        model.addAttribute("pages", pages);
        model.addAttribute("pageCourante", currentPage);
        model.addAttribute("motCle", motCle);
        return "security/listActe";
    }

    @RequestMapping(value = "/acte/add", method = RequestMethod.GET)
    public String createacte(Model model, Principal principal) {
        String status = "sauvegarder";
        List<String> listSexe = Arrays.asList("Feminin", "Masculin");
        model.addAttribute("listSexe", listSexe);
        model.addAttribute("acte", new Acte());
        User user = userRepository.findByEmail(principal.getName());
        model.addAttribute("acteForm", acteRepository.findActeByStatusAndUser(status, user));

        return "security/acteAdd";
    }

    @RequestMapping(value = "/acte/add", method = {RequestMethod.POST})
    public String createacte(Model model, Acte a, BindingResult result, Principal principal) {

        if (a.getStatus().startsWith("valider")) {
            List<String> listSexe = Arrays.asList("Feminin", "Masculin");
            model.addAttribute("listSexe", listSexe);
            Acte acte = new Acte();
            acte.setRegOrigin(a.getRegOrigin());
            acte.setDepartOrigin(a.getDepartOrigin());
            acte.setArrOrigin(a.getArrOrigin());
            acte.setNomCentEtCv(a.getNomCentEtCv());
            acte.setNomEnf(a.getNomEnf());
            acte.setPrenomEnf(a.getPrenomEnf());
            acte.setUser(userRepository.findByEmail(principal.getName()));
            acte.setDateNaissEnf(a.getDateNaissEnf());
            acte.setLieuNaiss(a.getLieuNaiss());
            acte.setSexeEnf(a.getSexeEnf());
            acte.setNomPer(a.getNomPer());
            acte.setPrenomPer(a.getPrenomPer());
            acte.setDateNaissPere(a.getDateNaissPere());
            acte.setAdressDomicilPer(a.getAdressDomicilPer());
            acte.setProfessPer(a.getProfessPer());
            acte.setNomMereEnf(a.getNomMereEnf());
            acte.setPrenMerEnf(a.getPrenMerEnf());
            acte.setDateNaissMere(a.getDateNaissMere());
            acte.setAdressDomicilMer(a.getAdressDomicilMer());
            acte.setProfessMer(a.getProfessMer());
            acte.setStatus(a.getStatus());
            acte.setLieuNaissMer(a.getLieuNaissMer());
            acte.setLieuNaissPer(a.getLieuNaissPer());
            acteService.save(acte);
            return "redirect:/acte/ok?success";
        } else {
            List<String> listSexe = Arrays.asList("Feminin", "Masculin");
            model.addAttribute("listSexe", listSexe);
            Acte acte = new Acte();
            acte.setRegOrigin(a.getRegOrigin());
            acte.setDepartOrigin(a.getDepartOrigin());
            acte.setArrOrigin(a.getArrOrigin());
            acte.setNomCentEtCv(a.getNomCentEtCv());
            acte.setNomEnf(a.getNomEnf());
            acte.setPrenomEnf(a.getPrenomEnf());
            acte.setUser(userRepository.findByEmail(principal.getName()));
            acte.setDateNaissEnf(a.getDateNaissEnf());
            acte.setLieuNaiss(a.getLieuNaiss());
            acte.setSexeEnf(a.getSexeEnf());
            acte.setNomPer(a.getNomPer());
            acte.setPrenomPer(a.getPrenomPer());
            acte.setDateNaissPere(a.getDateNaissPere());
            acte.setAdressDomicilPer(a.getAdressDomicilPer());
            acte.setProfessPer(a.getProfessPer());
            acte.setNomMereEnf(a.getNomMereEnf());
            acte.setPrenMerEnf(a.getPrenMerEnf());
            acte.setDateNaissMere(a.getDateNaissMere());
            acte.setAdressDomicilMer(a.getAdressDomicilMer());
            acte.setProfessMer(a.getProfessMer());
            acte.setStatus(a.getStatus());
            acte.setLieuNaissMer(a.getLieuNaissMer());
            acte.setLieuNaissPer(a.getLieuNaissPer());
            acteService.save(acte);
        }
        return "redirect:/acte/add?success";
    }

    @RequestMapping(value = "/acte/ok", method = RequestMethod.GET)
    public String listValid(Model model, Principal principal,
            @RequestParam(name = "motCle", defaultValue = "") String motCle,
            @RequestParam("page") Optional<Integer> page, @RequestParam("size") Optional<Integer> size) {
        int currentPage = page.orElse(0);
        int pageSize = size.orElse(5);
        String status = "valider";
        User user = userRepository.findByEmail(principal.getName());
        Page<Acte> pageUsers = acteRepository.findTop1ByUserAndStatusAndNomEnfLike(
                userRepository.findByID(user.getId()), status, "%" + motCle + "%",
                PageRequest.of(currentPage - 0, pageSize));
        model.addAttribute("actes", pageUsers.getContent());
        int[] pages = new int[pageUsers.getTotalPages()];
        model.addAttribute("pages", pages);
        model.addAttribute("pageCourante", currentPage);
        model.addAttribute("motCle", motCle);
        return "security/listActeValider";
    }

    @RequestMapping(value = "/acte/traite/no", method = RequestMethod.GET)
    public String listNonTraiter(Model model, @RequestParam(name = "motCle", defaultValue = "") String motCle,
            @RequestParam("page") Optional<Integer> page, @RequestParam("size") Optional<Integer> size) {
        int currentPage = page.orElse(0);
        int pageSize = size.orElse(5);

        String status = "valider";
        Page<Acte> pageUsers = acteRepository.findByStatusAndNomEnfLike(status,
                "%" + motCle + "%", PageRequest.of(currentPage - 0, pageSize));
        model.addAttribute("actes", pageUsers.getContent());
        int[] pages = new int[pageUsers.getTotalPages()];
        model.addAttribute("pages", pages);
        model.addAttribute("pageCourante", currentPage);
        model.addAttribute("motCle", motCle);
        return "security/listActeNonTraite";
    }

    @RequestMapping(value = "/acte/traite", method = RequestMethod.GET)
    public String listTraiterUser(Model model, @RequestParam(name = "motCle", defaultValue = "") String motCle,
            @RequestParam("page") Optional<Integer> page, @RequestParam("size") Optional<Integer> size) {
        int currentPage = page.orElse(0);
        int pageSize = size.orElse(5);
        String status = "traite";
        Page<Acte> pageUsers = acteRepository.findByStatusAndNomEnfLikeOrderByDateEtablissActNaissDesc(status,
                "%" + motCle + "%", PageRequest.of(currentPage - 0, pageSize));
        model.addAttribute("actes", pageUsers.getContent());
        int[] pages = new int[pageUsers.getTotalPages()];
        model.addAttribute("pages", pages);
        model.addAttribute("pageCourante", currentPage);
        model.addAttribute("motCle", motCle);
        return "security/listActeTraite";
    }

    @GetMapping("/acte/ok/{id}")
    public String detailActeValid(Model model, @PathVariable("id") Long id) {
        List<String> listSexe = Arrays.asList("Feminin", "Masculin");
        model.addAttribute("listSexe", listSexe);
        model.addAttribute("acte", new Acte());
        model.addAttribute("acteForm", acteRepository.findByID(id));
        return "security/detailActValid";
    }

    @GetMapping("/acte/update/{id}")
    public String UpdateActeSauv(Model model, @PathVariable("id") Long id, Principal principal) {
        Acte acte = acteRepository.findByID(id);
        if (acte != null) {

            List<String> listSexe = Arrays.asList("Feminin", "Masculin");
            model.addAttribute("listSexe", listSexe);
            model.addAttribute("acteForm", acte);
            return "security/acteUpdate";
        }
        return "security/acteUpdate";
    }

    @PostMapping("/acte/update/{id}")
    public String UpdateActeSauve(Model model, @PathVariable("id") Long id, Acte a, Principal principal) {

        if (acteRepository.findByID(id) != null) {

            List<String> listSexe = Arrays.asList("Feminin", "Masculin");
            model.addAttribute("listSexe", listSexe);
            model.addAttribute("acteForm", acteRepository.findByID(id));
            a.setStatus("valider");
            a.setRegOrigin(a.getRegOrigin());
            a.setDepartOrigin(a.getDepartOrigin());
            a.setArrOrigin(a.getArrOrigin());
            a.setNomCentEtCv(a.getNomCentEtCv());
            a.setNomEnf(a.getNomEnf());
            a.setPrenomEnf(a.getPrenomEnf());
            a.setUser(userRepository.findByEmail(principal.getName()));
            a.setDateNaissEnf(a.getDateNaissEnf());
            a.setLieuNaiss(a.getLieuNaiss());
            a.setSexeEnf(a.getSexeEnf());
            a.setNomPer(a.getNomPer());
            a.setPrenomPer(a.getPrenomPer());
            a.setDateNaissPere(a.getDateNaissPere());
            a.setAdressDomicilPer(a.getAdressDomicilPer());
            a.setProfessPer(a.getProfessPer());
            a.setNomMereEnf(a.getNomMereEnf());
            a.setPrenMerEnf(a.getPrenMerEnf());
            a.setDateNaissMere(a.getDateNaissMere());
            a.setAdressDomicilMer(a.getAdressDomicilMer());
            a.setProfessMer(a.getProfessMer());
            a.setDateUpd(LocalDate.now());
            a.setLieuNaissMer(a.getLieuNaissMer());
            a.setLieuNaissPer(a.getLieuNaissPer());
            acteRepository.save(a);
            return "redirect:/acte?success";
        }
        return "security/acteUpdate";
    }

    @GetMapping("/acte/delete/{id}")
    public String deleteActSauv(Model model, @PathVariable("id") Long id, Principal principal) {
        Acte acte = acteRepository.findByID(id);
        if (acte != null) {
            acteRepository.delete(acte);
            model.addAttribute("acteForm", userRepository.findAll());
            return "redirect:/acte?succes";
        }

        return "redirect:/acte?succes";
    }

    @GetMapping("/acte/traite/no/{id}")
    public String UpdateActeNO(Model model, @PathVariable("id") Long id, Principal principal) {
        Acte acte = acteRepository.findByID(id);
        if (acte != null) {
            List<String> listSexe = Arrays.asList("Feminin", "Masculin");
            model.addAttribute("listSexe", listSexe);
            model.addAttribute("acteForm", acte);
            return "security/acteUpdateTraiter";
        }
        return "security/acteUpdateTraiter";
    }

    @PostMapping("/acte/traite/no/{id}")
    public String UpdateActeNo(Model model, @PathVariable("id") Long id, Acte a, BindingResult result)
            throws IOException {

        Acte acte = acteRepository.findByID(id);
        model.addAttribute("acteForm", acte);

        Acte num = acteRepository.findByNumActNaiss(a.getNumActNaiss());
        if (num != null) {
            result.rejectValue("numActNaiss", null, "Il y a déjà un acte de naissance enregistré avec cet numero");
        }
        if (result.hasErrors()) {
            return "security/acteUpdateTraiter";
        }
        if (acte != null) {

            acte.setStatus("traite");
            acte.setNomAssOffEtCv(a.getNomAssOffEtCv());
            acte.setNomOffiEtCv(a.getNomOffiEtCv());
            acte.setDateEtablissActNaiss(LocalDate.now());
            acte.setNumActNaiss(a.getNumActNaiss());
            acte.setProvince(a.getProvince());
            acteRepository.save(acte);

            return "redirect:/acte/traite/no?success";
        }

        return "redirect:/acte/traite/no?success";
    }

}
