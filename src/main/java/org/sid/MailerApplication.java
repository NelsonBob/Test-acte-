package org.sid;

import org.sid.service.ActeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MailerApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(MailerApplication.class, args);
    }

    @Autowired
    private ActeService role;

    @Override
    public void run(String... args) throws Exception {
        // TODO Auto-generated method stub
        role.saveR();
    }

}
