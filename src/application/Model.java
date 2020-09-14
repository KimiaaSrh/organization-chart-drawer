package application;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import application.TriangleCell;
import home.Controller;
import application.RectangleCell;

public class Model {

    Cell graphParent;

    List<Cell> allCells;
    List<Cell> addedCells;
    List<Cell> removedCells;

    List<Edge> allEdges;
    List<Edge> addedEdges;
    List<Edge> removedEdges;

    Map<String,Cell> cellMap; // <id,cell>

    public Model() {

         graphParent = new Cell( "_ROOT_");

         // clear model, create lists
         clear();
    }
    
    public static int searchIndexByCellId(String parentCellId) {
    	for (int i = 0; i < Controller.controllerAddedCells.size(); i++) {
//    		System.out.println(i);
			if(Controller.controllerAddedCells.get(i).getCellId().toString().equals(parentCellId)) {
//				System.out.println(i+"boo boo");
				return i;
			}
		}
    	return -1;
    }
    
    
    public String searchCellIdByCellText(String inputText) {
    	for (int i = 0; i < Controller.controllerAddedCells.size(); i++) {
			if(Controller.controllerAddedCells.get(i).getCellText().toString().equals(inputText)) {
//				System.out.println(Controller.controllerAddedCells.get(i).getCellText().toString());
				return Controller.controllerAddedCells.get(i).getCellId();
			}
		}
    	return "";
    }

    public void clear() {

        allCells = new ArrayList<>();
        addedCells = new ArrayList<>();
        removedCells = new ArrayList<>();

        allEdges = new ArrayList<>();
       addedEdges = new ArrayList<>();
        removedEdges = new ArrayList<>();

        cellMap = new HashMap<>(); // <id,cell>

    }

    public void clearAddedLists() {
        addedCells.clear();
       addedEdges.clear();
    }

    public List<Cell> getAddedCells() {
        return addedCells;
    }
    
    public int getAddedCellsSize() {
    	return getAddedCells().size();
    }

    public List<Cell> getRemovedCells() {
        return removedCells;
    }

    public List<Cell> getAllCells() {
        return allCells;
    }

    public List<Edge> getAddedEdges() {
        return addedEdges;
    }

    public List<Edge> getRemovedEdges() {
        return removedEdges;
    }

    public List<Edge> getAllEdges() {
        return allEdges;
    }

    public void addCell(String id, CellType type,int index,String userInputText) {

        switch (type) {

        case RECTANGLE:
            RectangleCell rectangleCell = new RectangleCell(id);
            rectangleCell.setUserInputText(userInputText);
            addCell(index,rectangleCell);
            break;

        case TRIANGLE:
            TriangleCell circleCell = new TriangleCell(id);
            circleCell.setUserInputText(userInputText);
            addCell(index,circleCell);
            break;
        case LabelCellType:
        	System.out.println("user input text "+userInputText );
        	LabelCellType labelCell = new LabelCellType(id,userInputText);
        	labelCell.setUserInputText(userInputText);
            addCell(index,labelCell);
            break;
        case ExpertStaffCell:
        	System.out.println("user input text "+userInputText );
        	ExpertStaffCell expertStaffCell = new ExpertStaffCell(id,userInputText);
        	expertStaffCell.setUserInputText(userInputText);
            addCell(index,expertStaffCell);
            break;

        default:
            throw new UnsupportedOperationException("Unsupported type: " + type);
        }
    }

    private void addCell( int index,Cell cell) {

//        if(Controller.isFirstTime==true)addedCells.add(index,cell);
//        else addedCells.set(index,cell);
    	if(Controller.isFirstTime==true)addedCells.add(index,cell);
    	if(Controller.isFirstTime==false) {
    		Controller.controllerAddedCells.set(index, cell);
//    		System.out.println("hello from index " +index);
    	}
        cellMap.put( cell.getCellId(), cell);

    }

    public void addEdge( String sourceId, String targetId) {

        Cell sourceCell = cellMap.get( sourceId);
        Cell targetCell = cellMap.get( targetId);

        Edge edge = new Edge( sourceCell, targetCell);

        addedEdges.add( edge);

    }

    /**
     * Attach all cells which don't have a parent to graphParent 
     * @param cellList
     */
    public void attachOrphansToGraphParent( List<Cell> cellList) {

        for( Cell cell: cellList) {
            if( cell.getCellParents().size() == 0) {
                graphParent.addCellChild( cell);
            }
        }

    }

    /**
     * Remove the graphParent reference if it is set
     * @param cellList
     */
    public void disconnectFromGraphParent( List<Cell> cellList) {

        for( Cell cell: cellList) {
            graphParent.removeCellChild( cell);
        }
    }

    public void merge() {

        // cells
        allCells.addAll( addedCells);
        allCells.removeAll( removedCells);

        addedCells.clear();
        removedCells.clear();

        // edges
        allEdges.addAll( addedEdges);
        allEdges.removeAll( removedEdges);

        addedEdges.clear();
        removedEdges.clear();

    }
}