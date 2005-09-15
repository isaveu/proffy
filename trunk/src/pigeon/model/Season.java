/*
 * Season.java
 *
 * Created on 21 August 2005, 17:42
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package pigeon.model;

import java.io.Serializable;
import java.util.Vector;

/**
 *
 * @author Paul
 */
public class Season implements Serializable {
    
    private static final long serialVersionUID = 42L;
    
    private String name;
    private Club club;
    private Vector<Result> results;
    
    /** Creates a new instance of Season */
    public Season() {
        club = new Club();
        results = new Vector<Result>();
    }

    public Club getClub() {
        return club;
    }

    public void setClub(Club club) {
        this.club = club;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
}