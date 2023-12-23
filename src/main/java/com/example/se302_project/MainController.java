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

        @FXML
        private ScrollPane generatedResumeScrollPane2;

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

                                String file_path = "images/team5.mp4";
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
                blur.setWidth(15);
                blur.setHeight(15);
                blur.setIterations(3);
                LecturesHBox.setEffect(blur);

                firstEllipses.setVisible(false);
                secondEllipses.setVisible(false);
                thirdEllipses.setVisible(false);
                DetailedEvaluationHbox.setVisible(false);
                generatedResumeScrollPane2.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);


                int LectureIDTEMP = (LectureTableView.getItems().size()) + 1;
                String SatırsayısıtoString = Integer.toString(LectureIDTEMP);

                Label GENERALINFORMATION = new Label("1.GENERAL INFORMATION");
                GENERALINFORMATION.setStyle("-fx-background-color: orange; -fx-padding: 5px;-fx-font-weight: bold;-fx-font-size: 15px;");
                AddLectureGrid.add(GENERALINFORMATION,0,0);

                Label LectureID = new Label("Lecture ID :");
                AddLectureGrid.add(LectureID, 1, 0);

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
                AddLectureGrid.add(course_DescriptionLabel, 0,18);

                Label course_CategoryLabel = new Label("Lecture Category:");
                AddLectureGrid.add(course_CategoryLabel, 0, 19);

                Label WEEKLYSUBJECTSANDREQUIREDMATERIALS = new Label("2.WEEKLY SUBJECTS AND REQUIRED MATERIALS");
                WEEKLYSUBJECTSANDREQUIREDMATERIALS.setStyle("-fx-background-color: orange; -fx-padding: 5px;-fx-font-weight: bold;-fx-font-size: 8px;");
                AddLectureGrid.add(WEEKLYSUBJECTSANDREQUIREDMATERIALS,0,20);

                Label week1SubjectsLabel = new Label("Week 1 Subjects:");
                AddLectureGrid.add(week1SubjectsLabel, 0, 21);

                Label week2SubjectsLabel = new Label("Week 2 Subjects:");
                AddLectureGrid.add(week2SubjectsLabel, 0, 22);

                Label week3SubjectsLabel = new Label("Week 3 Subjects:");
                AddLectureGrid.add(week3SubjectsLabel, 0, 23);

                Label week4SubjectsLabel = new Label("Week 4 Subjects:");
                AddLectureGrid.add(week4SubjectsLabel, 0, 24);

                Label week5SubjectsLabel = new Label("Week 5 Subjects:");
                AddLectureGrid.add(week5SubjectsLabel, 0, 25);

                Label week6SubjectsLabel = new Label("Week 6 Subjects:");
                AddLectureGrid.add(week6SubjectsLabel, 0, 26);

                Label week7SubjectsLabel = new Label("Week 7 Subjects:");
                AddLectureGrid.add(week7SubjectsLabel, 0, 27);

                Label week8SubjectsLabel = new Label("Week 8 Subjects:");
                AddLectureGrid.add(week8SubjectsLabel, 0, 28);

                Label week9SubjectsLabel = new Label("Week 9 Subjects:");
                AddLectureGrid.add(week9SubjectsLabel, 0, 29);

                Label week10SubjectsLabel = new Label("Week 10 Subjects:");
                AddLectureGrid.add(week10SubjectsLabel, 0, 30);

                Label week11SubjectsLabel = new Label("Week 11 Subjects:");
                AddLectureGrid.add(week11SubjectsLabel, 0, 31);

                Label week12SubjectsLabel = new Label("Week 12 Subjects:");
                AddLectureGrid.add(week12SubjectsLabel, 0, 32);

                Label week13SubjectsLabel = new Label("Week 13 Subjects:");
                AddLectureGrid.add(week13SubjectsLabel, 0, 33);

                Label week14SubjectsLabel = new Label("Week 14 Subjects:");
                AddLectureGrid.add(week14SubjectsLabel, 0, 34);

                Label week15SubjectsLabel = new Label("Week 15 Subjects:");
                AddLectureGrid.add(week15SubjectsLabel, 0, 35);

                Label week1ReqMatLabel = new Label("Week 1 Required Materials:");
                AddLectureGrid.add(week1ReqMatLabel, 2, 21);

                Label week2ReqMatLabel = new Label("Week 2 Required Materials:");
                AddLectureGrid.add(week2ReqMatLabel, 2, 22);

                Label week3ReqMatLabel = new Label("Week 3 Required Materials:");
                AddLectureGrid.add(week3ReqMatLabel, 2, 23);

                Label week4ReqMatLabel = new Label("Week 4 Required Materials:");
                AddLectureGrid.add(week4ReqMatLabel, 2, 24);

                Label week5ReqMatLabel = new Label("Week 5 Required Materials:");
                AddLectureGrid.add(week5ReqMatLabel, 2, 25);

                Label week6ReqMatLabel = new Label("Week 6 Required Materials:");
                AddLectureGrid.add(week6ReqMatLabel, 2, 26);

                Label week7ReqMatLabel = new Label("Week 7 Required Materials:");
                AddLectureGrid.add(week7ReqMatLabel, 2, 27);

                Label week8ReqMatLabel = new Label("Week 8 Required Materials:");
                AddLectureGrid.add(week8ReqMatLabel, 2, 28);

                Label week9ReqMatLabel = new Label("Week 9 Required Materials:");
                AddLectureGrid.add(week9ReqMatLabel, 2, 29);

                Label week10ReqMatLabel = new Label("Week 10 Required Materials:");
                AddLectureGrid.add(week10ReqMatLabel, 2, 30);

                Label week11ReqMatLabel = new Label("Week 11 Required Materials:");
                AddLectureGrid.add(week11ReqMatLabel, 2, 31);

                Label week12ReqMatLabel = new Label("Week 12 Required Materials:");
                AddLectureGrid.add(week12ReqMatLabel, 2, 32);

                Label week13ReqMatLabel = new Label("Week 13 Required Materials:");
                AddLectureGrid.add(week13ReqMatLabel, 2, 33);

                Label week14ReqMatLabel = new Label("Week 14 Required Materials:");
                AddLectureGrid.add(week14ReqMatLabel, 2, 34);

                Label week15ReqMatLabel = new Label("Week 15 Required Materials:");
                AddLectureGrid.add(week15ReqMatLabel, 2, 35);

                Label courseNotesAndTextBooksLabel = new Label("Course Notes and Textbooks:");
                AddLectureGrid.add(courseNotesAndTextBooksLabel, 0, 36);

                Label suggestedReadingsAndMaterialsLabel = new Label("Suggested Readings and Materials:");
                AddLectureGrid.add(suggestedReadingsAndMaterialsLabel,0 , 37);

                Label ASSESMENT = new Label("3.ASSESMENT");
                ASSESMENT.setStyle("-fx-background-color: orange; -fx-padding: 5px;-fx-font-weight: bold;-fx-font-size: 15px;");
                AddLectureGrid.add(ASSESMENT,0,38);

                Label ECTSWORKLOADTABLE = new Label("4.ECTS / WORKLOAD TABLE");
                ECTSWORKLOADTABLE.setStyle("-fx-background-color: orange; -fx-padding: 5px;-fx-font-weight: bold;-fx-font-size: 15px;");
                AddLectureGrid.add(ECTSWORKLOADTABLE,0,110);

                Label numberLabel = new Label("NUMBER");
                numberLabel.setStyle("-fx-background-color: grey; -fx-padding: 5px;-fx-font-weight: bold;-fx-font-size: 10px;");
                AddLectureGrid.add(numberLabel, 1, 110);

                Label durationLabel = new Label("DURATION (HOURS)");
                durationLabel.setStyle("-fx-background-color: grey; -fx-padding: 5px;-fx-font-weight: bold;-fx-font-size: 10px;");
                AddLectureGrid.add(durationLabel, 2, 110);

                Label workloadLabel = new Label("WORKLOAD");
                workloadLabel.setStyle("-fx-background-color: grey; -fx-padding: 5px;-fx-font-weight: bold;-fx-font-size: 10px;");
                AddLectureGrid.add(workloadLabel, 3, 110);

                Label CourseHoursLabel = new Label("Course Hours\n(Including exam week: 16 x total hours):");
                AddLectureGrid.add(CourseHoursLabel, 0, 111);

                Label CourseHours16= new Label("16");
                AddLectureGrid.add(CourseHours16, 1, 111);

                Label LabAppHoursLabel = new Label("Laboratory/Application Hours\n(Including exam week: 16 x total hours)\n");
                AddLectureGrid.add(LabAppHoursLabel, 0, 112);

                Label LabHours16= new Label("16");
                AddLectureGrid.add(LabHours16, 1, 112);

                Label StudyHoursoutofClassNumLabel = new Label("Study Hours out of Class:");
                AddLectureGrid.add(StudyHoursoutofClassNumLabel, 0, 113);

                Label FieldWorkLabel = new Label("Field Work");
                AddLectureGrid.add(FieldWorkLabel, 0, 114);

                Label QuizLabel = new Label("Quiz/Studio Critique:");
                AddLectureGrid.add(QuizLabel, 0, 115);

                Label HWLabel = new Label("Homework/Assignments:");
                AddLectureGrid.add(HWLabel, 0, 116);

                Label PresentationLabel = new Label("Presentation/Jury::");
                AddLectureGrid.add(PresentationLabel, 0, 117);

                Label ProjectLabel = new Label("Project:");
                AddLectureGrid.add(ProjectLabel, 0, 118);

                Label PortfolioLabel = new Label("Portfolio:");
                AddLectureGrid.add(PortfolioLabel, 0, 119);

                Label SeminarLabel = new Label("Seminar/Workshop:");
                AddLectureGrid.add(SeminarLabel, 0,120);

                Label OralExamLabel = new Label("Oral Exam:");
                AddLectureGrid.add(OralExamLabel, 0, 121);

                Label MidtermLabel = new Label("Midterm:");
                AddLectureGrid.add(MidtermLabel, 0, 122);

                Label FinalLabel = new Label("Final Exam:");
                AddLectureGrid.add(FinalLabel, 0, 123);

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

                AddLectureGrid.add(LectureIDtext, 2, 0);

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

                TextField week1SubjectsText = new TextField();
                AddLectureGrid.add(week1SubjectsText, 1, 21);

                TextField week2SubjectsText = new TextField();
                AddLectureGrid.add(week2SubjectsText, 1, 22);

                TextField week3SubjectsText = new TextField();
                AddLectureGrid.add(week3SubjectsText, 1, 23);

                TextField week4SubjectsText = new TextField();
                AddLectureGrid.add(week4SubjectsText, 1, 24);

                TextField week5SubjectsText = new TextField();
                AddLectureGrid.add(week5SubjectsText, 1, 25);

                TextField week6SubjectsText = new TextField();
                AddLectureGrid.add(week6SubjectsText, 1, 26);

                TextField week7SubjectsText = new TextField();
                AddLectureGrid.add(week7SubjectsText, 1, 27);

                TextField week8SubjectsText = new TextField();
                AddLectureGrid.add(week8SubjectsText, 1, 28);

                TextField week9SubjectsText = new TextField();
                AddLectureGrid.add(week9SubjectsText, 1, 29);

                TextField week10SubjectsText = new TextField();
                AddLectureGrid.add(week10SubjectsText, 1, 30);

                TextField week11SubjectsText = new TextField();
                AddLectureGrid.add(week11SubjectsText, 1, 31);

                TextField week12SubjectsText = new TextField();
                AddLectureGrid.add(week12SubjectsText, 1, 32);

                TextField week13SubjectsText = new TextField();
                AddLectureGrid.add(week13SubjectsText, 1, 33);

                TextField week14SubjectsText = new TextField();
                AddLectureGrid.add(week14SubjectsText, 1, 34);

                TextField week15SubjectsText = new TextField();
                AddLectureGrid.add(week15SubjectsText, 1, 35);

                TextField week1ReqMatText = new TextField();
                AddLectureGrid.add(week1ReqMatText, 3, 21);

                TextField week2ReqMatText = new TextField();
                AddLectureGrid.add(week2ReqMatText, 3, 22);

                TextField week3ReqMatText = new TextField();
                AddLectureGrid.add(week3ReqMatText, 3, 23);

                TextField week4ReqMatText = new TextField();
                AddLectureGrid.add(week4ReqMatText, 3, 24);

                TextField week5ReqMatText = new TextField();
                AddLectureGrid.add(week5ReqMatText, 3, 25);

                TextField week6ReqMatText = new TextField();
                AddLectureGrid.add(week6ReqMatText ,3, 26);

                TextField week7ReqMatText = new TextField();
                AddLectureGrid.add(week7ReqMatText, 3, 27);

                TextField week8ReqMatText = new TextField();
                AddLectureGrid.add(week8ReqMatText, 3, 28);

                TextField week9ReqMatText = new TextField();
                AddLectureGrid.add(week9ReqMatText, 3, 29);

                TextField week10ReqMatText = new TextField();
                AddLectureGrid.add(week10ReqMatText, 3, 30);

                TextField week11ReqMatText = new TextField();
                AddLectureGrid.add(week11ReqMatText, 3, 31);

                TextField week12ReqMatText = new TextField();
                AddLectureGrid.add(week12ReqMatText, 3, 32);

                TextField week13ReqMatText = new TextField();
                AddLectureGrid.add(week13ReqMatText, 3, 33);

                TextField week14ReqMatText = new TextField();
                AddLectureGrid.add(week14ReqMatText, 3, 34);

                TextField week15ReqMatText = new TextField();
                AddLectureGrid.add(week15ReqMatText, 3, 35);

                TextField courseNotesAndTextBooksText = new TextField();
                AddLectureGrid.add(courseNotesAndTextBooksText, 1, 36);

                TextField suggestedReadingsAndMaterialsText = new TextField();
                AddLectureGrid.add(suggestedReadingsAndMaterialsText, 1, 37);

                TextField CourseHourDurText = new TextField();
                AddLectureGrid.add(CourseHourDurText, 2, 111);

                TextField CourseHourWorkText = new TextField();
                AddLectureGrid.add(CourseHourWorkText, 3, 111);

                TextField LabHoursDurText = new TextField();
                AddLectureGrid.add(LabHoursDurText, 2, 112);

                TextField LabHoursWorkText = new TextField();
                AddLectureGrid.add(LabHoursWorkText, 3, 112);

                TextField StudyHoursoutofClassNumText = new TextField();
                AddLectureGrid.add(StudyHoursoutofClassNumText, 1, 113);

                TextField StudyHoursoutofClassDurText = new TextField();
                AddLectureGrid.add(StudyHoursoutofClassDurText, 2, 113);

                TextField StudyHoursoutofClassWorkText = new TextField();
                AddLectureGrid.add(StudyHoursoutofClassWorkText, 3, 113);

                TextField FieldWorkNumText = new TextField();
                AddLectureGrid.add(FieldWorkNumText, 1, 114);

                TextField FieldWorkDurText = new TextField();
                AddLectureGrid.add(FieldWorkDurText, 2, 114);

                TextField FieldWorkWorkText = new TextField();
                AddLectureGrid.add(FieldWorkWorkText, 3, 114);

                TextField QuizNumText = new TextField();
                AddLectureGrid.add(QuizNumText, 1, 115);

                TextField QuizWorkText = new TextField();
                AddLectureGrid.add(QuizWorkText, 3, 115);

                TextField QuizDurText = new TextField();
                AddLectureGrid.add(QuizDurText, 2, 115);

                TextField HWNumText = new TextField();
                AddLectureGrid.add(HWNumText, 1, 116);

                TextField HWDurText = new TextField();
                AddLectureGrid.add(HWDurText, 2, 116);

                TextField HWWorkText = new TextField();
                AddLectureGrid.add(HWWorkText, 3, 116);

                TextField PresentationNumText = new TextField();
                AddLectureGrid.add(PresentationNumText, 1, 117);

                TextField PresentationDurText = new TextField();
                AddLectureGrid.add(PresentationDurText, 2, 117);

                TextField PresentationWorkText = new TextField();
                AddLectureGrid.add(PresentationWorkText, 3, 117);

                TextField ProjectNumText = new TextField();
                AddLectureGrid.add(ProjectNumText, 1, 118);

                TextField ProjectDurText = new TextField();
                AddLectureGrid.add(ProjectDurText, 2, 118);

                TextField ProjectWorkText = new TextField();
                AddLectureGrid.add(ProjectWorkText, 3, 118);

                TextField PortfolioNumText = new TextField();
                AddLectureGrid.add(PortfolioNumText, 1, 119);

                TextField PortfolioDurText = new TextField();
                AddLectureGrid.add(PortfolioDurText, 2, 119);

                TextField PortfolioWorkText = new TextField();
                AddLectureGrid.add(PortfolioWorkText, 3, 119);

                TextField SeminarNumText = new TextField();
                AddLectureGrid.add(SeminarNumText, 1, 120);

                TextField SeminarDurText = new TextField();
                AddLectureGrid.add(SeminarDurText, 2, 120);

                TextField SeminarWorkText = new TextField();
                AddLectureGrid.add(SeminarWorkText, 3, 120);

                TextField OralExamNumText = new TextField();
                AddLectureGrid.add(OralExamNumText, 1, 121);

                TextField OralExamDurText = new TextField();
                AddLectureGrid.add(OralExamDurText, 2, 121);

                TextField OralExamWorkText = new TextField();
                AddLectureGrid.add(OralExamWorkText, 3, 121);

                TextField MidtermNumText = new TextField();
                AddLectureGrid.add(MidtermNumText, 1, 122);

                TextField MidtermDurText = new TextField();
                AddLectureGrid.add(MidtermDurText, 2, 122);

                TextField MidtermWorkText = new TextField();
                AddLectureGrid.add(MidtermWorkText, 3, 122);

                TextField FinalExamNumText = new TextField();
                AddLectureGrid.add(FinalExamNumText, 1, 123);

                TextField FinalExamDurText = new TextField();
                AddLectureGrid.add(FinalExamDurText, 2, 123);

                TextField FinalExamWorkText = new TextField();
                AddLectureGrid.add(FinalExamWorkText, 3, 123);

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
                        String TempW1sub = week1SubjectsText.getText();
                        String TempW2sub = week2SubjectsText.getText();
                        String TempW3sub = week3SubjectsText.getText();
                        String TempW4sub = week4SubjectsText.getText();
                        String TempW5sub = week5SubjectsText.getText();
                        String TempW6sub = week6SubjectsText.getText();
                        String TempW7sub = week7SubjectsText.getText();
                        String TempW8sub = week8SubjectsText.getText();
                        String TempW9sub = week9SubjectsText.getText();
                        String TempW10sub = week10SubjectsText.getText();
                        String TempW11sub = week11SubjectsText.getText();
                        String TempW12sub = week12SubjectsText.getText();
                        String TempW13sub = week13SubjectsText.getText();
                        String TempW14sub = week14SubjectsText.getText();
                        String TempW15sub = week15SubjectsText.getText();
                        String TempW1req = week1ReqMatText.getText();
                        String TempW2req = week2ReqMatText.getText();
                        String TempW3req = week3ReqMatText.getText();
                        String TempW4req = week4ReqMatText.getText();
                        String TempW5req = week5ReqMatText.getText();
                        String TempW6req = week6ReqMatText.getText();
                        String TempW7req = week7ReqMatText.getText();
                        String TempW8req = week8ReqMatText.getText();
                        String TempW9req = week9ReqMatText.getText();
                        String TempW10req = week10ReqMatText.getText();
                        String TempW11req = week11ReqMatText.getText();
                        String TempW12req = week12ReqMatText.getText();
                        String TempW13req = week13ReqMatText.getText();
                        String TempW14req = week14ReqMatText.getText();
                        String TempW15req = week15ReqMatText.getText();
                        String TempCourseNotes = courseNotesAndTextBooksText.getText();
                        String TempSuggested = suggestedReadingsAndMaterialsText.getText();
                        String TempSHOOCNum = StudyHoursoutofClassNumText.getText();
                        String TempSHOOCDur = StudyHoursoutofClassDurText.getText();
                        String TempSHOOCWork = StudyHoursoutofClassWorkText.getText();
                        String TempFWNum = FieldWorkNumText.getText();
                        String TempFWDur = FieldWorkDurText.getText();
                        String TempFWWork = FieldWorkWorkText.getText();
                        String TempQuizNum = QuizNumText.getText();
                        String TempQuizDur = QuizDurText.getText();
                        String TempQuizWork =  QuizWorkText.getText();
                        String TempHWNum = HWNumText.getText();
                        String TempHWDur = HWDurText.getText();
                        String TempHWWork = HWWorkText.getText();
                        String TempPresentationNum = PresentationNumText.getText();
                        String TempPresentationDur = PresentationDurText.getText();
                        String TempPresentationWork = PresentationWorkText.getText();
                        String TempProjectNum = ProjectNumText.getText();
                        String TempProjectDur = ProjectDurText.getText();
                        String TempProjectWork = ProjectWorkText.getText();
                        String TempPortfolioNum = PortfolioNumText.getText();
                        String TempPortfolioDur = PortfolioDurText.getText();
                        String TempPortfolioWork= PortfolioWorkText.getText();
                        String TempSeminarNum = SeminarNumText.getText();
                        String TempSeminarDur = SeminarDurText.getText();
                        String TempSeminarWork = SeminarWorkText.getText();
                        String TempOralNum = OralExamNumText.getText();
                        String TempOralDur = OralExamDurText.getText();
                        String TempOralWork = OralExamWorkText.getText();
                        String TempMidtermNum = MidtermNumText.getText();
                        String TempMidtermDur = MidtermDurText.getText();
                        String TempMidtermWork = MidtermWorkText.getText();
                        String TempFinalNum = FinalExamNumText.getText();
                        String TempFinalDur = FinalExamDurText.getText();
                        String TempFinalWork = FinalExamWorkText.getText();


                        LectureConfig Lecture = new LectureConfig(lecture_id, TempName, TempLName, TempLCode, TempSemester, TempTHour, TempLHour, TempLCredit,
                                TempEcts, TempPrereq, TempLLang, TempLType, TempLLevel, TempTMethod, TempLCoordinator, TempAssistant, TempLObjective,
                                TempLOutcome, TempLDescription, TempLCategory,TempW1sub, TempW2sub, TempW3sub, TempW4sub, TempW5sub, TempW6sub,
                                TempW7sub, TempW8sub, TempW9sub, TempW10sub, TempW11sub, TempW12sub, TempW13sub, TempW14sub, TempW15sub, TempW1req,
                                TempW2req, TempW3req, TempW4req, TempW5req, TempW6req, TempW7req, TempW8req, TempW9req, TempW10req, TempW11req,
                                TempW12req, TempW13req, TempW14req, TempW15req, TempCourseNotes, TempSuggested,TempSHOOCNum, TempSHOOCDur, TempSHOOCWork, TempFWNum, TempFWDur, TempFWWork,
                                TempQuizNum, TempQuizDur, TempQuizWork, TempHWNum, TempHWDur, TempHWWork,
                                TempPresentationNum, TempPresentationDur, TempPresentationWork, TempProjectNum,
                                TempProjectDur, TempProjectWork, TempPortfolioNum, TempPortfolioDur, TempPortfolioWork,
                                TempSeminarNum, TempSeminarDur, TempSeminarWork, TempOralNum, TempOralDur,
                                TempOralWork, TempMidtermNum, TempMidtermDur, TempMidtermWork, TempFinalNum,
                                TempFinalDur, TempFinalWork);
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
                        String TempW1sub = Lecture.getWeek1Subjects();
                        String TempW2sub = Lecture.getWeek2Subjects();
                        String TempW3sub = Lecture.getWeek3Subjects();
                        String TempW4sub = Lecture.getWeek4Subjects();
                        String TempW5sub = Lecture.getWeek5Subjects();
                        String TempW6sub = Lecture.getWeek6Subjects();
                        String TempW7sub = Lecture.getWeek7Subjects();
                        String TempW8sub = Lecture.getWeek8Subjects();
                        String TempW9sub = Lecture.getWeek9Subjects();
                        String TempW10sub = Lecture.getWeek10Subjects();
                        String TempW11sub = Lecture.getWeek11Subjects();
                        String TempW12sub = Lecture.getWeek12Subjects();
                        String TempW13sub = Lecture.getWeek13Subjects();
                        String TempW14sub = Lecture.getWeek14Subjects();
                        String TempW15sub = Lecture.getWeek15Subjects();
                        String TempW1req = Lecture.getWeek1ReqMat();
                        String TempW2req = Lecture.getWeek2ReqMat();
                        String TempW3req = Lecture.getWeek3ReqMat();
                        String TempW4req = Lecture.getWeek4ReqMat();
                        String TempW5req = Lecture.getWeek5ReqMat();
                        String TempW6req = Lecture.getWeek6ReqMat();
                        String TempW7req = Lecture.getWeek7ReqMat();
                        String TempW8req = Lecture.getWeek8ReqMat();
                        String TempW9req = Lecture.getWeek9ReqMat();
                        String TempW10req = Lecture.getWeek10ReqMat();
                        String TempW11req = Lecture.getWeek11ReqMat();
                        String TempW12req = Lecture.getWeek12ReqMat();
                        String TempW13req = Lecture.getWeek13ReqMat();
                        String TempW14req = Lecture.getWeek14ReqMat();
                        String TempW15req = Lecture.getWeek15ReqMat();
                        String TempCourseNotes = Lecture.getCourseNotesAndTextBooks();
                        String TempSuggested = Lecture.getSuggestedReadingsAndMaterials();
                        String TempSHOOCNum = Lecture.getStudyHoursoutofClassNum();
                        String TempSHOOCDur = Lecture.getStudyHoursoutofClassDur();
                        String TempSHOOCWork = Lecture.getStudyHoursoutofClassWork();
                        String TempFWNum = Lecture.getFieldWorkNum();
                        String TempFWDur = Lecture.getFieldWorkDur();
                        String TempFWWork = Lecture.getFieldWorkWork();
                        String TempQuizNum = Lecture.getQuizNum();
                        String TempQuizDur = Lecture.getQuizDur();
                        String TempQuizWork =  Lecture.getQuizWork();
                        String TempHWNum = Lecture.getHWNum();
                        String TempHWDur = Lecture.getHWDur();
                        String TempHWWork = Lecture.getHWWork();
                        String TempPresentationNum = Lecture.getPresentationNum();
                        String TempPresentationDur = Lecture.getPresentationDur();
                        String TempPresentationWork = Lecture.getPresentationWork();
                        String TempProjectNum = Lecture.getProjectNum();
                        String TempProjectDur = Lecture.getProjectDur();
                        String TempProjectWork = Lecture.getProjectWork();
                        String TempPortfolioNum = Lecture.getPortfolioNum();
                        String TempPortfolioDur = Lecture.getPortfolioDur();
                        String TempPortfolioWork= Lecture.getPortfolioWork();
                        String TempSeminarNum = Lecture.getSeminarNum();
                        String TempSeminarDur = Lecture.getSeminarDur();
                        String TempSeminarWork = Lecture.getSeminarWork();
                        String TempOralNum = Lecture.getOralExamNum();
                        String TempOralDur = Lecture.getOralExamDur();
                        String TempOralWork = Lecture.getOralExamWork();
                        String TempMidtermNum = Lecture.getMidtermNum();
                        String TempMidtermDur = Lecture.getMidterDur();
                        String TempMidtermWork = Lecture.getMidterWork();
                        String TempFinalNum = Lecture.getFinalExamNum();
                        String TempFinalDur = Lecture.getFinalExamDur();
                        String TempFinalWork = Lecture.getFinalExamWork();

                        Label LectureID = new Label("Lecture ID :");
                        LectureGrid.add(LectureID, 1, 0);

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

                        Label week1SubjectsLabel = new Label("Week 1 Subjects:");
                        LectureGrid.add(week1SubjectsLabel, 0, 20);

                        Label week2SubjectsLabel = new Label("Week 2 Subjects:");
                        LectureGrid.add(week2SubjectsLabel, 0, 21);

                        Label week3SubjectsLabel = new Label("Week 3 Subjects:");
                        LectureGrid.add(week3SubjectsLabel, 0, 22);

                        Label week4SubjectsLabel = new Label("Week 4 Subjects:");
                        LectureGrid.add(week4SubjectsLabel, 0, 23);

                        Label week5SubjectsLabel = new Label("Week 5 Subjects:");
                        LectureGrid.add(week5SubjectsLabel, 0, 24);

                        Label week6SubjectsLabel = new Label("Week 6 Subjects:");
                        LectureGrid.add(week6SubjectsLabel, 0, 25);

                        Label week7SubjectsLabel = new Label("Week 7 Subjects:");
                        LectureGrid.add(week7SubjectsLabel, 0, 26);

                        Label week8SubjectsLabel = new Label("Week 8 Subjects:");
                        LectureGrid.add(week8SubjectsLabel, 0, 27);

                        Label week9SubjectsLabel = new Label("Week 9 Subjects:");
                        LectureGrid.add(week9SubjectsLabel, 0, 28);

                        Label week10SubjectsLabel = new Label("Week 10 Subjects:");
                        LectureGrid.add(week10SubjectsLabel, 0, 29);

                        Label week11SubjectsLabel = new Label("Week 11 Subjects:");
                        LectureGrid.add(week11SubjectsLabel, 0, 30);

                        Label week12SubjectsLabel = new Label("Week 12 Subjects:");
                        LectureGrid.add(week12SubjectsLabel, 0, 31);

                        Label week13SubjectsLabel = new Label("Week 13 Subjects:");
                        LectureGrid.add(week13SubjectsLabel, 0, 32);

                        Label week14SubjectsLabel = new Label("Week 14 Subjects:");
                        LectureGrid.add(week14SubjectsLabel, 0, 33);

                        Label week15SubjectsLabel = new Label("Week 15 Subjects:");
                        LectureGrid.add(week15SubjectsLabel, 0, 34);

                        Label week1ReqMatLabel = new Label("Week 1 Required Materials:");
                        LectureGrid.add(week1ReqMatLabel, 2, 20);

                        Label week2ReqMatLabel = new Label("Week 2 Required Materials:");
                        LectureGrid.add(week2ReqMatLabel, 2, 21);

                        Label week3ReqMatLabel = new Label("Week 3 Required Materials:");
                        LectureGrid.add(week3ReqMatLabel, 2, 22);

                        Label week4ReqMatLabel = new Label("Week 4 Required Materials:");
                        LectureGrid.add(week4ReqMatLabel, 2, 23);

                        Label week5ReqMatLabel = new Label("Week 5 Required Materials:");
                        LectureGrid.add(week5ReqMatLabel, 2, 24);

                        Label week6ReqMatLabel = new Label("Week 6 Required Materials:");
                        LectureGrid.add(week6ReqMatLabel, 2, 25);

                        Label week7ReqMatLabel = new Label("Week 7 Required Materials:");
                        LectureGrid.add(week7ReqMatLabel, 2, 26);

                        Label week8ReqMatLabel = new Label("Week 8 Required Materials:");
                        LectureGrid.add(week8ReqMatLabel, 2, 27);

                        Label week9ReqMatLabel = new Label("Week 9 Required Materials:");
                        LectureGrid.add(week9ReqMatLabel, 2, 28);

                        Label week10ReqMatLabel = new Label("Week 10 Required Materials:");
                        LectureGrid.add(week10ReqMatLabel, 2, 29);

                        Label week11ReqMatLabel = new Label("Week 11 Required Materials:");
                        LectureGrid.add(week11ReqMatLabel, 2, 30);

                        Label week12ReqMatLabel = new Label("Week 12 Required Materials:");
                        LectureGrid.add(week12ReqMatLabel, 2, 31);

                        Label week13ReqMatLabel = new Label("Week 13 Required Materials:");
                        LectureGrid.add(week13ReqMatLabel, 2, 32);

                        Label week14ReqMatLabel = new Label("Week 14 Required Materials:");
                        LectureGrid.add(week14ReqMatLabel, 2, 33);

                        Label week15ReqMatLabel = new Label("Week 15 Required Materials:");
                        LectureGrid.add(week15ReqMatLabel, 2, 34);

                        Label courseNotesAndTextBooksLabel = new Label("Course Notes and Textbooks:");
                        LectureGrid.add(courseNotesAndTextBooksLabel, 0, 35);

                        Label suggestedReadingsAndMaterialsLabel = new Label("Suggested Readings and Materials:");
                        LectureGrid.add(suggestedReadingsAndMaterialsLabel, 0, 36);

                        Label StudyHoursoutofClassNumLabel = new Label("Study Hours out of Class:");
                        LectureGrid.add(StudyHoursoutofClassNumLabel, 0, 113);

                        Label FieldWorkLabel = new Label("Field Work");
                        LectureGrid.add(FieldWorkLabel, 0, 114);

                        Label QuizLabel = new Label("Quiz/Studio Critique:");
                        LectureGrid.add(QuizLabel, 0, 115);

                        Label HWLabel = new Label("Homework/Assignments:");
                        LectureGrid.add(HWLabel, 0, 116);

                        Label PresentationLabel = new Label("Presentation/Jury::");
                        LectureGrid.add(PresentationLabel, 0, 117);

                        Label ProjectLabel = new Label("Project:");
                        LectureGrid.add(ProjectLabel, 0, 118);

                        Label PortfolioLabel = new Label("Portfolio:");
                        LectureGrid.add(PortfolioLabel, 0, 119);

                        Label SeminarLabel = new Label("Seminar/Workshop:");
                        LectureGrid.add(SeminarLabel, 0,120);

                        Label OralExamLabel = new Label("Oral Exam:");
                        LectureGrid.add(OralExamLabel, 0, 121);

                        Label MidtermLabel = new Label("Midterm:");
                        LectureGrid.add(MidtermLabel, 0, 122);

                        Label FinalLabel = new Label("Final Exam:");
                        LectureGrid.add(FinalLabel, 0, 123);

                        Label LectureIDtext = new Label(TempID);
                        LectureGrid.add(LectureIDtext, 2, 0);

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

                        Label week1SubjectsText = new Label(TempW1sub);
                        LectureGrid.add(week1SubjectsText, 1, 20);

                        Label week2SubjectsText = new Label(TempW2sub);
                        LectureGrid.add(week2SubjectsText, 1, 21);

                        Label week3SubjectsText = new Label(TempW3sub);
                        LectureGrid.add(week3SubjectsText, 1, 22);

                        Label week4SubjectsText = new Label(TempW4sub);
                        LectureGrid.add(week4SubjectsText, 1, 23);

                        Label week5SubjectsText = new Label(TempW5sub);
                        LectureGrid.add(week5SubjectsText, 1, 24);

                        Label week6SubjectsText = new Label(TempW6sub);
                        LectureGrid.add(week6SubjectsText, 1, 25);

                        Label week7SubjectsText = new Label(TempW7sub);
                        LectureGrid.add(week7SubjectsText, 1, 26);

                        Label week8SubjectsText = new Label(TempW8sub);
                        LectureGrid.add(week8SubjectsText, 1, 27);

                        Label week9SubjectsText = new Label(TempW9sub);
                        LectureGrid.add(week9SubjectsText, 1, 28);

                        Label week10SubjectsText = new Label(TempW10sub);
                        LectureGrid.add(week10SubjectsText, 1, 29);

                        Label week11SubjectsText =new Label(TempW11sub);
                        LectureGrid.add(week11SubjectsText, 1, 30);

                        Label week12SubjectsText = new Label(TempW12sub);
                        LectureGrid.add(week12SubjectsText, 1, 31);

                        Label week13SubjectsText = new Label(TempW13sub);
                        LectureGrid.add(week13SubjectsText, 1, 32);

                        Label week14SubjectsText = new Label(TempW14sub);
                        LectureGrid.add(week14SubjectsText, 1, 33);

                        Label week15SubjectsText =new Label(TempW15sub);
                        LectureGrid.add(week15SubjectsText, 1, 34);

                        Label week1ReqMatText = new Label(TempW1req);
                        LectureGrid.add(week1ReqMatText, 3, 20);

                        Label week2ReqMatText = new Label(TempW2req);
                        LectureGrid.add(week2ReqMatText, 3, 21);

                        Label week3ReqMatText = new Label(TempW3req);
                        LectureGrid.add(week3ReqMatText, 3, 22);

                        Label week4ReqMatText = new Label(TempW4req);
                        LectureGrid.add(week4ReqMatText, 3, 23);

                        Label week5ReqMatText = new Label(TempW5req);
                        LectureGrid.add(week5ReqMatText, 3, 24);

                        Label week6ReqMatText = new Label(TempW6req);
                        LectureGrid.add(week6ReqMatText, 3, 25);

                        Label week7ReqMatText = new Label(TempW7req);
                        LectureGrid.add(week7ReqMatText, 3, 26);

                        Label week8ReqMatText = new Label(TempW8req);
                        LectureGrid.add(week8ReqMatText, 3, 27);

                        Label week9ReqMatText = new Label(TempW9req);
                        LectureGrid.add(week9ReqMatText, 3, 28);

                        Label week10ReqMatText = new Label(TempW10req);
                        LectureGrid.add(week10ReqMatText, 3, 29);

                        Label week11ReqMatText = new Label(TempW11req);
                        LectureGrid.add(week11ReqMatText, 3, 30);

                        Label week12ReqMatText = new Label(TempW12req);
                        LectureGrid.add(week12ReqMatText, 3, 31);

                        Label week13ReqMatText = new Label(TempW13req);
                        LectureGrid.add(week13ReqMatText, 3, 32);

                        Label week14ReqMatText = new Label(TempW14req);
                        LectureGrid.add(week14ReqMatText, 3, 33);

                        Label week15ReqMatText = new Label(TempW15req);
                        LectureGrid.add(week15ReqMatText, 3, 34);

                        Label courseNotesAndTextBooksText = new Label(TempCourseNotes);
                        LectureGrid.add(courseNotesAndTextBooksText, 1, 35);

                        Label suggestedReadingsAndMaterialsText = new Label(TempSuggested);
                        LectureGrid.add(suggestedReadingsAndMaterialsText, 1, 36);

                        Label StudyHoursoutofClassNuMText = new Label(TempSHOOCNum);
                        LectureGrid.add(StudyHoursoutofClassNuMText, 1, 113);

                        Label StudyHoursoutofClassDurText = new Label(TempSHOOCDur);
                        LectureGrid.add(StudyHoursoutofClassDurText, 2, 113);

                        Label StudyHoursoutofClassWorkText = new Label(TempSHOOCWork);
                        LectureGrid.add(StudyHoursoutofClassWorkText, 3, 113);

                        Label FieldWorkNumText = new Label(TempFWNum);
                        LectureGrid.add(FieldWorkNumText, 1, 114);

                        Label FieldWorkDurText = new Label(TempFWDur);
                        LectureGrid.add(FieldWorkDurText, 2, 114);

                        Label FieldWorkWorkText = new Label(TempFWWork);
                        LectureGrid.add(FieldWorkWorkText, 3, 114);

                        Label QuizNumText = new Label(TempQuizNum);
                        LectureGrid.add(QuizNumText, 1, 115);

                        Label QuizDurText = new Label(TempQuizDur);
                        LectureGrid.add(QuizDurText, 2, 115);

                        Label QuizWorkText = new Label(TempQuizWork);
                        LectureGrid.add(QuizWorkText, 3, 115);

                        Label HWNumText = new Label(TempHWNum);
                        LectureGrid.add(HWNumText, 1, 116);

                        Label HWDurText = new Label(TempHWDur);
                        LectureGrid.add(HWDurText, 2, 116);

                        Label HWWorkText = new Label(TempHWWork);
                        LectureGrid.add(HWWorkText, 3, 116);

                        Label PresentationNumText = new Label(TempPresentationNum);
                        LectureGrid.add(PresentationNumText, 1, 117);

                        Label PresentationDurText = new Label(TempPresentationDur);
                        LectureGrid.add(PresentationDurText, 2, 117);

                        Label PresentationWorkText = new Label(TempPresentationWork);
                        LectureGrid.add(PresentationWorkText, 3, 117);

                        Label ProjectNumText = new Label(TempProjectNum);
                        LectureGrid.add(ProjectNumText, 1, 118);

                        Label ProjectDurText = new Label(TempProjectDur);
                        LectureGrid.add(ProjectDurText, 2, 118);

                        Label ProjectWorkText = new Label(TempProjectWork);
                        LectureGrid.add(ProjectWorkText, 3, 118);

                        Label PortfolioNumText = new Label(TempPortfolioNum);
                        LectureGrid.add(PortfolioNumText, 1, 119);

                        Label PortfolioDurText = new Label(TempPortfolioDur);
                        LectureGrid.add(PortfolioDurText, 2, 119);

                        Label PortfolioWorkText = new Label(TempPortfolioWork);
                        LectureGrid.add(PortfolioWorkText, 3, 119);

                        Label SeminarNumText = new Label(TempSeminarNum);
                        LectureGrid.add(SeminarNumText, 1,120);

                        Label SeminarDurText = new Label(TempSeminarDur);
                        LectureGrid.add(SeminarDurText, 2,120);

                        Label SeminarWorkText = new Label(TempSeminarWork);
                        LectureGrid.add(SeminarWorkText, 3,120);

                        Label OralExamNumText = new Label(TempOralNum);
                        LectureGrid.add(OralExamNumText, 1, 121);

                        Label OralExamDurText = new Label(TempOralDur);
                        LectureGrid.add(OralExamDurText, 2, 121);

                        Label OralExamWorkText = new Label(TempOralWork);
                        LectureGrid.add(OralExamWorkText, 3, 121);

                        Label MidtermNumText = new Label(TempMidtermNum);
                        LectureGrid.add(MidtermNumText, 1, 122);

                        Label MidtermDurText = new Label(TempMidtermDur);
                        LectureGrid.add(MidtermDurText, 2, 122);

                        Label MidtermWorkText = new Label(TempMidtermWork);
                        LectureGrid.add(MidtermWorkText, 3, 122);

                        Label FinalNumText = new Label(TempFinalNum);
                        LectureGrid.add(FinalNumText, 1, 123);

                        Label FinalDurText = new Label(TempFinalDur);
                        LectureGrid.add(FinalDurText, 2, 123);

                        Label FinalWorkText = new Label(TempFinalWork);
                        LectureGrid.add(FinalWorkText, 3, 123);

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
              /*  String TempLCode = Lecture.getLecture_Code();
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
                String TempW1sub = Lecture.getWeek1Subjects();
                String TempW2sub = Lecture.getWeek2Subjects();
                String TempW3sub = Lecture.getWeek3Subjects();
                String TempW4sub = Lecture.getWeek4Subjects();
                String TempW5sub = Lecture.getWeek5Subjects();
                String TempW6sub = Lecture.getWeek6Subjects();
                String TempW7sub = Lecture.getWeek7Subjects();
                String TempW8sub = Lecture.getWeek8Subjects();
                String TempW9sub = Lecture.getWeek9Subjects();
                String TempW10sub = Lecture.getWeek10Subjects();
                String TempW11sub = Lecture.getWeek11Subjects();
                String TempW12sub = Lecture.getWeek12Subjects();
                String TempW13sub = Lecture.getWeek13Subjects();
                String TempW14sub = Lecture.getWeek14Subjects();
                String TempW15sub = Lecture.getWeek15Subjects();
                String TempW1req = Lecture.getWeek1ReqMat();
                String TempW2req = Lecture.getWeek2ReqMat();
                String TempW3req = Lecture.getWeek3ReqMat();
                String TempW4req = Lecture.getWeek4ReqMat();
                String TempW5req = Lecture.getWeek5ReqMat();
                String TempW6req = Lecture.getWeek6ReqMat();
                String TempW7req = Lecture.getWeek7ReqMat();
                String TempW8req = Lecture.getWeek8ReqMat();
                String TempW9req = Lecture.getWeek9ReqMat();
                String TempW10req = Lecture.getWeek10ReqMat();
                String TempW11req = Lecture.getWeek11ReqMat();
                String TempW12req = Lecture.getWeek12ReqMat();
                String TempW13req = Lecture.getWeek13ReqMat();
                String TempW14req = Lecture.getWeek14ReqMat();
                String TempW15req = Lecture.getWeek15ReqMat();
                String TempCourseNotes = Lecture.getCourseNotesAndTextBooks();
                String TempSuggested = Lecture.getSuggestedReadingsAndMaterials();*/

                Label LectureID = new Label("Lecture ID :");
                EditLectureoldvalue.add(LectureID, 0, 0);

                Label lectureNameLabel = new Label("Lecture Name:");
                EditLectureoldvalue.add(lectureNameLabel, 0, 1);

                Label lecturerNameLabel = new Label("Lecturer's Name:");
                EditLectureoldvalue.add(lecturerNameLabel, 0, 2);

                /*Label lectureCodeLabel = new Label("Lecture Code:");
                EditLectureoldvalue.add(lectureCodeLabel, 0, 3);

                Label semesterLabel = new Label("Semester:");
                EditLectureoldvalue.add(semesterLabel, 0, 4);

                Label theoryHourLabel = new Label("Theory Hour:");
                EditLectureoldvalue.add(theoryHourLabel, 0, 5);

                Label labHourLabel = new Label("Lab Hour:");
                EditLectureoldvalue.add(labHourLabel, 0, 6);

                Label localCreditLabel = new Label("Local Credit:");
                EditLectureoldvalue.add(localCreditLabel, 0, 7);

                Label ECTSLabel = new Label("Ects:");
                EditLectureoldvalue.add(ECTSLabel, 0, 8);

                Label prerequisitesLabel = new Label("Prerequisites:");
                EditLectureoldvalue.add(prerequisitesLabel, 0, 9);

                Label course_LanguageLabel = new Label("Lecture Language:");
                EditLectureoldvalue.add(course_LanguageLabel, 0, 10);

                Label course_TypeLabel = new Label("Lecture Type:");
                EditLectureoldvalue.add(course_TypeLabel, 0, 11);

                Label course_LevelLabel = new Label("Lecture Level:");
                EditLectureoldvalue.add(course_LevelLabel, 0, 12);

                Label teaching_MethodsLabel = new Label("Teaching Method:");
                EditLectureoldvalue.add(teaching_MethodsLabel, 0, 13);

                Label course_CoordinatorLabel = new Label("Lecture Coordinator:");
                EditLectureoldvalue.add(course_CoordinatorLabel, 0, 14);

                Label assistantLabel = new Label("Lecture Assistant:");
                EditLectureoldvalue.add(assistantLabel, 0, 15);

                Label course_ObjectivesLabel = new Label("Lecture Objectives:");
                EditLectureoldvalue.add(course_ObjectivesLabel, 0, 16);

                Label learning_OutcomesLabel = new Label("Learning Outcome:");
                EditLectureoldvalue.add(learning_OutcomesLabel, 0, 17);

                Label course_DescriptionLabel = new Label("Lecture Description:");
                EditLectureoldvalue.add(course_DescriptionLabel, 0,18);

                Label course_CategoryLabel = new Label("Lecture Category:");
                EditLectureoldvalue.add(course_CategoryLabel, 0, 19);

                Label week1SubjectsLabel = new Label("Week 1 Subjects:");
                EditLectureoldvalue.add(week1SubjectsLabel, 0, 20);

                Label week2SubjectsLabel = new Label("Week 2 Subjects:");
                EditLectureoldvalue.add(week2SubjectsLabel, 0, 21);

                Label week3SubjectsLabel = new Label("Week 3 Subjects:");
                EditLectureoldvalue.add(week3SubjectsLabel, 0, 22);

                Label week4SubjectsLabel = new Label("Week 4 Subjects:");
                EditLectureoldvalue.add(week4SubjectsLabel, 0, 23);

                Label week5SubjectsLabel = new Label("Week 5 Subjects:");
                EditLectureoldvalue.add(week5SubjectsLabel, 0, 24);

                Label week6SubjectsLabel = new Label("Week 6 Subjects:");
                EditLectureoldvalue.add(week6SubjectsLabel, 0, 25);

                Label week7SubjectsLabel = new Label("Week 7 Subjects:");
                EditLectureoldvalue.add(week7SubjectsLabel, 0, 26);

                Label week8SubjectsLabel = new Label("Week 8 Subjects:");
                EditLectureoldvalue.add(week8SubjectsLabel, 0, 27);

                Label week9SubjectsLabel = new Label("Week 9 Subjects:");
                EditLectureoldvalue.add(week9SubjectsLabel, 0, 28);

                Label week10SubjectsLabel = new Label("Week 10 Subjects:");
                EditLectureoldvalue.add(week10SubjectsLabel, 0, 29);

                Label week11SubjectsLabel = new Label("Week 11 Subjects:");
                EditLectureoldvalue.add(week11SubjectsLabel, 0, 30);

                Label week12SubjectsLabel = new Label("Week 12 Subjects:");
                EditLectureoldvalue.add(week12SubjectsLabel, 0, 31);

                Label week13SubjectsLabel = new Label("Week 13 Subjects:");
                EditLectureoldvalue.add(week13SubjectsLabel, 0, 32);

                Label week14SubjectsLabel = new Label("Week 14 Subjects:");
                EditLectureoldvalue.add(week14SubjectsLabel, 0, 33);

                Label week15SubjectsLabel = new Label("Week 15 Subjects:");
                EditLectureoldvalue.add(week15SubjectsLabel, 0, 34);

                Label week1ReqMatLabel = new Label("Week 1 Required Materials:");
                EditLectureoldvalue.add(week1ReqMatLabel, 2, 20);

                Label week2ReqMatLabel = new Label("Week 2 Required Materials:");
                EditLectureoldvalue.add(week2ReqMatLabel, 2, 21);

                Label week3ReqMatLabel = new Label("Week 3 Required Materials:");
                EditLectureoldvalue.add(week3ReqMatLabel, 2, 22);

                Label week4ReqMatLabel = new Label("Week 4 Required Materials:");
                EditLectureoldvalue.add(week4ReqMatLabel, 2, 23);

                Label week5ReqMatLabel = new Label("Week 5 Required Materials:");
                EditLectureoldvalue.add(week5ReqMatLabel, 2, 24);

                Label week6ReqMatLabel = new Label("Week 6 Required Materials:");
                EditLectureoldvalue.add(week6ReqMatLabel, 2, 25);

                Label week7ReqMatLabel = new Label("Week 7 Required Materials:");
                EditLectureoldvalue.add(week7ReqMatLabel, 2, 26);

                Label week8ReqMatLabel = new Label("Week 8 Required Materials:");
                EditLectureoldvalue.add(week8ReqMatLabel, 2, 27);

                Label week9ReqMatLabel = new Label("Week 9 Required Materials:");
                EditLectureoldvalue.add(week9ReqMatLabel, 2, 28);

                Label week10ReqMatLabel = new Label("Week 10 Required Materials:");
                EditLectureoldvalue.add(week10ReqMatLabel, 2, 29);

                Label week11ReqMatLabel = new Label("Week 11 Required Materials:");
                EditLectureoldvalue.add(week11ReqMatLabel, 2, 30);

                Label week12ReqMatLabel = new Label("Week 12 Required Materials:");
                EditLectureoldvalue.add(week12ReqMatLabel, 2, 31);

                Label week13ReqMatLabel = new Label("Week 13 Required Materials:");
                EditLectureoldvalue.add(week13ReqMatLabel, 2, 32);

                Label week14ReqMatLabel = new Label("Week 14 Required Materials:");
                EditLectureoldvalue.add(week14ReqMatLabel, 2, 33);

                Label week15ReqMatLabel = new Label("Week 15 Required Materials:");
                EditLectureoldvalue.add(week15ReqMatLabel, 2, 34);

                Label courseNotesAndTextBooksLabel = new Label("Course Notes and Textbooks:");
                EditLectureoldvalue.add(courseNotesAndTextBooksLabel, 0, 35);

                Label suggestedReadingsAndMaterialsLabel = new Label("Suggested Readings and Materials:");
                EditLectureoldvalue.add(suggestedReadingsAndMaterialsLabel,0 , 36);*/

                Label LectureIDtext = new Label(TempID);
                EditLectureoldvalue.add(LectureIDtext, 1, 0);

                Label LectureNameText = new Label(TempLectureName);
                EditLectureoldvalue.add(LectureNameText, 1, 1);

                Label LecturersNameText = new Label(TempLecturerName);
                EditLectureoldvalue.add(LecturersNameText, 1, 2);

               /* Label lectureCodeText = new Label(TempLCode);
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

                Label week1SubjectsText = new Label(TempW1sub);
                LectureGrid.add(week1SubjectsText, 1, 20);

                Label week2SubjectsText = new Label(TempW2sub);
                LectureGrid.add(week2SubjectsText, 1, 21);

                Label week3SubjectsText = new Label(TempW3sub);
                LectureGrid.add(week3SubjectsText, 1, 22);

                Label week4SubjectsText = new Label(TempW4sub);
                LectureGrid.add(week4SubjectsText, 1, 23);

                Label week5SubjectsText = new Label(TempW5sub);
                LectureGrid.add(week5SubjectsText, 1, 24);

                Label week6SubjectsText = new Label(TempW6sub);
                LectureGrid.add(week6SubjectsText, 1, 25);

                Label week7SubjectsText = new Label(TempW7sub);
                LectureGrid.add(week7SubjectsText, 1, 26);

                Label week8SubjectsText = new Label(TempW8sub);
                LectureGrid.add(week8SubjectsText, 1, 27);

                Label week9SubjectsText = new Label(TempW9sub);
                LectureGrid.add(week9SubjectsText, 1, 28);

                Label week10SubjectsText = new Label(TempW10sub);
                LectureGrid.add(week10SubjectsText, 1, 29);

                Label week11SubjectsText =new Label(TempW11sub);
                LectureGrid.add(week11SubjectsText, 1, 30);

                Label week12SubjectsText = new Label(TempW12sub);
                LectureGrid.add(week12SubjectsText, 1, 31);

                Label week13SubjectsText = new Label(TempW13sub);
                LectureGrid.add(week13SubjectsText, 1, 32);

                Label week14SubjectsText = new Label(TempW14sub);
                LectureGrid.add(week14SubjectsText, 1, 33);

                Label week15SubjectsText =new Label(TempW15sub);
                LectureGrid.add(week15SubjectsText, 1, 34);

                Label week1ReqMatText = new Label(TempW1req);
                LectureGrid.add(week1ReqMatText, 3, 20);

                Label week2ReqMatText = new Label(TempW2req);
                LectureGrid.add(week2ReqMatText, 3, 21);

                Label week3ReqMatText = new Label(TempW3req);
                LectureGrid.add(week3ReqMatText, 3, 22);

                Label week4ReqMatText = new Label(TempW4req);
                LectureGrid.add(week4ReqMatText, 3, 23);

                Label week5ReqMatText = new Label(TempW5req);
                LectureGrid.add(week5ReqMatText, 3, 24);

                Label week6ReqMatText = new Label(TempW6req);
                LectureGrid.add(week6ReqMatText, 3, 25);

                Label week7ReqMatText = new Label(TempW7req);
                LectureGrid.add(week7ReqMatText, 3, 26);

                Label week8ReqMatText = new Label(TempW8req);
                LectureGrid.add(week8ReqMatText, 3, 27);

                Label week9ReqMatText = new Label(TempW9req);
                LectureGrid.add(week9ReqMatText, 3, 28);

                Label week10ReqMatText = new Label(TempW10req);
                LectureGrid.add(week10ReqMatText, 3, 29);

                Label week11ReqMatText = new Label(TempW11req);
                LectureGrid.add(week11ReqMatText, 3, 30);

                Label week12ReqMatText = new Label(TempW12req);
                LectureGrid.add(week12ReqMatText, 3, 31);

                Label week13ReqMatText = new Label(TempW13req);
                LectureGrid.add(week13ReqMatText, 3, 32);

                Label week14ReqMatText = new Label(TempW14req);
                LectureGrid.add(week14ReqMatText, 3, 33);

                Label week15ReqMatText = new Label(TempW15req);
                LectureGrid.add(week15ReqMatText, 3, 34);

                Label courseNotesAndTextBooksText = new Label(TempCourseNotes);
                LectureGrid.add(courseNotesAndTextBooksText, 1, 35);

                Label suggestedReadingsAndMaterialsText = new Label(TempSuggested);
                LectureGrid.add(suggestedReadingsAndMaterialsText, 1, 36);*/

                Label NewLectureID = new Label("Lecture ID :");
                EditLecturenewvalue.add(NewLectureID, 0, 0);

                Label NewlectureNameLabel = new Label("Lecture Name:");
                EditLecturenewvalue.add(NewlectureNameLabel, 0, 1);

                Label NewlecturerNameLabel = new Label("Lecturer's Name:");
                EditLecturenewvalue.add(NewlecturerNameLabel, 0, 2);

               /* Label NewlectureCodeLabel = new Label("Lecture Code:");
                EditLectureoldvalue.add(NewlectureCodeLabel, 0, 3);

                Label NewsemesterLabel = new Label("Semester:");
                EditLectureoldvalue.add(NewsemesterLabel, 0, 4);

                Label NewtheoryHourLabel = new Label("Theory Hour:");
                EditLectureoldvalue.add(NewtheoryHourLabel, 0, 5);

                Label NewlabHourLabel = new Label("Lab Hour:");
                EditLectureoldvalue.add(NewlabHourLabel, 0, 6);

                Label NewlocalCreditLabel = new Label("Local Credit:");
                EditLectureoldvalue.add(NewlocalCreditLabel, 0, 7);

                Label NewECTSLabel = new Label("Ects:");
                EditLectureoldvalue.add(NewECTSLabel, 0, 8);

                Label NewprerequisitesLabel = new Label("Prerequisites:");
                EditLectureoldvalue.add(NewprerequisitesLabel, 0, 9);

                Label Newcourse_LanguageLabel = new Label("Lecture Language:");
                EditLectureoldvalue.add(Newcourse_LanguageLabel, 0, 10);

                Label Newcourse_TypeLabel = new Label("Lecture Type:");
                EditLectureoldvalue.add(Newcourse_TypeLabel, 0, 11);

                Label Newcourse_LevelLabel = new Label("Lecture Level:");
                EditLectureoldvalue.add(Newcourse_LevelLabel, 0, 12);

                Label Newteaching_MethodsLabel = new Label("Teaching Method:");
                EditLectureoldvalue.add(Newteaching_MethodsLabel, 0, 13);

                Label Newcourse_CoordinatorLabel = new Label("Lecture Coordinator:");
                EditLectureoldvalue.add(Newcourse_CoordinatorLabel, 0, 14);

                Label NewassistantLabel = new Label("Lecture Assistant:");
                EditLectureoldvalue.add(NewassistantLabel, 0, 15);

                Label Newcourse_ObjectivesLabel = new Label("Lecture Objectives:");
                EditLectureoldvalue.add(Newcourse_ObjectivesLabel, 0, 16);

                Label Newlearning_OutcomesLabel = new Label("Learning Outcome:");
                EditLectureoldvalue.add(Newlearning_OutcomesLabel, 0, 17);

                Label Newcourse_DescriptionLabel = new Label("Lecture Description:");
                EditLectureoldvalue.add(Newcourse_DescriptionLabel, 0,18);

                Label Newcourse_CategoryLabel = new Label("Lecture Category:");
                EditLectureoldvalue.add(Newcourse_CategoryLabel, 0, 19);

                Label Newweek1SubjectsLabel = new Label("Week 1 Subjects:");
                EditLectureoldvalue.add(Newweek1SubjectsLabel, 0, 20);

                Label Newweek2SubjectsLabel = new Label("Week 2 Subjects:");
                EditLectureoldvalue.add(Newweek2SubjectsLabel, 0, 21);

                Label Newweek3SubjectsLabel = new Label("Week 3 Subjects:");
                EditLectureoldvalue.add(Newweek3SubjectsLabel, 0, 22);

                Label Newweek4SubjectsLabel = new Label("Week 4 Subjects:");
                EditLectureoldvalue.add(Newweek4SubjectsLabel, 0, 23);

                Label Newweek5SubjectsLabel = new Label("Week 5 Subjects:");
                EditLectureoldvalue.add(Newweek5SubjectsLabel, 0, 24);

                Label Newweek6SubjectsLabel = new Label("Week 6 Subjects:");
                EditLectureoldvalue.add(Newweek6SubjectsLabel, 0, 25);

                Label Newweek7SubjectsLabel = new Label("Week 7 Subjects:");
                EditLectureoldvalue.add(Newweek7SubjectsLabel, 0, 26);

                Label Newweek8SubjectsLabel = new Label("Week 8 Subjects:");
                EditLectureoldvalue.add(Newweek8SubjectsLabel, 0, 27);

                Label Newweek9SubjectsLabel = new Label("Week 9 Subjects:");
                EditLectureoldvalue.add(Newweek9SubjectsLabel, 0, 28);

                Label Newweek10SubjectsLabel = new Label("Week 10 Subjects:");
                EditLectureoldvalue.add(Newweek10SubjectsLabel, 0, 29);

                Label Newweek11SubjectsLabel = new Label("Week 11 Subjects:");
                EditLectureoldvalue.add(Newweek11SubjectsLabel, 0, 30);

                Label Newweek12SubjectsLabel = new Label("Week 12 Subjects:");
                EditLectureoldvalue.add(Newweek12SubjectsLabel, 0, 31);

                Label Newweek13SubjectsLabel = new Label("Week 13 Subjects:");
                EditLectureoldvalue.add(Newweek13SubjectsLabel, 0, 32);

                Label Newweek14SubjectsLabel = new Label("Week 14 Subjects:");
                EditLectureoldvalue.add(Newweek14SubjectsLabel, 0, 33);

                Label Newweek15SubjectsLabel = new Label("Week 15 Subjects:");
                EditLectureoldvalue.add(Newweek15SubjectsLabel, 0, 34);

                Label Newweek1ReqMatLabel = new Label("Week 1 Required Materials:");
                EditLectureoldvalue.add(Newweek1ReqMatLabel, 2, 20);

                Label Newweek2ReqMatLabel = new Label("Week 2 Required Materials:");
                EditLectureoldvalue.add(Newweek2ReqMatLabel, 2, 21);

                Label Newweek3ReqMatLabel = new Label("Week 3 Required Materials:");
                EditLectureoldvalue.add(Newweek3ReqMatLabel, 2, 22);

                Label Newweek4ReqMatLabel = new Label("Week 4 Required Materials:");
                EditLectureoldvalue.add(Newweek4ReqMatLabel, 2, 23);

                Label Newweek5ReqMatLabel = new Label("Week 5 Required Materials:");
                EditLectureoldvalue.add(Newweek5SubjectsLabel, 2, 24);

                Label Newweek6ReqMatLabel = new Label("Week 6 Required Materials:");
                EditLectureoldvalue.add(Newweek6ReqMatLabel, 2, 25);

                Label Newweek7ReqMatLabel = new Label("Week 7 Required Materials:");
                EditLectureoldvalue.add(Newweek7ReqMatLabel, 2, 26);

                Label Newweek8ReqMatLabel = new Label("Week 8 Required Materials:");
                EditLectureoldvalue.add(Newweek8ReqMatLabel, 2, 27);

                Label Newweek9ReqMatLabel = new Label("Week 9 Required Materials:");
                EditLectureoldvalue.add(Newweek9ReqMatLabel, 2, 28);

                Label Newweek10ReqMatLabel = new Label("Week 10 Required Materials:");
                EditLectureoldvalue.add(Newweek10ReqMatLabel, 2, 29);

                Label Newweek11ReqMatLabel = new Label("Week 11 Required Materials:");
                EditLectureoldvalue.add(Newweek11ReqMatLabel, 2, 30);

                Label Newweek12ReqMatLabel = new Label("Week 12 Required Materials:");
                EditLectureoldvalue.add(Newweek12ReqMatLabel, 2, 31);

                Label Newweek13ReqMatLabel = new Label("Week 13 Required Materials:");
                EditLectureoldvalue.add(Newweek13ReqMatLabel, 2, 32);

                Label Newweek14ReqMatLabel = new Label("Week 14 Required Materials:");
                EditLectureoldvalue.add(Newweek14ReqMatLabel, 2, 33);

                Label Newweek15ReqMatLabel = new Label("Week 15 Required Materials:");
                EditLectureoldvalue.add(Newweek15ReqMatLabel, 2, 34);

                Label NewcourseNotesAndTextBooksLabel = new Label("Course Notes and Textbooks:");
                EditLectureoldvalue.add(NewcourseNotesAndTextBooksLabel, 0, 35);

                Label NewsuggestedReadingsAndMaterialsLabel = new Label("Suggested Readings and Materials:");
                EditLectureoldvalue.add(NewsuggestedReadingsAndMaterialsLabel,0 , 36);*/


                TextField NewLectureIDtext = new TextField(TempID);
                NewLectureIDtext.setEditable(false);
                EditLecturenewvalue.add(NewLectureIDtext, 1, 0);

                TextField NewLectureNameText = new TextField();
                EditLecturenewvalue.add(NewLectureNameText, 1, 1);

                TextField NewLecturersNameText = new TextField();
                EditLecturenewvalue.add(NewLecturersNameText, 1, 2);

               /* TextField NewLectureCodeText = new TextField();
                EditLecturenewvalue.add(NewLectureCodeText, 1, 3);

                TextField NewSemesterText = new TextField();
                EditLecturenewvalue.add(NewSemesterText, 1, 4);

                TextField NewTheoryHourText = new TextField();
                EditLecturenewvalue.add(NewTheoryHourText, 1, 5);

                TextField NewLabHourText = new TextField();
                EditLecturenewvalue.add(NewLabHourText, 1, 6);

                TextField NewLocalCreditText = new TextField();
                EditLecturenewvalue.add(NewLocalCreditText, 1, 7);

                TextField NewEctsText = new TextField();
                EditLecturenewvalue.add(NewEctsText, 1, 8);

                TextField NewPrerequisitesText = new TextField();
                EditLecturenewvalue.add(NewPrerequisitesText, 1, 9);

                TextField NewLectureLanguageText = new TextField();
                EditLecturenewvalue.add(NewLectureLanguageText, 1, 10);

                TextField NewLectureTypeText = new TextField();
                EditLecturenewvalue.add(NewLectureTypeText, 1, 11);

                TextField NewLectureLevelText = new TextField();
                EditLecturenewvalue.add(NewLectureLevelText, 1, 12);

                TextField NewTeachingMethodText = new TextField();
                EditLecturenewvalue.add(NewTeachingMethodText, 1, 13);

                TextField NewLectureCoordinatorText = new TextField();
                EditLecturenewvalue.add(NewLectureCoordinatorText, 1, 14);

                TextField NewAssistantText = new TextField();
                EditLecturenewvalue.add(NewAssistantText, 1, 15);

                TextField NewLectureObjectiveText = new TextField();
                EditLecturenewvalue.add(NewLectureObjectiveText, 1, 16);

                TextField NewLearningOutcomeText = new TextField();
                EditLecturenewvalue.add(NewLearningOutcomeText, 1, 17);

                TextField NewLectureDescriptionText = new TextField();
                EditLecturenewvalue.add(NewLectureDescriptionText, 1, 18);

                TextField NewLectureCategoryText = new TextField();
                EditLecturenewvalue.add(NewLectureCategoryText, 1, 19);

                TextField Newweek1SubjectsText = new TextField();
                EditLecturenewvalue.add(Newweek1SubjectsText, 1, 20);

                TextField Newweek2SubjectsText = new TextField();
                EditLecturenewvalue.add(Newweek2SubjectsText, 1, 21);

                TextField Newweek3SubjectsText = new TextField();
                EditLecturenewvalue.add(Newweek3SubjectsText, 1, 22);

                TextField Newweek4SubjectsText = new TextField();
                EditLecturenewvalue.add(Newweek4SubjectsText, 1, 23);

                TextField Newweek5SubjectsText = new TextField();
                EditLecturenewvalue.add(Newweek5SubjectsText, 1, 24);

                TextField Newweek6SubjectsText = new TextField();
                EditLecturenewvalue.add(Newweek6SubjectsText, 1, 25);

                TextField Newweek7SubjectsText = new TextField();
                EditLecturenewvalue.add(Newweek7SubjectsText, 1, 26);

                TextField Newweek8SubjectsText = new TextField();
                EditLecturenewvalue.add(Newweek8SubjectsText, 1, 27);

                TextField Newweek9SubjectsText = new TextField();
                EditLecturenewvalue.add(Newweek9SubjectsText, 1, 28);

                TextField Newweek10SubjectsText = new TextField();
                EditLecturenewvalue.add(Newweek10SubjectsText, 1, 29);

                TextField Newweek11SubjectsText = new TextField();
                EditLecturenewvalue.add(Newweek11SubjectsText, 1, 30);

                TextField Newweek12SubjectsText = new TextField();
                EditLecturenewvalue.add(Newweek12SubjectsText, 1, 31);

                TextField Newweek13SubjectsText = new TextField();
                EditLecturenewvalue.add(Newweek13SubjectsText, 1, 32);

                TextField Newweek14SubjectsText = new TextField();
                EditLecturenewvalue.add(Newweek14SubjectsText, 1, 33);

                TextField Newweek15SubjectsText = new TextField();
                EditLecturenewvalue.add(Newweek15SubjectsText, 1, 34);

                TextField Newweek1ReqMatText = new TextField();
                EditLecturenewvalue.add(Newweek1ReqMatText, 3, 20);

                TextField Newweek2ReqMatText = new TextField();
                EditLecturenewvalue.add(Newweek2ReqMatText, 3, 21);

                TextField Newweek3ReqMatText = new TextField();
                EditLecturenewvalue.add(Newweek3ReqMatText, 3, 22);

                TextField Newweek4ReqMatText = new TextField();
                EditLecturenewvalue.add(Newweek4ReqMatText, 3, 23);

                TextField Newweek5ReqMatText = new TextField();
                EditLecturenewvalue.add(Newweek5ReqMatText, 3, 24);

                TextField Newweek6ReqMatText = new TextField();
                EditLecturenewvalue.add(Newweek6ReqMatText ,3, 25);

                TextField Newweek7ReqMatText = new TextField();
                EditLecturenewvalue.add(Newweek7ReqMatText, 3, 26);

                TextField Newweek8ReqMatText = new TextField();
                EditLecturenewvalue.add(Newweek8ReqMatText, 3, 27);

                TextField Newweek9ReqMatText = new TextField();
                EditLecturenewvalue.add(Newweek9ReqMatText, 3, 28);

                TextField Newweek10ReqMatText = new TextField();
                EditLecturenewvalue.add(Newweek10ReqMatText, 3, 29);

                TextField Newweek11ReqMatText = new TextField();
                EditLecturenewvalue.add(Newweek11ReqMatText, 3, 30);

                TextField Newweek12ReqMatText = new TextField();
                EditLecturenewvalue.add(Newweek12ReqMatText, 3, 31);

                TextField Newweek13ReqMatText = new TextField();
                EditLecturenewvalue.add(Newweek13ReqMatText, 3, 32);

                TextField Newweek14ReqMatText = new TextField();
                EditLecturenewvalue.add(Newweek14ReqMatText, 3, 33);

                TextField Newweek15ReqMatText = new TextField();
                EditLecturenewvalue.add(Newweek15ReqMatText, 3, 34);

                TextField NewcourseNotesAndTextBooksText = new TextField();
                EditLecturenewvalue.add(NewcourseNotesAndTextBooksText, 1, 35);

                TextField NewsuggestedReadingsAndMaterialsText = new TextField();
                EditLecturenewvalue.add(NewsuggestedReadingsAndMaterialsText, 1, 36);*/



                EditLectureConfirm.setOnAction(event -> {
                        LectureConfig LectureNew = new LectureConfig(lec_id, NewLectureNameText.getText(), NewLecturersNameText.getText());/* NewLectureCodeText.getText(),NewSemesterText.getText(),NewTheoryHourText.getText(),NewLabHourText.getText(), NewLocalCreditText.getText(), NewEctsText.getText(), NewPrerequisitesText.getText(), NewLectureLanguageText.getText(),
                                NewLectureTypeText.getText(), NewLectureLevelText.getText(), NewTeachingMethodText.getText(), NewLectureCoordinatorText.getText(),NewAssistantText.getText(),
                                NewLectureObjectiveText.getText(),
                                NewLearningOutcomeText.getText(),
                                NewLectureDescriptionText.getText(),
                                NewLectureCategoryText.getText(),
                                Newweek1SubjectsText.getText(),
                                Newweek2SubjectsText.getText(),
                                Newweek3SubjectsText.getText(),
                                Newweek4SubjectsText.getText(),
                                Newweek5SubjectsText.getText(),
                                Newweek6SubjectsText.getText(),
                                Newweek7SubjectsText.getText(),
                                Newweek8SubjectsText.getText(),
                                Newweek9SubjectsText.getText(),
                                Newweek10SubjectsText.getText(),
                                Newweek11SubjectsText.getText(),
                                Newweek12SubjectsText.getText(),
                                Newweek13SubjectsText.getText(),
                                Newweek14SubjectsText.getText(),
                                Newweek15SubjectsText.getText(),
                                Newweek1ReqMatText.getText(),
                                Newweek2ReqMatText.getText(),
                                Newweek3ReqMatText.getText(),
                                Newweek4ReqMatText.getText(),
                                Newweek5ReqMatText.getText(),
                                Newweek6ReqMatText.getText(),
                                Newweek7ReqMatText.getText(),
                                Newweek8ReqMatText.getText(),
                                Newweek9ReqMatText.getText(),
                                Newweek10ReqMatText.getText(),
                                Newweek11ReqMatText.getText(),
                                Newweek12ReqMatText.getText(),
                                Newweek13ReqMatText.getText(),
                                Newweek14ReqMatText.getText(),
                                Newweek15ReqMatText.getText(),
                                NewcourseNotesAndTextBooksText.getText(),
                                NewsuggestedReadingsAndMaterialsText.getText());*/
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
