package org.diplomado.domiapp.webapp.repositories;

import org.diplomado.domiapp.webapp.models.Usuario;

import java.sql.SQLException;

public interface UsuarioRepository extends CrudRepository<Usuario> {
    Usuario porUsername(String username) throws SQLException;
}
