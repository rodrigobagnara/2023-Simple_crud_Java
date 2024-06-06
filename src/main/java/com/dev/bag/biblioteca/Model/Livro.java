package com.dev.bag.biblioteca.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Livro {
    private Long id;
    private String fotoCapa;
    private String nome;
    private String autor;
    private Integer ano;
    private Integer exemplares;
}

