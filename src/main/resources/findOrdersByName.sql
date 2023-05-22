SELECT o.product_name
FROM orders o
         JOIN customers c ON o.customer_id = c.id
WHERE LOWER(c.name) = LOWER(:name);