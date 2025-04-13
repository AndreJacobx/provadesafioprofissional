package com.example.provadesafioprofissional.controller;

import com.example.provadesafioprofissional.model.ItemMagico;
import com.example.provadesafioprofissional.service.ItemMagicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/itens-magicos")
public class ItemMagicoController {

    @Autowired
    private ItemMagicoService itemMagicoService;

    @PostMapping
    public ItemMagico criar(@RequestBody ItemMagico item) {
        return itemMagicoService.salvar(item);
    }

    @GetMapping
    public List<ItemMagico> listarTodos() {
        return itemMagicoService.listarTodos();
    }

    @GetMapping("/{id}")
    public ItemMagico buscarPorId(@PathVariable Long id) {
        return itemMagicoService.buscarPorId(id);
    }

    @PostMapping("/personagem/{personagemId}")
    public ItemMagico adicionarAoPersonagem(@PathVariable Long personagemId, @RequestBody ItemMagico item) {
        return itemMagicoService.adicionarAoPersonagem(personagemId, item);
    }

    @GetMapping("/personagem/{personagemId}")
    public List<ItemMagico> listarPorPersonagem(@PathVariable Long personagemId) {
        return itemMagicoService.listarPorPersonagem(personagemId);
    }

    @GetMapping("/personagem/{personagemId}/amuleto")
    public ItemMagico buscarAmuleto(@PathVariable Long personagemId) {
        return itemMagicoService.buscarAmuletoDoPersonagem(personagemId);
    }

    @DeleteMapping("/{id}/remover-do-personagem")
    public void removerDoPersonagem(@PathVariable Long id) {
        itemMagicoService.removerDoPersonagem(id);
    }

    @DeleteMapping("/{id}")
    public void remover(@PathVariable Long id) {
        itemMagicoService.remover(id);
    }
}
