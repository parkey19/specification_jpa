package com.attacomsian.jpa.repositories.specs;

import com.attacomsian.jpa.domains.Item;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class ItemSpecification {

    public static Specification<Item> withColor(String color1, String color2) {
        return (Specification<Item>) ((root, query, builder) -> {
            Predicate p1 = builder.equal(root.get("color"), color1);
            Predicate p2 = builder.equal(root.get("color"), color2);
            return builder.or(p1, p2);
        }
        );
    }

    public static Specification<Item> withGradeAndName(String grade, String name) {
        return (Specification<Item>) ((root, query, builder) -> {
            Predicate p1 = builder.equal(root.get("grade"), grade);
            Predicate p2 = builder.equal(root.get("name"), name);
            return builder.or(p1, p2);
        }
        );
    }

    public static Specification<Item> withName(String name) {
        return (Specification<Item>) ((root, query, builder) ->
            builder.equal(root.get("name"), name)
        );
    }

}
