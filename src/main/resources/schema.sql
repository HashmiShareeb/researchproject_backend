CREATE TABLE IF NOT EXISTS rides (
    ride_id SERIAL PRIMARY KEY,
    ride_name VARCHAR(255) NOT NULL,
    ride_status VARCHAR(50) NOT NULL CHECK (ride_status IN ('AVAILABLE', 'OCCUPIED', 'IN_TRANSIT', 'MAINTENANCE', 'OUT_OF_SERVICE')),
    ride_price DECIMAL(10, 2) NOT NULL
);


CREATE TABLE IF NOT EXISTS rides (
                                     ride_id SERIAL PRIMARY KEY,
                                     ride_name VARCHAR(255) NOT NULL,
    ride_status VARCHAR(50) NOT NULL CHECK (ride_status IN (  'REQUESTED',
                                            'IN_PROGRESS',
                                            'COMPLETED',
                                            'CANCELLED')),
    ride_price DECIMAL(10, 2) NOT NULL,
    ride_description VARCHAR(255),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL
    );
--------------------------------
CREATE TABLE IF NOT EXISTS rides (
                                     ride_id SERIAL PRIMARY KEY,
                                     user_id varchar(36) NOT NULL,
                                     vehicle_id varchar(36),
                                     ride_name VARCHAR(255) NOT NULL,
    ride_status VARCHAR(50) NOT NULL,
    ride_price DECIMAL(10,2) NOT NULL,
    ride_description VARCHAR(255),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,

    -- Foreign Keys
    CONSTRAINT fk_rides_users FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE CASCADE,
    CONSTRAINT fk_rides_vehicles FOREIGN KEY (vehicle_id) REFERENCES vehicle(vehicle_id) ON DELETE SET NULL
    )


CREATE TABLE IF NOT EXISTS vehicle (
                                       vehicle_id VARCHAR(255) PRIMARY KEY,         -- Vehicle ID as a String (UUID in String format)
    manufacturer VARCHAR(255) NOT NULL,          -- Vehicle manufacturer
    model VARCHAR(255) NOT NULL,                 -- Vehicle model
    license_plate VARCHAR(50) NOT NULL,          -- Vehicle license plate
    year INT NOT NULL,                           -- Vehicle year
    battery_level INT,                           -- Vehicle battery level
    owner_id VARCHAR(255) NOT NULL,              -- Foreign key referencing the Owner table
    vehicle_status VARCHAR(50) NOT NULL,         -- Vehicle status (e.g., IN_USE, AVAILABLE)
    vehicle_image VARCHAR(255),                 -- Vehicle image URL
    );



CREATE TABLE users (
                       user_id VARCHAR(36) PRIMARY KEY AUTO_INCREMENT,
                       username VARCHAR(50) UNIQUE NOT NULL,
                       password VARCHAR(100) NOT NULL,
                       email VARCHAR(100) NOT NULL
);

CREATE TABLE roles (
                       id BIGSERIAL PRIMARY KEY, -- Auto-incrementing ID
                       user_id VARCHAR(36) NOT NULL,
                       role VARCHAR(20) NOT NULL,
                       FOREIGN KEY (user_id) REFERENCES users(user_id, username) ON DELETE CASCADE
);
