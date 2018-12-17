package obruening.multiinstance.repository.primary;

import org.springframework.data.jpa.repository.JpaRepository;

import obruening.multiinstance.model.primary.Post;

public interface PostRepository extends JpaRepository<Post, Long> {
}