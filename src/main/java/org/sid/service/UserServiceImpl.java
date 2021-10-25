package org.sid.service;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.Instant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import org.sid.entity.Role;
import org.sid.entity.User;
import org.sid.repository.RoleRepository;
import org.sid.repository.UserRepository;
import org.sid.web.dto.UserRegistrationDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.ResourceLoader;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    @Qualifier("jdbcTemplate")
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private ResourceLoader resourceLoader;

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public User save(UserRegistrationDto registration) {

        User user = new User();
        user.setFirstName(registration.getFirstName());
        user.setLastName(registration.getLastName());
        user.setEmail(registration.getEmail());
        user.setPassword(passwordEncoder.encode(registration.getPassword()));
        user.setSexe(registration.getSexe());
        user.setNationalite(registration.getNationalite());
        user.setProfession(registration.getProfession());
        user.setTelephone(registration.getTelephone());
        user.setDateNaissance(registration.getDateNaissance());
        user.setDateEnreg(Instant.now());
        user.setRoles(Arrays.asList(roleRepository.findByName("ROLE_USER")));
        user.setEnabled(true);
        return userRepository.save(user);
    }

    @Override
    public User saveAdmin(UserRegistrationDto registration) {
        Role adminRole = roleRepository.findByName("ROLE_ADMIN");
        User user = new User();
        user.setFirstName(registration.getFirstName());
        user.setLastName(registration.getLastName());
        user.setEmail(registration.getEmail());
        user.setPassword(passwordEncoder.encode(registration.getPassword()));
        user.setSexe(registration.getSexe());
        user.setNationalite(registration.getNationalite());
        user.setProfession(registration.getProfession());
        user.setTelephone(registration.getTelephone());
        user.setDateNaissance(registration.getDateNaissance());
        user.setDateEnreg(Instant.now());
        user.setRoles(Arrays.asList(adminRole));
        user.setEnabled(true);
        return userRepository.save(user);
    }

    @Override
    public void updatePassword(String password, Long userId) {
        userRepository.updatePassword(passwordEncoder.encode(password), userId);
    }

    @Override
    public void deleteUg(Long id) {
        if (userRepository.findById(id) != null) {
            userRepository.deleteById(id);
        }
        throw new RuntimeException("User not found");
    }

    @Override
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public void update(User user, Long code) {

        if (user.isEnabled() == true) {
            userRepository.update(code, user.getFirstName(), user.getLastName(), user.getEmail(),
                    user.getSexe(), user.getNationalite(), user.getProfession(), user.getTelephone(),
                    user.getDateNaissance(), true, Instant.now());
        } else {
            userRepository.update(code, user.getFirstName(), user.getLastName(), user.getEmail(),
                    user.getSexe(), user.getNationalite(), user.getProfession(), user.getTelephone(),
                    user.getDateNaissance(), false, Instant.now());
        }
    }

    @Autowired
    private UserDetailsService userDetailsService;
    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    public void autologin(String username, String password) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken
                = new UsernamePasswordAuthenticationToken(userDetails, password, userDetails.getAuthorities());

        if (usernamePasswordAuthenticationToken.isAuthenticated()) {
            SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            logger.debug(String.format("Auto login %s successfully!", username));
        }
    }

    @Override
    public String findLoggedInUsername() {
        Object userDetails = SecurityContextHolder.getContext().getAuthentication().getDetails();
        if (userDetails instanceof UserDetails) {
            return ((UserDetails) userDetails).getUsername();
        }

        return null;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException("Adresse email ou mot de passe incorrect!");

        }
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), authoritis(user.getRoles()));
    }

    private Collection<? extends GrantedAuthority> authoritis(Collection<Role> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }

    public JasperPrint exportPdfFileUser() throws SQLException, JRException, IOException {

        Connection conn = jdbcTemplate.getDataSource().getConnection();

        InputStream user = new FileInputStream("assets/pdf/rpt_users.jrxml");

        JasperReport jasperReport = JasperCompileManager.compileReport(user);

        // Parameters for report
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("enabled", true);
        JasperPrint print = JasperFillManager.fillReport(jasperReport, parameters, conn);
        return print;

    }

    public JasperPrint exportPdfFile(Long id) throws SQLException, JRException, IOException {
        Connection conn = jdbcTemplate.getDataSource().getConnection();

        InputStream user = new FileInputStream("assets/pdf/rpt_actes.jrxml");

        JasperReport jasperReport = JasperCompileManager.compileReport(user);

        // Parameters for report
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("idEmployee", id);
        JasperPrint print = JasperFillManager.fillReport(jasperReport, parameters, conn);

        return print;
    }
}
