package com.example.apiDocsTICS.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.apiDocsTICS.Exception.RecursoNoEncontradoException;
import com.example.apiDocsTICS.Model.ValoraModel;
import com.example.apiDocsTICS.Repository.IValoraRepository;

@Service
public class ValoraServiceImp implements IValoraService{
    @Autowired IValoraRepository valoraRepository;
    @Override
    public String crearValoracion(ValoraModel valoracion) {
        valoraRepository.save(valoracion);
        return "La valoracion con Id "+ valoracion.getIdValora() + " fue creada con exito";
    }

    @Override
    public String eliminarValoracionPorId(int idValora) {
        Optional<ValoraModel> valoracionEncontrada = valoraRepository.findById(idValora);
        if (valoracionEncontrada.isPresent()) {
            valoraRepository.delete(valoracionEncontrada.get());
            return "La valoracion con Id "+ idValora + " fue eliminada con exito";
        } else {
            throw new RecursoNoEncontradoException("Valoracion no encontrada con el Id " + idValora);
        }
    }

    @Override
    public String modificarValoracionPorId(int idValora, ValoraModel valoracion) {
        Optional<ValoraModel> valoracionEncontrada = valoraRepository.findById(idValora);
        if (valoracionEncontrada.isPresent()) {
            ValoraModel valoracionModificada = valoracionEncontrada.get();
            valoracionModificada.setValoracion(valoracion.getValoracion());
            valoracionModificada.setIdDocumento(valoracion.getIdDocumento());
            valoracionModificada.setIdUsuario(valoracion.getIdUsuario());
            valoraRepository.save(valoracionModificada);
            return "La valoracion con Id "+ idValora + " fue modificada con exito";
        } else {
            throw new RecursoNoEncontradoException("Valoracion no encontrada con el Id " + idValora);
        }
    }

    @Override
    public ValoraModel obtenerValoracionPorId(int idValora) {
        Optional<ValoraModel> valoracionEncontrada = valoraRepository.findById(idValora);
        if (valoracionEncontrada.isPresent()) {
            return valoracionEncontrada.get();
        } else {
            throw new RecursoNoEncontradoException("Valoracion no encontrada con el Id " + idValora);
        }
    }

    @Override
    public List<ValoraModel> obtenerValoraciones() {
        return valoraRepository.findAll();
    }
    
}
