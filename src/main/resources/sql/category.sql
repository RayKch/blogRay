/* 블로그 카테고리 관리 */
CREATE TABLE `ch_category` (
 `seq` int(11) NOT NULL AUTO_INCREMENT COMMENT '번호',
 `member_seq` int(11) NOT NULL COMMENT '회원 번호',
 `title` varchar(300) NOT NULL COMMENT '카테고리명',
 `description` text NOT NULL COMMENT '카테고리 설명',
 `reg_date` DATETIME NULL COMMENT '등록일',
 `mod_date` DATETIME NULL COMMENT '수정일',
 PRIMARY KEY (`seq`),
 FOREIGN KEY(`member_seq`) REFERENCES ch_member(seq) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT '블로그 카테고리';