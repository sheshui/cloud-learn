package com.neuqsoft.hrmanage.dto;

import lombok.Data;

/**
 * @author sunjiarui
 */
@Data
public class ChangePwdDto {
    private String userId;
    private String oldPwd;
    private String newPwd;
}
