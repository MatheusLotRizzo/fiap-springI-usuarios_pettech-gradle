package br.com.rizzo.usuarios_pettech.domain.repositories;

import br.com.rizzo.usuarios_pettech.domain.entities.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Long> {
}
