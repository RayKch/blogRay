/* 가입 회원(댓글은 모두 가능) */
CREATE TABLE `ch_member` (
   `seq` int(11) NOT NULL AUTO_INCREMENT COMMENT '번호',
   `id` varchar(300) NOT NULL COMMENT '아이디(이메일)',
   `password` varchar(65) NOT NULL COMMENT '비밀번호',
   `nickname` varchar(300) NOT NULL COMMENT '닉네임',
   `login_token` varchar(50) NULL COMMENT '로그인 토큰',
   `last_ip` varchar(15)  NULL COMMENT '마지막 접속 아이피',
   `last_date` DATE  NULL COMMENT '마지막 접속일',
   `reg_date` DATETIME NULL COMMENT '등록일',
   `mod_date` DATETIME NULL COMMENT '수정일',
   PRIMARY KEY (`seq`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT '가입 회원';