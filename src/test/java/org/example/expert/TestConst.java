package org.example.expert;

import org.example.expert.domain.auth.dto.request.SigninRequest;
import org.example.expert.domain.comment.entity.Comment;
import org.example.expert.domain.todo.entity.Todo;
import org.example.expert.domain.user.dto.request.UserRoleChangeRequest;
import org.example.expert.domain.user.entity.User;
import org.example.expert.domain.user.enums.UserRole;

public abstract class TestConst {
    public static User USER = new User("na2D@naver.com", "1234", UserRole.USER);
    public static Todo TODO = new Todo("제목", "내용", "맑음", USER);
    public static Comment COMMENT = new Comment("내용", USER, TODO);
    public static UserRoleChangeRequest USER_ROLE_CHANGE = new UserRoleChangeRequest("ADMIN");
    public static SigninRequest SIGN_IN_REQUEST = new SigninRequest("na2D@naver.com", "1234");
}
