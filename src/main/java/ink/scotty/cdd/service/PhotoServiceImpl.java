package ink.scotty.cdd.service;

import ink.scotty.cdd.mapper.PetMapper;
import ink.scotty.cdd.mapper.PhotoMapper;
import ink.scotty.cdd.pojo.Photo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PhotoServiceImpl implements PhotoService {
    @Autowired
    PhotoMapper photoMapper;
    @Autowired
    PetMapper petMapper;

    @Override
    public Photo addPhoto(Photo photo) {
        petMapper.updatePetPhotoNumber(photo.getPetId(), 1);
        photoMapper.addPhoto(photo);
        return photo;
    }

    @Override
    public int deletePhoto(int photoId, int petId) {
        int _petId = photoMapper.getPetIdByPhotoID(photoId);
        if (_petId != petId) return -1;
        petMapper.updatePetPhotoNumber(petId, -1);
        photoMapper.deletePhoto(photoId);
        return 1;
    }

    @Override
    public List<Photo> getAllPhotos(int petId) {
        return photoMapper.getAllPhotos(petId);
    }

    @Override
    public void updatePhoto(Photo photo) {
        photoMapper.updatePhoto(photo);
    }
}
