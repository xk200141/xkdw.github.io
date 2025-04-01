package com.dwh.dpicturebackend.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dwh.dpicturebackend.api.aliyunai.model.CreateOutPaintingTaskResponse;
import com.dwh.dpicturebackend.model.dto.picture.*;
import com.dwh.dpicturebackend.model.picture.Picture;
import com.dwh.dpicturebackend.model.user.User;
import com.dwh.dpicturebackend.model.vo.PictureVO;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
* @author admin
* @description 针对表【picture(图片)】的数据库操作Service
* @createDate 2025-02-27 09:44:48
*/
public interface PictureService extends IService<Picture> {
    /**
     * 上传图片
     *
     * @param  inputSource
     * @param pictureUploadRequest
     * @param loginUser
     * @return
     */
    PictureVO uploadPicture(Object inputSource,
                            PictureUploadRequest pictureUploadRequest,
                            User loginUser);

     QueryWrapper<Picture> getQueryWrapper(PictureQueryRequest pictureQueryRequest);
     PictureVO getPictureVO(Picture picture, HttpServletRequest request);

     Page<PictureVO> getPictureVOPage(Page<Picture> picturePage, HttpServletRequest request);
     void validPicture(Picture picture);


    /**
     * 图片审核
     *
     *
     * @param pictureReviewRequest
     * @param loginUser
     */
    void doPictureReview(PictureReviewRequest pictureReviewRequest, User loginUser);

     void fillReviewParams(Picture picture, User loginUser);

    /**
     * 批量抓取和创建图片
     *
     * @param pictureUploadByBatchRequest
     * @param loginUser
     * @return 成功创建的图片数
     */
    Integer uploadPictureByBatch(
            PictureUploadByBatchRequest pictureUploadByBatchRequest,
            User loginUser
    );

     void clearPictureFile(Picture oldPicture);

    void checkPictureAuth(User loginUser, Picture picture);

    void deletePicture(long pictureId, User loginUser);

    void editPicture(PictureEditRequest pictureEditRequest, User loginUser);

    List<PictureVO> searchPictureByColor(Long spaceId, String picColor, User loginUser);

    void editPictureByBatch(PictureEditByBatchRequest pictureEditByBatchRequest, User loginUser);
    CreateOutPaintingTaskResponse createPictureOutPaintingTask(CreatePictureOutPaintingTaskRequest createPictureOutPaintingTaskRequest, User loginUser);
}
