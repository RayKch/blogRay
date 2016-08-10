/* 블로그 카테고리 관리 */
CREATE TABLE `ch_category` (
 `seq` int(11) NOT NULL AUTO_INCREMENT COMMENT '번호',
 `title` varchar(300) NOT NULL COMMENT '카테고리명',
 `description` text NOT NULL COMMENT '카테고리 설명',
 `reg_date` int(11) NULL COMMENT '등록일',
 `mod_date` int(11) NULL COMMENT '수정일',
 PRIMARY KEY (`seq`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT '블로그 카테고리';