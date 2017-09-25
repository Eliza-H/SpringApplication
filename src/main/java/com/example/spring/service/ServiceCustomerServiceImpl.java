package com.example.spring.service;


import com.example.spring.dao.ServiceDao;
import com.example.spring.dto.AnnouncementDTO;
import com.example.spring.model.AddServiceUserParameters;
import com.example.spring.model.LikeParameters;
import com.example.spring.model.ServiceItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by elh on 17.09.17.
 */
@Service
public class ServiceCustomerServiceImpl implements ServiceCustomerService {
    @Autowired
    private ServiceDao serviceDao;

    @Transactional
    @Override
    public void add(final AddServiceUserParameters addServiceUserParameters) {
        final ServiceItem serviceItem = new ServiceItem(addServiceUserParameters.getTitle(), addServiceUserParameters.getDescription());
        serviceDao.add(serviceItem);
    }


    @Transactional(readOnly = true)
    @Override
    public List<AnnouncementDTO> list(final Long userId) {
        final List<ServiceItem> announcementAllItemsList = serviceDao.list();
        final List<ServiceItem> announcementLikedList = serviceDao.getLiked(userId);
        final List<AnnouncementDTO> list = new ArrayList<>();

        announcementAllItemsList.forEach(announcementItem -> {
            boolean isLiked = false; // shift + f6
            for (ServiceItem likedItem : announcementLikedList) {
                if (announcementItem.getId().equals(likedItem.getId())) {
                    isLiked = true;
                    break;
                }
            }
//            Optional<ServiceItem> liked = announcementLikedList.stream()
//                    .filter(likedItem -> likedItem.getId().equals(announcementItem.getId()))
//                    .findFirst();

            final AnnouncementDTO.AnnouncementDTOBuilder builder = AnnouncementDTO.builder();
            final AnnouncementDTO announcementDTO = builder.title(announcementItem.getTitle())
                    .description(announcementItem.getDescription())
                    .id(announcementItem.getId())
                    .isLiked(isLiked).build();
//                    .isLiked(liked.isPresent()).build();

            list.add(announcementDTO);
        });
        return list;
    }

    @Transactional
    @Override
    public void delete(final Long id) throws Exception {
        serviceDao.delete(id);
    }

    @Transactional
    @Override
    public void like(final LikeParameters likeRequest) {
        serviceDao.like(likeRequest);
    }

    @Transactional
    @Override
    public void unlike(LikeParameters likeRequest) {
        serviceDao.unlike(likeRequest);
    }

}
