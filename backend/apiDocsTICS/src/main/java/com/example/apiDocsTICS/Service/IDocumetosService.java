package com.example.apiDocsTICS.Service;

import java.util.List;

import org.bson.types.ObjectId;

import com.example.apiDocsTICS.Model.DocumentosModel;

public interface IDocumetosService {
    String CrearDocumento(DocumentosModel documentosModel);
    List<DocumentosModel> listarDocumentos();
    DocumentosModel buscarDocumento(ObjectId id);
}
