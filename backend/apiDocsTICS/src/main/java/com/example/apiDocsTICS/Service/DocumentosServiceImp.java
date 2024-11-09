package com.example.apiDocsTICS.Service;

import java.util.List;
import java.util.Optional;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.apiDocsTICS.Exception.RecursoNoEncontradoException;
import com.example.apiDocsTICS.Model.DocumentosModel;
import com.example.apiDocsTICS.Repository.IDocumentosRepository;

@Service
public class DocumentosServiceImp implements IDocumetosService {

    @Autowired
    private IDocumentosRepository documentosRepository;

    @Override
    public String CrearDocumento(DocumentosModel documentosModel) {
        DocumentosModel documentoGuardado = documentosRepository.save(documentosModel);
        return "Documento creado con ID: " + documentoGuardado.get_id();
    }

    @Override
    public List<DocumentosModel> listarDocumentos() {
        return documentosRepository.findAll();
    }

    @Override
    public DocumentosModel buscarDocumento(ObjectId id) {
        Optional<DocumentosModel> documento = documentosRepository.findById(id);
        if (documento.isPresent()) {
            return documento.get();
        } else {
            throw new RecursoNoEncontradoException("Documento no encontrado con ID: " + id.toString());
        }
    }
}
