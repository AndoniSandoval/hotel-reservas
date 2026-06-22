package com.aa.auth.services;

import java.util.Set;

import com.aa.auth.dto.UsuarioRequest;
import com.aa.auth.dto.UsuarioResponse;

public interface UsuarioService {

    Set<UsuarioResponse> listar();

    UsuarioResponse registrar(UsuarioRequest request);

    UsuarioResponse eliminar(String username);
}