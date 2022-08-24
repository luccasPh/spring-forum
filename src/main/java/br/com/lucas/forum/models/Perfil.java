package br.com.lucas.forum.models;

import javax.persistence.Entity;

import org.springframework.security.core.GrantedAuthority;

@Entity
public class Perfil extends BaseModel implements GrantedAuthority {

    String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getAuthority() {
        return name;
    }
}
