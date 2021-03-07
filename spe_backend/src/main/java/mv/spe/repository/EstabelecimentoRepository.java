package mv.spe.repository;

import mv.spe.domain.Estabelecimento;
import mv.spe.service.dto.EstabelecimentoListDTO;
import mv.spe.service.filter.EstabelecimentoFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface EstabelecimentoRepository extends JpaRepository<Estabelecimento, Long> {

    @Query("SELECT CASE WHEN count(p.id) > 0 THEN true ELSE false END FROM Estabelecimento p where p.nome = :nome AND (:id IS NULL OR :id <> p.id)")
    Boolean existsByNome(@Param("nome") String nome, @Param("id") Long id);

    @Query(
            value =
                    "SELECT     new mv.spe.service.dto.EstabelecimentoListDTO(e.id, e.nome, e.telefone, e.endereco)"
                            + " FROM        Estabelecimento e"
                            + " WHERE       (:#{#filtro.nome} IS NULL OR LOWER(e.nome) LIKE LOWER(CONCAT('%', :#{#filtro.nome}, '%')))",
            countQuery =
                    "SELECT     COUNT(e.id)"
                            + " FROM    Estabelecimento e"
                            + " WHERE   (:#{#filtro.nome} IS NULL OR LOWER(e.nome) LIKE LOWER(CONCAT('%', :#{#filtro.nome}, '%')))")
    Page<EstabelecimentoListDTO> listar(@Param("filtro") EstabelecimentoFilter filtro, Pageable pageable);


}
