package br.com.saphana.initialize;

import br.com.saphana.models.Pessoa;
import br.com.saphana.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class Initialize implements CommandLineRunner {

    @Autowired
    private PessoaRepository pessoaRepository;

    @Override
    @Transactional
    public void run(String... args) throws Exception {

        {
            Pessoa pessoa = new Pessoa();
            pessoa.setNome("TEXTO-SPRING");
            pessoaRepository.save(pessoa);
        }

    }
}
