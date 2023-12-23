package com.example.se302_project;



public class LectureConfig {
    private int lecture_id;
    private String lecture_Name;
    private String lecturer_Name;
    private String lecture_Code;
    public String semester;
    private String  theory_Hour_In_Week;
    private String application_or_Lab_Hour_In_Week;
    private String local_Credits;
    private String ECTS;
    private String prerequisites;
    private String course_Language;
    private String course_Type;
    private String course_Level;
    private String teaching_Methods_and_Techniques;
    private String course_Coordinator;
    private String assistant;
    private String course_Objectives;
    private String learning_Outcomes;
    private String course_Description;
    private String course_Category;
    private String week1Subjects;
    private String week2Subjects;
    private String week3Subjects;
    private String week4Subjects;
    private String week5Subjects;
    private String week6Subjects;
    private String week7Subjects;
    private String week8Subjects;
    private String week9Subjects;
    private String week10Subjects;
    private String week11Subjects;
    private String week12Subjects;
    private String week13Subjects;
    private String week14Subjects;
    private String week15Subjects;

    private String week1ReqMat;
    private String week2ReqMat;
    private String week3ReqMat;
    private String week4ReqMat;
    private String week5ReqMat;
    private String week6ReqMat;
    private String week7ReqMat;
    private String week8ReqMat;
    private String week9ReqMat;
    private String week10ReqMat;
    private String week11ReqMat;
    private String week12ReqMat;
    private String week13ReqMat;
    private String week14ReqMat;
    private String week15ReqMat;

    private String courseNotesAndTextBooks;
    private String suggestedReadingsAndMaterials;
  /*private String assesment_Semester_Activities;
    private Integer assesment_Number;
    private Integer weigthing;
    private Integer LO1;
    private Integer LO2;
    private Integer LO3;
    private Integer LO4;
    private Integer LO5;
    private Integer LO6;
    private Integer LO7;
    private String ECTS_Semester_Activities;
    private String ECTS_Number;
    private Integer duration_Hours;
    private Integer workload;
    private Integer outcome_Number;
    private String program_Competencies_or_Outcomes;
    private String contribution_Level;
    private Integer number_of_LOs;
*/
    public String getLecture_Code() {
        return lecture_Code;
    }

    public void setLecture_Code(String lecture_Code) {
        this.lecture_Code = lecture_Code;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        switch (semester) {
            case "Fall":
                // code for handling Fall semester
                break;
            case "Spring":
                // code for handling Spring semester
                break;
        }
    }

    public String getTheory_Hour_In_Week() {
        return theory_Hour_In_Week;
    }

    public void setTheory_Hour_In_Week(String theory_Hour_In_Week) {
        this.theory_Hour_In_Week = theory_Hour_In_Week;
    }

    public String getApplication_or_Lab_Hour_In_Week() {
        return application_or_Lab_Hour_In_Week;
    }

    public void setApplication_or_Lab_Hour_In_Week(String application_or_Lab_Hour_In_Week) {
        this.application_or_Lab_Hour_In_Week = application_or_Lab_Hour_In_Week;
    }

    public String getLocal_Credits() {
        return local_Credits;
    }

    public void setLocal_Credits(String local_Credits) {
        this.local_Credits = local_Credits;
    }

    public String  getECTS() {
        return ECTS;
    }

    public void setECTS(String ECTS) {
        this.ECTS = ECTS;
    }

    public String getPrerequisites() {
        return prerequisites;
    }

    public void setPrerequisites(String prerequisites) {
        this.prerequisites = prerequisites;
    }

    public String getCourse_Language() {
        return course_Language;
    }

    public void setCourse_Language(String course_Language) {
        switch (course_Language) {
            case "English":
                break;
            case "Turkish":
                break;
            case "Second Foreign Language":
                break;
        }

    }

    public String getCourse_Type() {
        return course_Type;
    }

    public void setCourse_Type(String course_Type) {
        switch (course_Type) {
            case "Required":
                break;
            case "Elective":
                break;
        }
    }

    public String getCourse_Level() {
        return course_Level;
    }

    public void setCourse_Level(String course_Level) {
        switch (course_Level) {
            case "Short Cycle":
                break;
            case "First Cycle":
                break;
            case "Second Cycle":
                break;
            case "Third Cycle":
                break;
        }
    }

    public String getTeaching_Methods_and_Techniques() {
        return teaching_Methods_and_Techniques;
    }

    public void setTeaching_Methods_and_Techniques(String teaching_Methods_and_Techniques) {
        this.teaching_Methods_and_Techniques = teaching_Methods_and_Techniques;
    }

    public String getCourse_Coordinator() {
        return course_Coordinator;
    }

    public void setCourse_Coordinator(String course_Coordinator) {
        this.course_Coordinator = course_Coordinator;
    }

    public String getAssistant() {
        return assistant;
    }

    public void setAssistant(String assistant) {
        this.assistant = assistant;
    }

    public String getCourse_Objectives() {
        return course_Objectives;
    }

    public void setCourse_Objectives(String course_Objectives) {
        this.course_Objectives = course_Objectives;
    }

    public String getLearning_Outcomes() {
        return learning_Outcomes;
    }

    public void setLearning_Outcomes(String learning_Outcomes) {
        this.learning_Outcomes = learning_Outcomes;
    }

    public String getCourse_Description() {
        return course_Description;
    }

    public void setCourse_Description(String course_Description) {
        this.course_Description = course_Description;
    }

    public String getCourse_Category() {
        return course_Category;
    }

    public void setCourse_Category(String course_Category) {
        switch (course_Category) {
            case "Core Course":
                break;
            case "Major Area Course":
                break;
            case "Supportive Course":
                break;
            case "Communication and Management Skills Course":
                break;
            case "Transferable Skill Courses":
                break;
        }
    }

    public String getWeek1Subjects() {
        return week1Subjects;
    }

    public void setWeek1Subjects(String week1Subjects) {
        this.week1Subjects = week1Subjects;
    }

    public String getWeek2Subjects() {
        return week2Subjects;
    }

    public void setWeek2Subjects(String week2Subjects) {
        this.week2Subjects = week2Subjects;
    }

    public String getWeek3Subjects() {
        return week3Subjects;
    }

    public void setWeek3Subjects(String week3Subjects) {
        this.week3Subjects = week3Subjects;
    }

    public String getWeek4Subjects() {
        return week4Subjects;
    }

    public void setWeek4Subjects(String week4Subjects) {
        this.week4Subjects = week4Subjects;
    }

    public String getWeek5Subjects() {
        return week5Subjects;
    }

    public void setWeek5Subjects(String week5Subjects) {
        this.week5Subjects = week5Subjects;
    }

    public String getWeek6Subjects() {
        return week6Subjects;
    }

    public void setWeek6Subjects(String week6Subjects) {
        this.week6Subjects = week6Subjects;
    }

    public String getWeek7Subjects() {
        return week7Subjects;
    }

    public void setWeek7Subjects(String week7Subjects) {
        this.week7Subjects = week7Subjects;
    }

    public String getWeek8Subjects() {
        return week8Subjects;
    }

    public void setWeek8Subjects(String week8Subjects) {
        this.week8Subjects = week8Subjects;
    }

    public String getWeek9Subjects() {
        return week9Subjects;
    }

    public void setWeek9Subjects(String week9Subjects) {
        this.week9Subjects = week9Subjects;
    }

    public String getWeek10Subjects() {
        return week10Subjects;
    }

    public void setWeek10Subjects(String week10Subjects) {
        this.week10Subjects = week10Subjects;
    }

    public String getWeek11Subjects() {
        return week11Subjects;
    }

    public void setWeek11Subjects(String week11Subjects) {
        this.week11Subjects = week11Subjects;
    }

    public String getWeek12Subjects() {
        return week12Subjects;
    }

    public void setWeek12Subjects(String week12Subjects) {
        this.week12Subjects = week12Subjects;
    }

    public String getWeek13Subjects() {
        return week13Subjects;
    }

    public void setWeek13Subjects(String week13Subjects) {
        this.week13Subjects = week13Subjects;
    }

    public String getWeek14Subjects() {
        return week14Subjects;
    }

    public void setWeek14Subjects(String week14Subjects) {
        this.week14Subjects = week14Subjects;
    }

    public String getWeek15Subjects() {
        return week15Subjects;
    }

    public void setWeek15Subjects(String week15Subjects) {
        this.week15Subjects = week15Subjects;
    }

    public String getWeek1ReqMat() {
        return week1ReqMat;
    }

    public void setWeek1ReqMat(String week1ReqMat) {
        this.week1ReqMat = week1ReqMat;
    }

    public String getWeek2ReqMat() {
        return week2ReqMat;
    }

    public void setWeek2ReqMat(String week2ReqMat) {
        this.week2ReqMat = week2ReqMat;
    }

    public String getWeek3ReqMat() {
        return week3ReqMat;
    }

    public void setWeek3ReqMat(String week3ReqMat) {
        this.week3ReqMat = week3ReqMat;
    }

    public String getWeek4ReqMat() {
        return week4ReqMat;
    }

    public void setWeek4ReqMat(String week4ReqMat) {
        this.week4ReqMat = week4ReqMat;
    }

    public String getWeek5ReqMat() {
        return week5ReqMat;
    }

    public void setWeek5ReqMat(String week5ReqMat) {
        this.week5ReqMat = week5ReqMat;
    }

    public String getWeek6ReqMat() {
        return week6ReqMat;
    }

    public void setWeek6ReqMat(String week6ReqMat) {
        this.week6ReqMat = week6ReqMat;
    }

    public String getWeek7ReqMat() {
        return week7ReqMat;
    }

    public void setWeek7ReqMat(String week7ReqMat) {
        this.week7ReqMat = week7ReqMat;
    }

    public String getWeek8ReqMat() {
        return week8ReqMat;
    }

    public void setWeek8ReqMat(String week8ReqMat) {
        this.week8ReqMat = week8ReqMat;
    }

    public String getWeek9ReqMat() {
        return week9ReqMat;
    }

    public void setWeek9ReqMat(String week9ReqMat) {
        this.week9ReqMat = week9ReqMat;
    }

    public String getWeek10ReqMat() {
        return week10ReqMat;
    }

    public void setWeek10ReqMat(String week10ReqMat) {
        this.week10ReqMat = week10ReqMat;
    }

    public String getWeek11ReqMat() {
        return week11ReqMat;
    }

    public void setWeek11ReqMat(String week11ReqMat) {
        this.week11ReqMat = week11ReqMat;
    }

    public String getWeek12ReqMat() {
        return week12ReqMat;
    }

    public void setWeek12ReqMat(String week12ReqMat) {
        this.week12ReqMat = week12ReqMat;
    }

    public String getWeek13ReqMat() {
        return week13ReqMat;
    }

    public void setWeek13ReqMat(String week13ReqMat) {
        this.week13ReqMat = week13ReqMat;
    }

    public String getWeek14ReqMat() {
        return week14ReqMat;
    }

    public void setWeek14ReqMat(String week14ReqMat) {
        this.week14ReqMat = week14ReqMat;
    }

    public String getWeek15ReqMat() {
        return week15ReqMat;
    }

    public void setWeek15ReqMat(String week15ReqMat) {
        this.week15ReqMat = week15ReqMat;
    }

    public String getCourseNotesAndTextBooks() {
        return courseNotesAndTextBooks;
    }

    public void setCourseNotesAndTextBooks(String courseNotesAndTextBooks) {
        this.courseNotesAndTextBooks = courseNotesAndTextBooks;
    }

    public String getSuggestedReadingsAndMaterials() {
        return suggestedReadingsAndMaterials;
    }

    public void setSuggestedReadingsAndMaterials(String suggestedReadingsAndMaterials) {
        this.suggestedReadingsAndMaterials = suggestedReadingsAndMaterials;
    }

    public LectureConfig(int lecture_id, String lecture_Name, String lecturer_Name, String lecture_Code, String semester, String theory_Hour_In_Week,
                         String application_or_Lab_Hour_In_Week, String local_Credits, String ECTS,
                         String prerequisites, String course_Language, String course_Type,
                         String course_Level, String teaching_Methods_and_Techniques,
                         String course_Coordinator, String assistant,
                         String course_Objectives, String learning_Outcomes, String course_Description,
                         String course_Category ) {
        this.lecture_Code = lecture_Code;
        this.semester = semester;
        this.theory_Hour_In_Week = theory_Hour_In_Week;
        this.application_or_Lab_Hour_In_Week = application_or_Lab_Hour_In_Week;
        this.local_Credits = local_Credits;
        this.ECTS = ECTS;
        this.prerequisites = prerequisites;
        this.course_Language = course_Language;
        this.course_Type = course_Type;
        this.course_Level = course_Level;
        this.teaching_Methods_and_Techniques = teaching_Methods_and_Techniques;
        this.course_Coordinator = course_Coordinator;
        this.assistant = assistant;
        this.course_Objectives = course_Objectives;
        this.learning_Outcomes = learning_Outcomes;
        this.course_Description = course_Description;
        this.course_Category = course_Category;
        this.lecture_id = lecture_id;
        this.lecture_Name = lecture_Name;
        this.lecturer_Name = lecturer_Name;
    }

    public LectureConfig(int lecture_id, String lecture_Name, String lecturer_Name, String lecture_Code, String semester, String theory_Hour_In_Week, String application_or_Lab_Hour_In_Week, String local_Credits, String ECTS, String prerequisites, String course_Language, String course_Type, String course_Level, String teaching_Methods_and_Techniques, String course_Coordinator, String assistant, String course_Objectives, String learning_Outcomes, String course_Description, String course_Category, String week1Subjects, String week2Subjects, String week3Subjects, String week4Subjects, String week5Subjects, String week6Subjects, String week7Subjects, String week8Subjects, String week9Subjects, String week10Subjects, String week11Subjects, String week12Subjects, String week13Subjects, String week14Subjects, String week15Subjects, String week1ReqMat, String week2ReqMat, String week3ReqMat, String week4ReqMat, String week5ReqMat, String week6ReqMat, String week7ReqMat, String week8ReqMat, String week9ReqMat, String week10ReqMat, String week11ReqMat, String week12ReqMat, String week13ReqMat, String week14ReqMat, String week15ReqMat, String courseNotesAndTextBooks, String suggestedReadingsAndMaterials) {
        this.lecture_id = lecture_id;
        this.lecture_Name = lecture_Name;
        this.lecturer_Name = lecturer_Name;
        this.lecture_Code = lecture_Code;
        this.semester = semester;
        this.theory_Hour_In_Week = theory_Hour_In_Week;
        this.application_or_Lab_Hour_In_Week = application_or_Lab_Hour_In_Week;
        this.local_Credits = local_Credits;
        this.ECTS = ECTS;
        this.prerequisites = prerequisites;
        this.course_Language = course_Language;
        this.course_Type = course_Type;
        this.course_Level = course_Level;
        this.teaching_Methods_and_Techniques = teaching_Methods_and_Techniques;
        this.course_Coordinator = course_Coordinator;
        this.assistant = assistant;
        this.course_Objectives = course_Objectives;
        this.learning_Outcomes = learning_Outcomes;
        this.course_Description = course_Description;
        this.course_Category = course_Category;
        this.week1Subjects = week1Subjects;
        this.week2Subjects = week2Subjects;
        this.week3Subjects = week3Subjects;
        this.week4Subjects = week4Subjects;
        this.week5Subjects = week5Subjects;
        this.week6Subjects = week6Subjects;
        this.week7Subjects = week7Subjects;
        this.week8Subjects = week8Subjects;
        this.week9Subjects = week9Subjects;
        this.week10Subjects = week10Subjects;
        this.week11Subjects = week11Subjects;
        this.week12Subjects = week12Subjects;
        this.week13Subjects = week13Subjects;
        this.week14Subjects = week14Subjects;
        this.week15Subjects = week15Subjects;
        this.week1ReqMat = week1ReqMat;
        this.week2ReqMat = week2ReqMat;
        this.week3ReqMat = week3ReqMat;
        this.week4ReqMat = week4ReqMat;
        this.week5ReqMat = week5ReqMat;
        this.week6ReqMat = week6ReqMat;
        this.week7ReqMat = week7ReqMat;
        this.week8ReqMat = week8ReqMat;
        this.week9ReqMat = week9ReqMat;
        this.week10ReqMat = week10ReqMat;
        this.week11ReqMat = week11ReqMat;
        this.week12ReqMat = week12ReqMat;
        this.week13ReqMat = week13ReqMat;
        this.week14ReqMat = week14ReqMat;
        this.week15ReqMat = week15ReqMat;
        this.courseNotesAndTextBooks = courseNotesAndTextBooks;
        this.suggestedReadingsAndMaterials = suggestedReadingsAndMaterials;
    }

    public LectureConfig(int lecture_id, String lecture_Name, String lecturer_Name) {
        this.lecture_id = lecture_id;
        this.lecture_Name = lecture_Name;
        this.lecturer_Name = lecturer_Name;
    }

    public int getLecture_id() {
        return lecture_id;
    }

    public void setLecture_id(int lecture_id) {
        this.lecture_id = lecture_id;
    }

    public String getLecture_Name() {
        return lecture_Name;
    }

    public void setLecture_Name(String lecture_Name) {
        this.lecture_Name = lecture_Name;
    }

    public String getLecturer_Name() {
        return lecturer_Name;
    }

    public void setLecturer_Name(String lecturer_Name) {
        this.lecturer_Name = lecturer_Name;
    }


}

