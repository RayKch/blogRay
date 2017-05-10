/* 블로그 카테고리 관리 */
CREATE TABLE `ch_category` (
 `seq` int(11) NOT NULL AUTO_INCREMENT COMMENT '번호',
 `type_code` varchar(1) NOT NULL COMMENT '카테고리 유형(L: 게시글형, C: 댓글형)',
 `member_seq` int(11) NOT NULL COMMENT '회원 번호',
 `title` varchar(300) NOT NULL COMMENT '카테고리명',
 `description` varchar(300) NOT NULL COMMENT '카테고리 설명',
 `order_no` int(11) NOT NULL DEFAULT 0 COMMENT '정렬 순서',
 `reg_date` DATETIME NULL COMMENT '등록일',
 `mod_date` DATETIME NULL COMMENT '수정일',
 PRIMARY KEY (`seq`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT '블로그 카테고리';

ALTER TABLE ch_category ADD type_code varchar(1) NOT NULL COMMENT '카테고리 유형(L: 게시글형, C: 댓글형)';
ALTER TABLE ch_board_comment ADD category_seq int(11) NULL COMMENT '카테고리 번호';
ALTER TABLE ch_board_comment ADD FOREIGN KEY (category_seq) REFERENCES ch_category(seq) on delete cascade;