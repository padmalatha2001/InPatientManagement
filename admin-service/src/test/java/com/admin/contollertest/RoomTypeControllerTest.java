package com.admin.contollertest;



import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.admin.bean.RoomTypeBean;
import com.admin.controller.RoomTypeController;
import com.admin.entity.RoomType;
import com.admin.service.RoomTypeService;

@ExtendWith(MockitoExtension.class)
public class RoomTypeControllerTest {

    @Mock
    private RoomTypeService roomTypeService;

    @InjectMocks
    private RoomTypeController roomTypeController;

    private RoomTypeBean roomTypeBean;

    @BeforeEach
    public void setUp() {
        roomTypeBean = new RoomTypeBean();
        roomTypeBean.setId(1L);
        roomTypeBean.setName("ac");
        // Initialize other properties if needed
    }

//    public void testSave() {
//        doNothing().when(roomTypeService).save(any(RoomTypeBean.class));
//
//        ResponseEntity<RoomTypeBean> response = roomTypeController.save(roomTypeBean);
//
//        assertEquals(HttpStatus.CREATED, response.getStatusCode());
//        assertEquals(roomTypeBean, response.getBody());
//        
//    }
    @Test
    public void testSave() {
        // Stubbing the save method of roomTypeService to return the input argument
        when(roomTypeService.save(any(RoomTypeBean.class))).thenAnswer(invocation -> invocation.getArgument(0));

        ResponseEntity<RoomTypeBean> response = roomTypeController.save(roomTypeBean);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(roomTypeBean, response.getBody());
    }


    @Test
    public void testGetAll() {
        List<RoomTypeBean> roomTypeList = new ArrayList<>();
        roomTypeList.add(roomTypeBean);
        when(roomTypeService.getAll()).thenReturn(roomTypeList);

        ResponseEntity<List<RoomTypeBean>> response = roomTypeController.getAll();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(roomTypeList, response.getBody());
    }

    @Test
    public void testGetById() {
        when(roomTypeService.getById(1L)).thenReturn(roomTypeBean);

        ResponseEntity<RoomTypeBean> response = roomTypeController.getById(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(roomTypeBean, response.getBody());
    }

//    @Test
//    public void testDelete() {
//        doNothing().when(roomTypeService).delete(1L);
//
//        ResponseEntity response = roomTypeController.delete(1L);
//
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//        assertEquals(null, response.getBody());
//    }

    @Test
    public void testPut() {
        doNothing().when(roomTypeService).updateStatus(any(RoomType.class));

        roomTypeController.put(new RoomType());

        // No need to assert response, as it's void
    }

    // Additional test methods for other endpoints if needed
}
