package mv.spe.service.mapper;

import mv.spe.domain.Profissional;
import mv.spe.service.dto.ProfissionalDTO;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface ProfissionalMapper extends EntityMapper<ProfissionalDTO, Profissional>{
}
