package letscode.sarafan.controller;

import letscode.sarafan.domain.Message;
import letscode.sarafan.repo.MessageRepo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("message")
public class MessageController {
    private final MessageRepo messageRepo;

    @Autowired
    public MessageController(MessageRepo messageRepo) {
        this.messageRepo = messageRepo;
    }

    /*int counter = 4;

    private List<Map<String, String>> messages = new ArrayList<Map<String, String>>() {{
        add(new HashMap<String, String>() {{ put("id", "1"); put("text", "First message"); }});
        add(new HashMap<String, String>() {{ put("id", "2"); put("text", "Second message"); }});
        add(new HashMap<String, String>() {{ put("id", "3"); put("text", "Third message"); }});
    }};*/

    @GetMapping
    public List<Message> list() {
        return messageRepo.findAll();
    }

    @GetMapping("{id}")
    public Message getOne(@PathVariable("id") Message message/*String id*/) {
        return message;
    }

    /*private Map<String, String> getMessage(@PathVariable String id) {
        return messages.stream()
                .filter(message -> message.get("id").equals(id))
                .findFirst()
                .orElseThrow(NotFoundException::new);
    }*/

    @PostMapping
    public Message create(@RequestBody /*Map<String, String>*/ Message message) {
        /*message.put("id", String.valueOf(counter++));
        messages.add(message);*/
        return messageRepo.save(message);
    }

    @PutMapping("{id}")
    public Message update(
            @PathVariable ("id") /*String id*/ Message messageFromDb,
            @RequestBody /*Map<String, String>*/ Message message) {
/*        Map<String, String> messageFromDb = getMessage(id);
        messageFromDb.putAll(message);
        messageFromDb.put("id", id);*/
        BeanUtils.copyProperties(message,messageFromDb,"id");
        return messageRepo.save(messageFromDb);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") /*String id*/ Message message) {
/*        Map<String, String> message = getMessage(id);
        messages.remove(message);*/
        messageRepo.delete(message);
    }
}
