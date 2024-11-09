package com.example.apiDocsTICS.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;
import com.example.apiDocsTICS.Model.ENUM.Visibilidad;
import com.example.apiDocsTICS.DTO.Documents.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DocumentosDTO {
    private String id;
    private String tituloDoc;
    private Visibilidad visibilidad;
    private String url;
    private String descripcion;
    private List<CategoriaDTO> categorias;
    private List<DescargaDTO> descargas;
    private List<VistaDTO> vistas;
    private List<ValoracionDTO> valoraciones;
    private List<InfoAutorDTO> infoAutores;
    private String abstracto;
}
