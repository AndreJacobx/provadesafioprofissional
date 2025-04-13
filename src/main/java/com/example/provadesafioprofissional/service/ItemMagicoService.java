package com.example.provadesafioprofissional.service;

import com.example.provadesafioprofissional.model.ItemMagico;
import com.example.provadesafioprofissional.model.Personagem;
import com.example.provadesafioprofissional.model.TipoItem;
import com.example.provadesafioprofissional.repository.ItemMagicoRepository;
import com.example.provadesafioprofissional.repository.PersonagemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemMagicoService {
    @Autowired
    private ItemMagicoRepository itemMagicoRepository;

    @Autowired
    private PersonagemRepository personagemRepository;

    public List<ItemMagico> listarTodos() {
        return itemMagicoRepository.findAll();
    }

    public ItemMagico buscarPorId(Long id) {
        return itemMagicoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Item Mágico não encontrado."));
    }

    public ItemMagico salvar(ItemMagico item) {
        validarItem(item);
        return itemMagicoRepository.save(item);
    }

    public void remover(Long id) {
        itemMagicoRepository.deleteById(id);
    }

    public List<ItemMagico> listarPorPersonagem(Long personagemId) {
        return itemMagicoRepository.findByPersonagemId(personagemId);
    }

    public ItemMagico adicionarAoPersonagem(Long personagemId, ItemMagico item) {
        Personagem personagem = personagemRepository.findById(personagemId)
                .orElseThrow(() -> new RuntimeException("Personagem não encontrado."));

        if (item.getTipo() == TipoItem.AMULETO && personagem.possuiAmuleto()) {
            throw new RuntimeException("Personagem já possui um Amuleto.");
        }

        validarItem(item);

        item.setPersonagem(personagem);
        personagem.getItensMagicos().add(item);

        return itemMagicoRepository.save(item);
    }

    public ItemMagico buscarAmuletoDoPersonagem(Long personagemId) {
        return itemMagicoRepository.findByPersonagemId(personagemId).stream()
                .filter(item -> item.getTipo() == TipoItem.AMULETO)
                .findFirst()
                .orElse(null);
    }

    public void removerDoPersonagem(Long itemId) {
        ItemMagico item = buscarPorId(itemId);
        item.setPersonagem(null);
        itemMagicoRepository.save(item);
    }

    private void validarItem(ItemMagico item) {
        if (item.getForca() == 0 && item.getDefesa() == 0) {
            throw new IllegalArgumentException("Item não pode ter Força e Defesa zeradas.");
        }

        if (item.getForca() > 10 || item.getDefesa() > 10) {
            throw new IllegalArgumentException("Força e Defesa devem ser no máximo 10.");
        }

        if (item.getTipo() == TipoItem.ARMA && item.getDefesa() != 0) {
            throw new IllegalArgumentException("Armas não podem ter defesa.");
        }

        if (item.getTipo() == TipoItem.ARMADURA && item.getForca() != 0) {
            throw new IllegalArgumentException("Armaduras não podem ter força.");
        }
    }

}
