/* 가입 회원(댓글은 모두 가능) */
CREATE TABLE `ch_file` (
   `seq` int(11) NOT NULL AUTO_INCREMENT COMMENT '번호',
   `path` varchar(300) NOT NULL COMMENT '경로',
   `board_seq` int(11) NULL COMMENT '게시글 시퀀스',
   `reg_date` DATETIME NULL COMMENT '등록일',
   PRIMARY KEY (`seq`),
   FOREIGN KEY(`board_seq`) REFERENCES ch_board(seq) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT '파일 업로드'