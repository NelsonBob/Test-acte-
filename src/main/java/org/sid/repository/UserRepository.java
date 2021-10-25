package org.sid.repository;

import java.time.Instant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import org.sid.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    public User findByEmail(String email);

    public User findByEnabled(boolean enabled);

    @Modifying
    @Query("update User u set u.password = :password where u.id = :id")
    public void updatePassword(@Param("password") String password, @Param("id") Long id);

    //@Query("select e from User e where e.firstName like :x or e.lastName like :x")
    //public Page<User> chercher(@Param("x") String e, Pageable pageable);
    public Page<User> findByFirstNameLikeOrLastNameLike(String firstName, String lastName, Pageable pageable);

    @Query("select e from User e where e.id=:id")
    public User findByID(@Param("id") Long id);
 
    @Modifying
    @Query("update User  set firstName = :firstName , lastName = :lastName , email = :email , sexe = :sexe , nationalite = :nationalite , profession = :profession , telephone = :telephone , dateNaissance = :dateNaissance , enabled = :enabled , dateUpdat = :dateUpdat where id = :id")
    void update(@Param("id") Long id, @Param("firstName") String firstName,
            @Param("lastName") String lastName, @Param("email") String email,
            @Param("sexe") String sexe, @Param("nationalite") String nationalite,
            @Param("profession") String profession, @Param("telephone") int telephone,
            @Param("dateNaissance") Date dateNaissance, @Param("enabled") boolean enabled,
            @Param("dateUpdat") Instant dateUpdat);

    public User findTopByOrderByIdDesc();

}
