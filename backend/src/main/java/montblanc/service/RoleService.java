package montblanc.service;

import montblanc.Entity.Role;
import montblanc.exception.RoleNotFoundException;
import montblanc.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class RoleService {

    @Autowired
    RoleRepository roleRepo;

    @Transactional(readOnly = true)
    public Role findByName(String name) throws RoleNotFoundException {
        Role role = roleRepo.findByName(name);
        if (null == role) throw new RoleNotFoundException();
        return role;
    }
}
