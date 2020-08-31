package com.neuqsoft.hrmanage.repo;

import com.neuqsoft.hrmanage.entity.Org;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author sunjiarui
 * @Date 2020/8/31
 */
public interface OrgRepo extends JpaRepository<Org, String> {
}
