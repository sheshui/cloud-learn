package com.neuqsoft.journal.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author sunjiarui
 * @Date 2020/9/30
 */
@RestController
public class JournalApi {
    @GetMapping("/journal")
    public String index() {
        return "mymoney";
    }
}
