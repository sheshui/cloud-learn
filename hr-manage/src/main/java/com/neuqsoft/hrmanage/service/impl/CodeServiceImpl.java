package com.neuqsoft.hrmanage.service.impl;

import com.neuqsoft.hrmanage.entity.CodeEntity;
import com.neuqsoft.hrmanage.repo.CodeRepo;
import com.neuqsoft.hrmanage.service.CodeService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class CodeServiceImpl implements CodeService {
    @Autowired
    CodeRepo codeRepo;

    @Override
    public List<CodeEntity> findCode(String code, String value) {
        if (StringUtils.isEmpty(code)) {
            return codeRepo.findAll();
        } else if (StringUtils.isEmpty(value)) {
            return codeRepo.findByCodeType(code);
        }
        return codeRepo.findByCodeTypeAndCodeValue(code, value);
    }
}
