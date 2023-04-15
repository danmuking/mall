DROP TABLE IF EXISTS user_role;
CREATE TABLE `user_role` (
                        `id` BIGINT(20) NOT NULL AUTO_INCREMENT PRIMARY KEY COMMENT '主键',
                        `user_id` BIGINT(20) NOT NULL COMMENT '用户主键',
                        foreign key (user_id) references user(id) ,
                         `role_id`BIGINT(20) NOT NULL COMMENT '角色主键',
                        foreign key (role_id) references role(id)
);
DROP TABLE IF EXISTS role_action;
CREATE TABLE `role_action` (
                             `id` BIGINT(20) NOT NULL AUTO_INCREMENT PRIMARY KEY COMMENT '主键',
                             `role_id` BIGINT(20) NOT NULL COMMENT '角色主键',
                             foreign key (role_id) references role(id) ,
                             `action_id`BIGINT(20) NOT NULL COMMENT '权限主键',
                             foreign key (action_id) references action(id)
);