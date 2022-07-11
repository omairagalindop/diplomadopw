package org.diplomado.domiapp.webapp.services;

import jakarta.inject.Inject;
import org.diplomado.domiapp.webapp.configs.Service;
import org.diplomado.domiapp.webapp.models.Usuario;
import org.diplomado.domiapp.webapp.repositories.UsuarioRepository;

import java.sql.SQLException;
import java.util.Optional;

@Service
public class UsuarioServiceImpl implements UsuarioService {
    private UsuarioRepository usuarioRepository;

    @Inject
    public UsuarioServiceImpl(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public Optional<Usuario> login(String username, String password) {
        try {
            return Optional.ofNullable(usuarioRepository.porUsername(username)).filter(u -> u.getPassword().equals(password));
        } catch (SQLException throwables) {
            throw new ServiceJdbcException(throwables.getMessage(), throwables.getCause());
        }
    }
}
