INSERT INTO oauth_client_details (client_id, client_secret, scope, authorized_grant_types, authorities, access_token_validity)
  VALUES ('client', '$2a$10$YrOPPdX2eriNY9UwUGWRqeSc4QQAE1.pC81RQumBOyUkoIL/cR19m', 'read,write', 'password,refresh_token,client_credentials', 'ROLE_CLIENT', 300);


insert into hibernate_sequence(next_val) values(4);

insert into roles values(1,'ROLE_SYSTEM_ADMIN',null,CURDATE(),null,null);
insert into roles values(2,'ROLE_COMPANY_ADMIN',null,CURDATE(),null,null);
insert into roles values(3,'ROLE_COMPANY_USER',null,CURDATE(),null,null);

-- encrypted password is 123456
INSERT INTO `users` VALUES (4,'chathuranga','$2a$10$5x3v0KGwv7oUPzlRydwcPuqdGrWCkiMVczzdHw1n6zAlzc6S3rraa',1,'SYSTEM_ADMIN',NULL,NULL,NULL,NULL,'ACTIVE');

INSERT INTO `user_roles` VALUES (4,1),(4,2),(4,3);
