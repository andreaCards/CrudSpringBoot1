package com.pragma.poc.repository;

import com.pragma.poc.document.Image;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface ImageRepository extends MongoRepository<Image, String> {

    public Image findImageByClientId(String id);

    public void deleteByClientId(String id);
    
    public Boolean existsByClientId(String id);

}
