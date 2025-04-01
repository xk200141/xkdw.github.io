package com.dwh.dpicturebackend.api.imagesearch.sub;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.URLUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpStatus;
import cn.hutool.json.JSONUtil;
import com.dwh.dpicturebackend.exception.BusinessException;
import com.dwh.dpicturebackend.exception.ErrorCode;
import lombok.extern.slf4j.Slf4j;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class GetImagePageUrlApi {

    /**
     * 获取图片页面地址
     *
     * @param imageUrl
     * @return
     */
    public static String getImagePageUrl(String imageUrl) {
        // 1. 准备请求参数
        Map<String, Object> formData = new HashMap<>();
        formData.put("image", imageUrl);
        formData.put("tn", "pc");
        formData.put("from", "pc");
        formData.put("image_source", "PC_UPLOAD_URL");
        // 获取当前时间戳
        long uptime = System.currentTimeMillis();
        // 请求地址
        String url = "https://graph.baidu.com/upload?uptime=" + uptime;
//        Map<String, String> headers = new HashMap<>();
//        String acsToken="1743049620506_1743078396162_uWQF3PmFCdsnRHUuXTYGCwQzhF8tQ1vt8bz/lznU5bU1cP5gC8Pp8jsnFNwvuepX4drPAhH5LLmRc9lLPNkmjajy8RyzUG5xdhpNJ8ZaVvvAR9jP2dkWl/zcATr0nAewNOKLXdaXB8zZZim1ZrRhV1ZiBFPi0OKZqu3bodPKVzP9Dnz2W8JFSgQkqQRCfrt8zwSmh7d1LQh+4oVRdR1A6m/sONNqsBrLsz8+Mt/Sai7DWoT33gkPGu0tK+qV4OXc5kMNO4hf6laHlhfyqYtiEpNZDCR6P8x5JJWUu9W9jUSLbrMEkKF33TJ4Uyga2mNCdznBM8tjYO2ycpc88Vm8GI+Nksrizi94RYiq9jM2q8dqL2rkVu3U4WstXQieL7bwvYeY56cOoozPGlLq+Y3rCc3Cp15xceFF8rXkCmZBU0cwumBq/wGlleuB82L4n+fMReoCgDZZwFJe1yDWuanxuYdcktAdfAgl06yKevCaDEI=";
//        headers.put("Acs-Token",acsToken);

        try {
            // 2. 发送 POST 请求到百度接口
            HttpResponse response = HttpRequest.post(url).header("acs-token", RandomUtil.randomString(1))
                    .form(formData)
                    .timeout(5000)
                    .execute();
            // 判断响应状态
            if (HttpStatus.HTTP_OK != response.getStatus()) {
                throw new BusinessException(ErrorCode.OPERATION_ERROR, "接口调用失败");
            }
            // 解析响应
            String responseBody = response.body();
            Map<String, Object> result = JSONUtil.toBean(responseBody, Map.class);

            // 3. 处理响应结果
            if (result == null || !Integer.valueOf(0).equals(result.get("status"))) {
                throw new BusinessException(ErrorCode.OPERATION_ERROR, "接口调用失败");
            }
            Map<String, Object> data = (Map<String, Object>) result.get("data");
            String rawUrl = (String) data.get("url");
            // 对 URL 进行解码
            String searchResultUrl = URLUtil.decode(rawUrl, StandardCharsets.UTF_8);
            // 如果 URL 为空
            if (searchResultUrl == null) {
                throw new BusinessException(ErrorCode.OPERATION_ERROR, "未返回有效结果");
            }
            return searchResultUrl;
        } catch (Exception e) {
            log.error("搜索失败", e);
            throw new BusinessException(ErrorCode.OPERATION_ERROR, "搜索失败");
        }
    }

    public static void main(String[] args) {
        // 测试以图搜图功能
        String imageUrl = "https://www.codefather.cn/logo.png";
        String result = getImagePageUrl(imageUrl);
        System.out.println("搜索成功，结果 URL：" + result);
    }
}
