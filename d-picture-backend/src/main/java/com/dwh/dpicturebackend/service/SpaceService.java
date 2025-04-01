package com.dwh.dpicturebackend.service;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dwh.dpicturebackend.model.dto.space.SpaceAddRequest;
import com.dwh.dpicturebackend.model.dto.space.SpaceQueryRequest;
import com.dwh.dpicturebackend.model.space.Space;
import com.dwh.dpicturebackend.model.user.User;
import com.dwh.dpicturebackend.model.vo.SpaceVO;

import javax.servlet.http.HttpServletRequest;

/**
* @author admin
* @description 针对表【space(空间)】的数据库操作Service
* @createDate 2025-03-08 20:28:15
*/
public interface SpaceService extends IService<Space> {


     void validSpace(Space space, boolean add);

    void fillSpaceBySpaceLevel(Space space);

    QueryWrapper<Space> getQueryWrapper(SpaceQueryRequest spaceQueryRequest);
    SpaceVO getSpaceVO(Space space, HttpServletRequest request);

    Page<SpaceVO> getSpaceVOPage(Page<Space> spacePage, HttpServletRequest request);

    public long addSpace(SpaceAddRequest spaceAddRequest, User loginUser);


    void checkSpaceAuth(User loginUser, Space space);
}
