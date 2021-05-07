package montblanc.dto;

import montblanc.Entity.Role;
import montblanc.Entity.User;
import montblanc.repository.RoleRepository;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper(injectionStrategy = InjectionStrategy.CONSTRUCTOR, componentModel = "spring")
public abstract class SignUpRequestMapper {

    @Autowired
    public RoleRepository roleRepository;

    @Mapping(target = "password", ignore = true)
    abstract SignUpRequest toDto(User user);

    @Mapping(target = "roles", expression = "java(getRoles())")
    @Mapping(target = "password", expression = "java(encode().encode(signUpRequest.getPassword()))")
    public abstract User toModel(SignUpRequest signUpRequest);

    public BCryptPasswordEncoder encode() {
        return new BCryptPasswordEncoder();
    }

    public List<Role> getRoles() {
        return List.of(roleRepository.findByName("USER"));
    }
}
