package servicos.gratitude.crud_gratitude_servicos.entity.dto.usuario;

import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import servicos.gratitude.crud_gratitude_servicos.entity.Usuario;

import java.util.Collection;


public class UsuarioDetalhesDTO implements UserDetails {

    private final String nome;
    private final String email;
    private final String senha;

    public UsuarioDetalhesDTO(Usuario usuario){
        this.nome = usuario.getNome();
        this.email = usuario.getEmail();
        this.senha = usuario.getSenha();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword(){return senha;}

    @Override
    public String getUsername(){return nome;}

    @Override
    public boolean isAccountNonExpired(){return true;}

    @Override
    public boolean isAccountNonLocked(){return true;}

    @Override
    public boolean isCredentialsNonExpired(){return true;}

    @Override
    public boolean isEnabled() {return true;}



    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getSenha() {
        return senha;
    }
}
