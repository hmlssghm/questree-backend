package com.sidediiiish.questree.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "members")
public class Member extends BaseEntity{

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) //pk를 DB가 자동 생성해주는 -> identity
    private Long id;
    private String name;
    private String email;
    private String password;

    //    @Builder
//    public Member(String name) {
//        this.name = name;
//    }

//    public void refactorName(String name) {
//        this.name = name;
//    }

}
