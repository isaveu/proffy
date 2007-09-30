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

import java.awt.Component;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import pigeon.model.Clock;
import pigeon.model.Season;
import pigeon.model.Time;
import pigeon.model.ValidationException;

/**
    Edits the list of times associated with a clock.

    The ClockSummary final class edits the higher level info like open / close times.
*/
final class ClockEditor extends javax.swing.JPanel
{

    private static final long serialVersionUID = 7677569341121266746L;

    private final Clock clock;
    private final int daysInRace;
    private final Season season;
    private final Configuration configuration;

    private ClockEditor(Clock clock, int daysInRace, Season season, Configuration configuration)
    {
        this.clock = clock;
        this.daysInRace = daysInRace;
        this.season = season;
        this.configuration = configuration;
        initComponents();
        timesTable.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
            public void valueChanged(ListSelectionEvent e) {
                refreshButtons();
            }
        });
        clockTimesPanel.setBorder(new javax.swing.border.TitledBorder("Clock Times For " + clock.getMember()));

        birdsEnteredText.setValue(new Integer(clock.getBirdsEntered()));
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
        java.awt.GridBagConstraints gridBagConstraints;

        clockTimesPanel = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        timesTable = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        addButton = new javax.swing.JButton();
        editButton = new javax.swing.JButton();
        removeButton = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        birdsEnteredText = new javax.swing.JFormattedTextField();

        setLayout(new java.awt.BorderLayout());

        clockTimesPanel.setLayout(new java.awt.GridBagLayout());

        clockTimesPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder("Clock Times For")));
        jScrollPane1.setViewportView(timesTable);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        clockTimesPanel.add(jScrollPane1, gridBagConstraints);

        addButton.setText("Add Ring Number");
        addButton.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                addButtonActionPerformed(evt);
            }
        });

        jPanel1.add(addButton);

        editButton.setText("Edit Ring Number");
        editButton.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                editButtonActionPerformed(evt);
            }
        });

        jPanel1.add(editButton);

        removeButton.setText("Remove Ring Number");
        removeButton.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                removeButtonActionPerformed(evt);
            }
        });

        jPanel1.add(removeButton);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        clockTimesPanel.add(jPanel1, gridBagConstraints);

        jLabel1.setText("Please enter details for all the birds timed in this clock.");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        clockTimesPanel.add(jLabel1, gridBagConstraints);

        jLabel2.setText("No. Birds entered");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        clockTimesPanel.add(jLabel2, gridBagConstraints);

        birdsEnteredText.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(java.text.NumberFormat.getIntegerInstance())));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        clockTimesPanel.add(birdsEnteredText, gridBagConstraints);

        add(clockTimesPanel, java.awt.BorderLayout.CENTER);

    }// </editor-fold>//GEN-END:initComponents

    private void editButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_editButtonActionPerformed
    {//GEN-HEADEREND:event_editButtonActionPerformed
        try {
            int index = timesTable.getSelectedRow();
            Time time = clock.getTimes().get(index);
            RingTimeEditor.editEntry(this, time, daysInRace, season, configuration);
            reloadTimesTable();
        } catch (UserCancelledException e) {
        }
    }//GEN-LAST:event_editButtonActionPerformed

    private void removeButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_removeButtonActionPerformed
    {//GEN-HEADEREND:event_removeButtonActionPerformed
        int index = timesTable.getSelectedRow();
        Time time = clock.getTimes().get(index);
        clock.removeTime(time);
        reloadTimesTable();
    }//GEN-LAST:event_removeButtonActionPerformed

    private void addButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_addButtonActionPerformed
    {//GEN-HEADEREND:event_addButtonActionPerformed
        try {
            Time time = RingTimeEditor.createEntry(this, daysInRace, season, configuration);
            clock.addTime(time);
            reloadTimesTable();
        } catch (UserCancelledException e) {
        }
    }//GEN-LAST:event_addButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addButton;
    private javax.swing.JFormattedTextField birdsEnteredText;
    private javax.swing.JPanel clockTimesPanel;
    private javax.swing.JButton editButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton removeButton;
    private javax.swing.JTable timesTable;
    // End of variables declaration//GEN-END:variables

    public static void editClockResults(Component parent, Clock clock, int daysInRace, Season season, Configuration configuration)
    {
        ClockEditor panel = new ClockEditor(clock, daysInRace, season, configuration);
        Object[] options = {"Finished"};
        while (true) {
            int result = JOptionPane.showOptionDialog(parent, panel, "Clock Times", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
            try {
                panel.updateClockObject();
                break;
            } catch (ValidationException e) {
                e.displayErrorDialog(panel);
            }
        }
    }
    
    private void updateClockObject() throws ValidationException
    {
        clock.setBirdsEntered(Integer.parseInt(birdsEnteredText.getText()));
    }

    private void reloadTimesTable()
    {
        timesTable.setModel(new TimesTableModel(clock, daysInRace, true));
        timesTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        refreshButtons();
    }

    private void refreshButtons()
    {
        final boolean isSomethingSelected = timesTable.getSelectedRow() != -1;
        removeButton.setEnabled(isSomethingSelected);
        editButton.setEnabled(isSomethingSelected);
    }
}
