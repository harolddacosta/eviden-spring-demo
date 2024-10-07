/* EVIDEN (C)2024 */
package com.eviden.demo.infrastructure.adapters.incoming.http.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.eviden.demo.domain.model.PurchaseOrder;
import com.eviden.demo.domain.model.PurchaseOrderItem;
import com.eviden.demo.domain.model.ShippingAddress;
import com.fasterxml.jackson.databind.ObjectMapper;

import net.datafaker.Faker;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.random.RandomGenerator;

@SpringBootTest(webEnvironment = WebEnvironment.MOCK)
@ActiveProfiles(value = "test")
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class PurchaseOrdersControllerTest {

    Faker faker = new Faker();

    @Autowired private MockMvc mockMvc;

    @Autowired private ObjectMapper jsonMapper;

    @Test
    void when_get_a_purchase_order_by_id() throws Exception {
        mockMvc.perform(get("/orders/purchase_0000000007"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.purchaseOrderId").exists());
    }

    @Test
    void when_get_a_list_of_examples() throws Exception {
        mockMvc.perform(get("/orders?page=0&size=5"))
                .andDo(print())
                .andExpect(header().string("Content-Type", "application/json"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.items").isArray())
                .andExpect(jsonPath("$.pagination").exists());
    }

    @Test
    void when_get_a_non_existing_purchase_order() throws Exception {
        mockMvc.perform(get("/orders/non-existing"))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    void when_post_an_example() throws Exception {
        PurchaseOrder requestBody = createObject();

        mockMvc.perform(
                        post("/orders")
                                .content(jsonMapper.writeValueAsString(requestBody))
                                .contentType(MediaType.APPLICATION_JSON)
                                .characterEncoding("UTF-8"))
                .andDo(print())
                .andExpect(header().exists("Location"))
                .andExpect(status().isCreated());
    }

    private PurchaseOrder createObject() {
        return PurchaseOrder.builder()
                .items(
                        List.of(
                                PurchaseOrderItem.builder()
                                        .productId(
                                                "test_pr_"
                                                        + RandomGenerator.getDefault()
                                                                .nextInt(1, 30000))
                                        .productName(
                                                faker.book().title()
                                                        + " - "
                                                        + faker.book().author()
                                                        + " - test")
                                        .quantity(2)
                                        .unitPrice((double) 123)
                                        .build()))
                .totalAmount((double) 4000)
                .status("PROCESSING")
                .purchaseOrderDate(LocalDateTime.now().atOffset(ZoneOffset.UTC))
                .purchaseOrderId("test_order_" + RandomGenerator.getDefault().nextInt(1, 30000))
                .customerId("test_customer_" + RandomGenerator.getDefault().nextInt(1, 30000))
                .shippingAddress(
                        ShippingAddress.builder()
                                .city("Caracas")
                                .country("Venezuela")
                                .state("Miranda")
                                .street("Los Teques")
                                .zipCode("008940")
                                .build())
                .build();
    }
}
