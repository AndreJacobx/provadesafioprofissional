package com.example.provadesafioprofissional.controller;

import com.example.provadesafioprofissional.model.Personagem;
import com.example.provadesafioprofissional.service.PersonagemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/personagens")
public class PersonagemController {

    @Autowired
    private PersonagemService personagemService;

    @PostMapping
    public Personagem criar(@RequestBody Personagem personagem) {
        return personagemService.salvar(personagem);
    }

    @GetMapping
    public List<Personagem> listarTodos() {
        return personagemService.listarTodos();
    }

    @GetMapping("/{id}")
    public Personagem buscarPorId(@PathVariable Long id) {
        return personagemService.buscarPorId(id)
                .orElseThrow(() -> new RuntimeException("Personagem não encontrado"));
    }

    @PatchMapping("/{id}/nome-aventureiro")
    public Personagem atualizarNomeAventureiro(@PathVariable Long id, @RequestBody String nome) {
        return personagemService.atualizarNomeAventureiro(id, nome);
    }

    @DeleteMapping("/{id}")
    public void remover(@PathVariable Long id) {
        personagemService.remover(id);
    }

}
