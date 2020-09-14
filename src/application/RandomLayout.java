package application;

import java.util.List;
import java.util.Random;

import application.Cell;
import application.Graph;
import application.Layout;
import home.Controller;

public class RandomLayout extends Layout {

    Graph graph;

    Random rnd = new Random();
    public static double parentWidth;
    public static double parentHeight;

    public RandomLayout(Graph graph) {

        this.graph = graph;

    }

    public void execute() {

        List<Cell> cells = Controller.controllerAddedCells;
        /*for (int i = 0; i < cells.size(); i++) {
        	Cell cell = cells.get(i);
			System.out.println(cell.getCellId().toString());
		}*/
       
        double firstTopDistance=35;
        double secondTopDistance=firstTopDistance+80;
        double deptsDistance=180.0;
        double subDeptsDistance=70.0;
        double secondFloorDistance=100.0;
        double thirdFloorDistance=200.0;
        double vasati=20;
        int deptSize=cells.size();
        double expp1=450;
        double expp2=510;
        double expp3=570;
        double rootWidth = 0;
        for (int i = 0;i<cells.size(); i++ ) {
        	Cell cell=cells.get(i);
        	if(!(cell.getCellId().equals(""))) {
        		
            	//org name
            	if(i==0) {
//        			System.out.println("umad too");
            		cell.relocate(cell.getLayoutX(), cell.getLayoutY());
            		rootWidth= parentWidth/2+90;
//            		cell.getView().relocate(parentWidth/2-15, firstTopDistance);
            	}
         
            	else {
            		//first dept : be in khater az baghie joda shode ke daghighan zire esme org bshe
            		if(i==1) {
            			((LabelCellType)cell).buttonCurrentlabel.setText(cell.getCellText());
            			cell.relocate(cell.getLayoutX(), cell.getLayoutY());
//            			cell.getView().relocate(rootWidth, secondTopDistance);
            		}
            		//other depts
            		else if(i<7) {
//            			System.out.println(cell.getCellId()+ "i< 7");
            			if(!(cell.getCellId().equals(""))) {
            				//System.out.println(cell.getCellId());
            				((LabelCellType)cell).buttonCurrentlabel.setText(cell.getCellText());
            				if(i%2==0)
                				cell.relocate(cell.getLayoutX(), cell.getLayoutY());
                			else if(i%2==1)
                				cell.relocate(cell.getLayoutX(), cell.getLayoutY());
            			}
            		}
            		// sub depts
            		else if(i<25) {
//            			System.out.println(cell.getCellId()+ "i< 25");
            			if(!(cell.getCellId().equals(""))) {
            				//System.out.println(cell.getCellId());
            				((LabelCellType)cell).buttonCurrentlabel.setText(cell.getCellText());
            				if(i%3==2)
                				cell.relocate(cell.getLayoutX(), cell.getLayoutY());
                			else if(i%3==1)
                				cell.relocate(cell.getLayoutX(), cell.getLayoutY());
                			else if(i%3==0)
                				cell.relocate(cell.getLayoutX(), cell.getLayoutY());
            			}
            		}
            		else if(i<79) {
//            			System.out.println(cell.getCellId()+ "i< 79");
            			if(!(cell.getCellId().equals(""))) {
//            				System.out.println(cell.getCellText());
            				if(i%3==2)
                				cell.relocate(cell.getLayoutX(), cell.getLayoutY());
                			else if(i%3==1) {
//                				System.out.println("im getting cell parent text "+cell.getCellParents().get(0).getCellText().toString());
                				cell.relocate(cell.getLayoutX(), cell.getLayoutY());
                			}
                			else if(i%3==0)
                				cell.relocate(cell.getLayoutX(), cell.getLayoutY());
            			}
            		}
            			
            	}
        	}
        	else {
				cell.setVisible(false);
			}
        	
        	
        }

    }

}
