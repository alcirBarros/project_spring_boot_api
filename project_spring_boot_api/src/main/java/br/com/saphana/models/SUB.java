package br.com.saphana.models;

import com.vividsolutions.jts.geom.Geometry;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "SUB")
public class SUB {

    @Id
    @Column(name = "OBJECTID")
    private Integer id;

    @Column(name = "COD_ID")
    private String COD_ID;


    @Column(name = "SHAPE")
    private Geometry SHAPE;
    
    

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCOD_ID() {
        return COD_ID;
    }

    public void setCOD_ID(String COD_ID) {
        this.COD_ID = COD_ID;
    }

//    public Geometry getSHAPE() {
//        return SHAPE;
//    }
//
//    public void setSHAPE(Geometry SHAPE) {
//        this.SHAPE = SHAPE;
//    }
}
