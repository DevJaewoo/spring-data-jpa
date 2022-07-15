package study.springdatajpa.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import study.springdatajpa.entity.Member;

@Data
@AllArgsConstructor
public class MemberDTO {

    private Long id;
    private String username;
    private String teamName;

    public MemberDTO(Member member) {
        this.id = member.getId();
        this.username = member.getUsername();
    }
}
