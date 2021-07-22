package com.example.kyosk;

import com.example.kyosk.wrappers.ConfigWrapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author bkariuki
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
public class TestCases {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void listData() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/configs")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    public void postData() throws Exception {
        ConfigWrapper wrapper = new ConfigWrapper();
        wrapper.setName("my test");
        wrapper.setCpuEnabled(true);
        wrapper.setMonitoringEnabled(true);
        wrapper.setCpuValue("88888");
        mockMvc.perform(MockMvcRequestBuilders.post("/configs")
                .content(new ObjectMapper().writeValueAsString(wrapper))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated())
                .andReturn();
    }
}
