package com.example.apiDocsTICS.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.apiDocsTICS.Exception.RecursoNoEncontradoException;
import com.example.apiDocsTICS.Model.DocumentoModel;
import com.example.apiDocsTICS.Repository.IDocumentoRepository;

@Service
public class DocumentoServiceImp implements IDocumentoService {
    @Autowired IDocumentoRepository documentoRepository;
    @Override
    public String crearDocumento(DocumentoModel documento) {
        documentoRepository.save(documento);
        return "El documento con id " + documento.getIdDocumento() + " y titulo " + documento.getTituloDoc() + " ha sido creado";
    }

    @Override
    public String eliminarDocumentoPorId(int idDocumento) {
        Optional <DocumentoModel> documentoEncontrado = documentoRepository.findById(idDocumento);
        if (documentoEncontrado.isPresent()){
            documentoRepository.delete(documentoEncontrado.get());
            return "El documento con id "+ idDocumento +" fue eliminado con exito";
        } else {
            throw new RecursoNoEncontradoException("Documento no encontrado con el Id " + idDocumento);
        }
    }

    @Override
    public String modificarDocumentoPorId(int idDocumento, DocumentoModel documentoNuevo) {
        Optional<DocumentoModel> documentoEncontrado = documentoRepository.findById(idDocumento);
        if (documentoEncontrado.isPresent()) {
            DocumentoModel documento = documentoEncontrado.get();
            documento.setTituloDoc(documentoNuevo.getTituloDoc());
            documento.setVisibilidad(documentoNuevo.getVisibilidad());
            documento.setURL(documentoNuevo.getURL());
            documento.setDescripcion(documentoNuevo.getDescripcion());
            documento.setIdCategoria(documentoNuevo.getIdCategoria());
            documentoRepository.save(documento);  // Guardar los cambios
            return "El documento con id " + idDocumento + " ha sido modificado";
        } else {
            throw new RecursoNoEncontradoException("Documento no encontrado con el Id " + idDocumento);
        }
    }

    @Override
    public DocumentoModel obtenerDocumentoPorId(int idDocumento) {
        Optional <DocumentoModel> documentoEncontrado = documentoRepository.findById(idDocumento);
        if (documentoEncontrado.isPresent()){
            return documentoEncontrado.get();
        } else {
            throw new RecursoNoEncontradoException("Documento no encontrado con el Id " + idDocumento);
        }
    }

    @Override
    public List<DocumentoModel> obtenerDocumentos() {
        return documentoRepository.findAll();
    }
    
}