package com.ieeecsbdc.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ieeecsbdc.model.Event;
import com.ieeecsbdc.repository.EventRepository;
import com.ieeecsbdc.controller.EventController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Optional;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(EventController.class)
public class EventControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EventRepository eventRepository;

    private Event event1;
    private Event event2;

    @BeforeEach
    public void setup() {
        event1 = new Event();
        event1.setId(1L);
        event1.setTitle("Event 1");
        event1.setDescription("Description 1");
        event1.setDate(LocalDate.of(2024, 9, 15));
        event1.setLocation("Dhaka");
        event1.setImage("image1.jpg");

        event2 = new Event();
        event2.setId(2L);
        event2.setTitle("Event 2");
        event2.setDescription("Description 2");
        event2.setDate(LocalDate.of(2024, 10, 10));
        event2.setLocation("Chittagong");
        event2.setImage("image2.jpg");
    }

    @Test
    public void testGetAllEvents() throws Exception {
        Mockito.when(eventRepository.findAll()).thenReturn(Arrays.asList(event1, event2));

        mockMvc.perform(get("/api/events"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    public void testGetEventById() throws Exception {
        Mockito.when(eventRepository.findById(1L)).thenReturn(Optional.of(event1));

        mockMvc.perform(get("/api/events/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Event 1"));
    }

    @Test
    public void testCreateEvent() throws Exception {
        Mockito.when(eventRepository.save(any(Event.class))).thenReturn(event1);

        mockMvc.perform(post("/api/events")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(event1)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Event 1"));
    }

    @Test
    public void testUpdateEvent() throws Exception {
        Mockito.when(eventRepository.findById(anyLong())).thenReturn(Optional.of(event1));
        Mockito.when(eventRepository.save(any(Event.class))).thenReturn(event1);

        event1.setTitle("Updated Event");

        mockMvc.perform(put("/api/events/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(event1)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Updated Event"));
    }

    @Test
    public void testDeleteEvent() throws Exception {
        Mockito.when(eventRepository.existsById(1L)).thenReturn(true);

        mockMvc.perform(delete("/api/events/1"))
                .andExpect(status().isNoContent());
    }
}
