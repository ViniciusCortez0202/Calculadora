/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calculadora;

import java.awt.Color;
import java.awt.Font;
//import java.awt.Image;
//import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.file.Files;
//import java.net.URL;
import javax.swing.*;

/**
 *
 * @author Aluno
 */
public class Cal extends JFrame implements ActionListener, IOperacoes, IValores{

    private final JLabel resultado = new JLabel("0");
    private final JButton[] botao = new JButton[20];
    private final JLabel icone = new JLabel("Calculadora");
    //private final ImageIcon imagem = new ImageIcon("iconcalculadora.png");
    //URL url = this.getClass().getResource("iconcalculadora.png");
    //Image icon = Toolkit.getDefaultToolkit().getImage(url);
    private int operacao = 0;
    private int tamanho = 0;
    private boolean situacaoResposta = false;
    private boolean pantes = false, pdepois = false;
    
    private void valores() {
        int j = 0;
        for (int i = 1; i <= 9; i++) {
            botao[j++] = new JButton("" + i);
        }

        botao[j++] = new JButton("+");
        botao[j++] = new JButton("-");
        botao[j++] = new JButton("*");
        botao[j++] = new JButton("/");
        botao[j++] = new JButton("0");
        botao[j++] = new JButton(".");
        botao[j++] = new JButton("x²");
        botao[j++] = new JButton("CE");
        botao[j++] = new JButton("<<");
        botao[j++]  = new JButton("C");
        botao[j] = new JButton("=");
    }

    public Cal() {

        valores();
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        super.setSize(266, 419);
        super.setLocationRelativeTo(null);
        super.setLayout(null);
        super.getContentPane().setBackground(Color.WHITE);
        //this.setIconImage(icon);
        
        resultado.setOpaque(true);
        resultado.setBounds(10, 10, 230, 50);
        resultado.setBackground(Color.BLACK);
        resultado.setForeground(Color.red);
        resultado.setFont(new Font("Arial", 0, 18));
        super.getContentPane().add(resultado);
        
        int g = 0;
        int w = 10, h = 80;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                botao[g].setBounds(w, h, 50, 50);
                super.getContentPane().add(botao[g++]);
                w += 60;
            }
            w = 10;
            h += 60;
        }
        
        h = 80;
        for (int i = 9; i < 13; i++) {
            botao[i].setBounds(190, h, 50, 50);
            super.getContentPane().add(botao[i]);
            h += 60;
        }
        
        w = 10;
        for(int i = 13; i < 16; i++)
        {
            botao[i].setBounds(w, h - 60, 50, 50);
            super.getContentPane().add(botao[i]);
            w += 60;
        }
        
        w = 10;
        for(int i = 16; i < 20; i++)
        {
            botao[i].setBounds(w, h, 50, 50);
            super.getContentPane().add(botao[i]);
            w += 60;
        }
        
        for (int i = 0; i < 20; i++) {
            botao[i].addActionListener(this);
        }
        
        super.setVisible(true);
    }
    @Override
    public String soma(double a, double b)
    {
        double c = a + b;
        
        return c + "";
    }
    @Override
    public String divisao(double a, double b)
    {
        if(b == 0)
        {
            JOptionPane.showMessageDialog(null, "Divisão indefinida");
            return "0";
        }
        
        double c = a / b;
       
        return c + "";
    }    
    @Override
    public String multiplicacao(double a, double b)
    {
        double c = a * b;
        
        return c + "";
    }     
    @Override
    public String subtracao(double a, double b)
    {
        double c = a - b;
        
        return c + "";
    } 
    @Override
    public String quadrado(double a)
    {
        double q = Math.pow(a, 2);
        
        return q + "";
    }
    @Override
    public String valor1()
    {
        return resultado.getText().substring(0, this.tamanho - 1);
    }
    @Override
    public String valor2()
    {
        return resultado.getText().substring(this.tamanho);
    }
    private boolean resposta()
    {
        if(this.operacao == 5)
        {
            double valor1 = Double.parseDouble(valor1());  
            String q = quadrado(valor1);
            resultado.setText(q);
            this.operacao = 0;
            this.tamanho = 0;
            this.situacaoResposta = true;
            this.pantes = this.pdepois = false;
            return true;
        }
               
        double valor1 = Double.parseDouble(valor1());
        double valor2 = Double.parseDouble(valor2());
               
        if(this.operacao == 1)
        {
            String s = soma(valor1, valor2);
            resultado.setText(s);
        }
        if(this.operacao == 2)
        {
            String su = subtracao(valor1, valor2);
            resultado.setText(su);
        }
        if(this.operacao == 3)
        {
            String m = multiplicacao(valor1, valor2);
            resultado.setText(m);
        }
        if(this.operacao == 4)
        {
            String d = divisao(valor1, valor2);
            resultado.setText(d);
        }
        this.operacao = 0;
        this.tamanho = 0;
        this.situacaoResposta = true;
        this.pantes = this.pdepois = false;
        return true;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        
        if(e.getSource() == botao[0])
        {
            if(resultado.getText().equalsIgnoreCase("0") || this.situacaoResposta == true)
            {
                resultado.setText("1");
                this.situacaoResposta = false;
            }
            else
            {
                resultado.setText(resultado.getText() + 1);
            }
            
        }
        else if(e.getSource() == botao[1])
        {
            if(resultado.getText().equalsIgnoreCase("0") || this.situacaoResposta == true)
            {
                resultado.setText("2");
                this.situacaoResposta = false;
            }
            else
            {
                resultado.setText(resultado.getText() + 2);
            }
            
        }
        else if(e.getSource() == botao[2])
        {
            if(resultado.getText().equalsIgnoreCase("0") || this.situacaoResposta == true)
            {
                resultado.setText("3");
                this.situacaoResposta = false;
            }
            else
            {
                resultado.setText(resultado.getText() + 3);
            }
        }        
        else if(e.getSource() == botao[3])
        {
            if(resultado.getText().equalsIgnoreCase("0") || this.situacaoResposta == true)
            {
                resultado.setText("4");
                this.situacaoResposta = false;
            }
            else
            {
                resultado.setText(resultado.getText() + 4);
            }
        }
        else if(e.getSource() == botao[4])
        {
            if(resultado.getText().equalsIgnoreCase("0") || this.situacaoResposta == true)
            {
                resultado.setText("5");
                this.situacaoResposta = false;
            }
            else
            {
                resultado.setText(resultado.getText() + 5);
            }
        }
        else if(e.getSource() == botao[5])
        {
            if(resultado.getText().equalsIgnoreCase("0") || this.situacaoResposta == true)
            {
                resultado.setText("6");
                this.situacaoResposta = false;
            }
            else
            {
                resultado.setText(resultado.getText() + 6);
            }
        }
        else if(e.getSource() == botao[6])
        {
            if(resultado.getText().equalsIgnoreCase("0") || this.situacaoResposta == true)
            {
                resultado.setText("7");
                this.situacaoResposta = false;
            }
            else
            {
                resultado.setText(resultado.getText() + 7);
            }
        }
        else if(e.getSource() == botao[7])
        {
            if(resultado.getText().equalsIgnoreCase("0") || this.situacaoResposta == true)
            {
                resultado.setText("8");
                this.situacaoResposta = false;
            }
            else
            {
                resultado.setText(resultado.getText() + 8);
            }
        }
        else if(e.getSource() == botao[8])
        {
            if(resultado.getText().equalsIgnoreCase("0") || this.situacaoResposta == true)
            {
                resultado.setText("9");
                this.situacaoResposta = false;
            }
            else
            {
                resultado.setText(resultado.getText() + 9);
            }
        }
        else if(e.getSource() == botao[9])//Soma
        {
            
            if(this.tamanho == resultado.getText().length())
            {
                String a = resultado.getText().substring(0, resultado.getText().length()-1);
                resultado.setText(a + "+");
            }
            else if(this.operacao == 0)
            {
                resultado.setText(resultado.getText()+"+");
                this.situacaoResposta = false;
            }
            else if(this.operacao != 0)
            {
                resposta();
                resultado.setText(resultado.getText()+"+");
            }
            this.operacao = 1;            
            this.tamanho = resultado.getText().length();   
            this.situacaoResposta = false;
        }
        else if(e.getSource() == botao[10])//Subtração
        {
            if(this.tamanho == resultado.getText().length())
            {
                String a = resultado.getText().substring(0, resultado.getText().length()-1);
                resultado.setText(a + "-");                
            }
            else if(this.operacao == 0)
            {
                resultado.setText(resultado.getText()+"-");
                this.situacaoResposta = false;
            }
            else if(this.operacao != 0)
            {
                resposta();
                resultado.setText(resultado.getText()+"-");
            }
            this.operacao = 2;
            this.tamanho = resultado.getText().length();
            this.situacaoResposta = false;
        }
        else if(e.getSource() == botao[11])//Multiplicação
        {
            if(this.tamanho == resultado.getText().length())
            {
                String a = resultado.getText().substring(0, resultado.getText().length()-1);
                resultado.setText(a + "*");                
            }
            else if(this.operacao == 0)
            {
                resultado.setText(resultado.getText()+"*");
                this.situacaoResposta = false;
            }
            else if(this.operacao != 0)
            {
                resposta();
                resultado.setText(resultado.getText()+"*");
            }
            this.operacao = 3;
            this.tamanho = resultado.getText().length();
            this.situacaoResposta = false;
        }
        else if(e.getSource() == botao[12])//Divisão
        {
            if(this.tamanho == resultado.getText().length())
            {
                String a = resultado.getText().substring(0, resultado.getText().length()-1);
                resultado.setText(a + "/");                
            }
            else if(this.operacao == 0)            
            {
                resultado.setText(resultado.getText()+"/");
                this.situacaoResposta = false;
            }
            else if(this.operacao != 0)
            {
                resposta();
                resultado.setText(resultado.getText()+"/");
            }
            this.operacao = 4;
            this.tamanho = resultado.getText().length();
            this.situacaoResposta = false;
        }
        else if(e.getSource() == botao[13])
        {
            if(this.situacaoResposta == true)
            {
                resultado.setText("0");
                this.situacaoResposta = false;
            }
            if(!(resultado.getText().equalsIgnoreCase("0")))
            {
                resultado.setText(resultado.getText() + 0);
            }
        }
        else if(e.getSource() == botao[14])//ponto
        {
            if(this.situacaoResposta == false)
            {
                if(resultado.getText().length() != this.tamanho)
                {
                    if(resultado.getText().length() > this.tamanho && pantes == false && this.operacao == 0)
                    {
                        resultado.setText(resultado.getText() + ".");
                        this.pantes = true;
                    }
                    else if(this.operacao != 0 && pdepois == false)
                    {
                        resultado.setText(resultado.getText() + ".");
                        this.pdepois = true;
                    }
                }
            }
        }
        else if(e.getSource() == botao[15])//quadrado
        {
            if(this.operacao == 0)
            {
                this.operacao = 5;
                this.tamanho = resultado.getText().length() + 1;
                resposta();
            }
        }
        else if(e.getSource() == botao[16])//CE
        {
            if(this.tamanho > 0)
            {
                String ce = resultado.getText().substring(0, this.tamanho);
                resultado.setText(ce);
            }
        }
        else if(e.getSource() == botao[17])//delete
        {
            if(!this.situacaoResposta == true)
            {
                String valorAntes = resultado.getText();
                String deletado = resultado.getText().substring(0, resultado.getText().length() - 1);
                if(resultado.getText().length() < this.tamanho)
                {
                    this.tamanho = 0;
                    this.operacao = 0;
                    this.situacaoResposta = false;
                    this.pdepois = false;
                }           
                if(resultado.getText().charAt(valorAntes.length() - 1) == '.' && resultado.getText().length() > this.tamanho)
                {
                    this.pantes = false;
                }            
                if(resultado.getText().equalsIgnoreCase("") || resultado.getText().length() == 1)
                {
                    resultado.setText("0");
                }
                else if(!resultado.getText().equalsIgnoreCase("0"))
                {
                    resultado.setText(deletado);
                }   
            }
        }
        else if(e.getSource() == botao[18])//Limpat tela
        {
            resultado.setText("0");
            this.operacao = 0;
            this.tamanho = 0; 
            this.pantes = this.pdepois = false;
        }
        else if(e.getSource() == botao[19])
        {
            if(resultado.getText().length() > this.tamanho && this.tamanho != 0)
            {
                resposta();
            }
        }
    }
}