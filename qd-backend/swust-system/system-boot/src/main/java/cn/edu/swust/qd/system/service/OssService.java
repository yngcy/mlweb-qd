package cn.edu.swust.qd.system.service;

import cn.edu.swust.qd.system.model.vo.FileInfoVO;
import org.springframework.web.multipart.MultipartFile;

/**
 * 对象存储服务接口
 *
 * @author <a href="https://github.com/yngcy">YounGCY</a>
 */
public interface OssService {

    /**
     * 上传文件
     *
     * @param file 表单文件对象
     * @return 文件信息
     */
    FileInfoVO uploadFile(MultipartFile file);

    /**
     * 删除文件
     *
     * @param filePath 文件完成URL
     * @return 删除结果
     */
    boolean deleteFile(String filePath);
}
