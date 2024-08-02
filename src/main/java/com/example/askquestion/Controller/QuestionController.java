package com.example.askquestion.Controller;

import com.example.askquestion.entity.Question;
import com.example.askquestion.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Random;
import java.util.UUID;

@RestController
@RequestMapping("/api/questions")
public class QuestionController {
    @Autowired
    private QuestionRepository questionRepository;

    @PostMapping
    public Question submitQuestion(@RequestBody Question question) {
        question.setComplaintID(generateRandom5DigitNumber());
        question.setMeetingLink(generateMeetingLink());
        return questionRepository.save(question);
    }

    @GetMapping
    public List<Question> getAllQuestions() {
        return questionRepository.findAll();
    }
    @DeleteMapping("/{id}")
    public void deleteQuestion(@PathVariable Long id) {
        questionRepository.deleteById(id);
    }
    private String generateRandom5DigitNumber() {
        Random random = new Random();
        int number = 10000 + random.nextInt(90000);  // Generates a 5-digit number
        return Integer.toString(number);
    }

    private String generateMeetingLink() {
        String baseUrl = "https://example.com/meeting/";
        String uniqueId = UUID.randomUUID().toString();
        return baseUrl + uniqueId;
    }
}
