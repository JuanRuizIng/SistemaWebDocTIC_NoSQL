package com.example.apiDocsTICS.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.apiDocsTICS.Service.IValoraService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.apiDocsTICS.Exception.RecursoNoEncontradoException;
import com.example.apiDocsTICS.Model.ValoraModel;


@RestController
@RequestMapping("/valoraciones")
public class ValoraController {
    @Autowired
    IValoraService valoraService;

    @PostMapping("/post")
    public ResponseEntity<String> crearValoracion(@RequestBody ValoraModel valora) {
        return new ResponseEntity<>(valoraService.crearValoracion(valora), HttpStatus.OK);
    }
    
    @DeleteMapping("/delete/{idValora}")
    public ResponseEntity<?> eliminarValoracionPorId(@PathVariable int idValora) {
        try {
            valoraService.eliminarValoracionPorId(idValora);
            return ResponseEntity.ok().build();
        } catch (RecursoNoEncontradoException e){
            String mensajeError = e.getMessage();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(mensajeError);
        }
    }

    @PutMapping("/put/{idValora}")
    public ResponseEntity<?> modificarValoracionPorId(@PathVariable int idValora, @RequestBody ValoraModel valora) {
        Object valoraActualizada = valoraService.modificarValoracionPorId(idValora, valora);
        if (valoraActualizada != null) {
            return ResponseEntity.ok().body(valoraActualizada);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Valoracion no encontrada con el Id " + idValora);
        }
    }

    @GetMapping("/get/{idValora}")
    public ResponseEntity<?> obtenerValoracionPorId(@PathVariable int idValora) {
        try {
            ValoraModel valora = valoraService.obtenerValoracionPorId(idValora);
            return ResponseEntity.ok(valora);
        } catch (RecursoNoEncontradoException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("/get")
    public ResponseEntity<?> obtenerValoraciones() {
    try {
        return ResponseEntity.ok().body(valoraService.obtenerValoraciones());
    } catch (RecursoNoEncontradoException e){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }
    }
}
