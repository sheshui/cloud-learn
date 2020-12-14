package com.neuqsoft.hrmanage.repo;

import com.neuqsoft.hrmanage.entity.CodeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CodeRepo extends JpaRepository<CodeEntity, String> {
    /**
     * 按照
     *
     * @param codeType
     * @return
     */
    List<CodeEntity> findByCodeType(String codeType);

    List<CodeEntity> findByCodeTypeAndCodeValue(String codeType, String codoValue);
}
