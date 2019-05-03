package br.com.projeto.conecta.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.projeto.conecta.domain.HabilidadesPrincipais;

@Repository
public interface HabilidadesPrincipaisRepository extends JpaRepository<HabilidadesPrincipais, Integer> {

}