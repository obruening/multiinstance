package obruening.multiinstance.repository.primary;

import org.springframework.data.jpa.repository.JpaRepository;

import obruening.multiinstance.model.primary.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}