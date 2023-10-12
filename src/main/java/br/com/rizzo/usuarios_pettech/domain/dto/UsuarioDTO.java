package br.com.rizzo.usuarios_pettech.domain.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDate;

public record UsuarioDTO(
        Long id,
        @NotBlank(message = "O Nome é obrigatório")
        String nome,
        @NotBlank(message = "O Email é obrigatório")
        @Email(message = "O Email é inválido")
        String email,
        @CPF(message = "O CPF é inválido")
        String cpf,
        LocalDate dataNascimento
) {
}
