package com.ex.FitApp.services;

import com.ex.FitApp.models.bindings.ContactUsAddBinding;
import com.ex.FitApp.models.views.MessageView;

import java.util.List;

public interface ContactUsService {
    void addMessage(ContactUsAddBinding contactUsModel);

    void deleteById(Long messageId);

    List<MessageView> getAllMessagesDetails();

    void deleteAllMessages();
}
