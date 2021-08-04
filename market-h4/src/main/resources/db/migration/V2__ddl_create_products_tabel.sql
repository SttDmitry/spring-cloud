CREATE TABLE `products` (
                            `id` int NOT NULL AUTO_INCREMENT,
                            `category_id` int NOT NULL,
                            `vendor_code` char(30) NOT NULL,
                            `title` varchar(25) NOT NULL,
                            `short_description` varchar(75) NOT NULL,
                            `full_description` varchar(25) NOT NULL,
                            `price` int NOT NULL,
                            PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
