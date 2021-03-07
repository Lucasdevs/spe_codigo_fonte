package mv.spe.web.rest;

import lombok.RequiredArgsConstructor;
import mv.spe.service.EstabelecimentoService;
import mv.spe.service.dto.EstabelecimentoDTO;
import mv.spe.service.dto.EstabelecimentoListDTO;
import mv.spe.service.filter.EstabelecimentoFilter;
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
@RequestMapping("api/estabelecimentos")
public class EstabelecimentoResource {

    private final EstabelecimentoService service;

    @PostMapping
    public ResponseEntity<EstabelecimentoDTO> cadastrar(@Valid @RequestBody EstabelecimentoDTO dto) {
        EstabelecimentoDTO dtoSalvo = this.service.cadastrar(dto);
        return ResponseEntity.created(URI.create("/estabelecimentos/" + dtoSalvo.getId())).body(dtoSalvo);
    }

    @PutMapping
    public ResponseEntity<EstabelecimentoDTO> editar(@Valid @RequestBody EstabelecimentoDTO dto) {
        return ResponseEntity.ok(this.service.editar(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<EstabelecimentoDTO> obterPorId(@PathVariable Long id) {
        return ResponseEntity.ok(this.service.obterPorId(id));    }

    @PostMapping("/listar")
    public ResponseEntity<Page<EstabelecimentoListDTO>> listar(
            @RequestBody EstabelecimentoFilter filtro,
            Pageable pageable) {

        Page<EstabelecimentoListDTO> page = this.service.listar(filtro, pageable);
        return new ResponseEntity<>(page, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        this.service.excluir(id);
        return ResponseEntity.ok().build();
    }
}
