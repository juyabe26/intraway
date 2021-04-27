package com.intraway.fizzbuzz.domain;

import javax.persistence.*;

@Entity
@Table(name = "tbl_respuesta")
public class Respuesta {

    @Id
    @Column(name = "id_respusta")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tbl_respuesta_generator")
    @SequenceGenerator(sequenceName = "seq_id_respusta", name = "tbl_respuesta_generator", allocationSize = 1)
    private Integer id;

    @Column(name = "resultado")
    private String resultado;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }
}
