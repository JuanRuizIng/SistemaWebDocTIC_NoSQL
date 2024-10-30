package com.example.apiDocsTICS.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.apiDocsTICS.Model.DescargaModel;
import com.example.apiDocsTICS.Service.IDescargaService;
import java.util.List;

@RestController
@RequestMapping("/api/descargas")
public class DescargaController {

    @Autowired
    private IDescargaService descargaService;

    @PostMapping("/crear")
    public ResponseEntity<String> crearDescarga(@RequestBody DescargaModel descarga) {
        return new ResponseEntity<>(descargaService.crearDescarga(descarga), HttpStatus.CREATED);
    }

    @GetMapping("/obtener/{id}")
    public ResponseEntity<DescargaModel> obtenerDescargaPorId(@PathVariable int id) {
        return new ResponseEntity<>(descargaService.obtenerDescargaPorId(id), HttpStatus.OK);
    }

    @GetMapping("/todas")
    public ResponseEntity<List<DescargaModel>> obtenerDescargas() {
        return new ResponseEntity<>(descargaService.obtenerDescargas(), HttpStatus.OK);
    }

    @PutMapping("/modificar/{id}")
    public ResponseEntity<String> modificarDescarga(@PathVariable int id, @RequestBody DescargaModel descarga) {
        return new ResponseEntity<>(descargaService.modificarDescargaPorId(id, descarga), HttpStatus.OK);
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<String> eliminarDescarga(@PathVariable int id) {
        return new ResponseEntity<>(descargaService.eliminarDescargaPorId(id), HttpStatus.OK);
    }
}
