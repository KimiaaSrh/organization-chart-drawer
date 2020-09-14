package application;

import home.Controller;
import javafx.scene.Group;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Pane;

public class Graph {

    private Model model;

    private Group canvas;
    public static double parentWidthOfGraph=1200 ;

    private ZoomableScrollPane scrollPane;

    MouseGestures mouseGestures;

    /**
     * the pane wrapper is necessary or else the scrollpane would always align
     * the top-most and left-most child to the top and left eg when you drag the
     * top child down, the entire scrollpane would move down
     */
    CellLayer cellLayer;

    public Graph() {

        this.model = new Model();
        
       

        canvas = new Group();
        cellLayer = new CellLayer();

        canvas.getChildren().add(cellLayer);

        mouseGestures = new MouseGestures(this);

        scrollPane = new ZoomableScrollPane(canvas);

        scrollPane.setFitToWidth(true);
        scrollPane.setFitToHeight(true);

    }

    public ScrollPane getScrollPane() {
        return this.scrollPane;
    }

    public Pane getCellLayer() {
        return this.cellLayer;
    }

    public Model getModel() {
    	 int ii = 0;
         for (; ii < 79 && Controller.isFirstTime ; ii++) {
 			if(ii<25)
 				this.model.addCell("", CellType.LabelCellType,ii,"");
 			else
 				this.model.addCell("", CellType.ExpertStaffCell,ii,"");
 		}
         double firstTopDistance=35;
         double secondTopDistance=firstTopDistance+80;
         double deptsDistance=180.0;
         double subDeptsDistance=70.0;
         double secondFloorDistance=100.0;
         double thirdFloorDistance=200.0;
         double vasati=20;
         double expp1=450;
         double expp2=510;
         double expp3=570;
         double rootWidth = 0;
        
         for (int i = 0; i <79 && Controller.isFirstTime; i++) {
          	Controller.controllerAddedCells.add( this.model.addedCells.get(i));
  		}
         for (int i = 0; i < 79; i++) {
        	 if(i==0) {
        		 Controller.controllerAddedCells.get(i).relocate(parentWidthOfGraph/2-15, firstTopDistance);
         		rootWidth= parentWidthOfGraph/2-60;
         	}
      
         	else {
         		//first dept : be in khater az baghie joda shode ke daghighan zire esme org bshe
         		if(i==1) {
         			Controller.controllerAddedCells.get(i).relocate(rootWidth, secondTopDistance);
         		}
         		//other depts
         		else if(i<7) {
         			if(i%2==0)
         				Controller.controllerAddedCells.get(i).relocate(rootWidth+(i/2)*deptsDistance, secondTopDistance);
         			else if(i%2==1)
         				Controller.controllerAddedCells.get(i).relocate(rootWidth-(i/2)*deptsDistance, secondTopDistance);
         		}
         		// sub depts
         		else if(i<25) {
         			if(i%3==2)
         				Controller.controllerAddedCells.get(i).relocate(Controller.controllerAddedCells.get((i-4)/3).getLayoutX(), thirdFloorDistance+vasati);
         			else if(i%3==1)
         				Controller.controllerAddedCells.get(i).relocate(Controller.controllerAddedCells.get((i-4)/3).getLayoutX()-subDeptsDistance, thirdFloorDistance);
         			else if(i%3==0)
         				Controller.controllerAddedCells.get(i).relocate(Controller.controllerAddedCells.get((i-4)/3).getLayoutX()+subDeptsDistance, thirdFloorDistance);
         		}
         		else if(i<79) {
         			if(i%3==2)
         				Controller.controllerAddedCells.get(i).relocate(Controller.controllerAddedCells.get((i-4)/3).getLayoutX(), expp1);
         			else if(i%3==1)
         				Controller.controllerAddedCells.get(i).relocate(Controller.controllerAddedCells.get((i-4)/3).getLayoutX(), expp2);
         			else if(i%3==0)
         				Controller.controllerAddedCells.get(i).relocate(Controller.controllerAddedCells.get((i-4)/3).getLayoutX(), expp3);
         		}
		}
         }
         Controller.isFirstTime=false;
         
        return this.model;
    }

    public void beginUpdate() {
    }

    public void endUpdate() {

        // add components to graph pane
        getCellLayer().getChildren().addAll(model.getAddedEdges());
        getCellLayer().getChildren().addAll(model.getAddedCells());

        // remove components from graph pane
        getCellLayer().getChildren().removeAll(model.getRemovedCells());
        getCellLayer().getChildren().removeAll(model.getRemovedEdges());

        // enable dragging of cells
        for (Cell cell : model.getAddedCells()) {
            mouseGestures.makeDraggable(cell);
        }

        // every cell must have a parent, if it doesn't, then the graphParent is
        // the parent
        getModel().attachOrphansToGraphParent(model.getAddedCells());

        // remove reference to graphParent
        getModel().disconnectFromGraphParent(model.getRemovedCells());

        // merge added & removed cells with all cells
        getModel().merge();

    }

    public double getScale() {
        return this.scrollPane.getScaleValue();
    }
}