CREATE TABLE food (ID INTEGER AUTO_INCREMENT NOT NULL, BRAND VARCHAR(255) NOT NULL, CREATIONDATE DATETIME NOT NULL, NAME VARCHAR(255) NOT NULL UNIQUE, CARB DOUBLE NOT NULL, FAT DOUBLE NOT NULL, IG INTEGER NOT NULL, KCAL INTEGER NOT NULL, PROTEIN DOUBLE NOT NULL, PRIMARY KEY (ID))
CREATE TABLE meal (ID INTEGER AUTO_INCREMENT NOT NULL, MEALTIME TIME NOT NULL, PRIMARY KEY (ID))
CREATE TABLE user (ID INTEGER AUTO_INCREMENT NOT NULL, GROWTHMODE VARCHAR(255) NOT NULL, NAME VARCHAR(255) NOT NULL, WEIGHDATE DATETIME NOT NULL, WEIGHT DOUBLE NOT NULL, PRIMARY KEY (ID))
CREATE TABLE user_day (ID INTEGER AUTO_INCREMENT NOT NULL, day_date DATE NOT NULL, SLEEPDURATION INTEGER NOT NULL, CARB DOUBLE NOT NULL, FAT DOUBLE NOT NULL, IG INTEGER NOT NULL, KCAL INTEGER NOT NULL, PROTEIN DOUBLE NOT NULL, USER_ID INTEGER NOT NULL, PRIMARY KEY (ID))
CREATE TABLE meal_content (Meal_ID INTEGER, quantity INTEGER NOT NULL, food_id INTEGER NOT NULL)
CREATE TABLE user_day_meal (UserDay_ID INTEGER NOT NULL, meals_ID INTEGER NOT NULL, PRIMARY KEY (UserDay_ID, meals_ID))
ALTER TABLE user_day ADD CONSTRAINT UNQ_user_day_0 UNIQUE (user_id, day_date)
ALTER TABLE user_day ADD CONSTRAINT FK_user_day_USER_ID FOREIGN KEY (USER_ID) REFERENCES user (ID)
ALTER TABLE meal_content ADD CONSTRAINT FK_meal_content_food_id FOREIGN KEY (food_id) REFERENCES food (ID)
ALTER TABLE meal_content ADD CONSTRAINT FK_meal_content_Meal_ID FOREIGN KEY (Meal_ID) REFERENCES meal (ID)
ALTER TABLE user_day_meal ADD CONSTRAINT FK_user_day_meal_UserDay_ID FOREIGN KEY (UserDay_ID) REFERENCES user_day (ID)
ALTER TABLE user_day_meal ADD CONSTRAINT FK_user_day_meal_meals_ID FOREIGN KEY (meals_ID) REFERENCES meal (ID)
