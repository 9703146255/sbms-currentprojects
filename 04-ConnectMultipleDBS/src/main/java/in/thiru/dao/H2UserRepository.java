package in.thiru.dao;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in.thiru.entity.H2User;

@Repository
@Qualifier("h2")
public interface H2UserRepository extends JpaRepository<H2User, Long>{

}
