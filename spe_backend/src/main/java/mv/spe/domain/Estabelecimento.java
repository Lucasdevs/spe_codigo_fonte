package mv.spe.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.io.Serializable;

@Getter
@Setter
@Entity
@Table(name ="TB_ESTABELECIMENTO")
public class Estabelecimento implements Serializable {

    private static final long serialVersionUID = 1708484795553840937L;

    @Id
    @Column(name = "PK_ESTABELECIMENTO")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_ESTABELECIMENTO")
    @SequenceGenerator(name = "SQ_ESTABELECIMENTO", sequenceName = "SQ_ESTABELECIMENTO", allocationSize = 1)
    private Long id;

    @Column(name = "NO_ESTABELECIMENTO", nullable = false)
    private String nome;

    @Column(name = "NU_TELEFONE", nullable = false)
    private String telefone;

    @Column(name = "NU_ENDERECO", nullable = false)
    private String endereco;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "FK_PROFISSIONAL")
    private Profissional profissional;
}
