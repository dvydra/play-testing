package models;

import org.junit.Before;
import org.junit.Test;
import play.test.Fixtures;
import play.test.UnitTest;

import java.util.List;
import java.util.Map;

public class TagTest extends UnitTest {
    @Before
    public void setup() {
        Fixtures.deleteAll();
    }

    @Test
    public void testTags() {
        User bob = new User("bob@gmail.com", "secret", "Bob").save();

        Post bobPost = new Post(bob, "My first post", "Hello world").save();
        Post anotherBobPost = new Post(bob, "This is another post", "Hello world").save();

        assertEquals(0, Post.findTaggedWith("Red").size());

        bobPost.tagItWith("Red").tagItWith("Blue").save();
        anotherBobPost.tagItWith("Red").tagItWith("Green").save();

        assertEquals(2, Post.findTaggedWith("Red").size());
        assertEquals(1, Post.findTaggedWith("Blue").size());
        assertEquals(1, Post.findTaggedWith("Green").size());


        //test having all tags
        assertEquals(1, Post.findTaggedWith("Red", "Blue").size());
        assertEquals(1, Post.findTaggedWith("Red", "Green").size());
        assertEquals(0, Post.findTaggedWith("Red", "Green", "Blue").size());
        assertEquals(0, Post.findTaggedWith("Green", "Blue").size());
    }

    @Test
    public void testTagCloud() {
        User bob = new User("bob@gmail.com", "secret", "Bob").save();

        Post bobPost = new Post(bob, "My first post", "Hello world").save();
        Post anotherBobPost = new Post(bob, "This is another post", "Hello world").save();

        assertEquals(0, Post.findTaggedWith("Red").size());

        bobPost.tagItWith("Red").tagItWith("Blue").save();
        anotherBobPost.tagItWith("Red").tagItWith("Green").save();

        List<Map> cloud = Tag.getCloud();
        assertEquals(
                "[{tag=Red, number=2}, {tag=Blue, number=1}, {tag=Green, number=1}]", cloud.toString()
        );
    }

}
