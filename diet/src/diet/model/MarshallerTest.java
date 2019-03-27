package diet.model;

import java.time.LocalDate;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;

import org.eclipse.persistence.jaxb.MarshallerProperties;
import org.junit.Test;

public class MarshallerTest {

	@Test
	public void test_meal_marshalling() throws JAXBException {

		final UserDay userDay = new UserDay(LocalDate.now(), new User());
		final Meal meal = new Meal(userDay, MealTime.AFTERNOON_SNACK);
		//meal.setFoodQuantity(new Food(new FoodData()), 5);
		
		
		System.setProperty("javax.xml.bind.context.factory","org.eclipse.persistence.jaxb.JAXBContextFactory");

		final JAXBContext jc = JAXBContext.newInstance(UserDay.class, Meal.class, User.class);

		final javax.xml.bind.Marshaller marshaller = jc.createMarshaller();
		marshaller.setProperty(MarshallerProperties.MEDIA_TYPE, "application/json");

		marshaller.setProperty(javax.xml.bind.Marshaller.JAXB_FORMATTED_OUTPUT, true);

		marshaller.marshal(meal, System.out);
	}

}
