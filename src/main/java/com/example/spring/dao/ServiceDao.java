package com.example.spring.dao;

import com.example.spring.model.LikeParameters;
import com.example.spring.model.ServiceItem;

import java.util.List;

/**
 * Created by elh on 17.09.17.
 */
public interface ServiceDao {
    void add(ServiceItem service);
    void delete(Long id) throws Exception;
    List<ServiceItem> getLiked(Long userId);
    List<ServiceItem> list();
    void like(LikeParameters likeParameters);
    void unlike(LikeParameters likeParameters);
}
