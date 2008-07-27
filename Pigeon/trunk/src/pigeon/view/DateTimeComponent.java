/*
    Copyright (C) 2005, 2006, 2007  Paul Richards.

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/

package pigeon.view;

import java.awt.Toolkit;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.text.ParseException;
import java.util.Date;
import javax.swing.JFormattedTextField;
import javax.swing.text.DateFormatter;
import javax.swing.text.DefaultFormatterFactory;
import pigeon.model.Constants;

/**
    This is a Swing GUI component which is for entering dates and times.
 
    It wraps up the DateFormatter stuff.
*/
public final class DateTimeComponent extends javax.swing.JPanel
{
    private static final long serialVersionUID = 5961795005357652678L;
    
    private DateTimeDisplayMode mode;
    
    /** Creates new form DateTimeComponent */
    public DateTimeComponent()
    {
        initComponents();
        textField.addFocusListener(new BeepingFocusListener());
        setMode(DateTimeDisplayMode.DATE_HOURS_MINUTES_SECONDS);
    }
    
    @Override
    public void setEnabled(boolean enabled)
    {
        super.setEnabled(enabled);
        textField.setEnabled(enabled);
    }

    public DateTimeDisplayMode getMode()
    {
        return mode;
    }
    
    /**
        Returns 'getMode().getFormat().toPattern()'.
        
        This allows the GUI to display error messages to the user which
        show the expected input format.
    */
    public String getFormatPattern()
    {
        return getMode().getFormat().toPattern();
    }

    public void setMode(DateTimeDisplayMode mode)
    {
        this.mode = mode;
        DateFormatter dateFormatter = new DateFormatter(mode.getFormat());
        dateFormatter.setOverwriteMode(true);
        textField.setFormatterFactory(new DefaultFormatterFactory(dateFormatter));
        textField.setColumns(mode.getDisplayColumns() + 1);
        textField.setFocusLostBehavior(JFormattedTextField.COMMIT);
    }
    
    public Date getDate() throws ParseException
    {
        textField.commitEdit();
        return (Date)textField.getValue();
    }
    
    public void setDate(Date date)
    {
        if (mode.isIntendedFor24HourRelativeFormat()) {
            if (date.getTime() < 0 || date.getTime() >= Constants.MILLISECONDS_PER_DAY) {
                throw new IllegalArgumentException("Date conflicts with 'mode.isIntendedFor24HourRelativeFormat()'");
            }
        }
        textField.setValue(date);
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents()
    {
        java.awt.GridBagConstraints gridBagConstraints;

        textField = new javax.swing.JFormattedTextField();

        setLayout(new java.awt.BorderLayout());

        add(textField, java.awt.BorderLayout.CENTER);

    }// </editor-fold>//GEN-END:initComponents
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JFormattedTextField textField;
    // End of variables declaration//GEN-END:variables
    
}
