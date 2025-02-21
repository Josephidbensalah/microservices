create table if not exists `customer` (
    `customer_id` int AUTO_INCREMENT PRIMARY KEY,
    `name` varchar(255) NOT NULL,
    `email` varchar(255) NOT NULL,
    `mobile_number` varchar(255) NOT Null ,
    `created_at` date NOT NULL,
    `created_by` varchar(25) NOT NULL,
    `updated_at` date NOT NULL,
    `updated_by` varchar(25) NOT NULL
);

create table if not exists `accounts` (
    `customer_id` int NOT NULL,
    `account_number` int AUTO_INCREMENT PRIMARY KEY,
    `account_type` varchar(255) NOT NULL,
    `branch_address` varchar(255) NOT NULL,
    `created_at` date NOT NULL,
    `created_by` varchar(25) NOT NULL,
    `updated_at` date NOT NULL,
    `updated_by` varchar(25) NOT NULL
);