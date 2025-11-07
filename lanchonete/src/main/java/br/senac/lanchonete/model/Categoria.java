package br.senac.lanchonete.model;

import lombok.Data;

@Data
public class Categoria {
    private int id;
    private String nome;
    private String descricao;
}
