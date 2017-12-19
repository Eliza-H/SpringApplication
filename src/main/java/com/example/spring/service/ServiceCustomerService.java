package com.example.spring.service;


import com.example.spring.dto.AnnouncementDTO;
import com.example.spring.model.AddServiceUserParameters;
import com.example.spring.model.LikeParameters;
import com.example.spring.model.ServiceItem;

import java.util.List;

/**
 * Created by elh on 17.09.17.
 */
public interface ServiceCustomerService {
    void add(final AddServiceUserParameters addServiceUserParameters);
    List<AnnouncementDTO> list( Long userId);
    List<AnnouncementDTO> getLiked(Long userId);
    void delete(Long id) throws Exception;
  void like(final LikeParameters likeRequest);
  void unlike(final LikeParameters likeRequest);
}
