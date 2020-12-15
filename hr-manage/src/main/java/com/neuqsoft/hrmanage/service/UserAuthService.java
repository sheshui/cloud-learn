package com.neuqsoft.hrmanage.service;

import com.neuqsoft.hrmanage.dto.ReturnMassage;
import com.neuqsoft.hrmanage.dto.UserDetailDto;
import com.neuqsoft.hrmanage.entity.UserAuth;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * 用户服务接口
 *
 * @author sunjiarui
 * @Date 2020/8/4
 */
public interface UserAuthService {
    /**
     * 保存用户信息
     *
     * @param userAuth 用户信息实体
     * @return ReturnMassage<String> 返回信息
     */
    ReturnMassage<String> saveUserAuth(UserAuth userAuth);

    /**
     * 批量导入用户-传入用户列表
     *
     * @param userAuths 用户基本信息
     * @return 导入结果
     */
    ReturnMassage<String> saveUserAuths(List<UserAuth> userAuths);

    /**
     * 批量导入用户-传入excle文件
     *
     * @param file excle文件
     * @return 导入结果
     */
    ReturnMassage<String> saveUserAuths(MultipartFile file) throws IOException;

    /**
     * 查询所有用户
     *
     * @param pageSize 单页大小
     * @param pageNo   页码
     * @return 查询所有用户
     */
    Page<UserAuth> findAll(int pageSize, int pageNo);

    /**
     * 删除用户
     *
     * @param userIds 删除的用户id
     * @return 删除结果
     */
    ReturnMassage<String> delUser(List<String> userIds);


    /**
     * 用户详细信息修改
     *
     * @param detail 用户详细信息
     * @return 修改结果
     */
    ReturnMassage<String> saveUserDetail(UserDetailDto detail);

    Page<UserAuth> search(String param, String value, int pageNo, int pageSize);

//    Page<UserAuth>
}
