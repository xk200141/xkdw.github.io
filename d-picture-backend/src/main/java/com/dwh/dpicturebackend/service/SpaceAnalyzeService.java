package com.dwh.dpicturebackend.service;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dwh.dpicturebackend.model.dto.space.SpaceAddRequest;
import com.dwh.dpicturebackend.model.dto.space.SpaceQueryRequest;
import com.dwh.dpicturebackend.model.dto.space.analyze.*;
import com.dwh.dpicturebackend.model.space.Space;
import com.dwh.dpicturebackend.model.user.User;
import com.dwh.dpicturebackend.model.vo.SpaceVO;
import com.dwh.dpicturebackend.model.vo.space.analyze.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
* @author admin
* @description 针对表【space(空间)】的数据库操作Service
* @createDate 2025-03-08 20:28:15
*/
public interface SpaceAnalyzeService extends IService<Space> {

    SpaceUsageAnalyzeResponse getSpaceUsageAnalyze(SpaceUsageAnalyzeRequest spaceUsageAnalyzeRequest, User loginUser);
    List<SpaceCategoryAnalyzeResponse> getSpaceCategoryAnalyze(SpaceCategoryAnalyzeRequest spaceCategoryAnalyzeRequest, User loginUser);

    List<SpaceTagAnalyzeResponse>getSpaceTagAnalyze(SpaceTagAnalyzeRequest spaceTagAnalyzeRequest,User loginUser);
    /*
    * 分段统计图片大小
    * */
    List<SpaceSizeAnalyzeResponse> getSpaceSizeAnalyze(SpaceSizeAnalyzeRequest spaceSizeAnalyzeRequest, User loginUser);
    //空间用户分析基于图片的创建时间维度统计用户的上传行为，
    List<SpaceUserAnalyzeResponse> getSpaceUserAnalyze(SpaceUserAnalyzeRequest spaceUserAnalyzeRequest, User loginUser);
//按存储使用量排序查询前 N 个空间
    List<Space> getSpaceRankAnalyze(SpaceRankAnalyzeRequest spaceRankAnalyzeRequest, User loginUser);
}
