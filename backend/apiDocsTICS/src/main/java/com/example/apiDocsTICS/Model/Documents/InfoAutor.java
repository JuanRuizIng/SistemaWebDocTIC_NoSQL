package com.example.apiDocsTICS.Model.Documents;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InfoAutor {
    private String usuarioId;
    private Date fechaPublicacion;
    private String biografia;
    private String rol;
}