/* 가입 회원(댓글은 모두 가능) */
CREATE TABLE `ch_user` (
   `seq` int(11) NOT NULL AUTO_INCREMENT COMMENT '번호',
   `id` varchar(300) NOT NULL COMMENT '아이디(이메일)',
   `password` varchar(300) NOT NULL COMMENT '비밀번호',
   `nickname` varchar(300) NOT NULL COMMENT '닉네임',
   `reg_date` int(11) NULL COMMENT '등록일',
   `mod_date` int(11) NULL COMMENT '수정일',
   PRIMARY KEY (`seq`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT '가입 회원';