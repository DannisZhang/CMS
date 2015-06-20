package org.dannis.cms.service;

/**
 * 汽车图片服务类
 *
 * @author deng.zhang
 * @version 1.0.0
 * @date 2015-06-20 22:54
 */
public interface CarImageService {
    /**
     * 保存图片
     *
     * @param fileName   文件名称，如car.jpg
     * @param imageBytes 图片数据
     * @return 若保存成功，则返回图片URL；否则返回null
     */
    String saveImage(String fileName, byte[] imageBytes);
}
