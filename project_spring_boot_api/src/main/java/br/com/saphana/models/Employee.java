package br.com.saphana.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "EMPLOYEE", schema = "BANCO_DE_DADOS_HDI_DB")
public class Employee {
    
    @Id
    @Column(name = "ID")
    private int id;

    @Column(name = "FIRST_NAME")
    private String firstName;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String first_name) {
        this.firstName = first_name;
    }
}
