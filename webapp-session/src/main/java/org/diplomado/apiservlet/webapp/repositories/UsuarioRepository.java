package org.diplomado.apiservlet.webapp.repositories;

import org.diplomado.apiservlet.webapp.models.Usuario;

import java.sql.SQLException;

public interface UsuarioRepository extends CrudRepository<Usuario> {
    Usuario porUsername(String username) throws SQLException;
}
