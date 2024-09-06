package com.totty.shoppingcart.services;

import com.totty.shoppingcart.dtos.ImageDto;
import com.totty.shoppingcart.models.Image;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ImageService {
    Image getImageById(Long id);

    void deleteImageById(Long id);

    List<ImageDto> saveImage(List<MultipartFile> file, Long productId);

    void updateImage(MultipartFile file, Long imageId);

}
