package br.com.saphana;

//import br.com.saphana.models.Pessoa;
//import br.com.saphana.service.PessoaService;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;

@Controller
@SpringBootApplication
public class ProjectSapHanaApplication {

    @Autowired
    private DataSource dataSource;



    public static void main(String[] args) {
        SpringApplication.run(ProjectSapHanaApplication.class, args);
    }

}
