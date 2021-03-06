package mv.spe.domain;



import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name ="TB_PROFISSIONAL")
public class Profissional implements Serializable {

    private static final long serialVersionUID = -3487207598530635368L;

    @Id
    @Column(name = "PK_PROFISSIONAL")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_PROFISSIONAL")
    @SequenceGenerator(name = "SQ_PROFISSIONAL", sequenceName = "SQ_PROFISSIONAL", allocationSize = 1)
    private Long id;

    @Column(name = "NO_PROFISSIONAL", nullable = false)
    private String nome;

    @Column(name = "NU_CELULAR", nullable = false)
    private String celular;

    @Column(name = "NU_TELEFONE")
    private String telefone;

    @Column(name = "DS_FUNCAO")
    private String funcao;

    @Column(name = "NU_ENDERECO", nullable = false)
    private String endereco;

    @OneToMany(mappedBy = "profissional")
    private List<Estabelecimento> estabelecimentos;
}
