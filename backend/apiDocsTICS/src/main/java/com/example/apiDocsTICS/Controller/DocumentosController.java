package com.example.apiDocsTICS.Controller;

import java.util.List;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.apiDocsTICS.DTO.DocumentosDTO;
import com.example.apiDocsTICS.Exception.RecursoNoEncontradoException;
import com.example.apiDocsTICS.Model.DocumentosModel;
import com.example.apiDocsTICS.Service.IDocumetosService;

@RestController
@RequestMapping("/documentos")
public class DocumentosController {

    @Autowired
    private IDocumetosService documentosService;

    @PostMapping("/insertar")
    public ResponseEntity<String> crearDocumento(@RequestBody DocumentosDTO documentosDTO) {
        // Convertir el DTO al modelo
        DocumentosModel documentosModel = new DocumentosModel();
        documentosModel.setTituloDoc(documentosDTO.getTituloDoc());
        documentosModel.setVisibilidad(documentosDTO.getVisibilidad());
        documentosModel.setURL(documentosDTO.getURL());
        documentosModel.setDescripcion(documentosDTO.getDescripcion());
        documentosModel.setCategorias(documentosDTO.getCategorias());
        documentosModel.setInfoAutores(documentosDTO.getInfoAutores());
        documentosModel.setAbstracto(documentosDTO.getAbstracto());

        String resultado = documentosService.CrearDocumento(documentosModel);
        return new ResponseEntity<>(resultado, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarDocumentoPorId(@PathVariable String id) {
        try {
            ObjectId documentoId = new ObjectId(id);
            DocumentosModel documento = documentosService.buscarDocumento(documentoId);
            return ResponseEntity.ok(documento);
        } catch (RecursoNoEncontradoException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("/listar")
    public ResponseEntity<List<DocumentosModel>> listarDocumentos() {
        List<DocumentosModel> documentos = documentosService.listarDocumentos();
        return new ResponseEntity<>(documentos, HttpStatus.OK);
    }
}
