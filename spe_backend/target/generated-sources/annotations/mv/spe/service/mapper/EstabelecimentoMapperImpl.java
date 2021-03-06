package mv.spe.service.mapper;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import mv.spe.domain.Estabelecimento;
import mv.spe.service.dto.EstabelecimentoDTO;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-03-03T09:32:54-0300",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 1.8.0_282 (Private Build)"
)
@Component
public class EstabelecimentoMapperImpl implements EstabelecimentoMapper {

    @Override
    public Estabelecimento toEntity(EstabelecimentoDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Estabelecimento estabelecimento = new Estabelecimento();

        estabelecimento.setId( dto.getId() );
        estabelecimento.setNome( dto.getNome() );
        estabelecimento.setTelefone( dto.getTelefone() );
        estabelecimento.setEndereco( dto.getEndereco() );

        return estabelecimento;
    }

    @Override
    public EstabelecimentoDTO toDto(Estabelecimento entity) {
        if ( entity == null ) {
            return null;
        }

        EstabelecimentoDTO estabelecimentoDTO = new EstabelecimentoDTO();

        estabelecimentoDTO.setId( entity.getId() );
        estabelecimentoDTO.setNome( entity.getNome() );
        estabelecimentoDTO.setTelefone( entity.getTelefone() );
        estabelecimentoDTO.setEndereco( entity.getEndereco() );

        return estabelecimentoDTO;
    }

    @Override
    public List<Estabelecimento> toEntity(List<EstabelecimentoDTO> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<Estabelecimento> list = new ArrayList<Estabelecimento>( dtoList.size() );
        for ( EstabelecimentoDTO estabelecimentoDTO : dtoList ) {
            list.add( toEntity( estabelecimentoDTO ) );
        }

        return list;
    }

    @Override
    public List<EstabelecimentoDTO> toDto(List<Estabelecimento> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<EstabelecimentoDTO> list = new ArrayList<EstabelecimentoDTO>( entityList.size() );
        for ( Estabelecimento estabelecimento : entityList ) {
            list.add( toDto( estabelecimento ) );
        }

        return list;
    }
}
