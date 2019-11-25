package br.com.saphana.service;

import br.com.saphana.models.Pessoa;
import br.com.saphana.repository.PessoaRepository;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PessoaService {

    @Autowired
    private PessoaRepository employeeRepository;

    public long getCount() {
        long count = employeeRepository.count();
        return count;
    }

    public List<Pessoa> findAllPessoa() {
        List<Pessoa> employee = new ArrayList<>();
        employeeRepository.findAll().forEach(employee::add);
        return employee;
    }

    public boolean insertPessoa(Pessoa employee) {
        try {
            employeeRepository.save(employee);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Pessoa findPessoaById(Long id) {
        Pessoa employee = employeeRepository.findById(id).orElse(null);
        return employee;
    }

    public boolean deletePessoa(long id) {
        Pessoa employee = employeeRepository.findById(id).orElse(null);
        if (employee != null) {
            employeeRepository.delete(employee);
            return true;
        }
        return false;
    }
}
