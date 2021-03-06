package mv.spe.service;

import com.github.dockerjava.api.exception.BadRequestException;
import lombok.RequiredArgsConstructor;
import mv.spe.domain.Estabelecimento;
import mv.spe.domain.Profissional;
import mv.spe.repository.ProfissionalRepository;
import mv.spe.service.dto.ProfissionalDTO;
import mv.spe.service.dto.ProfissionalListDTO;
import mv.spe.service.filter.ProfissionalFilter;
import mv.spe.service.mapper.ProfissionalMapper;
import mv.spe.service.util.ConstantsUtil;
import mv.spe.service.util.MethodUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Objects;

@Service
@Transactional
@RequiredArgsConstructor
public class ProfissionalService {


    private final ProfissionalMapper mapper;

    private final EstabelecimentoService estabelecimentoService;

    private final ProfissionalRepository repository;

    @Transactional(readOnly = true)
    public Page<ProfissionalListDTO> listar(ProfissionalFilter filtro, Pageable pageable) {
        return this.repository.listar(filtro, MethodUtil.removeCaseSort(pageable));
    }

    public ProfissionalDTO cadastrar(ProfissionalDTO dto) {

        if (Objects.nonNull(dto.getId())){
            throw new BadRequestException(ConstantsUtil.ID_NOT_NULL);
        }
        return salvar(dto);
    }

    public ProfissionalDTO editar(ProfissionalDTO dto) {

        if (!Objects.nonNull(dto.getId())) {
            throw new BadRequestException(ConstantsUtil.ID_NULL);
        }

        return salvar(dto);
    }

    private ProfissionalDTO salvar(ProfissionalDTO dto) {

        this.repository.save(this.mapper.toEntity(dto));

        return dto;
    }

    @Transactional(readOnly = true)
    public Profissional consultarPorId(Long id) {
        return this.repository
                .findById(id)
                .orElseThrow(() ->
                        new BadRequestException(ConstantsUtil.ID_NOT_FOUND));
    }

    public ProfissionalDTO obterPorId(Long id) {
        Profissional profissional = this.consultarPorId(id);
        return this.mapper.toDto(profissional);
    }


    public void excluir(Long id) {

        if (!this.repository.existsById(id)) {
        }

        ProfissionalDTO dto = this.mapper.toDto(this.repository.getOne(id));


        List<Estabelecimento> estabelecimentos =  (!CollectionUtils.isEmpty(dto.getIdEstabelecimentos()))
                ? this.estabelecimentoService.obterPorIds(dto.getIdEstabelecimentos())
                : null;

        if(Objects.nonNull(estabelecimentos)) {
            estabelecimentos.forEach(estabelecimento -> {
                this.repository.removerVinculoEstabelecimento(estabelecimento.getId());
            });
        }
        this.repository.deleteById(id);
    }

}
