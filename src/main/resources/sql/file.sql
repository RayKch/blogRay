/* 파일 정보 */
CREATE TABLE `ch_file` (
   `seq` int(11) NOT NULL AUTO_INCREMENT COMMENT '번호',
   `content_type` varchar(300) NOT NULL COMMENT '이미지 타입(jpg, jpeg, png, etc)',
   `file_name` varchar(300) NOT NULL COMMENT '실제 파일이름',
   `temp_file_name` varchar(300) NOT NULL COMMENT '임시 파일이름',
   `board_seq` int(11) NULL COMMENT '게시글 시퀀스',
   `reg_date` DATETIME NULL COMMENT '등록일',
   PRIMARY KEY (`seq`),
   FOREIGN KEY(`board_seq`) REFERENCES ch_board(seq) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT '파일 정보'