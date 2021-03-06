package mv.spe.service.filter;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class ProfissionalFilter implements Serializable {

    private static final long serialVersionUID = 9157078123445510367L;

    private String nome;

    private String funcao;

}
