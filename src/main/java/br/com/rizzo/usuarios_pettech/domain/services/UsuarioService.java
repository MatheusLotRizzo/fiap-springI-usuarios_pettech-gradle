package br.com.rizzo.usuarios_pettech.domain.services;

import br.com.rizzo.usuarios_pettech.domain.dto.UsuarioDTO;
import br.com.rizzo.usuarios_pettech.domain.entities.UsuarioEntity;
import br.com.rizzo.usuarios_pettech.domain.repositories.UsuarioRepository;
import br.com.rizzo.usuarios_pettech.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    @Autowired
    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public Page<UsuarioDTO> findAll(Pageable pageable) {
        Page<UsuarioEntity> usuarios = usuarioRepository.findAll(pageable);
        return usuarios.map(this::toUsuarioDto);
    }

    public UsuarioDTO findById(Long id) {
        return toUsuarioDto(usuarioRepository.findById(id).orElseThrow(() -> new NotFoundException("Usuário não encontrado")));
    }

    public UsuarioDTO save(UsuarioDTO usuarioDTO) {
        UsuarioEntity usuarioEntity = toUsuarioEntity(usuarioDTO);
        return toUsuarioDto(usuarioRepository.save(usuarioEntity));
    }

    public UsuarioDTO update(Long id, UsuarioDTO usuarioDTO) {
        UsuarioEntity usuario = usuarioRepository.findById(id).orElseThrow(() -> new NotFoundException("Usuário não encontrado"));
        usuario.setNome(usuarioDTO.nome());
        usuario.setEmail(usuarioDTO.email());
        usuario.setCpf(usuarioDTO.cpf());
        usuario.setDataNascimento(usuarioDTO.dataNascimento());
        return toUsuarioDto(usuarioRepository.save(usuario));
    }

    public void delete(Long id) {
        usuarioRepository.deleteById(id);
    }

    private UsuarioDTO toUsuarioDto(UsuarioEntity usuarioEntity) {
        return new UsuarioDTO(
                usuarioEntity.getId(),
                usuarioEntity.getNome(),
                usuarioEntity.getEmail(),
                usuarioEntity.getCpf(),
                usuarioEntity.getDataNascimento()
        );
    }

    private UsuarioEntity toUsuarioEntity(UsuarioDTO usuarioDTO) {
        return new UsuarioEntity(
                usuarioDTO.id(),
                usuarioDTO.nome(),
                usuarioDTO.email(),
                usuarioDTO.cpf(),
                usuarioDTO.dataNascimento()
        );
    }
}