package com.dev.bag.biblioteca.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.dev.bag.biblioteca.Model.Livro;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class LivroService {

    private static final String FILE_PATH = "livros.json";
    private List<Livro> livros = new ArrayList<>();
    private AtomicLong counter = new AtomicLong();

    public LivroService() {
        carregarLivros();
    }

    public List<Livro> getAllLivros() {
        return livros;
    }

    public void deleteLivro(Long id) {
        Livro livro = getLivroById(id);
        if (livro == null) {
            throw new RuntimeException("Livro não encontrado com o id :: " + id);
        }

        livros.remove(livro);
        salvarLivros();
    }

    public Livro createLivro(Livro livro) {
        livro.setId(counter.incrementAndGet());
        livros.add(livro);
        salvarLivros();
        return livro;
    }

    public Livro updateLivro(Long id, Livro livroDetails) {
        Livro livro = getLivroById(id);
        if (livro == null) {
            throw new RuntimeException("Livro não encontrado com o id :: " + id);
        }

        livro.setFotoCapa(livroDetails.getFotoCapa());
        livro.setNome(livroDetails.getNome());
        livro.setAutor(livroDetails.getAutor());
        livro.setAno(livroDetails.getAno());
        livro.setExemplares(livroDetails.getExemplares());

        salvarLivros();
        return livro;
    }


    public Livro getLivroById(Long id) {
        for (Livro livro : livros) {
            if (livro.getId().equals(id)) {
                return livro;
            }
        }
        return null;
    }

    private void salvarLivros() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            mapper.writeValue(new File(FILE_PATH), livros);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void carregarLivros() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            File file = new File(FILE_PATH);
            if (file.exists()) {
                String json = new String(Files.readAllBytes(Paths.get(FILE_PATH)));
                Livro[] livrosArray = mapper.readValue(file, Livro[].class);
                for (Livro livro : livrosArray) {
                    livros.add(livro);
                    counter.set(Math.max(counter.get(), livro.getId()));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
