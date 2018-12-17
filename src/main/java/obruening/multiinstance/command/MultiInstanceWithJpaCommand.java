package obruening.multiinstance.command;

import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;

import org.camunda.bpm.engine.RuntimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import obruening.multiinstance.model.primary.Comment;
import obruening.multiinstance.model.primary.Post;
import obruening.multiinstance.service.primary.PostService;

@Component
@Order(3)
public class MultiInstanceWithJpaCommand implements Command {

    @Autowired
    private PostService postService;

    @Autowired
    private RuntimeService runtimeService;

    @Override
    public String getName() {

        return "JPA Entity List";
    }

    @Override
    @Transactional("primaryTransactionManager")
    public void execute() {

        Post post = new Post();
        post.setText("Post");

        post.setComments(Arrays.asList(
                new Comment(post, "Peter", "Comment 1"), 
                new Comment(post, "Paul", "Comment 2"),
                new Comment(post, "Mary", "Comment 2")));

        post = postService.save(post);

        Map<String, Object> map = new TreeMap<>();
        map.put("post", post);

        runtimeService.startProcessInstanceByKey("multiInstanceWithJpaEntities", map);
    }
}
