package com.example.se302_project;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.collections.FXCollections;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.effect.BoxBlur;
import javafx.scene.layout.*;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;



import javafx.scene.media.*;


class Config {
        private String type;
        private String config;

        public Config(String type, String config) {
                this.type = type;
                this.config = config;
        }

        public String getType() {
                return type;
        }

        public String getConfig() {
                return config;
        }
}
    
public class MainController {


        @FXML
        private MediaView mediaView;

        @FXML
        private HBox mediaHbox;


        @FXML
        private Button AddAttribute;
        @FXML
        private Button SmallLectureButton;
        @FXML
        private Button SmallPLButton;
        @FXML
        private HBox AddLectureBox;
        @FXML
        private HBox EditLectureHBox;
        @FXML
        private GridPane EditLectureGrid;
        @FXML
        private GridPane ArgumentsProjectGrid;

        @FXML
        private HBox EditProjectHBox;

        @FXML
        private HBox DetailedEvaluationHbox;

        @FXML
        private TableView DetailedEvaluationTable;

        @FXML
        private TableColumn inputColumn;


        @FXML
        private TableColumn poutputColumn;


        @FXML
        private TableColumn EvaluationRunStatus;


        @FXML
        private TableColumn EvaluationRunOutput;

        @FXML
        private HBox EditProgLangHBox;

        @FXML
        private HBox StudentsHbox;

        @FXML
        private Button AddLectureButton;

        @FXML
        private GridPane AddLectureGrid;

        @FXML
        private HBox AddPLBox;

        @FXML
        private Button AddPLButton;

        @FXML
        private GridPane AddPLGrid;

        @FXML
        private HBox AddProjectBox;

        @FXML
        private Button AddProjectButton;

        @FXML
        private Button EditProgLangButton;


        @FXML
        private GridPane AddProjectGrid;

        @FXML
        private Button GoToAddLectureButton;

        @FXML
        private Button GoToAddPLButton;
        @FXML
        private Button StudentOpenZip;
        @FXML
        private Button StudentRun;

        @FXML
        private Button GoToAddProjectButton;

        @FXML
        private Button LectureButton;

        @FXML
        private Button EditLectureConfirm;

        @FXML
        private Button EditLectureButton;

        @FXML
        private Button CloseEditLecture;

        @FXML
        private Button CloseEditProgLang;
        @FXML
        private Button EditProgLangConfirm;

        @FXML
        private Label StudentIDLabel;

        @FXML
        private TableColumn LectureGoColumn;

        @FXML
        private GridPane LectureGrid;
        @FXML
        private GridPane StudentGrid;
        @FXML
        private GridPane EditLectureoldvalue;
        @FXML
        private GridPane EditLecturenewvalue;

        @FXML
        private TableColumn LectureNameColumn;

        @FXML
        private TableView LectureTableView;

        @FXML
        private TableColumn LectureTrashColumn;

        @FXML
        private HBox LecturesHBox;

        @FXML
        private TableColumn PLGoColumn;

        @FXML
        private GridPane PLGrid;

        @FXML
        private TableColumn PLNameColumn;

        @FXML
        private TableView PLTableView;

        @FXML
        private TableColumn PLTrashColumn;

        @FXML
        private HBox PL_HBox;

        @FXML
        private Button ProgrammingLanguageButton;

        @FXML
        private TableColumn ProjectGoColumn;

        @FXML
        private GridPane ProjectGrid;


        @FXML
        private GridPane EditProgLangnewvalue;


        @FXML
        private GridPane EditProgLangoldvalue;


        @FXML
        private TableColumn ProjectNameColumn;

        @FXML
        private TableView ProjectTableView;

        @FXML
        private TableColumn ProjectTrashColumn;

        @FXML
        private HBox ProjectsHBox;

        @FXML
        private HBox allHbox;

        @FXML
        private VBox drawerLong;

        @FXML
        private VBox drawerShort;

        @FXML
        private StackPane drawerStackPane;

        @FXML
        private ImageView ellipse1;

        @FXML
        private ImageView ellipse2;

        @FXML
        private HBox firstEllipses;

        @FXML
        private Button leftBarButton;

        @FXML
        private Button leftBarButton1;

        @FXML
        private VBox originalResumeVBox;

        @FXML
        private VBox originalResumeVBox1;

        @FXML
        private VBox originalResumeVBox2;

        @FXML
        private HBox secondEllipses;

        @FXML
        private VBox templateAttributeView;

        @FXML
        private VBox templateAttributeView1;

        @FXML
        private VBox templateAttributeView11;

        @FXML
        private VBox templateAttributeView2;

        @FXML
        private VBox templateNameVBox;

        @FXML
        private VBox templateNameVBox1;

        @FXML
        private VBox templateNameVBox2;

        @FXML
        private VBox templatePane;

        @FXML
        private VBox templatePane1;

        @FXML
        private VBox templatePane2;

        @FXML
        private HBox thirdEllipses;

        @FXML
        private TableView StudentTableView;
        @FXML
        private TableColumn StudentIDColumn;
        @FXML
        private TableColumn StudentNameColumn;
        @FXML
        private TableColumn StudentGradeColumn;
        @FXML
        private TableColumn StudentGoColumn;


        int lec_id = -1;
        int pl_id = -1;
        int project_id = -1;
        private Media media;
        private MediaPlayer mediaPlayer;

        public void initialize() throws SQLException, IOException {
                if (!new File("info.db").exists()) {
                        try {
                                mediaHbox.setVisible(true);
                                allHbox.setVisible(false);

                                String file_path = "images/team3.mp4";
                                media = new Media(getClass().getResource(file_path).toExternalForm());
                                mediaPlayer = new MediaPlayer(media);

                                MediaPlayer.Status status = mediaPlayer.getStatus();
                                if (status == MediaPlayer.Status.UNKNOWN) {
                                        System.out.println("Media Player trying to play");

                                }

                                mediaView.setMediaPlayer(mediaPlayer);
                                mediaPlayer.play();

                                mediaPlayer.setOnEndOfMedia(() -> {
                                        System.out.println("finished successfully");

                                        mediaHbox.setVisible(false);
                                        allHbox.setVisible(true);

                                        openLectureScreen();
                                });

                        } catch (Exception e) {
                                System.out.println("naneyi yedi");
                                e.printStackTrace();
                        }
                } else {
                        openLectureScreen();
                }

                firstEllipses.widthProperty().addListener((obs, oldVal, newVal) -> {
                        if (firstEllipses.getWidth() < 1400)
                                shortDrawer();
                        else
                                longDrawer();
                });

                LectureTableView.getSelectionModel().selectedItemProperty().addListener((observable, oldvalue, newValue) -> {
                        try {
                                selectFromLectureTable();
                        } catch (SQLException e) {
                                throw new RuntimeException(e);
                        } catch (IOException e) {
                                throw new RuntimeException(e);
                        }
                });
        }


        @FXML
        public void openLectureScreen() {
                LecturesHBox.setVisible(true);
                ProjectsHBox.setVisible(false);
                LecturesHBox.setEffect(null);
                StudentsHbox.setVisible(false);
                EditProgLangHBox.setVisible(false);
                EditLectureHBox.setVisible(false);

                PL_HBox.setVisible(false);
                AddProjectBox.setVisible(false);
                AddLectureBox.setVisible(false);
                AddPLBox.setVisible(false);

                firstEllipses.setVisible(false);
                secondEllipses.setVisible(true);
                thirdEllipses.setVisible(false);
                mediaHbox.setVisible(false);
                DetailedEvaluationHbox.setVisible(false);


                String path = "images/trash.png";
                String path2 = "images/Go.png";
                Image image = new Image(getClass().getResource(path).toExternalForm());
                Image image2 = new Image(getClass().getResource(path2).toExternalForm());

                ObservableList<TableShow> LectureList = FXCollections
                        .observableArrayList();

                LectureNameColumn.setCellValueFactory(new PropertyValueFactory<TableShow, String>("name"));
                LectureTrashColumn.setCellValueFactory(new PropertyValueFactory<TableShow, ImageView>("image"));
                LectureGoColumn.setCellValueFactory(new PropertyValueFactory<TableShow, ImageView>("image2"));

                for (LectureConfig lecture_config : DBConnector.getInstance().getAllLectureConfigObjects()) {
                        LectureList.add(new TableShow(lecture_config.getLecture_id(), lecture_config.getLecture_Name(), new ImageView(image), new ImageView(image2)));// new ImageView(image),new ImageView(image2)));
                }

                LectureTableView.setItems(LectureList);
        }


        @FXML
        public void openAddLectureScreen() throws SQLException {
                LecturesHBox.setVisible(true);
                LecturesHBox.setEffect(null);
                ProjectsHBox.setVisible(false);
                AddProjectBox.setVisible(false);
                AddLectureBox.setVisible(true);
                BoxBlur blur = new BoxBlur();
                blur.setWidth(10);
                blur.setHeight(10);
                blur.setIterations(3);
                LecturesHBox.setEffect(blur);

                firstEllipses.setVisible(false);
                secondEllipses.setVisible(false);
                thirdEllipses.setVisible(false);
                DetailedEvaluationHbox.setVisible(false);


                int LectureIDTEMP = (LectureTableView.getItems().size()) + 1;
                String SatırsayısıtoString = Integer.toString(LectureIDTEMP);


                Label LectureID = new Label("Lecture ID :");
                AddLectureGrid.add(LectureID, 0, 0);

                Label lectureNameLabel = new Label("Lecture Name:");
                AddLectureGrid.add(lectureNameLabel, 0, 1);

                Label lecturerNameLabel = new Label("Lecturer's Name:");
                AddLectureGrid.add(lecturerNameLabel, 0, 2);

                Label lectureCodeLabel = new Label("Lecture Code:");
                AddLectureGrid.add(lectureCodeLabel, 0, 3);

                Label semesterLabel = new Label("Semester:");
                AddLectureGrid.add(semesterLabel, 0, 4);

                Label theoryHourLabel = new Label("Theory Hour:");
                AddLectureGrid.add(theoryHourLabel, 0, 5);

                Label labHourLabel = new Label("Lab Hour:");
                AddLectureGrid.add(labHourLabel, 0, 6);

                Label localCreditLabel = new Label("Local Credit:");
                AddLectureGrid.add(localCreditLabel, 0, 7);

                Label ECTSLabel = new Label("Ects:");
                AddLectureGrid.add(ECTSLabel, 0, 8);

                Label prerequisitesLabel = new Label("Prerequisites:");
                AddLectureGrid.add(prerequisitesLabel, 0, 9);

                Label course_LanguageLabel = new Label("Lecture Language:");
                AddLectureGrid.add(course_LanguageLabel, 0, 10);

                Label course_TypeLabel = new Label("Lecture Type:");
                AddLectureGrid.add(course_TypeLabel, 0, 11);

                Label course_LevelLabel = new Label("Lecture Level:");
                AddLectureGrid.add(course_LevelLabel, 0, 12);

                Label teaching_MethodsLabel = new Label("Teaching Method:");
                AddLectureGrid.add(teaching_MethodsLabel, 0, 13);

                Label course_CoordinatorLabel = new Label("Lecture Coordinator:");
                AddLectureGrid.add(course_CoordinatorLabel, 0, 14);

                Label assistantLabel = new Label("Lecture Assistant:");
                AddLectureGrid.add(assistantLabel, 0, 15);

                Label course_ObjectivesLabel = new Label("Lecture Objectives:");
                AddLectureGrid.add(course_ObjectivesLabel, 0, 16);

                Label learning_OutcomesLabel = new Label("Learning Outcome:");
                AddLectureGrid.add(learning_OutcomesLabel, 0, 17);

                Label course_DescriptionLabel = new Label("Lecture Description:");
                AddLectureGrid.add(course_DescriptionLabel, 0, 18);

                Label course_CategoryLabel = new Label("Lecture Category:");
                AddLectureGrid.add(course_CategoryLabel, 0, 19);

                ObservableList<TableShow> dataList = LectureTableView.getItems();


                TextField LectureIDtext = new TextField();
                LectureIDtext.setEditable(false);

                int lecture_id;
// Son elemanın nameColumnundan names değerini al
                if (dataList.size() > 0) { // veriler varsa
                        int lastIndex = dataList.size() - 1;
                        TableColumn<TableShow, String> nameColumn = (TableColumn<TableShow, String>) LectureTableView.getColumns().get(1); // nameColumn sütunu
                        String lastNamesValue = nameColumn.getCellData(lastIndex); // son elemanın names değeri
                        LectureConfig lecture = DBConnector.getInstance().getLecture(lastNamesValue);
                        System.out.println(lecture.getLecture_id());
                        lecture_id = (lecture.getLecture_id()) + 1;

                        LectureIDtext.setText("");
                        LectureIDtext.setText(Integer.toString((lecture.getLecture_id()) + 1));

                } else {
                        lecture_id = LectureIDTEMP;
                        LectureIDtext.setText(SatırsayısıtoString);
                }


                //LectureIDtext.setText(SatırsayısıtoString);

                AddLectureGrid.add(LectureIDtext, 1, 0);

                TextField LectureNameText = new TextField();
                AddLectureGrid.add(LectureNameText, 1, 1);

                TextField LecturersNameText = new TextField();
                AddLectureGrid.add(LecturersNameText, 1, 2);

                TextField LectureCodeText = new TextField();
                AddLectureGrid.add(LectureCodeText, 1, 3);

                TextField SemesterText = new TextField();
                AddLectureGrid.add(SemesterText, 1, 4);

                TextField TheoryHourText = new TextField();
                AddLectureGrid.add(TheoryHourText, 1, 5);

                TextField LabHourText = new TextField();
                AddLectureGrid.add(LabHourText, 1, 6);

                TextField LocalCreditText = new TextField();
                AddLectureGrid.add(LocalCreditText, 1, 7);

                TextField EctsText = new TextField();
                AddLectureGrid.add(EctsText, 1, 8);

                TextField PrerequisitesText = new TextField();
                AddLectureGrid.add(PrerequisitesText, 1, 9);

                TextField LectureLanguageText = new TextField();
                AddLectureGrid.add(LectureLanguageText, 1, 10);

                TextField LectureTypeText = new TextField();
                AddLectureGrid.add(LectureTypeText, 1, 11);

                TextField LectureLevelText = new TextField();
                AddLectureGrid.add(LectureLevelText, 1, 12);

                TextField TeachingMethodText = new TextField();
                AddLectureGrid.add(TeachingMethodText, 1, 13);

                TextField LectureCoordinatorText = new TextField();
                AddLectureGrid.add(LectureCoordinatorText, 1, 14);

                TextField AssistantText = new TextField();
                AddLectureGrid.add(AssistantText, 1, 15);

                TextField LectureObjectiveText = new TextField();
                AddLectureGrid.add(LectureObjectiveText, 1, 16);

                TextField LearningOutcomeText = new TextField();
                AddLectureGrid.add(LearningOutcomeText, 1, 17);

                TextField LectureDescriptionText = new TextField();
                AddLectureGrid.add(LectureDescriptionText, 1, 18);

                TextField LectureCategoryText = new TextField();
                AddLectureGrid.add(LectureCategoryText, 1, 19);


                AddLectureButton.setOnAction(event -> {
                        String TempName = LectureNameText.getText();
                        String TempLName = LecturersNameText.getText();
                        String TempLCode = LectureCodeText.getText();
                        String TempSemester = SemesterText.getText();
                        String TempTHour = TheoryHourText.getText();
                        String TempLHour = LabHourText.getText();
                        String TempLCredit = LocalCreditText.getText();
                        String TempEcts = EctsText.getText();
                        String TempPrereq = PrerequisitesText.getText();
                        String TempLLang = LectureLanguageText.getText();
                        String TempLType = LectureTypeText.getText();
                        String TempLLevel = LectureLevelText.getText();
                        String TempTMethod = TeachingMethodText.getText();
                        String TempLCoordinator = LectureCoordinatorText.getText();
                        String TempAssistant = AssistantText.getText();
                        String TempLObjective = LectureObjectiveText.getText();
                        String TempLOutcome = LearningOutcomeText.getText();
                        String TempLDescription = LectureDescriptionText.getText();
                        String TempLCategory = LectureCategoryText.getText();

                        LectureConfig Lecture = new LectureConfig(lecture_id, TempName, TempLName, TempLCode, TempSemester, TempTHour, TempLHour, TempLCredit,
                                TempEcts, TempPrereq, TempLLang, TempLType, TempLLevel, TempTMethod, TempLCoordinator, TempAssistant, TempLObjective,
                                TempLOutcome, TempLDescription, TempLCategory);
                        DBConnector.getInstance().addLecture(Lecture);

                        LecturesHBox.setEffect(null);
                        AddLectureBox.setVisible(false);
                        LecturesHBox.setVisible(true);

                        openLectureScreen();


                });

        }


        public void selectFromLectureTable() throws SQLException, IOException {
                if (LectureTableView.getSelectionModel().getSelectedCells() == null ||
                        LectureTableView.getSelectionModel().getSelectedIndex() == -1) {
                        return;
                }

                int index = LectureTableView.getSelectionModel().getSelectedIndex();
                String LectureName = (String) LectureNameColumn.getCellData(index);

                ObservableList<TablePosition> selectedCells = LectureTableView.getSelectionModel().getSelectedCells();

                if (selectedCells.get(0).getTableColumn().equals(LectureTrashColumn)) {
                        //DBConnection.getInstance().deleteTemplate(LectureName);
                        System.out.println(LectureName);
                        LectureConfig Lecture = DBConnector.getInstance().getLecture(LectureName);
                        DBConnector.getInstance().deleteLectureObject(Lecture.getLecture_id());
                        openLectureScreen();
                } else {
                        ObservableList<TableShow> ts_list = LectureTableView.getSelectionModel().getSelectedItems();
                        TableShow ts = ts_list.get(0);
                        lec_id = ts.getId();
                        LectureConfig Lecture = DBConnector.getInstance().getLectureConfigObject(lec_id);
                        ObservableList<Node> children = LectureGrid.getChildren();
                        children.clear();
                        String TempID = Integer.toString(Lecture.getLecture_id());
                        String TempLectureName = Lecture.getLecture_Name();
                        String TempLecturerName = Lecture.getLecturer_Name();
                        String TempLCode = Lecture.getLecture_Code();
                        String TempSemester = Lecture.getSemester();
                        String TempTHour = Lecture.getTheory_Hour_In_Week();
                        String TempLHour = Lecture.getApplication_or_Lab_Hour_In_Week();
                        String TempLCredit = Lecture.getLecture_Code();
                        String TempEcts = Lecture.getECTS();
                        String TempPrereq = Lecture.getPrerequisites();
                        String TempLLang = Lecture.getCourse_Language();
                        String TempLType = Lecture.getCourse_Type();
                        String TempLLevel = Lecture.getCourse_Level();
                        String TempTMethod = Lecture.getTeaching_Methods_and_Techniques();
                        String TempLCoordinator = Lecture.getCourse_Coordinator();
                        String TempAssistant = Lecture.getAssistant();
                        String TempLObjective = Lecture.getCourse_Objectives();
                        String TempLOutcome = Lecture.getLearning_Outcomes();
                        String TempLDescription = Lecture.getCourse_Description();
                        String TempLCategory = Lecture.getCourse_Category();

                        Label LectureID = new Label("Lecture ID :");
                        LectureGrid.add(LectureID, 0, 0);

                        Label lectureNameLabel = new Label("Lecture Name:");
                        LectureGrid.add(lectureNameLabel, 0, 1);

                        Label lecturerNameLabel = new Label("Lecturer's Name:");
                        LectureGrid.add(lecturerNameLabel, 0, 2);

                        Label lectureCodeLabel = new Label("Lecture Code:");
                        LectureGrid.add(lectureCodeLabel, 0, 3);

                        Label semesterLabel = new Label("Semester:");
                        LectureGrid.add(semesterLabel, 0, 4);

                        Label theoryHourLabel = new Label("Theory Hour:");
                        LectureGrid.add(theoryHourLabel, 0, 5);

                        Label labHourLabel = new Label("Lab Hour:");
                        LectureGrid.add(labHourLabel, 0, 6);

                        Label localCreditLabel = new Label("Local Credit:");
                        LectureGrid.add(localCreditLabel, 0, 7);

                        Label ECTSLabel = new Label("Ects:");
                        LectureGrid.add(ECTSLabel, 0, 8);

                        Label prerequisitesLabel = new Label("Prerequisites:");
                        LectureGrid.add(prerequisitesLabel, 0, 9);

                        Label course_LanguageLabel = new Label("Lecture Language:");
                        LectureGrid.add(course_LanguageLabel, 0, 10);

                        Label course_TypeLabel = new Label("Lecture Type:");
                        LectureGrid.add(course_TypeLabel, 0, 11);

                        Label course_LevelLabel = new Label("Lecture Level:");
                        LectureGrid.add(course_LevelLabel, 0, 12);

                        Label teaching_MethodsLabel = new Label("Teaching Method:");
                        LectureGrid.add(teaching_MethodsLabel, 0, 13);

                        Label course_CoordinatorLabel = new Label("Lecture Coordinator:");
                        LectureGrid.add(course_CoordinatorLabel, 0, 14);

                        Label assistantLabel = new Label("Lecture Assistant:");
                        LectureGrid.add(assistantLabel, 0, 15);

                        Label course_ObjectivesLabel = new Label("Lecture Objectives:");
                        LectureGrid.add(course_ObjectivesLabel, 0, 16);

                        Label learning_OutcomesLabel = new Label("Learning Outcome:");
                        LectureGrid.add(learning_OutcomesLabel, 0, 17);

                        Label course_DescriptionLabel = new Label("Lecture Description:");
                        LectureGrid.add(course_DescriptionLabel, 0, 18);

                        Label course_CategoryLabel = new Label("Lecture Category:");
                        LectureGrid.add(course_CategoryLabel, 0, 19);

                        Label LectureIDtext = new Label(TempID);
                        LectureGrid.add(LectureIDtext, 1, 0);

                        Label LectureNameText = new Label(TempLectureName);
                        LectureGrid.add(LectureNameText, 1, 1);

                        Label LecturersNameText = new Label(TempLecturerName);
                        LectureGrid.add(LecturersNameText, 1, 2);

                        Label lectureCodeText = new Label(TempLCode);
                        LectureGrid.add(lectureCodeText, 1, 3);

                        Label semesterText = new Label(TempSemester);
                        LectureGrid.add(semesterText, 1, 4);

                        Label theoryHourText = new Label(TempTHour);
                        LectureGrid.add(theoryHourText, 1, 5);

                        Label labHourText = new Label(TempLHour);
                        LectureGrid.add(labHourText, 1, 6);

                        Label localCreditText = new Label(TempLCredit);
                        LectureGrid.add(localCreditText, 1, 7);

                        Label ECTSText = new Label(TempEcts);
                        LectureGrid.add(ECTSText, 1, 8);

                        Label prerequisitesText = new Label(TempPrereq);
                        LectureGrid.add(prerequisitesText, 1, 9);

                        Label course_LanguageText = new Label(TempLLang);
                        LectureGrid.add(course_LanguageText, 1, 10);

                        Label course_TypeText = new Label(TempLType);
                        LectureGrid.add(course_TypeText, 1, 11);

                        Label course_LevelText = new Label(TempLLevel);
                        LectureGrid.add(course_LevelText, 1, 12);

                        Label teaching_MethodsText = new Label(TempTMethod);
                        LectureGrid.add(teaching_MethodsText, 1, 13);

                        Label course_CoordinatorText = new Label(TempLCoordinator);
                        LectureGrid.add(course_CoordinatorText, 1, 14);

                        Label assistantText = new Label(TempAssistant);
                        LectureGrid.add(assistantText, 1, 15);

                        Label course_ObjectivesText = new Label(TempLObjective);
                        LectureGrid.add(course_ObjectivesText, 1, 16);

                        Label learning_OutcomesText = new Label(TempLOutcome);
                        LectureGrid.add(learning_OutcomesText, 1, 17);

                        Label course_DescriptionText = new Label(TempLDescription);
                        LectureGrid.add(course_DescriptionText, 1, 18);

                        Label course_CategoryText = new Label(TempLCategory);
                        LectureGrid.add(course_CategoryText, 1, 19);

                }
        }

        @FXML
        public void openEditLecture() throws UnsupportedSelectionException {
                ObservableList<TableShow> ts_list = LectureTableView.getSelectionModel().getSelectedItems();


                try {
                        TableShow ts = ts_list.get(0);
                } catch (Exception e) {
                        AlertUtil.showUnsupportedSelectionLectureAlert();
                        openLectureScreen();
                        throw new UnsupportedSelectionException("Unsupported Selection");


                }

                TableShow ts = ts_list.get(0);
                lec_id = ts.getId();

                LecturesHBox.setVisible(true);
                ProjectsHBox.setVisible(false);
                EditLectureHBox.setVisible(true);
                PL_HBox.setVisible(false);
                AddProjectBox.setVisible(false);
                AddPLBox.setVisible(false);
                AddLectureBox.setVisible(false);
                ProjectsHBox.setEffect(null);
                StudentsHbox.setVisible(false);

                BoxBlur blur = new BoxBlur();
                blur.setWidth(10);
                blur.setHeight(10);
                blur.setIterations(3);
                LecturesHBox.setEffect(blur);

                firstEllipses.setVisible(false);
                secondEllipses.setVisible(false);
                thirdEllipses.setVisible(false);


                LectureConfig Lecture = DBConnector.getInstance().getLectureConfigObject(lec_id);
                String TempID = Integer.toString(Lecture.getLecture_id());
                String TempLectureName = Lecture.getLecture_Name();
                String TempLecturerName = Lecture.getLecturer_Name();

                Label LectureID = new Label("Lecture ID :");
                EditLectureoldvalue.add(LectureID, 0, 0);

                Label lectureNameLabel = new Label("Lecture Name:");
                EditLectureoldvalue.add(lectureNameLabel, 0, 1);

                Label lecturerNameLabel = new Label("Lecturer's Name:");
                EditLectureoldvalue.add(lecturerNameLabel, 0, 2);


                Label LectureIDtext = new Label(TempID);
                EditLectureoldvalue.add(LectureIDtext, 1, 0);

                Label LectureNameText = new Label(TempLectureName);
                EditLectureoldvalue.add(LectureNameText, 1, 1);

                Label LecturersNameText = new Label(TempLecturerName);
                EditLectureoldvalue.add(LecturersNameText, 1, 2);


                Label NewLectureID = new Label("Lecture ID :");
                EditLecturenewvalue.add(NewLectureID, 0, 0);

                Label NewlectureNameLabel = new Label("Lecture Name:");
                EditLecturenewvalue.add(NewlectureNameLabel, 0, 1);

                Label NewlecturerNameLabel = new Label("Lecturer's Name:");
                EditLecturenewvalue.add(NewlecturerNameLabel, 0, 2);


                TextField NewLectureIDtext = new TextField(TempID);
                NewLectureIDtext.setEditable(false);
                EditLecturenewvalue.add(NewLectureIDtext, 1, 0);

                TextField NewLectureNameText = new TextField();
                EditLecturenewvalue.add(NewLectureNameText, 1, 1);

                TextField NewLecturersNameText = new TextField();
                EditLecturenewvalue.add(NewLecturersNameText, 1, 2);


                EditLectureConfirm.setOnAction(event -> {
                        LectureConfig LectureNew = new LectureConfig(lec_id, NewLectureNameText.getText(), NewLecturersNameText.getText());
                        DBConnector.getInstance().updateLecture(LectureNew);

                        EditLectureHBox.setVisible(false);
                        LecturesHBox.setVisible(true);
                        LecturesHBox.setEffect(null);
                        ProjectsHBox.setVisible(false);
                        PL_HBox.setVisible(false);
                        AddProjectBox.setVisible(false);
                        AddPLBox.setVisible(false);
                        AddLectureBox.setVisible(false);
                        ProjectsHBox.setEffect(null);
                        StudentsHbox.setVisible(false);
                        firstEllipses.setVisible(false);
                        secondEllipses.setVisible(true);
                        thirdEllipses.setVisible(false);
                        openLectureScreen();
                });

                CloseEditLecture.setOnAction(event -> {
                        EditLectureHBox.setVisible(false);
                        LecturesHBox.setVisible(true);
                        LecturesHBox.setEffect(null);
                        ProjectsHBox.setVisible(false);
                        PL_HBox.setVisible(false);
                        AddProjectBox.setVisible(false);
                        AddPLBox.setVisible(false);
                        AddLectureBox.setVisible(false);
                        ProjectsHBox.setEffect(null);
                        StudentsHbox.setVisible(false);
                        firstEllipses.setVisible(false);
                        secondEllipses.setVisible(true);
                        thirdEllipses.setVisible(false);
                        openLectureScreen();
                });


        }

        int row = 0;



        @FXML
        public void openPLScreen() {
                LecturesHBox.setVisible(false);
                ProjectsHBox.setVisible(false);
                PL_HBox.setVisible(true);
                PL_HBox.setEffect(null);
                AddProjectBox.setVisible(false);
                AddLectureBox.setVisible(false);
                AddPLBox.setVisible(false);
                firstEllipses.setVisible(false);
                secondEllipses.setVisible(false);
                thirdEllipses.setVisible(true);
                StudentsHbox.setVisible(false);
                DetailedEvaluationHbox.setVisible(false);

                String path = "images/trash.png";
                String path2="images/Go.png";

                Image image = new Image(getClass().getResource(path).toExternalForm());
                Image image2 = new Image(getClass().getResource(path2).toExternalForm());

                ObservableList<TableShow> ProgrammingLanguageList = FXCollections
                        .observableArrayList();

                PLNameColumn.setCellValueFactory(new PropertyValueFactory<TableShow, String>("name"));


                PLTrashColumn.setCellValueFactory(new PropertyValueFactory<TableShow, ImageView>("image"));



                // TODO : Database daha yazılmadı ben şimdiden koydum
               // ArrayList<PLConfig> plconfigs = DBConnector.getInstance().getAllPLConfigObjects();
               // for (PLConfig plconfig : plconfigs) {
                 ///       ProgrammingLanguageList.add(new TableShow(plconfig.getId(), plconfig.getName(),
                    //            new ImageView(image),new ImageView(image2)));
                }


                //PLTableView.setItems(ProgrammingLanguageList);


                @FXML
                public void openAddProgLangScreen() throws SQLException {

                        LecturesHBox.setVisible(false);
                        ProjectsHBox.setVisible(false);
                        PL_HBox.setVisible(true);
                        AddProjectBox.setVisible(false);
                        AddLectureBox.setVisible(false);
                        AddPLBox.setVisible(true);

                        firstEllipses.setVisible(false);
                        secondEllipses.setVisible(false);
                        thirdEllipses.setVisible(false);
                        BoxBlur blur = new BoxBlur();
                        blur.setWidth(10);
                        blur.setHeight(10);
                        blur.setIterations(3);
                        PL_HBox.setEffect(blur);

                        int ProgLangIDTEMP = (PLTableView.getItems().size())+1;
                        String SatırsayısıtoString=Integer.toString(ProgLangIDTEMP);
                        ObservableList<TableShow> dataList = PLTableView.getItems();


                        Label l1 = new Label("Programming Language ID :");

                        TextField l2 = new TextField();
                        l2.setEditable(false);

                        int PL_id;
        // Son elemanın nameColumnundan names değerini al
                        if (dataList.size() > 0) { // veriler varsa
                                int lastIndex = dataList.size() - 1;
                                TableColumn<TableShow, String> nameColumn = (TableColumn<TableShow, String>) PLTableView.getColumns().get(1); // nameColumn sütunu
                                String   lastNamesValue = nameColumn.getCellData(lastIndex); // son elemanın names değeri
                             //   PLConfig  programminglanguage = DBConnector.getInstance().getPL(lastNamesValue);
                               // System.out.println(programminglanguage.getId());
                               // PL_id=(programminglanguage.getId())+1;

                                //l2.setText("");
                                //l2.setText(Integer.toString((programminglanguage.getId())+1));

                        }
                        else{
                                PL_id=ProgLangIDTEMP;
                                l2.setText(SatırsayısıtoString);}

                        Label ProgLangName = new Label("Programming Langugage Name  : ");
                        Label ProgLangVersion = new Label("Programming Language Version  : ");
                        Label ProgLangNeedCompiler = new Label("Need Compiler  : ");
                        Label ProgLangCompileİns = new Label(" Compile İnstructions : ");
                        Label ProgLangRunİns = new Label("Run İnstructions  : ");
                        Label ProgLangVersionCheck = new Label("Version Check command  : ");
                        Label ProgLangVersionExtractPattern = new Label("Version Extract Pattern  : ");

                        TextField selectedPL_Name=new TextField();
                        TextField selectedPL_Version = new TextField();
                        TextField selectedPL_Compiler=new TextField();
                        TextField selectedPL_Compileİns = new TextField();
                        TextField selectedPL_Runİns=new TextField();
                        TextField selectedPL_VersionCheck=new TextField();
                        TextField selectedPL_VersionExtractPattern=new TextField();


                        AddPLGrid.add(l1,0,0);
                        AddPLGrid.add(ProgLangName,0,1);
                        AddPLGrid.add(ProgLangVersion,0,2);
                        AddPLGrid.add(ProgLangNeedCompiler,0,3);
                        AddPLGrid.add(ProgLangCompileİns,0,4);
                        AddPLGrid.add(ProgLangRunİns,0,5);
                        AddPLGrid.add(ProgLangVersionCheck,0,6);
                        AddPLGrid.add(ProgLangVersionExtractPattern,0,7);

                        AddPLGrid.add(l2,1,0);
                        AddPLGrid.add(selectedPL_Name ,1,1);
                        AddPLGrid.add(selectedPL_Version,1,2);
                        AddPLGrid.add(selectedPL_Compiler,1,3);
                        AddPLGrid.add(selectedPL_Compileİns,1,4);
                        AddPLGrid.add(selectedPL_Runİns,1,5);
                        AddPLGrid.add(selectedPL_VersionCheck,1,6);
                        AddPLGrid.add(selectedPL_VersionExtractPattern,1,7);

                        selectedPL_Name.textProperty().addListener((observable, oldValue, newValue) -> {
                                if (Objects.equals(selectedPL_Name.getText(), "Python")){
                                        selectedPL_Compiler.setText("false");
                                        selectedPL_Compileİns.setText("null");
                                        selectedPL_Runİns.setText("python <PARENT_DIRECTORY>/<FILENAME>.py <ARGS>");
                                        selectedPL_VersionCheck.setText("python --version");
                                        selectedPL_VersionExtractPattern.setText("Python (\\d+\\.\\d+\\.\\d+)");
                                } else if (Objects.equals(selectedPL_Name.getText(), "Java")) {
                                        selectedPL_Compiler.setText("true");
                                        selectedPL_Compileİns.setText("javac <PARENT_DIRECTORY>/<FILENAME>.java");
                                        selectedPL_Runİns.setText("java -classpath <PARENT_DIRECTORY> <FILENAME> <ARGS>");
                                        selectedPL_VersionCheck.setText("java --version");
                                        selectedPL_VersionExtractPattern.setText("java (\\d+\\.\\d+\\.\\d+\\.\\d+)");

                                }
                        });
                        AddPLButton.setOnAction(event -> {


                                String TempPL_Name =selectedPL_Name.getText();
                                String TempPL_Version=selectedPL_Version.getText();
                                Boolean TempPL_NeedCompiler =Boolean.parseBoolean(selectedPL_Compiler.getText());
                                String TempPL_Compilerİns=selectedPL_Compileİns.getText();
                                String TempPL_Runİns=selectedPL_Runİns.getText();
                                String TempPL_VersionCheck=selectedPL_VersionCheck.getText();
                        
                        
                                String TempPL_VersionExtractPattern=selectedPL_VersionExtractPattern.getText();
                                String pattern = TempPL_VersionExtractPattern;


                                PL_HBox.setEffect(null);
                                AddPLBox.setVisible(false);
                                PL_HBox.setVisible(true);
                                ObservableList<Node> children = AddPLGrid.getChildren();
                                children.clear();
                                openPLScreen();

                        });
                }

        @FXML
        public void openImportScreen() {
                LecturesHBox.setVisible(false);
                ProjectsHBox.setVisible(false);
                PL_HBox.setVisible(false);
                PL_HBox.setEffect(null);
                AddProjectBox.setVisible(false);
                DetailedEvaluationHbox.setVisible(false);

                AddLectureBox.setVisible(false);
                AddPLBox.setVisible(false);
                firstEllipses.setVisible(false);
                secondEllipses.setVisible(false);
                thirdEllipses.setVisible(true);
                StudentsHbox.setVisible(false);
                DetailedEvaluationHbox.setVisible(false);

                FileChooser fileChooser = new FileChooser();
                fileChooser.setTitle("Open Configuration File");
                fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("JSON", "*.json"));
                File file = fileChooser.showSaveDialog(null);
                if (file != null) {
                        String filePath = file.getAbsolutePath();

                        try (FileReader reader = new FileReader(filePath)) {
                                Gson gson = new Gson();
                                Type listType = new TypeToken<List<Config>>() {
                                }.getType();
                                List<Config> objectList = gson.fromJson(reader, listType);

                                for (Config obj : objectList) {
                                        switch (obj.getType()) {
                                                case "lecture":
                                                        DBConnector.getInstance().addLecture(gson.fromJson(obj.getConfig(), LectureConfig.class));
                                                        break;

                                        }
                                }

                                System.out.println("Data imported successfully!");
                        } catch (IOException e) {
                                e.printStackTrace();
                        }
                }

                openLectureScreen();
        }


        @FXML
        public void openExportScreen() {
                LecturesHBox.setVisible(false);
                ProjectsHBox.setVisible(false);
                PL_HBox.setVisible(false);
                PL_HBox.setEffect(null);
                AddProjectBox.setVisible(false);
                DetailedEvaluationHbox.setVisible(false);

                AddLectureBox.setVisible(false);
                AddPLBox.setVisible(false);
                firstEllipses.setVisible(false);
                secondEllipses.setVisible(false);
                thirdEllipses.setVisible(true);
                StudentsHbox.setVisible(false);

                Gson gson = new GsonBuilder().setPrettyPrinting().create();
                JsonArray jsonArray = new JsonArray();

                ArrayList<LectureConfig> lectures = DBConnector.getInstance().getAllLectureConfigObjects();
                for (LectureConfig lecture : lectures) {
                        JsonObject jsonObject = new JsonObject();
                        jsonObject.addProperty("type", "lecture");
                        jsonObject.addProperty("config", gson.toJson(lecture));
                        jsonArray.add(jsonObject);
                }


                String json = gson.toJson(jsonArray);

                FileChooser fileChooser = new FileChooser();
                fileChooser.setTitle("Save Configuration File");
                fileChooser.setInitialFileName("data.json");
                fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("JSON", "*.json"));
                File file = fileChooser.showSaveDialog(null);
                if (file != null) {
                        String filePath = file.getAbsolutePath();
                        try (FileWriter writer = new FileWriter(filePath)) {
                                writer.write(json);
                                System.out.println("JSON data has been written to the file.");
                        } catch (IOException e) {
                                e.printStackTrace();
                        }
                }

                openLectureScreen();
        }



        @FXML
        public void openEditProgLang() throws UnsupportedSelectionException {
                ObservableList<TableShow> ts_list = PLTableView.getSelectionModel().getSelectedItems();
                try {
                        TableShow ts = ts_list.get(0);
                } catch (Exception e) {
                        AlertUtil.showUnsupportedSelectionAlert();
                        openPLScreen();
                        throw new UnsupportedSelectionException("Unsupported Selection");

                }


                TableShow ts = ts_list.get(0);
                pl_id = ts.getId();

                LecturesHBox.setVisible(false);
                ProjectsHBox.setVisible(false);
                EditLectureHBox.setVisible(false);
                EditProgLangHBox.setVisible(true);
                PL_HBox.setVisible(true);
                AddProjectBox.setVisible(false);
                AddPLBox.setVisible(false);
                AddLectureBox.setVisible(false);
                ProjectsHBox.setEffect(null);
                StudentsHbox.setVisible(false);

                BoxBlur blur = new BoxBlur();
                blur.setWidth(10);
                blur.setHeight(10);
                blur.setIterations(3);
                PL_HBox.setEffect(blur);

                firstEllipses.setVisible(false);
                secondEllipses.setVisible(false);
                thirdEllipses.setVisible(false);


                Label ProgLangID = new Label("Programming Language ID :");
                EditProgLangoldvalue.add(ProgLangID, 0, 0);

                Label ProgLangName = new Label("Programming Langugage Name  : ");
                EditProgLangoldvalue.add(ProgLangName, 0, 1);

                Label ProgLangVersion = new Label("Programming Language Version  : ");
                EditProgLangoldvalue.add(ProgLangVersion, 0, 2);

                Label ProgLangNeedCompiler = new Label("Need Compiler  : ");
                EditProgLangoldvalue.add(ProgLangNeedCompiler, 0, 3);


                Label ProgLangCompileInstructions = new Label(" Compile İnstructions : ");
                EditProgLangoldvalue.add(ProgLangCompileInstructions, 0, 4);


                Label ProgLangRunInstructions = new Label("Run İnstructions  : ");
                EditProgLangoldvalue.add(ProgLangRunInstructions, 0, 5);


                Label ProgLangVersionCheckCommand = new Label("Version Check command  : ");
                EditProgLangoldvalue.add(ProgLangVersionCheckCommand, 0, 6);

                Label ProgLangVersionExtractPattern = new Label("Version Extract Pattern  : ");
                EditProgLangoldvalue.add(ProgLangVersionExtractPattern, 0, 7);


                Label NewProgLangID = new Label("Programming Language ID :");
                EditProgLangnewvalue.add(NewProgLangID, 0, 0);

                Label NewProgLangName = new Label("Programming Langugage Name  : ");
                EditProgLangnewvalue.add(NewProgLangName, 0, 1);

                Label NewProgLangVersion = new Label("Programming Language Version  : ");
                EditProgLangnewvalue.add(NewProgLangVersion, 0, 2);

                Label NewProgLangNeedCompiler = new Label("Need Compiler  : ");
                EditProgLangnewvalue.add(NewProgLangNeedCompiler, 0, 3);


                Label NewProgLangCompileInstructions = new Label(" Compile İnstructions : ");
                EditProgLangnewvalue.add(NewProgLangCompileInstructions, 0, 4);


                Label NewProgLangRunInstructions = new Label("Run İnstructions  : ");
                EditProgLangnewvalue.add(NewProgLangRunInstructions, 0, 5);


                Label NewProgLangVersionCheckCommand = new Label("Version Check command  : ");
                EditProgLangnewvalue.add(NewProgLangVersionCheckCommand, 0, 6);

                Label NewProgLangVersionExtractPattern = new Label("Version Extract Pattern  : ");
                EditProgLangnewvalue.add(NewProgLangVersionExtractPattern, 0, 7);


                TextField NewProgLangNameText = new TextField();
                EditProgLangnewvalue.add(NewProgLangNameText, 1, 1);

                TextField NewProgLangVersionText = new TextField();
                EditProgLangnewvalue.add(NewProgLangVersionText, 1, 2);

                TextField NewProgLangNeedCompilerText = new TextField();
                EditProgLangnewvalue.add(NewProgLangNeedCompilerText, 1, 3);

                TextField NewProgLangCompileInstructionsText = new TextField();
                EditProgLangnewvalue.add(NewProgLangCompileInstructionsText, 1, 4);

                TextField NewProgLangRunInstructionsText = new TextField();
                EditProgLangnewvalue.add(NewProgLangRunInstructionsText, 1, 5);

                TextField NewProgLangVersionCheckCommandText = new TextField();
                EditProgLangnewvalue.add(NewProgLangVersionCheckCommandText, 1, 6);


                EditProgLangConfirm.setOnAction(event -> {


                        EditLectureHBox.setVisible(false);
                        EditProgLangHBox.setVisible(false);
                        LecturesHBox.setVisible(false);
                        LecturesHBox.setEffect(null);
                        ProjectsHBox.setVisible(false);
                        PL_HBox.setVisible(true);
                        AddProjectBox.setVisible(false);
                        AddPLBox.setVisible(false);
                        AddLectureBox.setVisible(false);
                        ProjectsHBox.setEffect(null);
                        StudentsHbox.setVisible(false);
                        firstEllipses.setVisible(false);
                        secondEllipses.setVisible(true);
                        thirdEllipses.setVisible(false);
                        openPLScreen();
                });

                CloseEditProgLang.setOnAction(event -> {
                        EditLectureHBox.setVisible(false);
                        EditProgLangHBox.setVisible(false);
                        LecturesHBox.setVisible(false);
                        LecturesHBox.setEffect(null);
                        ProjectsHBox.setVisible(false);
                        PL_HBox.setVisible(true);
                        AddProjectBox.setVisible(false);
                        AddPLBox.setVisible(false);
                        AddLectureBox.setVisible(false);
                        ProjectsHBox.setEffect(null);
                        StudentsHbox.setVisible(false);
                        firstEllipses.setVisible(false);
                        secondEllipses.setVisible(true);
                        thirdEllipses.setVisible(false);
                        openPLScreen();
                });

        }

        /*
        @FXML
        public void openStudentScreen(int id) {
                project_id = id;

                LecturesHBox.setVisible(false);
                ProjectsHBox.setVisible(false);
                LecturesHBox.setEffect(null);
                StudentsHbox.setVisible(true);
                PL_HBox.setVisible(false);
                AddProjectBox.setVisible(false);
                AddLectureBox.setVisible(false);
                AddPLBox.setVisible(false);

                firstEllipses.setVisible(false);
                secondEllipses.setVisible(true);
                thirdEllipses.setVisible(false);
          
                String path="images/Go.png";
                Image image = new Image(getClass().getResource(path).toExternalForm());

        }*/


        @FXML
        private void longDrawer() {
                drawerStackPane.setPrefWidth(275);
                drawerShort.setVisible(false);
                drawerLong.setVisible(true);
        }

        @FXML
        private void shortDrawer() {
                drawerStackPane.setPrefWidth(70);
                drawerShort.setVisible(true);
                drawerLong.setVisible(false);
        }

        @FXML
        private void toggleDrawer() {
                if (drawerShort.isVisible()) {
                        longDrawer();
                } else
                        shortDrawer();
        }


        private String splitCamelCase(String input) {
                Pattern pattern = Pattern.compile("(?<=[a-z])(?=[A-Z])");
                Matcher matcher = pattern.matcher(input);
                String result = matcher.replaceAll(" ");
                return result;
        }

        private void deleteFolderRecursive(String destinationPath){
                try {
                        Path folder = Path.of(destinationPath);
                        Files.walkFileTree(folder, new SimpleFileVisitor<>() {
                                @Override
                                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                                        Files.delete(file);
                                        return FileVisitResult.CONTINUE;
                                }
                
                                @Override
                                public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
                                        Files.delete(dir);
                                        return FileVisitResult.CONTINUE;
                                }
                        });
                        
                        System.out.println("Folder removed successfully.");
                } catch (IOException e) {
                        System.out.println("Error removing folder: " + e.getMessage());
                }
        }

        private void extractZipFile(File zipFile, File outputDirectory) {
                try {
                        ZipFile zip = new ZipFile(zipFile);
                        Enumeration<? extends ZipEntry> entries = zip.entries();
                        
                        byte[] buffer = new byte[1024];
                        while (entries.hasMoreElements()) {
                        ZipEntry entry = entries.nextElement();
                        File entryFile = new File(outputDirectory, entry.getName());
                        
                        if (entry.isDirectory()) {
                                entryFile.mkdirs();
                        } else {
                                entryFile.getParentFile().mkdirs();
                                InputStream inputStream = zip.getInputStream(entry);
                                FileOutputStream outputStream = new FileOutputStream(entryFile);
                                int length;
                                while ((length = inputStream.read(buffer)) > 0) {
                                outputStream.write(buffer, 0, length);
                                }
                                outputStream.close();
                                inputStream.close();
                        }
                        }
                        
                        zip.close();
                        System.out.println("Extraction completed successfully.");
                } catch (IOException e) {
                        System.out.println("Error extracting zip file: " + e.getMessage());
                }
        }

        @FXML
        private void GoHelp() throws IOException {
                Stage stage = new Stage();
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("HelpScreen.fxml"));
                Parent root = fxmlLoader.load();
                Scene scene = new Scene(root, 800, 600);
                stage.setTitle("Help");
                stage.setScene(scene);
                stage.setResizable(false);
                stage.showAndWait();
        }
}
