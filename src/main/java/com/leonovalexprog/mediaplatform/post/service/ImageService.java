package com.leonovalexprog.mediaplatform.post.service;

import com.leonovalexprog.mediaplatform.post.exception.ImageNotFoundException;
import com.leonovalexprog.mediaplatform.post.model.Image;
import com.leonovalexprog.mediaplatform.post.repository.ImageRepository;
import com.leonovalexprog.mediaplatform.post.utils.ImageUtils;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ImageService {
    private final ImageRepository repository;

    @Transactional
    public Image uploadImage(MultipartFile file) throws IOException {
        if (!this.isUploaded(file.getOriginalFilename()))
            return repository.save(Image.builder()
                    .filename(file.getOriginalFilename())
                    .contentType(file.getContentType())
                    .imageData(ImageUtils.compressImage(file.getBytes()))
                    .build());
        else
            return null;
    }

    @Transactional
    public Image findImageByFilename(String filename) {
        return repository.findFirstByFilename(filename)
                .orElseThrow(() -> new ImageNotFoundException(String.format("Image %s not found", filename)));
    }

    @Transactional
    public byte[] downloadImage(String fileName){
            Optional<Image> dbImageData = repository.findFirstByFilename(fileName);
            return ImageUtils.decompressImage(dbImageData.get().getImageData());
    }


    private boolean isUploaded(String filename) {
        return repository.existsByFilename(filename);
    }
}
