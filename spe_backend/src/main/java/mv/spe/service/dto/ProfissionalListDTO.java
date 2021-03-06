package mv.spe.service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProfissionalListDTO implements Serializable {

    private static final long serialVersionUID = -4202729906547534728L;

    private Long id;
    private String nome;
    private String celular;
    private String telefone;
    private String funcao;
    private String endereco;

}
