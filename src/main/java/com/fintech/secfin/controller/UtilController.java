package com.fintech.secfin.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/util")
@CrossOrigin(origins = "http://localhost:3000")
public class UtilController {

    @GetMapping("/proxy")
    public String proxy(@RequestParam String url) {
        RestTemplate rt = new RestTemplate();
        return rt.getForObject(url, String.class); // ⚠️ SSRF
    }
}
