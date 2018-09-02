import java.util.Scanner;

public class MyAccount {
	public enum State{
		pending, active, inactive, priority, in_default,closed, second_chance
		,administration, plan_period, bad_debt, health_debt,collection
	}
	
	private static State currentState = State.pending; 
	
	public static void main(String[] argv) {
		Scanner scan = new Scanner(System.in);
		while (true) {
		switch(currentState) {
		case pending: 
			System.out.println("Signup or not? 1 for yes, 0 for no");
		    currentState = changeStateTo(State.active, currentState, scan);
		    break;
		case active:
			System.out.println("Have any current product or bill? 1 for yes, 0 for no");
			currentState = changeStateTo(currentState, State.inactive, scan);
			if (currentState == State.active) {
				System.out.println("Do you wanna make a complaint? 1 for yes, 0 for no");
				currentState = changeStateTo(State.priority, currentState, scan);
			}
			if (currentState == State.active) {
				System.out.println("Do you fail to pay the bill? 1 for yes, 0 for no");
				currentState = changeStateTo(State.in_default, currentState, scan);
			}
			break;
		case inactive:
			System.out.println("Do you wanna add a product or bill? 1 for yes, 0 for no");
			currentState = changeStateTo(State.active, currentState,scan);
			break;
		case in_default: 
			System.out.println("Catch up the bills? 1 for yes, 0 for no?");
			currentState = changeStateTo(State.active, currentState, scan);
			if (currentState == State.in_default) {
				System.out.println("No response? 1 for yes, 0 for no");
				currentState = changeStateTo(State.closed, currentState, scan);
			}
			if (currentState == State.in_default) {
				System.out.println("Do you want second chance? 1 for yes, 0 for no");
				currentState = changeStateTo(State.second_chance, State.administration, scan);
			}
			break;
		case priority:
			System.out.println("Complaint resolved? 1 for yes, 0 for no");
			currentState = changeStateTo(State.active, currentState.active,scan);
			break;
		case closed: 
			System.out.println("Start as a new account? 1 for yes, 0 for no");
			currentState = changeStateTo(State.pending, currentState, scan);
			break;
		case second_chance:
			System.out.println("Fail to comply within the second chance period£¿ 1 for yes, 0 for no");
			currentState = changeStateTo(State.administration,currentState,scan);
			break;
		case administration:
			System.out.println("Offer a payment plan.");
			currentState = State.plan_period;
			System.out.println(currentState);
		case plan_period:
			System.out.println("Accept the payment plan? 1 for yes, 0 for no");
			currentState = changeStateTo(State.health_debt,State.bad_debt,scan);
			break;
		case bad_debt:
			System.out.println("Plan period passed?");
			currentState = changeStateTo(State.plan_period,currentState,scan);
			if (currentState == State.plan_period) {
				System.out.println("we will remove your account to collection. 1 for yes, o for no");
				currentState = changeStateTo(State.collection,currentState,scan);
			}
			break;
		case health_debt:
			System.out.println("Missed a payment plan? 1 for yes, 0 for no");
			currentState = changeStateTo(State.bad_debt,currentState,scan);
			break;
		case collection:
			System.out.println("This account need to be collected or written off. 1 for yes, 0 for no");
			currentState = changeStateTo(State.pending,currentState,scan);
			break;
		}
		}
	}
	
	public static State changeStateTo(State x, State y, Scanner scan) {
		int i = scan.nextInt();
		if (i==1) {
			System.out.println(x);
			return x;
		}else{
			System.out.println(y);
		}
		return y; 
	}
	
}
