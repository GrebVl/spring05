package com.gb.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import com.gb.model.Issue;
import com.gb.services.IssueService;

import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

@Controller
@RequestMapping("ui")
@RequiredArgsConstructor
public class IssueController {

    @Autowired
    private IssueService service;

    @GetMapping("newIssue")
    public void createIssue(@RequestParam long idBook, @RequestParam long idReader) {
        service.createIssue(idBook, idReader);
    }
    

    @GetMapping("issues")
    public List<Issue> getAllIssue(Model model){
        return service.getIssues();
    }

    @GetMapping("issueIdBook")
    public Issue getIssueBook(@RequestParam long idBook) {
        return service.getByBook(idBook);
    }

    @GetMapping("issueIdReader")
    public Issue getIssueReader(@RequestParam long idReadr) {
        return service.getByReader(idReadr);
    }


    @GetMapping("issueId")
    public Issue getId(@RequestParam Long id) {
        return service.getById(id);
    }
    

    @GetMapping("updateIssue")
    public void getUpdate(@RequestParam long id, @RequestParam long idBook, @RequestParam long idReader) {
        service.updateIssue(id, idBook, idReader);
    }

    @GetMapping("deleteIssue")
    public String deleteIssue(@RequestParam long id) {
        service.deleteIssue(id);
        return "issue delete";
    }
}
