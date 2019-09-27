package br.com.saphana.repository;

import br.com.saphana.models.Employee;
import org.springframework.data.repository.CrudRepository;


public interface EmployeeRepository extends CrudRepository<Employee, Long>{
	

}
