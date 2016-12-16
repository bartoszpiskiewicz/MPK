package com.mpk;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mpk.dao.UserRepository;
import com.mpk.entity.User;

@Component
public class Test implements InitializingBean {
	
	@Autowired
	UserRepository userRepo;

    @Override
    public void afterPropertiesSet() throws Exception {
    	User user = new User();
    	user.setFirstName("Mateusz");
    	user.setLastName("Nalepa");
    	user.setUsername("mateusz");
    	user.setPassword("password");
    	user.setMail("mateusz@mail.com");
    	user.setPhone("111222333");
    	user.setActive(true);
    	user.setRole("ROLE_ADMIN");
    	userRepo.save(user);
    	
    	user = new User();
    	user.setFirstName("Patryk");
    	user.setLastName("Olesiñski");
    	user.setUsername("patryk");
    	user.setPassword("password");
    	user.setMail("patryk@mail.com");
    	user.setPhone("111222333");
    	user.setActive(true);
    	user.setRole("ROLE_USER");
    	userRepo.save(user);
    	
    	user = new User();
    	user.setFirstName("Bartek");
    	user.setLastName("Piskiewicz");
    	user.setUsername("bartek");
    	user.setPassword("password");
    	user.setMail("bartek@mail.com");
    	user.setPhone("111222333");
    	user.setActive(true);
    	user.setRole("ROLE_ADMIN");
    	userRepo.save(user);
    	
    	user = new User();
    	user.setFirstName("Dawid");
    	user.setLastName("Kij");
    	user.setUsername("dawid");
    	user.setPassword("password");
    	user.setMail("dawid@mail.com");
    	user.setPhone("111222333");
    	user.setActive(true);
    	user.setRole("ROLE_USER");
    	userRepo.save(user);
    	
    	user = new User();
    	user.setFirstName("User");
    	user.setLastName("User");
    	user.setUsername("user");
    	user.setPassword("password");
    	user.setMail("user@mail.com");
    	user.setPhone("111222333");
    	user.setActive(true);
    	user.setRole("ROLE_USER");
    	userRepo.save(user);
    	
    	user = new User();
    	user.setFirstName("Admin");
    	user.setLastName("Admin");
    	user.setUsername("admin");
    	user.setPassword("password");
    	user.setMail("admin@mail.com");
    	user.setPhone("111222333");
    	user.setActive(true);
    	user.setRole("ROLE_ADMIN");
    	userRepo.save(user);
    }
}
