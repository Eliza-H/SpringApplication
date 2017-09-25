package com.example.spring.contoller;

import com.example.spring.contoller.request.AddServiceRequest;
import com.example.spring.contoller.request.LikeRequest;

import com.example.spring.dto.AnnouncementDTO;
import com.example.spring.service.ServiceCustomerService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by elh on 17.09.17.
 */
@Controller
@RequestMapping("/service/")
public class ServiceController {
    @Autowired
    ServiceCustomerService serviceService;

    @RequestMapping("list/{id}")
    @SneakyThrows
    @ResponseBody
    public List<AnnouncementDTO> listItems(@Valid @PathVariable("id") Long userId) {
        List<AnnouncementDTO> serviceItems = serviceService.list(userId);
        return serviceItems;
    }
    @RequestMapping(value = "add", method = RequestMethod.POST)
    @SneakyThrows
    @ResponseBody
    public boolean add(@RequestBody @Valid AddServiceRequest addServiceRequest) {
        serviceService.add(addServiceRequest);
        return true;
    }

    @RequestMapping(value = "delete/{id}", method = RequestMethod.GET)
    @SneakyThrows
    @ResponseBody
    public boolean delete(@Valid @PathVariable("id") Long id) {
        serviceService.delete(id);
        return true;
    }

    @RequestMapping(value = "like", method = RequestMethod.POST)
    @SneakyThrows
    @ResponseBody
    public boolean like(@RequestBody @Valid LikeRequest likeRequest) {
        serviceService.like(likeRequest);
        return true;
    }

    @RequestMapping(value = "unlike", method = RequestMethod.POST)
    @SneakyThrows
    @ResponseBody
    public boolean unlike(@RequestBody @Valid LikeRequest likeRequest) {
        serviceService.unlike(likeRequest);
        return true;
    }

}
