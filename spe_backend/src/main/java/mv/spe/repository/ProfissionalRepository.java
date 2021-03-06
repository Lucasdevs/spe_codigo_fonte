package mv.spe.repository;

import mv.spe.domain.Estabelecimento;
import mv.spe.domain.Profissional;
import mv.spe.service.dto.ProfissionalDTO;
import mv.spe.service.dto.ProfissionalListDTO;
import mv.spe.service.filter.ProfissionalFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;

@Repository
public interface ProfissionalRepository  extends JpaRepository<Profissional, Long> {

    @Query("SELECT CASE WHEN count(p.id) > 0 THEN true ELSE false END FROM Profissional p where p.nome = :nome AND (:id IS NULL OR :id <> p.id)")
    Boolean existsByNome(@Param("nome") String nome, @Param("id") Long id);



    @Query(
            value =
                    "SELECT     new mv.spe.service.dto.ProfissionalListDTO(p.id, p.nome, p.celular, p.telefone, p.funcao, p.endereco)"
                            + " FROM        Profissional p"
                            + " WHERE       (:#{#filtro.nome} IS NULL OR LOWER(p.nome) LIKE LOWER(CONCAT('%', :#{#filtro.nome}, '%')))"
                            + " AND         (:#{#filtro.funcao} IS NULL OR LOWER(p.funcao) LIKE LOWER(CONCAT('%', :#{#filtro.funcao}, '%')))",
            countQuery =
                    "SELECT     COUNT(p.id)"
                            + " FROM    Profissional p"
                            + " WHERE   (:#{#filtro.nome} IS NULL OR LOWER(p.nome) LIKE LOWER(CONCAT('%', :#{#filtro.nome}, '%')))"
                            + " AND     (:#{#filtro.funcao} IS NULL OR p.funcao = :#{#filtro.funcao})")
    Page<ProfissionalListDTO> listar(@Param("filtro") ProfissionalFilter filtro, Pageable pageable);

    @Modifying
    @Query("DELETE FROM Estabelecimento up WHERE up.profissional.id = :idEstabelecimento")
    void removerVinculoEstabelecimento(@Param("idEstabelecimento") Long idEstabelecimento);
}
