package home;
import application.Cell;
import application.CellType;
import application.Graph;
import application.LabelCellType;
import application.Model;
import application.Layout;
import application.RandomLayout;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.awt.ScrollPane;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import application.CellType;
import application.Graph;
import application.Layout;
import application.Model;
import application.RandomLayout;

public class Controller implements Initializable {
	
	
	
	
	DatabaseController database=new DatabaseController();
	
	@FXML
    private AnchorPane parentAnchroPane;

    @FXML
    private VBox pnlDeptItems = null;
    
    @FXML
    private VBox pnISubDeptItems = null;
    
    @FXML
    private VBox pnIDeptExpertsItems = null;
    
    @FXML
    private VBox pnIEmployeeItems = null;
    
    @FXML
	private ComboBox<String> deptsNameCombobox;
    
    @FXML
	private TextField deptNameTextField;
    
    
    @FXML
   	private TextField subDeptNames;
    
    @FXML
   	private TextField OrgNameTextField;
    
    @FXML
   	private TextField deptExpertsField;
    
    @FXML
   	private TextField firstAndLastName;
    
    @FXML
   	private TextField dateOfEntry;
    
    @FXML
    private Button btnWelcome;
    
    @FXML
    private Button saveToDb;
    
    @FXML
    private Button saveToDb2;
    
    @FXML
    private Button saveToDb3;
    
    @FXML
    private Button saveToDbOrgName;
    
    
    @FXML
    private Button globalSave;

    @FXML
    private Button btnOrgInfo;

    @FXML
    private Button btnEmployeeInfo;

    @FXML
    private Button btnDeptsInfo;

    @FXML
    private Button btnSubDeptInfo;

    @FXML
    private Button btnOrgTree;
    
    @FXML
    private Button btnEditInfo;
    
    @FXML
    private Button btnDeptExperts;

    @FXML
    private Pane pnlWelcome;

    @FXML
    private Pane pnlOrgInfo;

    @FXML
    private Pane pnlDeptInfo;

    @FXML
    private Pane pnlSubdeptInfo;
    
    @FXML
    private Pane pnlDeptExperts;

    @FXML
    private Pane pnlEmployeeInfo;


    @FXML
    private Pane pnlOrgChart;
    
    @FXML
    private Pane pnlEditInfo;
    
    @FXML
    private Label left1deptinfo;
    @FXML
    private Label left2deptinfo;
    @FXML
    private Label left3deptinfo;
    @FXML
    private Label left4deptinfo;
    
    @FXML
    private ImageView img; 
    
    
    
    @FXML
    private Label left1subdeptinfo;
    @FXML
    private Label left2subdeptinfo;
    @FXML
    private Label left3subdeptinfo;
    @FXML
    private Label left4subdeptinfo;
    
    
    @FXML
    private Label left1deptsexperts;
    @FXML
    private Label left2deptsexperts;
    @FXML
    private Label left3deptsexperts;
    @FXML
    private Label left4deptsexperts;
    
    
    @FXML
    private Label left1employeeinfo;
    @FXML
    private Label left2employeeinfo;
    @FXML
    private Label left3employeeinfo;
    @FXML
    private Label left4employeeinfo;
    
    @FXML
    private Label toplabelorgpane;
    
    @FXML
    private Label toplabeldeptspane;
    
    @FXML
    private Label toplabelsubdeptspane;
    
    @FXML
    private Label toplabeldeptexperts;
    
    @FXML
    private Label toplabelemployees;
    
    ObservableList<String> deptsComboboxItems=FXCollections.observableArrayList();
    ObservableList<String> subDeptsAndDeptsComboboxItems=FXCollections.observableArrayList();
    ObservableList<String> expertsAndsubDeptsAndDeptsComboboxItems=FXCollections.observableArrayList();
    
    TextField[] deptNamesArray = new TextField[20];
    TextField[] subDeptNamesArray = new TextField[30];
    TextField[] deptExpertsArray = new TextField[80];
    TextField[] firstAndLastNameArray = new TextField[80];
    TextField[] dateOfEntryArray = new TextField[80];

    Node[] deptNodes = new Node[20];
    Node[] subDeptNodes = new Node[30];
    Node[] employeeNodes = new Node[80];
    Node[] deptExpertsNodes = new Node[80];
    
    int maxNumberOfSubdepts=3;
    int maxNumberOfExperts=3;
    int maxNumberOfDepts=6;
    public static boolean isFirstTime=true;
    int[] deptsCapacity= {3,3,3,3,3,3};
    int[] subdeptsCapacity={3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3};
    
    int whichSave=0;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
//    	toplabel.setContentDisplay(ContentDisplay.RIGHT);
    	
        for (int i = 0; i < deptNodes.length; i++) {
            try {

                final int j = i;
                deptNodes[i] = FXMLLoader.load(getClass().getResource("Depts.fxml"));
                //give the items some effect

                deptNodes[i].setOnMouseEntered(event -> {
                	deptNodes[j].setStyle("-fx-background-color : #0A0E3F");
                });
                deptNodes[i].setOnMouseExited(event -> {
                	deptNodes[j].setStyle("-fx-background-color : #02030A");
                });
                pnlDeptItems.getChildren().addAll(deptNodes[i]);


              left4deptinfo.setText("نام دپارتمان");
              left1deptinfo.setText("");
              left2deptinfo.setText("");
              left3deptinfo.setText("");
              toplabeldeptspane.setText(" لطفا نام دپارتمان را در جای خالی پر کنید ");
              toplabeldeptspane.setAlignment(Pos.CENTER_RIGHT);
              
                
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        for (int i = 0; i < subDeptNodes.length; i++) {

                final int j = i;
                subDeptNodes[i] = addHBox();
               
                //give the items some effect

                subDeptNodes[i].setOnMouseEntered(event -> {
                	subDeptNodes[j].setStyle("-fx-background-color : #0A0E3F");
                });
                subDeptNodes[i].setOnMouseExited(event -> {
                	subDeptNodes[j].setStyle("-fx-background-color : #02030A");
                });
               
                pnISubDeptItems.getChildren().add(subDeptNodes[i]);

                left4subdeptinfo.setText("نام دپارتمان");
            	left3subdeptinfo.setText("نام زیر دپارتمان");
                left1subdeptinfo.setText("");
                left2subdeptinfo.setText("");
                toplabelsubdeptspane.setText(" لطفا  پس از انتخاب دپارتمان , نام زیر دپارتمان مربوطه را در جای خالی پر کنید ");
                toplabelsubdeptspane.setAlignment(Pos.CENTER_RIGHT);

        }
        
        for (int i = 0; i < employeeNodes.length; i++) {

                final int j = i;
                employeeNodes[i] = addHBox3();

                //give the items some effect

                employeeNodes[i].setOnMouseEntered(event -> {
                	employeeNodes[j].setStyle("-fx-background-color : #0A0E3F");
                });
                employeeNodes[i].setOnMouseExited(event -> {
                	employeeNodes[j].setStyle("-fx-background-color : #02030A");
                });
                pnIEmployeeItems.getChildren().add(employeeNodes[i]);
                
              left4employeeinfo.setText("نام");
              left3employeeinfo.setText(" نام خانوادگی");
              left2employeeinfo.setText("نوع کارشناس");
              left1employeeinfo.setText("کد پرسنلی ");
              toplabelemployees.setText(" لطفا  پس از  درج نام و نام خانوادگی کارمند  مورد نظر  ,عنوان کارشناس  مربوطه را انتخاب کنید ");
              toplabelemployees.setAlignment(Pos.CENTER_RIGHT);
        }
        
        for (int i = 0; i < deptExpertsNodes.length; i++) {
                final int j = i;
                deptExpertsNodes[i] = addHBox2();

                //give the items some effect

                deptExpertsNodes[i].setOnMouseEntered(event -> {
                	deptExpertsNodes[j].setStyle("-fx-background-color : #0A0E3F");
                });
                deptExpertsNodes[i].setOnMouseExited(event -> {
                	deptExpertsNodes[j].setStyle("-fx-background-color : #02030A");
                });
                
                pnIDeptExpertsItems.getChildren().add(deptExpertsNodes[i]);

            	left4deptsexperts.setText("عنوان زیر دپارتمان");
                left3deptsexperts.setText("عنوان کارشناس");
                left1deptsexperts.setText("");
                left2deptsexperts.setText("");
                toplabeldeptexperts.setText(" لطفا  پس از انتخاب زیر دپارتمان مورد نظر  ,عنوان کارشناس  مربوطه را در جای خالی پر کنید ");
                toplabeldeptexperts.setAlignment(Pos.CENTER_RIGHT);
        }
        //deptsNameCombobox.setItems(deptsComboboxItems);
    }
    public static List<Cell> controllerAddedCells= new ArrayList<>();
    Graph graph = new Graph();
	Model model = graph.getModel();

    public void handleClicks(ActionEvent actionEvent) throws SQLException {

        
        if(actionEvent.getSource() == globalSave && whichSave==0) {
        	model.addCell("orgName", CellType.LabelCellType,0,OrgNameTextField.getText().toString());

        }
        
    	if (actionEvent.getSource() == globalSave && whichSave==1) {
    		for (int i = 0; i < deptNodes.length; i++) {
    			deptNamesArray[i]=(TextField)deptNodes[i].lookup("#deptNameTextField");
			}
    		for(int i = 0; i < deptNamesArray.length; i++) {
    			if(!(deptNamesArray[i].getText().equals(""))) {
    				model.addCell("dept"+Integer.valueOf(i+1), CellType.LabelCellType,i+1,deptNamesArray[i].getText().toString());
//    				((LabelCellType)(controllerAddedCells.get(Model.searchIndexByCellId("dept"+Integer.valueOf(i+1)))).buttonCurrentlabel.setText(deptNamesArray[i].getText().toString());
                	model.addEdge("orgName", "dept"+Integer.valueOf(i+1));
        			deptsComboboxItems.add(deptNamesArray[i].getText());
    			}
    			
    		}
	
        }

    	
    	if (actionEvent.getSource() == globalSave && whichSave==2) {
    		String samp;
    		for (int i = 0; i < subDeptNodes.length; i++) {
        		subDeptNamesArray[i]=(TextField)subDeptNodes[i].lookup("#subDeptNames");
        		if(!(subDeptNamesArray[i].getText().equals(""))) {
        			samp=((ComboBox<String>)subDeptNodes[i].lookup("#emailComboBox")).getValue();
        			int indexOfParent=model.searchIndexByCellId(model.searchCellIdByCellText(samp));
        			if(deptsCapacity[indexOfParent-1]==3) {
            			model.addCell("subdept"+Integer.valueOf(i+1), CellType.LabelCellType,(3*indexOfParent+4),subDeptNamesArray[i].getText().toString());
            			deptsCapacity[indexOfParent-1]--;
        			}
        			else if(deptsCapacity[indexOfParent-1]==2) {
            			model.addCell("subdept"+Integer.valueOf(i+1), CellType.LabelCellType,(3*indexOfParent+5),subDeptNamesArray[i].getText().toString());
            			deptsCapacity[indexOfParent-1]--;
        			}
        			else if(deptsCapacity[indexOfParent-1]==1) {
            			model.addCell("subdept"+Integer.valueOf(i+1), CellType.LabelCellType,(3*indexOfParent+6),subDeptNamesArray[i].getText().toString());
            			deptsCapacity[indexOfParent-1]--;
        			}
        			model.addEdge("dept"+Integer.toString(indexOfParent), "subdept"+Integer.toString(i+1));
        			String gac=subDeptNamesArray[i].getText();
            		subDeptsAndDeptsComboboxItems.add(gac+" "+samp);
        		}
    		}
//    		System.out.println("array list from subdepts");
//    		for (int i = 0; i <79; i++) {
//    			System.out.println(i+ " "+controllerAddedCells.get(i).getCellId());
//    		}
        }
    	
    	
    	if (actionEvent.getSource() == globalSave && whichSave==3) {
    		String sag;
    		for (int i = 0; i < deptExpertsNodes.length; i++) {
    			deptExpertsArray[i]=(TextField)deptExpertsNodes[i].lookup("#deptExpertsField");
    			if(!(deptExpertsArray[i].getText().equals(""))) {
        			sag=((ComboBox<String>)deptExpertsNodes[i].lookup("#emailComboBox2")).getValue();
        			String[] parts = sag.split(" ");
        			int indexOfParent=model.searchIndexByCellId(model.searchCellIdByCellText(parts[0]));
        			System.out.println("str " +parts[0]+" index of parent : "+indexOfParent);
        			if(subdeptsCapacity[indexOfParent-7]==3) {
        				model.addCell("exp"+ Integer.toString(i+1), CellType.ExpertStaffCell,(3*indexOfParent+4),deptExpertsArray[i].getText().toString());
        				subdeptsCapacity[indexOfParent-7]--;
        			}
        			else if(subdeptsCapacity[indexOfParent-7]==2) {
        				model.addCell("exp"+ Integer.toString(i+1), CellType.ExpertStaffCell,(3*indexOfParent+5),deptExpertsArray[i].getText().toString());
        				subdeptsCapacity[indexOfParent-7]--;
        			}
        			else if(subdeptsCapacity[indexOfParent-7]==1) {
        				model.addCell("exp"+ Integer.toString(i+1), CellType.ExpertStaffCell,(3*indexOfParent+6),deptExpertsArray[i].getText().toString());
        				subdeptsCapacity[indexOfParent-7]--;
        			}
        	    	model.addEdge(controllerAddedCells.get(indexOfParent).getCellId(), "exp"+Integer.toString(i+1));
        			String gac=deptExpertsArray[i].getText();
        			expertsAndsubDeptsAndDeptsComboboxItems.add(gac+" "+sag);
    			}		
    		}
    		
        }
        graph.endUpdate();

    	
        if (actionEvent.getSource() == btnWelcome) {
        	pnlWelcome.setStyle("-fx-background-color : #02030A");
        	pnlWelcome.toFront();
        }
        else if (actionEvent.getSource() ==btnSubDeptInfo ) {
        	pnlSubdeptInfo.setStyle("-fx-background-color : #02030A");
        	pnlSubdeptInfo.toFront();
        	whichSave=2;
        	left1subdeptinfo.setText("نام دپارتمان");
        	left1subdeptinfo.setText("نام زیر دپارتمان");
        	left1subdeptinfo.setText("");
        	left1subdeptinfo.setText("");
        	toplabelsubdeptspane.setText(" لطفا  پس از انتخاب دپارتمان , نام زیر دپارتمان مربوطه را در جای خالی پر کنید ");
        	toplabelsubdeptspane.setAlignment(Pos.CENTER_RIGHT);
        	
        }
        else if (actionEvent.getSource() == btnDeptsInfo) {
        	pnlDeptInfo.setStyle("-fx-background-color : #02030A");
        	pnlDeptInfo.toFront();
        	whichSave=1;
        	left4deptinfo.setText("نام دپارتمان");
            left1deptinfo.setText("");
            left2deptinfo.setText("");
            left3deptinfo.setText("");
            toplabeldeptspane.setText(" لطفا نام دپارتمان را در جای خالی پر کنید ");
            toplabeldeptspane.setAlignment(Pos.CENTER_RIGHT);
        }
        else if(actionEvent.getSource()==btnOrgInfo)
        {
        	pnlOrgInfo.setStyle("-fx-background-color : #02030A");
        	pnlOrgInfo.toFront();
        	whichSave=0;
        }
        else if (actionEvent.getSource() == btnDeptExperts) {
        	pnlDeptExperts.setStyle("-fx-background-color : #02030A");
        	pnlDeptExperts.toFront();
        	whichSave=3;
        	left4deptsexperts.setText("عنوان زیر دپارتمان");
            left3deptsexperts.setText("عنوان کارشناس");
            left1deptsexperts.setText("");
            left2deptsexperts.setText("");
            toplabeldeptexperts.setText(" لطفا  پس از انتخاب زیر دپارتمان مورد نظر  ,عنوان کارشناس  مربوطه را در جای خالی پر کنید ");
            toplabeldeptexperts.setAlignment(Pos.CENTER_RIGHT);
        }
        else if (actionEvent.getSource() == btnEmployeeInfo) {
        	pnlEmployeeInfo.setStyle("-fx-background-color : #02030A");
        	pnlEmployeeInfo.toFront();
        	whichSave=4;
        	left4employeeinfo.setText("نام");
            left3employeeinfo.setText(" نام خانوادگی");
            left2employeeinfo.setText("نوع کارشناس");
            left1employeeinfo.setText("کد پرسنلی ");
            toplabelemployees.setText(" لطفا  پس از  درج نام و نام خانوادگی کارمند  مورد نظر  ,عنوان کارشناس  مربوطه را انتخاب کنید ");
            toplabelemployees.setAlignment(Pos.CENTER_RIGHT);
        }
        else if (actionEvent.getSource() == btnEditInfo) {
        	pnlEditInfo.setStyle("-fx-background-color : #02030A");
        	pnlEditInfo.toFront();
        }
        else if(actionEvent.getSource()==btnOrgTree)
        {
        	try {
        		Stage primaryStage = new Stage();
    			BorderPane root = new BorderPane();

//    	        graph = new Graph();

    	        root.setCenter(graph.getScrollPane());

    	        Scene scene = new Scene(root, 1200, 750);
    	        RandomLayout.parentWidth=scene.getWidth();
    	        Graph.parentWidthOfGraph=scene.getWidth();
    	        RandomLayout.parentHeight=scene.getHeight();
    	        scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());

    	        primaryStage.setScene(scene);
    	        primaryStage.show();
    	        /*int deptCounter=0;
    	        for (; deptCounter < deptNamesArray.length; deptCounter++) {
					while (!(deptNamesArray[deptCounter].getText().equals("")))
						deptCounter++;
				}
    	        
    	        int subDeptCounter=0;
    	        for (; subDeptCounter < subDeptNamesArray.length; subDeptCounter++) {
					while (!(subDeptNamesArray[subDeptCounter].getText().equals("")))
						subDeptCounter++;
				}
    	        
    	        int expertCounter=0;
    	        for (; expertCounter < deptExpertsArray.length; expertCounter++) {
					while (!(deptExpertsArray[expertCounter].getText().equals("")))
						expertCounter++;
				}
    	        addGraphComponents(deptCounter,subDeptCounter,expertCounter);*/
//    	        addGraphComponents(4,10,20);

    	        Layout layout = new RandomLayout(graph);
    	        layout.execute();
    		} catch(Exception e) {
    			e.printStackTrace();
    		}
        }
    }
    private void addGraphComponents(int numberOfDepts,int numberOfSubDepts,int numberOfExperts) {

//		Model model = graph.getModel();
//
//        graph.beginUpdate();

//        model.addCell("orgName", CellType.LabelCellType);
        
//        for (int i = 1; i < 7; i++) {
//        	if(i< numberOfDepts+1) {
//        		//System.out.println("entered to number of depts");
//        		model.addCell("dept"+Integer.valueOf(i), CellType.LabelCellType);
//            	model.addEdge("orgName", "dept"+Integer.valueOf(i));
//        	}
//        	else {
//        		model.addCell("", CellType.LabelCellType);
//        		//System.out.println("entered to number of depts esle");
//        	}
//        	
//		}
        
//        int m=1;
//        int j = 1;
//        int x=1;
//        while ( j <19) {
//        	while( m < numberOfDepts+1 ) {
//        		x=1;
//        		while(x<maxNumberOfSubdepts+1) {
//        			if( j <numberOfSubDepts+1) {
//        				System.out.println("umad too subdept if");
//        				System.out.println(j);
//        				model.addCell("subdept"+Integer.valueOf(j), CellType.LabelCellType);
//            			model.addEdge("dept"+Integer.toString(m), "subdept"+Integer.toString(j));
//        			}
//        			else 
//        				model.addCell("", CellType.LabelCellType);
//        			j++;
//            		x++;
//                	
//        		}
//        		m++;
//			}
//			System.out.println("umad too subdept else");
//			System.out.println(j);
//			model.addCell("", CellType.LabelCellType);
//			j++;
//		
//		}
//        
//        m=1;
//        j = 1;
//        x=1;
//        while ( j <55) {
//        	//System.out.println();
//        	while( m <maxNumberOfDepts*maxNumberOfSubdepts+1 ) {
//        		x=1;
//        		while(x<maxNumberOfExperts+1) {
//        			if(j<numberOfExperts+1) {
//        				//System.out.println("sag");
//        				model.addCell("exp"+ Integer.toString(j), CellType.ExpertStaffCell);
//                		model.addEdge("subdept"+Integer.toString(m), "exp"+Integer.toString(j));
//        			}
//        			else 
//        				model.addCell("", CellType.ExpertStaffCell);
//            		j++;
//            		x++;
//        		}
//        		m++;
//			}
//			model.addCell("", CellType.ExpertStaffCell);
//			j++;
//		}
//        
//        for (int k = 0; k < model.getAddedCells().size(); k++) {
//			System.out.println(model.getAddedCells().get(k).getCellId());
//		}
//       
//        graph.endUpdate();

    }
	
    public HBox addHBox() {
	    HBox hbox = new HBox();
	    hbox.setPadding(new Insets(15, 12, 15, 12));
	    hbox.setSpacing(150);
	    hbox.setAlignment(Pos.CENTER_RIGHT);
	    hbox.setStyle("-fx-background-color: #02030A;");

	    TextField buttonCurrent = new TextField("");
	    buttonCurrent.setId("subDeptNames");
	    buttonCurrent.setPrefSize(100, 20);
	    buttonCurrent.setStyle("-fx-background-color: #d4d8f7;");

	    ComboBox emailComboBox = new ComboBox();
	    emailComboBox.setId("emailComboBox");
        emailComboBox.setItems(deptsComboboxItems);
        emailComboBox.setPromptText("انتخاب کنید");
        emailComboBox.setEditable(true);
        emailComboBox.setPrefSize(100, 20);
        hbox.setMargin(emailComboBox, new Insets(0, 150, 0, 0));
	    hbox.getChildren().addAll(buttonCurrent, emailComboBox);

	    return hbox;
	}
    
    public HBox addHBox2() {
	    HBox hbox = new HBox();
	    hbox.setPadding(new Insets(15, 12, 15, 12));
	    hbox.setSpacing(150);
	    hbox.setAlignment(Pos.CENTER_RIGHT);
	    hbox.setStyle("-fx-background-color: #02030A;");

	    TextField buttonCurrent = new TextField("");
	    buttonCurrent.setId("deptExpertsField");
	    buttonCurrent.setPrefSize(100, 20);
	    buttonCurrent.setStyle("-fx-background-color: #d4d8f7;");

	    ComboBox emailComboBox = new ComboBox();
	    emailComboBox.setId("emailComboBox2");
        emailComboBox.setItems(subDeptsAndDeptsComboboxItems);
        emailComboBox.setPromptText("انتخاب کنید");
        emailComboBox.setEditable(true);
        emailComboBox.setPrefSize(100, 20);
        hbox.setMargin(emailComboBox, new Insets(0, 150, 0, 0));
	    hbox.getChildren().addAll(buttonCurrent, emailComboBox);

	    return hbox;
	}
    
    public HBox addHBox3() {
	    HBox hbox = new HBox();
	    hbox.setPadding(new Insets(15, 12, 15, 12));
	    hbox.setSpacing(40);
	    hbox.setAlignment(Pos.CENTER);
	    hbox.setStyle("-fx-background-color: #02030A;");

	    TextField buttonCurrent = new TextField("");
	    buttonCurrent.setPrefSize(100, 20);
	    buttonCurrent.setStyle("-fx-background-color: #d4d8f7;");
	    
	    TextField buttonCurrent2 = new TextField("");
	    buttonCurrent.setPrefSize(100, 20);
	    buttonCurrent2.setStyle("-fx-background-color: #d4d8f7;");
	    
	    TextField buttonCurrent3 = new TextField("");
	    buttonCurrent.setPrefSize(100, 20);
	    buttonCurrent3.setStyle("-fx-background-color: #d4d8f7;");

	    ComboBox emailComboBox = new ComboBox();
	    emailComboBox.setId("emailComboBox3");
        emailComboBox.setItems(expertsAndsubDeptsAndDeptsComboboxItems);
        emailComboBox.setPromptText("انتخاب کنید");
        emailComboBox.setEditable(true);
        emailComboBox.setPrefSize(100, 20);
	    hbox.getChildren().addAll(buttonCurrent, emailComboBox,buttonCurrent2,buttonCurrent3);

	    return hbox;
	}
    
    
}
