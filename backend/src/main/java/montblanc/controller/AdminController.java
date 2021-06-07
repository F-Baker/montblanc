package montblanc.controller;

import montblanc.Entity.EnrollmentStatus;
import montblanc.dto.EnrollmentEditDTO;
import montblanc.dto.UserFullDTO;
import montblanc.service.AdminService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @GetMapping("/enrollment-status")
    public ResponseEntity<List<EnrollmentStatus>> getEnrollmentStatus() {
    	List<EnrollmentStatus> status= adminService.getEnrollmentStatus();
    	return ResponseEntity.ok(status);
    }
    
    @GetMapping("/students")
    public ResponseEntity<List<UserFullDTO>> getStudents() {
    	List<UserFullDTO> students= adminService.getStudents();
    	return ResponseEntity.ok(students);
    }
    
    @PostMapping("/students/edit/status")
    public ResponseEntity<String> editStudentStatus(@RequestBody EnrollmentEditDTO editDTO) {
        try {
        	System.out.println(editDTO);
            adminService.editStudentStatus(editDTO.getStudentId(), editDTO.getStatusId());
            return ResponseEntity.ok("OK");
        } catch (Exception ex) {
            System.err.println(ex);
            return ResponseEntity.badRequest().build();
        }
    }

}
