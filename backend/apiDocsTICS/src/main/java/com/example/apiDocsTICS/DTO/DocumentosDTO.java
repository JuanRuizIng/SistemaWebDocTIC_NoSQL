package com.example.apiDocsTICS.DTO;

import java.util.List;
import com.example.apiDocsTICS.Model.ENUM.Visibilidad;
import com.example.apiDocsTICS.Model.Documents.Categoria;
import com.example.apiDocsTICS.Model.Documents.InfoAutor;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DocumentosDTO {
    private String tituloDoc;
    private Visibilidad visibilidad;
    private String URL;
    private String descripcion;
    private List<Categoria> categorias;
    private List<InfoAutor> infoAutores;
    private String abstracto;
}
