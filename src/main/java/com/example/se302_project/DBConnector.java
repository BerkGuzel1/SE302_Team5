package com.example.se302_project;

import java.io.File;
import java.sql.*;
import java.util.ArrayList;

public class DBConnector {
    private static DBConnector instance = null;

    private final String fileName;
    private Connection connection;

    private PreparedStatement insertLecture, insertProgrammingLanguage, insertProject, insertGrade, insertEvaluation, insertStudentTable, insertDetailedEvaluation, getDetailedEvaluation2p,
            getPLConfig, getAllPLConfigIds, getAllLectureIds, getAllProjectIds, getMaxProjectID, getEvalIdFromProjectId,
            getLectureConfig, getProjectConfig, getEvaluations, getAllGrades, getStudent, insertProjectwithID,
            getLecture, getPL, deleteLecture, deleteLanguge, deleteProject, deleteGrade, deleteEvaluation, deleteDetailedEvaluation, getAllDetailedEvaluationids, getProjectIDfromLectureID, getProjectIDfromPL;


    DBConnector() {
        this.fileName = "info.db";
        File file = new File(fileName);
        boolean firstRun = !file.exists();

        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:" + fileName);

            if (firstRun) {
                Statement stmt = connection.createStatement();

                stmt.executeUpdate("CREATE TABLE IF NOT EXISTS Lecture (" +
                        "LECTURE_ID INTEGER PRIMARY KEY," +
                        "LECTURE_NAME TEXT," +
                        "LECTURER TEXT," +
                        "LECTURE_CODE TEXT," +
                        "SEMESTER TEXT," +
                        "THEORY_HOUR_IN_WEEK TEXT," +
                        "APPLICATION_OR_LAB_HOUR_IN_WEEK TEXT," +
                        "LOCAL_CREDITS TEXT," +
                        "ECTS TEXT," +
                        "PREREQUISITES TEXT," +
                        "COURSE_LANGUAGE TEXT," +
                        "COURSE_TYPE TEXT," +
                        "COURSE_LEVEL TEXT," +
                        "TEACHING_METHODS_AND_TECHNIQUES TEXT," +
                        "COURSE_COORDINATOR TEXT," +
                        "ASSISTANT TEXT," +
                        "COURSE_OBJECTIVES TEXT," +
                        "LEARNING_OUTCOMES TEXT," +
                        "COURSE_DESCRIPTION TEXT," +
                        "COURSE_CATEGORY TEXT, " +
                        "week1Subjects TEXT," +
                        "week2Subjects TEXT," +
                        "week3Subjects TEXT," +
                        "week4Subjects TEXT," +
                        "week5Subjects TEXT," +
                        "week6Subjects TEXT," +
                        "week7Subjects TEXT," +
                        "week8Subjects TEXT," +
                        "week9Subjects TEXT," +
                        "week10Subjects TEXT," +
                        "week11Subjects TEXT," +
                        "week12Subjects TEXT," +
                        "week13Subjects TEXT," +
                        "week14Subjects TEXT," +
                        "week15Subjects TEXT," +
                        "week1ReqMat TEXT," +
                        "week2ReqMat TEXT," +
                        "week3ReqMat TEXT," +
                        "week4ReqMat TEXT," +
                        "week5ReqMat TEXT," +
                        "week6ReqMat TEXT," +
                        "week7ReqMat TEXT," +
                        "week8ReqMat TEXT," +
                        "week9ReqMat TEXT," +
                        "week10ReqMat TEXT," +
                        "week11ReqMat TEXT," +
                        "week12ReqMat TEXT," +
                        "week13ReqMat TEXT," +
                        "week14ReqMat TEXT," +
                        "week15ReqMat TEXT," +
                        "courseNotesAndTextBooks TEXT," +
                        "suggestedReadingsAndMaterials TEXT," +
                        "STUDY_HOURS_OUT_OF_CLASS_NUM TEXT," +
                        "STUDY_HOURS_OUT_OF_CLASS_DUR TEXT," +
                        "STUDY_HOURS_OUT_OF_CLASS_WORK TEXT," +
                        "FIELD_WORK_NUM TEXT," +
                        "FIELD_WORK_DUR TEXT," +
                        "FIELD_WORK_WORK TEXT," +
                        "QUIZ_NUM TEXT," +
                        "QUIZ_DUR TEXT," +
                        "QUIZ_WORK TEXT," +
                        "HOMEWORK_NUM TEXT," +
                        "HOMEWORK_DUR TEXT," +
                        "HOMEWORK_WORK TEXT," +
                        "PRESENTATION_NUM TEXT," +
                        "PRESENTATION_DUR TEXT," +
                        "PRESENTATION_WORK TEXT," +
                        "PROJECT_NUM TEXT," +
                        "PROJECT_DUR TEXT," +
                        "PROJECT_WORK TEXT," +
                        "PORTFOLIO_NUM TEXT," +
                        "PORTFOLIO_DUR TEXT," +
                        "PORTFOLIO_WORK TEXT," +
                        "SEMINAR_NUM TEXT," +
                        "SEMINAR_DUR TEXT," +
                        "SEMINAR_WORK TEXT," +
                        "ORAL_EXAM_NUM TEXT," +
                        "ORAL_EXAM_DUR TEXT," +
                        "ORAL_EXAM_WORK TEXT," +
                        "MIDTERM_NUM TEXT," +
                        "MIDTERM_DUR TEXT," +
                        "MIDTERM_WORK TEXT," +
                        "FINAL_EXAM_NUM TEXT," +
                        "FINAL_EXAM_DUR TEXT," +
                        "FINAL_EXAM_WORK TEXT)");



/*
                stmt.executeUpdate("CREATE TABLE IF NOT EXISTS Evaluation_Table (" +
                        "ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                        "PROJECT_ID INTEGER," +
                        "P_INPUT TEXT," +
                        "P_OUTPUT TEXT)");

                stmt.executeUpdate("CREATE TABLE IF NOT EXISTS Student_Table (" +
                        "STUDENT_ID TEXT PRIMARY KEY," +
                        "STUDENT_NAME TEXT)");

                stmt.executeUpdate("CREATE TABLE IF NOT EXISTS Detailed_Evaluation_Table (" +
                        "ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "EVALUATION_ID INTEGER, " +
                        "RUN_STATUS INTEGER, " +
                        "RUN_OUTPUT TEXT, " +
                        "STUDENT_ID TEXT)");
*/


                System.out.println("Tables have Created!!");
            }

            insertLecture = connection.prepareStatement("INSERT INTO Lecture (LECTURE_ID, LECTURE_NAME, LECTURER, LECTURE_CODE,SEMESTER,THEORY_HOUR_IN_WEEK,APPLICATION_OR_LAB_HOUR_IN_WEEK,LOCAL_CREDITS,ECTS,PREREQUISITES,COURSE_LANGUAGE,COURSE_TYPE,COURSE_LEVEL,TEACHING_METHODS_AND_TECHNIQUES,COURSE_COORDINATOR,ASSISTANT,COURSE_OBJECTIVES,LEARNING_OUTCOMES,COURSE_DESCRIPTION,COURSE_CATEGORY," +
                    "week1Subjects,week2Subjects,week3Subjects,week4Subjects,week5Subjects,week6Subjects,week7Subjects,week8Subjects,week9Subjects,week10Subjects," +
                    "week11Subjects,week12Subjects,week13Subjects,week14Subjects,week15Subjects," +
                    "week1ReqMat,week2ReqMat,week3ReqMat,week4ReqMat,week5ReqMat,week6ReqMat,week7ReqMat,week8ReqMat,week9ReqMat,week10ReqMat," +
                    "week11ReqMat,week12ReqMat,week13ReqMat,week14ReqMat,week15ReqMat," +
                    "courseNotesAndTextBooks,suggestedReadingsAndMaterials,STUDY_HOURS_OUT_OF_CLASS_NUM, STUDY_HOURS_OUT_OF_CLASS_DUR, STUDY_HOURS_OUT_OF_CLASS_WORK, FIELD_WORK_NUM, FIELD_WORK_DUR, FIELD_WORK_WORK, QUIZ_NUM, QUIZ_DUR, QUIZ_WORK, HOMEWORK_NUM, HOMEWORK_DUR, HOMEWORK_WORK, PRESENTATION_NUM, PRESENTATION_DUR, PRESENTATION_WORK, PROJECT_NUM, PROJECT_DUR, PROJECT_WORK, PORTFOLIO_NUM, PORTFOLIO_DUR, PORTFOLIO_WORK, SEMINAR_NUM, SEMINAR_DUR, SEMINAR_WORK, ORAL_EXAM_NUM, ORAL_EXAM_DUR, ORAL_EXAM_WORK, MIDTERM_NUM, MIDTERM_DUR, MIDTERM_WORK, FINAL_EXAM_NUM, FINAL_EXAM_DUR, FINAL_EXAM_WORK) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
         /*   insertEvaluation = connection.prepareStatement("INSERT INTO Evaluation_Table (PROJECT_ID, P_INPUT, P_OUTPUT) VALUES (?,?,?)");
            insertDetailedEvaluation = connection.prepareStatement("INSERT INTO Detailed_Evaluation_Table(EVALUATION_ID, RUN_STATUS, RUN_OUTPUT, STUDENT_ID) VALUES (?,?,?,?)");
*/
            getAllLectureIds = connection.prepareStatement("SELECT LECTURE_ID FROM Lecture");
            getLectureConfig = connection.prepareStatement("SELECT * FROM Lecture WHERE LECTURE_ID = ?");
            getLecture = connection.prepareStatement("SELECT * FROM LECTURE WHERE LECTURE_NAME = ?");
           /* getEvaluations = connection.prepareStatement("SELECT * FROM Evaluation_Table WHERE PROJECT_ID = ?");
            getStudent = connection.prepareStatement("SELECT * FROM Student_Table WHERE STUDENT_ID = ?");
*/

            deleteLecture = connection.prepareStatement("DELETE FROM Lecture WHERE LECTURE_ID = ?");
        /*    deleteLanguge = connection.prepareStatement("DELETE FROM ProgrammingLanguage WHERE PLANGUAGE_ID = ?");
            deleteEvaluation = connection.prepareStatement("DELETE FROM Evaluation_Table WHERE PROJECT_ID = ?");
            deleteDetailedEvaluation = connection.prepareStatement("DELETE FROM  Detailed_Evaluation_Table where EVALUATION_ID=?"); ////// NEYE GÖRE SİLİNMESİ GEREK BAKILMASI GEREKİYOR

            getDetailedEvaluation2p = connection.prepareStatement("SELECT * FROM Detailed_Evaluation_Table WHERE EVALUATION_ID =? AND STUDENT_ID =?");

            getAllDetailedEvaluationids = connection.prepareStatement("SELECT EVALUATION_ID, STUDENT_ID FROM Detailed_Evaluation_Table");

            getProjectIDfromLectureID = connection.prepareStatement("SELECT PROJECT_ID FROM Project Where PROJECT_LECTURE_ID = ?");

            getEvalIdFromProjectId = connection.prepareStatement("SELECT ID FROM Evaluation_Table WHERE PROJECT_ID = ?");*/

        } catch (ClassNotFoundException | SQLException e) {
            System.err.println(e);
        }
    }

    public static DBConnector getInstance() {

        if (instance == null) {
            instance = new DBConnector();
        }

        return instance;
    }

    public void addLecture(LectureConfig lecture) {
        try {
            int lecture_id = lecture.getLecture_id();
            String lecture_name = lecture.getLecture_Name();
            String lecturer_name = lecture.getLecturer_Name();
            String lectureCode = lecture.getLecture_Code();
            String Semester = lecture.getSemester();
            String TheoryHour = lecture.getTheory_Hour_In_Week();
            String LabHour = lecture.getApplication_or_Lab_Hour_In_Week();
            String LocalCredit = lecture.getLocal_Credits();
            String Ects = lecture.getECTS();
            String Prereq = lecture.getPrerequisites();
            String LectureLang = lecture.getCourse_Language();
            String LectureType = lecture.getCourse_Type();
            String LectureLevel = lecture.getCourse_Level();
            String TMethod = lecture.getTeaching_Methods_and_Techniques();
            String LecturCoordinator = lecture.getCourse_Coordinator();
            String Assistant = lecture.getAssistant();
            String LectureObjective = lecture.getCourse_Objectives();
            String LectureOutcome = lecture.getLearning_Outcomes();
            String LectureDescription = lecture.getCourse_Description();
            String LectureCategory = lecture.getCourse_Category();
            String week1Subjects = lecture.getWeek1Subjects() ;
            String week2Subjects= lecture.getWeek2Subjects();
            String week3Subjects= lecture.getWeek3Subjects();
            String week4Subjects= lecture.getWeek4Subjects();
            String week5Subjects= lecture.getWeek5Subjects();
            String week6Subjects= lecture.getWeek6Subjects();
            String week7Subjects= lecture.getWeek7Subjects();
            String week8Subjects= lecture.getWeek8Subjects();
            String week9Subjects= lecture.getWeek9Subjects();
            String week10Subjects= lecture.getWeek10Subjects();
            String week11Subjects= lecture.getWeek11Subjects();
            String week12Subjects= lecture.getWeek12Subjects();
            String week13Subjects= lecture.getWeek13Subjects();
            String week14Subjects= lecture.getWeek14Subjects();
            String week15Subjects= lecture.getWeek15Subjects();
            String week1ReqMat= lecture.getWeek1ReqMat();
            String week2ReqMat= lecture.getWeek2ReqMat();
            String week3ReqMat= lecture.getWeek3ReqMat();
            String week4ReqMat= lecture.getWeek4ReqMat();
            String week5ReqMat= lecture.getWeek5ReqMat();
            String week6ReqMat= lecture.getWeek6ReqMat();
            String week7ReqMat= lecture.getWeek7ReqMat();
            String week8ReqMat= lecture.getWeek8ReqMat();
            String week9ReqMat= lecture.getWeek9ReqMat();
            String week10ReqMat= lecture.getWeek10ReqMat();
            String week11ReqMat= lecture.getWeek11ReqMat();
            String week12ReqMat= lecture.getWeek12ReqMat();
            String week13ReqMat= lecture.getWeek13ReqMat();
            String week14ReqMat= lecture.getWeek14ReqMat();
            String week15ReqMat= lecture.getWeek15ReqMat();
            String courseNotesAndTextBooks= lecture.getCourseNotesAndTextBooks();
            String suggestedReadingsAndMaterials= lecture.getSuggestedReadingsAndMaterials();
            String SHOOCNum = lecture.getStudyHoursoutofClassNum();
            String SHOOCDur = lecture.getStudyHoursoutofClassDur();
            String SHOOCWork = lecture.getStudyHoursoutofClassWork();
            String FWNum = lecture.getFieldWorkNum();
            String FWDur = lecture.getFieldWorkDur();
            String FWWork = lecture.getFieldWorkWork();
            String QuizNum = lecture.getQuizNum();
            String QuizDur = lecture.getQuizDur();
            String QuizWork =  lecture.getQuizWork();
            String HWNum = lecture.getHWNum();
            String HWDur = lecture.getHWDur();
            String HWWork = lecture.getHWWork();
            String PresentationNum = lecture.getPresentationNum();
            String PresentationDur = lecture.getPresentationDur();
            String PresentationWork = lecture.getPresentationWork();
            String ProjectNum = lecture.getProjectNum();
            String ProjectDur = lecture.getProjectDur();
            String ProjectWork = lecture.getProjectWork();
            String PortfolioNum = lecture.getPortfolioNum();
            String PortfolioDur = lecture.getPortfolioDur();
            String PortfolioWork= lecture.getPortfolioWork();
            String SeminarNum = lecture.getSeminarNum();
            String SeminarDur = lecture.getSeminarDur();
            String SeminarWork = lecture.getSeminarWork();
            String OralNum = lecture.getOralExamNum();
            String OralDur = lecture.getOralExamDur();
            String OralWork = lecture.getOralExamWork();
            String MidtermNum = lecture.getMidtermNum();
            String MidtermDur = lecture.getMidterDur();
            String MidtermWork = lecture.getMidterWork();
            String FinalNum = lecture.getFinalExamNum();
            String FinalDur = lecture.getFinalExamDur();
            String FinalWork = lecture.getFinalExamWork();


            insertLecture.setInt(1, lecture_id);
            insertLecture.setString(2, lecture_name);
            insertLecture.setString(3, lecturer_name);
            insertLecture.setString(4, lectureCode);
            insertLecture.setString(5, Semester);
            insertLecture.setString(6, TheoryHour);
            insertLecture.setString(7, LabHour);
            insertLecture.setString(8, LocalCredit);
            insertLecture.setString(9, Ects);
            insertLecture.setString(10, Prereq);
            insertLecture.setString(11, LectureLang);
            insertLecture.setString(12, LectureType);
            insertLecture.setString(13, LectureLevel);
            insertLecture.setString(14, TMethod);
            insertLecture.setString(15, LecturCoordinator);
            insertLecture.setString(16, Assistant);
            insertLecture.setString(17, LectureObjective);
            insertLecture.setString(18, LectureOutcome);
            insertLecture.setString(19, LectureDescription);
            insertLecture.setString(20, LectureCategory);
            insertLecture.setString(21, week1Subjects);
            insertLecture.setString(22, week2Subjects);
            insertLecture.setString(23, week3Subjects);
            insertLecture.setString(24, week4Subjects);
            insertLecture.setString(25, week5Subjects);
            insertLecture.setString(26, week6Subjects);
            insertLecture.setString(27, week7Subjects);
            insertLecture.setString(28, week8Subjects);
            insertLecture.setString(29, week9Subjects);
            insertLecture.setString(30, week10Subjects);
            insertLecture.setString(31, week11Subjects);
            insertLecture.setString(32, week12Subjects);
            insertLecture.setString(33, week13Subjects);
            insertLecture.setString(34, week14Subjects);
            insertLecture.setString(35, week15Subjects);
            insertLecture.setString(36, week1ReqMat);
            insertLecture.setString(37, week2ReqMat);
            insertLecture.setString(38, week3ReqMat);
            insertLecture.setString(39, week4ReqMat);
            insertLecture.setString(40, week5ReqMat);
            insertLecture.setString(41, week6ReqMat);
            insertLecture.setString(42, week7ReqMat);
            insertLecture.setString(43, week8ReqMat);
            insertLecture.setString(44, week9ReqMat);
            insertLecture.setString(45, week10ReqMat);
            insertLecture.setString(46, week11ReqMat);
            insertLecture.setString(47, week12ReqMat);
            insertLecture.setString(48, week13ReqMat);
            insertLecture.setString(49, week14ReqMat);
            insertLecture.setString(50, week15ReqMat);
            insertLecture.setString(51, courseNotesAndTextBooks);
            insertLecture.setString(52, suggestedReadingsAndMaterials);
            insertLecture.setString(53, SHOOCNum);
            insertLecture.setString(54, SHOOCDur);
            insertLecture.setString(55, SHOOCWork);
            insertLecture.setString(56, FWNum);
            insertLecture.setString(57, FWDur);
            insertLecture.setString(58, FWWork);
            insertLecture.setString(59, QuizNum);
            insertLecture.setString(60, QuizDur);
            insertLecture.setString(61, QuizWork);
            insertLecture.setString(62, HWNum);
            insertLecture.setString(63, HWDur);
            insertLecture.setString(64, HWWork);
            insertLecture.setString(65, PresentationNum);
            insertLecture.setString(66, PresentationDur);
            insertLecture.setString(67, PresentationWork);
            insertLecture.setString(68, ProjectNum);
            insertLecture.setString(69, ProjectDur);
            insertLecture.setString(70, ProjectWork);
            insertLecture.setString(71, PortfolioNum);
            insertLecture.setString(72, PortfolioDur);
            insertLecture.setString(73, PortfolioWork);
            insertLecture.setString(74, SeminarNum);
            insertLecture.setString(75, SeminarDur);
            insertLecture.setString(76, SeminarWork);
            insertLecture.setString(77, OralNum);
            insertLecture.setString(78, OralDur);
            insertLecture.setString(79, OralWork);
            insertLecture.setString(80, MidtermNum);
            insertLecture.setString(81, MidtermDur);
            insertLecture.setString(82, MidtermWork);
            insertLecture.setString(83, FinalNum);
            insertLecture.setString(84, FinalDur);
            insertLecture.setString(85, FinalWork);

            insertLecture.execute();

        } catch (Exception e) {
            System.err.println(e);
        }
    }


    public LectureConfig getLectureConfigObject(int id) {
        try {
            getLectureConfig.setInt(1, id);
            getLectureConfig.execute();
            ResultSet rs = getLectureConfig.executeQuery();
            rs.next();

            String Lecturename = rs.getString(2);
            String Lecturername = rs.getString(3);
            String LectureCode = rs.getString(4);
            String Semester = rs.getString(5);
            String TheoryHour = rs.getString(6);
            String LabHour = rs.getString(7);
            String LocalCredit = rs.getString(8);
            String Ects = rs.getString(9);
            String Prereq = rs.getString(10);
            String LectureLang = rs.getString(11);
            String LectureType = rs.getString(12);
            String LectureLevel = rs.getString(13);
            String TMethod = rs.getString(14);
            String LecturCoordinator = rs.getString(15);
            String Assistant = rs.getString(16);
            String LectureObjective = rs.getString(17);
            String LectureOutcome = rs.getString(18);
            String LectureDescription = rs.getString(19);
            String LectureCategory = rs.getString(20);
            String week1Subjects = rs.getString(21) ;
            String week2Subjects= rs.getString(22);
            String week3Subjects= rs.getString(23);
            String week4Subjects= rs.getString(24);
            String week5Subjects= rs.getString(25);
            String week6Subjects= rs.getString(26);
            String week7Subjects= rs.getString(27);
            String week8Subjects= rs.getString(28);
            String week9Subjects= rs.getString(29);
            String week10Subjects= rs.getString(30);
            String week11Subjects= rs.getString(    31);
            String week12Subjects= rs.getString(32);
            String week13Subjects= rs.getString(33);
            String week14Subjects= rs.getString(34);
            String week15Subjects= rs.getString(35);
            String week1ReqMat= rs.getString(36);
            String week2ReqMat= rs.getString(37);
            String week3ReqMat= rs.getString(38);
            String week4ReqMat= rs.getString(39);
            String week5ReqMat= rs.getString(40);
            String week6ReqMat= rs.getString(41);
            String week7ReqMat= rs.getString(42);
            String week8ReqMat= rs.getString(43);
            String week9ReqMat= rs.getString(44);
            String week10ReqMat= rs.getString(45);
            String week11ReqMat= rs.getString(46);
            String week12ReqMat= rs.getString(47);
            String week13ReqMat= rs.getString(48);
            String week14ReqMat= rs.getString(49);
            String week15ReqMat= rs.getString(50);
            String courseNotesAndTextBooks= rs.getString(51);
            String suggestedReadingsAndMaterials= rs.getString(52);
            String SHOOCNum = rs.getString(53);
            String SHOOCDur = rs.getString(54);
            String SHOOCWork = rs.getString(55);
            String FWNum = rs.getString(56);
            String FWDur = rs.getString(57);
            String FWWork = rs.getString(58);
            String QuizNum = rs.getString(59);
            String QuizDur = rs.getString(60);
            String QuizWork =  rs.getString(61);
            String HWNum = rs.getString(62);
            String HWDur = rs.getString(63);
            String HWWork = rs.getString(64);
            String PresentationNum = rs.getString(65);
            String PresentationDur = rs.getString(66);
            String PresentationWork = rs.getString(67);
            String ProjectNum = rs.getString(68);
            String ProjectDur = rs.getString(69);
            String ProjectWork = rs.getString(70);
            String PortfolioNum = rs.getString(71);
            String PortfolioDur = rs.getString(72);
            String PortfolioWork= rs.getString(73);
            String SeminarNum = rs.getString(74);
            String SeminarDur = rs.getString(75);
            String SeminarWork = rs.getString(76);
            String OralNum = rs.getString(77);
            String OralDur = rs.getString(78);
            String OralWork = rs.getString(79);
            String MidtermNum = rs.getString(80);
            String MidtermDur = rs.getString(81);
            String MidtermWork = rs.getString(82);
            String FinalNum = rs.getString(83);
            String FinalDur = rs.getString(84);
            String FinalWork = rs.getString(85);


            LectureConfig config = new LectureConfig(id, Lecturename, Lecturername, LectureCode, Semester, TheoryHour, LabHour, LocalCredit, Ects, Prereq, LectureLang, LectureType, LectureLevel, TMethod, LecturCoordinator, Assistant, LectureObjective, LectureOutcome, LectureDescription, LectureCategory,week1Subjects,week2Subjects,week3Subjects,week4Subjects,week5Subjects,week6Subjects,week7Subjects,week8Subjects,week9Subjects,week10Subjects, week11Subjects,week12Subjects,week13Subjects,week14Subjects,week15Subjects,
                    week1ReqMat,week2ReqMat,week3ReqMat,week4ReqMat,week5ReqMat,week6ReqMat,week7ReqMat,week8ReqMat,week9ReqMat,week10ReqMat,week11ReqMat,week12ReqMat,week13ReqMat,week14ReqMat,week15ReqMat, courseNotesAndTextBooks,suggestedReadingsAndMaterials, SHOOCNum, SHOOCDur, SHOOCWork, FWNum, FWDur, FWWork, QuizNum, QuizDur, QuizWork, HWNum, HWDur, HWWork, PresentationNum, PresentationDur, PresentationWork, ProjectNum, ProjectDur, ProjectWork, PortfolioNum, PortfolioDur, PortfolioWork, SeminarNum,
                    SeminarDur, SeminarWork, OralNum, OralDur, OralWork, MidtermNum, MidtermDur, MidtermWork, FinalNum, FinalDur, FinalWork);

            return config;

        } catch (Exception e) {
            System.out.println(e);
        }

        return null;
    }

    //Kontrol edilmesi lazım getLecturenin
    public LectureConfig getLecture(String Name) throws SQLException {
        try {
            getLecture.setString(1, Name);
            getLecture.execute();
            ResultSet rs = getLecture.executeQuery();
            rs.next();

            int LectureID = rs.getInt(1);
            String Lecturername = rs.getString(3);


            LectureConfig config = new LectureConfig(LectureID, Name, Lecturername);

            return config;

        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }


    public ArrayList<LectureConfig> getAllLectureConfigObjects() {
        ArrayList<LectureConfig> configList = new ArrayList<>();
        try {
            ResultSet rs = getAllLectureIds.executeQuery();
            ResultSetMetaData rsmd = rs.getMetaData();
            int columnCount = rsmd.getColumnCount();

            try {
                while (rs.next()) {
                    int i = 1;
                    while (i <= columnCount) {
                        int id = rs.getInt(i++);
                        LectureConfig config = getLectureConfigObject(id);
                        configList.add(config);
                    }
                }
            } catch (SQLException e) {
                System.out.println(e);
            }
            return configList;

        } catch (Exception e) {
            System.out.println(e);
        }

        return configList;
    }


    public void deleteLectureObject(int id) {
        try {
            deleteLecture.setInt(1, id);
            deleteLecture.execute();
            //  getProjectIDfromLectureID.setInt(1,id);
            //  getProjectIDfromLectureID.execute();
            //  ResultSet rs = getProjectIDfromLectureID.executeQuery();
            // while (rs.next()) {
            //    int pid = rs.getInt(1);
            //   deleteProjectObject(pid);
            // }

        } catch (SQLException e) {
            System.out.println(e);
        }
    }


 /*   public void deleteProjectObject(int id) {
        try {
            deleteProject.setInt(1, id);
            deleteEvaluation.setInt(1, id);
            deleteGrade.setInt(1, id);
            deleteProject.execute();
            deleteEvaluation.execute();
            deleteGrade.execute();

        } catch (SQLException e) {
            System.out.println(e);
        }
    }*/


    public void updateLecture(LectureConfig newLecture) {
        int id = newLecture.getLecture_id();
        deleteLectureObject(id);
        addLecture(newLecture);
    }
}