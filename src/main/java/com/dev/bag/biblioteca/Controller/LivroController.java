package com.dev.bag.biblioteca.Controller;

import com.dev.bag.biblioteca.Model.Livro;
import com.dev.bag.biblioteca.Service.LivroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/livros") // 127.0.0.1:8000/livros
@CrossOrigin(origins = "*", maxAge = 3600)
public class LivroController {

    @Autowired
    private LivroService livroService;

    @GetMapping
    public List<Livro> getAllLivros() {
        return livroService.getAllLivros();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Livro> getLivroById(@PathVariable(value = "id") Long livroId) {
        Livro livro = livroService.getLivroById(livroId);
        return ResponseEntity.ok().body(livro);
    }

    @PostMapping()
    public Livro createLivro(@RequestBody Livro livro) {
        return livroService.createLivro(livro);
    }

    /* *
        {
           "fotoCapa": "123",
           "nome": "Memórias Póstumas de Brás Cubas",
           "autor": "Machadão de Asis",
           "ano": 1890,
           "exemplares": 30
         }
    * */

    @PutMapping("/{id}")
    public ResponseEntity<Livro> updateLivro(@PathVariable(value = "id") Long livroId, @RequestBody Livro livroDetails) {
        Livro updatedLivro = livroService.updateLivro(livroId, livroDetails);
        return ResponseEntity.ok(updatedLivro);
    }

    /* *
        {
           "fotoCapa": "foto_png",
           "nome": "Memórias Póstumas de Brás Cubas",
           "autor": "Machadão de Asis",
           "ano": 1980,
           "exemplares": 30
         }
    * */

    @DeleteMapping("/{id}")
    public Map<String, Boolean> deleteLivro(@PathVariable(value = "id") Long livroId) {
        livroService.deleteLivro(livroId);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

}
