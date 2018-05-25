package com.tctf.service;

import com.tctf.entity.Post;
import com.tctf.mapper.PostMapper;
import com.tctf.utils.RedisClient;
import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("PostService")
public class PostService {
    @Autowired
    private PostMapper postMapper;

    @Autowired
    private RedisClient redisClient;

    public Map AddPost(Post post){
        Map<String,String> map = new HashMap<>();
        String title = StringEscapeUtils.escapeHtml(post.getTitle());
        String content = StringEscapeUtils.escapeHtml(post.getContent());
        if(StringUtils.isBlank(title) || StringUtils.isBlank(content)){
            map.put("status","title or content is blank");
        }else{
            //redisClient.set(post.getPid(), post);
            post.setTitle(title);
            post.setContent(content);
            postMapper.InsertPost(post);
            map.put("status","Add post success");
        }
        return map;
    }


    public List<Post> GetPost(Integer uid){
        List<Post> list = postMapper.GetPostByUid(uid);
        return list;
    }




}
