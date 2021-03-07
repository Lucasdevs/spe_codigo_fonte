package mv.spe.service;

import com.github.dockerjava.api.exception.BadRequestException;
import lombok.RequiredArgsConstructor;
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

import java.util.ArrayList;
import java.util.Objects;

@Service
@Transactional
@RequiredArgsConstructor
public class ProfissionalService {


    private final ProfissionalMapper mapper;

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
        Profissional entidade = this.mapper.toEntity(dto);
        if (!Objects.nonNull(entidade.getEstabelecimentos())) {
            entidade.setEstabelecimentos(new ArrayList<>());
        }
        return this.mapper.toDto(this.repository.save(entidade));
    }

    private Profissional consultarPorId(Long id) {
        return this.repository
                .findById(id)
                .orElseThrow(() ->
                        new BadRequestException(ConstantsUtil.ID_NOT_FOUND));
    }
    @Transactional(readOnly = true)
    public ProfissionalDTO obterPorId(Long id) {
        return this.mapper.toDto(this.consultarPorId(id));
    }


    public void excluir(Long id) {
        Profissional entidade = this.consultarPorId(id);

        if(!entidade.getEstabelecimentos().isEmpty()) {
            entidade.getEstabelecimentos().forEach(estabelecimento -> {
                this.repository.removerVinculoEstabelecimento(estabelecimento.getId());
            });
        }
        this.repository.delete(entidade);
    }

}
