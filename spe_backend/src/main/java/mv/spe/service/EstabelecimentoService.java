package mv.spe.service;

import com.github.dockerjava.api.exception.BadRequestException;
import lombok.RequiredArgsConstructor;
import mv.spe.domain.Estabelecimento;
import mv.spe.repository.EstabelecimentoRepository;
import mv.spe.service.dto.EstabelecimentoDTO;
import mv.spe.service.dto.EstabelecimentoListDTO;
import mv.spe.service.filter.EstabelecimentoFilter;
import mv.spe.service.mapper.EstabelecimentoMapper;
import mv.spe.service.util.ConstantsUtil;
import mv.spe.service.util.MethodUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;


@Service
@Transactional
@RequiredArgsConstructor
public class EstabelecimentoService {

    private final EstabelecimentoMapper mapper;

    private final EstabelecimentoRepository repository;

    @Transactional(readOnly = true)
    public Page<EstabelecimentoListDTO> listar(EstabelecimentoFilter filtro, Pageable pageable) {
        return this.repository.listar(filtro, MethodUtil.removeCaseSort(pageable));
    }

    public EstabelecimentoDTO cadastrar(EstabelecimentoDTO dto) {

        if (Objects.nonNull(dto.getId())){
            throw new BadRequestException(ConstantsUtil.ID_NOT_NULL);
        }
        return salvar(dto);
    }

    public EstabelecimentoDTO editar(EstabelecimentoDTO dto) {

        if (!Objects.nonNull(dto.getId())) {
            throw new BadRequestException(ConstantsUtil.ID_NULL);
        }

        return salvar(dto);
    }

    @Transactional(readOnly = true)
    public List<Estabelecimento> obterPorIds(List<Long> id) {
        return this.repository.findAllById(id);
    }

    private EstabelecimentoDTO salvar(EstabelecimentoDTO dto) {

        this.repository.save(this.mapper.toEntity(dto));

        return dto;
    }

    @Transactional(readOnly = true)
    public Estabelecimento consultarPorId(Long id) {
        return this.repository
                .findById(id)
                .orElseThrow(() ->
                        new BadRequestException(ConstantsUtil.ID_NOT_FOUND));
    }

    public EstabelecimentoDTO obterPorId(Long id) {
        Estabelecimento estabelecimento = this.consultarPorId(id);
        return this.mapper.toDto(estabelecimento);
    }


    public void excluir(Long id) {
        if (!this.repository.existsById(id)) {
            throw new BadRequestException(ConstantsUtil.ID_NOT_FOUND);
        }
        this.repository.deleteById(id);
    }


}
