/* 블로그 게시판(일반게시판은 가입자만 사용) */
CREATE TABLE `ch_board` (
 `seq` int(11) NOT NULL AUTO_INCREMENT COMMENT '번호',
 `category_seq` int(11) NOT NULL COMMENT '블로그 카테고리 번호',
 `member_seq` int(11) NOT NULL COMMENT '회원 번호',
 `title` varchar(300) NOT NULL COMMENT '제목',
 `content` text NOT NULL COMMENT '내용',
 `recommend` int(11) NULL DEFAULT 0 COMMENT '추천',
 `un_recommend` int(11) NULL DEFAULT 0 COMMENT '비추천',
 `view_cnt` int(11) NULL DEFAULT 0 COMMENT '조회수',
 `reg_date` DATETIME NULL COMMENT '등록일',
 `mod_date` DATETIME NULL COMMENT '수정일',
 PRIMARY KEY (`seq`),
 FOREIGN KEY(`category_seq`) REFERENCES ch_category(seq) ON DELETE CASCADE,
 FOREIGN KEY(`member_seq`) REFERENCES ch_member(seq) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT '블로그 게시판';


/* 블로그 게시판 댓글(댓글은 모두 가능) */
CREATE TABLE `ch_board_comment` (
 `seq` int(11) NOT NULL AUTO_INCREMENT COMMENT '번호',
 `category_seq` int(11) NULL COMMENT '카테고리 번호',
 `parent_seq` int(11) NOT NULL COMMENT '상위 댓글 시퀀스',
 `board_seq` int(11) NULL COMMENT '블로그 게시판 번호',
 `member_seq` int(11) NULL COMMENT '회원 번호',
 `nickname` varchar(300) NULL COMMENT '닉네임',
 `password` varchar(300) NULL COMMENT '비밀번호',
 `content` text NOT NULL COMMENT '내용',
 `recommend` int(11) NULL DEFAULT 0 COMMENT '추천',
 `un_recommend` int(11) NULL DEFAULT 0 COMMENT '비추천',
 `del_yn` varchar(1) NULL COMMENT '삭제 여부',
 `del_date` DATETIME NULL COMMENT '삭제일',
 `reg_date` DATETIME NULL COMMENT '등록일',
 `mod_date` DATETIME NULL COMMENT '수정일',
 PRIMARY KEY (`seq`),
 FOREIGN KEY(`board_seq`) REFERENCES ch_board(seq) ON DELETE CASCADE,
 FOREIGN KEY(`member_seq`) REFERENCES ch_member(seq) ON DELETE CASCADE,
 FOREIGN KEY(`category_seq`) REFERENCES ch_category(seq) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT '블로그 게시판 댓글';