/* EVIDEN (C)2024 */
package com.eviden.demo;

import net.datafaker.Faker;
import net.datafaker.providers.base.Options;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.random.RandomGenerator;

public class SQLStatementsCreator {

    static Faker faker = new Faker(new Random(534));
    static Faker faker2 = new Faker(new Random(244));

    public static void main(String[] args) throws IOException {
        int quantityOfProductsCreated = createProducts();
        createOrders(quantityOfProductsCreated);
    }

    private static void createOrders(int quantityOfProductsCreated) throws IOException {
        enum OrderStatus {
            COMPLETED,
            PROCESSING,
            PENDING,
            CANCELLED
        }

        final Options opt = faker.options();

        // INSERT INTO public.purchase_order_item ("version", quantity, unit_price,
        // product_id, purchase_order_id) VALUES('', '', '', '', 0, 0, 0, 0, 0);

        try (BufferedWriter writer =
                new BufferedWriter(new FileWriter("/home/harold/V1_0_3__orders.sql"))) {
            for (int index = 1; index < 100; ++index) {
                List<String> sqlForPurchaseOrderList = new ArrayList<>();
                float totalAmount = 0;

                for (int indexForItem = 1;
                        indexForItem <= RandomGenerator.getDefault().nextInt(1, 10);
                        ++indexForItem) {
                    // int index = i.getAndIncrement();
                    int quantity = RandomGenerator.getDefault().nextInt(1, 10);
                    float itemPrice = RandomGenerator.getDefault().nextFloat(1, 1000);
                    String sqlForPurchaseOrderItem =
                            String.format(
                                    "INSERT INTO public.purchase_order_item (\"version\", quantity, unit_price, product_id, purchase_order_id) "
                                            + "VALUES(0, %d, %.2f, %d, %d);",
                                    quantity,
                                    itemPrice,
                                    RandomGenerator.getDefault()
                                            .nextInt(1, quantityOfProductsCreated),
                                    index);

                    sqlForPurchaseOrderList.add(sqlForPurchaseOrderItem);
                    totalAmount = totalAmount + (quantity * itemPrice);
                }

                String sqlForPurchaseOrder =
                        String.format(
                                "INSERT INTO public.purchase_order (id, \"version\", city, country, customer_id, purchase_order_date, purchase_order_id, state, status, street, total_amount, zip_code) "
                                        + "VALUES(%d, 0, '%s', '%s', 'customer_%010d', '%s','purchase_%010d', '%s', '%s', '%s', %.2f, '%s');",
                                index,
                                faker.address().city().replaceAll("'", "-"),
                                faker2.country().name().replaceAll("'", "-"),
                                RandomGenerator.getDefault().nextInt(1, 10000),
                                faker.timeAndDate()
                                        .between(
                                                LocalDateTime.of(2020, 1, 1, 12, 12, 12)
                                                        .toInstant(ZoneOffset.UTC),
                                                LocalDateTime.of(2024, 1, 1, 12, 12, 12)
                                                        .toInstant(ZoneOffset.UTC)),
                                index,
                                faker2.address().state().replaceAll("'", "-"),
                                opt.option(OrderStatus.class),
                                faker2.address().streetName().replaceAll("'", "-"),
                                totalAmount,
                                faker.address().zipCode());

                writer.newLine();
                writer.write(sqlForPurchaseOrder);

                for (String sqlForItem : sqlForPurchaseOrderList) {
                    writer.newLine();
                    writer.write(sqlForItem);
                }
            }
        }
    }

    private static int createProducts() throws IOException {
        try (BufferedWriter writer =
                new BufferedWriter(new FileWriter("/home/harold/V1_0_2__products.sql"))) {
            Set<String> productsList = new HashSet<String>();
            for (int i = 1; i < 100; ++i) {
                productsList.add(
                        faker.book().title().replaceAll("'", "-")
                                + " - "
                                + faker2.book().publisher().replaceAll("'", "-"));
            }

            final AtomicInteger i = new AtomicInteger(1);
            productsList.stream()
                    .forEach(
                            item -> {
                                int index = i.getAndIncrement();
                                String sql =
                                        String.format(
                                                "INSERT INTO public.product (id, \"version\", product_id, product_name) VALUES(%d, 0, 'pr_%010d', '%s');",
                                                index, index, item);
                                try {
                                    writer.newLine();
                                    writer.write(sql);
                                } catch (IOException e) {
                                    // TODO Auto-generated catch block
                                    e.printStackTrace();
                                }
                            });

            return productsList.size();
        }
    }
}
