package pe.edu.upeu.asistencia.controllers;

import jakarta.servlet.http.HttpSession;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/asis")
public class MessagesController {

    @GetMapping("/messages")
    public ResponseEntity<List<String>> messages(HttpSession session) {
       String sesionx=(String) session.getAttribute("USER_SESSION");
               System.out.println("data:"+sesionx);
        return ResponseEntity.ok(Arrays.asList("first", "second"));
    }
}
