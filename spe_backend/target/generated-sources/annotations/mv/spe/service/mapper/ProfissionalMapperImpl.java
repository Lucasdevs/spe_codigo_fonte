package mv.spe.service.mapper;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import mv.spe.domain.Profissional;
import mv.spe.service.dto.ProfissionalDTO;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-03-03T09:32:54-0300",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 1.8.0_282 (Private Build)"
)
@Component
public class ProfissionalMapperImpl implements ProfissionalMapper {

    @Override
    public Profissional toEntity(ProfissionalDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Profissional profissional = new Profissional();

        profissional.setId( dto.getId() );
        profissional.setNome( dto.getNome() );
        profissional.setCelular( dto.getCelular() );
        profissional.setTelefone( dto.getTelefone() );
        profissional.setFuncao( dto.getFuncao() );
        profissional.setEndereco( dto.getEndereco() );

        return profissional;
    }

    @Override
    public ProfissionalDTO toDto(Profissional entity) {
        if ( entity == null ) {
            return null;
        }

        ProfissionalDTO profissionalDTO = new ProfissionalDTO();

        profissionalDTO.setId( entity.getId() );
        profissionalDTO.setNome( entity.getNome() );
        profissionalDTO.setCelular( entity.getCelular() );
        profissionalDTO.setTelefone( entity.getTelefone() );
        profissionalDTO.setFuncao( entity.getFuncao() );
        profissionalDTO.setEndereco( entity.getEndereco() );

        return profissionalDTO;
    }

    @Override
    public List<Profissional> toEntity(List<ProfissionalDTO> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<Profissional> list = new ArrayList<Profissional>( dtoList.size() );
        for ( ProfissionalDTO profissionalDTO : dtoList ) {
            list.add( toEntity( profissionalDTO ) );
        }

        return list;
    }

    @Override
    public List<ProfissionalDTO> toDto(List<Profissional> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<ProfissionalDTO> list = new ArrayList<ProfissionalDTO>( entityList.size() );
        for ( Profissional profissional : entityList ) {
            list.add( toDto( profissional ) );
        }

        return list;
    }
}
