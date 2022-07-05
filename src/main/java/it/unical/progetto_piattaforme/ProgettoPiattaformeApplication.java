package it.unical.progetto_piattaforme;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Collections;

@SpringBootApplication
public class ProgettoPiattaformeApplication {

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(ProgettoPiattaformeApplication.class);
        app.setDefaultProperties(Collections.singletonMap("server.port", "9090"));
        app.run(args);
        //SpringApplication.run(ProgettoPiattaformeApplication.class, args);
    }

}
