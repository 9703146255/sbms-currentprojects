package in.thiru.service;

import org.springframework.stereotype.Service;

import in.thiru.dao.H2UserRepository;
import in.thiru.dao.MySQLUserRepository;
import in.thiru.entity.H2User;
import in.thiru.entity.MySQLUser;

@Service
public class UserService {
	
	 private final MySQLUserRepository mysqlUserRepository;
	    private final H2UserRepository h2UserRepository;

	    public UserService(MySQLUserRepository mysqlUserRepository, H2UserRepository h2UserRepository) {
	        this.mysqlUserRepository = mysqlUserRepository;
	        this.h2UserRepository = h2UserRepository;
	    }

	    public MySQLUser saveMySQLUser(MySQLUser user) {
	        return mysqlUserRepository.save(user);
	    }

	    public H2User saveH2User(H2User user) {
	        return h2UserRepository.save(user);
	    }

}
