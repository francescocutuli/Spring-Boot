# PREPARE_INSERT_PRODUCT
INSERT INTO products(name,expdate,description)
VALUES (?,?,?);

# SELECT_ALL_PRODUCTS
SELECT *
FROM product;

# SELECT_BY_ID
SELECT *
FROM product
WHERE id = ?;

# UPDATE_PRODUCT
UPDATE product
SET name = ?,
expdate = ?,
description = ?,
WHERE id = ?;

# DELETE_BY_ID
DELETE FROM product
WHERE id = ?;

# DELETE_ALL
DELETE FROM product;