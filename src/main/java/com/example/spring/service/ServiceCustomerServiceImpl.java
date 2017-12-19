package com.example.spring.service;


import com.example.spring.contoller.FileUploadController;
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
import java.util.stream.Collectors;

/**
 * Created by elh on 17.09.17.
 */
@Service
public class ServiceCustomerServiceImpl implements ServiceCustomerService {
    @Autowired
    private ServiceDao serviceDao;

    @Autowired
    public FileUploadController fileUploadController;

    @Transactional
    @Override
    public void add(final AddServiceUserParameters addServiceUserParameters) {
        final ServiceItem serviceItem = new ServiceItem(addServiceUserParameters.getTitle(), addServiceUserParameters.getDescription(), addServiceUserParameters.getFilename());
        serviceDao.add(serviceItem);
    }

    @Transactional(readOnly = true)
    @Override
    public List<AnnouncementDTO> getLiked(Long userId) {
        final List<ServiceItem> announcementLikedList = serviceDao.getLiked(userId);
        return announcementLikedList.stream().map(announcementItem -> {
            final AnnouncementDTO.AnnouncementDTOBuilder builder = AnnouncementDTO.builder();
            builder.filename(announcementItem.getFilename());
            builder.title(announcementItem.getTitle());
            builder.description(announcementItem.getDescription());
            builder.id(announcementItem.getId());
            builder.isLiked(true);
            return builder.build();
        }).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    @Override
    public List<AnnouncementDTO> list(final Long userId) {
        final List<ServiceItem> announcementAllItemsList = serviceDao.list();
        final List<AnnouncementDTO> list = new ArrayList<>();
        announcementAllItemsList.forEach(announcementItem -> {
            boolean isLiked = false; // shift + f6
            if(announcementItem.getLikes().isEmpty() != true){
                isLiked = true;
            }
            final AnnouncementDTO.AnnouncementDTOBuilder builder = AnnouncementDTO.builder();

            final AnnouncementDTO announcementDTO = builder.title(announcementItem.getTitle())
                    .description(announcementItem.getDescription())
                    .filename(announcementItem.getFilename())
                    .id(announcementItem.getId())
                    .isLiked(isLiked).build();

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
