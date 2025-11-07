package br.senac.lanchonete.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {

    @PostMapping
    public ResponseEntity<Categorias> listarCategorias(@RequestBody Categorias tipo){



        return null;{

        }

        }






