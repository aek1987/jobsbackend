package jobplatform.service;


import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class AiService {

    private final String BASE_URL = "http://localhost:5000";

    private final RestTemplate restTemplate = new RestTemplate();

    // Appeler /recommend
    public Object getRecommendations(String[] skills) {
        String url = BASE_URL + "/recommend";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        Map<String, Object> request = new HashMap<>();
        request.put("skills", skills);

        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(request, headers);

        return restTemplate.postForObject(url, entity, Object.class);
    }

    // Appeler /analyze-cv
    public Object analyzeCV(String text) {
        String url = BASE_URL + "/analyze-cv";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        Map<String, Object> request = new HashMap<>();
        request.put("text", text);

        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(request, headers);

        return restTemplate.postForObject(url, entity, Object.class);
    }
}
