package diet.model;

import java.time.LocalDate;
import java.util.Collections;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;

import org.eclipse.persistence.jaxb.MarshallerProperties;
import org.junit.Test;

import diet.model.jaxb.MapAdapter2;
import diet.model.jaxb.MapEntry;

public class MarshallerTest {

	@Test
	public void test_meal_marshalling() throws JAXBException {

		final User user = new User();
		final UserDay userDay = new UserDay(LocalDate.now(), user);
		final Meal meal = new Meal(userDay, MealTime.AFTERNOON_SNACK);
		final Food food = new Food(new FoodData("food", "brand", new Intake(), FoodType.ANIMALS, Counting.GRAM));
		meal.setFoodQuantities(Collections.singletonMap(food, 5));
		
		user.setScore(food, 10);
		
		System.setProperty("javax.xml.bind.context.factory","org.eclipse.persistence.jaxb.JAXBContextFactory");

		final JAXBContext jc = JAXBContext.newInstance(UserDay.class, Meal.class, User.class, MapEntry.class, MapAdapter2.class);

		final javax.xml.bind.Marshaller marshaller = jc.createMarshaller();
		marshaller.setProperty(MarshallerProperties.MEDIA_TYPE, "application/json");

		marshaller.setProperty(javax.xml.bind.Marshaller.JAXB_FORMATTED_OUTPUT, true);

		marshaller.marshal(meal, System.out);
		marshaller.marshal(user, System.out);
	}

}
