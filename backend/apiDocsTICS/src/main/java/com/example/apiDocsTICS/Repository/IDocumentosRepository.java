package com.example.apiDocsTICS.Repository;

import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import com.example.apiDocsTICS.Model.DocumentosModel;

public interface IDocumentosRepository extends MongoRepository<DocumentosModel, ObjectId>{
}
