package ink.scotty.cdd.service;

import ink.scotty.cdd.pojo.Photo;

import java.util.List;

public interface PhotoService {

    // 添加相片
    Photo addPhoto(Photo photo);

    // 删除相片
    int deletePhoto(int photoId, int petId);

    // 获取所有相片
    List<Photo> getAllPhotos(int petId);

    // 更新相片
    void updatePhoto(Photo photo);
}
