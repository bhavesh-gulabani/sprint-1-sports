package com.cg.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cg.bean.Admin;
import com.cg.exception.ResourceNotFoundException;
import com.cg.exception.UserAlreadyExistsException;

@Service
public interface IAdminService {
	public List<Admin> getAllAdmins();
	public Admin getAdminById(long custId) throws ResourceNotFoundException;
	public Admin removeAdmin(long id) throws ResourceNotFoundException;
	public Admin addAdmin(Admin admin) throws UserAlreadyExistsException;
	public Admin updateAdmin(Admin admin) throws ResourceNotFoundException;
}
