package com.example.apiDocsTICS.Model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;
import com.example.apiDocsTICS.Model.ENUM.Visibilidad;
import com.example.apiDocsTICS.Model.Documents.*;


@Document("Documentos")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DocumentosModel {
    @Id
    private String _id;
    private String tituloDoc;
    private Visibilidad visibilidad;
    private String URL;
    private String descripcion;
    private List<Categoria> categorias;
    private List<Descarga> descargas;
    private List<Vista> vistas;
    private List<Valoracion> valoraciones;
    private List<InfoAutor> infoAutores;
    private String abstracto;
}