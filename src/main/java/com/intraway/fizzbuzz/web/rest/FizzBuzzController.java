package com.intraway.fizzbuzz.web.rest;

import com.intraway.fizzbuzz.service.FizzBuzzDTO;
import com.intraway.fizzbuzz.service.FizzBuzzService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/intraway")
@Api(tags = "Solucion al requerimiento Intraway")
public class FizzBuzzController {

    @Autowired
    private FizzBuzzService fizzBuzzService;

    @GetMapping("/fizzbuzz")
    @ApiOperation(
        value = "EndPoint el cual devuelve en formato JSON una lista con las características propias de cada número incluido en el rango consultado",
        notes = "min y max son 2 enteros, por ejemplo -50 y 50"
    )
    public ResponseEntity<FizzBuzzDTO> fizzBuzz(
        @RequestParam(name = "min", required = true) Integer min,
        @RequestParam(name = "max", required = true) Integer max
    ) {
        if (min > max) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            return ResponseEntity.ok(fizzBuzzService.fizzBuzz(min, max));
        }
    }

    @PostMapping("/fizzbuzz")
    @ApiOperation(
        value = "EndPoint encargado de Guardar los resultados obtenidos",
        notes = "Si el reultado ya se encuantra dentro solo se asigna el id del resultado anterios"
    )
    public void agregarResultado(@RequestBody FizzBuzzDTO FizzBuzzDTO) {
        fizzBuzzService.agregarResultado(FizzBuzzDTO);
    }
}
