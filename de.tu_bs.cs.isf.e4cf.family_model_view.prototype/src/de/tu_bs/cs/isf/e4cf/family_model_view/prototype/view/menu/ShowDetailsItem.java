package de.tu_bs.cs.isf.e4cf.family_model_view.prototype.view.menu;

import de.tu_bs.cs.isf.e4cf.core.compare.parts.detail_view.util.DetailViewStringTable;
import de.tu_bs.cs.isf.e4cf.core.compare.string_table.E4CompareEventTable;
import de.tu_bs.cs.isf.e4cf.core.util.ServiceContainer;
import de.tu_bs.cs.isf.e4cf.family_model_view.prototype.stringtable.FamilyModelViewEvents;
import de.tu_bs.cs.isf.e4cf.family_model_view.prototype.stringtable.FamilyModelViewFiles;
import de.tu_bs.cs.isf.e4cf.family_model_view.prototype.view.FamilyModelView;
import de.tu_bs.cs.isf.e4cf.family_model_view.prototype.view.elements.FXFamilyModelElement;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ShowDetailsItem extends MenuItem {

	private ServiceContainer serviceContainer;
	private FXFamilyModelElement selectedElement;

	public ShowDetailsItem(FamilyModelView view, ServiceContainer serviceContainer) {
		this.serviceContainer = serviceContainer;
		this.selectedElement = view.getFamilyModelTree().getTree().getRoot().getValue();
		setText("Show Details");
        
		// set the icon
        Image image = new Image(FamilyModelViewFiles.FV_DETAIL_16);
        ImageView imView = new ImageView(image);
        setGraphic(imView);
        
        // always keep track on the current selection
        view.getFamilyModelTree().getTree().getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
        	this.selectedElement = newValue != null ? newValue.getValue() : null;
        });
	}
	
	@Override
	public void fire() {
		if (selectedElement != null) {
			serviceContainer.eventBroker.send(FamilyModelViewEvents.EVENT_SHOW_DETAILS, selectedElement);			
		}
		
		super.fire();
	}

	
	
}
