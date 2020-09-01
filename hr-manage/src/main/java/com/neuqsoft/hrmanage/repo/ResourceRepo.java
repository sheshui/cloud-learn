package com.neuqsoft.hrmanage.repo;

import com.neuqsoft.hrmanage.entity.Resource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author sunjiarui
 * @Date 2020/8/31
 */
@Repository
public interface ResourceRepo extends JpaRepository<Resource, String> {
}
