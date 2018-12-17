package obruening.multiinstance.model.primary;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "comments")
public class Comment {
    
    public Comment() {}
    public Comment(Post post, String author, String text) {
        this.post = post;
        this.author = author;
        this.text = text;
    }

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

    private String author;
    
	private String text;
	
    @ManyToOne
    @JoinColumn(name="post_id")
	private Post post;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public String getAuthor() {
        return author;
    }
    
	public void setAuthor(String author) {
        this.author = author;
    }
    
    public String getText() {
        return text;
    }
    
    public void setText(String text) {
        this.text = text;
    }
    
    public Post getPost() {
        return post;
    }
    
    public void setPost(Post post) {
        this.post = post;
    }
}