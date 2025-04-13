package com.example.provadesafioprofissional.repository;

import com.example.provadesafioprofissional.model.Personagem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonagemRepository extends JpaRepository<Personagem, Long> {
}