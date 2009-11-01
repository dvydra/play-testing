package models;

import play.data.validation.Required;
import play.db.jpa.Model;

import javax.persistence.Entity;
import java.util.List;
import java.util.Map;

@Entity
public class Tag extends Model implements Comparable<Tag> {

    @Required
    public String name;

    private Tag(String name) {
        this.name = name;
    }

    public String toString() {
        return String.format("Tag: %s", name);
    }

    public int compareTo(Tag otherTag) {
        return name.compareTo(otherTag.name);
    }

    public static Tag findOrCreateByName(String name) {
        Tag tag = Tag.find("byName", name).first();
        if (tag == null) {
            tag = new Tag(name);
        }
        return tag;
    }

    public static List<Map> getCloud() {
        List<Map> result = Tag.find(
                "select new map(t.name as tag, count(p.id) as number) from Post p join p.tags as t group by t.name"
        ).fetch();
        return result;
    }
    
}
