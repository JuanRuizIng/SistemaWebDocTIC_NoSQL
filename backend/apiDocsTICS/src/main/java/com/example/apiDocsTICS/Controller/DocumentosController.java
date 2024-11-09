package com.example.apiDocsTICS.Controller;

import java.util.List;
import java.util.stream.Collectors;

import com.example.apiDocsTICS.DTO.Documents.CategoriaDTO;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.apiDocsTICS.DTO.Documents.*;
import com.example.apiDocsTICS.Model.Documents.*;
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
    public ResponseEntity<String> crearDocumento(@RequestBody DocumentosDTO documentoDTO) {
        DocumentosModel documentoModel = mapDTOToModel(documentoDTO);
        String resultado = documentosService.CrearDocumento(documentoModel);
        return new ResponseEntity<>(resultado, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarDocumentoPorId(@PathVariable String id) {
        try {
            ObjectId documentoId = new ObjectId(id);
            DocumentosModel documento = documentosService.buscarDocumento(documentoId);
            DocumentosDTO documentoDTO = mapModelToDTO(documento);
            return ResponseEntity.ok(documentoDTO);
        } catch (RecursoNoEncontradoException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("/listar")
    public ResponseEntity<List<DocumentosDTO>> listarDocumentos() {
        List<DocumentosModel> documentos = documentosService.listarDocumentos();
        List<DocumentosDTO> documentosDTO = documentos.stream()
                .map(this::mapModelToDTO)
                .collect(Collectors.toList());
        return new ResponseEntity<>(documentosDTO, HttpStatus.OK);
    }

    // Métodos de ayuda para mapear entre Model y DTO
    private DocumentosModel mapDTOToModel(DocumentosDTO dto) {
        // Implementa el mapeo de DTO a Model
        // Puedes utilizar librerías como ModelMapper para simplificar
        DocumentosModel model = new DocumentosModel();
        model.set_id(dto.getId());
        model.setTituloDoc(dto.getTituloDoc());
        model.setVisibilidad(dto.getVisibilidad());
        model.setURL(dto.getUrl());
        model.setDescripcion(dto.getDescripcion());
        model.setCategorias(dto.getCategorias().stream().map(this::mapCategoriaDTOToModel).collect(Collectors.toList()));
        model.setDescargas(dto.getDescargas().stream().map(this::mapDescargaDTOToModel).collect(Collectors.toList()));
        model.setVistas(dto.getVistas().stream().map(this::mapVistaDTOToModel).collect(Collectors.toList()));
        model.setValoraciones(dto.getValoraciones().stream().map(this::mapValoracionDTOToModel).collect(Collectors.toList()));
        model.setInfoAutores(dto.getInfoAutores().stream().map(this::mapInfoAutorDTOToModel).collect(Collectors.toList()));
        model.setAbstracto(dto.getAbstracto());
        return model;
    }

    private DocumentosDTO mapModelToDTO(DocumentosModel model) {
        // Implementa el mapeo de Model a DTO
        DocumentosDTO dto = new DocumentosDTO();
        dto.setId(model.get_id());
        dto.setTituloDoc(model.getTituloDoc());
        dto.setVisibilidad(model.getVisibilidad());
        dto.setUrl(model.getURL());
        dto.setDescripcion(model.getDescripcion());
        dto.setCategorias(model.getCategorias().stream().map(this::mapCategoriaModelToDTO).collect(Collectors.toList()));
        dto.setDescargas(model.getDescargas().stream().map(this::mapDescargaModelToDTO).collect(Collectors.toList()));
        dto.setVistas(model.getVistas().stream().map(this::mapVistaModelToDTO).collect(Collectors.toList()));
        dto.setValoraciones(model.getValoraciones().stream().map(this::mapValoracionModelToDTO).collect(Collectors.toList()));
        dto.setInfoAutores(model.getInfoAutores().stream().map(this::mapInfoAutorModelToDTO).collect(Collectors.toList()));
        dto.setAbstracto(model.getAbstracto());
        return dto;
    }

    // Métodos de mapeo para documentos embebidos
    private Categoria mapCategoriaDTOToModel(CategoriaDTO dto) {
        return new Categoria(dto.getCategoriaId());
    }

    private CategoriaDTO mapCategoriaModelToDTO(Categoria model) {
        return new CategoriaDTO(model.getCategoriaId());
    }

    private Descarga mapDescargaDTOToModel(DescargaDTO dto) {
        return new Descarga(dto.getUsuarioId(), dto.getFechaDescarga());
    }

    private DescargaDTO mapDescargaModelToDTO(Descarga model) {
        return new DescargaDTO(model.getUsuarioId(), model.getFechaDescarga());
    }

    private Vista mapVistaDTOToModel(VistaDTO dto) {
        return new Vista(dto.getUsuarioId(), dto.getFechaVista());
    }

    private VistaDTO mapVistaModelToDTO(Vista model) {
        return new VistaDTO(model.getUsuarioId(), model.getFechaVista());
    }

    private Valoracion mapValoracionDTOToModel(ValoracionDTO dto) {
        return new Valoracion(dto.getUsuarioId(), dto.getValoracion(), dto.getFechaValora());
    }

    private ValoracionDTO mapValoracionModelToDTO(Valoracion model) {
        return new ValoracionDTO(model.getUsuarioId(), model.getValoracion(), model.getFechaValora());
    }

    private InfoAutor mapInfoAutorDTOToModel(InfoAutorDTO dto) {
        return new InfoAutor(dto.getUsuarioId(), dto.getFechaPublicacion(), dto.getBiografia(), dto.getRol());
    }

    private InfoAutorDTO mapInfoAutorModelToDTO(InfoAutor model) {
        return new InfoAutorDTO(model.getUsuarioId(), model.getFechaPublicacion(), model.getBiografia(), model.getRol());
    }
}
