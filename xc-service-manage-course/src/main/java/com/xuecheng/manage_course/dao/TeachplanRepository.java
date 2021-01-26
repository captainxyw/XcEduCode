package com.xuecheng.manage_course.dao;

import com.xuecheng.framework.domain.course.Teachplan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TeachplanRepository extends JpaRepository<Teachplan, String> {
    //根据课程id和parentid 查询teachplan SELECT	* FROM	teachplan a 	a.courseid = 	AND a.parentid = '0'
    List<Teachplan> findByCourseidAndParentid(String courseid, String parentId);
}
