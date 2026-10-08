package kon.atha.HospitalManagement.Application;

import kon.atha.HospitalManagement.controller.HospitalController;
import kon.atha.HospitalManagement.view.HospitalView;

public class HospitalApplication {
	public static void main(String[] args) {
		HospitalView view = new HospitalView();
		HospitalController controller = new HospitalController(view);
		view.setController(controller);

		view.showView();
	}
}
