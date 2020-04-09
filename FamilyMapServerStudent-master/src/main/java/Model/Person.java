package Model;

import java.util.Objects;

public class Person {
    private String personID;
    private String associatedUsername;
    private String firstName;
    private String lastName;
    private String gender;
    private String fatherID;
    private String motherID;
    private String spouseID;

    /**
     * THis method will store the values of the params passed from the PersonDAO
     * @param personID
     * @param userName
     * @param firstName
     * @param lastName
     * @param gender
     * @param fatherID
     * @param motherID
     * @param spouseID
     */
    public Person(String personID, String userName, String firstName, String lastName, String gender, String fatherID, String motherID, String spouseID) {
        this.personID = personID;
        this.associatedUsername = userName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.fatherID = fatherID;
        this.motherID = motherID;
        this.spouseID = spouseID;
    }

    /**
     * This method will return the PersonID to the client
     * @return
     */
    public String getPersonID() {
        return personID;
    }

    /**
     * This method will store the personID value
     * @param personID
     */
    public void setPersonID(String personID) {
        this.personID = personID;
    }

    /**
     * This method will return the associated username to the client
     * @return
     */
    public String getUsername() {
        return associatedUsername;
    }

    /**
     * This method will store the associated username value
     * @param username
     */
    public void setUsername(String username) {
        this.associatedUsername = associatedUsername;
    }

    /**
     * This method will return the first name to the client
     * @return
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * This method will store the first name value
     * @param firstName
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * This method will return the last name to the client
     * @return
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * This method will store the last name value
     * @param lastName
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * This method will return the gender to the client
     * @return
     */
    public String getGender() {
        return gender;
    }

    /**
     * This method will store the gender value
     * @param gender
     */
    public void setGender(String gender) {
        this.gender = gender;
    }

    /**
     * This method will return the Father ID to the client
     * @return
     */
    public String getFatherID() {
        return fatherID;
    }

    /**
     * This method will store the Father ID value
     * @param fatherID
     */
    public void setFatherID(String fatherID) {
        this.fatherID = fatherID;
    }

    /**
     * This method will return the Mother ID to the client
     * @return
     */
    public String getMotherID() {
        return motherID;
    }

    /**
     * This method will store the Mother ID value
     * @param motherID
     */
    public void setMotherID(String motherID) {
        this.motherID = motherID;
    }

    /**
     * This method will return the Spouse ID to the client
     * @return
     */
    public String getSpouseID() {
        return spouseID;
    }

    /**
     * This method will store the spouse ID value
     * @param spouseID
     */
    public void setSpouseID(String spouseID) {
        this.spouseID = spouseID;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null)
            return false;
        if (o instanceof Person) {
            Person oPerson = (Person) o;
            return oPerson.getPersonID().equals(getPersonID()) &&
                    oPerson.getUsername().equals(getUsername()) &&
                    oPerson.getFirstName().equals(getFirstName()) &&
                    oPerson.getLastName().equals(getLastName()) &&
                    oPerson.getGender().equals(getGender()) &&
                    oPerson.getFatherID().equals(getFatherID()) &&
                    oPerson.getMotherID().equals(getMotherID()) &&
                    oPerson.getSpouseID().equals(getSpouseID());
        } else {
            return false;
        }
    }

}
