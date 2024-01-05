/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.shoescare.custom;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author mhmmadhaibah
 */
public class WordWrapCellRenderer extends JTextArea implements TableCellRenderer
{
    public WordWrapCellRenderer()
    {
        setLineWrap(true);
        setWrapStyleWord(true);
    }
    
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column)
    {
        setText(value.toString());
        setSize(table.getColumnModel().getColumn(column).getWidth(), getPreferredSize().height);
        
        if (table.getRowHeight(row) < getPreferredSize().height)
        {
            table.setRowHeight(row, getPreferredSize().height);
        }
        
        if (table.getRowHeight(row) < 64)
        {
            table.setRowHeight(row, 64);
        }
        
        if (isSelected)
        {
            setBackground(table.getSelectionBackground());
        }
        else
        {
            setBackground(Color.WHITE);
        }
        
        return this;
    }
}
