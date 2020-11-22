public class User {
    private int ID;
    private String firstName, lastName, ProgramAndPlan, AcademicLevel, ASURITE;

    public User(int ID, String firstName, String lastName, String ProgramAndPlan, String AcademicLevel, String ASURITE){
        this.ID = ID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.ProgramAndPlan = ProgramAndPlan;
        this.AcademicLevel = AcademicLevel;
        this.ASURITE = ASURITE;
    }

    //Getters and setters
    public int getID() { return ID; }
    public void setID(int ID) { this.ID = ID; }

    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) {this.lastName = lastName; }

    public String getProgramAndPlan() {return ProgramAndPlan; }
    public void setProgramAndPlan(String programAndPlan) {ProgramAndPlan = programAndPlan; }

    public String getAcademicLevel() { return AcademicLevel;}
    public void setAcademicLevel(String academicLevel) {AcademicLevel = academicLevel;}

    public String getASURITE() {return ASURITE;}
    public void setASURITE(String ASURITE) {this.ASURITE = ASURITE;}
}
