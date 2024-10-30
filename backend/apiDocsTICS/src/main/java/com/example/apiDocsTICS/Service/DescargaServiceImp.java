package com.example.apiDocsTICS.Service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.apiDocsTICS.Exception.RecursoNoEncontradoException;
import com.example.apiDocsTICS.Model.DescargaModel;
import com.example.apiDocsTICS.Repository.IDescargaRepository;

@Service
public class DescargaServiceImp implements IDescargaService {
    @Autowired
    IDescargaRepository descargaRepository;

    @Override
    public String crearDescarga(DescargaModel descarga) {
        descargaRepository.save(descarga);
        return "Descarga creada exitosamente";
    }

    @Override
    public String eliminarDescargaPorId(int idDescarga) {
        descargaRepository.deleteById(idDescarga);
        return "Descarga eliminada exitosamente";
    }

    @Override
    public String modificarDescargaPorId(int idDescarga, DescargaModel descarga) {
        Optional<DescargaModel> descargaOptional = descargaRepository.findById(idDescarga);
        if (descargaOptional.isPresent()) {
            descargaRepository.save(descarga);
            return "Descarga modificada exitosamente";
        }
        throw new RecursoNoEncontradoException("Descarga no encontrada");
    }

    @Override
    public DescargaModel obtenerDescargaPorId(int idDescarga) {
        return descargaRepository.findById(idDescarga)
                .orElseThrow(() -> new RecursoNoEncontradoException("Descarga no encontrada"));
    }

    @Override
    public List<DescargaModel> obtenerDescargas() {
        return descargaRepository.findAll();
    }
}
