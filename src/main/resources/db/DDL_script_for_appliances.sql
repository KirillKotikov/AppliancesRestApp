INSERT INTO computer (id, name, company_manufacturer, producing_country, available_online, installment_plan)
VALUES (1, 'Computers Asus', 'AsusTek Computer Inc.', 'Taiwan', true, true);
INSERT INTO computer (id, name, company_manufacturer, producing_country, available_online, installment_plan)
VALUES (2, 'Computers Apple', 'Apple', 'USA', false, false);
INSERT INTO computer (id, name, company_manufacturer, producing_country, available_online, installment_plan)
VALUES (3, 'Computers HP', 'Hewlett-Packard', 'USA', true, true);

INSERT INTO computer_model (computer_id, name, serial_number, color, size, price, category, processor_type, in_stock)
VALUES (1, 'Asus 1', 98, 'Grey', 14, 14990, 'Notebook', 'Intel', true);
INSERT INTO computer_model (computer_id, name, serial_number, color, size, price, category, processor_type, in_stock)
VALUES (1, 'Asus 22', 3222, 'White', 16, 15000, 'Notebook', 'Intel', false);
INSERT INTO computer_model (computer_id, name, serial_number, color, size, price, category, processor_type, in_stock)
VALUES (1, 'Asus 34', 542, 'Black', 15, 19000, 'Notebook', 'Intel', true);

INSERT INTO computer_model (computer_id, name, serial_number, color, size, price, category, processor_type, in_stock)
VALUES (2, 'Apple Macbook 1', 356, 'Grey', 13, 89000, 'Notebook', 'Apple', true);
INSERT INTO computer_model (computer_id, name, serial_number, color, size, price, category, processor_type, in_stock)
VALUES (2, 'Apple 1', 555, 'White', 16, 56000, 'PK', 'Apple', true);
INSERT INTO computer_model (computer_id, name, serial_number, color, size, price, category, processor_type, in_stock)
VALUES (2, 'Apple 16', 678, 'White', 16, 78000, 'PK', 'Intel', false);

INSERT INTO computer_model (computer_id, name, serial_number, color, size, price, category, processor_type, in_stock)
VALUES (3, 'HP 144', 876, 'White', 16, 45000, 'PK', 'Intel', true);
INSERT INTO computer_model (computer_id, name, serial_number, color, size, price, category, processor_type, in_stock)
VALUES (3, 'HP 13', 12456, 'Grey', 16, 35000, 'notebook', 'Intel', false);
INSERT INTO computer_model (computer_id, name, serial_number, color, size, price, category, processor_type, in_stock)
VALUES (3, 'HP 56', 9574, 'White', 16, 33000, 'notebook', 'Intel', true);



INSERT INTO fridge (id, name, company_manufacturer, producing_country, available_online, installment_plan)
VALUES (1, 'Fridges LG', 'LG Group', 'Korea', false, false);
INSERT INTO fridge (id, name, company_manufacturer, producing_country, available_online, installment_plan)
VALUES (2, 'Fridges Haier', 'Haier', 'China', true, true);
INSERT INTO fridge (id, name, company_manufacturer, producing_country, available_online, installment_plan)
VALUES (3, 'Fridges Bosh', 'Bosh company', 'Germany', true, true);

INSERT INTO fridge_model (fridge_id, name, serial_number, color, size, price, numbers_of_doors, compressor_type, in_stock)
VALUES (1, 'LG 12', 923, 'White', 180, 44990, '2', 'simple', true);
INSERT INTO fridge_model (fridge_id, name, serial_number, color, size, price, numbers_of_doors, compressor_type, in_stock)
VALUES (1, 'LG 23', 999, 'Grey', 200, 98000, '1', 'simple', false);
INSERT INTO fridge_model (fridge_id, name, serial_number, color, size, price, numbers_of_doors, compressor_type, in_stock)
VALUES (1, 'LG 77', 789, 'Reg', 190, 129000, '2', 'twice', true);

INSERT INTO fridge_model (fridge_id, name, serial_number, color, size, price, numbers_of_doors, compressor_type, in_stock)
VALUES (2, 'Haier Mo', 1122, 'White', 180, 45999, '1', 'simple', false);
INSERT INTO fridge_model (fridge_id, name, serial_number, color, size, price, numbers_of_doors, compressor_type, in_stock)
VALUES (2, 'Haier Zi', 1423, 'Grey', 180, 87990, '2', 'simple', true);
INSERT INTO fridge_model (fridge_id, name, serial_number, color, size, price, numbers_of_doors, compressor_type, in_stock)
VALUES (2, 'Haier li', 1452, 'Grey', 200, 125000, '2', 'twice', true);

INSERT INTO fridge_model (fridge_id, name, serial_number, color, size, price, numbers_of_doors, compressor_type, in_stock)
VALUES (3, 'Bosh Asu', 765, 'Black', 190, 84930, '1', 'simple', true);
INSERT INTO fridge_model (fridge_id, name, serial_number, color, size, price, numbers_of_doors, compressor_type, in_stock)
VALUES (3, 'Bosh W2', 908, 'White', 160, 45890, '2', 'twice', false);
INSERT INTO fridge_model (fridge_id, name, serial_number, color, size, price, numbers_of_doors, compressor_type, in_stock)
VALUES (3, 'Bosh TTUR', 662, 'White', 170, 66000, '1', 'simple', true);



INSERT INTO hoover (id, name, company_manufacturer, producing_country, available_online, installment_plan)
VALUES (1, 'Hoovers LG', 'LG Group', 'Korea', false, false);
INSERT INTO hoover (id, name, company_manufacturer, producing_country, available_online, installment_plan)
VALUES (2, 'Hoovers Mi', 'Xiaomi', 'China', true, true);
INSERT INTO hoover (id, name, company_manufacturer, producing_country, available_online, installment_plan)
VALUES (3, 'Hoovers Vitek', 'Vitek company', 'China', true, true);

INSERT INTO hoover_model (hoover_id, name, serial_number, color, size, price, dust_container_volume, number_of_modes, in_stock)
VALUES (1, 'LG pop', 4421, 'Black', 50, 24000, 300, 6, true);
INSERT INTO hoover_model (hoover_id, name, serial_number, color, size, price, dust_container_volume, number_of_modes, in_stock)
VALUES (1, 'LG loco', 6777, 'Grey', 54, 20000, 270, 3, false);
INSERT INTO hoover_model (hoover_id, name, serial_number, color, size, price, dust_container_volume, number_of_modes, in_stock)
VALUES (1, 'LG redis', 332, 'Red', 45, 34000, 250, 7, true);

INSERT INTO hoover_model (hoover_id, name, serial_number, color, size, price, dust_container_volume, number_of_modes, in_stock)
VALUES (2, 'Mi vacuum', 221, 'White', 40, 16500, 200, 3, true);
INSERT INTO hoover_model (hoover_id, name, serial_number, color, size, price, dust_container_volume, number_of_modes, in_stock)
VALUES (2, 'Mi 1G', 32, 'White', 45, 19900, 250, 4, false);
INSERT INTO hoover_model (hoover_id, name, serial_number, color, size, price, dust_container_volume, number_of_modes, in_stock)
VALUES (2, 'Mi 2c', 2123, 'White', 39, 12500, 250, 5, true);

INSERT INTO hoover_model (hoover_id, name, serial_number, color, size, price, dust_container_volume, number_of_modes, in_stock)
VALUES (3, 'Vitek Super', 332234, 'Grey', 50, 23000, 350, 6, true);
INSERT INTO hoover_model (hoover_id, name, serial_number, color, size, price, dust_container_volume, number_of_modes, in_stock)
VALUES (3, 'Vitek cover', 101, 'Brown', 44, 26000, 300, 5, false);
INSERT INTO hoover_model (hoover_id, name, serial_number, color, size, price, dust_container_volume, number_of_modes, in_stock)
VALUES (3, 'Vitek sandra', 102, 'Red', 47, 27990, 329, 4, true);



INSERT INTO smartphone (id, name, company_manufacturer, producing_country, available_online, installment_plan)
VALUES (1, 'Smartphones Apple', 'Apple', 'USA', true, true);
INSERT INTO smartphone (id, name, company_manufacturer, producing_country, available_online, installment_plan)
VALUES (2, 'Smartphones Mi', 'Xiaomi', 'China', false, true);
INSERT INTO smartphone (id, name, company_manufacturer, producing_country, available_online, installment_plan)
VALUES (3, 'Smartphones Samsung', 'Samsung company', 'Korea', true, false);

INSERT INTO smartphone_model (smartphone_id, name, serial_number, color, size, price, volume_of_memory, number_of_cameras, in_stock)
VALUES (1, 'Iphone 12', 103, 'Red', 12, 150000, 512, 3, true);
INSERT INTO smartphone_model (smartphone_id, name, serial_number, color, size, price, volume_of_memory, number_of_cameras, in_stock)
VALUES (1, 'Iphone 11 pro', 104, 'White', 11, 135000, 256, 3, false);
INSERT INTO smartphone_model (smartphone_id, name, serial_number, color, size, price, volume_of_memory, number_of_cameras, in_stock)
VALUES (1, 'Iphone Xs', 105, 'Black', 10, 122000, 128, 2, true);

INSERT INTO smartphone_model (smartphone_id, name, serial_number, color, size, price, volume_of_memory, number_of_cameras, in_stock)
VALUES (2, 'Redmi note15', 106, 'Red', 15, 111000, 512, 3, true);
INSERT INTO smartphone_model (smartphone_id, name, serial_number, color, size, price, volume_of_memory, number_of_cameras, in_stock)
VALUES (2, 'Redmi 11', 107, 'White', 11, 1000000, 256, 3, false);
INSERT INTO smartphone_model (smartphone_id, name, serial_number, color, size, price, volume_of_memory, number_of_cameras, in_stock)
VALUES (2, 'Redmi x5', 108, 'Black', 10, 112399, 128, 2, true);

INSERT INTO smartphone_model (smartphone_id, name, serial_number, color, size, price, volume_of_memory, number_of_cameras, in_stock)
VALUES (3, 'Samsung tres', 109, 'Red', 13, 109000, 512, 3, true);
INSERT INTO smartphone_model (smartphone_id, name, serial_number, color, size, price, volume_of_memory, number_of_cameras, in_stock)
VALUES (3, 'Samsung si2', 110, 'White', 12, 98909, 256, 3, false);
INSERT INTO smartphone_model (smartphone_id, name, serial_number, color, size, price, volume_of_memory, number_of_cameras, in_stock)
VALUES (3, 'Samsung 25', 111, 'Black', 10, 97890, 128, 2, true);



INSERT INTO television (id, name, company_manufacturer, producing_country, available_online, installment_plan)
VALUES (1, 'Televisions LG', 'LG Group', 'Korea', true, true);
INSERT INTO television (id, name, company_manufacturer, producing_country, available_online, installment_plan)
VALUES (2, 'Televisions Xiaomi', 'Xiaomi', 'China', false, true);
INSERT INTO television (id, name, company_manufacturer, producing_country, available_online, installment_plan)
VALUES (3, 'Televisions Samsung', 'Samsung company', 'Korea', true, false);

INSERT INTO television_model (television_id, name, serial_number, color, size, price, category, technology, in_stock)
VALUES (1, 'LG NANO65', 112, 'Black', 65, 89000, '4K', 'nanocell', true);
INSERT INTO television_model (television_id, name, serial_number, color, size, price, category, technology, in_stock)
VALUES (1, 'LG NANO85', 113, 'Black', 85, 110000, '4K', 'nanocell', false);
INSERT INTO television_model (television_id, name, serial_number, color, size, price, category, technology, in_stock)
VALUES (1, 'LG NANO45', 114, 'White', 45, 65000, '4K', 'nanocell', true);

INSERT INTO television_model (television_id, name, serial_number, color, size, price, category, technology, in_stock)
VALUES (2, 'Xiaomi AM55', 115, 'Grey', 55, 125990, '4K', 'QLED', true);
INSERT INTO television_model (television_id, name, serial_number, color, size, price, category, technology, in_stock)
VALUES (2, 'Xiaomi AM65', 116, 'White', 65, 140000, '8K', 'QLED', false);
INSERT INTO television_model (television_id, name, serial_number, color, size, price, category, technology, in_stock)
VALUES (2, 'Xiaomi AM45', 117, 'Grey', 45, 56000, 'FUL HD', 'LED', true);

INSERT INTO television_model (television_id, name, serial_number, color, size, price, category, technology, in_stock)
VALUES (3, 'Samsung QL55', 118, 'Grey', 55, 57990, '4K', 'LED', true);
INSERT INTO television_model (television_id, name, serial_number, color, size, price, category, technology, in_stock)
VALUES (3, 'Samsung QL65', 119, 'White', 65, 67990, '4k', 'LED', false);
INSERT INTO television_model (television_id, name, serial_number, color, size, price, category, technology, in_stock)
VALUES (3, 'Samsung QL75', 120, 'Grey', 75, 250000, '8K', 'QLED', true);