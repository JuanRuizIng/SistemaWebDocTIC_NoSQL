package com.example.apiDocsTICS.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.apiDocsTICS.Exception.RecursoNoEncontradoException;
import com.example.apiDocsTICS.Model.ComentarioModel;
import com.example.apiDocsTICS.Repository.IComentarioRepository;




@Service
public class ComentarioServiceImp implements IComentarioService {
    @Autowired IComentarioRepository comentarioRepository;
    @Override
    public String crearComentario(ComentarioModel comentario) {
        comentarioRepository.save(comentario);
        return "El comentario con id " + comentario.getIdComentario() + " ha sido creado";
    }

    @Override
    public String eliminarComentarioPorId(int idComentario) {
        comentarioRepository.deleteById(idComentario);
        return "El comentario con id " + idComentario + " ha sido eliminado";
    }

    @Override
    public String modificarComentarioPorId(int idComentario, ComentarioModel comentario) {
        Optional<ComentarioModel> comentarioEncontrado = comentarioRepository.findById(idComentario);
        if (comentarioEncontrado.isPresent()) {
            ComentarioModel comentarioModificado = comentarioEncontrado.get();
            comentarioModificado.setComentario(comentario.getComentario());
            comentarioRepository.save(comentarioModificado);  // Guardar los cambios
            return "El comentario con id " + idComentario + " ha sido modificado";
        } else {
            return "El comentario con id " + idComentario + " no fue encontrado";
        }
    }

    @Override
    public ComentarioModel obtenerComentarioPorId(int idComentario) {
        Optional<ComentarioModel> comentarioEncontrado = comentarioRepository.findById(idComentario);
        return comentarioEncontrado.orElseThrow(()->new RecursoNoEncontradoException("El comentario con el id " + idComentario + " no existe"));
    }

    @Override
    public List<ComentarioModel> obtenerComentarios() {
        return comentarioRepository.findAll();
    }
    
}
