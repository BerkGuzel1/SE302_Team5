package com.example.se302_project;

import java.io.File;
import java.sql.*;
import java.util.ArrayList;

public class DBConnector {
    private static DBConnector instance = null;

    private final String fileName;
    private Connection connection;

    private PreparedStatement insertLecture, insertProgrammingLanguage, insertProject, insertGrade, insertEvaluation, insertStudentTable, insertDetailedEvaluation, getDetailedEvaluation2p,
            getPLConfig, getAllPLConfigIds,getAllLectureIds,getAllProjectIds, getMaxProjectID, getEvalIdFromProjectId,
            getLectureConfig, getProjectConfig, getEvaluations, getAllGrades, getStudent, insertProjectwithID,
            getLecture,getPL, deleteLecture, deleteLanguge, deleteProject, deleteGrade, deleteEvaluation,deleteDetailedEvaluation,getAllDetailedEvaluationids, getProjectIDfromLectureID, getProjectIDfromPL;


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
                        "LECTURE_CODE TEXT,"+
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
                        "COURSE_CATEGORY TEXT)" );



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

            insertLecture = connection.prepareStatement("INSERT INTO Lecture (LECTURE_ID, LECTURE_NAME, LECTURER, LECTURE_CODE,SEMESTER,THEORY_HOUR_IN_WEEK,APPLICATION_OR_LAB_HOUR_IN_WEEK,LOCAL_CREDITS,ECTS,PREREQUISITES,COURSE_LANGUAGE,COURSE_TYPE,COURSE_LEVEL,TEACHING_METHODS_AND_TECHNIQUES,COURSE_COORDINATOR,ASSISTANT,COURSE_OBJECTIVES,LEARNING_OUTCOMES,COURSE_DESCRIPTION,COURSE_CATEGORY) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
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
            String LectureOutcome= lecture.getLearning_Outcomes();
            String LectureDescription = lecture.getCourse_Description();
            String LectureCategory = lecture.getCourse_Category();


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
            String LectureCode= rs.getString(4);
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
            String LectureOutcome= rs.getString(18);
            String LectureDescription = rs.getString(19);
            String LectureCategory = rs.getString(20);


            LectureConfig config = new LectureConfig(id, Lecturename, Lecturername,LectureCode,Semester,TheoryHour,LabHour,LocalCredit,Ects,Prereq,LectureLang,LectureType,LectureLevel,TMethod,LecturCoordinator,Assistant,LectureObjective,LectureOutcome,LectureDescription,LectureCategory);

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


    public void deleteProjectObject(int id){
        try {
            deleteProject.setInt(1, id);
            deleteEvaluation.setInt(1,id);
            deleteGrade.setInt(1,id);
            deleteProject.execute();
            deleteEvaluation.execute();
            deleteGrade.execute();

        } catch (SQLException e) {
            System.out.println(e);
        }
    }


    public void updateLecture(LectureConfig newLecture){
        int id = newLecture.getLecture_id();
        deleteLectureObject(id);
        addLecture(newLecture);
    }
}