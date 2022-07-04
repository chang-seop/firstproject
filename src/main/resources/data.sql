INSERT into article(id, title, content) values (1, '가가가가', '1111');
INSERT into article(id, title, content) values (2, '나나나나', '2222');
INSERT into article(id, title, content) values (3, '다다다다', '3333');

-- article 더미 데이터
INSERT into article(id, title, content) values (4, '당신의 인생 영화는?', '댓글 ㄱ');
INSERT into article(id, title, content) values (5, '당신의 소울 푸드는?', '댓글 ㄱㄱ');
INSERT into article(id, title, content) values (6, '당신의 취미는?', '댓글 ㄱㄱㄱ');

-- comment 더미 데이터
---- 4번 게시글의 댓글들
INSERT into comment(id, article_id, nickname, body) VALUES (1, 4, 'park', '굳 윌 헌팅');
INSERT into comment(id, article_id, nickname, body) VALUES (2, 4, 'kim', '아이 엠 샘');
INSERT into comment(id, article_id, nickname, body) VALUES (3, 4, 'Choi', '쇼생크의 탈출');

---- 5번 게시글의 댓글들
INSERT into comment(id, article_id, nickname, body) VALUES (4, 5, 'park', '치킨');
INSERT into comment(id, article_id, nickname, body) VALUES (5, 5, 'kim', '샤브샤브');
INSERT into comment(id, article_id, nickname, body) VALUES (6, 5, 'Choi', '초밥');

---- 6번 게시글의 댓글들
INSERT into comment(id, article_id, nickname, body) VALUES (7, 6, 'park', '조깅');
INSERT into comment(id, article_id, nickname, body) VALUES (8, 6, 'kim', '유튜브');
INSERT into comment(id, article_id, nickname, body) VALUES (9, 6, 'Choi', '독서');