package enviar_correo.demo.services;

import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private TemplateEngine templateEngine;

    public void enviarCorreoHtml(String destinatario, String nombreUsuario) {
        try {
            // 1. Preparar el contexto de Thymeleaf con los datos dinámicos
            Context context = new Context();
            context.setVariable("nombre", nombreUsuario);

            // 2. Procesar la plantilla HTML (lee el archivo registro.html)
            String contenidoHtml = templateEngine.process("registro", context);

            // 3. Preparar el mensaje MIME para HTML
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

            helper.setTo(destinatario);
            helper.setSubject("¡Registro Exitoso!");
            helper.setText(contenidoHtml, true); 

            // 4. Enviar
            mailSender.send(message);
            System.out.println("Correo HTML enviado a: " + destinatario);

        } catch (Exception e) {
            System.out.println("Error al enviar el correo: " + e.getMessage());
        }
    }
}