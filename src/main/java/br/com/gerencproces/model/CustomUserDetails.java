package br.com.gerencproces.model;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class CustomUserDetails extends Usuario implements UserDetails {

	private static final long serialVersionUID = -8343759896675473320L;
	
	public CustomUserDetails(final Usuario usuario) {
		super(usuario);
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		Collection<GrantedAuthority> roles = new ArrayList<GrantedAuthority>();
		
		if(super.getAdmin()) {
			roles.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
		}
		if(super.getFinalizador()) {
			roles.add(new SimpleGrantedAuthority("ROLE_FINALIZADOR"));
		}
		if(super.getTriador()) {
			roles.add(new SimpleGrantedAuthority("ROLE_TRIADOR"));
		}
		
		return roles;
	}

	@Override
	public String getPassword() {
		return super.getSenha();
	}

	@Override
	public String getUsername() {
		return super.getLogin();
	}

	@Override
	public boolean isAccountNonExpired() {		
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {		
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
