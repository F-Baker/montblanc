package montblanc.controller;

import montblanc.dto.LoginDTO;
import montblanc.dto.UserDTO;
import montblanc.service.AuthService;
import montblanc.service.JWTService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    JWTService jwtService;
    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<UserDTO> login(@RequestBody LoginDTO loginDTO) {
        try {
            UserDTO userDTO = authService.login(loginDTO.getEmail(), loginDTO.getPassword());
            System.err.println(userDTO);
            String res = jwtService.getJWT(userDTO.getEmail(), userDTO);
            System.err.println(res);
            userDTO.setAccessToken(res);
            return ResponseEntity.ok(userDTO);
        } catch (Exception ex) {
            System.err.println(ex);
            return ResponseEntity.badRequest().build();
        }
    }

//	@PostMapping("/register")
//	public ResponseEntity<UserDTO> register(@RequestBody UserRegisterDTO userDTO) {

//		//tester si User est valide
//		if (errors.hasErrors()) {
//			mv.addObject("user", user);
//			mv.setViewName("user/showForm");
//		} else {
//			String pass = userDTO.getPassword();
//			String encodedPassword = encoder.encode(user.getPassword());
//			user.setPassword(encodedPassword);
//			//créer un set
//			Set<Role> roles = new HashSet<>();
//			//récupérer le role avec le libelle "USER" et l'ajouter dans set
//			Role roleUser = roleService.findByLibelle("USER");
//			roles.add(roleUser);
//			user.setRoles(roles);
//			userService.create(user);
//			//envoyer mail de confirmation
//			String body = "Nom d'utilisateur : " + user.getUsername() + "\n";
//			body += "Mot de passe : " + pass;
//			emailUtil.sendMail(user.getMail(), "Inscription", body);
//			mv.setViewName("redirect:/admin/user/list");
//		}
//		return mv;
//	}

}
