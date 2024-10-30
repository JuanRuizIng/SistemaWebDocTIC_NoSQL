package com.example.apiDocsTICS.Repository;


import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.apiDocsTICS.Model.UsuariosModel;


public interface IUsuariosRepository extends MongoRepository<UsuariosModel, ObjectId> {
    Optional<UsuariosModel> findByNombreUsuario(String nombreUsuario);
    @Aggregation(pipeline = {
        "{ $unwind: { path: '$contrasenas' }}",
        "{ $match: { 'contrasenas.usuario': ?0 }}",
        "{ $project: { _id: 1} }"
    })
    Optional<ObjectId> validarUsuario(String usuario);
}
