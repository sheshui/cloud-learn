package com.neuqsoft.hrmanage.service;

import com.neuqsoft.hrmanage.entity.CodeEntity;

import java.util.List;

/**
 * @author sunjiarui
 */
public interface CodeService {
    /**
     * 查找代码表
     *
     * @param code  代码类型
     * @param value 代码值
     * @return 代码表
     */
    List<CodeEntity> findCode(String code, String value);
}
