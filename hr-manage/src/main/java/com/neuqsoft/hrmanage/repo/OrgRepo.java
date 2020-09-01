package com.neuqsoft.hrmanage.repo;

import com.neuqsoft.hrmanage.entity.Org;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author sunjiarui
 * @Date 2020/8/31
 */
@Repository
public interface OrgRepo extends JpaRepository<Org, String> {
}
