package mv.spe.service.mapper;

import mv.spe.domain.Estabelecimento;
import mv.spe.service.dto.EstabelecimentoDTO;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface EstabelecimentoMapper extends EntityMapper<EstabelecimentoDTO, Estabelecimento> {
}
