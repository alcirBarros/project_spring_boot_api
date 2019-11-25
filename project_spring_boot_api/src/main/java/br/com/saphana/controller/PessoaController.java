package br.com.saphana.controller;

import br.com.saphana.models.Pessoa;
import br.com.saphana.service.PessoaService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PessoaController {

    @Autowired
    private PessoaService employeeService;

    @ResponseBody
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public List<Pessoa> home() {
        List<Pessoa> findAllPessoa = employeeService.findAllPessoa();
        return findAllPessoa;
    }

}
