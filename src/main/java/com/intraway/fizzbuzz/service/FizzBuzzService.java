package com.intraway.fizzbuzz.service;

import com.intraway.fizzbuzz.domain.Cabecera;
import com.intraway.fizzbuzz.domain.Respuesta;
import com.intraway.fizzbuzz.repository.CabeceraRepository;
import com.intraway.fizzbuzz.repository.RespuestaRepository;
import io.swagger.v3.oas.models.security.SecurityScheme;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FizzBuzzService {

    @Autowired
    private CabeceraRepository cabeceraRepository;

    @Autowired
    private RespuestaRepository respuestaRepository;

    public FizzBuzzDTO fizzBuzz(Integer min, Integer max) {
        List<String> listaRetorno = new ArrayList<>();
        FizzBuzzDTO fizzBuzzDTO = new FizzBuzzDTO();
        for (int i = min; i <= max; i++) {
            if (i % 3 == 0 && i % 5 == 0) {
                listaRetorno.add("FizzBuzz");
            } else if (i % 3 == 0) {
                listaRetorno.add("Fizz");
            } else if (i % 5 == 0) {
                listaRetorno.add("Buzz");
            } else {
                listaRetorno.add(String.valueOf(i));
            }
        }
        boolean existe = listaRetorno.contains("FizzBuzz");
        boolean existeNum3 = listaRetorno.contains("Fizz");
        fizzBuzzDTO.setFecha(new Date());
        fizzBuzzDTO.setList(listaRetorno);
        if (existe) {
            fizzBuzzDTO.setDescripcion("se encontraron múltiplos de 3 y de 5");
            fizzBuzzDTO.setCodigo("001");
        } else if (existeNum3) {
            fizzBuzzDTO.setDescripcion("se encontraron múltiplos de 3");
            fizzBuzzDTO.setCodigo("002");
        } else {
            fizzBuzzDTO.setDescripcion("se encontraron múltiplos de 5");
            fizzBuzzDTO.setCodigo("003");
        }
        return fizzBuzzDTO;
    }

    public void agregarResultado(FizzBuzzDTO fizzBuzzDTO) {
        String str = fizzBuzzDTO.getList().toString().replaceAll("\\[|\\]", "").replaceAll(", ", ", ");
        Respuesta resul = buscarcadena(str);
        Respuesta respuesta = new Respuesta();
        if (resul != null) {
            respuesta = resul;
        } else {
            respuesta.setResultado(str);
            respuestaRepository.save(respuesta);
        }

        Cabecera cabecera = new Cabecera();
        cabecera.setCodigo(fizzBuzzDTO.getCodigo());
        cabecera.setDescripcion(fizzBuzzDTO.getDescripcion());
        cabecera.setFecha(fizzBuzzDTO.getFecha());
        cabecera.setRespuesta(respuesta);
        cabeceraRepository.save(cabecera);
    }

    private Respuesta buscarcadena(String str) {
        Respuesta respuesta = respuestaRepository.buscarcadena(str);
        if (respuesta != null) return respuesta;
        return null;
    }
}
