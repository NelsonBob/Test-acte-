package org.sid.entity;

import java.time.Instant;
import javax.persistence.*;
import java.util.Collection;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "person", uniqueConstraints = @UniqueConstraint(columnNames = "email"))
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String sexe;
    private String nationalite;
    private String profession;
    private int telephone;
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateNaissance;
    private Instant dateEnreg, dateUpdat;
    private boolean enabled;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(
                    name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "role_id", referencedColumnName = "id"))
    private Collection<Role> roles;
    @OneToMany(mappedBy = "user")
    private Collection<Acte> actes;

    public User() {
    }

    public User(String firstName, String lastName, String email, String password, String sexe, String nationalite, String profession, int telephone, Date dateNaissance) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.sexe = sexe;
        this.nationalite = nationalite;
        this.profession = profession;
        this.telephone = telephone;
        this.dateNaissance = dateNaissance;
        this.dateEnreg = Instant.now();
    }

    public User(String firstName, String email) {
        super();
        this.firstName = firstName;
        this.email = email;
    }

    public User(String firstName, String lastName, String email, String password, String sexe, String nationalite, String profession, int telephone, Date dateNaissance, Collection<Role> roles) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.sexe = sexe;
        this.nationalite = nationalite;
        this.profession = profession;
        this.telephone = telephone;
        this.dateNaissance = dateNaissance;
        this.dateEnreg = Instant.now();
        this.roles = roles;
    }

    public User(Long id, String firstName, String lastName, String email, String password, String sexe, String nationalite, String profession, int telephone, Date dateNaissance) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.sexe = sexe;
        this.nationalite = nationalite;
        this.profession = profession;
        this.telephone = telephone;
        this.dateNaissance = dateNaissance;
        this.dateEnreg = Instant.now();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Collection<Role> getRoles() {
        return roles;
    }

    public void setRoles(Collection<Role> roles) {
        this.roles = roles;
    }

    public String getSexe() {
        return sexe;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    public String getNationalite() {
        return nationalite;
    }

    public void setNationalite(String nationalite) {
        this.nationalite = nationalite;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public int getTelephone() {
        return telephone;
    }

    public void setTelephone(int telephone) {
        this.telephone = telephone;
    }

    public Date getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(Date dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public Instant getDateEnreg() {
        return dateEnreg;
    }

    public void setDateEnreg(Instant dateEnreg) {
        this.dateEnreg = dateEnreg;
    }

    public Instant getDateUpdat() {
        return dateUpdat;
    }

    public void setDateUpdat(Instant dateUpdat) {
        this.dateUpdat = dateUpdat;
    }

    public Collection<Acte> getActes() {
        return actes;
    }

    public void setActes(Collection<Acte> actes) {
        this.actes = actes;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email + ", password=" + password + ", sexe=" + sexe + ", nationalite=" + nationalite + ", profession=" + profession + ", telephone=" + telephone + ", dateNaissance=" + dateNaissance + ", dateEnreg=" + dateEnreg + ", dateUpdat=" + dateUpdat + ", roles=" + roles + ", actes=" + actes + '}';
    }

}
