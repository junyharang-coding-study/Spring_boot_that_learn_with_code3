package org.junyharang.boardstudy.repository;

import org.junyharang.boardstudy.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
} // interface 끝
