package diet.model;

import java.util.function.Function;

public enum GrowthMode {

	INCREASE(null),
        MAINTAIN(null),
	DECREASE(null),
	;
	
	private final Function<Double, Intake> intakeComputer;
	
	private GrowthMode(Function<Double, Intake> intakeComputer) {
		this.intakeComputer = intakeComputer;
	}
	
	public Intake computeIntake(double weight) {
		return this.intakeComputer.apply(weight);
	}
}
