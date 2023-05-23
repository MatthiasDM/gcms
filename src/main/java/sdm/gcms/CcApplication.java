package sdm.gcms;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.*;
import static sdm.gcms.shared.database.Database.startUp;

@SpringBootApplication
@OpenAPIDefinition
public class CcApplication extends SpringBootServletInitializer {

    private static Logger log = LoggerFactory.getLogger(CcApplication.class);

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        startUp();
        return application.sources(CcApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(CcApplication.class, args);
    }

}
