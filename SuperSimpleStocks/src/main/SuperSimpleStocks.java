package main;

import main.controller.SuperSimpleStocksController;
import main.model.SuperSimpleStocksModel;
import main.view.SuperSimpleStocksView;

/**
 * The SuperSimpleStocks application main class.
 * This application is loosely based around an MVC design pattern.
 * 
 * @author andersarchibald
 * @version 1.0
 *
 */
public class SuperSimpleStocks {
	
	private SuperSimpleStocksModel model = new SuperSimpleStocksModel();
	private SuperSimpleStocksController controller = new SuperSimpleStocksController();
	private SuperSimpleStocksView view = new SuperSimpleStocksView();
	
	public SuperSimpleStocks(){
		init();
	}
	
	public static void main(String[]  args){
		SuperSimpleStocks sss = new SuperSimpleStocks();
	}
	
	
	/**
	 * This method must be called to initialize the application.
	 */
	private void init(){
		model.initialiseStocks();
		controller.setModel(model);
		controller.setView(view);
		view.setController(controller);
		view.displayMenu();
	}

	//GETTERS AND SETTERS added as a testing convenience
	public SuperSimpleStocksModel getModel() {
		return model;
	}

	public void setModel(SuperSimpleStocksModel model) {
		this.model = model;
	}

	public SuperSimpleStocksController getController() {
		return controller;
	}

	public void setController(SuperSimpleStocksController controller) {
		this.controller = controller;
	}

	public SuperSimpleStocksView getView() {
		return view;
	}

	public void setView(SuperSimpleStocksView view) {
		this.view = view;
	}

}
