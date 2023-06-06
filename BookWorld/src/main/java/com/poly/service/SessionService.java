package com.poly.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpSession;

import java.util.HashMap;
import java.util.Map;
@Service
public class SessionService {
    private Map<String, String> sessionData;

    public SessionService() {
        sessionData = new HashMap<>();
    }

    public void set(String key, String value) {
        sessionData.put(key, value);
    }

    public String get(String key) {
        return sessionData.get(key);
    }

    public void remove(String key) {
        sessionData.remove(key);
    }
    
}
