package in.thiru.dao;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in.thiru.entity.MySQLUser;

@Repository
@Qualifier("mysql") 
public interface MySQLUserRepository extends JpaRepository<MySQLUser, Long> {

}
