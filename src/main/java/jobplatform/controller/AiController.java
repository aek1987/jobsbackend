package jobplatform.controller;




import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import jobplatform.service.AiService;

@RestController
@RequestMapping("/api/ai")
public class AiController {

    @Autowired
    private AiService aiService;

    @PostMapping("/recommend")
    public Object recommend(@RequestBody Map<String, Object> request) {
        String[] skills = ((java.util.List<String>) request.get("skills")).toArray(new String[0]);
        return aiService.getRecommendations(skills);
    }

    @PostMapping("/analyze-cv")
    public Object analyzeCV(@RequestBody Map<String, Object> request) {
        String text = request.get("text").toString();
        return aiService.analyzeCV(text);
    }
}
