package com.example.apiDocsTICS.Model.Documents;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;
import org.bson.types.ObjectId;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Vista {
    private ObjectId usuarioId;
    private Date fechaVista;
}
