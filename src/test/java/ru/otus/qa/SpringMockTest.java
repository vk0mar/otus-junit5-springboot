package ru.otus.qa;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import ru.otus.qa.service.CalculatorService;
import ru.otus.qa.web.CalculatorController;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringJUnitWebConfig(classes = {CalculatorController.class, CalculatorService.class})
@EnableWebMvc
public class SpringMockTest {

    private MockMvc mockMvc;


    @Autowired
    private WebApplicationContext wac;

    @BeforeEach
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).dispatchOptions(true).build();
    }


    @Test
    public void getMultiply() throws Exception {
        //  создаем POST-запрос, набиваем его параметрами и выполняем
        ResultActions result = mockMvc.perform(MockMvcRequestBuilders.get("/calc")
                .param("operation", "multiply")
                .param("a", "10")
                .param("b", "5"));
        Assert.assertEquals(
                "50",

                result
                        .andExpect(status().isOk())
                        .andExpect(content().contentType("application/json;charset=UTF-8"))
                        .andReturn().getResponse().getContentAsString()
        );

    }

    @Test
    void getPlus() throws Exception {
        Assert.assertEquals(
                "8",

                this.mockMvc.perform(get("/calc?operation=plus&a=5&b=3")
                        .accept(MediaType.parseMediaType("application/json;charset=UTF-8;")))
                        .andExpect(status().isOk())
                        .andExpect(content().contentType("application/json;charset=UTF-8"))
                        //.andExpect(jsonPath("$").value("88"));
                        .andReturn().getResponse().getContentAsString()
        );
    }

    @Test
    void getDivide() throws Exception {
        Assert.assertEquals(
                "25",

                this.mockMvc.perform(get("/calc?operation=divide&a=100&b=4")
                        .accept(MediaType.parseMediaType("application/json;charset=UTF-8;")))
                        .andExpect(status().isOk())
                        .andExpect(content().contentType("application/json;charset=UTF-8"))
                        .andReturn().getResponse().getContentAsString()
        );
    }

    @Test
    void getMinus() throws Exception {
        Assert.assertEquals(
                "-3",

                this.mockMvc.perform(get("/calc?operation=minus&a=1&b=4")
                        .accept(MediaType.parseMediaType("application/json;charset=UTF-8;")))
                        .andExpect(status().isOk())
                        .andExpect(content().contentType("application/json;charset=UTF-8"))
                        .andReturn().getResponse().getContentAsString()
        );
    }


}
