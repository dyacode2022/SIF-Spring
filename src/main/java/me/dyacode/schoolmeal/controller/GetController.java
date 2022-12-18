package me.dyacode.schoolmeal.controller;

import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api")
public class GetController {

    final public String INFO_BASE_URL = "https://open.neis.go.kr/hub/schoolInfo?Type=json&pIndex=1&pSize=100";
    final public String KEY = "d374573af8d34cddaf4e4c250b995c8c";

    @GetMapping("/info")
    public JSONObject GetSchool(@RequestParam String name ) throws ParseException {

        String API_URL = INFO_BASE_URL + "&SCHUL_NM=" + name + "&KEY=" + KEY;

        RestTemplate restTemplate = new RestTemplate();
        String res = restTemplate.getForObject(API_URL, String.class);

        JSONParser parser = new JSONParser();
        JSONObject obj = (JSONObject) parser.parse(res);
        JSONArray arr = (JSONArray) obj.get("schoolInfo");
        JSONObject arrbody = (JSONObject) arr.get(1);
        JSONArray row = (JSONArray) arrbody.get("row");
        JSONObject resp = (JSONObject) row.get(0);

        return resp;
    }

}
