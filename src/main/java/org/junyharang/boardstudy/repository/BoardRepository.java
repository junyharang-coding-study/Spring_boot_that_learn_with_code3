package org.junyharang.boardstudy.repository;

import org.junyharang.boardstudy.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board, Long> {
} // interface 끝
