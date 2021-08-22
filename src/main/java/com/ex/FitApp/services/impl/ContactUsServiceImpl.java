package com.ex.FitApp.services.impl;

import com.ex.FitApp.models.bindings.ContactUsAddBinding;
import com.ex.FitApp.models.entities.ContactUsEntity;
import com.ex.FitApp.models.views.MessageView;
import com.ex.FitApp.repositories.ContactUsRepository;
import com.ex.FitApp.services.ContactUsService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ContactUsServiceImpl implements ContactUsService {

    private final ModelMapper modelMapper;
    private final ContactUsRepository contactUsRepository;

    public ContactUsServiceImpl(ModelMapper modelMapper, ContactUsRepository contactUsRepository) {
        this.modelMapper = modelMapper;
        this.contactUsRepository = contactUsRepository;
    }

    @Override
    public void addMessage(ContactUsAddBinding contactUsModel) {
        this.contactUsRepository.save(modelMapper.map(contactUsModel, ContactUsEntity.class));
    }

    @Override
    public void deleteById(Long messageId) {
        this.contactUsRepository.deleteById(messageId);
    }

    @Override
    public List<MessageView> getAllMessagesDetails() {
        return this.contactUsRepository.findAll().stream().map(contactUsEntity -> modelMapper.map(contactUsEntity,MessageView.class)).collect(Collectors.toList());
    }

    @Override
    public void deleteAllMessages() {
        this.contactUsRepository.deleteAll();
    }
}
