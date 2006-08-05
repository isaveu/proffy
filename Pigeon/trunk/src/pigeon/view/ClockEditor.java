/*
 * Pigeon: A pigeon club race result management program.
 * Copyright (C) 2005-2006  Paul Richards
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 */


package pigeon.view;

import java.awt.Component;
import java.util.Date;
import javax.swing.DefaultCellEditor;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.table.TableCellEditor;
import pigeon.model.Clock;
import pigeon.model.Time;

/**
 * Edits the list of times associated with a clock.  The ClockSummary class edits the basic info like open / close times.
 * @author  pauldoo
 */
public class ClockEditor extends javax.swing.JPanel
{
    
    private static final long serialVersionUID = 42L;
    
    private Clock clock;
    
    /** Creates new form ClockEditor */
    public ClockEditor(Clock clock)
    {
        this.clock = clock;
        initComponents();
        clockTimesPanel.setBorder(new javax.swing.border.TitledBorder("Clock Times For " + clock.getMember()));
        reloadTimesTable();
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents()
    {
        clockTimesPanel = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        timesTable = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        addButton = new javax.swing.JButton();
        removeButton = new javax.swing.JButton();

        setLayout(new java.awt.BorderLayout());

        clockTimesPanel.setLayout(new java.awt.BorderLayout());

        clockTimesPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder("Clock Times For")));
        timesTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][]
            {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String []
            {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(timesTable);

        clockTimesPanel.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        addButton.setText("Add Ring Number");
        addButton.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                addButtonActionPerformed(evt);
            }
        });

        jPanel1.add(addButton);

        removeButton.setText("Rembove Ring Number");
        jPanel1.add(removeButton);

        clockTimesPanel.add(jPanel1, java.awt.BorderLayout.SOUTH);

        add(clockTimesPanel, java.awt.BorderLayout.CENTER);

    }// </editor-fold>//GEN-END:initComponents
    
    private void addButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_addButtonActionPerformed
    {//GEN-HEADEREND:event_addButtonActionPerformed
        String ringNumber = JOptionPane.showInputDialog(this, "Please enter the ring number", "New time", JOptionPane.QUESTION_MESSAGE);
        clock.addTime(new Time(ringNumber, 0));
        reloadTimesTable();
    }//GEN-LAST:event_addButtonActionPerformed
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addButton;
    private javax.swing.JPanel clockTimesPanel;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton removeButton;
    private javax.swing.JTable timesTable;
    // End of variables declaration//GEN-END:variables
    public static void editClockResults(Component parent, Clock clock)
    {
        ClockEditor panel = new ClockEditor(clock);
        Object[] options = {"Finished"};
        int result = JOptionPane.showOptionDialog(parent, panel, "Clock Times", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
    }
    
    private void reloadTimesTable()
    {
        timesTable.setModel(new TimesTableModel(clock, true));
        timesTable.setDefaultEditor(String.class, createTimeCellEditor());
    }
    
    private static TableCellEditor createTimeCellEditor()
    {
        JFormattedTextField formatter = new JFormattedTextField(Utilities.TIME_FORMAT);
        return new DefaultCellEditor(formatter);
    }
}
