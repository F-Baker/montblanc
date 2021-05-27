//package montblanc.persistence.dto;
//
//import montblanc.persistence.entities.Role;
//import montblanc.persistence.entities.User;
//import montblanc.persistence.repositories.RoleRepository;
//import montblanc.security.SignUpRequest;
//import org.mapstruct.InjectionStrategy;
//import org.mapstruct.Mapper;
//import org.mapstruct.Mapping;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.stereotype.Component;
//
//import java.util.List;
//
//@Component
//@Mapper(injectionStrategy = InjectionStrategy.CONSTRUCTOR, componentModel = "spring")
//public abstract class SignUpRequestMapper {
//
//    @Autowired
//    public RoleRepository roleRepository;
//
//    @Mapping(target = "password", ignore = true)
//    abstract SignUpRequest toDto(User user);
//
//    @Mapping(target = "roles", expression = "java(getRoles())")
//    @Mapping(target = "password", expression = "java(encode().encode(signUpRequest.getPassword()))")
//    public abstract User toModel(SignUpRequest signUpRequest);
//    //todo: remove the mapstruct mapper and create an object User
//    // eg User user = new user
//    // take the expression convert it to an object
//    // need to direct idea to the mapping
//
//
//    public BCryptPasswordEncoder encode() {
//        return new BCryptPasswordEncoder();
//    }
//
//    public List<Role> getRoles() {
//        return List.of(roleRepository.findByName("USER"));
//    }
//}
