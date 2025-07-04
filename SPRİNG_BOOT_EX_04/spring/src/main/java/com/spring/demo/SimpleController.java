package com.spring.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class SimpleController {

    @Autowired
    private BookRepository bookRepository; // Repository katmanını inject et

    @GetMapping("/books/{id}")
    public String viewBook(@PathVariable Long id, Model model) {
        Book book = bookRepository.findById(id)
                      .orElseThrow(() -> new IllegalArgumentException("Invalid book Id:" + id));
        model.addAttribute("book", book);
        return "view-book"; 
    }
}
