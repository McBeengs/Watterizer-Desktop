/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.watterizer.panels;

/**
 *
 * @author 15153769
 */
public class errortest {
    public static void main(String[] args) {
        new GenericErrorJFrame("Mensagem de teste bem bem grande", GenericErrorJFrame.ERROR_MESSAGE, "Não foi possível conectar-se a internet. Isso pode ter sido causado por alguma configuração errada no Sistema Operacional, uma queda no provedor ou "
                    + "um problema no cabeamento. Aguarde alguns instantes e tente novamente. Caso o problema persista, entre em contato com um administrador.", 0).setVisible(true);
    }
}
