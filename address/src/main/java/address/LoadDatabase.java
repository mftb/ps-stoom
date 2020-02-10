package address;

import lombok.extern.slf4j.Slf4j;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
class LoadDatabase {

    @Bean
    CommandLineRunner initDatabase(AddressRepository repository) {
        return args -> {
            log.info("Preloading " + repository.save(new Address("Rua Domingos Bonato", 57, "A", "Santa Genebra II", "Campinas", "SP", "Brasil", "13084785", "", "")));
            log.info("Preloading " + repository.save(new Address("Rua Domingos Bonato", 57, "B", "Santa Genebra II", "Campinas", "SP", "Brasil", "13084785", "", "")));
        };
    }
}