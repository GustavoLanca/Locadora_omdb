package com.example.locadora.annotations;

public enum AccessLevel {
    PUBLIC,      // Sem validação
    USER,        // Usuário comum
    MODERATOR,   // Moderador
    ADMIN,       // Administrador
    SUPER_ADMIN  // Super administrador
}