package enviar_correo.demo.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import enviar_correo.demo.services.EmailService;


@RestController
public class RegistroController {

    @Autowired
    private EmailService emailService;

    @PostMapping("/api/registro")
    public String registrarUsuario(@RequestParam String email, @RequestParam String nombre) {
       
        emailService.enviarCorreoHtml(email, nombre);
        
        return "Usuario registrado y correo enviado a " + email;
    }
}