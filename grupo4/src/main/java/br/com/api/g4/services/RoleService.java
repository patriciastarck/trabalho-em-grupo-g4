package br.com.api.g4.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.api.g4.dto.RoleDTO;
import br.com.api.g4.entities.Role;
import br.com.api.g4.repositories.RoleRepository;

@Service
public class RoleService {
	
	@Autowired
	RoleRepository roleRepository;

	public Role parseDeRole(RoleDTO objeto) {
		Role role = new Role();
		
		role.setName(objeto.getName());
		
		return role;
	}
	
	public Role save(RoleDTO role) {
		Role rolem =parseDeRole(role);
		return roleRepository.save(rolem);
	}
}
