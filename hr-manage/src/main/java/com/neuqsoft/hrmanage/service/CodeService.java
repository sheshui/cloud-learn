package com.neuqsoft.hrmanage.service;

import com.neuqsoft.hrmanage.entity.CodeEntity;

import java.util.List;

public interface CodeService {
    List<CodeEntity> findCode(String code, String value);
}
