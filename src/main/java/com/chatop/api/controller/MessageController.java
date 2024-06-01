package com.chatop.api.controller;

import com.chatop.api.model.Message;
import com.chatop.api.service.MessageService;

import io.swagger.annotations.ApiOperation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/messages")
public class MessageController {
   
    @Autowired 

    private MessageService messageService;

    @PostMapping
    @ApiOperation(value = "Enregistre un nouveau message")
    public ResponseEntity<?> createMessage(@RequestBody Message messageDTO) {

        Message message = new Message();
       
        messageService.createMessage(message);

        return ResponseEntity.ok("{\"message\":\"Message created !\"}");
    }

}

