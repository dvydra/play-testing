package models;

import play.data.validation.MaxSize;
import play.data.validation.Required;
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
    @Required
    public String author;

    @Lob
    @Required
    @MaxSize(10000)
    public String content;

    @Required
    public Date postedAt;

    @ManyToOne
    @Required
    public Post post;
    
    public Comment(Post post, String author, String content) {
        this.post = post;
        this.author = author;
        this.content = content;
        this.postedAt = new Date();
    }

    public String toString() {
        return String.format("Comment: %s - %s", author, postedAt.toString());
    }

    
}
