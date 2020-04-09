package Result;

import Model.Person;

public class PersonResult {

    private String associatedUsername;
    private String personID;
    private String firstName;
    private String lastName;
    private String gender;
    private String fatherID;
    private String motherID;
    private String spouseID;
    private String message;
    private boolean success;

    /**
     * This will store the values from the person services
     * @param associatedUsername
     * @param personID
     * @param firstName
     * @param lastName
     * @param gender
     * @param fatherID
     * @param motherID
     * @param spouseID
     */
    public PersonResult(String associatedUsername, String personID, String firstName, String lastName, String gender, String fatherID, String motherID, String spouseID) {
        this.associatedUsername = associatedUsername;
        this.personID = personID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.fatherID = fatherID;
        this.motherID = motherID;
        this.spouseID = spouseID;
        this.success = true;
    }

    public PersonResult(Person person){
        this.associatedUsername = person.getUsername();
        this.personID = person.getPersonID();
        this.firstName = person.getFirstName();
        this.lastName = person.getLastName();
        this.gender = person.getGender();
        this.fatherID = person.getFatherID();
        this.motherID = person.getMotherID();
        this.spouseID = person.getSpouseID();
        this.success = true;
    }

    /**
     * This will store the message value from the person services
     * @param message
     */
    public PersonResult(String message) {
        this.message = message;
        this.success = false;
    }

    /**
     * This will reutrn the associated username to the client
     * @return
     */
    public String getAssociatedUsername() {
        return associatedUsername;
    }

    /**
     * This will store the associated username value from the person services
     * @param associatedUsername
     */
    public void setAssociatedUsername(String associatedUsername) {
        this.associatedUsername = associatedUsername;
    }

    /**
     * This will reutrn the person ID to the client
     * @return
     */
    public String getPersonID() {
        return personID;
    }

    /**
     * This will store the person ID value from the person services
     * @param personID
     */
    public void setPersonID(String personID) {
        this.personID = personID;
    }

    /**
     * This will reutrn the first name to the client
     * @return
     */
    public String getFistName() {
        return firstName;
    }

    /**
     * This will store the first name value from the person services
     * @param firstName
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * This will reutrn the last name to the client
     * @return
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * This will store the last name value from the person services
     * @param lastName
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * This will reutrn the gender to the client
     * @return
     */
    public String getGender() {
        return gender;
    }

    /**
     * This will store the gender value from the person services
     * @param gender
     */
    public void setGender(String gender) {
        this.gender = gender;
    }

    /**
     * This will reutrn the father ID to the client
     * @return
     */
    public String getFatherID() {
        return fatherID;
    }

    /**
     * This will store the father ID value from the person services
     * @param fatherID
     */
    public void setFatherID(String fatherID) {
        this.fatherID = fatherID;
    }

    /**
     * This will reutrn the mother ID to the client
     * @return
     */
    public String getMotherID() {
        return motherID;
    }

    /**
     * This will store the mother ID value from the person services
     * @param motherID
     */
    public void setMotherID(String motherID) {
        this.motherID = motherID;
    }

    /**
     * This will reutrn the SPOUSE id to the client
     * @return
     */
    public String getSpouseID() {
        return spouseID;
    }

    /**
     * This will store the SPOUSE id value from the person services
     * @param spouseID
     */
    public void setSpouseID(String spouseID) {
        this.spouseID = spouseID;
    }

    /**
     * This will reutrn the message to the client
     * @return
     */
    public String getMessage() {
        return message;
    }

    /**
     * This will store the message value from the person services
     * @param message
     */
    public void setMessage(String message) {
        this.message = message;
    }

    public boolean getSuccess(){ return success; }

    public void setSuccess(boolean success){ this.success = success; }
}
