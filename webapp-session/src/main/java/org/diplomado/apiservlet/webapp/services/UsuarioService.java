package org.diplomado.apiservlet.webapp.services;

import org.diplomado.apiservlet.webapp.models.Usuario;

import java.util.Optional;

public interface UsuarioService {
    Optional<Usuario> login(String username, String password);
}
