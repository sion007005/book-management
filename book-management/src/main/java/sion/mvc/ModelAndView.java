package sion.mvc;

public class ModelAndView {
	private Model model = new Model();
	private String viewName;

	public ModelAndView() {
	}
	
	public ModelAndView(String viewName) {
		this.viewName = viewName;
	}
	
	public String getViewName() {
		return viewName;
	}

	public void setViewName(String viewName) {
		this.viewName = viewName;
	}

	public Model getModel() {
		return model;
	}

	public void setModel(Model model) {
		this.model = model;
	}

	public void addObject(String key, Object value) {
		model.put(key, value);
	}

}
