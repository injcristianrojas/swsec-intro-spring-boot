package cl.injcristianrojas.data.jpa.repositories;

import cl.injcristianrojas.data.jpa.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findAll();
    <S extends Post> S save(S s);
}
