/*
**The DivorceClass mainly encapsulates the request parameters
* sent from the client and serves as the interface for passing parameters
* between the modules. Support is mainly used for divorce alcohol crazy processing module,
* including information of divorce dispute category, gender of consultant, and error of double counter.
 */
package org.csu.edu.bean;

public class DivorceClass {
    private int id;
    private String sex;
    private String description;
    private String error;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
