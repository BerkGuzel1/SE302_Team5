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
        private HBox AddLectureBox;
        @FXML
        private HBox EditLectureHBox;

        @FXML
        private Button AddLectureButton;

        @FXML
        private GridPane AddLectureGrid;
        @FXML
        private Button EditLectureConfirm;
        @FXML
        private Button CloseEditLecture;
        @FXML
        private TableColumn LectureGoColumn;

        @FXML
        private GridPane LectureGrid;
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
        private HBox allHbox;

        @FXML
        private HBox firstEllipses;

        @FXML
        private HBox secondEllipses;

        @FXML
        private HBox thirdEllipses;

        @FXML
        private ScrollPane generatedResumeScrollPane2;

        int lec_id = -1;

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
                EditLectureHBox.setVisible(false);
                AddLectureBox.setVisible(false);
                mediaHbox.setVisible(false);


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

                AddLectureBox.setVisible(true);


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

                Label numberLabel = new Label("NUMBER");
                numberLabel.setStyle("-fx-background-color: grey; -fx-padding: 5px;-fx-font-weight: bold;-fx-font-size: 10px;");
                AddLectureGrid.add(numberLabel, 1, 38);

                Label durationLabel = new Label("WEIGHTING");
                durationLabel.setStyle("-fx-background-color: grey; -fx-padding: 5px;-fx-font-weight: bold;-fx-font-size: 10px;");
                AddLectureGrid.add(durationLabel, 2, 38);

                Label lo1 = new Label("LO1");
                lo1.setStyle("-fx-background-color: grey; -fx-padding: 5px;-fx-font-weight: bold;-fx-font-size: 10px;");
                AddLectureGrid.add(lo1, 3, 38);

                Label lo2 = new Label("LO2");
                lo2.setStyle("-fx-background-color: grey; -fx-padding: 5px;-fx-font-weight: bold;-fx-font-size: 10px;");
                AddLectureGrid.add(lo2, 4, 38);

                Label lo3 = new Label("LO3");
                lo3.setStyle("-fx-background-color: grey; -fx-padding: 5px;-fx-font-weight: bold;-fx-font-size: 10px;");
                AddLectureGrid.add(lo3, 5, 38);

                Label lo4 = new Label("LO4");
                lo4.setStyle("-fx-background-color: grey; -fx-padding: 5px;-fx-font-weight: bold;-fx-font-size: 10px;");
                AddLectureGrid.add(lo4, 6, 38);

                Label participationNumberLabel = new Label("Participation:");
                AddLectureGrid.add(participationNumberLabel, 0, 39);

                Label labOrApplicationNumberLabel = new Label("Laboratory or Application:");
                AddLectureGrid.add(labOrApplicationNumberLabel, 0, 40);

                Label fieldWorkNumberLabel = new Label("Field Work:");
                AddLectureGrid.add(fieldWorkNumberLabel, 0, 41);

                Label quizOrStudioCritiqueNumberLabel = new Label("Quiz or Studio Critique:");
                AddLectureGrid.add(quizOrStudioCritiqueNumberLabel, 0, 42);

                Label homeworkOrAssignmentNumberLabel = new Label("Homework or Assignment:");
                AddLectureGrid.add(homeworkOrAssignmentNumberLabel, 0, 43);

                Label presentationOrJuryNumberLabel = new Label("Presentation or Jury:");
                AddLectureGrid.add(presentationOrJuryNumberLabel, 0, 44);

                Label projectNumberLabel = new Label("Project:");
                AddLectureGrid.add(projectNumberLabel, 0, 45);

                Label portfolioNumberLabel = new Label("Portfolio:");
                AddLectureGrid.add(portfolioNumberLabel, 0, 46);

                Label seminarOrWorkshopNumberLabel = new Label("Seminar or Workshop:");
                AddLectureGrid.add(seminarOrWorkshopNumberLabel, 0, 47);

                Label oralExamNumberLabel = new Label("Oral Exam:");
                AddLectureGrid.add(oralExamNumberLabel, 0, 48);

                Label midtermNumberLabel = new Label("Midterm:");
                AddLectureGrid.add(midtermNumberLabel, 0, 49);

                Label finalExamNumberLabel = new Label("Final Exam:");
                AddLectureGrid.add(finalExamNumberLabel, 0, 50);

                Label ECTSWORKLOADTABLE = new Label("4.ECTS / WORKLOAD TABLE");
                ECTSWORKLOADTABLE.setStyle("-fx-background-color: orange; -fx-padding: 5px;-fx-font-weight: bold;-fx-font-size: 15px;");
                AddLectureGrid.add(ECTSWORKLOADTABLE,0,51);

                Label numberLabel1 = new Label("NUMBER");
                numberLabel1.setStyle("-fx-background-color: grey; -fx-padding: 5px;-fx-font-weight: bold;-fx-font-size: 10px;");
                AddLectureGrid.add(numberLabel1, 1, 51);

                Label durationLabel1 = new Label("DURATION (HOURS)");
                durationLabel1.setStyle("-fx-background-color: grey; -fx-padding: 5px;-fx-font-weight: bold;-fx-font-size: 10px;");
                AddLectureGrid.add(durationLabel1, 2, 51);

                Label workloadLabel = new Label("WORKLOAD");
                workloadLabel.setStyle("-fx-background-color: grey; -fx-padding: 5px;-fx-font-weight: bold;-fx-font-size: 10px;");
                AddLectureGrid.add(workloadLabel, 3, 51);

                Label CourseHoursLabel = new Label("Course Hours\n(Including exam week: 16 x total hours):");
                AddLectureGrid.add(CourseHoursLabel, 0, 52);

                Label CourseHours16= new Label("16");
                AddLectureGrid.add(CourseHours16, 1, 52);

                Label LabAppHoursLabel = new Label("Laboratory/Application Hours\n(Including exam week: 16 x total hours)\n");
                AddLectureGrid.add(LabAppHoursLabel, 0, 53);

                Label LabHours16= new Label("16");
                AddLectureGrid.add(LabHours16, 1, 53);

                Label StudyHoursoutofClassNumLabel = new Label("Study Hours out of Class:");
                AddLectureGrid.add(StudyHoursoutofClassNumLabel, 0, 54);

                Label FieldWorkLabel = new Label("Field Work");
                AddLectureGrid.add(FieldWorkLabel, 0, 55);

                Label QuizLabel = new Label("Quiz/Studio Critique:");
                AddLectureGrid.add(QuizLabel, 0, 56);

                Label HWLabel = new Label("Homework/Assignments:");
                AddLectureGrid.add(HWLabel, 0, 57);

                Label PresentationLabel = new Label("Presentation/Jury::");
                AddLectureGrid.add(PresentationLabel, 0, 58);

                Label ProjectLabel = new Label("Project:");
                AddLectureGrid.add(ProjectLabel, 0, 59);

                Label PortfolioLabel = new Label("Portfolio:");
                AddLectureGrid.add(PortfolioLabel, 0, 60);

                Label SeminarLabel = new Label("Seminar/Workshop:");
                AddLectureGrid.add(SeminarLabel, 0,61);

                Label OralExamLabel = new Label("Oral Exam:");
                AddLectureGrid.add(OralExamLabel, 0, 62);

                Label MidtermLabel = new Label("Midterm:");
                AddLectureGrid.add(MidtermLabel, 0, 63);

                Label FinalLabel = new Label("Final Exam:");
                AddLectureGrid.add(FinalLabel, 0, 64);

                Label COURSEPROGRAMOUTCOMEMATRIX = new Label("5.COURSE/PROGRAM OUTCOME MATRIX");
                COURSEPROGRAMOUTCOMEMATRIX.setStyle("-fx-background-color: orange; -fx-padding: 5px;-fx-font-weight: bold;-fx-font-size: 15px;");
                AddLectureGrid.add(COURSEPROGRAMOUTCOMEMATRIX,0,65);

                /*Label Contribution1Label = new Label("Contribution Level 1 ");
                Contribution1Label.setStyle("-fx-background-color: grey; -fx-padding: 10px;-fx-font-weight: bold;-fx-font-size: 8px;");
                AddLectureGrid.add(Contribution1Label, 1, 65);*/

                Label point1label = new Label("1 point");
                point1label.setStyle("-fx-background-color: grey; -fx-padding: 5px;-fx-font-weight: bold;-fx-font-size: 10px;");
                AddLectureGrid.add(point1label, 1, 65);

                Label point2label= new Label("2 point");
                point2label.setStyle("-fx-background-color: grey; -fx-padding: 5px;-fx-font-weight: bold;-fx-font-size: 10px;");
                AddLectureGrid.add(point2label, 2, 65);

                Label point3label = new Label("3 point");
                point3label.setStyle("-fx-background-color: grey; -fx-padding: 5px;-fx-font-weight: bold;-fx-font-size: 10px;");
                AddLectureGrid.add(point3label, 3, 65);

                Label point4label = new Label("4 point");
                point4label.setStyle("-fx-background-color: grey; -fx-padding: 5px;-fx-font-weight: bold;-fx-font-size: 10px;");
                AddLectureGrid.add(point4label, 4, 65);

                Label point5label = new Label("5 point");
                point5label.setStyle("-fx-background-color: grey; -fx-padding: 5px;-fx-font-weight: bold;-fx-font-size: 10px;");
                AddLectureGrid.add(point5label, 5, 65);

                Label o1 = new Label("To have adequate knowledge in Mathematics,Science and Computer Engineering; to be able to use theoretical and applied information in these areas on complex engineering problems");
                o1.setStyle("-fx-background-color: white; -fx-padding: 5px;-fx-font-weight: bold;-fx-font-size: 10px;");
                AddLectureGrid.add(o1, 0, 66);

                Label o2 = new Label("To be able to identify, define, formulate, and solve complex Computer Engineering problems; to be able to select and apply proper analysis and modeling methods for this purpose.");
                o2.setStyle("-fx-background-color: white; -fx-padding: 5px;-fx-font-weight: bold;-fx-font-size: 10px;");
                AddLectureGrid.add(o2, 0, 67);

                Label o3 = new Label("To be able to design a complex system, process, device or product under realistic constraints and conditions, in such a way as to meet the requirements; to be able to apply modern design methods for this purpose.");
                o3.setStyle("-fx-background-color: white; -fx-padding: 5px;-fx-font-weight: bold;-fx-font-size: 10px;");
                AddLectureGrid.add(o3, 0, 68);

                Label o4 = new Label("To be able to devise, select, and use modern techniques and tools needed for analysis and solution of complex problems in Computer Engineering applications; to be able to use information technologies effectively.");
                o4.setStyle("-fx-background-color: white; -fx-padding: 5px;-fx-font-weight: bold;-fx-font-size: 10px;");
                AddLectureGrid.add(o4, 0, 69);

                Label o5 = new Label("To be able to design and conduct experiments, gather data, analyze, and interpret results for investigating complex engineering problems or Computer Engineering research topics.");
                o5.setStyle("-fx-background-color: white; -fx-padding: 5px;-fx-font-weight: bold;-fx-font-size: 10px;");
                AddLectureGrid.add(o5, 0, 70);

                Label o6 = new Label("To be able to work efficiently in Computer Engineering disciplinary and multi-disciplinary teams;to be able to work individually.\n");
                o6.setStyle("-fx-background-color: white; -fx-padding: 5px;-fx-font-weight: bold;-fx-font-size: 10px;");
                AddLectureGrid.add(o6, 0, 71);

                Label o7 = new Label("To be able to communicate effectively in Turkish,both orally and in writing; to be able to author and comprehend written reports, to be able to prepare design and implementation reports, to present effectively, to be able to give and receive clear and comprehensible instructions.");
                o7.setStyle("-fx-background-color: white; -fx-padding: 5px;-fx-font-weight: bold;-fx-font-size: 10px;");
                AddLectureGrid.add(o7, 0, 72);

                Label o8 = new Label("To have knowledge about global and social impact of Computer Engineering practices on health, environment, and safety; to have knowledge about contemporary issues as they pertain to engineering; to be aware of the legal ramifications of Computer Engineering solutions.");
                o8.setStyle("-fx-background-color: white; -fx-padding: 5px;-fx-font-weight: bold;-fx-font-size: 10px;");
                AddLectureGrid.add(o8, 0, 73);

                Label o9 = new Label("To be aware of ethical behavior, professional and ethical responsibility; to have knowledge about standards utilized in engineering applications.");
                o9.setStyle("-fx-background-color: white; -fx-padding: 5px;-fx-font-weight: bold;-fx-font-size: 10px;");
                AddLectureGrid.add(o9, 0, 74);

                Label o10 = new Label("To have knowledge about industrial practices such as project management, risk management, and change management; to have awareness of entrepreneurship and innovation; to have knowledge about sustainable development.");
                o10.setStyle("-fx-background-color: white; -fx-padding: 5px;-fx-font-weight: bold;-fx-font-size: 10px;");
                AddLectureGrid.add(o10, 0, 75);

                Label o11 = new Label("To be able to collect data in the area of Computer Engineering, and to be able to communicate with colleagues in a foreign language. (European Language Portfolio Global Scale, Level B1)");
                o11.setStyle("-fx-background-color: white; -fx-padding: 5px;-fx-font-weight: bold;-fx-font-size: 10px;");
                AddLectureGrid.add(o11, 0, 76);

                Label o12 = new Label("To be able to speak a second foreign language at a medium level of fluency efficiently.\n");
                o12.setStyle("-fx-background-color: white; -fx-padding: 5px;-fx-font-weight: bold;-fx-font-size: 10px;");
                AddLectureGrid.add(o12, 0, 77);

                Label o13 = new Label("To recognize the need for lifelong learning; to be able to access information, to be able to stay current with developments in science and technology; to be able to relate the knowledge accumulated throughout the human history to Computer Engineering.");
                o13.setStyle("-fx-background-color: white; -fx-padding: 5px;-fx-font-weight: bold;-fx-font-size: 10px;");
                AddLectureGrid.add(o13, 0, 78);

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

                TextField partNumText = new TextField();
                AddLectureGrid.add(partNumText, 1, 39);

                TextField partWeiText = new TextField();
                AddLectureGrid.add(partWeiText, 2, 39);

                TextField partL01Text = new TextField();
                AddLectureGrid.add(partL01Text, 3, 39);

                TextField partL02Text = new TextField();
                AddLectureGrid.add(partL02Text, 4, 39);

                TextField partL03Text = new TextField();
                AddLectureGrid.add(partL03Text, 5, 39);

                TextField partL04Text = new TextField();
                AddLectureGrid.add(partL04Text, 6, 39);

                TextField labNumbText = new TextField();
                AddLectureGrid.add(labNumbText, 1, 40);

                TextField labWeiText = new TextField();
                AddLectureGrid.add(labWeiText, 2, 40);

                TextField labL01Text = new TextField();
                AddLectureGrid.add(labL01Text, 3, 40);

                TextField labL02Text = new TextField();
                AddLectureGrid.add(labL02Text, 4, 40);

                TextField labL03Text = new TextField();
                AddLectureGrid.add(labL03Text, 5, 40);

                TextField labL04Text = new TextField();
                AddLectureGrid.add(labL04Text, 6, 40);

                TextField fwNumbText = new TextField();
                AddLectureGrid.add(fwNumbText, 1, 41);

                TextField fwWeiText = new TextField();
                AddLectureGrid.add(fwWeiText, 2, 41);

                TextField fwL01Text = new TextField();
                AddLectureGrid.add(fwL01Text, 3, 41);

                TextField fwL02Text = new TextField();
                AddLectureGrid.add(fwL02Text, 4, 41);

                TextField fwL03Text = new TextField();
                AddLectureGrid.add(fwL03Text, 5, 41);

                TextField fwL04Text = new TextField();
                AddLectureGrid.add(fwL04Text, 6, 41);

                TextField quizNumbText = new TextField();
                AddLectureGrid.add(quizNumbText, 1, 42);

                TextField quizWeiText = new TextField();
                AddLectureGrid.add(quizWeiText, 2, 42);

                TextField quizL01Text = new TextField();
                AddLectureGrid.add(quizL01Text, 3, 42);

                TextField quizL02Text = new TextField();
                AddLectureGrid.add(quizL02Text, 4, 42);

                TextField quizL03Text = new TextField();
                AddLectureGrid.add(quizL03Text, 5, 42);

                TextField quizL04Text = new TextField();
                AddLectureGrid.add(quizL04Text, 6, 42);

                TextField HWNumbText = new TextField();
                AddLectureGrid.add(HWNumbText, 1, 43);

                TextField HWWeiText = new TextField();
                AddLectureGrid.add(HWWeiText, 2, 43);

                TextField HWL01Text = new TextField();
                AddLectureGrid.add(HWL01Text, 3, 43);

                TextField HWL02Text = new TextField();
                AddLectureGrid.add(HWL02Text, 4, 43);

                TextField HWL03Text = new TextField();
                AddLectureGrid.add(HWL03Text, 5, 43);

                TextField HWL04Text = new TextField();
                AddLectureGrid.add(HWL04Text, 6, 43);

                TextField PresNumbText = new TextField();
                AddLectureGrid.add(PresNumbText, 1, 44);

                TextField PresWeiText = new TextField();
                AddLectureGrid.add(PresWeiText, 2, 44);

                TextField PresL01Text = new TextField();
                AddLectureGrid.add(PresL01Text, 3, 44);

                TextField PresL02Text = new TextField();
                AddLectureGrid.add(PresL02Text, 4, 44);

                TextField PresL03Text = new TextField();
                AddLectureGrid.add(PresL03Text, 5, 44);

                TextField PresL04Text = new TextField();
                AddLectureGrid.add(PresL04Text, 6, 44);

                TextField ProjNumbText = new TextField();
                AddLectureGrid.add (ProjNumbText, 1, 45);

                TextField ProjWeiText = new TextField();
                AddLectureGrid.add(ProjWeiText, 2, 45);

                TextField ProjL01Text = new TextField();
                AddLectureGrid.add(ProjL01Text, 3, 45);

                TextField ProjL02Text = new TextField();
                AddLectureGrid.add(ProjL02Text, 4, 45);

                TextField ProjL03Text = new TextField();
                AddLectureGrid.add(ProjL03Text, 5, 45);

                TextField ProjL04Text = new TextField();
                AddLectureGrid.add(ProjL04Text, 6, 45);

                TextField PortfolioNumbText = new TextField();
                AddLectureGrid.add (PortfolioNumbText, 1, 46);

                TextField PortfolioWeiText = new TextField();
                AddLectureGrid.add(PortfolioWeiText, 2, 46);

                TextField PortfolioL01Text = new TextField();
                AddLectureGrid.add(PortfolioL01Text, 3, 46);

                TextField PortfolioL02Text = new TextField();
                AddLectureGrid.add(PortfolioL02Text, 4, 46);

                TextField PortfolioL03Text = new TextField();
                AddLectureGrid.add(PortfolioL03Text, 5, 46);

                TextField PortfolioL04Text = new TextField();
                AddLectureGrid.add(PortfolioL04Text, 6, 46);

                TextField SemNumbText = new TextField();
                AddLectureGrid.add(SemNumbText, 1, 47);

                TextField SemWeiText = new TextField();
                AddLectureGrid.add(SemWeiText, 2, 47);

                TextField SemL01Text = new TextField();
                AddLectureGrid.add(SemL01Text, 3, 47);

                TextField SemL02Text = new TextField();
                AddLectureGrid.add(SemL02Text, 4, 47);

                TextField SemL03Text = new TextField();
                AddLectureGrid.add(SemL03Text, 5, 47);

                TextField SemL04Text = new TextField();
                AddLectureGrid.add(SemL04Text, 6, 47);

                TextField OralNumbText = new TextField();
                AddLectureGrid.add(OralNumbText, 1, 48);

                TextField OralWeiText = new TextField();
                AddLectureGrid.add(OralWeiText, 2, 48);

                TextField OralL01Text = new TextField();
                AddLectureGrid.add(OralL01Text, 3, 48);

                TextField OralL02Text = new TextField();
                AddLectureGrid.add(OralL02Text, 4, 48);

                TextField OralL03Text = new TextField();
                AddLectureGrid.add(OralL03Text, 5, 48);

                TextField OralL04Text = new TextField();
                AddLectureGrid.add(OralL04Text, 6, 48);

                TextField MidtermNumbText = new TextField();
                AddLectureGrid.add(MidtermNumbText, 1, 49);

                TextField MidtermWeiText = new TextField();
                AddLectureGrid.add(MidtermWeiText, 2, 49);

                TextField MidtermL01Text = new TextField();
                AddLectureGrid.add(MidtermL01Text, 3, 49);

                TextField MidtermL02Text = new TextField();
                AddLectureGrid.add(MidtermL02Text, 4, 49);

                TextField MidtermL03Text = new TextField();
                AddLectureGrid.add(MidtermL03Text, 5, 49);

                TextField MidtermL04Text = new TextField();
                AddLectureGrid.add(MidtermL04Text, 6, 49);

                TextField FinalNumbText = new TextField();
                AddLectureGrid.add(FinalNumbText, 1, 50);

                TextField FinalWeiText = new TextField();
                AddLectureGrid.add(FinalWeiText, 2, 50);

                TextField FinalL01Text = new TextField();
                AddLectureGrid.add(FinalL01Text, 3, 50);

                TextField FinalL02Text = new TextField();
                AddLectureGrid.add(FinalL02Text, 4, 50);

                TextField FinalL03Text = new TextField();
                AddLectureGrid.add(FinalL03Text, 5, 50);

                TextField FinalL04Text = new TextField();
                AddLectureGrid.add(FinalL04Text, 6, 50);

                TextField CourseHourDurText = new TextField();
                AddLectureGrid.add(CourseHourDurText, 2, 52);

                TextField CourseHourWorkText = new TextField();
                AddLectureGrid.add(CourseHourWorkText, 3, 52);

                TextField LabHoursDurText = new TextField();
                AddLectureGrid.add(LabHoursDurText, 2, 53);

                TextField LabHoursWorkText = new TextField();
                AddLectureGrid.add(LabHoursWorkText, 3, 53);

                TextField StudyHoursoutofClassNumText = new TextField();
                AddLectureGrid.add(StudyHoursoutofClassNumText, 1, 54);

                TextField StudyHoursoutofClassDurText = new TextField();
                AddLectureGrid.add(StudyHoursoutofClassDurText, 2, 54);

                TextField StudyHoursoutofClassWorkText = new TextField();
                AddLectureGrid.add(StudyHoursoutofClassWorkText, 3, 54);

                TextField FieldWorkNumText = new TextField();
                AddLectureGrid.add(FieldWorkNumText, 1, 55);

                TextField FieldWorkDurText = new TextField();
                AddLectureGrid.add(FieldWorkDurText, 2, 55);

                TextField FieldWorkWorkText = new TextField();
                AddLectureGrid.add(FieldWorkWorkText, 3, 55);

                TextField QuizNumText = new TextField();
                AddLectureGrid.add(QuizNumText, 1, 56);

                TextField QuizWorkText = new TextField();
                AddLectureGrid.add(QuizWorkText, 3, 56);

                TextField QuizDurText = new TextField();
                AddLectureGrid.add(QuizDurText, 2, 56);

                TextField HWNumText = new TextField();
                AddLectureGrid.add(HWNumText, 1, 57);

                TextField HWDurText = new TextField();
                AddLectureGrid.add(HWDurText, 2, 57);

                TextField HWWorkText = new TextField();
                AddLectureGrid.add(HWWorkText, 3, 57);

                TextField PresentationNumText = new TextField();
                AddLectureGrid.add(PresentationNumText, 1, 58);

                TextField PresentationDurText = new TextField();
                AddLectureGrid.add(PresentationDurText, 2, 58);

                TextField PresentationWorkText = new TextField();
                AddLectureGrid.add(PresentationWorkText, 3, 58);

                TextField ProjectNumText = new TextField();
                AddLectureGrid.add(ProjectNumText, 1, 59);

                TextField ProjectDurText = new TextField();
                AddLectureGrid.add(ProjectDurText, 2, 59);

                TextField ProjectWorkText = new TextField();
                AddLectureGrid.add(ProjectWorkText, 3, 59);

                TextField PortfolioNumText = new TextField();
                AddLectureGrid.add(PortfolioNumText, 1, 60);

                TextField PortfolioDurText = new TextField();
                AddLectureGrid.add(PortfolioDurText, 2, 60);

                TextField PortfolioWorkText = new TextField();
                AddLectureGrid.add(PortfolioWorkText, 3, 60);

                TextField SeminarNumText = new TextField();
                AddLectureGrid.add(SeminarNumText, 1, 61);

                TextField SeminarDurText = new TextField();
                AddLectureGrid.add(SeminarDurText, 2, 61);

                TextField SeminarWorkText = new TextField();
                AddLectureGrid.add(SeminarWorkText, 3, 61);

                TextField OralExamNumText = new TextField();
                AddLectureGrid.add(OralExamNumText, 1, 62);

                TextField OralExamDurText = new TextField();
                AddLectureGrid.add(OralExamDurText, 2, 62);

                TextField OralExamWorkText = new TextField();
                AddLectureGrid.add(OralExamWorkText, 3, 62);

                TextField MidtermNumText = new TextField();
                AddLectureGrid.add(MidtermNumText, 1, 63);

                TextField MidtermDurText = new TextField();
                AddLectureGrid.add(MidtermDurText, 2, 63);

                TextField MidtermWorkText = new TextField();
                AddLectureGrid.add(MidtermWorkText, 3, 63);

                TextField FinalExamNumText = new TextField();
                AddLectureGrid.add(FinalExamNumText, 1, 64);

                TextField FinalExamDurText = new TextField();
                AddLectureGrid.add(FinalExamDurText, 2, 64);

                TextField FinalExamWorkText = new TextField();
                AddLectureGrid.add(FinalExamWorkText, 3, 64);

                TextField outcome1_1 = new TextField();
                AddLectureGrid.add(outcome1_1, 1, 66);

                TextField outcome1_2 = new TextField();
                AddLectureGrid.add(outcome1_2, 2, 66);

                TextField outcome1_3 = new TextField();
                AddLectureGrid.add(outcome1_3, 3, 66);

                TextField outcome1_4 = new TextField();
                AddLectureGrid.add(outcome1_4, 4, 66);

                TextField outcome1_5 = new TextField();
                AddLectureGrid.add(outcome1_5, 5, 66);

                TextField outcome2_1 = new TextField();
                AddLectureGrid.add(outcome2_1, 1, 67);

                TextField outcome2_2 = new TextField();
                AddLectureGrid.add(outcome2_2, 2, 67);

                TextField outcome2_3 = new TextField();
                AddLectureGrid.add(outcome2_3, 3, 67);

                TextField outcome2_4 = new TextField();
                AddLectureGrid.add(outcome2_4, 4, 67);

                TextField outcome2_5 = new TextField();
                AddLectureGrid.add(outcome2_5, 5, 67);

                TextField outcome3_1 = new TextField();
                AddLectureGrid.add(outcome3_1, 1, 68);

                TextField outcome3_2 = new TextField();
                AddLectureGrid.add(outcome3_2, 2, 68);

                TextField outcome3_3 = new TextField();
                AddLectureGrid.add(outcome3_3, 3, 68);

                TextField outcome3_4 = new TextField();
                AddLectureGrid.add(outcome3_4, 4, 68);

                TextField outcome3_5 = new TextField();
                AddLectureGrid.add(outcome3_5, 5, 68);

                TextField outcome4_1 = new TextField();
                AddLectureGrid.add(outcome4_1, 1, 69);

                TextField outcome4_2 = new TextField();
                AddLectureGrid.add(outcome4_2, 2, 69);

                TextField outcome4_3 = new TextField();
                AddLectureGrid.add(outcome4_3, 3, 69);

                TextField outcome4_4 = new TextField();
                AddLectureGrid.add(outcome4_4, 4, 69);

                TextField outcome4_5 = new TextField();
                AddLectureGrid.add(outcome4_5, 5, 69);

                TextField outcome5_1 = new TextField();
                AddLectureGrid.add(outcome5_1, 1, 70);

                TextField outcome5_2 = new TextField();
                AddLectureGrid.add(outcome5_2, 2, 70);

                TextField outcome5_3 = new TextField();
                AddLectureGrid.add(outcome5_3, 3, 70);

                TextField outcome5_4 = new TextField();
                AddLectureGrid.add(outcome5_4, 4, 70);

                TextField outcome5_5 = new TextField();
                AddLectureGrid.add(outcome5_5, 5, 70);

                TextField outcome6_1 = new TextField();
                AddLectureGrid.add(outcome6_1, 1, 71);

                TextField outcome6_2 = new TextField();
                AddLectureGrid.add(outcome6_2, 2, 71);

                TextField outcome6_3 = new TextField();
                AddLectureGrid.add(outcome6_3, 3, 71);

                TextField outcome6_4 = new TextField();
                AddLectureGrid.add(outcome6_4, 4, 71);

                TextField outcome6_5 = new TextField();
                AddLectureGrid.add(outcome6_5, 5, 71);

                TextField outcome7_1 = new TextField();
                AddLectureGrid.add(outcome7_1, 1, 72);

                TextField outcome7_2 = new TextField();
                AddLectureGrid.add(outcome7_2, 2, 72);

                TextField outcome7_3 = new TextField();
                AddLectureGrid.add(outcome7_3, 3, 72);

                TextField outcome7_4 = new TextField();
                AddLectureGrid.add(outcome7_4, 4, 72);

                TextField outcome7_5 = new TextField();
                AddLectureGrid.add(outcome7_5, 5, 72);

                TextField outcome8_1 = new TextField();
                AddLectureGrid.add(outcome8_1, 1, 73);

                TextField outcome8_2 = new TextField();
                AddLectureGrid.add(outcome8_2, 2, 73);

                TextField outcome8_3 = new TextField();
                AddLectureGrid.add(outcome8_3, 3, 73);

                TextField outcome8_4 = new TextField();
                AddLectureGrid.add(outcome8_4, 4, 73);

                TextField outcome8_5 = new TextField();
                AddLectureGrid.add(outcome8_5, 5, 73);

                TextField outcome9_1 = new TextField();
                AddLectureGrid.add(outcome9_1, 1, 74);

                TextField outcome9_2 = new TextField();
                AddLectureGrid.add(outcome9_2, 2, 74);

                TextField outcome9_3 = new TextField();
                AddLectureGrid.add(outcome9_3, 3, 74);

                TextField outcome9_4 = new TextField();
                AddLectureGrid.add(outcome9_4, 4, 74);

                TextField outcome9_5 = new TextField();
                AddLectureGrid.add(outcome9_5, 5, 74);

                TextField outcome10_1 = new TextField();
                AddLectureGrid.add(outcome10_1, 1, 75);

                TextField outcome10_2 = new TextField();
                AddLectureGrid.add(outcome10_2, 2, 75);

                TextField outcome10_3 = new TextField();
                AddLectureGrid.add(outcome10_3, 3, 75);

                TextField outcome10_4 = new TextField();
                AddLectureGrid.add(outcome10_4, 4, 75);

                TextField outcome10_5 = new TextField();
                AddLectureGrid.add(outcome10_5, 5, 75);

                TextField outcome11_1 = new TextField();
                AddLectureGrid.add(outcome11_1, 1, 76);

                TextField outcome11_2 = new TextField();
                AddLectureGrid.add(outcome11_2, 2, 76);

                TextField outcome11_3 = new TextField();
                AddLectureGrid.add(outcome11_3, 3, 76);

                TextField outcome11_4 = new TextField();
                AddLectureGrid.add(outcome11_4, 4, 76);

                TextField outcome11_5 = new TextField();
                AddLectureGrid.add(outcome11_5, 5, 76);

                TextField outcome12_1 = new TextField();
                AddLectureGrid.add(outcome12_1, 1, 77);

                TextField outcome12_2 = new TextField();
                AddLectureGrid.add(outcome12_2, 2, 77);

                TextField outcome12_3 = new TextField();
                AddLectureGrid.add(outcome12_3, 3, 77);

                TextField outcome12_4 = new TextField();
                AddLectureGrid.add(outcome12_4, 4, 77);

                TextField outcome12_5 = new TextField();
                AddLectureGrid.add(outcome12_5, 5, 77);

                TextField outcome13_1 = new TextField();
                AddLectureGrid.add(outcome13_1, 1, 78);

                TextField outcome13_2 = new TextField();
                AddLectureGrid.add(outcome13_2, 2, 78);

                TextField outcome13_3 = new TextField();
                AddLectureGrid.add(outcome13_3, 3, 78);

                TextField outcome13_4 = new TextField();
                AddLectureGrid.add(outcome13_4, 4, 78);

                TextField outcome13_5 = new TextField();
                AddLectureGrid.add(outcome13_5, 5, 78);


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
                        String TempPartNumb = partNumText.getText();
                        String TempPartWei = partWeiText.getText();
                        String TempPartL01 = partL01Text.getText();
                        String TempPartL02 = partL02Text.getText();
                        String TempPartL03 = partL03Text.getText();
                        String TempPartL04 = partL04Text.getText();
                        String TempLabNumb = labNumbText.getText();
                        String TempLabWei = labWeiText.getText();
                        String TempLabL01 = labL01Text.getText();
                        String TempLabL02 = labL02Text.getText();
                        String TempLabL03 = labL03Text.getText();
                        String TempLabL04 = labL04Text.getText();
                        String TempFWNumb = fwNumbText.getText();
                        String TempFWWei = fwWeiText.getText();
                        String TempFWL01 = fwL01Text.getText();
                        String TempFWL02 = fwL02Text.getText();
                        String TempFWL03 = fwL03Text.getText();
                        String TempFWL04 = fwL04Text.getText();
                        String TempQuizNumb = quizNumbText.getText();
                        String TempQuizWei = quizWeiText.getText();
                        String TempQuizL01 = quizL01Text.getText();
                        String TempQuizL02 = quizL02Text.getText();
                        String TempQuizL03 = quizL03Text.getText();
                        String TempQuizL04 = quizL04Text.getText();
                        String TempHWNumb = HWNumbText.getText();
                        String TempHWWei = HWWeiText.getText();
                        String TempHWL01 = HWL01Text.getText();
                        String TempHWL02 = HWL02Text.getText();
                        String TempHWL03 = HWL03Text.getText();
                        String TempHWL04 = HWL04Text.getText();
                        String TempPresNumb = PresNumbText.getText();
                        String TempPresWei = PresWeiText.getText();
                        String TempPresL01 = PresL01Text.getText();
                        String TempPresL02 = PresL02Text.getText();
                        String TempPresL03 = PresL03Text.getText();
                        String TempPresL04 = PresL04Text.getText();
                        String TempProjNumb = ProjNumbText.getText();
                        String TempProjWei = ProjWeiText.getText();
                        String TempProjL01 = ProjL01Text.getText();
                        String TempProjL02 = ProjL02Text.getText();
                        String TempProjL03 = ProjL03Text.getText();
                        String TempProjL04 = ProjL04Text.getText();
                        String TempPortNumb = PortfolioNumbText.getText();
                        String TempPortWei = PortfolioWeiText.getText();
                        String TempPortL01 = PortfolioL01Text.getText();
                        String TempPortL02 = PortfolioL02Text.getText();
                        String TempPortL03 = PortfolioL03Text.getText();
                        String TempPortL04 = PortfolioL04Text.getText();
                        String TempSEMNumb = SemNumbText.getText();
                        String TempSEMWei = SemWeiText.getText();
                        String TempSEML01 = SemL01Text.getText();
                        String TempSEML02 = SemL02Text.getText();
                        String TempSEML03 = SemL03Text.getText();
                        String TempSEML04 = SemL04Text.getText();
                        String TempORALNumb = OralNumbText.getText();
                        String TempORALWei = OralWeiText.getText();
                        String TempORALL01 = OralL01Text.getText();
                        String TempORALL02 = OralL02Text.getText();
                        String TempORALL03 = OralL03Text.getText();
                        String TempORALL04 = OralL04Text.getText();
                        String TempMIDNumb = MidtermNumbText.getText();
                        String TempMIDWei = MidtermWeiText.getText();
                        String TempMIDL01 = MidtermL01Text.getText();
                        String TempMIDL02 = MidtermL02Text.getText();
                        String TempMIDL03 = MidtermL03Text.getText();
                        String TempMIDL04 = MidtermL04Text.getText();
                        String TempFINALNumb = FinalNumbText.getText();
                        String TempFINALWei = FinalWeiText.getText();
                        String TempFINALL01 = FinalL01Text.getText();
                        String TempFINALL02 = FinalL02Text.getText();
                        String TempFINALL03 = FinalL03Text.getText();
                        String TempFINALL04 = FinalL04Text.getText();
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
                        String Tempoutcome1_1 = outcome1_1.getText();
                        String Tempoutcome1_2 = outcome1_2.getText();
                        String Tempoutcome1_3 = outcome1_3.getText();
                        String Tempoutcome1_4 = outcome1_4.getText();
                        String Tempoutcome1_5 = outcome1_5.getText();
                        String Tempoutcome2_1 = outcome2_1.getText();
                        String Tempoutcome2_2 = outcome2_2.getText();
                        String Tempoutcome2_3 = outcome2_3.getText();
                        String Tempoutcome2_4 = outcome2_4.getText();
                        String Tempoutcome2_5 = outcome2_5.getText();
                        String Tempoutcome3_1 = outcome3_1.getText();
                        String Tempoutcome3_2 = outcome3_2.getText();
                        String Tempoutcome3_3 = outcome3_3.getText();
                        String Tempoutcome3_4 = outcome3_4.getText();
                        String Tempoutcome3_5 = outcome3_5.getText();
                        String Tempoutcome4_1 = outcome4_1.getText();
                        String Tempoutcome4_2 = outcome4_2.getText();
                        String Tempoutcome4_3 = outcome4_3.getText();
                        String Tempoutcome4_4 = outcome4_4.getText();
                        String Tempoutcome4_5 = outcome4_5.getText();
                        String Tempoutcome5_1 = outcome5_1.getText();
                        String Tempoutcome5_2 = outcome5_2.getText();
                        String Tempoutcome5_3 = outcome5_3.getText();
                        String Tempoutcome5_4 = outcome5_4.getText();
                        String Tempoutcome5_5 = outcome5_5.getText();
                        String Tempoutcome6_1 = outcome6_1.getText();
                        String Tempoutcome6_2 = outcome6_2.getText();
                        String Tempoutcome6_3 = outcome6_3.getText();
                        String Tempoutcome6_4 = outcome6_4.getText();
                        String Tempoutcome6_5 = outcome6_5.getText();
                        String Tempoutcome7_1 = outcome7_1.getText();
                        String Tempoutcome7_2 = outcome7_2.getText();
                        String Tempoutcome7_3 = outcome7_3.getText();
                        String Tempoutcome7_4 = outcome7_4.getText();
                        String Tempoutcome7_5 = outcome7_5.getText();
                        String Tempoutcome8_1 = outcome8_1.getText();
                        String Tempoutcome8_2 = outcome8_2.getText();
                        String Tempoutcome8_3 = outcome8_3.getText();
                        String Tempoutcome8_4 = outcome8_4.getText();
                        String Tempoutcome8_5 = outcome8_5.getText();
                        String Tempoutcome9_1 = outcome9_1.getText();
                        String Tempoutcome9_2 = outcome9_2.getText();
                        String Tempoutcome9_3 = outcome9_3.getText();
                        String Tempoutcome9_4 = outcome9_4.getText();
                        String Tempoutcome9_5 = outcome9_5.getText();
                        String Tempoutcome10_1 = outcome10_1.getText();
                        String Tempoutcome10_2 = outcome10_2.getText();
                        String Tempoutcome10_3 = outcome10_3.getText();
                        String Tempoutcome10_4 = outcome10_4.getText();
                        String Tempoutcome10_5 = outcome10_5.getText();
                        String Tempoutcome11_1 = outcome11_1.getText();
                        String Tempoutcome11_2 = outcome11_2.getText();
                        String Tempoutcome11_3 = outcome11_3.getText();
                        String Tempoutcome11_4 = outcome11_4.getText();
                        String Tempoutcome11_5 = outcome11_5.getText();
                        String Tempoutcome12_1 = outcome12_1.getText();
                        String Tempoutcome12_2 = outcome12_2.getText();
                        String Tempoutcome12_3 = outcome12_3.getText();
                        String Tempoutcome12_4 = outcome12_4.getText();
                        String Tempoutcome12_5 = outcome12_5.getText();
                        String Tempoutcome13_1 = outcome13_1.getText();
                        String Tempoutcome13_2 = outcome13_2.getText();
                        String Tempoutcome13_3 = outcome13_3.getText();
                        String Tempoutcome13_4 = outcome13_4.getText();
                        String Tempoutcome13_5 = outcome13_5.getText();


                        LectureConfig Lecture = new LectureConfig(lecture_id, TempName, TempLName, TempLCode, TempSemester, TempTHour, TempLHour, TempLCredit,
                                TempEcts, TempPrereq, TempLLang, TempLType, TempLLevel, TempTMethod, TempLCoordinator, TempAssistant, TempLObjective,
                                TempLOutcome, TempLDescription, TempLCategory,TempW1sub, TempW2sub, TempW3sub, TempW4sub, TempW5sub, TempW6sub,
                                TempW7sub, TempW8sub, TempW9sub, TempW10sub, TempW11sub, TempW12sub, TempW13sub, TempW14sub, TempW15sub, TempW1req,
                                TempW2req, TempW3req, TempW4req, TempW5req, TempW6req, TempW7req, TempW8req, TempW9req, TempW10req, TempW11req,
                                TempW12req, TempW13req, TempW14req, TempW15req, TempCourseNotes, TempSuggested,TempSHOOCNum,
                                TempPartNumb,TempLabNumb,TempFWNumb,TempQuizNumb,TempHWNumb,TempPresNumb,TempProjNumb,TempPortNumb,TempSEMNumb,TempORALNumb,TempMIDNumb,TempFINALNumb,TempPartWei,TempLabWei,TempFWWei,TempQuizWei,TempHWWei,TempPresWei,TempProjWei,TempPortWei,TempSEMWei,TempORALWei,TempMIDWei,TempFINALWei,
                                TempPartL01,TempLabL01,TempFWL01,TempQuizL01,TempHWL01,TempPresL01,TempProjL01,TempPortL01,TempSEML01,TempORALL01,TempMIDL01,TempFINALL01,TempPartL02,TempLabL02,TempFWL02,TempQuizL02,TempHWL02,TempPresL02,TempProjL02,TempPortL02,TempSEML02,TempORALL02,TempMIDL02,TempFINALL02,TempPartL03,TempLabL03,TempFWL03,
                                TempQuizL03,TempHWL03,TempPresL03,TempProjL03,TempPortL03,TempSEML03,TempORALL03,TempMIDL03,TempFINALL03,TempPartL04,TempLabL04,TempFWL04,TempQuizL04,TempHWL04,TempPresL04,TempProjL04,TempPortL04,TempSEML04,TempORALL04,TempMIDL04,TempFINALL04,
                                TempSHOOCDur, TempSHOOCWork, TempFWNum, TempFWDur, TempFWWork,
                                TempQuizNum, TempQuizDur, TempQuizWork, TempHWNum, TempHWDur, TempHWWork,
                                TempPresentationNum, TempPresentationDur, TempPresentationWork, TempProjectNum,
                                TempProjectDur, TempProjectWork, TempPortfolioNum, TempPortfolioDur, TempPortfolioWork,
                                TempSeminarNum, TempSeminarDur, TempSeminarWork, TempOralNum, TempOralDur,
                                TempOralWork, TempMidtermNum, TempMidtermDur, TempMidtermWork, TempFinalNum,
                                TempFinalDur, TempFinalWork,Tempoutcome1_1, Tempoutcome1_2, Tempoutcome1_3, Tempoutcome1_4, Tempoutcome1_5,
                                Tempoutcome2_1, Tempoutcome2_2, Tempoutcome2_3, Tempoutcome2_4, Tempoutcome2_5,
                                Tempoutcome3_1, Tempoutcome3_2, Tempoutcome3_3, Tempoutcome3_4, Tempoutcome3_5,
                                Tempoutcome4_1, Tempoutcome4_2, Tempoutcome4_3, Tempoutcome4_4, Tempoutcome4_5,
                                Tempoutcome5_1, Tempoutcome5_2, Tempoutcome5_3, Tempoutcome5_4, Tempoutcome5_5,
                                Tempoutcome6_1, Tempoutcome6_2, Tempoutcome6_3, Tempoutcome6_4, Tempoutcome6_5,
                                Tempoutcome7_1, Tempoutcome7_2, Tempoutcome7_3, Tempoutcome7_4, Tempoutcome7_5,
                                Tempoutcome8_1, Tempoutcome8_2, Tempoutcome8_3, Tempoutcome8_4, Tempoutcome8_5,
                                Tempoutcome9_1, Tempoutcome9_2, Tempoutcome9_3, Tempoutcome9_4, Tempoutcome9_5,
                                Tempoutcome10_1, Tempoutcome10_2, Tempoutcome10_3, Tempoutcome10_4, Tempoutcome10_5,
                                Tempoutcome11_1, Tempoutcome11_2, Tempoutcome11_3, Tempoutcome11_4, Tempoutcome11_5,
                                Tempoutcome12_1, Tempoutcome12_2, Tempoutcome12_3, Tempoutcome12_4, Tempoutcome12_5,
                                Tempoutcome13_1, Tempoutcome13_2, Tempoutcome13_3, Tempoutcome13_4, Tempoutcome13_5);
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
                        String TempPartNumb = Lecture.getParticipation_Number();
                        String TempLabNumb = Lecture.getLaboratory_or_Application_Number();
                        String TempFieldWNumb = Lecture.getField_Work_Number();
                        String TempQuizNumb = Lecture.getQuiz_or_StudioCritique_Number();
                        String TempHWNumb = Lecture.getHomework_or_Assignment_Number();
                        String TempPresentNumb = Lecture.getPresentation_or_Jury_Number();
                        String TempProjectNumb = Lecture.getProject_Number();
                        String TempPortNumb = Lecture.getPortfolio_Number();
                        String TempSeminarNumb = Lecture.getSeminar_or_Workshop_Number();
                        String TempOralNumb =  Lecture.getOral_Exam_Number();
                        String TempMidtermNumb = Lecture.getMidterm_Number();
                        String TempFinalNumb = Lecture.getFinal_Exam_Number();
                        String TempPartWei = Lecture.getParticipation_Weigthing();
                        String TempLabWei = Lecture.getLaboratory_or_Application_Weigthing();
                        String TempFieldWWei = Lecture.getField_Work_Weigthing();
                        String TempQuizWei = Lecture.getQuiz_or_StudioCritique_Number();
                        String TempHWWei = Lecture.getHomework_or_Assignment_Weigthing();
                        String TempPresentWei = Lecture.getPresentation_or_Jury_Weigthing();
                        String TempProjectWei = Lecture.getProject_Weigthing();
                        String TempPortWei = Lecture.getPortfolio_Weigthing();
                        String TempSeminarWei = Lecture.getSeminar_or_Workshop_Weigthing();
                        String TempOralWei =  Lecture.getOral_Exam_Weigthing();
                        String TempMidtermWei = Lecture.getMidterm_Weigthing();
                        String TempFinalWei = Lecture.getFinal_Exam_Weigthing();
                        String TempPartL1 = Lecture.getParticipation_LO1();
                        String TempLabL1 = Lecture.getLaboratory_or_Application_LO1();
                        String TempFieldWL1 = Lecture.getField_Work_LO1();
                        String TempQuizL1 = Lecture.getQuiz_or_StudioCritique_LO1();
                        String TempHWL1 = Lecture.getHomework_or_Assignment_LO1();
                        String TempPresentL1 = Lecture.getPresentation_or_Jury_LO1();
                        String TempProjectL1 = Lecture.getProject_LO1();
                        String TempPortL1 = Lecture.getPortfolio_LO1();
                        String TempSeminarL1 = Lecture.getSeminar_or_Workshop_LO1();
                        String TempOralL1 =  Lecture.getOral_Exam_LO1();
                        String TempMidtermL1 = Lecture.getMidterm_LO1();
                        String TempFinalL1 = Lecture.getFinal_Exam_LO1();
                        String TempPartL2 = Lecture.getParticipation_LO2();
                        String TempLabL2 = Lecture.getLaboratory_or_Application_LO2();
                        String TempFieldWL2 = Lecture.getField_Work_LO2();
                        String TempQuizL2 = Lecture.getQuiz_or_StudioCritique_LO2();
                        String TempHWL2 = Lecture.getHomework_or_Assignment_LO2();
                        String TempPresentL2 = Lecture.getPresentation_or_Jury_LO2();
                        String TempProjectL2 = Lecture.getProject_LO2();
                        String TempPortL2 = Lecture.getPortfolio_LO2();
                        String TempSeminarL2 = Lecture.getSeminar_or_Workshop_LO2();
                        String TempOralL2 =  Lecture.getOral_Exam_LO2();
                        String TempMidtermL2 = Lecture.getMidterm_LO2();
                        String TempFinalL2 = Lecture.getFinal_Exam_LO2();
                        String TempPartL3 = Lecture.getParticipation_LO3();
                        String TempLabL3 = Lecture.getLaboratory_or_Application_LO3();
                        String TempFieldWL3 = Lecture.getField_Work_LO3();
                        String TempQuizL3 = Lecture.getQuiz_or_StudioCritique_LO3();
                        String TempHWL3 = Lecture.getHomework_or_Assignment_LO3();
                        String TempPresentL3 = Lecture.getPresentation_or_Jury_LO3();
                        String TempProjectL3 = Lecture.getProject_LO3();
                        String TempPortL3= Lecture.getPortfolio_LO3();
                        String TempSeminarL3 = Lecture.getSeminar_or_Workshop_LO3();
                        String TempOralL3 =  Lecture.getOral_Exam_LO3();
                        String TempMidtermL3 = Lecture.getMidterm_LO3();
                        String TempFinalL3 = Lecture.getFinal_Exam_LO3();
                        String TempPartL4 = Lecture.getParticipation_LO4();
                        String TempLabL4 = Lecture.getLaboratory_or_Application_LO4();
                        String TempFieldWL4 = Lecture.getField_Work_LO4();
                        String TempQuizL4 = Lecture.getQuiz_or_StudioCritique_LO4();
                        String TempHWL4 = Lecture.getHomework_or_Assignment_LO4();
                        String TempPresentL4 = Lecture.getPresentation_or_Jury_LO4();
                        String TempProjectL4 = Lecture.getProject_LO4();
                        String TempPortL4= Lecture.getPortfolio_LO4();
                        String TempSeminarL4 = Lecture.getSeminar_or_Workshop_LO4();
                        String TempOralL4 =  Lecture.getOral_Exam_LO4();
                        String TempMidtermL4 = Lecture.getMidterm_LO4();
                        String TempFinalL4 = Lecture.getFinal_Exam_LO4();
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
                        String outcome1_1 = Lecture.getOutCome1_1();
                        String outcome1_2 = Lecture.getOutCome1_2();
                        String outcome1_3 = Lecture.getOutCome1_3();
                        String outcome1_4 = Lecture.getOutCome1_4();
                        String outcome1_5 = Lecture.getOutCome1_5();
                        String outcome2_1 = Lecture.getOutCome2_1();
                        String outcome2_2 = Lecture.getOutCome2_2();
                        String outcome2_3 = Lecture.getOutCome2_3();
                        String outcome2_4 = Lecture.getOutCome2_4();
                        String outcome2_5 = Lecture.getOutCome2_5();
                        String outcome3_1 = Lecture.getOutCome3_1();
                        String outcome3_2 = Lecture.getOutCome3_2();
                        String outcome3_3 = Lecture.getOutCome3_3();
                        String outcome3_4 = Lecture.getOutCome3_4();
                        String outcome3_5 = Lecture.getOutCome3_5();
                        String outcome4_1 = Lecture.getOutCome4_1();
                        String outcome4_2 = Lecture.getOutCome4_2();
                        String outcome4_3 = Lecture.getOutCome4_3();
                        String outcome4_4 = Lecture.getOutCome4_4();
                        String outcome4_5 = Lecture.getOutCome4_5();
                        String outcome5_1 = Lecture.getOutCome5_1();
                        String outcome5_2 = Lecture.getOutCome5_2();
                        String outcome5_3 = Lecture.getOutCome5_3();
                        String outcome5_4 = Lecture.getOutCome5_4();
                        String outcome5_5 = Lecture.getOutCome5_5();
                        String outcome6_1 = Lecture.getOutCome6_1();
                        String outcome6_2 = Lecture.getOutCome6_2();
                        String outcome6_3 = Lecture.getOutCome6_3();
                        String outcome6_4 = Lecture.getOutCome6_4();
                        String outcome6_5 = Lecture.getOutCome6_5();
                        String outcome7_1 = Lecture.getOutCome7_1();
                        String outcome7_2 = Lecture.getOutCome7_2();
                        String outcome7_3 = Lecture.getOutCome7_3();
                        String outcome7_4 = Lecture.getOutCome7_4();
                        String outcome7_5 = Lecture.getOutCome7_5();
                        String outcome8_1 = Lecture.getOutCome8_1();
                        String outcome8_2 = Lecture.getOutCome8_2();
                        String outcome8_3 = Lecture.getOutCome8_3();
                        String outcome8_4 = Lecture.getOutCome8_4();
                        String outcome8_5 = Lecture.getOutCome8_5();
                        String outcome9_1 = Lecture.getOutCome9_1();
                        String outcome9_2 = Lecture.getOutCome9_2();
                        String outcome9_3 = Lecture.getOutCome9_3();
                        String outcome9_4 = Lecture.getOutCome9_4();
                        String outcome9_5 = Lecture.getOutCome9_5();
                        String outcome10_1 = Lecture.getOutCome10_1();
                        String outcome10_2 = Lecture.getOutCome10_2();
                        String outcome10_3 = Lecture.getOutCome10_3();
                        String outcome10_4 = Lecture.getOutCome10_4();
                        String outcome10_5 = Lecture.getOutCome10_5();
                        String outcome11_1 = Lecture.getOutCome11_1();
                        String outcome11_2 = Lecture.getOutCome11_2();
                        String outcome11_3 = Lecture.getOutCome11_3();
                        String outcome11_4 = Lecture.getOutCome11_4();
                        String outcome11_5 = Lecture.getOutCome11_5();
                        String outcome12_1 = Lecture.getOutCome12_1();
                        String outcome12_2 = Lecture.getOutCome12_2();
                        String outcome12_3 = Lecture.getOutCome12_3();
                        String outcome12_4 = Lecture.getOutCome12_4();
                        String outcome12_5 = Lecture.getOutCome12_5();
                        String outcome13_1 = Lecture.getOutCome13_1();
                        String outcome13_2 = Lecture.getOutCome13_2();
                        String outcome13_3 = Lecture.getOutCome13_3();
                        String outcome13_4 = Lecture.getOutCome13_4();
                        String outcome13_5 = Lecture.getOutCome13_5();


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

                        Label WEEKLYSUBJECTSANDREQUIREDMATERIALS = new Label("2.WEEKLY SUBJECTS AND REQUIRED MATERIALS");
                        WEEKLYSUBJECTSANDREQUIREDMATERIALS.setStyle("-fx-background-color: orange; -fx-padding: 5px;-fx-font-weight: bold;-fx-font-size: 8px;");
                        LectureGrid.add(WEEKLYSUBJECTSANDREQUIREDMATERIALS,0,20);

                        Label week1SubjectsLabel = new Label("Week 1 Subjects:");
                        LectureGrid.add(week1SubjectsLabel, 0, 21);

                        Label week2SubjectsLabel = new Label("Week 2 Subjects:");
                        LectureGrid.add(week2SubjectsLabel, 0, 22);

                        Label week3SubjectsLabel = new Label("Week 3 Subjects:");
                        LectureGrid.add(week3SubjectsLabel, 0, 23);

                        Label week4SubjectsLabel = new Label("Week 4 Subjects:");
                        LectureGrid.add(week4SubjectsLabel, 0, 24);

                        Label week5SubjectsLabel = new Label("Week 5 Subjects:");
                        LectureGrid.add(week5SubjectsLabel, 0, 25);

                        Label week6SubjectsLabel = new Label("Week 6 Subjects:");
                        LectureGrid.add(week6SubjectsLabel, 0, 26);

                        Label week7SubjectsLabel = new Label("Week 7 Subjects:");
                        LectureGrid.add(week7SubjectsLabel, 0, 27);

                        Label week8SubjectsLabel = new Label("Week 8 Subjects:");
                        LectureGrid.add(week8SubjectsLabel, 0, 28);

                        Label week9SubjectsLabel = new Label("Week 9 Subjects:");
                        LectureGrid.add(week9SubjectsLabel, 0, 29);

                        Label week10SubjectsLabel = new Label("Week 10 Subjects:");
                        LectureGrid.add(week10SubjectsLabel, 0, 30);

                        Label week11SubjectsLabel = new Label("Week 11 Subjects:");
                        LectureGrid.add(week11SubjectsLabel, 0, 31);

                        Label week12SubjectsLabel = new Label("Week 12 Subjects:");
                        LectureGrid.add(week12SubjectsLabel, 0, 32);

                        Label week13SubjectsLabel = new Label("Week 13 Subjects:");
                        LectureGrid.add(week13SubjectsLabel, 0, 33);

                        Label week14SubjectsLabel = new Label("Week 14 Subjects:");
                        LectureGrid.add(week14SubjectsLabel, 0, 34);

                        Label week15SubjectsLabel = new Label("Week 15 Subjects:");
                        LectureGrid.add(week15SubjectsLabel, 0, 35);

                        Label week1ReqMatLabel = new Label("Week 1 Required Materials:");
                        LectureGrid.add(week1ReqMatLabel, 0, 36);

                        Label week2ReqMatLabel = new Label("Week 2 Required Materials:");
                        LectureGrid.add(week2ReqMatLabel, 0, 37);

                        Label week3ReqMatLabel = new Label("Week 3 Required Materials:");
                        LectureGrid.add(week3ReqMatLabel, 0, 38);

                        Label week4ReqMatLabel = new Label("Week 4 Required Materials:");
                        LectureGrid.add(week4ReqMatLabel, 0, 39);

                        Label week5ReqMatLabel = new Label("Week 5 Required Materials:");
                        LectureGrid.add(week5ReqMatLabel, 0, 40);

                        Label week6ReqMatLabel = new Label("Week 6 Required Materials:");
                        LectureGrid.add(week6ReqMatLabel, 0, 41);

                        Label week7ReqMatLabel = new Label("Week 7 Required Materials:");
                        LectureGrid.add(week7ReqMatLabel, 0, 42);

                        Label week8ReqMatLabel = new Label("Week 8 Required Materials:");
                        LectureGrid.add(week8ReqMatLabel, 0, 43);

                        Label week9ReqMatLabel = new Label("Week 9 Required Materials:");
                        LectureGrid.add(week9ReqMatLabel, 0, 44);

                        Label week10ReqMatLabel = new Label("Week 10 Required Materials:");
                        LectureGrid.add(week10ReqMatLabel, 0, 45);

                        Label week11ReqMatLabel = new Label("Week 11 Required Materials:");
                        LectureGrid.add(week11ReqMatLabel, 0, 46);

                        Label week12ReqMatLabel = new Label("Week 12 Required Materials:");
                        LectureGrid.add(week12ReqMatLabel, 0, 47);

                        Label week13ReqMatLabel = new Label("Week 13 Required Materials:");
                        LectureGrid.add(week13ReqMatLabel, 0, 48);

                        Label week14ReqMatLabel = new Label("Week 14 Required Materials:");
                        LectureGrid.add(week14ReqMatLabel, 0, 49);

                        Label week15ReqMatLabel = new Label("Week 15 Required Materials:");
                        LectureGrid.add(week15ReqMatLabel, 0, 50);

                        Label courseNotesAndTextBooksLabel = new Label("Course Notes and Textbooks:");
                        LectureGrid.add(courseNotesAndTextBooksLabel, 0, 51);

                        Label suggestedReadingsAndMaterialsLabel = new Label("Suggested Readings and Materials:");
                        LectureGrid.add(suggestedReadingsAndMaterialsLabel, 0, 52);

                        Label ASSESMENT = new Label("3.ASSESMENT");
                        ASSESMENT.setStyle("-fx-background-color: orange; -fx-padding: 5px;-fx-font-weight: bold;-fx-font-size: 15px;");
                        LectureGrid.add(ASSESMENT,0,53);

                        Label participationNumberLabel = new Label("Participation Number:");
                        LectureGrid.add(participationNumberLabel, 0, 54);

                        Label  participationWeiLabel= new Label("Participation Weighting:");
                        LectureGrid.add(participationWeiLabel, 0, 55);

                        Label  participation01Label= new Label("Participation LO1:");
                        LectureGrid.add(participation01Label, 0, 56);

                        Label participation02Label = new Label("Participation LO2:");
                        LectureGrid.add(participation02Label, 0, 57);

                        Label participation03Label = new Label("Participation LO3:");
                        LectureGrid.add(participation03Label, 0, 58);

                        Label  participation04Label= new Label("Participation LO4:");
                        LectureGrid.add(participation04Label, 0, 59);

                        Label labOrApplicationNumberLabel = new Label("Laboratory Number:");
                        LectureGrid.add(labOrApplicationNumberLabel, 0, 60);

                        Label  labOrApplicationWeiLabel= new Label("Laboratory Weighting:");
                        LectureGrid.add(labOrApplicationWeiLabel, 0, 61);

                        Label labOrApplication01Label = new Label("Laboratory LO1:");
                        LectureGrid.add(labOrApplication01Label, 0, 62);

                        Label labOrApplication02Label = new Label("Laboratory LO2:");
                        LectureGrid.add(labOrApplication02Label, 0, 63);

                        Label labOrApplication03Label = new Label("Laboratory LO3:");
                        LectureGrid.add(labOrApplication03Label, 0, 64);

                        Label labOrApplication04Label = new Label("Laboratory LO4:");
                        LectureGrid.add(labOrApplication04Label, 0, 65);

                        Label fieldWorkNumberLabel = new Label("Field Work Number:");
                        LectureGrid.add(fieldWorkNumberLabel, 0, 66);

                        Label FieldWorkWeiLabel = new Label("Field Work Weighting");
                        LectureGrid.add(FieldWorkWeiLabel, 0, 67);

                        Label FieldWork01Label = new Label("Field Work LO1:");
                        LectureGrid.add(FieldWork01Label, 0, 68);

                        Label FieldWork02Label = new Label("Field Work LO2:");
                        LectureGrid.add(FieldWork02Label, 0, 69);

                        Label FieldWork03Label = new Label("Field Work LO3:");
                        LectureGrid.add(FieldWork03Label, 0, 70);

                        Label FieldWork04Label = new Label("Field Work LO4:");
                        LectureGrid.add(FieldWork04Label, 0, 71);

                        Label quizOrStudioCritiqueNumberLabel = new Label("Quiz Number:");
                        LectureGrid.add(quizOrStudioCritiqueNumberLabel, 0, 72);

                        Label quizOrStudioCritiqueWeiLabel = new Label("Quiz Weighting:");
                        LectureGrid.add(quizOrStudioCritiqueWeiLabel, 0,73);

                        Label quizOrStudioCritique01Label = new Label("Quiz LO1:");
                        LectureGrid.add(quizOrStudioCritique01Label, 0, 74);

                        Label quizOrStudioCritique02Label = new Label("Quiz LO2:");
                        LectureGrid.add(quizOrStudioCritique02Label, 0, 75);

                        Label quizOrStudioCritique03Label = new Label("Quiz LO3:");
                        LectureGrid.add(quizOrStudioCritique03Label, 0, 76);

                        Label quizOrStudioCritique04Label = new Label("Quiz LO4:");
                        LectureGrid.add(quizOrStudioCritique04Label, 0, 77);

                        Label homeworkOrAssignmentNumberLabel = new Label("Homework Number:");
                        LectureGrid.add(homeworkOrAssignmentNumberLabel, 0, 78);

                        Label homeworkOrAssignmentWeiLabel = new Label("Homework Weighting:");
                        LectureGrid.add(homeworkOrAssignmentWeiLabel, 0, 79);

                        Label homeworkOrAssignment01Label = new Label("Homework LO1:");
                        LectureGrid.add(homeworkOrAssignment01Label, 0, 80);

                        Label homeworkOrAssignment02Label = new Label("Homework LO2:");
                        LectureGrid.add(homeworkOrAssignment02Label, 0, 81);

                        Label homeworkOrAssignment03Label = new Label("Homework LO3:");
                        LectureGrid.add(homeworkOrAssignment03Label, 0, 82);

                        Label homeworkOrAssignment04Label = new Label("Homework LO4:");
                        LectureGrid.add(homeworkOrAssignment04Label, 0, 83);

                        Label presentationOrJuryNumberLabel = new Label("Presentation Number:");
                        LectureGrid.add(presentationOrJuryNumberLabel, 0, 84);

                        Label presentationOrJuryWeiLabel = new Label("Presentation Weighting:");
                        LectureGrid.add(presentationOrJuryWeiLabel, 0, 85);

                        Label presentationOrJury01Label = new Label("Presentation LO1:");
                        LectureGrid.add(presentationOrJury01Label, 0, 86);

                        Label presentationOrJury02Label = new Label("Presentation LO2:");
                        LectureGrid.add(presentationOrJury02Label, 0, 87);

                        Label presentationOrJury03Label = new Label("Presentation LO3:");
                        LectureGrid.add(presentationOrJury03Label, 0, 88);

                        Label presentationOrJury04Label = new Label("Presentation LO4:");
                        LectureGrid.add(presentationOrJury04Label, 0, 89);

                        Label projectNumberLabel = new Label("Project Number:");
                        LectureGrid.add(projectNumberLabel, 0, 90);

                        Label projectWeiLabel = new Label("Project Weighting:");
                        LectureGrid.add(projectWeiLabel, 0, 91);

                        Label project01Label = new Label("Project LO1:");
                        LectureGrid.add(project01Label, 0, 92);

                        Label project02Label = new Label("Project LO2:");
                        LectureGrid.add(project02Label, 0, 93);

                        Label project03Label = new Label("Project LO3:");
                        LectureGrid.add(project03Label, 0, 94);

                        Label project04Label = new Label("Project LO4:");
                        LectureGrid.add(project04Label, 0, 95);

                        Label portfolioNumberLabel = new Label("Portfolio Number:");
                        LectureGrid.add(portfolioNumberLabel, 0, 96);

                        Label portfolioWeiLabel = new Label("Portfolio Weighting:");
                        LectureGrid.add(portfolioWeiLabel, 0, 97);

                        Label portfolio01Label = new Label("Portfolio LO1:");
                        LectureGrid.add(portfolio01Label, 0, 98);

                        Label portfolio02Label = new Label("Portfolio LO2:");
                        LectureGrid.add(portfolio02Label, 0, 99);

                        Label portfolio03Label = new Label("Portfolio LO3:");
                        LectureGrid.add(portfolio03Label, 0, 100);

                        Label portfolio04Label = new Label("Portfolio LO4:");
                        LectureGrid.add(portfolio04Label, 0, 101);

                        Label seminarNumberLabel = new Label("Seminar Number:");
                        LectureGrid.add(seminarNumberLabel, 0, 102);

                        Label seminarWeiLabel = new Label("Seminar Weighting:");
                        LectureGrid.add(seminarWeiLabel, 0, 103);

                        Label seminar01Label = new Label("Seminar LO1:");
                        LectureGrid.add(seminar01Label, 0, 104);

                        Label seminar02Label = new Label("Seminar LO2:");
                        LectureGrid.add(seminar02Label, 0, 105);

                        Label seminar03Label = new Label("Seminar LO3:");
                        LectureGrid.add(seminar03Label, 0, 106);

                        Label seminar04Label = new Label("Seminar LO4:");
                        LectureGrid.add(seminar04Label, 0, 107);

                        Label oralNumberLabel = new Label("Oral Exam Number:");
                        LectureGrid.add(oralNumberLabel, 0, 108);

                        Label oralWeiLabel = new Label("Oral Exam Weighting:");
                        LectureGrid.add(oralWeiLabel, 0, 109);

                        Label oral01Label = new Label("Oral Exam LO1:");
                        LectureGrid.add(oral01Label, 0, 110);

                        Label oral02Label = new Label("Oral Exam LO2:");
                        LectureGrid.add(oral02Label, 0, 111);

                        Label oral03Label = new Label("Oral Exam LO3:");
                        LectureGrid.add(oral03Label, 0, 112);

                        Label oral04Label = new Label("Oral Exam LO4:");
                        LectureGrid.add(oral04Label, 0, 113);

                        Label midtermNumberLabel = new Label("Midterm Number:");
                        LectureGrid.add(midtermNumberLabel, 0, 114);

                        Label midtermWeiLabel = new Label("Midterm Weighting:");
                        LectureGrid.add(midtermWeiLabel, 0, 115);

                        Label midterm01Label = new Label("Midterm LO1:");
                        LectureGrid.add(midterm01Label, 0, 116);

                        Label midterm02Label = new Label("Midterm LO2:");
                        LectureGrid.add(midterm02Label, 0, 117);

                        Label midterm03Label = new Label("Midterm LO3:");
                        LectureGrid.add(midterm03Label, 0, 118);

                        Label midterm04Label = new Label("Midterm LO4:");
                        LectureGrid.add(midterm04Label, 0, 119);

                        Label finalNumberLabel = new Label("Final Exam Number:");
                        LectureGrid.add(finalNumberLabel, 0, 120);

                        Label finalWeiLabel = new Label("Final Exam Weighting:");
                        LectureGrid.add(finalWeiLabel, 0, 121);

                        Label final01Label = new Label("Final Exam LO1:");
                        LectureGrid.add(final01Label, 0, 122);

                        Label final02Label = new Label("Final Exam LO2:");
                        LectureGrid.add(final02Label, 0, 123);

                        Label final03Label = new Label("Final Exam LO3:");
                        LectureGrid.add(final03Label, 0, 124);

                        Label final04Label = new Label("Final Exam LO4:");
                        LectureGrid.add(final04Label, 0, 125);

                        Label ECTSWORKLOADTABLE = new Label("4.ECTS / WORKLOAD TABLE");
                        ECTSWORKLOADTABLE.setStyle("-fx-background-color: orange; -fx-padding: 5px;-fx-font-weight: bold;-fx-font-size: 15px;");
                        LectureGrid.add(ECTSWORKLOADTABLE,0,126);

                        Label StudyHoursoutofClassNumLabel = new Label("Study Hours out of Class Number:");
                        LectureGrid.add(StudyHoursoutofClassNumLabel, 0, 127);

                        Label StudyHoursoutofClassDurLabel = new Label("Study Hours out of Class Duration:");
                        LectureGrid.add(StudyHoursoutofClassDurLabel, 0, 128);

                        Label StudyHoursoutofClassWorkLabel = new Label("Study Hours out of Class Workload:");
                        LectureGrid.add(StudyHoursoutofClassWorkLabel, 0, 129);

                        Label FieldWorkNumLabel = new Label("Field Work Number");
                        LectureGrid.add(FieldWorkNumLabel, 0, 130);

                        Label FieldWorkDurLabel = new Label("Field Work Duration");
                        LectureGrid.add(FieldWorkDurLabel, 0, 131);

                        Label FieldWorkWorkLabel = new Label("Field Work Workload");
                        LectureGrid.add(FieldWorkWorkLabel, 0, 132);

                        Label QuizNumLabel = new Label("Quiz/Studio Critique Number:");
                        LectureGrid.add(QuizNumLabel, 0, 133);

                        Label QuizDurLabel = new Label("Quiz/Studio Critique Duration:");
                        LectureGrid.add(QuizDurLabel, 0, 134);

                        Label QuizWorkLabel = new Label("Quiz/Studio Critique Workload:");
                        LectureGrid.add(QuizWorkLabel, 0, 135);

                        Label HWNumLabel = new Label("Homework/Assignments Number:");
                        LectureGrid.add(HWNumLabel, 0, 136);

                        Label HWDurLabel = new Label("Homework/Assignments Duration:");
                        LectureGrid.add(HWDurLabel, 0, 137);

                        Label HWWorkLabel = new Label("Homework/Assignments Workload:");
                        LectureGrid.add(HWWorkLabel, 0, 138);

                        Label PresentationNumLabel = new Label("Presentation/Jury Number:");
                        LectureGrid.add(PresentationNumLabel, 0, 139);

                        Label PresentationDurLabel = new Label("Presentation/Jury Duration:");
                        LectureGrid.add(PresentationDurLabel, 0, 140);

                        Label PresentationWorkLabel = new Label("Presentation/Jury Workload:");
                        LectureGrid.add(PresentationWorkLabel, 0, 141);

                        Label ProjectNumLabel = new Label("Project Number:");
                        LectureGrid.add(ProjectNumLabel, 0, 142);

                        Label ProjectDurLabel = new Label("Project Duration:");
                        LectureGrid.add(ProjectDurLabel, 0, 143);

                        Label ProjectWorkLabel = new Label("Project Workload:");
                        LectureGrid.add(ProjectWorkLabel, 0, 144);

                        Label PortfolioNumLabel = new Label("Portfolio Number:");
                        LectureGrid.add(PortfolioNumLabel, 0, 145);

                        Label PortfolioDurLabel = new Label("Portfolio Duration:");
                        LectureGrid.add(PortfolioDurLabel, 0, 146);

                        Label PortfolioWorkLabel = new Label("Portfolio Workload:");
                        LectureGrid.add(PortfolioWorkLabel, 0, 147);

                        Label SeminarNumLabel = new Label("Seminar/Workshop Number:");
                        LectureGrid.add(SeminarNumLabel, 0,148);

                        Label SeminarDurLabel = new Label("Seminar/Workshop Duration:");
                        LectureGrid.add(SeminarDurLabel, 0,149);

                        Label SeminarWorkLabel = new Label("Seminar/Workshop Workload:");
                        LectureGrid.add(SeminarWorkLabel, 0,150);

                        Label OralExamNumLabel = new Label("Oral Exam Number:");
                        LectureGrid.add(OralExamNumLabel, 0, 151);

                        Label OralExamDurLabel = new Label("Oral Exam Duration:");
                        LectureGrid.add(OralExamDurLabel, 0, 152);

                        Label OralExamWorkLabel = new Label("Oral Exam Workload:");
                        LectureGrid.add(OralExamWorkLabel, 0, 153);

                        Label MidtermNumLabel = new Label("Midterm Number: ");
                        LectureGrid.add(MidtermNumLabel, 0, 154);

                        Label MidtermDurLabel = new Label("Midterm Duration:");
                        LectureGrid.add(MidtermDurLabel, 0, 155);

                        Label MidtermWorkLabel = new Label("Midterm Workload:");
                        LectureGrid.add(MidtermWorkLabel, 0, 156);

                        Label FinalNumLabel = new Label("Final Exam Number:");
                        LectureGrid.add(FinalNumLabel, 0, 157);

                        Label FinalDurLabel = new Label("Final Exam Duration:");
                        LectureGrid.add(FinalDurLabel, 0, 158);

                        Label FinalWorkLabel = new Label("Final Exam Workload:");
                        LectureGrid.add(FinalWorkLabel, 0, 159);

                        Label COURSEPROGRAMOUTCOMEMATRIX = new Label("5.COURSE/PROGRAM OUTCOME MATRIX");
                        COURSEPROGRAMOUTCOMEMATRIX.setStyle("-fx-background-color: orange; -fx-padding: 5px;-fx-font-weight: bold;-fx-font-size: 15px;");
                        LectureGrid.add(COURSEPROGRAMOUTCOMEMATRIX,0,160);

                        Label  outcome1_1Label= new Label("Outcome 1_1:");
                        LectureGrid.add(outcome1_1Label, 0, 161);

                        Label outcome1_2Label = new Label("Outcome 1_2:");
                        LectureGrid.add(outcome1_2Label, 0,  162);

                        Label outcome1_3Label = new Label("Outcome 1_3:");
                        LectureGrid.add(outcome1_3Label, 0, 163);

                        Label  outcome1_4Label= new Label("Outcome 1_4:");
                        LectureGrid.add(outcome1_4Label, 0, 164);

                        Label  outcome1_5Label= new Label("Outcome 1_5:");
                        LectureGrid.add(outcome1_5Label, 0, 165);

                        Label  outcome2_1Label= new Label("Outcome 2_1:");
                        LectureGrid.add(outcome2_1Label, 0, 166);

                        Label outcome2_2Label = new Label("Outcome 2_2:");
                        LectureGrid.add(outcome2_2Label, 0,  167);

                        Label outcome2_3Label = new Label("Outcome 2_3:");
                        LectureGrid.add(outcome2_3Label, 0, 168);

                        Label  outcome2_4Label= new Label("Outcome 2_4:");
                        LectureGrid.add(outcome2_4Label, 0, 169);

                        Label  outcome2_5Label= new Label("Outcome 2_5:");
                        LectureGrid.add(outcome2_5Label, 0, 170);

                        Label  outcome3_1Label= new Label("Outcome 3_1:");
                        LectureGrid.add(outcome3_1Label, 0, 171);

                        Label outcome3_2Label = new Label("Outcome 3_2:");
                        LectureGrid.add(outcome3_2Label, 0,  172);

                        Label outcome3_3Label = new Label("Outcome 3_3:");
                        LectureGrid.add(outcome3_3Label, 0, 173);

                        Label  outcome3_4Label= new Label("Outcome 3_4:");
                        LectureGrid.add(outcome3_4Label, 0, 174);

                        Label  outcome3_5Label= new Label("Outcome 3_5:");
                        LectureGrid.add(outcome3_5Label, 0, 175);

                        Label  outcome4_1Label= new Label("Outcome 4_1:");
                        LectureGrid.add(outcome4_1Label, 0, 176);

                        Label outcome4_2Label = new Label("Outcome 4_2:");
                        LectureGrid.add(outcome4_2Label, 0,  177);

                        Label outcome4_3Label = new Label("Outcome 4_3:");
                        LectureGrid.add(outcome4_3Label, 0, 178);

                        Label  outcome4_4Label= new Label("Outcome 4_4:");
                        LectureGrid.add(outcome4_4Label, 0, 179);

                        Label  outcome4_5Label= new Label("Outcome 4_5:");
                        LectureGrid.add(outcome4_5Label, 0, 180);

                        Label  outcome5_1Label= new Label("Outcome 5_1:");
                        LectureGrid.add(outcome5_1Label, 0, 181);

                        Label outcome5_2Label = new Label("Outcome 5_2:");
                        LectureGrid.add(outcome5_2Label, 0,  182);

                        Label outcome5_3Label = new Label("Outcome 5_3:");
                        LectureGrid.add(outcome5_3Label, 0, 183);

                        Label  outcome5_4Label= new Label("Outcome 5_4:");
                        LectureGrid.add(outcome5_4Label, 0, 184);

                        Label  outcome5_5Label= new Label("Outcome 5_5:");
                        LectureGrid.add(outcome5_5Label, 0, 185);

                        Label  outcome6_1Label= new Label("Outcome 6_1:");
                        LectureGrid.add(outcome6_1Label, 0, 186);

                        Label outcome6_2Label = new Label("Outcome 6_2:");
                        LectureGrid.add(outcome6_2Label, 0,  187);

                        Label outcome6_3Label = new Label("Outcome 6_3:");
                        LectureGrid.add(outcome6_3Label, 0, 188);

                        Label  outcome6_4Label= new Label("Outcome 6_4:");
                        LectureGrid.add(outcome6_4Label, 0, 189);

                        Label  outcome6_5Label= new Label("Outcome 6_5:");
                        LectureGrid.add(outcome6_5Label, 0, 190);

                        Label  outcome7_1Label= new Label("Outcome 7_1:");
                        LectureGrid.add(outcome7_1Label, 0, 191);

                        Label outcome7_2Label = new Label("Outcome 7_2:");
                        LectureGrid.add(outcome7_2Label, 0,  192);

                        Label outcome7_3Label = new Label("Outcome 7_3:");
                        LectureGrid.add(outcome7_3Label, 0, 193);

                        Label  outcome7_4Label= new Label("Outcome 7_4:");
                        LectureGrid.add(outcome7_4Label, 0, 194);

                        Label  outcome7_5Label= new Label("Outcome 7_5:");
                        LectureGrid.add(outcome7_5Label, 0, 195);

                        Label  outcome8_1Label= new Label("Outcome 8_1:");
                        LectureGrid.add(outcome8_1Label, 0, 196);

                        Label outcome8_2Label = new Label("Outcome 8_2:");
                        LectureGrid.add(outcome8_2Label, 0,  197);

                        Label outcome8_3Label = new Label("Outcome 8_3:");
                        LectureGrid.add(outcome8_3Label, 0, 198);

                        Label  outcome8_4Label= new Label("Outcome 8_4:");
                        LectureGrid.add(outcome8_4Label, 0, 199);

                        Label  outcome8_5Label= new Label("Outcome 8_5:");
                        LectureGrid.add(outcome8_5Label, 0, 200);

                        Label  outcome9_1Label= new Label("Outcome 9_1:");
                        LectureGrid.add(outcome9_1Label, 0, 201);

                        Label outcome9_2Label = new Label("Outcome 9_2:");
                        LectureGrid.add(outcome9_2Label, 0,  202);

                        Label outcome9_3Label = new Label("Outcome 9_3:");
                        LectureGrid.add(outcome9_3Label, 0, 203);

                        Label  outcome9_4Label= new Label("Outcome 9_4:");
                        LectureGrid.add(outcome9_4Label, 0, 204);

                        Label  outcome9_5Label= new Label("Outcome 9_5:");
                        LectureGrid.add(outcome9_5Label, 0, 205);

                        Label  outcome10_1Label= new Label("Outcome 10_1:");
                        LectureGrid.add(outcome10_1Label, 0, 206);

                        Label outcome10_2Label = new Label("Outcome 10_2:");
                        LectureGrid.add(outcome10_2Label, 0,  207);

                        Label outcome10_3Label = new Label("Outcome 10_3:");
                        LectureGrid.add(outcome10_3Label, 0, 208);

                        Label  outcome10_4Label= new Label("Outcome 10_4:");
                        LectureGrid.add(outcome10_4Label, 0, 209);

                        Label  outcome10_5Label= new Label("Outcome 10_5:");
                        LectureGrid.add(outcome10_5Label, 0, 210);

                        Label  outcome11_1Label= new Label("Outcome 11_1:");
                        LectureGrid.add(outcome11_1Label, 0, 211);

                        Label outcome11_2Label = new Label("Outcome 11_2:");
                        LectureGrid.add(outcome11_2Label, 0,  212);

                        Label outcome11_3Label = new Label("Outcome 11_3:");
                        LectureGrid.add(outcome11_3Label, 0, 213);

                        Label  outcome11_4Label= new Label("Outcome 11_4:");
                        LectureGrid.add(outcome11_4Label, 0, 214);

                        Label  outcome11_5Label= new Label("Outcome 11_5:");
                        LectureGrid.add(outcome11_5Label, 0, 215);

                        Label  outcome12_1Label= new Label("Outcome 12_1:");
                        LectureGrid.add(outcome12_1Label, 0, 216);

                        Label outcome12_2Label = new Label("Outcome 12_2:");
                        LectureGrid.add(outcome12_2Label, 0,  217);

                        Label outcome12_3Label = new Label("Outcome 12_3:");
                        LectureGrid.add(outcome12_3Label, 0, 218);

                        Label  outcome12_4Label= new Label("Outcome 12_4:");
                        LectureGrid.add(outcome12_4Label, 0, 219);

                        Label  outcome12_5Label= new Label("Outcome 12_5:");
                        LectureGrid.add(outcome12_5Label, 0, 220);

                        Label  outcome13_1Label= new Label("Outcome 13_1:");
                        LectureGrid.add(outcome13_1Label, 0, 221);

                        Label outcome13_2Label = new Label("Outcome 13_2:");
                        LectureGrid.add(outcome13_2Label, 0,  222);

                        Label outcome13_3Label = new Label("Outcome 13_3:");
                        LectureGrid.add(outcome13_3Label, 0, 223);

                        Label  outcome13_4Label= new Label("Outcome 13_4:");
                        LectureGrid.add(outcome13_4Label, 0, 224);

                        Label  outcome13_5Label= new Label("Outcome 13_5:");
                        LectureGrid.add(outcome13_5Label, 0, 225);



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
                        LectureGrid.add(week1SubjectsText, 1, 21);

                        Label week2SubjectsText = new Label(TempW2sub);
                        LectureGrid.add(week2SubjectsText, 1, 22);

                        Label week3SubjectsText = new Label(TempW3sub);
                        LectureGrid.add(week3SubjectsText, 1, 23);

                        Label week4SubjectsText = new Label(TempW4sub);
                        LectureGrid.add(week4SubjectsText, 1, 24);

                        Label week5SubjectsText = new Label(TempW5sub);
                        LectureGrid.add(week5SubjectsText, 1, 25);

                        Label week6SubjectsText = new Label(TempW6sub);
                        LectureGrid.add(week6SubjectsText, 1, 26);

                        Label week7SubjectsText = new Label(TempW7sub);
                        LectureGrid.add(week7SubjectsText, 1, 27);

                        Label week8SubjectsText = new Label(TempW8sub);
                        LectureGrid.add(week8SubjectsText, 1, 28);

                        Label week9SubjectsText = new Label(TempW9sub);
                        LectureGrid.add(week9SubjectsText, 1, 29);

                        Label week10SubjectsText = new Label(TempW10sub);
                        LectureGrid.add(week10SubjectsText, 1, 30);

                        Label week11SubjectsText =new Label(TempW11sub);
                        LectureGrid.add(week11SubjectsText, 1, 31);

                        Label week12SubjectsText = new Label(TempW12sub);
                        LectureGrid.add(week12SubjectsText, 1, 32);

                        Label week13SubjectsText = new Label(TempW13sub);
                        LectureGrid.add(week13SubjectsText, 1, 33);

                        Label week14SubjectsText = new Label(TempW14sub);
                        LectureGrid.add(week14SubjectsText, 1, 34);

                        Label week15SubjectsText =new Label(TempW15sub);
                        LectureGrid.add(week15SubjectsText, 1, 35);

                        Label week1ReqMatText = new Label(TempW1req);
                        LectureGrid.add(week1ReqMatText, 1, 36);

                        Label week2ReqMatText = new Label(TempW2req);
                        LectureGrid.add(week2ReqMatText, 1, 37);

                        Label week3ReqMatText = new Label(TempW3req);
                        LectureGrid.add(week3ReqMatText, 1, 38);

                        Label week4ReqMatText = new Label(TempW4req);
                        LectureGrid.add(week4ReqMatText, 1, 39);

                        Label week5ReqMatText = new Label(TempW5req);
                        LectureGrid.add(week5ReqMatText, 1, 40);

                        Label week6ReqMatText = new Label(TempW6req);
                        LectureGrid.add(week6ReqMatText, 1, 41);

                        Label week7ReqMatText = new Label(TempW7req);
                        LectureGrid.add(week7ReqMatText, 1, 42);

                        Label week8ReqMatText = new Label(TempW8req);
                        LectureGrid.add(week8ReqMatText, 1, 43);

                        Label week9ReqMatText = new Label(TempW9req);
                        LectureGrid.add(week9ReqMatText, 1, 44);

                        Label week10ReqMatText = new Label(TempW10req);
                        LectureGrid.add(week10ReqMatText, 1, 45);

                        Label week11ReqMatText = new Label(TempW11req);
                        LectureGrid.add(week11ReqMatText, 1, 46);

                        Label week12ReqMatText = new Label(TempW12req);
                        LectureGrid.add(week12ReqMatText, 1, 47);

                        Label week13ReqMatText = new Label(TempW13req);
                        LectureGrid.add(week13ReqMatText, 1, 48);

                        Label week14ReqMatText = new Label(TempW14req);
                        LectureGrid.add(week14ReqMatText, 1, 49);

                        Label week15ReqMatText = new Label(TempW15req);
                        LectureGrid.add(week15ReqMatText, 1, 50);

                        Label courseNotesAndTextBooksText = new Label(TempCourseNotes);
                        LectureGrid.add(courseNotesAndTextBooksText, 1, 51);

                        Label suggestedReadingsAndMaterialsText = new Label(TempSuggested);
                        LectureGrid.add(suggestedReadingsAndMaterialsText, 1, 52);

                        Label partNumText = new Label(TempPartNumb);LectureGrid.add(partNumText, 1, 53);

                        Label partWeiText = new Label(TempPartWei);LectureGrid.add(partWeiText, 1, 54);

                        Label partL01Text = new Label(TempPartL1);LectureGrid.add(partL01Text, 1, 55);

                        Label partL02Text = new Label(TempPartL2);LectureGrid.add(partL02Text, 1, 56);

                        Label partL03Text = new Label(TempPartL3);LectureGrid.add(partL03Text, 1, 57);

                        Label partL04Text = new Label(TempPartL4);LectureGrid.add(partL04Text, 1, 58);

                        Label labNumbText = new Label(TempLabNumb);LectureGrid.add(labNumbText, 1, 59);

                        Label labWeiText = new Label(TempLabWei);LectureGrid.add(labWeiText, 1, 60);

                        Label labL01Text = new Label(TempLabL1);LectureGrid.add(labL01Text, 1, 61);

                        Label labL02Text = new Label(TempLabL2);LectureGrid.add(labL02Text, 1, 62);

                        Label labL03Text = new Label(TempLabL3);LectureGrid.add(labL03Text, 1, 63);

                        Label labL04Text = new Label(TempLabL4);LectureGrid.add(labL04Text, 1, 64);

                        Label fwNumbText = new Label(TempFieldWNumb);LectureGrid.add(fwNumbText, 1, 65);

                        Label fwWeiText = new Label(TempFieldWWei);LectureGrid.add(fwWeiText, 1, 66);

                        Label fwL01Text = new Label(TempFieldWL1);LectureGrid.add(fwL01Text, 1, 67);

                        Label fwL02Text = new Label(TempFieldWL2);LectureGrid.add(fwL02Text, 1, 68);

                        Label fwL03Text = new Label(TempFieldWL3);LectureGrid.add(fwL03Text, 1, 69);

                        Label fwL04Text = new Label(TempFieldWL4);LectureGrid.add(fwL04Text, 1, 70);

                        Label quizNumbText = new Label(TempQuizNumb);LectureGrid.add(quizNumbText, 1, 71);

                        Label quizWeiText = new Label(TempQuizWei);LectureGrid.add(quizWeiText, 1, 72);

                        Label quizL01Text = new Label(TempQuizL1);LectureGrid.add(quizL01Text, 1, 73);

                        Label quizL02Text = new Label(TempQuizL2);LectureGrid.add(quizL02Text, 1, 74);

                        Label quizL03Text = new Label(TempQuizL3);LectureGrid.add(quizL03Text, 1, 75);

                        Label quizL04Text = new Label(TempQuizL4);LectureGrid.add(quizL04Text, 1, 76);

                        Label HWNumbText = new Label(TempHWNumb);LectureGrid.add(HWNumbText, 1, 77);

                        Label HWWeiText = new Label(TempHWWei);LectureGrid.add(HWWeiText, 1, 78);

                        Label HWL01Text = new Label(TempHWL1);LectureGrid.add(HWL01Text, 1, 79);

                        Label HWL02Text = new Label(TempHWL2);LectureGrid.add(HWL02Text, 1, 80);

                        Label HWL03Text = new Label(TempHWL3);LectureGrid.add(HWL03Text, 1, 81);

                        Label HWL04Text = new Label(TempHWL4);LectureGrid.add(HWL04Text, 1, 82);

                        Label PresNumbText = new Label(TempPresentNumb);LectureGrid.add(PresNumbText, 1, 83);

                        Label PresWeiText = new Label(TempPresentWei);LectureGrid.add(PresWeiText, 1, 84);

                        Label PresL01Text = new Label(TempPresentL1);LectureGrid.add(PresL01Text, 1, 85);

                        Label PresL02Text = new Label(TempPresentL2);LectureGrid.add(PresL02Text, 1, 86);

                        Label PresL03Text = new Label(TempPresentL3);LectureGrid.add(PresL03Text, 1, 87);

                        Label PresL04Text = new Label(TempPresentL4);LectureGrid.add(PresL04Text, 1, 88);

                        Label ProjNumbText = new Label(TempProjectNumb);LectureGrid.add (ProjNumbText, 1, 89);

                        Label ProjWeiText = new Label(TempProjectWei);LectureGrid.add(ProjWeiText, 1, 90);

                        Label ProjL01Text = new Label(TempProjectL1);LectureGrid.add(ProjL01Text, 1, 91);

                        Label ProjL02Text = new Label(TempProjectL2);LectureGrid.add(ProjL02Text, 1, 92);

                        Label ProjL03Text = new Label(TempProjectL3);LectureGrid.add(ProjL03Text, 1, 93);

                        Label ProjL04Text = new Label(TempProjectL4);LectureGrid.add(ProjL04Text, 1, 94);

                        Label PortfolioNumbText = new Label(TempPortNumb);LectureGrid.add (PortfolioNumbText, 1, 95);

                        Label PortfolioWeiText = new Label(TempPortWei);LectureGrid.add(PortfolioWeiText, 1, 96);

                        Label PortfolioL01Text = new Label(TempPortL1);LectureGrid.add(PortfolioL01Text, 1, 97);

                        Label PortfolioL02Text = new Label(TempPortL2);LectureGrid.add(PortfolioL02Text, 1, 98);

                        Label PortfolioL03Text = new Label(TempPortL3);LectureGrid.add(PortfolioL03Text, 1, 99);

                        Label PortfolioL04Text = new Label(TempPortL4);LectureGrid.add(PortfolioL04Text, 1, 100);

                        Label SemNumbText = new Label(TempSeminarNumb);LectureGrid.add(SemNumbText, 1, 101);

                        Label SemWeiText = new Label(TempSeminarWei);LectureGrid.add(SemWeiText, 1, 102);

                        Label SemL01Text = new Label(TempSeminarL1);LectureGrid.add(SemL01Text, 1, 103);

                        Label SemL02Text = new Label(TempSeminarL2);LectureGrid.add(SemL02Text, 1, 104);

                        Label SemL03Text = new Label(TempSeminarL3);LectureGrid.add(SemL03Text, 1, 105);

                        Label SemL04Text = new Label(TempSeminarL4);LectureGrid.add(SemL04Text, 1, 106);

                        Label OralNumbText = new Label(TempOralNumb);LectureGrid.add(OralNumbText, 1, 107);

                        Label OralWeiText = new Label(TempOralWei);LectureGrid.add(OralWeiText, 1, 108);

                        Label OralL01Text = new Label(TempOralL1);LectureGrid.add(OralL01Text, 1, 109);

                        Label OralL02Text = new Label(TempOralL2);LectureGrid.add(OralL02Text, 1, 110);

                        Label OralL03Text = new Label(TempOralL3);LectureGrid.add(OralL03Text, 1, 111);

                        Label OralL04Text = new Label(TempOralL4);LectureGrid.add(OralL04Text, 1, 112);

                        Label MidtermNumbText = new Label(TempMidtermNumb);LectureGrid.add(MidtermNumbText, 1, 113);

                        Label MidtermWeiText = new Label(TempMidtermWei);LectureGrid.add(MidtermWeiText, 1, 114);

                        Label MidtermL01Text = new Label(TempMidtermL1);LectureGrid.add(MidtermL01Text, 1, 115);

                        Label MidtermL02Text = new Label(TempMidtermL2);LectureGrid.add(MidtermL02Text, 1, 116);

                        Label MidtermL03Text = new Label(TempMidtermL3);LectureGrid.add(MidtermL03Text, 1, 117);

                        Label MidtermL04Text = new Label(TempMidtermL4);LectureGrid.add(MidtermL04Text, 1, 118);

                        Label FinalNumbText = new Label(TempFinalNumb);LectureGrid.add(FinalNumbText, 1, 119);

                        Label FinalWeiText = new Label(TempFinalWei);LectureGrid.add(FinalWeiText, 1, 120);

                        Label FinalL01Text = new Label(TempFinalL1);LectureGrid.add(FinalL01Text, 1, 121);

                        Label FinalL02Text = new Label(TempFinalL2);LectureGrid.add(FinalL02Text, 1, 122);

                        Label FinalL03Text = new Label(TempFinalL3);LectureGrid.add(FinalL03Text, 1, 123);

                        Label FinalL04Text = new Label(TempFinalL4);LectureGrid.add(FinalL04Text, 1, 124);

                        Label CourseHourDurText = new Label();LectureGrid.add(CourseHourDurText, 1, 125);

                        Label CourseHourWorkText = new Label();LectureGrid.add(CourseHourWorkText, 1, 126);

                        Label LabHoursDurText = new Label();LectureGrid.add(LabHoursDurText, 1, 127);

                        Label LabHoursWorkText = new Label();LectureGrid.add(LabHoursWorkText, 1, 128);

                        Label StudyHoursoutofClassNumText = new Label(TempSHOOCNum);LectureGrid.add(StudyHoursoutofClassNumText, 1, 129);

                        Label StudyHoursoutofClassDurText = new Label(TempSHOOCDur);LectureGrid.add(StudyHoursoutofClassDurText, 1, 130);

                        Label StudyHoursoutofClassWorkText = new Label(TempSHOOCWork);LectureGrid.add(StudyHoursoutofClassWorkText, 1, 131);

                        Label FieldWorkNumText = new Label(TempFWNum);LectureGrid.add(FieldWorkNumText, 1, 132);

                        Label FieldWorkDurText = new Label(TempFWDur);LectureGrid.add(FieldWorkDurText, 1, 133);

                        Label FieldWorkWorkText = new Label(TempFWWork);LectureGrid.add(FieldWorkWorkText, 1, 134);

                        Label QuizNumText = new Label(TempQuizNum);LectureGrid.add(QuizNumText, 1, 135);

                        Label QuizWorkText = new Label(TempQuizDur);LectureGrid.add(QuizWorkText, 1, 136);

                        Label QuizDurText = new Label(TempQuizWork);LectureGrid.add(QuizDurText, 1, 137);

                        Label HWNumText = new Label(TempHWNum);LectureGrid.add(HWNumText, 1, 138);

                        Label HWDurText = new Label(TempHWDur);LectureGrid.add(HWDurText, 1, 139);

                        Label HWWorkText = new Label(TempHWWork);LectureGrid.add(HWWorkText, 1, 140);

                        Label PresentationNumText = new Label(TempPresentationNum);LectureGrid.add(PresentationNumText, 1, 141);

                        Label PresentationDurText = new Label(TempPresentationDur);LectureGrid.add(PresentationDurText, 1, 142);

                        Label PresentationWorkText = new Label(TempPresentationWork);LectureGrid.add(PresentationWorkText, 1, 143);

                        Label ProjectNumText = new Label(TempProjectNum);LectureGrid.add(ProjectNumText, 1, 144);

                        Label ProjectDurText = new Label(TempProjectDur);LectureGrid.add(ProjectDurText, 1, 145);

                        Label ProjectWorkText = new Label(TempProjectWork);LectureGrid.add(ProjectWorkText, 1, 146);

                        Label PortfolioNumText = new Label(TempPortfolioNum);LectureGrid.add(PortfolioNumText, 1, 147);

                        Label PortfolioDurText = new Label(TempPortfolioDur);LectureGrid.add(PortfolioDurText, 1, 148);

                        Label PortfolioWorkText = new Label(TempPortfolioWork);LectureGrid.add(PortfolioWorkText, 1, 149);

                        Label SeminarNumText = new Label(TempSeminarNum);LectureGrid.add(SeminarNumText, 1, 150);

                        Label SeminarDurText = new Label(TempSeminarDur);LectureGrid.add(SeminarDurText, 1, 151);

                        Label SeminarWorkText = new Label(TempSeminarWork);LectureGrid.add(SeminarWorkText, 1, 152);

                        Label OralExamNumText = new Label(TempOralNum);LectureGrid.add(OralExamNumText, 1, 153);

                        Label OralExamDurText = new Label(TempOralDur);LectureGrid.add(OralExamDurText, 1, 154);

                        Label OralExamWorkText = new Label(TempOralWork);LectureGrid.add(OralExamWorkText, 1, 155);

                        Label MidtermNumText = new Label(TempMidtermNum);LectureGrid.add(MidtermNumText, 1, 156);

                        Label MidtermDurText = new Label(TempMidtermDur);LectureGrid.add(MidtermDurText, 1, 157);

                        Label MidtermWorkText = new Label(TempMidtermWork);LectureGrid.add(MidtermWorkText, 1, 158);

                        Label FinalExamNumText = new Label(TempFinalNum);LectureGrid.add(FinalExamNumText, 1, 159);

                        Label FinalExamDurText = new Label(TempFinalDur);LectureGrid.add(FinalExamDurText, 1, 160);

                        Label FinalExamWorkText = new Label(TempFinalWork);LectureGrid.add(FinalExamWorkText, 1, 161);

                        Label outcome1_1Text = new Label(outcome1_1);
                        LectureGrid.add(outcome1_1Text, 1, 162);

                        Label outcome1_2Text = new Label(outcome1_2);
                        LectureGrid.add(outcome1_2Text, 1, 163);

                        Label outcome1_3Text = new Label(outcome1_3);
                        LectureGrid.add(outcome1_3Text, 1, 164);

                        Label outcome1_4Text = new Label(outcome1_4);
                        LectureGrid.add(outcome1_4Text, 1, 165);

                        Label outcome1_5Text = new Label(outcome1_5);
                        LectureGrid.add(outcome1_5Text, 1, 166);

                        Label outcome2_1Text = new Label(outcome2_1);
                        LectureGrid.add(outcome2_1Text, 1, 167);

                        Label outcome2_2Text = new Label(outcome2_2);
                        LectureGrid.add(outcome2_2Text, 1, 168);

                        Label outcome2_3Text =  new Label(outcome2_3);
                        LectureGrid.add(outcome2_3Text, 1, 169);

                        Label outcome2_4Text = new Label(outcome2_4);
                        LectureGrid.add(outcome2_4Text, 1, 170);

                        Label outcome2_5Text = new Label(outcome2_5);
                        LectureGrid.add(outcome2_5Text, 1, 171);

                        Label outcome3_1Text = new Label(outcome3_1);
                        LectureGrid.add(outcome3_1Text, 1, 172);

                        Label outcome3_2Text = new Label(outcome3_2);
                        LectureGrid.add(outcome3_2Text, 1, 173);

                        Label outcome3_3Text = new Label(outcome3_3);
                        LectureGrid.add(outcome3_3Text, 1, 174);

                        Label outcome3_4Text = new Label(outcome3_4);
                        LectureGrid.add(outcome3_4Text, 1, 175);

                        Label outcome3_5Text = new Label(outcome3_5);
                        LectureGrid.add(outcome3_5Text, 1, 176);

                        Label outcome4_1Text = new Label(outcome4_1);
                        LectureGrid.add(outcome4_1Text, 1, 177);

                        Label outcome4_2Text = new Label(outcome4_2);
                        LectureGrid.add(outcome4_2Text, 1, 178);

                        Label outcome4_3Text = new Label(outcome4_3);
                        LectureGrid.add(outcome4_3Text, 1, 179);

                        Label outcome4_4Text = new Label(outcome4_4);
                        LectureGrid.add(outcome4_4Text, 1, 180);

                        Label outcome4_5Text = new Label(outcome4_5);
                        LectureGrid.add(outcome4_5Text, 1, 181);

                        Label outcome5_1Text = new Label(outcome5_1);
                        LectureGrid.add(outcome5_1Text, 1, 182);

                        Label outcome5_2Text = new Label(outcome5_2);
                        LectureGrid.add(outcome5_2Text, 1, 183);

                        Label outcome5_3Text = new Label(outcome5_3);
                        LectureGrid.add(outcome5_3Text, 1, 184);

                        Label outcome5_4Text = new Label(outcome5_4);
                        LectureGrid.add(outcome5_4Text, 1, 185);

                        Label outcome5_5Text = new Label(outcome5_5);
                        LectureGrid.add(outcome5_5Text, 1, 186);

                        Label outcome6_1Text = new Label(outcome6_1);
                        LectureGrid.add(outcome6_1Text, 1, 187);

                        Label outcome6_2Text = new Label(outcome6_2);
                        LectureGrid.add(outcome6_2Text, 1, 188);

                        Label outcome6_3Text = new Label(outcome6_3);
                        LectureGrid.add(outcome6_3Text, 1, 189);

                        Label outcome6_4Text = new Label(outcome6_4);
                        LectureGrid.add(outcome6_4Text, 1, 190);

                        Label outcome6_5Text = new Label(outcome6_5);
                        LectureGrid.add(outcome6_5Text, 1, 191);

                        Label outcome7_1Text = new Label(outcome7_1);
                        LectureGrid.add(outcome7_1Text, 1, 192);

                        Label outcome7_2Text = new Label(outcome7_2);
                        LectureGrid.add(outcome7_2Text, 1, 193);

                        Label outcome7_3Text = new Label(outcome7_3);
                        LectureGrid.add(outcome7_3Text, 1, 194);

                        Label outcome7_4Text = new Label(outcome7_4);
                        LectureGrid.add(outcome7_4Text, 1, 195);

                        Label outcome7_5Text = new Label(outcome7_5);
                        LectureGrid.add(outcome7_5Text, 1, 196);

                        Label outcome8_1Text = new Label(outcome8_1);
                        LectureGrid.add(outcome8_1Text, 1, 197);

                        Label outcome8_2Text = new Label(outcome8_2);
                        LectureGrid.add(outcome8_2Text, 1, 198);

                        Label outcome8_3Text = new Label(outcome8_3);
                        LectureGrid.add(outcome8_3Text, 1, 199);

                        Label outcome8_4Text = new Label(outcome8_4);
                        LectureGrid.add(outcome8_4Text, 1, 200);

                        Label outcome8_5Text = new Label(outcome8_5);
                        LectureGrid.add(outcome8_5Text, 1, 201);

                        Label outcome9_1Text = new Label(outcome9_1);
                        LectureGrid.add(outcome9_1Text, 1, 202);

                        Label outcome9_2Text = new Label(outcome9_2);
                        LectureGrid.add(outcome9_2Text, 1, 203);

                        Label outcome9_3Text = new Label(outcome9_3);
                        LectureGrid.add(outcome9_3Text, 1, 204);

                        Label outcome9_4Text = new Label(outcome9_4);
                        LectureGrid.add(outcome9_4Text, 1, 205);

                        Label outcome9_5Text = new Label(outcome9_5);
                        LectureGrid.add(outcome9_5Text, 1, 206);

                        Label outcome10_1Text = new Label(outcome10_1);
                        LectureGrid.add(outcome10_1Text, 1, 207);

                        Label outcome10_2Text = new Label(outcome10_2);
                        LectureGrid.add(outcome10_2Text, 1, 208);

                        Label outcome10_3Text = new Label(outcome10_3);
                        LectureGrid.add(outcome10_3Text, 1, 209);

                        Label outcome10_4Text = new Label(outcome10_4);
                        LectureGrid.add(outcome10_4Text, 1, 210);

                        Label outcome10_5Text = new Label(outcome10_5);
                        LectureGrid.add(outcome10_5Text, 1, 211);

                        Label outcome11_1Text = new Label(outcome11_1);
                        LectureGrid.add(outcome11_1Text, 1, 212);

                        Label outcome11_2Text = new Label(outcome11_2);
                        LectureGrid.add(outcome11_2Text, 1, 213);

                        Label outcome11_3Text = new Label(outcome11_3);
                        LectureGrid.add(outcome11_3Text, 1, 214);

                        Label outcome11_4Text = new Label(outcome11_4);
                        LectureGrid.add(outcome11_4Text, 1, 215);

                        Label outcome11_5Text = new Label(outcome11_5);
                        LectureGrid.add(outcome11_5Text, 1, 216);

                        Label outcome12_1Text = new Label(outcome12_1);
                        LectureGrid.add(outcome12_1Text, 1, 217);

                        Label outcome12_2Text = new Label(outcome12_2);
                        LectureGrid.add(outcome12_2Text, 1, 218);

                        Label outcome12_3Text = new Label(outcome12_3);
                        LectureGrid.add(outcome12_3Text, 1, 219);

                        Label outcome12_4Text = new Label(outcome12_4);
                        LectureGrid.add(outcome12_4Text, 1, 220);

                        Label outcome12_5Text = new Label(outcome12_5);
                        LectureGrid.add(outcome12_5Text, 1, 221);

                        Label outcome13_1Text = new Label(outcome13_1);
                        LectureGrid.add(outcome13_1Text, 1, 222);

                        Label outcome13_2Text = new Label(outcome13_2);
                        LectureGrid.add(outcome13_2Text, 1, 223);

                        Label outcome13_3Text = new Label(outcome13_3);
                        LectureGrid.add(outcome13_3Text, 1, 224);

                        Label outcome13_4Text = new Label(outcome13_4);
                        LectureGrid.add(outcome13_4Text, 1, 225);

                        Label outcome13_5Text = new Label(outcome13_5);
                        LectureGrid.add(outcome13_5Text, 1, 226);
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

                EditLectureHBox.setVisible(true);



                AddLectureBox.setVisible(false);



                BoxBlur blur = new BoxBlur();
                blur.setWidth(10);
                blur.setHeight(10);
                blur.setIterations(3);
                LecturesHBox.setEffect(blur);






                LectureConfig Lecture = DBConnector.getInstance().getLectureConfigObject(lec_id);
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
                String TempPartNumb = Lecture.getParticipation_Number();
                String TempPartWei = Lecture.getParticipation_Weigthing();
                String TempPartL01 = Lecture.getParticipation_LO1();
                String TempPartL02 = Lecture.getParticipation_LO2();
                String TempPartL03 = Lecture.getParticipation_LO3();
                String TempPartL04 = Lecture.getParticipation_LO4();
                String TempLabNumb = Lecture.getLaboratory_or_Application_Number();
                String TempLabWei = Lecture.getLaboratory_or_Application_Weigthing();
                String TempLabL01 = Lecture.getLaboratory_or_Application_LO1();
                String TempLabL02 = Lecture.getLaboratory_or_Application_LO2();
                String TempLabL03 = Lecture.getLaboratory_or_Application_LO3();
                String TempLabL04 = Lecture.getLaboratory_or_Application_LO4();
                String TempFWNumb = Lecture.getField_Work_Number();
                String TempFWWei = Lecture.getField_Work_Weigthing();
                String TempFWL01 = Lecture.getField_Work_LO1();
                String TempFWL02 = Lecture.getField_Work_LO2();
                String TempFWL03 = Lecture.getField_Work_LO3();
                String TempFWL04 = Lecture.getField_Work_LO4();
                String TempQuizNumb = Lecture.getQuiz_or_StudioCritique_Number();
                String TempQuizWei = Lecture.getQuiz_or_StudioCritique_Weigthing();
                String TempQuizL01 = Lecture.getQuiz_or_StudioCritique_LO1();
                String TempQuizL02 = Lecture.getQuiz_or_StudioCritique_LO2();
                String TempQuizL03 = Lecture.getQuiz_or_StudioCritique_LO3();
                String TempQuizL04 = Lecture.getQuiz_or_StudioCritique_LO4();
                String TempHWNumb = Lecture.getHomework_or_Assignment_Number();
                String TempHWWei = Lecture.getHomework_or_Assignment_Weigthing();
                String TempHWL01 = Lecture.getHomework_or_Assignment_LO1();
                String TempHWL02 = Lecture.getHomework_or_Assignment_LO2();
                String TempHWL03 = Lecture.getHomework_or_Assignment_LO3();
                String TempHWL04 = Lecture.getHomework_or_Assignment_LO4();
                String TempPresNumb = Lecture.getPresentation_or_Jury_Number();
                String TempPresWei = Lecture.getPresentation_or_Jury_Weigthing();
                String TempPresL01 = Lecture.getPresentation_or_Jury_LO1();
                String TempPresL02 = Lecture.getPresentation_or_Jury_LO2();
                String TempPresL03 = Lecture.getPresentation_or_Jury_LO3();
                String TempPresL04 = Lecture.getPresentation_or_Jury_LO4();
                String TempProjNumb = Lecture.getProject_Number();
                String TempProjWei = Lecture.getProject_Weigthing();
                String TempProjL01 = Lecture.getProject_LO1();
                String TempProjL02 = Lecture.getProject_LO2();
                String TempProjL03 = Lecture.getProject_LO3();
                String TempProjL04 = Lecture.getProject_LO4();
                String TempPortNumb = Lecture.getPortfolio_Number();
                String TempPortWei = Lecture.getPortfolio_Weigthing();
                String TempPortL01 = Lecture.getPortfolio_LO1();
                String TempPortL02 = Lecture.getPortfolio_LO2();
                String TempPortL03 = Lecture.getPortfolio_LO3();
                String TempPortL04 = Lecture.getPortfolio_LO4();
                String TempSEMNumb = Lecture.getSeminar_or_Workshop_Number();
                String TempSEMWei = Lecture.getSeminar_or_Workshop_Weigthing();
                String TempSEML01 = Lecture.getSeminar_or_Workshop_LO1();
                String TempSEML02 = Lecture.getSeminar_or_Workshop_LO2();
                String TempSEML03 = Lecture.getSeminar_or_Workshop_LO3();
                String TempSEML04 = Lecture.getSeminar_or_Workshop_LO4();
                String TempORALNumb = Lecture.getOral_Exam_Number();
                String TempORALWei = Lecture.getOral_Exam_Weigthing();
                String TempORALL01 = Lecture.getOral_Exam_LO1();
                String TempORALL02 = Lecture.getOral_Exam_LO2();
                String TempORALL03 = Lecture.getOral_Exam_LO3();
                String TempORALL04 = Lecture.getOral_Exam_LO4();
                String TempMIDNumb = Lecture.getMidterm_Number();
                String TempMIDWei = Lecture.getMidterm_Weigthing();
                String TempMIDL01 = Lecture.getMidterm_LO1();
                String TempMIDL02 = Lecture.getMidterm_LO2();
                String TempMIDL03 =Lecture.getMidterm_LO3();
                String TempMIDL04 = Lecture.getMidterm_LO4();
                String TempFINALNumb = Lecture.getFinal_Exam_Number();
                String TempFINALWei = Lecture.getFinal_Exam_Weigthing();
                String TempFINALL01 = Lecture.getFinal_Exam_LO1();
                String TempFINALL02 = Lecture.getFinal_Exam_LO2();
                String TempFINALL03 = Lecture.getFinal_Exam_LO3();
                String TempFINALL04 = Lecture.getFinal_Exam_LO4();
                String TempSHOOCNum = Lecture.getStudyHoursoutofClassNum();
                String TempSHOOCDur = Lecture.getStudyHoursoutofClassDur();
                String TempSHOOCWork = Lecture.getStudyHoursoutofClassWork();
                String TempFWNum = Lecture.getFieldWorkNum();
                String TempFWDur = Lecture.getFieldWorkDur();
                String TempFWWork = Lecture.getFieldWorkWork();
                String TempQuizNum = Lecture.getQuizNum();
                String TempQuizDur = Lecture.getQuizDur();
                String TempQuizWork = Lecture.getQuizWork();
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
                String Tempoutcome1_1 = Lecture.getOutCome1_1();
                String Tempoutcome1_2 = Lecture.getOutCome1_2();
                String Tempoutcome1_3 = Lecture.getOutCome1_3();
                String Tempoutcome1_4 = Lecture.getOutCome1_4();
                String Tempoutcome1_5 = Lecture.getOutCome1_5();
                String Tempoutcome2_1 = Lecture.getOutCome2_1();
                String Tempoutcome2_2 = Lecture.getOutCome2_2();
                String Tempoutcome2_3 = Lecture.getOutCome2_3();
                String Tempoutcome2_4 = Lecture.getOutCome2_4();
                String Tempoutcome2_5 = Lecture.getOutCome2_5();
                String Tempoutcome3_1 = Lecture.getOutCome3_1();
                String Tempoutcome3_2 = Lecture.getOutCome3_2();
                String Tempoutcome3_3 = Lecture.getOutCome3_3();
                String Tempoutcome3_4 = Lecture.getOutCome3_4();
                String Tempoutcome3_5 = Lecture.getOutCome3_5();
                String Tempoutcome4_1 = Lecture.getOutCome4_1();
                String Tempoutcome4_2 = Lecture.getOutCome4_2();
                String Tempoutcome4_3 = Lecture.getOutCome4_3();
                String Tempoutcome4_4 = Lecture.getOutCome4_4();
                String Tempoutcome4_5 = Lecture.getOutCome4_5();
                String Tempoutcome5_1 = Lecture.getOutCome5_1();
                String Tempoutcome5_2 = Lecture.getOutCome5_2();
                String Tempoutcome5_3 = Lecture.getOutCome5_3();
                String Tempoutcome5_4 = Lecture.getOutCome5_4();
                String Tempoutcome5_5 = Lecture.getOutCome5_5();
                String Tempoutcome6_1 = Lecture.getOutCome6_1();
                String Tempoutcome6_2 = Lecture.getOutCome6_2();
                String Tempoutcome6_3 = Lecture.getOutCome6_3();
                String Tempoutcome6_4 = Lecture.getOutCome6_4();
                String Tempoutcome6_5 = Lecture.getOutCome6_5();
                String Tempoutcome7_1 = Lecture.getOutCome7_1();
                String Tempoutcome7_2 = Lecture.getOutCome7_2();
                String Tempoutcome7_3 = Lecture.getOutCome7_3();
                String Tempoutcome7_4 = Lecture.getOutCome7_4();
                String Tempoutcome7_5 = Lecture.getOutCome7_5();
                String Tempoutcome8_1 = Lecture.getOutCome8_1();
                String Tempoutcome8_2 = Lecture.getOutCome8_2();
                String Tempoutcome8_3 = Lecture.getOutCome8_3();
                String Tempoutcome8_4 = Lecture.getOutCome8_4();
                String Tempoutcome8_5 = Lecture.getOutCome8_5();
                String Tempoutcome9_1 = Lecture.getOutCome9_1();
                String Tempoutcome9_2 = Lecture.getOutCome9_2();
                String Tempoutcome9_3 = Lecture.getOutCome9_3();
                String Tempoutcome9_4 = Lecture.getOutCome9_4();
                String Tempoutcome9_5 = Lecture.getOutCome9_5();
                String Tempoutcome10_1 = Lecture.getOutCome10_1();
                String Tempoutcome10_2 = Lecture.getOutCome10_2();
                String Tempoutcome10_3 = Lecture.getOutCome10_3();
                String Tempoutcome10_4 = Lecture.getOutCome10_4();
                String Tempoutcome10_5 = Lecture.getOutCome10_5();
                String Tempoutcome11_1 = Lecture.getOutCome11_1();
                String Tempoutcome11_2 = Lecture.getOutCome11_2();
                String Tempoutcome11_3 = Lecture.getOutCome11_3();
                String Tempoutcome11_4 = Lecture.getOutCome11_4();
                String Tempoutcome11_5 = Lecture.getOutCome11_5();
                String Tempoutcome12_1 = Lecture.getOutCome12_1();
                String Tempoutcome12_2 = Lecture.getOutCome12_2();
                String Tempoutcome12_3 = Lecture.getOutCome12_3();
                String Tempoutcome12_4 = Lecture.getOutCome12_4();
                String Tempoutcome12_5 = Lecture.getOutCome12_5();
                String Tempoutcome13_1 = Lecture.getOutCome13_1();
                String Tempoutcome13_2 = Lecture.getOutCome13_2();
                String Tempoutcome13_3 = Lecture.getOutCome13_3();
                String Tempoutcome13_4 = Lecture.getOutCome13_4();
                String Tempoutcome13_5 = Lecture.getOutCome13_5();

                Label LectureID = new Label("Lecture ID :");
                EditLectureoldvalue.add(LectureID, 0, 0);

                Label lectureNameLabel = new Label("Lecture Name:");
                EditLectureoldvalue.add(lectureNameLabel, 0, 1);

                Label lecturerNameLabel = new Label("Lecturer's Name:");
                EditLectureoldvalue.add(lecturerNameLabel, 0, 2);

                Label lectureCodeLabel = new Label("Lecture Code:");
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
                EditLectureoldvalue.add(week1ReqMatLabel, 0, 35);

                Label week2ReqMatLabel = new Label("Week 2 Required Materials:");
                EditLectureoldvalue.add(week2ReqMatLabel, 0, 36);

                Label week3ReqMatLabel = new Label("Week 3 Required Materials:");
                EditLectureoldvalue.add(week3ReqMatLabel, 0, 37);

                Label week4ReqMatLabel = new Label("Week 4 Required Materials:");
                EditLectureoldvalue.add(week4ReqMatLabel, 0, 38);

                Label week5ReqMatLabel = new Label("Week 5 Required Materials:");
                EditLectureoldvalue.add(week5ReqMatLabel, 0, 39);

                Label week6ReqMatLabel = new Label("Week 6 Required Materials:");
                EditLectureoldvalue.add(week6ReqMatLabel, 0, 40);

                Label week7ReqMatLabel = new Label("Week 7 Required Materials:");
                EditLectureoldvalue.add(week7ReqMatLabel, 0, 41);

                Label week8ReqMatLabel = new Label("Week 8 Required Materials:");
                EditLectureoldvalue.add(week8ReqMatLabel, 0, 42);

                Label week9ReqMatLabel = new Label("Week 9 Required Materials:");
                EditLectureoldvalue.add(week9ReqMatLabel, 0, 43);

                Label week10ReqMatLabel = new Label("Week 10 Required Materials:");
                EditLectureoldvalue.add(week10ReqMatLabel, 0, 44);

                Label week11ReqMatLabel = new Label("Week 11 Required Materials:");
                EditLectureoldvalue.add(week11ReqMatLabel, 0, 45);

                Label week12ReqMatLabel = new Label("Week 12 Required Materials:");
                EditLectureoldvalue.add(week12ReqMatLabel, 0,46 );

                Label week13ReqMatLabel = new Label("Week 13 Required Materials:");
                EditLectureoldvalue.add(week13ReqMatLabel, 0, 47);

                Label week14ReqMatLabel = new Label("Week 14 Required Materials:");
                EditLectureoldvalue.add(week14ReqMatLabel, 0, 48);

                Label week15ReqMatLabel = new Label("Week 15 Required Materials:");
                EditLectureoldvalue.add(week15ReqMatLabel, 0, 49);

                Label courseNotesAndTextBooksLabel = new Label("Course Notes and Textbooks:");
                EditLectureoldvalue.add(courseNotesAndTextBooksLabel, 0, 50);

                Label suggestedReadingsAndMaterialsLabel = new Label("Suggested Readings and Materials:");
                EditLectureoldvalue.add(suggestedReadingsAndMaterialsLabel, 0, 51);

                Label participationNumberLabel = new Label("Participation Number:");
                EditLectureoldvalue.add(participationNumberLabel, 0, 52);

                Label  participationWeiLabel= new Label("Participation Weighting:");
                EditLectureoldvalue.add(participationWeiLabel, 0, 53);

                Label  participation01Label= new Label("Participation LO1:");
                EditLectureoldvalue.add(participation01Label, 0, 54);

                Label participation02Label = new Label("Participation LO2:");
                EditLectureoldvalue.add(participation02Label, 0, 55);

                Label participation03Label = new Label("Participation LO3:");
                EditLectureoldvalue.add(participation03Label, 0, 56);

                Label  participation04Label= new Label("Participation LO4:");
                EditLectureoldvalue.add(participation04Label, 0, 57);

                Label labOrApplicationNumberLabel = new Label("Laboratory Number:");
                EditLectureoldvalue.add(labOrApplicationNumberLabel, 0, 58);

                Label  labOrApplicationWeiLabel= new Label("Laboratory Weighting:");
                EditLectureoldvalue.add(labOrApplicationWeiLabel, 0, 59);

                Label labOrApplication01Label = new Label("Laboratory LO1:");
                EditLectureoldvalue.add(labOrApplication01Label, 0, 60);

                Label labOrApplication02Label = new Label("Laboratory LO2:");
                EditLectureoldvalue.add(labOrApplication02Label, 0, 61);

                Label labOrApplication03Label = new Label("Laboratory LO3:");
                EditLectureoldvalue.add(labOrApplication03Label, 0, 62);

                Label labOrApplication04Label = new Label("Laboratory LO4:");
                EditLectureoldvalue.add(labOrApplication04Label, 0, 63);

                Label fieldWorkNumberLabel = new Label("Field Work Number:");
                EditLectureoldvalue.add(fieldWorkNumberLabel, 0, 64);

                Label FieldWorkWeiLabel = new Label("Field Work Weighting");
                EditLectureoldvalue.add(FieldWorkWeiLabel, 0, 65);

                Label FieldWork01Label = new Label("Field Work LO1:");
                EditLectureoldvalue.add(FieldWork01Label, 0, 66);

                Label FieldWork02Label = new Label("Field Work LO2:");
                EditLectureoldvalue.add(FieldWork02Label, 0, 67);

                Label FieldWork03Label = new Label("Field Work LO3:");
                EditLectureoldvalue.add(FieldWork03Label, 0, 68);

                Label FieldWork04Label = new Label("Field Work LO4:");
                EditLectureoldvalue.add(FieldWork04Label, 0, 69);

                Label quizOrStudioCritiqueNumberLabel = new Label("Quiz Number:");
                EditLectureoldvalue.add(quizOrStudioCritiqueNumberLabel, 0, 70);

                Label quizOrStudioCritiqueWeiLabel = new Label("Quiz Weighting:");
                EditLectureoldvalue.add(quizOrStudioCritiqueWeiLabel, 0,71);

                Label quizOrStudioCritique01Label = new Label("Quiz LO1:");
                EditLectureoldvalue.add(quizOrStudioCritique01Label, 0, 72);

                Label quizOrStudioCritique02Label = new Label("Quiz LO2:");
                EditLectureoldvalue.add(quizOrStudioCritique02Label, 0, 73);

                Label quizOrStudioCritique03Label = new Label("Quiz LO3:");
                EditLectureoldvalue.add(quizOrStudioCritique03Label, 0, 74);

                Label quizOrStudioCritique04Label = new Label("Quiz LO4:");
                EditLectureoldvalue.add(quizOrStudioCritique04Label, 0, 75);

                Label homeworkOrAssignmentNumberLabel = new Label("Homework Number:");
                EditLectureoldvalue.add(homeworkOrAssignmentNumberLabel, 0, 76);

                Label homeworkOrAssignmentWeiLabel = new Label("Homework Weighting:");
                EditLectureoldvalue.add(homeworkOrAssignmentWeiLabel, 0, 77);

                Label homeworkOrAssignment01Label = new Label("Homework LO1:");
                EditLectureoldvalue.add(homeworkOrAssignment01Label, 0, 78);

                Label homeworkOrAssignment02Label = new Label("Homework LO2:");
                EditLectureoldvalue.add(homeworkOrAssignment02Label, 0, 79);

                Label homeworkOrAssignment03Label = new Label("Homework LO3:");
                EditLectureoldvalue.add(homeworkOrAssignment03Label, 0, 80);

                Label homeworkOrAssignment04Label = new Label("Homework LO4:");
                EditLectureoldvalue.add(homeworkOrAssignment04Label, 0, 81);

                Label presentationOrJuryNumberLabel = new Label("Presentation Number:");
                EditLectureoldvalue.add(presentationOrJuryNumberLabel, 0, 82);

                Label presentationOrJuryWeiLabel = new Label("Presentation Weighting:");
                EditLectureoldvalue.add(presentationOrJuryWeiLabel, 0, 83);

                Label presentationOrJury01Label = new Label("Presentation LO1:");
                EditLectureoldvalue.add(presentationOrJury01Label, 0, 84);

                Label presentationOrJury02Label = new Label("Presentation LO2:");
                EditLectureoldvalue.add(presentationOrJury02Label, 0, 85);

                Label presentationOrJury03Label = new Label("Presentation LO3:");
                EditLectureoldvalue.add(presentationOrJury03Label, 0, 86);

                Label presentationOrJury04Label = new Label("Presentation LO4:");
                EditLectureoldvalue.add(presentationOrJury04Label, 0, 87);

                Label projectNumberLabel = new Label("Project Number:");
                EditLectureoldvalue.add(projectNumberLabel, 0, 88);

                Label projectWeiLabel = new Label("Project Weighting:");
                EditLectureoldvalue.add(projectWeiLabel, 0, 89);

                Label project01Label = new Label("Project LO1:");
                EditLectureoldvalue.add(project01Label, 0, 90);

                Label project02Label = new Label("Project LO2:");
                EditLectureoldvalue.add(project02Label, 0, 91);

                Label project03Label = new Label("Project LO3:");
                EditLectureoldvalue.add(project03Label, 0, 92);

                Label project04Label = new Label("Project LO4:");
                EditLectureoldvalue.add(project04Label, 0, 93);

                Label portfolioNumberLabel = new Label("Portfolio Number:");
                EditLectureoldvalue.add(portfolioNumberLabel, 0, 94);

                Label portfolioWeiLabel = new Label("Portfolio Weighting:");
                EditLectureoldvalue.add(portfolioWeiLabel, 0, 95);

                Label portfolio01Label = new Label("Portfolio LO1:");
                EditLectureoldvalue.add(portfolio01Label, 0, 96);

                Label portfolio02Label = new Label("Portfolio LO2:");
                EditLectureoldvalue.add(portfolio02Label, 0, 97);

                Label portfolio03Label = new Label("Portfolio LO3:");
                EditLectureoldvalue.add(portfolio03Label, 0, 98);

                Label portfolio04Label = new Label("Portfolio LO4:");
                EditLectureoldvalue.add(portfolio04Label, 0, 99);

                Label seminarNumberLabel = new Label("Seminar Number:");
                EditLectureoldvalue.add(seminarNumberLabel, 0, 100);

                Label seminarWeiLabel = new Label("Seminar Weighting:");
                EditLectureoldvalue.add(seminarWeiLabel, 0, 101);

                Label seminar01Label = new Label("Seminar LO1:");
                EditLectureoldvalue.add(seminar01Label, 0, 102);

                Label seminar02Label = new Label("Seminar LO2:");
                EditLectureoldvalue.add(seminar02Label, 0, 103);

                Label seminar03Label = new Label("Seminar LO3:");
                EditLectureoldvalue.add(seminar03Label, 0, 104);

                Label seminar04Label = new Label("Seminar LO4:");
                EditLectureoldvalue.add(seminar04Label, 0, 105);

                Label oralNumberLabel = new Label("Oral Exam Number:");
                EditLectureoldvalue.add(oralNumberLabel, 0, 106);

                Label oralWeiLabel = new Label("Oral Exam Weighting:");
                EditLectureoldvalue.add(oralWeiLabel, 0, 107);

                Label oral01Label = new Label("Oral Exam LO1:");
                EditLectureoldvalue.add(oral01Label, 0, 108);

                Label oral02Label = new Label("Oral Exam LO2:");
                EditLectureoldvalue.add(oral02Label, 0, 109);

                Label oral03Label = new Label("Oral Exam LO3:");
                EditLectureoldvalue.add(oral03Label, 0, 110);

                Label oral04Label = new Label("Oral Exam LO4:");
                EditLectureoldvalue.add(oral04Label, 0, 111);

                Label midtermNumberLabel = new Label("Midterm Number:");
                EditLectureoldvalue.add(midtermNumberLabel, 0, 112);

                Label midtermWeiLabel = new Label("Midterm Weighting:");
                EditLectureoldvalue.add(midtermWeiLabel, 0, 113);

                Label midterm01Label = new Label("Midterm LO1:");
                EditLectureoldvalue.add(midterm01Label, 0, 114);

                Label midterm02Label = new Label("Midterm LO2:");
                EditLectureoldvalue.add(midterm02Label, 0, 115);

                Label midterm03Label = new Label("Midterm LO3:");
                EditLectureoldvalue.add(midterm03Label, 0, 116);

                Label midterm04Label = new Label("Midterm LO4:");
                EditLectureoldvalue.add(midterm04Label, 0, 117);

                Label finalNumberLabel = new Label("Final Exam Number:");
                EditLectureoldvalue.add(finalNumberLabel, 0, 118);

                Label finalWeiLabel = new Label("Final Exam Weighting:");
                EditLectureoldvalue.add(finalWeiLabel, 0, 119);

                Label final01Label = new Label("Final Exam LO1:");
                EditLectureoldvalue.add(final01Label, 0, 120);

                Label final02Label = new Label("Final Exam LO2:");
                EditLectureoldvalue.add(final02Label, 0, 121);

                Label final03Label = new Label("Final Exam LO3:");
                EditLectureoldvalue.add(final03Label, 0, 122);

                Label final04Label = new Label("Final Exam LO4:");
                EditLectureoldvalue.add(final04Label, 0, 123);

                Label StudyHoursoutofClassNumLabel = new Label("Study Hours out of Class Number:");
                EditLectureoldvalue.add(StudyHoursoutofClassNumLabel, 0, 125);

                Label StudyHoursoutofClassDurLabel = new Label("Study Hours out of Class Duration:");
                EditLectureoldvalue.add(StudyHoursoutofClassDurLabel, 0, 126);

                Label StudyHoursoutofClassWorkLabel = new Label("Study Hours out of Class Workload:");
                EditLectureoldvalue.add(StudyHoursoutofClassWorkLabel, 0, 127);

                Label FieldWorkNumLabel = new Label("Field Work Number");
                EditLectureoldvalue.add(FieldWorkNumLabel, 0, 128);

                Label FieldWorkDurLabel = new Label("Field Work Duration");
                EditLectureoldvalue.add(FieldWorkDurLabel, 0, 129);

                Label FieldWorkWorkLabel = new Label("Field Work Workload");
                EditLectureoldvalue.add(FieldWorkWorkLabel, 0, 130);

                Label QuizNumLabel = new Label("Quiz/Studio Critique Number:");
                EditLectureoldvalue.add(QuizNumLabel, 0, 131);

                Label QuizDurLabel = new Label("Quiz/Studio Critique Duration:");
                EditLectureoldvalue.add(QuizDurLabel, 0, 132);

                Label QuizWorkLabel = new Label("Quiz/Studio Critique Workload:");
                EditLectureoldvalue.add(QuizWorkLabel, 0, 133);

                Label HWNumLabel = new Label("Homework/Assignments Number:");
                EditLectureoldvalue.add(HWNumLabel, 0, 134);

                Label HWDurLabel = new Label("Homework/Assignments Duration:");
                EditLectureoldvalue.add(HWDurLabel, 0, 135);

                Label HWWorkLabel = new Label("Homework/Assignments Workload:");
                EditLectureoldvalue.add(HWWorkLabel, 0, 136);

                Label PresentationNumLabel = new Label("Presentation/Jury Number:");
                EditLectureoldvalue.add(PresentationNumLabel, 0, 137);

                Label PresentationDurLabel = new Label("Presentation/Jury Duration:");
                EditLectureoldvalue.add(PresentationDurLabel, 0, 138);

                Label PresentationWorkLabel = new Label("Presentation/Jury Workload:");
                EditLectureoldvalue.add(PresentationWorkLabel, 0, 139);

                Label ProjectNumLabel = new Label("Project Number:");
                EditLectureoldvalue.add(ProjectNumLabel, 0, 140);

                Label ProjectDurLabel = new Label("Project Duration:");
                EditLectureoldvalue.add(ProjectDurLabel, 0, 141);

                Label ProjectWorkLabel = new Label("Project Workload:");
                EditLectureoldvalue.add(ProjectWorkLabel, 0, 142);

                Label PortfolioNumLabel = new Label("Portfolio Number:");
                EditLectureoldvalue.add(PortfolioNumLabel, 0, 143);

                Label PortfolioDurLabel = new Label("Portfolio Duration:");
                EditLectureoldvalue.add(PortfolioDurLabel, 0, 144);

                Label PortfolioWorkLabel = new Label("Portfolio Workload:");
                EditLectureoldvalue.add(PortfolioWorkLabel, 0, 145);

                Label SeminarNumLabel = new Label("Seminar/Workshop Number:");
                EditLectureoldvalue.add(SeminarNumLabel, 0,146);

                Label SeminarDurLabel = new Label("Seminar/Workshop Duration:");
                EditLectureoldvalue.add(SeminarDurLabel, 0,147);

                Label SeminarWorkLabel = new Label("Seminar/Workshop Workload:");
                EditLectureoldvalue.add(SeminarWorkLabel, 0,148);

                Label OralExamNumLabel = new Label("Oral Exam Number:");
                EditLectureoldvalue.add(OralExamNumLabel, 0, 149);

                Label OralExamDurLabel = new Label("Oral Exam Duration:");
                EditLectureoldvalue.add(OralExamDurLabel, 0, 150);

                Label OralExamWorkLabel = new Label("Oral Exam Workload:");
                EditLectureoldvalue.add(OralExamWorkLabel, 0, 151);

                Label MidtermNumLabel = new Label("Midterm Number: ");
                EditLectureoldvalue.add(MidtermNumLabel, 0, 152);

                Label MidtermDurLabel = new Label("Midterm Duration:");
                EditLectureoldvalue.add(MidtermDurLabel, 0, 153);

                Label MidtermWorkLabel = new Label("Midterm Workload:");
                EditLectureoldvalue.add(MidtermWorkLabel, 0, 154);

                Label FinalNumLabel = new Label("Final Exam Number:");
                EditLectureoldvalue.add(FinalNumLabel, 0, 155);

                Label FinalDurLabel = new Label("Final Exam Duration:");
                EditLectureoldvalue.add(FinalDurLabel, 0, 156);

                Label FinalWorkLabel = new Label("Final Exam Workload:");
                EditLectureoldvalue.add(FinalWorkLabel, 0, 157);

                Label  outcome1_1Label= new Label("Outcome 1_1:");
                EditLectureoldvalue.add(outcome1_1Label, 0, 159);

                Label outcome1_2Label = new Label("Outcome 1_2:");
                EditLectureoldvalue.add(outcome1_2Label, 0,  160);

                Label outcome1_3Label = new Label("Outcome 1_3:");
                EditLectureoldvalue.add(outcome1_3Label, 0, 161);

                Label  outcome1_4Label= new Label("Outcome 1_4:");
                EditLectureoldvalue.add(outcome1_4Label, 0, 162);

                Label  outcome1_5Label= new Label("Outcome 1_5:");
                EditLectureoldvalue.add(outcome1_5Label, 0, 163);

                Label  outcome2_1Label= new Label("Outcome 2_1:");
                EditLectureoldvalue.add(outcome2_1Label, 0, 164);

                Label outcome2_2Label = new Label("Outcome 2_2:");
                EditLectureoldvalue.add(outcome2_2Label, 0,  165);

                Label outcome2_3Label = new Label("Outcome 2_3:");
                EditLectureoldvalue.add(outcome2_3Label, 0, 166);

                Label  outcome2_4Label= new Label("Outcome 2_4:");
                EditLectureoldvalue.add(outcome2_4Label, 0, 167);

                Label  outcome2_5Label= new Label("Outcome 2_5:");
                EditLectureoldvalue.add(outcome2_5Label, 0, 168);

                Label  outcome3_1Label= new Label("Outcome 3_1:");
                EditLectureoldvalue.add(outcome3_1Label, 0, 169);

                Label outcome3_2Label = new Label("Outcome 3_2:");
                EditLectureoldvalue.add(outcome3_2Label, 0,  170);

                Label outcome3_3Label = new Label("Outcome 3_3:");
                EditLectureoldvalue.add(outcome3_3Label, 0, 171);

                Label  outcome3_4Label= new Label("Outcome 3_4:");
                EditLectureoldvalue.add(outcome3_4Label, 0, 172);

                Label  outcome3_5Label= new Label("Outcome 3_5:");
                EditLectureoldvalue.add(outcome3_5Label, 0, 173);

                Label  outcome4_1Label= new Label("Outcome 4_1:");
                EditLectureoldvalue.add(outcome4_1Label, 0, 174);

                Label outcome4_2Label = new Label("Outcome 4_2:");
                EditLectureoldvalue.add(outcome4_2Label, 0,  175);

                Label outcome4_3Label = new Label("Outcome 4_3:");
                EditLectureoldvalue.add(outcome4_3Label, 0, 176);

                Label  outcome4_4Label= new Label("Outcome 4_4:");
                EditLectureoldvalue.add(outcome4_4Label, 0, 177);

                Label  outcome4_5Label= new Label("Outcome 4_5:");
                EditLectureoldvalue.add(outcome4_5Label, 0, 178);

                Label  outcome5_1Label= new Label("Outcome 5_1:");
                EditLectureoldvalue.add(outcome5_1Label, 0, 179);

                Label outcome5_2Label = new Label("Outcome 5_2:");
                EditLectureoldvalue.add(outcome5_2Label, 0,  180);

                Label outcome5_3Label = new Label("Outcome 5_3:");
                EditLectureoldvalue.add(outcome5_3Label, 0, 181);

                Label  outcome5_4Label= new Label("Outcome 5_4:");
                EditLectureoldvalue.add(outcome5_4Label, 0, 182);

                Label  outcome5_5Label= new Label("Outcome 5_5:");
                EditLectureoldvalue.add(outcome5_5Label, 0, 183);

                Label  outcome6_1Label= new Label("Outcome 6_1:");
                EditLectureoldvalue.add(outcome6_1Label, 0, 184);

                Label outcome6_2Label = new Label("Outcome 6_2:");
                EditLectureoldvalue.add(outcome6_2Label, 0,  185);

                Label outcome6_3Label = new Label("Outcome 6_3:");
                EditLectureoldvalue.add(outcome6_3Label, 0, 186);

                Label  outcome6_4Label= new Label("Outcome 6_4:");
                EditLectureoldvalue.add(outcome6_4Label, 0, 187);

                Label  outcome6_5Label= new Label("Outcome 6_5:");
                EditLectureoldvalue.add(outcome6_5Label, 0, 188);

                Label  outcome7_1Label= new Label("Outcome 7_1:");
                EditLectureoldvalue.add(outcome7_1Label, 0, 189);

                Label outcome7_2Label = new Label("Outcome 7_2:");
                EditLectureoldvalue.add(outcome7_2Label, 0,  190);

                Label outcome7_3Label = new Label("Outcome 7_3:");
                EditLectureoldvalue.add(outcome7_3Label, 0, 191);

                Label  outcome7_4Label= new Label("Outcome 7_4:");
                EditLectureoldvalue.add(outcome7_4Label, 0, 192);

                Label  outcome7_5Label= new Label("Outcome 7_5:");
                EditLectureoldvalue.add(outcome7_5Label, 0, 193);

                Label  outcome8_1Label= new Label("Outcome 8_1:");
                EditLectureoldvalue.add(outcome8_1Label, 0, 194);

                Label outcome8_2Label = new Label("Outcome 8_2:");
                EditLectureoldvalue.add(outcome8_2Label, 0,  195);

                Label outcome8_3Label = new Label("Outcome 8_3:");
                EditLectureoldvalue.add(outcome8_3Label, 0, 196);

                Label  outcome8_4Label= new Label("Outcome 8_4:");
                EditLectureoldvalue.add(outcome8_4Label, 0, 197);

                Label  outcome8_5Label= new Label("Outcome 8_5:");
                EditLectureoldvalue.add(outcome8_5Label, 0, 198);

                Label  outcome9_1Label= new Label("Outcome 9_1:");
                EditLectureoldvalue.add(outcome9_1Label, 0, 199);

                Label outcome9_2Label = new Label("Outcome 9_2:");
                EditLectureoldvalue.add(outcome9_2Label, 0,  200);

                Label outcome9_3Label = new Label("Outcome 9_3:");
                EditLectureoldvalue.add(outcome9_3Label, 0, 201);

                Label  outcome9_4Label= new Label("Outcome 9_4:");
                EditLectureoldvalue.add(outcome9_4Label, 0, 202);

                Label  outcome9_5Label= new Label("Outcome 9_5:");
                EditLectureoldvalue.add(outcome9_5Label, 0, 203);

                Label  outcome10_1Label= new Label("Outcome 10_1:");
                EditLectureoldvalue.add(outcome10_1Label, 0, 204);

                Label outcome10_2Label = new Label("Outcome 10_2:");
                EditLectureoldvalue.add(outcome10_2Label, 0,  205);

                Label outcome10_3Label = new Label("Outcome 10_3:");
                EditLectureoldvalue.add(outcome10_3Label, 0, 206);

                Label  outcome10_4Label= new Label("Outcome 10_4:");
                EditLectureoldvalue.add(outcome10_4Label, 0, 207);

                Label  outcome10_5Label= new Label("Outcome 10_5:");
                EditLectureoldvalue.add(outcome10_5Label, 0, 208);

                Label  outcome11_1Label= new Label("Outcome 11_1:");
                EditLectureoldvalue.add(outcome11_1Label, 0, 209);

                Label outcome11_2Label = new Label("Outcome 11_2:");
                EditLectureoldvalue.add(outcome11_2Label, 0,  210);

                Label outcome11_3Label = new Label("Outcome 11_3:");
                EditLectureoldvalue.add(outcome11_3Label, 0, 211);

                Label  outcome11_4Label= new Label("Outcome 11_4:");
                EditLectureoldvalue.add(outcome11_4Label, 0, 212);

                Label  outcome11_5Label= new Label("Outcome 11_5:");
                EditLectureoldvalue.add(outcome11_5Label, 0, 213);

                Label  outcome12_1Label= new Label("Outcome 12_1:");
                EditLectureoldvalue.add(outcome12_1Label, 0, 214);

                Label outcome12_2Label = new Label("Outcome 12_2:");
                EditLectureoldvalue.add(outcome12_2Label, 0,  215);

                Label outcome12_3Label = new Label("Outcome 12_3:");
                EditLectureoldvalue.add(outcome12_3Label, 0, 216);

                Label  outcome12_4Label= new Label("Outcome 12_4:");
                EditLectureoldvalue.add(outcome12_4Label, 0, 217);

                Label  outcome12_5Label= new Label("Outcome 12_5:");
                EditLectureoldvalue.add(outcome12_5Label, 0, 218);

                Label  outcome13_1Label= new Label("Outcome 13_1:");
                EditLectureoldvalue.add(outcome13_1Label, 0, 219);

                Label outcome13_2Label = new Label("Outcome 13_2:");
                EditLectureoldvalue.add(outcome13_2Label, 0,  220);

                Label outcome13_3Label = new Label("Outcome 13_3:");
                EditLectureoldvalue.add(outcome13_3Label, 0, 221);

                Label  outcome13_4Label= new Label("Outcome 13_4:");
                EditLectureoldvalue.add(outcome13_4Label, 0, 222);

                Label  outcome13_5Label= new Label("Outcome 13_5:");
                EditLectureoldvalue.add(outcome13_5Label, 0, 223);

                /*---------------------------------------------------------*/
                Label LectureIDtext = new Label(TempID);
                EditLectureoldvalue.add(LectureIDtext, 1, 0);

                Label LectureNameText = new Label(TempLectureName);
                EditLectureoldvalue.add(LectureNameText, 1, 1);

                Label LecturersNameText = new Label(TempLecturerName);
                EditLectureoldvalue.add(LecturersNameText, 1, 2);

                Label lectureCodeLabelText = new Label(TempLCode);
                EditLectureoldvalue.add(lectureCodeLabelText, 1, 3);

                Label semesterLabelText = new Label(TempSemester);
                EditLectureoldvalue.add(semesterLabelText, 1, 4);

                Label theoryHourLabelText = new Label(TempTHour);
                EditLectureoldvalue.add(theoryHourLabelText, 1, 5);

                Label labHourLabelText = new Label(TempLHour);
                EditLectureoldvalue.add(labHourLabelText, 1, 6);

                Label localCreditLabelText = new Label(TempLCredit);
                EditLectureoldvalue.add(localCreditLabelText, 1, 7);

                Label ECTSLabelText= new Label(TempEcts);
                EditLectureoldvalue.add(ECTSLabelText, 1, 8);

                Label prerequisitesLabelText = new Label(TempPrereq);
                EditLectureoldvalue.add(prerequisitesLabelText, 1, 9);

                Label course_LanguageLabelText= new Label(TempLLang);
                EditLectureoldvalue.add(course_LanguageLabelText, 1, 10);

                Label course_TypeLabelText = new Label(TempLType);
                EditLectureoldvalue.add(course_TypeLabelText, 1, 11);

                Label course_LevelLabelText = new Label(TempLLevel);
                EditLectureoldvalue.add(course_LevelLabelText, 1, 12);

                Label teaching_MethodsLabelText = new Label(TempTMethod);
                EditLectureoldvalue.add(teaching_MethodsLabelText, 1, 13);

                Label course_CoordinatorLabelText = new Label(TempLCoordinator);
                EditLectureoldvalue.add(course_CoordinatorLabelText, 1, 14);

                Label assistantLabelText = new Label(TempAssistant);
                EditLectureoldvalue.add(assistantLabelText, 1, 15);

                Label course_ObjectivesLabelText = new Label(TempLObjective);
                EditLectureoldvalue.add(course_ObjectivesLabelText, 1, 16);

                Label learning_OutcomesLabelText = new Label(TempLOutcome);
                EditLectureoldvalue.add(learning_OutcomesLabelText, 1, 17);

                Label course_DescriptionLabelText = new Label(TempLDescription);
                EditLectureoldvalue.add(course_DescriptionLabelText, 1,18);

                Label course_CategoryLabelText = new Label(TempLCategory);
                EditLectureoldvalue.add(course_CategoryLabelText, 1, 19);

                Label week1SubjectsLabelText = new Label(TempW1sub);
                EditLectureoldvalue.add(week1SubjectsLabelText, 1, 20);

                Label week2SubjectsLabelText = new Label(TempW2sub);
                EditLectureoldvalue.add(week2SubjectsLabelText, 1, 21);

                Label week3SubjectsLabelText = new Label(TempW3sub);
                EditLectureoldvalue.add(week3SubjectsLabelText, 1, 22);

                Label week4SubjectsLabelText = new Label(TempW4sub);
                EditLectureoldvalue.add(week4SubjectsLabelText, 1, 23);

                Label week5SubjectsLabelText = new Label(TempW5sub);
                EditLectureoldvalue.add(week5SubjectsLabelText, 1, 24);

                Label week6SubjectsLabelText = new Label(TempW6sub);
                EditLectureoldvalue.add(week6SubjectsLabelText, 1, 25);

                Label week7SubjectsLabelText = new Label(TempW7sub);
                EditLectureoldvalue.add(week7SubjectsLabelText, 1, 26);

                Label week8SubjectsLabelText = new Label(TempW8sub);
                EditLectureoldvalue.add(week8SubjectsLabelText, 1, 27);

                Label week9SubjectsLabelText = new Label(TempW9sub);
                EditLectureoldvalue.add(week9SubjectsLabelText, 1, 28);

                Label week10SubjectsLabelText = new Label(TempW10sub);
                EditLectureoldvalue.add(week10SubjectsLabelText, 1, 29);

                Label week11SubjectsLabelText = new Label(TempW11sub);
                EditLectureoldvalue.add(week11SubjectsLabelText, 1, 30);

                Label week12SubjectsLabelText = new Label(TempW12sub);
                EditLectureoldvalue.add(week12SubjectsLabelText, 1, 31);

                Label week13SubjectsLabelText = new Label(TempW13sub);
                EditLectureoldvalue.add(week13SubjectsLabelText, 1, 32);

                Label week14SubjectsLabelText = new Label(TempW14sub);
                EditLectureoldvalue.add(week14SubjectsLabelText, 1, 33);

                Label week15SubjectsLabelText = new Label(TempW15sub);
                EditLectureoldvalue.add(week15SubjectsLabelText, 1, 34);

                Label week1ReqMatLabelText = new Label(TempW1req);
                EditLectureoldvalue.add(week1ReqMatLabelText, 1, 36);

                Label week2ReqMatLabelText = new Label(TempW2req);
                EditLectureoldvalue.add(week2ReqMatLabelText, 1, 37);

                Label week3ReqMatLabelText = new Label(TempW3req);
                EditLectureoldvalue.add(week3ReqMatLabelText, 1, 38);

                Label week4ReqMatLabelText = new Label(TempW4req);
                EditLectureoldvalue.add(week4ReqMatLabelText, 1, 39);

                Label week5ReqMatLabelText = new Label(TempW5req);
                EditLectureoldvalue.add(week5ReqMatLabelText, 1, 40);

                Label week6ReqMatLabelText = new Label(TempW6req);
                EditLectureoldvalue.add(week6ReqMatLabelText, 1, 41);

                Label week7ReqMatLabelText = new Label(TempW7req);
                EditLectureoldvalue.add(week7ReqMatLabelText, 1, 42);

                Label week8ReqMatLabelText = new Label(TempW8req);
                EditLectureoldvalue.add(week8ReqMatLabelText, 1, 43);

                Label week9ReqMatLabelText = new Label(TempW9req);
                EditLectureoldvalue.add(week9ReqMatLabelText, 1, 44);

                Label week10ReqMatLabelText = new Label(TempW10req);
                EditLectureoldvalue.add(week10ReqMatLabelText, 1, 45);

                Label week11ReqMatLabelText = new Label(TempW11req);
                EditLectureoldvalue.add(week11ReqMatLabelText, 1, 46);

                Label week12ReqMatLabelText = new Label(TempW12req);
                EditLectureoldvalue.add(week12ReqMatLabelText, 1, 47);

                Label week13ReqMatLabelText = new Label(TempW13req);
                EditLectureoldvalue.add(week13ReqMatLabelText, 1, 48);

                Label week14ReqMatLabelText = new Label(TempW14req);
                EditLectureoldvalue.add(week14ReqMatLabelText, 1, 49);

                Label week15ReqMatLabelText= new Label(TempW15req);
                EditLectureoldvalue.add(week15ReqMatLabelText, 1, 50);

                Label courseNotesAndTextBooksLabelText = new Label(TempCourseNotes);
                EditLectureoldvalue.add(courseNotesAndTextBooksLabelText, 1, 51);

                Label suggestedReadingsAndMaterialsLabelText = new Label(TempSuggested);
                EditLectureoldvalue.add(suggestedReadingsAndMaterialsLabelText, 1, 52);

                Label participationNumberLabelText = new Label(TempPartNumb);
                EditLectureoldvalue.add(participationNumberLabelText, 1, 53);

                Label  participationWeiLabelText= new Label(TempPartWei);
                EditLectureoldvalue.add(participationWeiLabelText, 1, 54);

                Label  participation01LabelText= new Label(TempPartL01);
                EditLectureoldvalue.add(participation01LabelText, 1, 55);

                Label participation02LabelText = new Label(TempPartL02);
                EditLectureoldvalue.add(participation02LabelText, 1, 56);

                Label participation03LabelText = new Label(TempPartL03);
                EditLectureoldvalue.add(participation03LabelText, 1, 57);

                Label  participation04LabelText= new Label(TempPartL04);
                EditLectureoldvalue.add(participation04LabelText, 1, 58);

                Label labOrApplicationNumberLabelText = new Label(TempLabNumb);
                EditLectureoldvalue.add(labOrApplicationNumberLabelText, 1, 59);

                Label  labOrApplicationWeiLabelText= new Label(TempLabWei);
                EditLectureoldvalue.add(labOrApplicationWeiLabelText, 1, 60);

                Label labOrApplication01LabelText = new Label(TempLabL01);
                EditLectureoldvalue.add(labOrApplication01LabelText, 1, 61);

                Label labOrApplication02LabelText = new Label(TempLabL02);
                EditLectureoldvalue.add(labOrApplication02LabelText, 1, 62);

                Label labOrApplication03LabelText = new Label(TempLabL03);
                EditLectureoldvalue.add(labOrApplication03LabelText, 1, 63);

                Label labOrApplication04LabelText = new Label(TempLabL04);
                EditLectureoldvalue.add(labOrApplication04LabelText, 1, 64);

                Label fieldWorkNumberLabelText = new Label(TempFWNumb);
                EditLectureoldvalue.add(fieldWorkNumberLabelText, 1, 65);

                Label FieldWorkWeiLabelText = new Label(TempFWWei);
                EditLectureoldvalue.add(FieldWorkWeiLabelText, 1, 66);

                Label FieldWork01LabelText = new Label(TempFWL01);
                EditLectureoldvalue.add(FieldWork01LabelText, 1, 67);

                Label FieldWork02LabelText = new Label(TempFWL02);
                EditLectureoldvalue.add(FieldWork02LabelText, 1, 68);

                Label FieldWork03LabelText = new Label(TempFWL03);
                EditLectureoldvalue.add(FieldWork03LabelText, 1, 69);

                Label FieldWork04LabelText = new Label(TempFWL04);
                EditLectureoldvalue.add(FieldWork04LabelText, 1, 70);

                Label quizOrStudioCritiqueNumberLabelText = new Label(TempQuizNumb);
                EditLectureoldvalue.add(quizOrStudioCritiqueNumberLabelText, 1, 71);

                Label quizOrStudioCritiqueWeiLabelText = new Label(TempQuizWei);
                EditLectureoldvalue.add(quizOrStudioCritiqueWeiLabelText, 1,72);

                Label quizOrStudioCritique01LabelText = new Label(TempQuizL01);
                EditLectureoldvalue.add(quizOrStudioCritique01LabelText, 1, 73);

                Label quizOrStudioCritique02LabelText = new Label(TempQuizL02);
                EditLectureoldvalue.add(quizOrStudioCritique02LabelText, 1, 74);

                Label quizOrStudioCritique03LabelText = new Label(TempQuizL03);
                EditLectureoldvalue.add(quizOrStudioCritique03LabelText, 1, 75);

                Label quizOrStudioCritique04LabelText = new Label(TempQuizL04);
                EditLectureoldvalue.add(quizOrStudioCritique04LabelText, 1, 76);

                Label homeworkOrAssignmentNumberLabelText = new Label(TempHWNumb);
                EditLectureoldvalue.add(homeworkOrAssignmentNumberLabelText, 1, 77);

                Label homeworkOrAssignmentWeiLabelText = new Label(TempHWWei);
                EditLectureoldvalue.add(homeworkOrAssignmentWeiLabelText, 1, 78);

                Label homeworkOrAssignment01LabelText = new Label(TempHWL01);
                EditLectureoldvalue.add(homeworkOrAssignment01LabelText, 1, 79);

                Label homeworkOrAssignment02LabelText = new Label(TempHWL02);
                EditLectureoldvalue.add(homeworkOrAssignment02LabelText, 1, 80);

                Label homeworkOrAssignment03LabelText = new Label(TempHWL03);
                EditLectureoldvalue.add(homeworkOrAssignment03LabelText, 1, 81);

                Label homeworkOrAssignment04LabelText = new Label(TempHWL04);
                EditLectureoldvalue.add(homeworkOrAssignment04LabelText, 1, 82);

                Label presentationOrJuryNumberLabelText = new Label(TempPresNumb);
                EditLectureoldvalue.add(presentationOrJuryNumberLabelText, 1, 83);

                Label presentationOrJuryWeiLabelText = new Label(TempPresWei);
                EditLectureoldvalue.add(presentationOrJuryWeiLabelText, 1, 84);

                Label presentationOrJury01LabelText = new Label(TempPresL01);
                EditLectureoldvalue.add(presentationOrJury01LabelText, 1, 85);

                Label presentationOrJury02LabelText = new Label(TempPresL02);
                EditLectureoldvalue.add(presentationOrJury02LabelText, 1, 86);

                Label presentationOrJury03LabelText = new Label(TempPresL03);
                EditLectureoldvalue.add(presentationOrJury03LabelText, 1, 87);

                Label presentationOrJury04LabelText = new Label(TempPresL04);
                EditLectureoldvalue.add(presentationOrJury04LabelText, 1, 88);

                Label projectNumberLabelText = new Label(TempProjNumb);
                EditLectureoldvalue.add(projectNumberLabelText, 1, 89);

                Label projectWeiLabelText = new Label(TempProjWei);
                EditLectureoldvalue.add(projectWeiLabelText, 1, 90);

                Label project01LabelText= new Label(TempProjL01);
                EditLectureoldvalue.add(project01LabelText, 1, 91);

                Label project02LabelText= new Label(TempProjL02);
                EditLectureoldvalue.add(project02LabelText, 1, 92);

                Label project03LabelText = new Label(TempProjL03);
                EditLectureoldvalue.add(project03LabelText, 1, 93);

                Label project04LabelText = new Label(TempProjL04);
                EditLectureoldvalue.add(project04LabelText, 1, 94);

                Label portfolioNumberLabelText = new Label(TempPortNumb);
                EditLectureoldvalue.add(portfolioNumberLabelText, 1, 95);

                Label portfolioWeiLabelText = new Label(TempPortWei);
                EditLectureoldvalue.add(portfolioWeiLabelText, 1, 96);

                Label portfolio01LabelText = new Label(TempPortL01);
                EditLectureoldvalue.add(portfolio01LabelText, 1, 97);

                Label portfolio02LabelText = new Label(TempPortL02);
                EditLectureoldvalue.add(portfolio02LabelText, 1, 98);

                Label portfolio03LabelText = new Label(TempPortL03);
                EditLectureoldvalue.add(portfolio03LabelText, 1, 99);

                Label portfolio04LabelText = new Label(TempPortL04);
                EditLectureoldvalue.add(portfolio04LabelText, 1, 100);

                Label seminarNumberLabelText = new Label(TempSEMNumb);
                EditLectureoldvalue.add(seminarNumberLabelText, 1, 101);

                Label seminarWeiLabelText = new Label(TempSEMWei);
                EditLectureoldvalue.add(seminarWeiLabelText, 1, 102);

                Label seminar01LabelText = new Label(TempSEML01);
                EditLectureoldvalue.add(seminar01LabelText, 1, 103);

                Label seminar02LabelText = new Label(TempSEML02);
                EditLectureoldvalue.add(seminar02LabelText, 1, 104);

                Label seminar03LabelText = new Label(TempSEML03);
                EditLectureoldvalue.add(seminar03LabelText, 1, 105);

                Label seminar04LabelText = new Label(TempSEML04);
                EditLectureoldvalue.add(seminar04LabelText, 1, 106);

                Label  oralNumberLabelText = new Label(TempORALNumb);
                EditLectureoldvalue.add(oralNumberLabelText, 1, 107);

                Label oralWeiLabelText = new Label(TempORALWei);
                EditLectureoldvalue.add(oralWeiLabelText, 1, 108);

                Label oral01LabelText = new Label(TempORALL01);
                EditLectureoldvalue.add(oral01LabelText, 1, 109);

                Label oral02LabelText = new Label(TempORALL02);
                EditLectureoldvalue.add(oral02LabelText, 1, 110);

                Label oral03LabelText = new Label(TempORALL03);
                EditLectureoldvalue.add(oral03LabelText, 1, 111);

                Label oral04LabelText = new Label(TempORALL04);
                EditLectureoldvalue.add(oral04LabelText, 1, 112);

                Label midtermNumberLabelText = new Label(TempMIDNumb);
                EditLectureoldvalue.add(midtermNumberLabelText, 1, 113);

                Label midtermWeiLabelText = new Label(TempMIDWei);
                EditLectureoldvalue.add(midtermWeiLabelText, 1, 114);

                Label midterm01LabelText = new Label(TempMIDL01);
                EditLectureoldvalue.add(midterm01LabelText, 1, 115);

                Label midterm02LabelText = new Label(TempMIDL02);
                EditLectureoldvalue.add(midterm02LabelText, 1, 116);

                Label midterm03LabelText = new Label(TempMIDL03);
                EditLectureoldvalue.add(midterm03LabelText, 1, 117);

                Label midterm04LabelText = new Label(TempMIDL04);
                EditLectureoldvalue.add(midterm04LabelText, 1, 118);

                Label finalNumberLabelText= new Label(TempFINALNumb);
                EditLectureoldvalue.add(finalNumberLabelText, 1, 119);

                Label finalWeiLabelText = new Label(TempFINALWei);
                EditLectureoldvalue.add(finalWeiLabelText, 1, 120);

                Label final01LabelText = new Label(TempFINALL01);
                EditLectureoldvalue.add(final01LabelText, 1, 121);

                Label final02LabelText = new Label(TempFINALL02);
                EditLectureoldvalue.add(final02LabelText, 1, 123);

                Label final03LabelText = new Label(TempFINALL03);
                EditLectureoldvalue.add(final03LabelText, 1, 124);

                Label final04LabelText = new Label(TempFINALL04);
                EditLectureoldvalue.add(final04LabelText, 1, 125);

                Label StudyHoursoutofClassNumLabelText = new Label(TempSHOOCNum);
                EditLectureoldvalue.add(StudyHoursoutofClassNumLabelText, 1, 126);

                Label StudyHoursoutofClassDurLabelText = new Label(TempSHOOCDur);
                EditLectureoldvalue.add(StudyHoursoutofClassDurLabelText, 1, 126);

                Label StudyHoursoutofClassWorkLabelText = new Label(TempSHOOCWork);
                EditLectureoldvalue.add(StudyHoursoutofClassWorkLabelText, 1, 127);

                Label FieldWorkNumLabelText = new Label(TempFWNum);
                EditLectureoldvalue.add(FieldWorkNumLabelText, 1, 128);

                Label FieldWorkDurLabelText = new Label(TempFWDur);
                EditLectureoldvalue.add(FieldWorkDurLabelText, 1, 129);

                Label FieldWorkWorkLabelText = new Label(TempFWWork);
                EditLectureoldvalue.add(FieldWorkWorkLabelText, 1, 130);

                Label QuizNumLabelText = new Label(TempQuizNum);
                EditLectureoldvalue.add(QuizNumLabelText, 1, 131);

                Label QuizDurLabelText = new Label(TempQuizDur);
                EditLectureoldvalue.add(QuizDurLabelText, 1, 132);

                Label QuizWorkLabelText = new Label(TempQuizWork);
                EditLectureoldvalue.add(QuizWorkLabelText, 1, 133);

                Label HWNumLabelText = new Label(TempHWNum);
                EditLectureoldvalue.add(HWNumLabelText, 1, 134);

                Label HWDurLabelText = new Label(TempHWDur);
                EditLectureoldvalue.add(HWDurLabelText, 1, 135);

                Label HWWorkLabelText = new Label(TempHWWork);
                EditLectureoldvalue.add(HWWorkLabelText, 1, 136);

                Label PresentationNumLabelText = new Label(TempPresentationNum);
                EditLectureoldvalue.add(PresentationNumLabelText, 1, 137);

                Label PresentationDurLabelText = new Label(TempPresentationDur);
                EditLectureoldvalue.add(PresentationDurLabelText, 1, 138);

                Label PresentationWorkLabelText = new Label(TempPresentationWork);
                EditLectureoldvalue.add(PresentationWorkLabelText, 1, 139);

                Label ProjectNumLabelText = new Label(TempProjectNum);
                EditLectureoldvalue.add(ProjectNumLabelText, 1, 140);

                Label ProjectDurLabelText = new Label(TempProjectDur);
                EditLectureoldvalue.add(ProjectDurLabelText, 1, 141);

                Label ProjectWorkLabelText = new Label(TempProjectWork);
                EditLectureoldvalue.add(ProjectWorkLabelText, 1, 142);

                Label PortfolioNumLabelText = new Label(TempPortfolioNum);
                EditLectureoldvalue.add(PortfolioNumLabelText, 1, 143);

                Label PortfolioDurLabelText = new Label(TempPortfolioDur);
                EditLectureoldvalue.add(PortfolioDurLabelText, 1, 144);

                Label PortfolioWorkLabelText = new Label(TempPortfolioWork);
                EditLectureoldvalue.add(PortfolioWorkLabelText, 1, 145);

                Label SeminarNumLabelText = new Label(TempSeminarNum);
                EditLectureoldvalue.add(SeminarNumLabelText, 1,146);

                Label SeminarDurLabelText = new Label(TempSeminarDur);
                EditLectureoldvalue.add(SeminarDurLabelText, 1,147);

                Label SeminarWorkLabelText = new Label(TempSeminarWork);
                EditLectureoldvalue.add(SeminarWorkLabelText, 1,148);

                Label OralExamNumLabelText = new Label(TempOralNum);
                EditLectureoldvalue.add(OralExamNumLabelText, 1, 149);

                Label OralExamDurLabelText = new Label(TempOralDur);
                EditLectureoldvalue.add(OralExamDurLabelText, 1, 150);

                Label OralExamWorkLabelText = new Label(TempOralWork);
                EditLectureoldvalue.add(OralExamWorkLabelText, 1, 151);

                Label MidtermNumLabelText = new Label(TempMidtermNum);
                EditLectureoldvalue.add(MidtermNumLabelText, 1, 152);

                Label MidtermDurLabelText = new Label(TempMidtermDur);
                EditLectureoldvalue.add(MidtermDurLabelText, 1, 153);

                Label MidtermWorkLabelText = new Label(TempMidtermWork);
                EditLectureoldvalue.add(MidtermWorkLabelText, 1, 154);

                Label FinalNumLabelText = new Label(TempFinalNum);
                EditLectureoldvalue.add(FinalNumLabelText, 1, 155);

                Label FinalDurLabelText = new Label(TempFinalDur);
                EditLectureoldvalue.add(FinalDurLabelText, 1, 156);

                Label FinalWorkLabelText = new Label(TempFinalWork);
                EditLectureoldvalue.add(FinalWorkLabelText, 1, 157);

                Label outcome1_1LabelText = new Label(Tempoutcome1_1);
                EditLectureoldvalue.add(outcome1_1LabelText, 1,  159);

                Label outcome1_2LabelText = new Label(Tempoutcome1_2);
                EditLectureoldvalue.add(outcome1_2LabelText, 1,  160);

                Label outcome1_3LabelText = new Label(Tempoutcome1_3);
                EditLectureoldvalue.add(outcome1_3LabelText, 1, 161);

                Label  outcome1_4LabelText= new Label(Tempoutcome1_4);
                EditLectureoldvalue.add(outcome1_4LabelText, 1, 162);

                Label  outcome1_5LabelText= new Label(Tempoutcome1_5);
                EditLectureoldvalue.add(outcome1_5LabelText, 1, 163);

                Label  outcome2_1LabelText= new Label( Tempoutcome2_1);
                EditLectureoldvalue.add(outcome2_1LabelText, 1, 164);

                Label outcome2_2LabelText = new Label(Tempoutcome2_2);
                EditLectureoldvalue.add(outcome2_2LabelText, 1,  165);

                Label outcome2_3LabelText= new Label(Tempoutcome2_3);
                EditLectureoldvalue.add(outcome2_3LabelText, 1, 166);

                Label  outcome2_4LabelText= new Label(Tempoutcome2_4);
                EditLectureoldvalue.add(outcome2_4LabelText, 1, 167);

                Label  outcome2_5LabelText= new Label(Tempoutcome2_5);
                EditLectureoldvalue.add(outcome2_5LabelText, 1, 168);

                Label  outcome3_1LabelText= new Label(Tempoutcome3_1);
                EditLectureoldvalue.add(outcome3_1LabelText, 1, 169);

                Label outcome3_2LabelText = new Label(Tempoutcome3_2);
                EditLectureoldvalue.add(outcome3_2LabelText, 1,  170);

                Label outcome3_3LabelText = new Label(Tempoutcome3_3);
                EditLectureoldvalue.add(outcome3_3LabelText, 1, 171);

                Label  outcome3_4LabelText= new Label(Tempoutcome3_4);
                EditLectureoldvalue.add(outcome3_4LabelText, 1, 172);

                Label  utcome3_5LabelText= new Label(Tempoutcome3_5);
                EditLectureoldvalue.add(utcome3_5LabelText, 1, 173);

                Label  outcome4_1LabelText= new Label(Tempoutcome4_1);
                EditLectureoldvalue.add(outcome4_1LabelText, 1, 174);

                Label outcome4_2LabelText = new Label(Tempoutcome4_2);
                EditLectureoldvalue.add(outcome4_2LabelText, 1,  175);

                Label outcome4_3LabelText= new Label(Tempoutcome4_3);
                EditLectureoldvalue.add(outcome4_3LabelText, 1, 176);

                Label  outcome4_4LabelText= new Label(Tempoutcome4_4);
                EditLectureoldvalue.add(outcome4_4LabelText, 1, 177);

                Label  outcome4_5LabelText= new Label(Tempoutcome4_5);
                EditLectureoldvalue.add(outcome4_5LabelText, 1, 178);

                Label  outcome5_1LabelText= new Label(Tempoutcome5_1);
                EditLectureoldvalue.add(outcome5_1LabelText, 1, 179);

                Label outcome5_2LabelText = new Label(Tempoutcome5_2);
                EditLectureoldvalue.add(outcome5_2LabelText, 1,  180);

                Label outcome5_3LabelText= new Label(Tempoutcome5_3);
                EditLectureoldvalue.add(outcome5_3LabelText, 1, 181);

                Label  outcome5_4LabelText= new Label(Tempoutcome5_4);
                EditLectureoldvalue.add(outcome5_4LabelText, 1, 182);

                Label  outcome5_5LabelText= new Label(Tempoutcome5_5);
                EditLectureoldvalue.add(outcome5_5LabelText, 1, 183);

                Label  outcome6_1LabelText= new Label(Tempoutcome6_1);
                EditLectureoldvalue.add(outcome6_1LabelText, 1, 184);

                Label outcome6_2LabelText = new Label(Tempoutcome6_2);
                EditLectureoldvalue.add(outcome6_2LabelText, 1,  185);

                Label utcome6_3LabelText = new Label(Tempoutcome6_3);
                EditLectureoldvalue.add(utcome6_3LabelText, 1, 186);

                Label  outcome6_4LabelText= new Label(Tempoutcome6_4);
                EditLectureoldvalue.add(outcome6_4LabelText, 1, 187);

                Label  outcome6_5LabelText= new Label(Tempoutcome6_5);
                EditLectureoldvalue.add(outcome6_5LabelText, 1, 188);

                Label  outcome7_1LabelText= new Label(Tempoutcome7_1);
                EditLectureoldvalue.add(outcome7_1LabelText, 1, 189);

                Label outcome7_2LabelText = new Label(Tempoutcome7_2);
                EditLectureoldvalue.add(outcome7_2LabelText, 1,  190);

                Label outcome7_3LabelText = new Label(Tempoutcome7_3);
                EditLectureoldvalue.add(outcome7_3LabelText, 1, 191);

                Label  outcome7_4LabelText= new Label(Tempoutcome7_4);
                EditLectureoldvalue.add(outcome7_4LabelText, 1, 192);

                Label  outcome7_5LabelText= new Label(Tempoutcome7_5);
                EditLectureoldvalue.add(outcome7_5LabelText, 1, 193);

                Label  outcome8_1LabelText= new Label(Tempoutcome8_1);
                EditLectureoldvalue.add(outcome8_1LabelText, 1, 194);

                Label utcome8_2LabelText= new Label(Tempoutcome8_2);
                EditLectureoldvalue.add(utcome8_2LabelText, 1,  195);

                Label outcome8_3LabelText = new Label(Tempoutcome8_3);
                EditLectureoldvalue.add(outcome8_3LabelText, 1, 196);

                Label  outcome8_4LabelText= new Label(Tempoutcome8_4);
                EditLectureoldvalue.add(outcome8_4LabelText, 1, 197);

                Label  outcome8_5LabelText= new Label(Tempoutcome8_5);
                EditLectureoldvalue.add(outcome8_5LabelText, 1, 198);

                Label  outcome9_1LabelText= new Label(Tempoutcome9_1);
                EditLectureoldvalue.add(outcome9_1LabelText, 1, 199);

                Label outcome9_2LabelText= new Label(Tempoutcome9_2);
                EditLectureoldvalue.add(outcome9_2LabelText, 1,  200);

                Label outcome9_3LabelText= new Label(Tempoutcome9_3);
                EditLectureoldvalue.add(outcome9_3LabelText, 1, 201);

                Label outcome9_4LabelText= new Label(Tempoutcome9_4);
                EditLectureoldvalue.add(outcome9_4LabelText, 1, 202);

                Label  outcome9_5LabelText= new Label(Tempoutcome9_5);
                EditLectureoldvalue.add(outcome9_5LabelText, 1, 203);

                Label  outcome10_1LabelText= new Label(Tempoutcome10_1);
                EditLectureoldvalue.add(outcome10_1LabelText, 1, 204);

                Label outcome10_2LabelText= new Label(Tempoutcome10_2);
                EditLectureoldvalue.add(outcome10_2LabelText, 1,  205);

                Label outcome10_3LabelText= new Label(Tempoutcome10_3);
                EditLectureoldvalue.add(outcome10_3LabelText, 1, 206);

                Label  outcome10_4LabelText= new Label(Tempoutcome10_4);
                EditLectureoldvalue.add(outcome10_4LabelText, 1, 207);

                Label  outcome10_5LabelText= new Label(Tempoutcome10_5);
                EditLectureoldvalue.add(outcome10_5LabelText, 1, 208);

                Label  outcome11_1LabelText= new Label(Tempoutcome11_1);
                EditLectureoldvalue.add(outcome11_1LabelText, 1, 209);

                Label outcome11_2LabelText = new Label(Tempoutcome11_2);
                EditLectureoldvalue.add(outcome11_2LabelText, 1,  210);

                Label outcome11_3LabelText = new Label(Tempoutcome11_3);
                EditLectureoldvalue.add(outcome11_3LabelText, 1, 211);

                Label  outcome11_4LabelText= new Label(Tempoutcome11_4);
                EditLectureoldvalue.add(outcome11_4LabelText, 1, 212);

                Label  outcome11_5LabelText= new Label(Tempoutcome11_5);
                EditLectureoldvalue.add(outcome11_5LabelText, 1, 213);

                Label  outcome12_1LabelText= new Label(Tempoutcome12_1);
                EditLectureoldvalue.add(outcome12_1LabelText, 1, 214);

                Label outcome12_2LabelText = new Label(Tempoutcome12_2);
                EditLectureoldvalue.add(outcome12_2LabelText, 1,  215);

                Label outcome12_3LabelText = new Label(Tempoutcome12_3);
                EditLectureoldvalue.add(outcome12_3LabelText, 1, 216);

                Label  outcome12_4LabelText= new Label(Tempoutcome12_4);
                EditLectureoldvalue.add(outcome12_4LabelText, 1, 217);

                Label  outcome12_5LabelText= new Label(Tempoutcome12_5);
                EditLectureoldvalue.add(outcome12_5LabelText, 1, 218);

                Label  outcome13_1LabelText= new Label(Tempoutcome13_1);
                EditLectureoldvalue.add(outcome13_1LabelText, 1, 219);

                Label outcome13_2LabelText = new Label(Tempoutcome13_2);
                EditLectureoldvalue.add(outcome13_2LabelText, 1,  220);

                Label outcome13_3LabelText = new Label(Tempoutcome13_3);
                EditLectureoldvalue.add(outcome13_3LabelText, 1, 221);

                Label  outcome13_4LabelText= new Label(Tempoutcome13_4);
                EditLectureoldvalue.add(outcome13_4LabelText, 1, 222);

                Label  outcome13_5LabelText= new Label(Tempoutcome13_5);
                EditLectureoldvalue.add(outcome13_5LabelText, 1, 223);

                Label NEWLectureID = new Label("Lecture ID :");
                EditLecturenewvalue.add(NEWLectureID, 0, 0);

                Label NEWlectureNameLabel = new Label("Lecture Name:");
                EditLecturenewvalue.add(NEWlectureNameLabel, 0, 1);

                Label NEWlecturerNameLabel = new Label("Lecturer's Name:");
                EditLecturenewvalue.add(NEWlecturerNameLabel, 0, 2);

                Label NEWlectureCodeLabel = new Label("Lecture Code:");
                EditLecturenewvalue.add(NEWlectureCodeLabel, 0, 3);

                Label NEWsemesterLabel = new Label("Semester:");
                EditLecturenewvalue.add(NEWsemesterLabel, 0, 4);

                Label NEWtheoryHourLabel = new Label("Theory Hour:");
                EditLecturenewvalue.add(NEWtheoryHourLabel, 0, 5);

                Label NEWlabHourLabel = new Label("Lab Hour:");
                EditLectureoldvalue.add(NEWlabHourLabel, 0, 6);

                Label NEWlocalCreditLabel = new Label("Local Credit:");
                EditLecturenewvalue.add(NEWlocalCreditLabel, 0, 7);

                Label NEWECTSLabel = new Label("Ects:");
                EditLecturenewvalue.add(NEWECTSLabel, 0, 8);

                Label NEWprerequisitesLabel = new Label("Prerequisites:");
                EditLecturenewvalue.add(NEWprerequisitesLabel, 0, 9);

                Label NEWcourse_LanguageLabel = new Label("Lecture Language:");
                EditLecturenewvalue.add(NEWcourse_LanguageLabel, 0, 10);

                Label NEWcourse_TypeLabel = new Label("Lecture Type:");
                EditLecturenewvalue.add(NEWcourse_TypeLabel, 0, 11);

                Label NEWcourse_LevelLabel = new Label("Lecture Level:");
                EditLecturenewvalue.add(NEWcourse_LevelLabel, 0, 12);

                Label NEWteaching_MethodsLabel = new Label("Teaching Method:");
                EditLecturenewvalue.add(NEWteaching_MethodsLabel, 0, 13);

                Label NEWcourse_CoordinatorLabel = new Label("Lecture Coordinator:");
                EditLecturenewvalue.add(NEWcourse_CoordinatorLabel, 0, 14);

                Label NEWassistantLabel = new Label("Lecture Assistant:");
                EditLecturenewvalue.add(NEWassistantLabel, 0, 15);

                Label NEWcourse_ObjectivesLabel = new Label("Lecture Objectives:");
                EditLecturenewvalue.add(NEWcourse_ObjectivesLabel, 0, 16);

                Label NEWlearning_OutcomesLabel = new Label("Learning Outcome:");
                EditLecturenewvalue.add(NEWlearning_OutcomesLabel, 0, 17);

                Label NEWcourse_DescriptionLabel = new Label("Lecture Description:");
                EditLecturenewvalue.add(NEWcourse_DescriptionLabel, 0,18);

                Label NEWcourse_CategoryLabel = new Label("Lecture Category:");
                EditLecturenewvalue.add(NEWcourse_CategoryLabel, 0, 19);

                Label NEWweek1SubjectsLabel = new Label("Week 1 Subjects:");
                EditLecturenewvalue.add(NEWweek1SubjectsLabel, 0, 20);

                Label NEWweek2SubjectsLabel = new Label("Week 2 Subjects:");
                EditLecturenewvalue.add(NEWweek2SubjectsLabel, 0, 21);

                Label NEWweek3SubjectsLabel = new Label("Week 3 Subjects:");
                EditLecturenewvalue.add(NEWweek3SubjectsLabel, 0, 22);

                Label NEWweek4SubjectsLabel = new Label("Week 4 Subjects:");
                EditLecturenewvalue.add(NEWweek4SubjectsLabel, 0, 23);

                Label NEWweek5SubjectsLabel = new Label("Week 5 Subjects:");
                EditLecturenewvalue.add(NEWweek5SubjectsLabel, 0, 24);

                Label NEWweek6SubjectsLabel = new Label("Week 6 Subjects:");
                EditLecturenewvalue.add(NEWweek6SubjectsLabel, 0, 25);

                Label NEWweek7SubjectsLabel = new Label("Week 7 Subjects:");
                EditLecturenewvalue.add(NEWweek7SubjectsLabel, 0, 26);

                Label NEWweek8SubjectsLabel = new Label("Week 8 Subjects:");
                EditLecturenewvalue.add(NEWweek8SubjectsLabel, 0, 27);

                Label NEWweek9SubjectsLabel = new Label("Week 9 Subjects:");
                EditLecturenewvalue.add(NEWweek9SubjectsLabel, 0, 28);

                Label NEWweek10SubjectsLabel = new Label("Week 10 Subjects:");
                EditLecturenewvalue.add(NEWweek10SubjectsLabel, 0, 29);

                Label NEWweek11SubjectsLabel = new Label("Week 11 Subjects:");
                EditLecturenewvalue.add(NEWweek11SubjectsLabel, 0, 30);

                Label NEWweek12SubjectsLabel = new Label("Week 12 Subjects:");
                EditLecturenewvalue.add(NEWweek12SubjectsLabel, 0, 31);

                Label NEWweek13SubjectsLabel = new Label("Week 13 Subjects:");
                EditLecturenewvalue.add(NEWweek13SubjectsLabel, 0, 32);

                Label NEWweek14SubjectsLabel = new Label("Week 14 Subjects:");
                EditLecturenewvalue.add(NEWweek14SubjectsLabel, 0, 33);

                Label NEWweek15SubjectsLabel = new Label("Week 15 Subjects:");
                EditLecturenewvalue.add(NEWweek15SubjectsLabel, 0, 34);

                Label NEWweek1ReqMatLabel = new Label("Week 1 Required Materials:");
                EditLecturenewvalue.add(NEWweek1ReqMatLabel, 0, 36);

                Label NEWweek2ReqMatLabel = new Label("Week 2 Required Materials:");
                EditLecturenewvalue.add(NEWweek2ReqMatLabel, 0, 37);

                Label NEWweek3ReqMatLabel = new Label("Week 3 Required Materials:");
                EditLecturenewvalue.add(NEWweek3ReqMatLabel, 0, 38);

                Label NEWweek4ReqMatLabel = new Label("Week 4 Required Materials:");
                EditLecturenewvalue.add(NEWweek4ReqMatLabel, 0, 39);

                Label NEWweek5ReqMatLabel = new Label("Week 5 Required Materials:");
                EditLecturenewvalue.add(NEWweek5ReqMatLabel, 0, 40);

                Label NEWweek6ReqMatLabel = new Label("Week 6 Required Materials:");
                EditLecturenewvalue.add(NEWweek6ReqMatLabel, 0, 41);

                Label NEWweek7ReqMatLabel = new Label("Week 7 Required Materials:");
                EditLecturenewvalue.add(NEWweek7ReqMatLabel, 0, 42);

                Label NEWweek8ReqMatLabel = new Label("Week 8 Required Materials:");
                EditLecturenewvalue.add(NEWweek8ReqMatLabel, 0, 43);

                Label NEWweek9ReqMatLabel = new Label("Week 9 Required Materials:");
                EditLecturenewvalue.add(NEWweek9ReqMatLabel, 0, 44);

                Label NEWweek10ReqMatLabel = new Label("Week 10 Required Materials:");
                EditLecturenewvalue.add(NEWweek10ReqMatLabel, 0, 45);

                Label NEWweek11ReqMatLabel = new Label("Week 11 Required Materials:");
                EditLecturenewvalue.add(NEWweek11ReqMatLabel, 0, 46);

                Label NEWweek12ReqMatLabel = new Label("Week 12 Required Materials:");
                EditLecturenewvalue.add(NEWweek12ReqMatLabel, 0, 47);

                Label NEWweek13ReqMatLabel = new Label("Week 13 Required Materials:");
                EditLecturenewvalue.add(NEWweek13ReqMatLabel, 0, 48);

                Label NEWweek14ReqMatLabel = new Label("Week 14 Required Materials:");
                EditLecturenewvalue.add(NEWweek14ReqMatLabel, 0, 49);

                Label NEWweek15ReqMatLabel = new Label("Week 15 Required Materials:");
                EditLecturenewvalue.add(NEWweek15ReqMatLabel, 0, 50);

                Label NEWcourseNotesAndTextBooksLabel = new Label("Course Notes and Textbooks:");
                EditLecturenewvalue.add(NEWcourseNotesAndTextBooksLabel, 0, 51);

                Label NEWsuggestedReadingsAndMaterialsLabel = new Label("Suggested Readings and Materials:");
                EditLecturenewvalue.add(NEWsuggestedReadingsAndMaterialsLabel, 0, 52);

                Label NEWparticipationNumberLabel = new Label("Participation Number:");
                EditLecturenewvalue.add(NEWparticipationNumberLabel, 0, 53);

                Label  NEWparticipationWeiLabel= new Label("Participation Weighting:");
                EditLecturenewvalue.add(NEWparticipationWeiLabel, 0, 54);

                Label  NEWparticipation01Label= new Label("Participation LO1:");
                EditLecturenewvalue.add(NEWparticipation01Label, 0, 55);

                Label NEWparticipation02Label = new Label("Participation LO2:");
                EditLecturenewvalue.add(NEWparticipation02Label, 0, 56);

                Label NEWparticipation03Label = new Label("Participation LO3:");
                EditLecturenewvalue.add(NEWparticipation03Label, 0, 57);

                Label  NEWparticipation04Label= new Label("Participation LO4:");
                EditLecturenewvalue.add(NEWparticipation04Label, 0, 58);

                Label NEWlabOrApplicationNumberLabel = new Label("Laboratory Number:");
                EditLecturenewvalue.add(NEWlabOrApplicationNumberLabel, 0, 59);

                Label  NEWlabOrApplicationWeiLabel= new Label("Laboratory Weighting:");
                EditLecturenewvalue.add(NEWlabOrApplicationWeiLabel, 0, 60);

                Label NEWlabOrApplication01Label = new Label("Laboratory LO1:");
                EditLecturenewvalue.add(NEWlabOrApplication01Label, 0, 61);

                Label NEWlabOrApplication02Label = new Label("Laboratory LO2:");
                EditLecturenewvalue.add(NEWlabOrApplication02Label, 0, 62);

                Label NEWlabOrApplication03Label = new Label("Laboratory LO3:");
                EditLecturenewvalue.add(NEWlabOrApplication03Label, 0, 63);

                Label NEWlabOrApplication04Label = new Label("Laboratory LO4:");
                EditLecturenewvalue.add(NEWlabOrApplication04Label, 0, 64);

                Label NEWfieldWorkNumberLabel = new Label("Field Work Number:");
                EditLecturenewvalue.add(NEWfieldWorkNumberLabel, 0, 65);

                Label NEWFieldWorkWeiLabel = new Label("Field Work Weighting");
                EditLecturenewvalue.add(NEWFieldWorkWeiLabel, 0, 66);

                Label NEWFieldWork01Label = new Label("Field Work LO1:");
                EditLecturenewvalue.add(NEWFieldWork01Label, 0, 67);

                Label NEWFieldWork02Label = new Label("Field Work LO2:");
                EditLecturenewvalue.add(NEWFieldWork02Label, 0, 68);

                Label NEWFieldWork03Label = new Label("Field Work LO3:");
                EditLecturenewvalue.add(NEWFieldWork03Label, 0, 69);

                Label NEWFieldWork04Label = new Label("Field Work LO4:");
                EditLecturenewvalue.add(NEWFieldWork04Label, 0, 70);

                Label NEWquizOrStudioCritiqueNumberLabel = new Label("Quiz Number:");
                EditLecturenewvalue.add(NEWquizOrStudioCritiqueNumberLabel, 0, 71);

                Label NEWquizOrStudioCritiqueWeiLabel = new Label("Quiz Weighting:");
                EditLecturenewvalue.add(NEWquizOrStudioCritiqueWeiLabel, 0,72);

                Label NEWquizOrStudioCritique01Label = new Label("Quiz LO1:");
                EditLecturenewvalue.add(NEWquizOrStudioCritique01Label, 0, 73);

                Label NEWquizOrStudioCritique02Label = new Label("Quiz LO2:");
                EditLecturenewvalue.add(NEWquizOrStudioCritique02Label, 0, 74);

                Label NEWquizOrStudioCritique03Label = new Label("Quiz LO3:");
                EditLecturenewvalue.add(NEWquizOrStudioCritique03Label, 0, 75);

                Label NEWquizOrStudioCritique04Label = new Label("Quiz LO4:");
                EditLecturenewvalue.add(NEWquizOrStudioCritique04Label, 0, 76);

                Label NEWhomeworkOrAssignmentNumberLabel = new Label("Homework Number:");
                EditLecturenewvalue.add(NEWhomeworkOrAssignmentNumberLabel, 0, 77);

                Label NEWhomeworkOrAssignmentWeiLabel = new Label("Homework Weighting:");
                EditLecturenewvalue.add(NEWhomeworkOrAssignmentWeiLabel, 0, 78);

                Label NEWhomeworkOrAssignment01Label = new Label("Homework LO1:");
                EditLecturenewvalue.add(NEWhomeworkOrAssignment01Label, 0, 79);

                Label NEWhomeworkOrAssignment02Label = new Label("Homework LO2:");
                EditLecturenewvalue.add(NEWhomeworkOrAssignment02Label, 0, 80);

                Label NEWhomeworkOrAssignment03Label = new Label("Homework LO3:");
                EditLecturenewvalue.add(NEWhomeworkOrAssignment03Label, 0, 81);

                Label NEWhomeworkOrAssignment04Label = new Label("Homework LO4:");
                EditLecturenewvalue.add(NEWhomeworkOrAssignment04Label, 0, 82);

                Label NEWpresentationOrJuryNumberLabel = new Label("Presentation Number:");
                EditLecturenewvalue.add(NEWpresentationOrJuryNumberLabel, 0, 83);

                Label NEWpresentationOrJuryWeiLabel = new Label("Presentation Weighting:");
                EditLecturenewvalue.add(NEWpresentationOrJuryWeiLabel, 0, 84);

                Label NEWpresentationOrJury01Label = new Label("Presentation LO1:");
                EditLecturenewvalue.add(NEWpresentationOrJury01Label, 0, 85);

                Label NEWpresentationOrJury02Label = new Label("Presentation LO2:");
                EditLecturenewvalue.add(NEWpresentationOrJury02Label, 0, 86);

                Label NEWpresentationOrJury03Label = new Label("Presentation LO3:");
                EditLecturenewvalue.add(NEWpresentationOrJury03Label, 0, 87);

                Label NEWpresentationOrJury04Label = new Label("Presentation LO4:");
                EditLecturenewvalue.add(NEWpresentationOrJury04Label, 0, 88);

                Label NEWprojectNumberLabel = new Label("Project Number:");
                EditLecturenewvalue.add(NEWprojectNumberLabel, 0, 89);

                Label NEWprojectWeiLabel = new Label("Project Weighting:");
                EditLecturenewvalue.add(NEWprojectWeiLabel, 0, 90);

                Label NEWproject01Label = new Label("Project LO1:");
                EditLecturenewvalue.add(NEWproject01Label, 0, 91);

                Label NEWproject02Label = new Label("Project LO2:");
                EditLecturenewvalue.add(NEWproject02Label, 0, 92);

                Label NEWproject03Label = new Label("Project LO3:");
                EditLecturenewvalue.add(NEWproject03Label, 0, 93);

                Label NEWproject04Label = new Label("Project LO4:");
                EditLecturenewvalue.add(NEWproject04Label, 0, 94);

                Label NEWportfolioNumberLabel = new Label("Portfolio Number:");
                EditLecturenewvalue.add(NEWportfolioNumberLabel, 0, 95);

                Label NEWportfolioWeiLabel = new Label("Portfolio Weighting:");
                EditLecturenewvalue.add(NEWportfolioWeiLabel, 0, 96);

                Label NEWportfolio01Label = new Label("Portfolio LO1:");
                EditLecturenewvalue.add(NEWportfolio01Label, 0, 97);

                Label NEWportfolio02Label = new Label("Portfolio LO2:");
                EditLecturenewvalue.add(NEWportfolio02Label, 0, 98);

                Label NEWportfolio03Label = new Label("Portfolio LO3:");
                EditLecturenewvalue.add(NEWportfolio03Label, 0, 99);

                Label NEWportfolio04Label = new Label("Portfolio LO4:");
                EditLecturenewvalue.add(NEWportfolio04Label, 0, 100);

                Label NEWseminarNumberLabel = new Label("Seminar Number:");
                EditLecturenewvalue.add(NEWseminarNumberLabel, 0, 101);

                Label NEWseminarWeiLabel = new Label("Seminar Weighting:");
                EditLecturenewvalue.add(NEWseminarWeiLabel, 0, 102);

                Label NEWseminar01Label = new Label("Seminar LO1:");
                EditLecturenewvalue.add(NEWseminar01Label, 0, 103);

                Label NEWseminar02Label = new Label("Seminar LO2:");
                EditLecturenewvalue.add(NEWseminar02Label, 0, 104);

                Label NEWseminar03Label = new Label("Seminar LO3:");
                EditLecturenewvalue.add(NEWseminar03Label, 0, 105);

                Label NEWseminar04Label = new Label("Seminar LO4:");
                EditLecturenewvalue.add(NEWseminar04Label, 0, 106);

                Label NEWoralNumberLabel = new Label("Oral Exam Number:");
                EditLecturenewvalue.add(NEWoralNumberLabel, 0, 107);

                Label NEWoralWeiLabel = new Label("Oral Exam Weighting:");
                EditLecturenewvalue.add(NEWoralWeiLabel, 0, 108);

                Label NEWoral01Label = new Label("Oral Exam LO1:");
                EditLecturenewvalue.add(NEWoral01Label, 0, 109);

                Label NEWoral02Label = new Label("Oral Exam LO2:");
                EditLecturenewvalue.add(NEWoral02Label, 0, 110);

                Label NEWoral03Label = new Label("Oral Exam LO3:");
                EditLecturenewvalue.add(NEWoral03Label, 0, 111);

                Label NEWoral04Label = new Label("Oral Exam LO4:");
                EditLecturenewvalue.add(NEWoral04Label, 0, 112);

                Label NEWmidtermNumberLabel = new Label("Midterm Number:");
                EditLecturenewvalue.add(NEWmidtermNumberLabel, 0, 113);

                Label NEWmidtermWeiLabel = new Label("Midterm Weighting:");
                EditLecturenewvalue.add(NEWmidtermWeiLabel, 0, 114);

                Label NEWmidterm01Label = new Label("Midterm LO1:");
                EditLecturenewvalue.add(NEWmidterm01Label, 0, 115);

                Label NEWmidterm02Label = new Label("Midterm LO2:");
                EditLecturenewvalue.add(NEWmidterm02Label, 0, 116);

                Label NEWmidterm03Label = new Label("Midterm LO3:");
                EditLecturenewvalue.add(NEWmidterm03Label, 0, 117);

                Label NEWmidterm04Label = new Label("Midterm LO4:");
                EditLecturenewvalue.add(NEWmidterm04Label, 0, 118);

                Label NEWfinalNumberLabel = new Label("Final Exam Number:");
                EditLecturenewvalue.add(NEWfinalNumberLabel, 0, 119);

                Label NEWfinalWeiLabel = new Label("Final Exam Weighting:");
                EditLecturenewvalue.add(NEWfinalWeiLabel, 0, 120);

                Label NEWfinal01Label = new Label("Final Exam LO1:");
                EditLecturenewvalue.add(NEWfinal01Label, 0, 121);

                Label NEWfinal02Label = new Label("Final Exam LO2:");
                EditLecturenewvalue.add(NEWfinal02Label, 0, 123);

                Label NEWfinal03Label = new Label("Final Exam LO3:");
                EditLecturenewvalue.add(NEWfinal03Label, 0, 124);

                Label NEWfinal04Label = new Label("Final Exam LO4:");
                EditLecturenewvalue.add(NEWfinal04Label, 0, 125);

                Label NEWStudyHoursoutofClassDurLabel = new Label("Study Hours out of Class Duration:");
                EditLecturenewvalue.add(NEWStudyHoursoutofClassDurLabel, 0, 126);

                Label NEWStudyHoursoutofClassWorkLabel = new Label("Study Hours out of Class Workload:");
                EditLecturenewvalue.add(NEWStudyHoursoutofClassWorkLabel, 0, 127);

                Label NEWFieldWorkNumLabel = new Label("Field Work Number");
                EditLecturenewvalue.add(NEWFieldWorkNumLabel, 0, 128);

                Label NEWFieldWorkDurLabel = new Label("Field Work Duration");
                EditLecturenewvalue.add(NEWFieldWorkDurLabel, 0, 129);

                Label NEWFieldWorkWorkLabel = new Label("Field Work Workload");
                EditLecturenewvalue.add(NEWFieldWorkWorkLabel, 0, 130);

                Label NEWQuizNumLabel = new Label("Quiz/Studio Critique Number:");
                EditLecturenewvalue.add(NEWQuizNumLabel, 0, 131);

                Label NEWQuizDurLabel = new Label("Quiz/Studio Critique Duration:");
                EditLecturenewvalue.add(NEWQuizDurLabel, 0, 132);

                Label NEWQuizWorkLabel = new Label("Quiz/Studio Critique Workload:");
                EditLecturenewvalue.add(NEWQuizWorkLabel, 0, 133);

                Label NEWHWNumLabel = new Label("Homework/Assignments Number:");
                EditLecturenewvalue.add(NEWHWNumLabel, 0, 134);

                Label NEWHWDurLabel = new Label("Homework/Assignments Duration:");
                EditLecturenewvalue.add(NEWHWDurLabel, 0, 135);

                Label NEWHWWorkLabel = new Label("Homework/Assignments Workload:");
                EditLecturenewvalue.add(NEWHWWorkLabel, 0, 136);

                Label NEWPresentationNumLabel = new Label("Presentation/Jury Number:");
                EditLecturenewvalue.add(NEWPresentationNumLabel, 0, 137);

                Label NEWPresentationDurLabel = new Label("Presentation/Jury Duration:");
                EditLecturenewvalue.add(NEWPresentationDurLabel, 0, 138);

                Label NEWPresentationWorkLabel = new Label("Presentation/Jury Workload:");
                EditLecturenewvalue.add(NEWPresentationWorkLabel, 0, 139);

                Label NEWProjectNumLabel = new Label("Project Number:");
                EditLecturenewvalue.add(NEWProjectNumLabel, 0, 140);

                Label NEWProjectDurLabel = new Label("Project Duration:");
                EditLecturenewvalue.add(NEWProjectDurLabel, 0, 141);

                Label NEWProjectWorkLabel = new Label("Project Workload:");
                EditLecturenewvalue.add(NEWProjectWorkLabel, 0, 142);

                Label NEWPortfolioNumLabel = new Label("Portfolio Number:");
                EditLecturenewvalue.add(NEWPortfolioNumLabel, 0, 143);

                Label NEWPortfolioDurLabel = new Label("Portfolio Duration:");
                EditLecturenewvalue.add(NEWPortfolioDurLabel, 0, 144);

                Label NEWPortfolioWorkLabel = new Label("Portfolio Workload:");
                EditLecturenewvalue.add(NEWPortfolioWorkLabel, 0, 145);

                Label NEWSeminarNumLabel = new Label("Seminar/Workshop Number:");
                EditLecturenewvalue.add(NEWSeminarNumLabel, 0,146);

                Label NEWSeminarDurLabel = new Label("Seminar/Workshop Duration:");
                EditLecturenewvalue.add(NEWSeminarDurLabel, 0,147);

                Label NEWSeminarWorkLabel = new Label("Seminar/Workshop Workload:");
                EditLecturenewvalue.add(NEWSeminarWorkLabel, 0,148);

                Label NEWOralExamNumLabel = new Label("Oral Exam Number:");
                EditLecturenewvalue.add(NEWOralExamNumLabel, 0, 149);

                Label NEWOralExamDurLabel = new Label("Oral Exam Duration:");
                EditLecturenewvalue.add(NEWOralExamDurLabel, 0, 150);

                Label NEWOralExamWorkLabel = new Label("Oral Exam Workload:");
                EditLecturenewvalue.add(NEWOralExamWorkLabel, 0, 151);

                Label NEWMidtermNumLabel = new Label("Midterm Number: ");
                EditLecturenewvalue.add(NEWMidtermNumLabel, 0, 152);

                Label NEWMidtermDurLabel = new Label("Midterm Duration:");
                EditLecturenewvalue.add(NEWMidtermDurLabel, 0, 153);

                Label NEWMidtermWorkLabel = new Label("Midterm Workload:");
                EditLecturenewvalue.add(NEWMidtermWorkLabel, 0, 154);

                Label NEWFinalNumLabel = new Label("Final Exam Number:");
                EditLecturenewvalue.add(NEWFinalNumLabel, 0, 155);

                Label NEWFinalDurLabel = new Label("Final Exam Duration:");
                EditLecturenewvalue.add(NEWFinalDurLabel, 0, 156);

                Label NEWFinalWorkLabel = new Label("Final Exam Workload:");
                EditLecturenewvalue.add(NEWFinalWorkLabel, 0, 157);

                Label NEWoutcome1_1Label = new Label("Outcome1_1");
                EditLecturenewvalue.add(NEWoutcome1_1Label,0,159);

                Label NEWoutcome1_2Label = new Label("Outcome 1_2:");
                EditLecturenewvalue.add(NEWoutcome1_2Label, 0,  160);

                Label NEWoutcome1_3Label = new Label("Outcome 1_3:");
                EditLecturenewvalue.add(NEWoutcome1_3Label, 0, 161);

                Label  NEWoutcome1_4Label= new Label("Outcome 1_4:");
                EditLecturenewvalue.add(NEWoutcome1_4Label, 0, 162);

                Label  NEWoutcome1_5Label= new Label("Outcome 1_5:");
                EditLecturenewvalue.add(NEWoutcome1_5Label, 0, 163);

                Label  NEWoutcome2_1Label= new Label("Outcome 2_1:");
                EditLecturenewvalue.add(NEWoutcome2_1Label, 0, 164);

                Label NEWoutcome2_2Label = new Label("Outcome 2_2:");
                EditLecturenewvalue.add(NEWoutcome2_2Label, 0,  165);

                Label NEWoutcome2_3Label = new Label("Outcome 2_3:");
                EditLecturenewvalue.add(NEWoutcome2_3Label, 0, 166);

                Label  NEWoutcome2_4Label= new Label("Outcome 2_4:");
                EditLecturenewvalue.add(NEWoutcome2_4Label, 0, 167);

                Label  NEWoutcome2_5Label= new Label("Outcome 2_5:");
                EditLecturenewvalue.add(NEWoutcome2_5Label, 0, 168);

                Label  NEWoutcome3_1Label= new Label("Outcome 3_1:");
                EditLecturenewvalue.add(NEWoutcome3_1Label, 0, 169);

                Label NEWoutcome3_2Label = new Label("Outcome 3_2:");
                EditLecturenewvalue.add(NEWoutcome3_2Label, 0,  170);

                Label NEWoutcome3_3Label = new Label("Outcome 3_3:");
                EditLecturenewvalue.add(NEWoutcome3_3Label, 0, 171);

                Label  NEWoutcome3_4Label= new Label("Outcome 3_4:");
                EditLecturenewvalue.add(NEWoutcome3_4Label, 0, 172);

                Label  NEWoutcome3_5Label= new Label("Outcome 3_5:");
                EditLecturenewvalue.add(NEWoutcome3_5Label, 0, 173);

                Label  NEWoutcome4_1Label= new Label("Outcome 4_1:");
                EditLecturenewvalue.add(NEWoutcome4_1Label, 0, 174);

                Label NEWoutcome4_2Label = new Label("Outcome 4_2:");
                EditLecturenewvalue.add(NEWoutcome4_2Label, 0,  175);

                Label NEWoutcome4_3Label = new Label("Outcome 4_3:");
                EditLecturenewvalue.add(NEWoutcome4_3Label, 0, 176);

                Label  NEWoutcome4_4Label= new Label("Outcome 4_4:");
                EditLecturenewvalue.add(NEWoutcome4_4Label, 0, 177);

                Label  NEWoutcome4_5Label= new Label("Outcome 4_5:");
                EditLecturenewvalue.add(NEWoutcome4_5Label, 0, 178);

                Label  NEWoutcome5_1Label= new Label("Outcome 5_1:");
                EditLecturenewvalue.add(NEWoutcome5_1Label, 0, 179);

                Label NEWoutcome5_2Label = new Label("Outcome 5_2:");
                EditLecturenewvalue.add(NEWoutcome5_2Label, 0,  180);

                Label NEWoutcome5_3Label = new Label("Outcome 5_3:");
                EditLecturenewvalue.add(NEWoutcome5_3Label, 0, 181);

                Label  NEWoutcome5_4Label= new Label("Outcome 5_4:");
                EditLecturenewvalue.add(NEWoutcome5_4Label, 0, 182);

                Label  NEWoutcome5_5Label= new Label("Outcome 5_5:");
                EditLecturenewvalue.add(NEWoutcome5_5Label, 0, 183);

                Label  NEWoutcome6_1Label= new Label("Outcome 6_1:");
                EditLecturenewvalue.add(NEWoutcome6_1Label, 0, 184);

                Label NEWoutcome6_2Label = new Label("Outcome 6_2:");
                EditLecturenewvalue.add(NEWoutcome6_2Label, 0,  185);

                Label NEWoutcome6_3Label = new Label("Outcome 6_3:");
                EditLecturenewvalue.add(NEWoutcome6_3Label, 0, 186);

                Label  NEWoutcome6_4Label= new Label("Outcome 6_4:");
                EditLecturenewvalue.add(NEWoutcome6_4Label, 0, 187);

                Label  NEWoutcome6_5Label= new Label("Outcome 6_5:");
                EditLecturenewvalue.add(NEWoutcome6_5Label, 0, 188);

                Label  NEWoutcome7_1Label= new Label("Outcome 7_1:");
                EditLecturenewvalue.add(NEWoutcome7_1Label, 0, 189);

                Label NEWoutcome7_2Label = new Label("Outcome 7_2:");
                EditLecturenewvalue.add(NEWoutcome7_2Label, 0,  190);

                Label NEWoutcome7_3Label = new Label("Outcome 7_3:");
                EditLecturenewvalue.add(NEWoutcome7_3Label, 0, 191);

                Label  NEWoutcome7_4Label= new Label("Outcome 7_4:");
                EditLecturenewvalue.add(NEWoutcome7_4Label, 0, 192);

                Label  NEWoutcome7_5Label= new Label("Outcome 7_5:");
                EditLecturenewvalue.add(NEWoutcome7_5Label, 0, 193);

                Label  NEWoutcome8_1Label= new Label("Outcome 8_1:");
                EditLecturenewvalue.add(NEWoutcome8_1Label, 0, 194);

                Label NEWoutcome8_2Label = new Label("Outcome 8_2:");
                EditLecturenewvalue.add(NEWoutcome8_2Label, 0,  195);

                Label NEWoutcome8_3Label = new Label("Outcome 8_3:");
                EditLecturenewvalue.add(NEWoutcome8_3Label, 0, 196);

                Label  NEWoutcome8_4Label= new Label("Outcome 8_4:");
                EditLecturenewvalue.add(NEWoutcome8_4Label, 0, 197);

                Label  NEWoutcome8_5Label= new Label("Outcome 8_5:");
                EditLecturenewvalue.add(NEWoutcome8_5Label, 0, 198);

                Label  NEWoutcome9_1Label= new Label("Outcome 9_1:");
                EditLecturenewvalue.add(NEWoutcome9_1Label, 0, 199);

                Label NEWoutcome9_2Label = new Label("Outcome 9_2:");
                EditLecturenewvalue.add(NEWoutcome9_2Label, 0,  200);

                Label NEWoutcome9_3Label = new Label("Outcome 9_3:");
                EditLecturenewvalue.add(NEWoutcome9_3Label, 0, 201);

                Label NEWoutcome9_4Label= new Label("Outcome 9_4:");
                EditLecturenewvalue.add(NEWoutcome9_4Label, 0, 202);

                Label  NEWoutcome9_5Label= new Label("Outcome 9_5:");
                EditLecturenewvalue.add(NEWoutcome9_5Label, 0, 203);

                Label  NEWoutcome10_1Label= new Label("Outcome 10_1:");
                EditLecturenewvalue.add(NEWoutcome10_1Label, 0, 204);

                Label NEWoutcome10_2Label = new Label("Outcome 10_2:");
                EditLecturenewvalue.add(NEWoutcome10_2Label, 0,  205);

                Label NEWoutcome10_3Label = new Label("Outcome 10_3:");
                EditLecturenewvalue.add(NEWoutcome10_3Label, 0, 206);

                Label  NEWoutcome10_4Label= new Label("Outcome 10_4:");
                EditLecturenewvalue.add(NEWoutcome10_4Label, 0, 207);

                Label  NEWoutcome10_5Label= new Label("Outcome 10_5:");
                EditLecturenewvalue.add(NEWoutcome10_5Label, 0, 208);

                Label  NEWoutcome11_1Label= new Label("Outcome 11_1:");
                EditLecturenewvalue.add(NEWoutcome11_1Label, 0, 209);

                Label NEWoutcome11_2Label = new Label("Outcome 11_2:");
                EditLecturenewvalue.add(NEWoutcome11_2Label, 0,  210);

                Label NEWoutcome11_3Label = new Label("Outcome 11_3:");
                EditLecturenewvalue.add(NEWoutcome11_3Label, 0, 211);

                Label  NEWoutcome11_4Label= new Label("Outcome 11_4:");
                EditLecturenewvalue.add(NEWoutcome11_4Label, 0, 212);

                Label  NEWoutcome11_5Label= new Label("Outcome 11_5:");
                EditLecturenewvalue.add(NEWoutcome11_5Label, 0, 213);

                Label  NEWoutcome12_1Label= new Label("Outcome 12_1:");
                EditLecturenewvalue.add(NEWoutcome12_1Label, 0, 214);

                Label NEWoutcome12_2Label = new Label("Outcome 12_2:");
                EditLecturenewvalue.add(NEWoutcome12_2Label, 0,  215);

                Label NEWoutcome12_3Label = new Label("Outcome 12_3:");
                EditLecturenewvalue.add(NEWoutcome12_3Label, 0, 216);

                Label  NEWoutcome12_4Label= new Label("Outcome 12_4:");
                EditLecturenewvalue.add(NEWoutcome12_4Label, 0, 217);

                Label  NEWoutcome12_5Label= new Label("Outcome 12_5:");
                EditLecturenewvalue.add(NEWoutcome12_5Label, 0, 218);

                Label  NEWoutcome13_1Label= new Label("Outcome 13_1:");
                EditLecturenewvalue.add(NEWoutcome13_1Label, 0, 219);

                Label NEWoutcome13_2Label = new Label("Outcome 13_2:");
                EditLecturenewvalue.add(NEWoutcome13_2Label, 0,  220);

                Label NEWoutcome13_3Label = new Label("Outcome 13_3:");
                EditLecturenewvalue.add(NEWoutcome13_3Label, 0, 221);

                Label  NEWoutcome13_4Label= new Label("Outcome 13_4:");
                EditLecturenewvalue.add(NEWoutcome13_4Label, 0, 222);

                Label  NEWoutcome13_5Label= new Label("Outcome 13_5:");
                EditLecturenewvalue.add(NEWoutcome13_5Label, 0, 223);


                //--------------------------------------------------------
                TextField NewLectureIDtext = new TextField(TempID);
                NewLectureIDtext.setEditable(false);
                EditLecturenewvalue.add(NewLectureIDtext, 1, 0);

                TextField NEWLectureNameText = new TextField(TempLectureName);
                EditLecturenewvalue.add(NEWLectureNameText, 1, 1);

                TextField NEWLecturersNameText = new TextField(TempLecturerName);
                EditLecturenewvalue.add(NEWLecturersNameText, 1, 2);

                TextField NEWlectureCodeLabelText = new TextField(TempLCode);
                EditLecturenewvalue.add(NEWlectureCodeLabelText, 1, 3);

                TextField NEWsemesterLabelText = new TextField(TempSemester);
                EditLecturenewvalue.add(NEWsemesterLabelText, 1, 4);

                TextField NEWtheoryHourLabelText = new TextField(TempTHour);
                EditLecturenewvalue.add(NEWtheoryHourLabelText, 1, 5);

                TextField NEWlabHourLabelText = new TextField(TempLHour);
                EditLectureoldvalue.add(NEWlabHourLabelText, 1, 6);

                TextField NEWlocalCreditLabelText = new TextField(TempLCredit);
                EditLecturenewvalue.add(NEWlocalCreditLabelText, 1, 7);

                TextField NEWECTSLabelText= new TextField(TempEcts);
                EditLecturenewvalue.add(NEWECTSLabelText, 1, 8);

                TextField NEWprerequisitesLabelText = new TextField(TempPrereq);
                EditLecturenewvalue.add(NEWprerequisitesLabelText, 1, 9);

                TextField NEWcourse_LanguageLabelText= new TextField(TempLLang);
                EditLecturenewvalue.add(NEWcourse_LanguageLabelText, 1, 10);

                TextField NEWcourse_TypeLabelText = new TextField(TempLType);
                EditLecturenewvalue.add(NEWcourse_TypeLabelText, 1, 11);

                TextField NEWcourse_LevelLabelText = new TextField(TempLLevel);
                EditLecturenewvalue.add(NEWcourse_LevelLabelText, 1, 12);

                TextField NEWteaching_MethodsLabelText = new TextField(TempTMethod);
                EditLecturenewvalue.add(NEWteaching_MethodsLabelText, 1, 13);

                TextField NEWcourse_CoordinatorLabelText = new TextField(TempLCoordinator);
                EditLecturenewvalue.add(NEWcourse_CoordinatorLabelText, 1, 14);

                TextField NEWassistantLabelText = new TextField(TempAssistant);
                EditLecturenewvalue.add(NEWassistantLabelText, 1, 15);

                TextField NEWcourse_ObjectivesLabelText = new TextField(TempLObjective);
                EditLecturenewvalue.add(NEWcourse_ObjectivesLabelText, 1, 16);

                TextField NEWlearning_OutcomesLabelText = new TextField(TempLOutcome);
                EditLecturenewvalue.add(NEWlearning_OutcomesLabelText, 1, 17);

                TextField NEWcourse_DescriptionLabelText = new TextField(TempLDescription);
                EditLecturenewvalue.add(NEWcourse_DescriptionLabelText, 1,18);

                TextField NEWcourse_CategoryLabelText = new TextField(TempLCategory);
                EditLecturenewvalue.add(NEWcourse_CategoryLabelText, 1, 19);

                TextField NEWweek1SubjectsLabelText = new TextField(TempW1sub);
                EditLecturenewvalue.add(NEWweek1SubjectsLabelText, 1, 20);

                TextField NEWweek2SubjectsLabelText = new TextField(TempW2sub);
                EditLecturenewvalue.add(NEWweek2SubjectsLabelText, 1, 21);

                TextField NEWweek3SubjectsLabelText = new TextField(TempW3sub);
                EditLecturenewvalue.add(NEWweek3SubjectsLabelText, 1, 22);

                TextField NEWweek4SubjectsLabelText = new TextField(TempW4sub);
                EditLecturenewvalue.add(NEWweek4SubjectsLabelText, 1, 23);

                TextField NEWweek5SubjectsLabelText = new TextField(TempW5sub);
                EditLecturenewvalue.add(NEWweek5SubjectsLabelText, 1, 24);

                TextField NEWweek6SubjectsLabelText = new TextField(TempW6sub);
                EditLecturenewvalue.add(NEWweek6SubjectsLabelText, 1, 25);

                TextField NEWweek7SubjectsLabelText = new TextField(TempW7sub);
                EditLecturenewvalue.add(NEWweek7SubjectsLabelText, 1, 26);

                TextField NEWweek8SubjectsLabelText = new TextField(TempW8sub);
                EditLecturenewvalue.add(NEWweek8SubjectsLabelText, 1, 27);

                TextField NEWweek9SubjectsLabelText = new TextField(TempW9sub);
                EditLecturenewvalue.add(NEWweek9SubjectsLabelText, 1, 28);

                TextField NEWweek10SubjectsLabelText = new TextField(TempW10sub);
                EditLecturenewvalue.add(NEWweek10SubjectsLabelText, 1, 29);

                TextField NEWweek11SubjectsLabelText = new TextField(TempW11sub);
                EditLecturenewvalue.add(NEWweek11SubjectsLabelText, 1, 30);

                TextField NEWweek12SubjectsLabelText = new TextField(TempW12sub);
                EditLecturenewvalue.add(NEWweek12SubjectsLabelText, 1, 31);

                TextField NEWweek13SubjectsLabelText = new TextField(TempW13sub);
                EditLecturenewvalue.add(NEWweek13SubjectsLabelText, 1, 32);

                TextField NEWweek14SubjectsLabelText = new TextField(TempW14sub);
                EditLecturenewvalue.add(NEWweek14SubjectsLabelText, 1, 33);

                TextField NEWweek15SubjectsLabelText = new TextField(TempW15sub);
                EditLecturenewvalue.add(NEWweek15SubjectsLabelText, 1, 34);

                TextField NEWweek1ReqMatLabelText = new TextField(TempW1req);
                EditLecturenewvalue.add(NEWweek1ReqMatLabelText, 1, 36);

                TextField NEWweek2ReqMatLabelText = new TextField(TempW2req);
                EditLecturenewvalue.add(NEWweek2ReqMatLabelText, 1, 37);

                TextField NEWweek3ReqMatLabelText = new TextField(TempW3req);
                EditLecturenewvalue.add(NEWweek3ReqMatLabelText, 1, 38);

                TextField NEWweek4ReqMatLabelText = new TextField(TempW4req);
                EditLecturenewvalue.add(NEWweek4ReqMatLabelText, 1, 39);

                TextField NEWweek5ReqMatLabelText = new TextField(TempW5req);
                EditLecturenewvalue.add(NEWweek5ReqMatLabelText, 1, 40);

                TextField NEWweek6ReqMatLabelText = new TextField(TempW6req);
                EditLecturenewvalue.add(NEWweek6ReqMatLabelText, 1, 41);

                TextField NEWweek7ReqMatLabelText = new TextField(TempW7req);
                EditLecturenewvalue.add(NEWweek7ReqMatLabelText, 1, 42);

                TextField NEWweek8ReqMatLabelText = new TextField(TempW8req);
                EditLecturenewvalue.add(NEWweek8ReqMatLabelText, 1, 43);

                TextField NEWweek9ReqMatLabelText = new TextField(TempW9req);
                EditLecturenewvalue.add(NEWweek9ReqMatLabelText, 1, 44);

                TextField NEWweek10ReqMatLabelText = new TextField(TempW10req);
                EditLecturenewvalue.add(NEWweek10ReqMatLabelText, 1, 45);

                TextField NEWweek11ReqMatLabelText = new TextField(TempW11req);
                EditLecturenewvalue.add(NEWweek11ReqMatLabelText, 1, 46);

                TextField NEWweek12ReqMatLabelText = new TextField(TempW12req);
                EditLecturenewvalue.add(NEWweek12ReqMatLabelText, 1, 47);

                TextField NEWweek13ReqMatLabelText = new TextField(TempW13req);
                EditLecturenewvalue.add(NEWweek13ReqMatLabelText, 1, 48);

                TextField NEWweek14ReqMatLabelText = new TextField(TempW14req);
                EditLecturenewvalue.add(NEWweek14ReqMatLabelText, 1, 49);

                TextField NEWweek15ReqMatLabelText= new TextField(TempW15req);
                EditLecturenewvalue.add(NEWweek15ReqMatLabelText, 1, 50);

                TextField NEWcourseNotesAndTextBooksLabelText = new TextField(TempCourseNotes);
                EditLecturenewvalue.add(NEWcourseNotesAndTextBooksLabelText, 1, 51);

                TextField NEWsuggestedReadingsAndMaterialsLabelText = new TextField(TempSuggested);
                EditLecturenewvalue.add(NEWsuggestedReadingsAndMaterialsLabelText, 1, 52);

                TextField NEWparticipationNumberLabelText = new TextField(TempPartNumb);
                EditLecturenewvalue.add(NEWparticipationNumberLabelText, 1, 53);

                TextField  NEWparticipationWeiLabelText= new TextField(TempPartWei);
                EditLecturenewvalue.add(NEWparticipationWeiLabelText, 1, 54);

                TextField  NEWparticipation01LabelText= new TextField(TempPartL01);
                EditLecturenewvalue.add(NEWparticipation01LabelText, 1, 55);

                TextField NEWparticipation02LabelText = new TextField(TempPartL02);
                EditLecturenewvalue.add(NEWparticipation02LabelText, 1, 56);

                TextField NEWparticipation03LabelText = new TextField(TempPartL03);
                EditLecturenewvalue.add(NEWparticipation03LabelText, 1, 57);

                TextField  NEWparticipation04LabelText= new TextField(TempPartL04);
                EditLecturenewvalue.add(NEWparticipation04LabelText, 1, 58);

                TextField NEWlabOrApplicationNumberLabelText = new TextField(TempLabNumb);
                EditLecturenewvalue.add(NEWlabOrApplicationNumberLabelText, 1, 59);

                TextField  NEWlabOrApplicationWeiLabelText= new TextField(TempLabWei);
                EditLecturenewvalue.add(NEWlabOrApplicationWeiLabelText, 1, 60);

                TextField NEWlabOrApplication01LabelText = new TextField(TempLabL01);
                EditLecturenewvalue.add(NEWlabOrApplication01LabelText, 1, 61);

                TextField NEWlabOrApplication02LabelText = new TextField(TempLabL02);
                EditLecturenewvalue.add(NEWlabOrApplication02LabelText, 1, 62);

                TextField NEWlabOrApplication03LabelText = new TextField(TempLabL03);
                EditLecturenewvalue.add(NEWlabOrApplication03LabelText, 1, 63);

                TextField NEWlabOrApplication04LabelText = new TextField(TempLabL04);
                EditLecturenewvalue.add(NEWlabOrApplication04LabelText, 1, 64);

                TextField NEWfieldWorkNumberLabelText = new TextField(TempFWNumb);
                EditLecturenewvalue.add(NEWfieldWorkNumberLabelText, 1, 65);

                TextField NEWFieldWorkWeiLabelText = new TextField(TempFWWei);
                EditLecturenewvalue.add(NEWFieldWorkWeiLabelText, 1, 66);

                TextField NEWFieldWork01LabelText = new TextField(TempFWL01);
                EditLecturenewvalue.add(NEWFieldWork01LabelText, 1, 67);

                TextField NEWFieldWork02LabelText = new TextField(TempFWL02);
                EditLecturenewvalue.add(NEWFieldWork02LabelText, 1, 68);

                TextField NEWFieldWork03LabelText = new TextField(TempFWL03);
                EditLecturenewvalue.add(NEWFieldWork03LabelText, 1, 69);

                TextField NEWFieldWork04LabelText = new TextField(TempFWL04);
                EditLecturenewvalue.add(NEWFieldWork04LabelText, 1, 70);

                TextField NEWquizOrStudioCritiqueNumberLabelText = new TextField(TempQuizNumb);
                EditLecturenewvalue.add(NEWquizOrStudioCritiqueNumberLabelText, 1, 71);

                TextField NEWquizOrStudioCritiqueWeiLabelText = new TextField(TempQuizWei);
                EditLecturenewvalue.add(NEWquizOrStudioCritiqueWeiLabelText, 1,72);

                TextField NEWquizOrStudioCritique01LabelText = new TextField(TempQuizL01);
                EditLecturenewvalue.add(NEWquizOrStudioCritique01LabelText, 1, 73);

                TextField NEWquizOrStudioCritique02LabelText = new TextField(TempQuizL02);
                EditLecturenewvalue.add(NEWquizOrStudioCritique02LabelText, 1, 74);

                TextField NEWquizOrStudioCritique03LabelText = new TextField(TempQuizL03);
                EditLecturenewvalue.add(NEWquizOrStudioCritique03LabelText, 1, 75);

                TextField NEWquizOrStudioCritique04LabelText = new TextField(TempQuizL04);
                EditLecturenewvalue.add(NEWquizOrStudioCritique04LabelText, 1, 76);

                TextField NEWhomeworkOrAssignmentNumberLabelText = new TextField(TempHWNumb);
                EditLecturenewvalue.add(NEWhomeworkOrAssignmentNumberLabelText, 1, 77);

                TextField NEWhomeworkOrAssignmentWeiLabelText = new TextField(TempHWWei);
                EditLecturenewvalue.add(NEWhomeworkOrAssignmentWeiLabelText, 1, 78);

                TextField NEWhomeworkOrAssignment01LabelText = new TextField(TempHWL01);
                EditLecturenewvalue.add(NEWhomeworkOrAssignment01LabelText, 1, 79);

                TextField NEWhomeworkOrAssignment02LabelText = new TextField(TempHWL02);
                EditLecturenewvalue.add(NEWhomeworkOrAssignment02LabelText, 1, 80);

                TextField NEWhomeworkOrAssignment03LabelText = new TextField(TempHWL03);
                EditLecturenewvalue.add(NEWhomeworkOrAssignment03LabelText, 1, 81);

                TextField NEWhomeworkOrAssignment04LabelText = new TextField(TempHWL04);
                EditLecturenewvalue.add(NEWhomeworkOrAssignment04LabelText, 1, 82);

                TextField NEWpresentationOrJuryNumberLabelText = new TextField(TempPresNumb);
                EditLecturenewvalue.add(NEWpresentationOrJuryNumberLabelText, 1, 83);

                TextField NEWpresentationOrJuryWeiLabelText = new TextField(TempPresWei);
                EditLecturenewvalue.add(NEWpresentationOrJuryWeiLabelText, 1, 84);

                TextField NEWpresentationOrJury01LabelText = new TextField(TempPresL01);
                EditLecturenewvalue.add(NEWpresentationOrJury01LabelText, 1, 85);

                TextField NEWpresentationOrJury02LabelText = new TextField(TempPresL02);
                EditLecturenewvalue.add(NEWpresentationOrJury02LabelText, 1, 86);

                TextField NEWpresentationOrJury03LabelText = new TextField(TempPresL03);
                EditLecturenewvalue.add(NEWpresentationOrJury03LabelText, 1, 87);

                TextField NEWpresentationOrJury04LabelText = new TextField(TempPresL04);
                EditLecturenewvalue.add(NEWpresentationOrJury04LabelText, 1, 88);

                TextField NEWprojectNumberLabelText = new TextField(TempProjNumb);
                EditLecturenewvalue.add(NEWprojectNumberLabelText, 1, 89);

                TextField NEWprojectWeiLabelText = new TextField(TempProjWei);
                EditLecturenewvalue.add(NEWprojectWeiLabelText, 1, 90);

                TextField NEWproject01LabelText= new TextField(TempProjL01);
                EditLecturenewvalue.add(NEWproject01LabelText, 1, 91);

                TextField NEWproject02LabelText= new TextField(TempProjL02);
                EditLecturenewvalue.add(NEWproject02LabelText, 1, 92);

                TextField NEWproject03LabelText = new TextField(TempProjL03);
                EditLecturenewvalue.add(NEWproject03LabelText, 1, 93);

                TextField NEWproject04LabelText = new TextField(TempProjL04);
                EditLecturenewvalue.add(NEWproject04LabelText, 1, 94);

                TextField NEWportfolioNumberLabelText = new TextField(TempPortNumb);
                EditLecturenewvalue.add(NEWportfolioNumberLabelText, 1, 95);

                TextField NEWportfolioWeiLabelText = new TextField(TempPortWei);
                EditLecturenewvalue.add(NEWportfolioWeiLabelText, 1, 96);

                TextField NEWportfolio01LabelText = new TextField(TempPortL01);
                EditLecturenewvalue.add(NEWportfolio01LabelText, 1, 97);

                TextField NEWportfolio02LabelText = new TextField(TempPortL02);
                EditLecturenewvalue.add(NEWportfolio02LabelText, 1, 98);

                TextField NEWportfolio03LabelText = new TextField(TempPortL03);
                EditLecturenewvalue.add(NEWportfolio03LabelText, 1, 99);

                TextField NEWportfolio04LabelText = new TextField(TempPortL04);
                EditLecturenewvalue.add(NEWportfolio04LabelText, 1, 100);

                TextField NEWseminarNumberLabelText = new TextField(TempSEMNumb);
                EditLecturenewvalue.add(NEWseminarNumberLabelText, 1, 101);

                TextField NEWseminarWeiLabelText = new TextField(TempSEMWei);
                EditLecturenewvalue.add(NEWseminarWeiLabelText, 1, 102);

                TextField NEWseminar01LabelText = new TextField(TempSEML01);
                EditLecturenewvalue.add(NEWseminar01LabelText, 1, 103);

                TextField NEWseminar02LabelText = new TextField(TempSEML02);
                EditLecturenewvalue.add(NEWseminar02LabelText, 1, 104);

                TextField NEWseminar03LabelText = new TextField(TempSEML03);
                EditLecturenewvalue.add(NEWseminar03LabelText, 1, 105);

                TextField NEWseminar04LabelText = new TextField(TempSEML04);
                EditLecturenewvalue.add(NEWseminar04LabelText, 1, 106);

                TextField NEWoralNumberLabelText = new TextField(TempORALNumb);
                EditLecturenewvalue.add(NEWoralNumberLabelText, 1, 107);

                TextField NEWoralWeiLabelText = new TextField(TempORALWei);
                EditLecturenewvalue.add(NEWoralWeiLabelText, 1, 108);

                TextField NEWoral01LabelText = new TextField(TempORALL01);
                EditLecturenewvalue.add(NEWoral01LabelText, 1, 109);

                TextField NEWoral02LabelText = new TextField(TempORALL02);
                EditLecturenewvalue.add(NEWoral02LabelText, 1, 110);

                TextField NEWoral03LabelText = new TextField(TempORALL03);
                EditLecturenewvalue.add(NEWoral03LabelText, 1, 111);

                TextField NEWoral04LabelText = new TextField(TempORALL04);
                EditLecturenewvalue.add(NEWoral04LabelText, 1, 112);

                TextField NEWmidtermNumberLabelText = new TextField(TempMIDNumb);
                EditLecturenewvalue.add(NEWmidtermNumberLabelText, 1, 113);

                TextField NEWmidtermWeiLabelText = new TextField(TempMIDWei);
                EditLecturenewvalue.add(NEWmidtermWeiLabelText, 1, 114);

                TextField NEWmidterm01LabelText = new TextField(TempMIDL01);
                EditLecturenewvalue.add(NEWmidterm01LabelText, 1, 115);

                TextField NEWmidterm02LabelText = new TextField(TempMIDL02);
                EditLecturenewvalue.add(NEWmidterm02LabelText, 1, 116);

                TextField NEWmidterm03LabelText = new TextField(TempMIDL03);
                EditLecturenewvalue.add(NEWmidterm03LabelText, 1, 117);

                TextField NEWmidterm04LabelText = new TextField(TempMIDL04);
                EditLecturenewvalue.add(NEWmidterm04LabelText, 1, 118);

                TextField NEWfinalNumberLabelText= new TextField(TempFINALNumb);
                EditLecturenewvalue.add(NEWfinalNumberLabelText, 1, 119);

                TextField NEWfinalWeiLabelText = new TextField(TempFINALWei);
                EditLecturenewvalue.add(NEWfinalWeiLabelText, 1, 120);

                TextField NEWfinal01LabelText = new TextField(TempFINALL01);
                EditLecturenewvalue.add(NEWfinal01LabelText, 1, 121);

                TextField NEWfinal02LabelText = new TextField(TempFINALL02);
                EditLecturenewvalue.add(NEWfinal02LabelText, 1, 123);

                TextField NEWfinal03LabelText = new TextField(TempFINALL03);
                EditLecturenewvalue.add(NEWfinal03LabelText, 1, 124);

                TextField NEWfinal04LabelText = new TextField(TempFINALL04);
                EditLecturenewvalue.add(NEWfinal04LabelText, 1, 125);

                TextField NEWStudyHoursoutofClassNumLabelText = new TextField(TempSHOOCNum);
                LectureGrid.add(NEWStudyHoursoutofClassNumLabelText, 1, 126);

                TextField NEWStudyHoursoutofClassDurLabelText = new TextField(TempSHOOCDur);
                LectureGrid.add(NEWStudyHoursoutofClassDurLabelText, 1, 126);

                TextField NEWStudyHoursoutofClassWorkLabelText = new TextField(TempSHOOCWork);
                EditLecturenewvalue.add(NEWStudyHoursoutofClassWorkLabelText, 1, 127);

                TextField NEWFieldWorkNumLabelText = new TextField(TempFWNum);
                EditLecturenewvalue.add(NEWFieldWorkNumLabelText, 1, 128);

                TextField NEWFieldWorkDurLabelText = new TextField(TempFWDur);
                EditLecturenewvalue.add(NEWFieldWorkDurLabelText, 1, 129);

                TextField NEWFieldWorkWorkLabelText = new TextField(TempFWWork);
                EditLecturenewvalue.add(NEWFieldWorkWorkLabelText, 1, 130);

                TextField NEWQuizNumLabelText = new TextField(TempQuizNum);
                EditLecturenewvalue.add(NEWQuizNumLabelText, 1, 131);

                TextField NEWQuizDurLabelText = new TextField(TempQuizDur);
                EditLecturenewvalue.add(NEWQuizDurLabelText, 1, 132);

                TextField NEWQuizWorkLabelText = new TextField(TempQuizWork);
                EditLecturenewvalue.add(NEWQuizWorkLabelText, 1, 133);

                TextField NEWHWNumLabelText = new TextField(TempHWNum);
                EditLecturenewvalue.add(NEWHWNumLabelText, 1, 134);

                TextField NEWHWDurLabelText = new TextField(TempHWDur);
                EditLecturenewvalue.add(NEWHWDurLabelText, 1, 135);

                TextField NEWHWWorkLabelText = new TextField(TempHWWork);
                EditLecturenewvalue.add(NEWHWWorkLabelText, 1, 136);

                TextField NEWPresentationNumLabelText = new TextField(TempPresentationNum);
                EditLecturenewvalue.add(NEWPresentationNumLabelText, 1, 137);

                TextField NEWPresentationDurLabelText = new TextField(TempPresentationDur);
                EditLecturenewvalue.add(NEWPresentationDurLabelText, 1, 138);

                TextField NEWPresentationWorkLabelText = new TextField(TempPresentationWork);
                EditLecturenewvalue.add(NEWPresentationWorkLabelText, 1, 139);

                TextField NEWProjectNumLabelText = new TextField(TempProjectNum);
                EditLecturenewvalue.add(NEWProjectNumLabelText, 1, 140);

                TextField NEWProjectDurLabelText = new TextField(TempProjectDur);
                EditLecturenewvalue.add(NEWProjectDurLabelText, 1, 141);

                TextField NEWProjectWorkLabelText = new TextField(TempProjectWork);
                EditLecturenewvalue.add(NEWProjectWorkLabelText, 1, 142);

                TextField NEWPortfolioNumLabelText = new TextField(TempPortfolioNum);
                EditLecturenewvalue.add(NEWPortfolioNumLabelText, 1, 143);

                TextField NEWPortfolioDurLabelText = new TextField(TempPortfolioDur);
                EditLecturenewvalue.add(NEWPortfolioDurLabelText, 1, 144);

                TextField NEWPortfolioWorkLabelText = new TextField(TempPortfolioWork);
                EditLecturenewvalue.add(NEWPortfolioWorkLabelText, 1, 145);

                TextField NEWSeminarNumLabelText = new TextField(TempSeminarNum);
                EditLecturenewvalue.add(NEWSeminarNumLabelText, 1,146);

                TextField NEWSeminarDurLabelText = new TextField(TempSeminarDur);
                EditLecturenewvalue.add(NEWSeminarDurLabelText, 1,147);

                TextField NEWSeminarWorkLabelText = new TextField(TempSeminarWork);
                EditLecturenewvalue.add(NEWSeminarWorkLabelText, 1,148);

                TextField NEWOralExamNumLabelText = new TextField(TempOralNum);
                EditLecturenewvalue.add(NEWOralExamNumLabelText, 1, 149);

                TextField NEWOralExamDurLabelText = new TextField(TempOralDur);
                EditLecturenewvalue.add(NEWOralExamDurLabelText, 1, 150);

                TextField NEWOralExamWorkLabelText = new TextField(TempOralWork);
                EditLecturenewvalue.add(NEWOralExamWorkLabelText, 1, 151);

                TextField NEWMidtermNumLabelText = new TextField(TempMidtermNum);
                EditLecturenewvalue.add(NEWMidtermNumLabelText, 1, 152);

                TextField NEWMidtermDurLabelText = new TextField(TempMidtermDur);
                EditLecturenewvalue.add(NEWMidtermDurLabelText, 1, 153);

                TextField NEWMidtermWorkLabelText = new TextField(TempMidtermWork);
                EditLecturenewvalue.add(NEWMidtermWorkLabelText, 1, 154);

                TextField NEWFinalNumLabelText = new TextField(TempFinalNum);
                EditLecturenewvalue.add(NEWFinalNumLabelText, 1, 155);

                TextField NEWFinalDurLabelText = new TextField(TempFinalDur);
                EditLecturenewvalue.add(NEWFinalDurLabelText, 1, 156);

                TextField NEWFinalWorkLabelText = new TextField(TempFinalWork);
                EditLecturenewvalue.add(NEWFinalWorkLabelText, 1, 157);

                TextField NEWoutcome1_1LabelText = new TextField(Tempoutcome1_1);
                EditLecturenewvalue.add(NEWoutcome1_1LabelText, 1,  159);

                TextField NEWoutcome1_2LabelText = new TextField(Tempoutcome1_2);
                EditLecturenewvalue.add(NEWoutcome1_2LabelText, 1,  160);

                TextField NEWoutcome1_3LabelText = new TextField(Tempoutcome1_3);
                EditLecturenewvalue.add(NEWoutcome1_3LabelText, 1, 161);

                TextField  NEWoutcome1_4LabelText= new TextField(Tempoutcome1_4);
                EditLecturenewvalue.add(NEWoutcome1_4LabelText, 1, 162);

                TextField  NEWoutcome1_5LabelText= new TextField(Tempoutcome1_5);
                EditLecturenewvalue.add(NEWoutcome1_5LabelText, 1, 163);

                TextField  NEWoutcome2_1LabelText= new TextField( Tempoutcome2_1);
                EditLecturenewvalue.add(NEWoutcome2_1LabelText, 1, 164);

                TextField NEWoutcome2_2LabelText = new TextField(Tempoutcome2_2);
                EditLecturenewvalue.add(NEWoutcome2_2LabelText, 1,  165);

                TextField NEWoutcome2_3LabelText= new TextField(Tempoutcome2_3);
                EditLecturenewvalue.add(NEWoutcome2_3LabelText, 1, 166);

                TextField  NEWoutcome2_4LabelText= new TextField(Tempoutcome2_4);
                EditLecturenewvalue.add(NEWoutcome2_4LabelText, 1, 167);

                TextField  NEWoutcome2_5LabelText= new TextField(Tempoutcome2_5);
                EditLecturenewvalue.add(NEWoutcome2_5LabelText, 1, 168);

                TextField  NEWoutcome3_1LabelText= new TextField(Tempoutcome3_1);
                EditLecturenewvalue.add(NEWoutcome3_1LabelText, 1, 169);

                TextField NEWoutcome3_2LabelText = new TextField(Tempoutcome3_2);
                EditLecturenewvalue.add(NEWoutcome3_2LabelText, 1,  170);

                TextField NEWoutcome3_3LabelText = new TextField(Tempoutcome3_3);
                EditLecturenewvalue.add(NEWoutcome3_3LabelText, 1, 171);

                TextField  NEWoutcome3_4LabelText= new TextField(Tempoutcome3_4);
                EditLecturenewvalue.add(NEWoutcome3_4LabelText, 1, 172);

                TextField  NEWoutcome3_5LabelText= new TextField(Tempoutcome3_5);
                EditLecturenewvalue.add(NEWoutcome3_5LabelText, 1, 173);

                TextField  NEWoutcome4_1LabelText= new TextField(Tempoutcome4_1);
                EditLecturenewvalue.add(NEWoutcome4_1LabelText, 1, 174);

                TextField NEWoutcome4_2LabelText = new TextField(Tempoutcome4_2);
                EditLecturenewvalue.add(NEWoutcome4_2LabelText, 1,  175);

                TextField NEWoutcome4_3LabelText= new TextField(Tempoutcome4_3);
                EditLecturenewvalue.add(NEWoutcome4_3LabelText, 1, 176);

                TextField  NEWoutcome4_4LabelText= new TextField(Tempoutcome4_4);
                EditLecturenewvalue.add(NEWoutcome4_4LabelText, 1, 177);

                TextField  NEWoutcome4_5LabelText= new TextField(Tempoutcome4_5);
                EditLecturenewvalue.add(NEWoutcome4_5LabelText, 1, 178);

                TextField  NEWoutcome5_1LabelText= new TextField(Tempoutcome5_1);
                EditLecturenewvalue.add(NEWoutcome5_1LabelText, 1, 179);

                TextField NEWoutcome5_2LabelText = new TextField(Tempoutcome5_2);
                EditLecturenewvalue.add(NEWoutcome5_2LabelText, 1,  180);

                TextField NEWoutcome5_3LabelText= new TextField(Tempoutcome5_3);
                EditLecturenewvalue.add(NEWoutcome5_3LabelText, 1, 181);

                TextField  NEWoutcome5_4LabelText= new TextField(Tempoutcome5_4);
                EditLecturenewvalue.add(NEWoutcome5_4LabelText, 1, 182);

                TextField  NEWoutcome5_5LabelText= new TextField(Tempoutcome5_5);
                EditLecturenewvalue.add(NEWoutcome5_5LabelText, 1, 183);

                TextField  NEWoutcome6_1LabelText= new TextField(Tempoutcome6_1);
                EditLecturenewvalue.add(NEWoutcome6_1LabelText, 1, 184);

                TextField NEWoutcome6_2LabelText = new TextField(Tempoutcome6_2);
                EditLecturenewvalue.add(NEWoutcome6_2LabelText, 1,  185);

                TextField NEWoutcome6_3LabelText = new TextField(Tempoutcome6_3);
                EditLecturenewvalue.add(NEWoutcome6_3LabelText, 1, 186);

                TextField  NEWoutcome6_4LabelText= new TextField(Tempoutcome6_4);
                EditLecturenewvalue.add(NEWoutcome6_4LabelText, 1, 187);

                TextField  NEWoutcome6_5LabelText= new TextField(Tempoutcome6_5);
                EditLecturenewvalue.add(NEWoutcome6_5LabelText, 1, 188);

                TextField  NEWoutcome7_1LabelText= new TextField(Tempoutcome7_1);
                EditLecturenewvalue.add(NEWoutcome7_1LabelText, 1, 189);

                TextField NEWoutcome7_2LabelText = new TextField(Tempoutcome7_2);
                EditLecturenewvalue.add(NEWoutcome7_2LabelText, 1,  190);

                TextField NEWoutcome7_3LabelText = new TextField(Tempoutcome7_3);
                EditLecturenewvalue.add(NEWoutcome7_3LabelText, 1, 191);

                TextField  NEWoutcome7_4LabelText= new TextField(Tempoutcome7_4);
                EditLecturenewvalue.add(NEWoutcome7_4LabelText, 1, 192);

                TextField  NEWoutcome7_5LabelText= new TextField(Tempoutcome7_5);
                EditLecturenewvalue.add(NEWoutcome7_5LabelText, 1, 193);

                TextField  NEWoutcome8_1LabelText= new TextField(Tempoutcome8_1);
                EditLecturenewvalue.add(NEWoutcome8_1LabelText, 1, 194);

                TextField NEWoutcome8_2LabelText= new TextField(Tempoutcome8_2);
                EditLecturenewvalue.add(NEWoutcome8_2LabelText, 1,  195);

                TextField NEWoutcome8_3LabelText = new TextField(Tempoutcome8_3);
                EditLecturenewvalue.add(NEWoutcome8_3LabelText, 1, 196);

                TextField  NEWoutcome8_4LabelText= new TextField(Tempoutcome8_4);
                EditLecturenewvalue.add(NEWoutcome8_4LabelText, 1, 197);

                TextField  NEWoutcome8_5LabelText= new TextField(Tempoutcome8_5);
                EditLecturenewvalue.add(NEWoutcome8_5LabelText, 1, 198);

                TextField  NEWoutcome9_1LabelText= new TextField(Tempoutcome9_1);
                EditLecturenewvalue.add(NEWoutcome9_1LabelText, 1, 199);

                TextField NEWoutcome9_2LabelText= new TextField(Tempoutcome9_2);
                EditLecturenewvalue.add(NEWoutcome9_2LabelText, 1,  200);

                TextField NEWoutcome9_3LabelText= new TextField(Tempoutcome9_3);
                EditLecturenewvalue.add(NEWoutcome9_3LabelText, 1, 201);

                TextField NEWoutcome9_4LabelText= new TextField(Tempoutcome9_4);
                EditLecturenewvalue.add(NEWoutcome9_4LabelText, 1, 202);

                TextField  NEWoutcome9_5LabelText= new TextField(Tempoutcome9_5);
                EditLecturenewvalue.add(NEWoutcome9_5LabelText, 1, 203);

                TextField  NEWoutcome10_1LabelText= new TextField(Tempoutcome10_1);
                EditLecturenewvalue.add(NEWoutcome10_1LabelText, 1, 204);

                TextField NEWoutcome10_2LabelText= new TextField(Tempoutcome10_2);
                EditLecturenewvalue.add(NEWoutcome10_2LabelText, 1,  205);

                TextField NEWoutcome10_3LabelText= new TextField(Tempoutcome10_3);
                EditLecturenewvalue.add(NEWoutcome10_3LabelText, 1, 206);

                TextField  NEWoutcome10_4LabelText= new TextField(Tempoutcome10_4);
                EditLecturenewvalue.add(NEWoutcome10_4LabelText, 1, 207);

                TextField  NEWoutcome10_5LabelText= new TextField(Tempoutcome10_5);
                EditLecturenewvalue.add(NEWoutcome10_5LabelText, 1, 208);

                TextField  NEWoutcome11_1LabelText= new TextField(Tempoutcome11_1);
                EditLecturenewvalue.add(NEWoutcome11_1LabelText, 1, 209);

                TextField NEWoutcome11_2LabelText = new TextField(Tempoutcome11_2);
                EditLecturenewvalue.add(NEWoutcome11_2LabelText, 1,  210);

                TextField NEWoutcome11_3LabelText = new TextField(Tempoutcome11_3);
                EditLecturenewvalue.add(NEWoutcome11_3LabelText, 1, 211);

                TextField  NEWoutcome11_4LabelText= new TextField(Tempoutcome11_4);
                EditLecturenewvalue.add(NEWoutcome11_4LabelText, 1, 212);

                TextField  NEWoutcome11_5LabelText= new TextField(Tempoutcome11_5);
                EditLecturenewvalue.add(NEWoutcome11_5LabelText, 1, 213);

                TextField  NEWoutcome12_1LabelText= new TextField(Tempoutcome12_1);
                EditLecturenewvalue.add(NEWoutcome12_1LabelText, 1, 214);

                TextField NEWoutcome12_2LabelText = new TextField(Tempoutcome12_2);
                EditLecturenewvalue.add(NEWoutcome12_2LabelText, 1,  215);

                TextField NEWoutcome12_3LabelText = new TextField(Tempoutcome12_3);
                EditLecturenewvalue.add(NEWoutcome12_3LabelText, 1, 216);

                TextField  NEWoutcome12_4LabelText= new TextField(Tempoutcome12_4);
                EditLecturenewvalue.add(NEWoutcome12_4LabelText, 1, 217);

                TextField  NEWoutcome12_5LabelText= new TextField(Tempoutcome12_5);
                EditLecturenewvalue.add(NEWoutcome12_5LabelText, 1, 218);

                TextField  NEWoutcome13_1LabelText= new TextField(Tempoutcome13_1);
                EditLecturenewvalue.add(NEWoutcome13_1LabelText, 1, 219);

                TextField NEWoutcome13_2LabelText = new TextField(Tempoutcome13_2);
                EditLecturenewvalue.add(NEWoutcome13_2LabelText, 1,  220);

                TextField NEWoutcome13_3LabelText = new TextField(Tempoutcome13_3);
                EditLecturenewvalue.add(NEWoutcome13_3LabelText, 1, 221);

                TextField  NEWoutcome13_4LabelText= new TextField(Tempoutcome13_4);
                EditLecturenewvalue.add(NEWoutcome13_4LabelText, 1, 222);

                TextField  NEWoutcome13_5LabelText= new TextField(Tempoutcome13_5);
                EditLecturenewvalue.add(NEWoutcome13_5LabelText, 1, 223);


                EditLectureConfirm.setOnAction(event -> {
                        LectureConfig LectureNew = new LectureConfig(lec_id,NEWLectureNameText.getText(),NEWLecturersNameText.getText(),NEWlectureCodeLabelText.getText(),NEWsemesterLabelText.getText(),NEWtheoryHourLabelText.getText(),NEWlabOrApplication01LabelText.getText(),NEWlabOrApplication02LabelText.getText(),NEWlabOrApplication03LabelText.getText(),NEWlabOrApplication04LabelText.getText(),
                                NEWcourse_LanguageLabelText.getText(),NEWcourse_TypeLabelText.getText(),NEWcourse_LevelLabelText.getText(),NEWcourse_CategoryLabel.getText(),NEWcourse_CategoryLabel.getText(),NEWassistantLabelText.getText(),NEWcourse_ObjectivesLabelText.getText(),NEWlearning_OutcomesLabelText.getText(),NEWcourse_DescriptionLabelText.getText(),NEWcourse_CategoryLabelText.getText(),NEWweek1SubjectsLabel.getText(),NEWweek2SubjectsLabelText.getText(),NEWweek3SubjectsLabelText.getText(),NEWweek4SubjectsLabelText.getText(),NEWweek5SubjectsLabelText.getText(),NEWweek6SubjectsLabelText.getText(),NEWweek7SubjectsLabelText.getText(),NEWweek8SubjectsLabelText.getText(),NEWweek9SubjectsLabelText.getText(),NEWweek10SubjectsLabelText.getText(),NEWweek11SubjectsLabelText.getText(),NEWweek12SubjectsLabelText.getText(),
                                NEWweek13SubjectsLabelText.getText(),NEWweek14SubjectsLabelText.getText(),NEWweek15SubjectsLabelText.getText(),NEWweek1ReqMatLabelText.getText(),NEWweek2ReqMatLabelText.getText(),NEWweek3ReqMatLabelText.getText(),NEWweek4ReqMatLabelText.getText(),NEWweek5ReqMatLabelText.getText(),NEWweek6ReqMatLabelText.getText(),NEWweek7ReqMatLabelText.getText(),NEWweek8ReqMatLabelText.getText(),NEWweek9ReqMatLabelText.getText(),NEWweek10ReqMatLabelText.getText(),NEWweek11ReqMatLabelText.getText(),NEWweek12ReqMatLabelText.getText(),NEWweek13ReqMatLabelText.getText(),NEWweek14ReqMatLabelText.getText(),NEWweek15ReqMatLabelText.getText(),NEWcourseNotesAndTextBooksLabelText.getText(),NEWsuggestedReadingsAndMaterialsLabelText.getText(),NEWparticipationNumberLabelText.getText(),NEWlabOrApplicationNumberLabelText.getText(),
                                NEWFieldWorkNumLabelText.getText(),NEWquizOrStudioCritiqueNumberLabelText.getText(),NEWhomeworkOrAssignmentNumberLabelText.getText(),NEWpresentationOrJuryNumberLabelText.getText(),NEWprojectNumberLabelText.getText(),NEWPortfolioWorkLabelText.getText(),NEWSeminarNumLabelText.getText(),NEWOralExamNumLabelText.getText(),
                                NEWmidtermNumberLabelText.getText(),NEWfinalNumberLabelText.getText(),NEWparticipationWeiLabelText.getText(),NEWlabOrApplicationWeiLabelText.getText(),NEWFieldWorkWeiLabelText.getText(),
                                NEWquizOrStudioCritiqueWeiLabelText.getText(),NEWhomeworkOrAssignmentWeiLabelText.getText(),NEWpresentationOrJuryWeiLabelText.getText(),NEWprojectWeiLabelText.getText(),NEWportfolioWeiLabelText.getText(),
                                NEWSeminarWorkLabelText.getText(),NEWOralExamWorkLabelText.getText(),NEWmidtermWeiLabelText.getText(),NEWfinalWeiLabelText.getText(),         NEWparticipation01LabelText.getText(),NEWlabOrApplication01LabelText.getText(),NEWFieldWork01LabelText.getText(),NEWquizOrStudioCritique01LabelText.getText(),NEWhomeworkOrAssignment01LabelText.getText(),
                                NEWpresentationOrJury01LabelText.getText(),NEWproject01LabelText.getText(),NEWportfolio01LabelText.getText(),
                                NEWseminar01LabelText.getText(),NEWoral01LabelText.getText(),NEWmidterm01LabelText.getText(),NEWfinal01LabelText.getText(),
                                NEWparticipation02LabelText.getText(),
                                NEWlabOrApplication02LabelText.getText(),NEWFieldWork02LabelText.getText(),NEWquizOrStudioCritique02LabelText.getText(),NEWhomeworkOrAssignment02LabelText.getText(),
                                NEWpresentationOrJury02LabelText.getText(),NEWproject02LabelText.getText(),NEWportfolio02LabelText.getText(),
                                NEWseminar02LabelText.getText(),NEWoral02LabelText.getText(),NEWmidterm02LabelText.getText(),NEWfinal02LabelText.getText(),
                                NEWparticipation03LabelText.getText(),
                                NEWlabOrApplication03LabelText.getText(),NEWFieldWork03LabelText.getText(),NEWquizOrStudioCritique03LabelText.getText(),NEWhomeworkOrAssignment03LabelText.getText(),
                                NEWpresentationOrJury03LabelText.getText(),NEWproject03LabelText.getText(),NEWportfolio03LabelText.getText(),
                                NEWseminar03LabelText.getText(),NEWoral03LabelText.getText(),NEWmidterm03LabelText.getText(),NEWfinal03LabelText.getText(),
                                NEWparticipation04LabelText.getText(),
                                NEWlabOrApplication04LabelText.getText(),NEWFieldWork04LabelText.getText(),NEWquizOrStudioCritique04LabelText.getText(),NEWhomeworkOrAssignment04LabelText.getText(),
                                NEWpresentationOrJury04LabelText.getText(),NEWproject04LabelText.getText(),NEWportfolio04LabelText.getText(),
                                NEWseminar04LabelText.getText(),NEWoral04LabelText.getText(),NEWmidterm04LabelText.getText(),NEWfinal04LabelText.getText(),
                                NEWStudyHoursoutofClassNumLabelText.getText(),NEWStudyHoursoutofClassDurLabelText.getText(),NEWStudyHoursoutofClassWorkLabelText.getText(),
                                NEWFieldWorkNumLabelText.getText(),NEWFieldWorkDurLabelText.getText(),NEWFieldWorkWorkLabelText.getText(),
                                NEWQuizNumLabelText.getText(),NEWQuizDurLabelText.getText(),NEWQuizWorkLabelText.getText(),
                                NEWHWNumLabelText.getText(),NEWHWDurLabelText.getText(),NEWHWWorkLabelText.getText(),
                                NEWPresentationNumLabelText.getText(),NEWPresentationDurLabelText.getText(),NEWPresentationWorkLabelText.getText(),
                                NEWProjectNumLabelText.getText(),NEWProjectDurLabelText.getText(),NEWProjectWorkLabelText.getText(),
                                NEWPortfolioNumLabelText.getText(),NEWPortfolioDurLabelText.getText(),NEWPortfolioWorkLabelText.getText(),
                                NEWSeminarNumLabelText.getText(),NEWSeminarDurLabelText.getText(),NEWSeminarWorkLabelText.getText(),
                                NEWOralExamNumLabelText.getText(),NEWOralExamDurLabelText.getText(),NEWOralExamWorkLabelText.getText(),
                                NEWMidtermNumLabelText.getText(),NEWMidtermDurLabelText.getText(),NEWMidtermWorkLabelText.getText(),
                                NEWFinalNumLabelText.getText(),NEWFinalDurLabelText.getText(),NEWFinalWorkLabelText.getText(),
                                NEWoutcome1_1LabelText.getText(), NEWoutcome1_2LabelText.getText(), NEWoutcome1_3LabelText.getText(),
                                NEWoutcome1_4LabelText.getText(), NEWoutcome1_5LabelText.getText(), NEWoutcome2_1LabelText.getText(), NEWoutcome2_2LabelText.getText(),
                                NEWoutcome2_3LabelText.getText(), NEWoutcome2_4LabelText.getText(), NEWoutcome2_5LabelText.getText(), NEWoutcome3_1LabelText.getText(),
                                NEWoutcome3_2LabelText.getText(), NEWoutcome3_3LabelText.getText(), NEWoutcome3_4LabelText.getText(),
                                NEWoutcome3_5LabelText.getText(), NEWoutcome4_1LabelText.getText(), NEWoutcome4_2LabelText.getText(), NEWoutcome4_3LabelText.getText(),
                                NEWoutcome4_4LabelText.getText(), NEWoutcome4_5LabelText.getText(), NEWoutcome5_1LabelText.getText(), NEWoutcome5_2LabelText.getText(),
                                NEWoutcome5_3LabelText.getText(), NEWoutcome5_4LabelText.getText(), NEWoutcome5_5LabelText.getText(), NEWoutcome6_1LabelText.getText(),
                                NEWoutcome6_2LabelText.getText(), NEWoutcome6_3LabelText.getText(), NEWoutcome6_4LabelText.getText(), NEWoutcome6_5LabelText.getText(),
                                NEWoutcome7_1LabelText.getText(), NEWoutcome7_2LabelText.getText(), NEWoutcome7_3LabelText.getText(), NEWoutcome7_4LabelText.getText(),
                                NEWoutcome7_5LabelText.getText(), NEWoutcome8_1LabelText.getText(), NEWoutcome8_2LabelText.getText(), NEWoutcome8_3LabelText.getText(),
                                NEWoutcome8_4LabelText.getText(), NEWoutcome8_5LabelText.getText(), NEWoutcome9_1LabelText.getText(), NEWoutcome9_2LabelText.getText(),
                                NEWoutcome9_3LabelText.getText(), NEWoutcome9_4LabelText.getText(), NEWoutcome9_5LabelText.getText(), NEWoutcome10_1LabelText.getText(),
                                NEWoutcome10_2LabelText.getText(), NEWoutcome10_3LabelText.getText(), NEWoutcome10_4LabelText.getText(), NEWoutcome10_5LabelText.getText(),
                                NEWoutcome11_1LabelText.getText(), NEWoutcome11_2LabelText.getText(), NEWoutcome11_3LabelText.getText(), NEWoutcome11_4LabelText.getText(),
                                NEWoutcome11_5LabelText.getText(), NEWoutcome12_1LabelText.getText(), NEWoutcome12_2LabelText.getText(), NEWoutcome12_3LabelText.getText(),
                                NEWoutcome12_4LabelText.getText(), NEWoutcome12_5LabelText.getText(), NEWoutcome13_1LabelText.getText(), NEWoutcome13_2LabelText.getText(),
                                NEWoutcome13_3LabelText.getText(), NEWoutcome13_4LabelText.getText(), NEWoutcome13_5LabelText.getText());
                        DBConnector.getInstance().updateLecture(LectureNew);

                        EditLectureHBox.setVisible(false);
                        LecturesHBox.setVisible(true);
                        LecturesHBox.setEffect(null);




                        AddLectureBox.setVisible(false);





                        openLectureScreen();
                });

                CloseEditLecture.setOnAction(event -> {
                        EditLectureHBox.setVisible(false);
                        LecturesHBox.setVisible(true);
                        LecturesHBox.setEffect(null);




                        AddLectureBox.setVisible(false);





                        openLectureScreen();
                });


        }

        int row = 0;



        @FXML
        public void openPLScreen() {
                LecturesHBox.setVisible(false);

                AddLectureBox.setVisible(false);

                firstEllipses.setVisible(false);
                secondEllipses.setVisible(false);
                thirdEllipses.setVisible(true);



                String path = "images/trash.png";
                String path2="images/Go.png";

                Image image = new Image(getClass().getResource(path).toExternalForm());
                Image image2 = new Image(getClass().getResource(path2).toExternalForm());

                ObservableList<TableShow> ProgrammingLanguageList = FXCollections
                        .observableArrayList();







                // TODO : Database daha yazılmadı ben şimdiden koydum
               // ArrayList<PLConfig> plconfigs = DBConnector.getInstance().getAllPLConfigObjects();
               // for (PLConfig plconfig : plconfigs) {
                 ///       ProgrammingLanguageList.add(new TableShow(plconfig.getId(), plconfig.getName(),
                    //            new ImageView(image),new ImageView(image2)));
                }





        @FXML
        public void openImportScreen() {
                LecturesHBox.setVisible(false);






                AddLectureBox.setVisible(false);







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






                AddLectureBox.setVisible(false);






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
