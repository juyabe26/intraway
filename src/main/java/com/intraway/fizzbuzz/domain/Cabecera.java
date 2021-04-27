package com.intraway.fizzbuzz.domain;

import java.time.LocalDateTime;
import java.util.Date;
import javax.persistence.*;

@Entity
@Table(name = "tbl_cabecera")
public class Cabecera {

    @Id
    @Column(name = "id_cabecera")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tbl_cabecera_generator")
    @SequenceGenerator(sequenceName = "seq_id_cabecera", name = "tbl_cabecera_generator", allocationSize = 1)
    private Integer id;

    @Column(name = "fecha")
    private Date fecha;

    @Column(name = "codigo")
    private String codigo;

    @Column(name = "descripcion")
    private String descripcion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_respusta")
    private Respuesta respuesta;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Respuesta getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(Respuesta respuesta) {
        this.respuesta = respuesta;
    }
}
