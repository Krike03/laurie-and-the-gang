package org.laurieandthegang.parkshark.security;

public record KeycloakUserDTO (String userName, String password, Role role){
}
