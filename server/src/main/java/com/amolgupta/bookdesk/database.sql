### Database
* Create Table
    CREATE TABLE IF NOT EXISTS User (
        id VARCHAR(50) PRIMARY KEY, 
        first_name VARCHAR(50), 
        last_name VARCHAR(50), 
        user_email TEXT NOT NULL 
    );

    CREATE TABLE IF NOT EXISTS Room (
        id VARCHAR(50) PRIMARY KEY NOT NULL, 
        room_name VARCHAR(100)
    );

    CREATE TABLE IF NOT EXISTS Desk (
        id VARCHAR(50) PRIMARY KEY NOT NULL,
        desk_number INTEGER,
        covid_compliant BOOLEAN,
        room_id INTEGER,
        FOREIGN KEY (room_id) REFERENCES Room (id)
    );


### Data


### Sqlite commands
.header on
.mode column
pragma table_info('Desk')