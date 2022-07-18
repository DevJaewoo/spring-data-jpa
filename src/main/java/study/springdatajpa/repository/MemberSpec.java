package study.springdatajpa.repository;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;
import study.springdatajpa.entity.Member;
import study.springdatajpa.entity.Team;

import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;

public class MemberSpec {

    public static Specification<Member> teamName(final String teamName) {
        return (root, query, builder) -> {

            if(!StringUtils.hasText(teamName)) {
                return null;
            }

            Join<Member, Team> team = root.join("team", JoinType.INNER);
            return builder.equal(team.get("name"), teamName);
        };
    }

    public static Specification<Member> username(final String username) {
        return (root, query, builder) -> builder.equal(root.get("username"), username);
    }
}
