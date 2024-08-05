package com.kacper.musicapp.mail;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/public")
public class MailController
{
    private final MailService mailService;

    public MailController(MailService mailService) {
        this.mailService = mailService;
    }

    @PostMapping("/mail/send/{mail}")
    public String sendMail(
            @PathVariable String mail,
            @RequestBody MailStructure mailStructure
    ) {
        mailService.sendMail(mail, mailStructure);

        return "Mail sent successfully!";
    }
}
