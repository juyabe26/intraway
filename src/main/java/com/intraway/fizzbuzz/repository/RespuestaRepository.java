package com.intraway.fizzbuzz.repository;

import com.intraway.fizzbuzz.domain.Cabecera;
import com.intraway.fizzbuzz.domain.Respuesta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RespuestaRepository extends JpaRepository<Respuesta, Integer> {
    @Query("SELECT rts FROM Respuesta rts where rts.resultado = ?1 ")
    Respuesta buscarcadena(String str);
}
