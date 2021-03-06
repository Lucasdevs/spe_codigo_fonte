package mv.spe.web.rest;


import lombok.RequiredArgsConstructor;
import mv.spe.service.ProfissionalService;
import mv.spe.service.dto.ProfissionalDTO;
import mv.spe.service.dto.ProfissionalListDTO;
import mv.spe.service.filter.ProfissionalFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/profissionais")
public class ProfissionalResource {

    private final ProfissionalService service;

    @PostMapping
    public ResponseEntity<ProfissionalDTO> cadastrar(@Valid @RequestBody ProfissionalDTO dto) {
        ProfissionalDTO dtoSalvo = this.service.cadastrar(dto);
        return ResponseEntity.created(URI.create("/profissionais/" + dtoSalvo.getId())).body(dtoSalvo);
    }

    @PutMapping
    public ResponseEntity<ProfissionalDTO> editar(@Valid @RequestBody ProfissionalDTO dto) {
        return ResponseEntity.ok(this.service.editar(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProfissionalDTO> obterPorId(@PathVariable Long id) {
        return ResponseEntity.ok(this.service.obterPorId(id));    }

    @PostMapping("/listar")
    public ResponseEntity<Page<ProfissionalListDTO>> listar(
            @RequestBody ProfissionalFilter filtro,
            Pageable pageable) {

        Page<ProfissionalListDTO> page = this.service.listar(filtro, pageable);
        return new ResponseEntity<>(page, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        this.service.excluir(id);
        return ResponseEntity.ok().build();
    }
}
