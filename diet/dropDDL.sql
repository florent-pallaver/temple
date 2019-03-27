ALTER TABLE user_day DROP FOREIGN KEY FK_user_day_USER_ID
ALTER TABLE user_day DROP KEY UNQ_user_day_0
ALTER TABLE meal_content DROP FOREIGN KEY FK_meal_content_food_id
ALTER TABLE meal_content DROP FOREIGN KEY FK_meal_content_Meal_ID
ALTER TABLE user_day_meal DROP FOREIGN KEY FK_user_day_meal_UserDay_ID
ALTER TABLE user_day_meal DROP FOREIGN KEY FK_user_day_meal_meals_ID
DROP TABLE food
DROP TABLE meal
DROP TABLE user
DROP TABLE user_day
DROP TABLE meal_content
DROP TABLE user_day_meal
