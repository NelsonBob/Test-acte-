package org.sid.service;

import java.time.LocalDate;

import org.sid.entity.Acte;
import org.sid.entity.Role;
import org.sid.repository.ActeRepository;
import org.sid.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ActeService {

    @Autowired
    private ActeRepository acteRepository;
    @Autowired
    private RoleRepository roleRepository;

    public void saveR() {
        if ((roleRepository.findByName("ROLE_USER")) != null || (roleRepository.findByName("ROLE_ADMIN")) != null) {

        } else {
            Role d = new Role();
            Role d1 = new Role();
            d.setName("ROLE_USER");
            d1.setName("ROLE_ADMIN");
            int r1 = 1, j = 2;
            while (r1 < j) {

                roleRepository.save(d);
                roleRepository.save(d1);
                r1 = r1 + 1;
            }
        }
    }

    public Acte save(Acte a) {
        Acte acte = new Acte();
        acte.setRegOrigin(a.getRegOrigin());
        acte.setDepartOrigin(a.getDepartOrigin());
        acte.setArrOrigin(a.getArrOrigin());
        acte.setNomCentEtCv(a.getNomCentEtCv());
        acte.setNomEnf(a.getNomEnf());
        acte.setPrenomEnf(a.getPrenomEnf());
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
        acte.setDateEtablissActNaiss(LocalDate.now());
        acte.setStatus(a.getStatus());
        acte.setUser(a.getUser());
        acte.setLieuNaissMer(a.getLieuNaissMer());
        acte.setLieuNaissPer(a.getLieuNaissPer());
        acte.setProvince(a.getProvince());
        return acteRepository.save(acte);
    }

}
