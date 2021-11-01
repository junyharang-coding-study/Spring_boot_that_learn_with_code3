package org.junyharang.boardstudy.repository;

import org.junit.jupiter.api.Test;
import org.junyharang.boardstudy.entity.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest class MemberRepositoryTest {

    @Autowired private MemberRepository memberRepository;

    @Test public void 회원추가() {

        IntStream.rangeClosed(1, 100).forEach(i -> {

            Member member = Member.builder()
                    .email("junyharang" + i + "@junyharang.com")
                    .password("1234")
                    .name("주니하랑" + i)
                    .build();

            memberRepository.save(member);
        });

    } // 회원추가() 끝

} // class 끝