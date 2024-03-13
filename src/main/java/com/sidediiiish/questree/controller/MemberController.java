package com.sidediiiish.questree.controller;

import com.sidediiiish.questree.domain.Member;
import com.sidediiiish.questree.dto.MemberForm;
import com.sidediiiish.questree.service.MemberService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.crypto.SecretKey;
import java.util.Base64;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@AllArgsConstructor
@RestController
@Controller
public class MemberController {
    private final SecretKey secretKey;
    private final MemberService memberService;

//    @GetMapping("/members/new")
//    public String createForm() {
//        return "members/createMemberForm";
//    }

    @RequestMapping(value="/members/new", method= RequestMethod.POST)
    public String create(@RequestBody MemberForm form) {
        Member member = new Member();
        member.setName(form.getName());
        member.setEmail(form.getEmail());
        member.setPassword(form.getPassword());

        memberService.join(member);
        return "Success";
    }

//    @GetMapping("/members")
//    public String list(Model model) {
//        List<Member> members = memberService.findMembers();
//        model.addAttribute("members", members);
//        return "members/memberList";
//    }
}
