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
  /* private Integer week;
    private String subjects;
   private String required_Material;
    private String course_Notes_or_Textbooks;
    private String suggested_Readings_or_Materials;
   private String assesment_Semester_Activities;
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

    public LectureConfig(int lecture_id, String lecture_Name, String lecturer_Name,String lecture_Code, String semester, String theory_Hour_In_Week,
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

    public LectureConfig(int lecture_id, String lecture_Name, String lecturer_Name) {
        this.lecture_id = lecture_id;
        this.lecture_Name = lecture_Name;
        this.lecturer_Name = lecturer_Name;
    }

    public LectureConfig(int lecture_id, String lecture_Name, String lecturer_Name,String lecture_Code) {
        this.lecture_id = lecture_id;
        this.lecture_Name = lecture_Name;
        this.lecturer_Name = lecturer_Name;
        this.lecture_Code = lecture_Code;
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

