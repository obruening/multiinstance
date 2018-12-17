package obruening.multiinstance.service.primary;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import obruening.multiinstance.model.primary.Post;
import obruening.multiinstance.repository.primary.PostRepository;

@Service
public class PostService {

	@Autowired
	private PostRepository postRepository;

	@Transactional("primaryTransactionManager")
	public Post save(Post post) {
		return postRepository.save(post);
	}
}
