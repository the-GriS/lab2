package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static java.lang.Math.*;


public class MainFrame extends JFrame {

    private static final int WIDTH = 400;
    private static final int HEIGHT = 420;

    private Double sum = 0.0;

    private JTextField textFieldX;
    private JTextField textFieldY;
    private JTextField textFieldZ;

    private JTextField textFieldResult;

    private ButtonGroup radioButtons = new ButtonGroup();

    private Box hboxFormulaType = Box.createHorizontalBox();

    private int formulaID = 1;

    public Double calculate1(Double x, Double y, Double z)
    {
        return  (pow(cos(pow(E, x)) + log(pow(1 + y, 2)) + pow(pow(E, cos(x) + pow(sin(PI*z),2)), 1.0/2) + pow(1/x, 1.0/2) + cos(pow(y, 2)), sin(z)));
    }

    public Double calculate2(Double x, Double y, Double z)
    {
        return pow(1 + x*x, 1.0/y)/pow(E, sin(z) + x);
    }

    private void addRadioButton(String buttonName, final int formulaID)
    {
        JRadioButton button = new JRadioButton(buttonName);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainFrame.this.formulaID = formulaID;
                //imagePane.updateUI();
            }
        });
        radioButtons.add(button);
        hboxFormulaType.add(button);
    }

    public MainFrame()
    {
        super("Вычисление формулы");
        setSize(WIDTH, HEIGHT);
        Toolkit kit = Toolkit.getDefaultToolkit();
        setLocation((kit.getScreenSize().width - WIDTH) / 2,
                (kit.getScreenSize().height - HEIGHT) / 2);

        hboxFormulaType.add(Box.createHorizontalGlue());
        addRadioButton("Формула1", 1);
        addRadioButton("Формула2", 2);
        radioButtons.setSelected(radioButtons.getElements().nextElement().getModel(), true);
        hboxFormulaType.add(Box.createHorizontalGlue());
        hboxFormulaType.setBorder(BorderFactory.createLineBorder(Color.YELLOW));

        JLabel labelForX = new JLabel("X:");
        textFieldX = new JTextField("0", 5);
        textFieldX.setMaximumSize(textFieldX.getPreferredSize());
        JLabel labelForY = new JLabel("Y:");
        textFieldY = new JTextField("0", 5);
        textFieldY.setMaximumSize(textFieldY.getPreferredSize());
        JLabel labelForZ = new JLabel("Z:");
        textFieldZ = new JTextField("0", 5);
        textFieldZ.setMaximumSize(textFieldZ.getPreferredSize());

        Box hboxVariables = Box.createHorizontalBox();
        hboxVariables.setBorder(BorderFactory.createLineBorder(Color.RED));
        //hboxVariables.add(Box.createHorizontalGlue());
        hboxVariables.add(labelForX);
        hboxVariables.add(Box.createHorizontalStrut(10));
        hboxVariables.add(textFieldX);
        //hboxVariables.add(Box.createHorizontalStrut(30));
        hboxVariables.add(Box.createHorizontalGlue());
        hboxVariables.add(labelForY);
        hboxVariables.add(Box.createHorizontalStrut(10));
        hboxVariables.add(textFieldY);
        //hboxVariables.add(Box.createHorizontalStrut(30));
        hboxVariables.add(Box.createHorizontalGlue());
        hboxVariables.add(labelForZ);
        hboxVariables.add(Box.createHorizontalStrut(10));
        hboxVariables.add(textFieldZ);
        //hboxVariables.add(Box.createHorizontalGlue());

        JLabel labelForResult = new JLabel("Результат: ");
        textFieldResult = new JTextField("0", 30);
        textFieldResult.setMaximumSize(textFieldResult.getPreferredSize());
        Box hboxResult = Box.createHorizontalBox();
        hboxResult.add(Box.createHorizontalGlue());
        hboxResult.add(labelForResult);
        hboxResult.add(Box.createHorizontalStrut(10));
        hboxResult.add(textFieldResult);
        hboxResult.add(Box.createHorizontalGlue());
        hboxResult.setBorder(BorderFactory.createLineBorder(Color.BLUE));

        JButton buttonCalc = new JButton("Вычислить");
        buttonCalc.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try
                {
                    Double x = Double.parseDouble(textFieldX.getText());
                    Double y = Double.parseDouble(textFieldY.getText());
                    Double z = Double.parseDouble(textFieldZ.getText());
                    Double result;
                    if (formulaID == 1) result = calculate1(x, y, z);
                    else result = calculate2(x, y, z);
                    textFieldResult.setText(result.toString());
                }
                /*catch (ArithmeticException ex)
                {
                    JOptionPane.showMessageDialog(MainFrame.this, "Невозможно провести вычисления", "Ошибочные данные", JOptionPane.WARNING_MESSAGE);
                }*/
                catch (NumberFormatException ex)
                {
                    JOptionPane.showMessageDialog(MainFrame.this, "Ошибка в формате числа с плавающей точкой", "Ошибочный формат числа", JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        JButton buttonReset = new JButton("Очистить поля");
        buttonReset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textFieldX.setText("0");
                textFieldY.setText("0");
                textFieldZ.setText("0");
                textFieldResult.setText("0");
            }
        });

        Box hboxButtons = Box.createHorizontalBox();
        hboxButtons.add(Box.createHorizontalGlue());
        hboxButtons.add(buttonCalc);
        hboxButtons.add(Box.createHorizontalStrut(30));
        hboxButtons.add(buttonReset);
        hboxButtons.add(Box.createHorizontalGlue());
        hboxButtons.setBorder(BorderFactory.createLineBorder(Color.GREEN));

        JButton buttonMPlus = new JButton("M+");
        buttonMPlus.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try
                {
                    Double x = Double.parseDouble(textFieldX.getText());
                    Double y = Double.parseDouble(textFieldY.getText());
                    Double z = Double.parseDouble(textFieldZ.getText());
                    if (formulaID == 1) sum += calculate1(x, y, z);
                    else sum += calculate2(x, y, z);
                    textFieldResult.setText(sum.toString());
                }
                catch (NumberFormatException ex)
                {
                    JOptionPane.showMessageDialog(MainFrame.this, "Ошибка в формате числа с плавающей точкой", "Ошибочный формат числа", JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        JButton buttonMC = new JButton("MC");
        buttonMC.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sum = 0.0;
                textFieldResult.setText(sum.toString());
            }
        });

        Box hboxButtons2 = Box.createHorizontalBox();
        hboxButtons2.add(buttonMPlus);
        hboxButtons2.add(Box.createHorizontalGlue());
        hboxButtons2.add(buttonMC);

        hboxButtons2.setBorder(BorderFactory.createLineBorder(Color.YELLOW));

        Box contentBox = Box.createVerticalBox();
        //contentBox.add(Box.createVerticalGlue());
        contentBox.add(hboxFormulaType);
        contentBox.add(hboxVariables);
        contentBox.add(hboxButtons2);
        contentBox.add(hboxButtons);
        contentBox.add(hboxResult);
        contentBox.add(Box.createVerticalGlue());
        getContentPane().add(contentBox, BorderLayout.CENTER);

    }
}
