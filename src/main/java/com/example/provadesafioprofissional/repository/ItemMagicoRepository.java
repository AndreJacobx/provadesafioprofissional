package com.example.provadesafioprofissional.repository;

import com.example.provadesafioprofissional.model.ItemMagico;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemMagicoRepository extends JpaRepository<ItemMagico, Long> {
    List<ItemMagico> findByPersonagemId(Long personagemId);
}
