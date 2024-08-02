package com.example.askquestion.service;

import com.example.askquestion.entity.Question;
import com.example.askquestion.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class qustionservice {

    @Autowired
    private QuestionRepository questionRepository;

    public Question submitQuestion(Question question) {
        question.setComplaintID(generateUniqueID());
        question.setMeetingLink(generateMeetingLink());
        return questionRepository.save(question);
    }

    public List<Question> getAllQuestions() {
        return questionRepository.findAll();
    }

    private String generateUniqueID() {
        // Generates a random UUID as a string.
        return UUID.randomUUID().toString();
    }

    private String generateMeetingLink() {
        // Generates a unique meeting link using a base URL and a random UUID.
        String baseUrl = "https://example.com/meeting/";
        String uniqueId = UUID.randomUUID().toString();
        return baseUrl + uniqueId;
    }
}