package com.aluracursos.forohub.infra.security;

import com.aluracursos.forohub.domain.usuario.UsuarioRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    @Autowired
    private TokenService tokenService;
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        System.out.println("El filtro está siendo llamado");
        //Obtener el token del header
        var authHeader = request.getHeader("Authorization");//.replace("Bearer ", "");
        System.out.println("authHeader: "+authHeader);
        if (authHeader != null){
            //System.out.println("Validamos que token no es nulo");
            var token = authHeader.replace("Bearer ", "");
            //System.out.println("token en filter: "+ token);
            var sujeto = tokenService.getSubjet(token);
            //System.out.println("tokenService.getSubjet(token): "+tokenService.getSubjet(token));//Este usuario tiene sesión?
            if (sujeto != null){
                // token válido
                var usuario = usuarioRepository.findByEmail(sujeto);
                var authentication = new UsernamePasswordAuthenticationToken(usuario, null,
                        usuario.getAuthorities()); // Forzamos un inicio de sesión
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }
        filterChain.doFilter(request, response);
    }
}
