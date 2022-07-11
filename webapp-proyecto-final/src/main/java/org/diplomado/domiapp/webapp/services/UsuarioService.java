package org.diplomado.domiapp.webapp.services;

import org.diplomado.domiapp.webapp.models.Usuario;

import java.util.Optional;

public interface UsuarioService {
    Optional<Usuario> login(String username, String password);
}
