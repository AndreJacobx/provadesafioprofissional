package com.example.provadesafioprofissional.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Personagem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String nome;
    private String nomeAventureiro;

    @Enumerated(EnumType.STRING)
    private Classe classe;

    private int level;
    private int forcaBase;
    private int defesaBase;

    @OneToMany(mappedBy = "personagem", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ItemMagico> itensMagicos = new ArrayList<>();

    public Personagem() {}

    public Personagem(String nome, String nomeAventureiro, Classe classe, int level, int forcaBase, int defesaBase) {
        if (forcaBase + defesaBase > 10) {
            throw new IllegalArgumentException("Força e Defesa base não podem somar mais de 10 pontos.");
        }
        this.nome = nome;
        this.nomeAventureiro = nomeAventureiro;
        this.classe = classe;
        this.level = level;
        this.forcaBase = forcaBase;
        this.defesaBase = defesaBase;
    }

    public int getForcaTotal() {
        int soma = forcaBase;
        for (ItemMagico item : itensMagicos) {
            soma += item.getForca();
        }
        return soma;
    }

    public int getDefesaTotal() {
        int soma = defesaBase;
        for (ItemMagico item : itensMagicos) {
            soma += item.getDefesa();
        }
        return soma;
    }

    public boolean possuiAmuleto() {
        return itensMagicos.stream().anyMatch(item -> item.getTipo() == TipoItem.AMULETO);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNomeAventureiro() {
        return nomeAventureiro;
    }

    public void setNomeAventureiro(String nomeAventureiro) {
        this.nomeAventureiro = nomeAventureiro;
    }

    public Classe getClasse() {
        return classe;
    }

    public void setClasse(Classe classe) {
        this.classe = classe;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getForcaBase() {
        return forcaBase;
    }

    public void setForcaBase(int forcaBase) {
        this.forcaBase = forcaBase;
    }

    public int getDefesaBase() {
        return defesaBase;
    }

    public void setDefesaBase(int defesaBase) {
        this.defesaBase = defesaBase;
    }

    public List<ItemMagico> getItensMagicos() {
        return itensMagicos;
    }

    public void setItensMagicos(List<ItemMagico> itensMagicos) {
        this.itensMagicos = itensMagicos;
    }


}
