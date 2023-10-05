package br.com.rizzo.usuarios_pettech.domain.dto;

import java.time.LocalDate;

public record UsuarioDTO(Long id, String nome, String email, String cpf, LocalDate dataNascimento) {
}
