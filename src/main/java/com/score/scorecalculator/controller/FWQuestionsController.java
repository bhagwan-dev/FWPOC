package com.score.scorecalculator.controller;

import com.score.scorecalculator.domain.FWQuestions;
import com.score.scorecalculator.exceptions.MyResourceNotFoundException;
import com.score.scorecalculator.services.FWQuestionsServices;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.*;

import java.util.Locale;

@RestController
@RequestMapping("/api/questions")
@RequiredArgsConstructor
@CrossOrigin("*")
public class FWQuestionsController {
    @Autowired
    FWQuestionsServices fwQuestionsServices;

    private final MessageSource messageSource;

    @PostMapping(value = "/")
    public FWQuestions add(@RequestBody FWQuestions fwQuestions){
        return fwQuestionsServices.add(fwQuestions);
    }
    @GetMapping(value = "/{id}")
    public FWQuestions getById(@PathVariable Long id){
        return fwQuestionsServices.getById(id).orElseThrow(()-> new MyResourceNotFoundException(messageSource.getMessage("api.error.user.not.found", new Long[]{id,id}, Locale.ENGLISH)));
    }
    @GetMapping(value = "/findByType")
    public FWQuestions getByQuestionType(@RequestParam String type){
        return fwQuestionsServices.getByQuestionType(type).orElseThrow(()-> new MyResourceNotFoundException(messageSource.getMessage("api.error.question.type.not.found", new String[]{type}, Locale.ENGLISH)));
    }
}
