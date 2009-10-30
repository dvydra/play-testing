package models;

import play.db.jpa.Model;

import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: dvydra
 * Date: 30-Oct-2009
 * Time: 14:02:46
 * To change this template use File | Settings | File Templates.
 */

@Entity
public class Comment extends Model {
    public String author;

    @Lob
    public String content;

    public Date postedAt;

    @ManyToOne
    public Post post;
    
    public Comment(Post post, String author, String content) {
        this.post = post;
        this.author = author;
        this.content = content;
        this.postedAt = new Date();
    }

    
}
