package br.senac.lanchonete.Controller;

import br.senac.lanchonete.model.Categoria;
import br.senac.lanchonete.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import br.senac.lanchonete.model.Erro;

import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {

    @Autowired
    private CategoriaRepository repository;

    @GetMapping("/{id}")
    public ResponseEntity<Categoria> getCategoria(@PathVariable Long id) {
        Optional<Categoria> categoria = repository.findById(id);
        if (categoria.isPresent()) {
            return ResponseEntity.ok(categoria.get());
        } else {
            Erro erro = Erro.builder().status(HttpStatus.NOT_FOUND).mensagem("Categoria não encontrada").
                    exception("NotFoundException").build();
            return new ResponseEntity(erro, erro.getStatus());
        }

    }

    @PostMapping
    public ResponseEntity<Categoria> criarCategoria(@RequestBody Categoria tipo) {
        try {
            repository.save(tipo);
            return ResponseEntity.created(URI.create("/categorias/" + tipo.getId())).body(tipo);
        } catch (DataIntegrityViolationException e) {
            Erro erro = Erro.builder().status(HttpStatus.BAD_REQUEST).mensagem("duplicado").
                    exception(e.getClass().getName()).build();
            return new ResponseEntity(erro, erro.getStatus());
        }catch (Exception e){
            Erro erro = Erro.builder().status(HttpStatus.INTERNAL_SERVER_ERROR).mensagem("erro interno" + e.getMessage()).
                    exception(e.getClass().getName()).build();
            return new ResponseEntity(erro, erro.getStatus());
        }
    }
}