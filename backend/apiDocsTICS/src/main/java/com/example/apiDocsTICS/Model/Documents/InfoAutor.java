package com.example.apiDocsTICS.Model.Documents;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;
import com.example.apiDocsTICS.Model.ENUM.Rol;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InfoAutor {
    private String usuarioId;
    private Date fechaPublicacion;
    private String biografia;
    private Rol rol;
}