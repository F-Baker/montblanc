package montblanc.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import montblanc.Entity.EnrollmentStatus;
import montblanc.Entity.User;
import montblanc.dto.UserFullDTO;
import montblanc.repository.EnrollmentStatusRepository;
import montblanc.repository.RoleRepository;
import montblanc.repository.UserRepository;

@Service
public class AdminService {
	@Autowired	
	UserRepository userRepo;
	
	@Autowired	
	EnrollmentStatusRepository esRepo;
	
	@Autowired	
	RoleRepository roleRepo;
	
	public void editStudentStatus(long studentId, int statusId) {
		User user = userRepo.findById(studentId).get();
		EnrollmentStatus enrollmentStatus = esRepo.findById(statusId).get();
		user.setEnrollmentStatus(enrollmentStatus);
		userRepo.save(user);
	}

	public List<EnrollmentStatus> getEnrollmentStatus() {
		return esRepo.findAll();
	}

	public List<UserFullDTO> getStudents() {
		List<User> users = userRepo.findByRole("ROLE_STUDENT");
		List<UserFullDTO> usersDTO = new ArrayList<UserFullDTO>();
		for(User user : users) {
			UserFullDTO u = new UserFullDTO();
			u.setId(user.getUserId());
			u.setFirstName(user.getFirstName());
			u.setLastName(user.getLastName());
			u.setEmail(user.getEmail());
			u.setPhoneNumber(user.getPhoneNumber());
			u.setAddress(user.getAddress());
			u.setPostalCode(user.getPostalCode());
			u.setCity(user.getCity());
			u.setEnrollmentStatus(user.getEnrollmentStatus().getName());
			usersDTO.add(u);
		}
		return usersDTO;
	}

}
