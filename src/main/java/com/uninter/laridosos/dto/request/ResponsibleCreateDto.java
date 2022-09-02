package com.uninter.laridosos.dto.request;

import lombok.*;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResponsibleCreateDto implements Serializable {

    @NotNull(message = "Nome é obrigatório")
    private String name;
    @NotNull(message = "CPF é obrigatório")
    private String cpf;
    @NotNull(message = "Data de nascimento é obrigatório")
    private Date birthDate;
    private String phone;
}
