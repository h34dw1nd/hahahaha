package com.tctf.mapper;

import com.tctf.entity.Post;
import org.apache.ibatis.annotations.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface PostMapper {

    @Transactional
    void InsertPost(Post post);

    Post GetOne(Integer pid);

    List<Post> GetPostByUid(@Param("uid") Integer uid);

    @Transactional
    void DelPost(@Param("pid") Integer pid, @Param("uid") Integer uid);

    void AuditPost(@Param("pid") Integer pid);

    List<Post> GetAllPost();
}
