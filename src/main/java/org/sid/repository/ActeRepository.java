package org.sid.repository;

import java.util.List;
import org.sid.entity.Acte;
import org.sid.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ActeRepository extends JpaRepository<Acte, Long> {

    public List<Acte> findByUser(User user);

    @Query("select e from Acte e where e.nomEnf like :x and e.user.id=:userId")
    public Page<Acte> chercher(@Param("x") String e, @Param("userId") Long userId, Pageable pageable);

    public Page<Acte> findTop1ByUserAndStatusAndNomEnfLike(User user, String status, String nomEnf, Pageable pageable);

    //@Query("select e from Acte e where e.nomEnf like :x and  e.status='traite' order by dateEtablissActNaiss desc")
    //public Page<Acte> traiter(@Param("x") String e, Pageable pageable);
    public Page<Acte> findByStatusAndNomEnfLikeOrderByDateEtablissActNaissDesc(String status, String nomEnf, Pageable pageable);

    //@Query("select e from Acte e where e.nomEnf like :x and e.status='valider' and e.user.enabled=true")
    //public Page<Acte> nonTraite(@Param("x") String e, Pageable pageable);
    public Page<Acte> findByStatusAndNomEnfLike(String status, String nomEnf, Pageable pageable);

    public Acte findByNumActNaiss(String numActNaiss);

    @Query("select e from Acte e where e.id=:id")
    public Acte findByID(@Param("id") Long id);

    //select le dernier acte de naissance
    public Acte findTopByOrderByIdDesc();

    //@Query("select new Acte( max(e.id), e.nomEnf,e.prenomEnf,e.regOrigin,e.departOrigin ,e.ArrOrigin ,e.nomCentEtCv "
    //        + ",e.lieuNaiss ,e.lieuNaissMer ,e.lieuNaissPer ,e.sexeEnf ,e.nomPer ,e.prenomPer ,e.adressDomicilPer ,e.professPer ,e.nomMereEnf "
    //      + ",e.prenMerEnf ,e.province ,e.adressDomicilMer ,e.professMer "
    //        + ",e.dateNaissEnf ,e.dateNaissPere ,e.dateNaissMere) from Acte e where e.user.id=:userId and e.status='sauvegarder' order by e.id")
    //public Acte findMaxActeByUser(@Param("userId") Long userId);
    public Acte findActeByStatusAndUser(String status, User user);

}
