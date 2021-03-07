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
public class EstabelecimentoDTO implements Serializable {

    private static final long serialVersionUID = -730212686243788177L;

    private Long id;

    @NotNull
    @Size(max = 80)
    private String nome;

    @NotNull
    @Size(max = 15)
    private String telefone;

    @NotNull
    @Size(max = 100)
    private String endereco;

    private Long idProfissional;
}
