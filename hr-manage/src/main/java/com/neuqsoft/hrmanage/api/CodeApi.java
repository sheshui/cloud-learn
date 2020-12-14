package com.neuqsoft.hrmanage.api;

import com.neuqsoft.hrmanage.entity.CodeEntity;
import com.neuqsoft.hrmanage.service.CodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author sunjiarui
 */
@RestController
@RequestMapping("/code/anonymous")
public class CodeApi {
    @Autowired
    CodeService codeService;

    @GetMapping("/find")
    public List<CodeEntity> findCode(@RequestParam(required = false) String code, @RequestParam(required = false) String value) {
        return codeService.findCode(code, value);
    }
}
