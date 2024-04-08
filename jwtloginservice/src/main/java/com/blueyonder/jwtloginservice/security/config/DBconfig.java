//package com.blueyonder.jwtloginservice.security.config;
//
//import com.blueyonder.jwtloginservice.entities.ERole;
//import com.blueyonder.jwtloginservice.entities.Role;
//import com.blueyonder.jwtloginservice.entities.UserCredentials;
//import com.blueyonder.jwtloginservice.repositories.RoleRepository;
//import com.blueyonder.jwtloginservice.repositories.UserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.context.event.ApplicationReadyEvent;
//import org.springframework.context.event.EventListener;
//import org.springframework.stereotype.Component;
//
//import java.util.HashSet;
//import java.util.List;
//import java.util.Set;
//
//@Component
//public class DBconfig {
//    @Autowired
//    private UserRepository repo;
//
//    @Autowired
//    private RoleRepository rRepo;
//
//    @EventListener
//    public void appReady(ApplicationReadyEvent event){
//        Role role = new Role();
//        role.setName(ERole.ROLE_USER);
//
//        rRepo.save(role);
//
//        role.setName(ERole.ROLE_ADMIN);
//        rRepo.save(role);
//
//        List<Role> lrole = rRepo.findAll();
//        for(Role r : lrole){
//            System.out.println(r.getName());
//        }
//
////        UserCredentials uc = new UserCredentials("admin", "admin@admin.com", "password");
////        Set<Role> roleSet = new HashSet<>();
////        roleSet.add(role);
////        uc.setRoles(roleSet);
////
////        repo.save(uc);
//    }
//
//}
