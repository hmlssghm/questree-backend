package com.sidediiiish.questree.controller;

import com.sidediiiish.questree.domain.Member;
import com.sidediiiish.questree.dto.MemberForm;
import com.sidediiiish.questree.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Controller
public class MemberController {
    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

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
