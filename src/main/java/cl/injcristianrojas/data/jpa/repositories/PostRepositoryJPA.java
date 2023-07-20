package cl.injcristianrojas.data.jpa.repositories;

import cl.injcristianrojas.data.jpa.model.PostJPA;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepositoryJPA extends JpaRepository<PostJPA, Long> {
    List<PostJPA> findAll();
    <S extends PostJPA> S save(S s);
}
