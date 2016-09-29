/* 블로그 통계 buffer */
CREATE TABLE `ch_stats_buffer` (
 `type_code` varchar(10) NOT NULL COMMENT '조회유형(today: 일일방문, view: 게시글조회)',
 `ip` varchar(300) NOT NULL COMMENT '접속IP',
 `board_seq` int(11) NULL COMMENT '게시글 시퀀스',
 `reg_date` DATETIME NOT NULL COMMENT '등록일',
 FOREIGN KEY(`board_seq`) REFERENCES ch_board(seq) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT '블로그 통계 buffer';

/* 블로그 통계 관리 */
CREATE TABLE `ch_stats` (
 `today_cnt` int(11) NOT NULL DEFAULT 0 COMMENT '일일방문자 수',
 `reg_date` DATETIME NOT NULL COMMENT '등록일',
 PRIMARY KEY (`reg_date`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT '블로그 통계 관리';