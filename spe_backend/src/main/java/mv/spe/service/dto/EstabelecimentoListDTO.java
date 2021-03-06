package mv.spe.service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EstabelecimentoListDTO implements Serializable {

    private static final long serialVersionUID = 1008644674049627532L;

    private Long id;
    private String nome;
    private String telefone;
    private String endereco;

}

