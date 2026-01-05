package com.example.demo.messages;

import com.example.demo.model.Message;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/messages") // Basis-Pfad für alle Endpunkte in diesem Controller
public class MessageController {

    // Schritt 1 (POST-Teil): Liste von Message-Objekten im Controller
    private final List<Message> messages = new ArrayList<>();

    // Optional: ein paar Testdaten
    public MessageController() {
        messages.add(new Message("1", "Alice", "Hallo Welt!"));
        messages.add(new Message("2", "Bob", "Spring Boot ist cool."));
    }

    // Schritt 2 (GET-Teil):
    // Verarbeitet GET-Anfragen an /api/messages
    // Gibt die Liste der Nachrichten als JSON zurück
    @GetMapping
    public List<Message> getAllMessages() {
        return messages;
    }

    // Schritt 2 (POST-Teil):
    // Verarbeitet POST-Anfragen an /api/messages
    // Erwartet im Request-Body ein JSON-Objekt mit id, name, message
    // Beispiel-JSON:
    // {
    //   "id": "3",
    //   "name": "Charlie",
    //   "message": "Hi zusammen!"
    // }
    @PostMapping
    public Message addMessage(@RequestBody Message newMessage) {
        // Nachricht in der Liste speichern
        messages.add(newMessage);

        // Optional: die gespeicherte Nachricht zurückgeben (wird als JSON zurückgeschickt)
        return newMessage;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMessage(@PathVariable String id) {

        // removeIf löscht alle Elemente, bei denen die Bedingung true ist
        boolean removed = messages.removeIf(message -> id.equals(message.id()));

        if (removed) {
            // 204 No Content, wenn etwas gelöscht wurde
            return ResponseEntity.noContent().build();
        } else {
            // 404 Not Found, wenn keine Nachricht mit dieser ID existiert
            return ResponseEntity.notFound().build();
        }
    }
}

