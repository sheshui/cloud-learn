package com.neuqsoft.hrmanage.repo;

import com.neuqsoft.hrmanage.entity.CodeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author sunjiarui
 */
public interface CodeRepo extends JpaRepository<CodeEntity, String> {
    /**
     * 按照
     *
     * @param codeType 类型
     * @return 代码表
     */
    List<CodeEntity> findByCodeType(String codeType);

    /**
     * 代码类型和值查询
     *
     * @param codeType  类型
     * @param codoValue 值
     * @return 代码表
     */
    List<CodeEntity> findByCodeTypeAndCodeValue(String codeType, String codoValue);
}
