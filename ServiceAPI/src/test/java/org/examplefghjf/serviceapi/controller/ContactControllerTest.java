package org.examplefghjf.serviceapi.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import jdk.jfr.ContentType;
import org.examplefghjf.serviceapi.db.entity.Contact;
import org.examplefghjf.serviceapi.db.entity.Group;
import org.examplefghjf.serviceapi.service.ContactService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

//@ExtendWith(SpringExtension.class)
@WebMvcTest(ContactController.class)
class ContactControllerTest {

    @MockBean
    private ContactService contactService;
    @Autowired
    private ContactController contactController;

    @Autowired
    private MockMvc mockMvc;

    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        objectMapper = new ObjectMapper();
    }


    @Test
    void createContact_RequestIsValid_ReturnCreatedContactInResponseEntity() throws Exception{
        Contact contact = new Contact();
        contact.setContactName("John Doe");
        contact.setStatus("active");
        contact.setPhone("8-800-535-35-35");
        contact.setEmail("123@mail.ru");


        Mockito.when(contactService.createContact(contact)).thenReturn(contact);
        mockMvc.perform(post("/contacts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(objectMapper.writeValueAsString(contact)))  // Преобразуем объект в JSON
                 // Ожидаем статус 200 OK
                .andExpect(MockMvcResultMatchers.status().isOk());

    }

    @Test
    void getContact_Test() throws Exception {
        Contact contact1 = new Contact();
        contact1.setContactName("John Doe");
        contact1.setStatus("active");
        contact1.setPhone("8-800-535-35-35");
        contact1.setEmail("123@mail.ru");
        Contact contact2 = new Contact();
        contact2.setContactName("Jah");
        contact2.setStatus("active");
        contact2.setPhone("8-111-535-35-35");
        contact2.setEmail("1234@mail.ru");
        List<Contact> contacts = Arrays.asList(contact1,contact2);

        Mockito.when(contactService.getAllContacts()).thenReturn(contacts);

        mockMvc.perform(get("/contacts")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(contacts)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].contactName").value("John Doe"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].phone").value("8-800-535-35-35"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].email").value("123@mail.ru"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].contactName").value("Jah"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].phone").value("8-111-535-35-35"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].email").value("1234@mail.ru"));

    }
}