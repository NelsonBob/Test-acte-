package org.sid.entity;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.ToString;

@Entity
public class Acte implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private User user;
    private String nomEnf;
    private String prenomEnf;
    private String regOrigin;
    private String departOrigin;
    private String ArrOrigin, province;
    private String nomCentEtCv;
    private String lieuNaiss, lieuNaissPer, lieuNaissMer;
    private String sexeEnf;
    private String nomPer;
    private String prenomPer;
    private String adressDomicilPer;
    private String professPer;
    private String nomMereEnf;
    private String prenMerEnf;
    private String adressDomicilMer;
    private String professMer;
    private String nomOffiEtCv;
    private String nomAssOffEtCv;
    private String numActNaiss;
    private String dateNaissEnf, dateNaissPere, dateNaissMere;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateEtablissActNaiss, dateUpd;
    private String status;

    public Acte() {
        super();
    }

    public Acte(final Long id, final String nomEnf, final String prenomEnf, final String regOrigin,
            final String departOrigin, final String ArrOrigin, final String nomCentEtCv, final String lieuNaiss,
            final String lieuNaissPer, final String lieuNaissMer, final String sexeEnf, final String nomPer,
            final String prenomPer, final String adressDomicilPer, final String professPer, final String nomMereEnf,
            final String prenMerEnf, final String adressDomicilMer, final String professMer, final String dateNaissEnf,
            final String dateNaissPere, final String dateNaissMere, String province) {
        this.id = id;
        this.nomEnf = nomEnf;
        this.prenomEnf = prenomEnf;
        this.regOrigin = regOrigin;
        this.departOrigin = departOrigin;
        this.ArrOrigin = ArrOrigin;
        this.nomCentEtCv = nomCentEtCv;
        this.lieuNaiss = lieuNaiss;
        this.sexeEnf = sexeEnf;
        this.nomPer = nomPer;
        this.prenomPer = prenomPer;
        this.adressDomicilPer = adressDomicilPer;
        this.professPer = professPer;
        this.nomMereEnf = nomMereEnf;
        this.prenMerEnf = prenMerEnf;
        this.adressDomicilMer = adressDomicilMer;
        this.professMer = professMer;
        this.dateNaissEnf = dateNaissEnf;
        this.dateNaissPere = dateNaissPere;
        this.dateNaissMere = dateNaissMere;
        this.lieuNaissMer = lieuNaissMer;
        this.lieuNaissPer = lieuNaissPer;
        this.province = province;
    }

    public Acte(final User user, final String nomEnf, final String prenomEnf, final String regOrigin,
            final String departOrigin, final String ArrOrigin, final String nomCentEtCv, final String lieuNaiss,
            final String sexeEnf, final String nomPer, final String prenomPer, final String adressDomicilPer,
            final String professPer, final String nomMereEnf, final String prenMerEnf, final String adressDomicilMer,
            final String professMer, final String nomOffiEtCv, final String nomAssOffEtCv, final String dateNaissEnf,
            final String dateNaissPere, final String dateNaissMere, final String status, final String lieuNaissPer,
            final String lieuNaissMer, String province) {
        this.user = user;
        this.nomEnf = nomEnf;
        this.prenomEnf = prenomEnf;
        this.regOrigin = regOrigin;
        this.departOrigin = departOrigin;
        this.ArrOrigin = ArrOrigin;
        this.nomCentEtCv = nomCentEtCv;
        this.lieuNaiss = lieuNaiss;
        this.sexeEnf = sexeEnf;
        this.nomPer = nomPer;
        this.prenomPer = prenomPer;
        this.adressDomicilPer = adressDomicilPer;
        this.professPer = professPer;
        this.nomMereEnf = nomMereEnf;
        this.prenMerEnf = prenMerEnf;
        this.adressDomicilMer = adressDomicilMer;
        this.professMer = professMer;
        this.nomOffiEtCv = nomOffiEtCv;
        this.nomAssOffEtCv = nomAssOffEtCv;
        this.dateNaissEnf = dateNaissEnf;
        this.dateNaissPere = dateNaissPere;
        this.dateNaissMere = dateNaissMere;
        this.status = status;
        this.lieuNaissMer = lieuNaissMer;
        this.lieuNaissPer = lieuNaissPer;
        this.province = province;
    }

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(final User user) {
        this.user = user;
    }

    public String getNomEnf() {
        return nomEnf;
    }

    public void setNomEnf(final String nomEnf) {
        this.nomEnf = nomEnf;
    }

    public String getPrenomEnf() {
        return prenomEnf;
    }

    public void setPrenomEnf(final String prenomEnf) {
        this.prenomEnf = prenomEnf;
    }

    public String getRegOrigin() {
        return regOrigin;
    }

    public void setRegOrigin(final String regOrigin) {
        this.regOrigin = regOrigin;
    }

    public String getDepartOrigin() {
        return departOrigin;
    }

    public void setDepartOrigin(final String departOrigin) {
        this.departOrigin = departOrigin;
    }

    public String getArrOrigin() {
        return ArrOrigin;
    }

    public void setArrOrigin(final String ArrOrigin) {
        this.ArrOrigin = ArrOrigin;
    }

    public String getNomCentEtCv() {
        return nomCentEtCv;
    }

    public void setNomCentEtCv(final String nomCentEtCv) {
        this.nomCentEtCv = nomCentEtCv;
    }

    public String getLieuNaiss() {
        return lieuNaiss;
    }

    public void setLieuNaiss(final String lieuNaiss) {
        this.lieuNaiss = lieuNaiss;
    }

    public String getSexeEnf() {
        return sexeEnf;
    }

    public void setSexeEnf(final String sexeEnf) {
        this.sexeEnf = sexeEnf;
    }

    public String getNomPer() {
        return nomPer;
    }

    public void setNomPer(final String nomPer) {
        this.nomPer = nomPer;
    }

    public String getPrenomPer() {
        return prenomPer;
    }

    public void setPrenomPer(final String prenomPer) {
        this.prenomPer = prenomPer;
    }

    public String getAdressDomicilPer() {
        return adressDomicilPer;
    }

    public void setAdressDomicilPer(final String adressDomicilPer) {
        this.adressDomicilPer = adressDomicilPer;
    }

    public String getProfessPer() {
        return professPer;
    }

    public void setProfessPer(final String professPer) {
        this.professPer = professPer;
    }

    public String getNomMereEnf() {
        return nomMereEnf;
    }

    public void setNomMereEnf(final String nomMereEnf) {
        this.nomMereEnf = nomMereEnf;
    }

    public String getPrenMerEnf() {
        return prenMerEnf;
    }

    public void setPrenMerEnf(final String prenMerEnf) {
        this.prenMerEnf = prenMerEnf;
    }

    public String getAdressDomicilMer() {
        return adressDomicilMer;
    }

    public void setAdressDomicilMer(final String adressDomicilMer) {
        this.adressDomicilMer = adressDomicilMer;
    }

    public String getProfessMer() {
        return professMer;
    }

    public void setProfessMer(final String professMer) {
        this.professMer = professMer;
    }

    public String getNomOffiEtCv() {
        return nomOffiEtCv;
    }

    public void setNomOffiEtCv(final String nomOffiEtCv) {
        this.nomOffiEtCv = nomOffiEtCv;
    }

    public String getNomAssOffEtCv() {
        return nomAssOffEtCv;
    }

    public void setNomAssOffEtCv(final String nomAssOffEtCv) {
        this.nomAssOffEtCv = nomAssOffEtCv;
    }

    public String getNumActNaiss() {
        return numActNaiss;
    }

    public void setNumActNaiss(final String numActNaiss) {
        this.numActNaiss = numActNaiss;
    }

    public String getDateNaissEnf() {
        return dateNaissEnf;
    }

    public void setDateNaissEnf(final String dateNaissEnf) {
        this.dateNaissEnf = dateNaissEnf;
    }

    public String getDateNaissPere() {
        return dateNaissPere;
    }

    public void setDateNaissPere(final String dateNaissPere) {
        this.dateNaissPere = dateNaissPere;
    }

    public String getDateNaissMere() {
        return dateNaissMere;
    }

    public void setDateNaissMere(final String dateNaissMere) {
        this.dateNaissMere = dateNaissMere;
    }

    public LocalDate getDateEtablissActNaiss() {
        return dateEtablissActNaiss;
    }

    public void setDateEtablissActNaiss(LocalDate dateEtablissActNaiss) {
        this.dateEtablissActNaiss = dateEtablissActNaiss;
    }

    public LocalDate getDateUpd() {
        return dateUpd;
    }

    public void setDateUpd(LocalDate dateUpd) {
        this.dateUpd = dateUpd;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(final String status) {
        this.status = status;
    }

    public String getLieuNaissPer() {
        return lieuNaissPer;
    }

    public void setLieuNaissPer(String lieuNaissPer) {
        this.lieuNaissPer = lieuNaissPer;
    }

    public String getLieuNaissMer() {
        return lieuNaissMer;
    }

    public void setLieuNaissMer(String lieuNaissMer) {
        this.lieuNaissMer = lieuNaissMer;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

}
