package com.cg.service;

import java.util.List;
import java.util.Optional;

import javax.validation.constraints.NotBlank;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.cg.bean.Admin;
import com.cg.dao.IAdminRepository;
import com.cg.exception.ResourceNotFoundException;
import com.cg.exception.UserAlreadyExistsException;

@Service
public class AdminServiceImpl implements IAdminService {

	@Autowired
	IAdminRepository adminRepo;
	
	@Autowired
	private PasswordEncoder bcryptEncoder;
	
	@Override
	public List<Admin> getAllAdmins() {
		List<Admin> AdminList = (List<Admin>) adminRepo.findAll();		
		return AdminList;
	}

	@Override
	public Admin getAdminById(long AdminId) throws ResourceNotFoundException {
		Optional<Admin> AdminOptional = adminRepo.findById(AdminId);
		return AdminOptional.orElseThrow(() -> new ResourceNotFoundException("Admin details not found"));
	}

	@Override
	public Admin addAdmin(Admin admin) throws UserAlreadyExistsException {
		List< @NotBlank Admin> AdminList = getAllAdmins();
		for (Admin systemAdmin : AdminList) {
			if (systemAdmin.getEmail().equals(admin.getEmail()))
				throw new UserAlreadyExistsException("Admin Already exists");
		}
		// Encrypt the password
		admin.setPassword(bcryptEncoder.encode(admin.getPassword()));
		return adminRepo.save(admin);
	}

	public Admin removeAdmin(long id) throws ResourceNotFoundException {
		Admin customerToBeRemoved = getAdminById(id);
		adminRepo.deleteById(id);
		return customerToBeRemoved;
	}
	
	public Admin updateAdmin(Admin admin) throws ResourceNotFoundException {
		getAdminById(admin.getId());	// to check whether the Admin exists
		return adminRepo.save(admin); 
	}

}
