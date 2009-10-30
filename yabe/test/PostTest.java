import models.Post;
import models.User;
import org.junit.Before;
import org.junit.Test;
import play.test.Fixtures;
import play.test.UnitTest;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: dvydra
 * Date: 30-Oct-2009
 * Time: 13:56:40
 * To change this template use File | Settings | File Templates.
 */
public class PostTest extends UnitTest {

    @Before
    public void setup() {
        Fixtures.deleteAll();
    }

    @Test
    public void createPost() {
        // Create a new user and save it
        User bob = new User("bob@gmail.com", "secret", "Bob").save();

        // Create a new post
        new Post(bob, "My first post", "Hello world").save();

        // Test that the post has been created
        assertEquals(1, Post.count());

        // Retrieve all post created by bob
        List<Post> bobPosts = Post.find("byAuthor", bob).fetch();

        // Tests
        assertEquals(1, bobPosts.size());
        Post firstPost = bobPosts.get(0);
        assertNotNull(firstPost);
        assertEquals(bob, firstPost.author);
        assertEquals("My first post", firstPost.title);
        assertEquals("Hello world", firstPost.content);
        assertNotNull(firstPost.postedAt);
    }
}
