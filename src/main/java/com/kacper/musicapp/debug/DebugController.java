package com.kacper.musicapp.debug;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/debug")
public class DebugController
{
    @GetMapping("/test")
    public String test() {
        return "test";
    }
}
