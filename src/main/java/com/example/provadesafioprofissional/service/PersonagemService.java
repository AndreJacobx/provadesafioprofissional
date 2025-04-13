package com.example.provadesafioprofissional.service;

import com.example.provadesafioprofissional.model.Personagem;
import com.example.provadesafioprofissional.repository.PersonagemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonagemService {

    @Autowired
    private PersonagemRepository personagemRepository;

    public List<Personagem> listarTodos() {
        return personagemRepository.findAll();
    }

    public Optional<Personagem> buscarPorId(Long id) {
        return personagemRepository.findById(id);
    }

    public Personagem salvar(Personagem personagem) {
        if (personagem.getForcaBase() + personagem.getDefesaBase() > 10) {
            throw new IllegalArgumentException("Força + Defesa não pode passar de 10.");
        }
        return personagemRepository.save(personagem);
    }

    public Personagem atualizarNomeAventureiro(Long id, String novoNome) {
        Personagem personagem = personagemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Personagem não encontrado."));
        personagem.setNomeAventureiro(novoNome);
        return personagemRepository.save(personagem);
    }

    public void remover(Long id) {
        personagemRepository.deleteById(id);
    }
}
